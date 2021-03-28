package frc.robot;

import java.util.AbstractMap;
import java.util.Map;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.autonomous.Autonomous;

public class ShuffleboardControl {

    private static Autonomous auto;

    private static SendableChooser<Autonomous.Path> autoChooserWidget;
    private static NetworkTableEntry selectedAutoLabelWidget;
    private static NetworkTableEntry autoSetupSuccessfulWidget;
    private static NetworkTableEntry pathWidget;

    private static NetworkTableEntry setSpeedWidget;
    private static NetworkTableEntry actualSpeedWidget;
    private static NetworkTableEntry isIntakeUpWidget;
    private static NetworkTableEntry encodersWidget;
    private static NetworkTableEntry gyroWidget;
    private static NetworkTableEntry cellCountWidget;
    private static NetworkTableEntry ballOverflowWidget;
    private static NetworkTableEntry matchTimeWidget;

    private static NetworkTableEntry operatorControllerWidget;

    // The number of ticks since autonomous was enabled. Used for the match time widget.
    private static int autoEnabledTicks = 100;

    // SHUFFLEBOARD

    /**
     * Arranges the Shuffleboard layout. An initiation method; should only be called once, in Robot.robotInit().
     */
    public static void layoutShuffleboard() {
        // Get references to tabs & layouts
        ShuffleboardTab autonomousTab = Shuffleboard.getTab("Autonomous");
        ShuffleboardTab teleopTab = Shuffleboard.getTab("Teleoperated");
        ShuffleboardTab buttonsTab = Shuffleboard.getTab("Buttons");

        ShuffleboardLayout chooseAutoLayout = autonomousTab.getLayout("Choose auto", BuiltInLayouts.kGrid)
            .withProperties(Map.of("number of columns", 1, "number of rows", 3))
            .withPosition(0, 0)
            .withSize(3, 3);
        ShuffleboardLayout controlsLayout = teleopTab.getLayout("Controls", BuiltInLayouts.kList)
            .withPosition(0, 0)
            .withSize(1, 3);

        // Setup autonomous tab

        autoChooserWidget = new SendableChooser<Autonomous.Path>();
        for (Autonomous.Path path : Autonomous.Path.values()) {
            autoChooserWidget.addOption(path.toString(), path);
        }

        chooseAutoLayout.add("Browse autos", autoChooserWidget)
            .withWidget(BuiltInWidgets.kComboBoxChooser)
            .withSize(0, 0); // = withPosition
        chooseAutoLayout.add("Choose selected", new InstantCommand(ShuffleboardControl::setAutonomous))
            .withWidget(BuiltInWidgets.kCommand)
            .withSize(0, 1);
        selectedAutoLabelWidget = chooseAutoLayout.add("Currently selected auto:", "Default")
            .withSize(0, 2).getEntry();

        double[] emptyDouble = new double[0];
        pathWidget = autonomousTab.add("Path", emptyDouble)
            .withWidget("Path")
            .withProperties(Map.of("year", 2020, "field width", 26*12 + 11.25, "field height", 52*12 + 5.25))
            .withPosition(3, 0)
            .withSize(2, 4).getEntry();

        autoSetupSuccessfulWidget = autonomousTab.add("Auto setup successful", false)
            .withWidget(BuiltInWidgets.kBooleanBox)
            .withPosition(5, 0)
            .withSize(3, 3).getEntry();

        autonomousTab.add("Party", false)
            .withWidget(BuiltInWidgets.kToggleButton)
            .withPosition(8, 0)
            .withSize(1, 3);

        // Setup teleoperated tab
        // ***** ADD CAMERA & FMS INFO WIDGET MANUALLY *****

        setSpeedWidget = controlsLayout.add("Set speed:", 0).getEntry();
        actualSpeedWidget = controlsLayout.add("Actual speed:", 0).getEntry();
        isIntakeUpWidget = controlsLayout.add("Intake up?", !RobotMap.isIntakeDown).withWidget(BuiltInWidgets.kBooleanBox).getEntry();
        encodersWidget = controlsLayout.add("Encoders", "404").getEntry();
        gyroWidget = controlsLayout.add("Gyro", "404").getEntry();

        cellCountWidget = teleopTab.add("Power cell count", 3)
            .withWidget(BuiltInWidgets.kDial)
            .withProperties(Map.of("min", 0, "max", 5))
            .withPosition(1, 0)
            .withSize(2, 2).getEntry();

        ballOverflowWidget = teleopTab.add("Ball overflow", false)
            .withWidget(BuiltInWidgets.kBooleanBox)
            .withProperties(Map.of("color when false", "#7E8083", "color when true", "#8b0000"))
            .withPosition(1, 2)
            .withSize(2, 1).getEntry();
        
        matchTimeWidget = teleopTab.add("Match time", 150)
            .withWidget("Match Time")
            .withPosition(6, 1)
            .withSize(3, 2).getEntry();

        // Setup buttons tab

        boolean[] emptyBool = new boolean[13];
        operatorControllerWidget = buttonsTab.add("Operator Controller", emptyBool)
            .withWidget("Xbox Controller")
            .withProperties(Map.ofEntries(  new AbstractMap.SimpleEntry<String, String>("a descr", ""),
                                            new AbstractMap.SimpleEntry<String, String>("b descr", ""),
                                            new AbstractMap.SimpleEntry<String, String>("x descr", ""),
                                            new AbstractMap.SimpleEntry<String, String>("y descr", ""),
                                            new AbstractMap.SimpleEntry<String, String>("lb descr", "Vomit"),
                                            new AbstractMap.SimpleEntry<String, String>("rb descr", "Intake extend/retract"),
                                            new AbstractMap.SimpleEntry<String, String>("ls descr", ""),
                                            new AbstractMap.SimpleEntry<String, String>("rs descr", ""),
                                            new AbstractMap.SimpleEntry<String, String>("left joystick descr", "Intake and transversal in/out"),
                                            new AbstractMap.SimpleEntry<String, String>("right joystick descr", ""),
                                            new AbstractMap.SimpleEntry<String, String>("left trigger descr", "Warm up flywheel"),
                                            new AbstractMap.SimpleEntry<String, String>("right trigger descr", "Shoot sequence"),
                                            new AbstractMap.SimpleEntry<String, String>("pov descr", "Climb"),
                                            
                                            new AbstractMap.SimpleEntry<String, String>("background color", "#333333"),
                                            new AbstractMap.SimpleEntry<String, String>("button color", "#666666"),
                                            new AbstractMap.SimpleEntry<String, String>("text color 1", "#f2f2f2"),
                                            new AbstractMap.SimpleEntry<String, String>("text color 2", "#cccccc")))
            .withPosition(0, 0)
            .withSize(4, 3).getEntry();
    }

    /**
     * Updates data used in Shuffleboard. This will be updated even if the robot is disabled.
     */
    public static void printDataToShuffleboard() {
        //control panel
        setSpeedWidget.setDouble(Subsystems.flyboi.wheelSpeed);
        actualSpeedWidget.setDouble(Subsystems.flyboi.getPower());

        //cell count
        cellCountWidget.setDouble(RobotMap.cellCount);
        ballOverflowWidget.setBoolean(RobotMap.cellCount > 5);

        //match time
        double time = DriverStation.getInstance().getMatchTime();
        if (time == -1 && DriverStation.getInstance().isDisabled()) time = 0;
        if (DriverStation.getInstance().isAutonomousEnabled()) autoEnabledTicks = 0;
        else autoEnabledTicks++;
        if (DriverStation.getInstance().isAutonomousEnabled() || (DriverStation.getInstance().isDisabled() && autoEnabledTicks < 50)) time += 135;
        matchTimeWidget.setDouble(time);

        //sensor values
        encodersWidget.setString(Subsystems.driveBase.getLeftPosition() + " L " + Subsystems.driveBase.getRightPosition() + " R");
        gyroWidget.setDouble(Subsystems.driveBase.getGyroAngle());
        isIntakeUpWidget.setBoolean(!RobotMap.isIntakeDown);
    }

    /**
     * Updates data used in Shuffleboard. Used for data that is only useful when the robot isn't being operated, such as pre-match checks or auto refreshing.
     */
    public static void disabledPeriodic() {
        //buttons
        boolean[] buttons = new boolean[13];
        buttons[8] = Math.abs(UserInterface.operatorController.getLeftJoystickX()) > 0.1 || Math.abs(UserInterface.operatorController.getLeftJoystickY()) > 0.1;
        buttons[9] = Math.abs(UserInterface.operatorController.getRightJoystickX()) > 0.1 || Math.abs(UserInterface.operatorController.getRightJoystickY()) > 0.1;
        operatorControllerWidget.setBooleanArray(buttons);

        //auto
        setAutonomous();
    }


    // AUTONOMOUS

    /**
     * Sets the current autonomous to the specified path.
     */
    public static void setAutonomous(Autonomous.Path path) {
        if(path == null) path = Autonomous.defaultAuto;
        try {
            auto = new Autonomous(path);
            autoSetupSuccessfulWidget.setBoolean(true);
            selectedAutoLabelWidget.setString(path.toString());
        } catch (Exception e) {
            selectedAutoLabelWidget.setString("Autonomous '" + path + "' not found. Setting to default path '" + Autonomous.defaultAuto + "'.");
            autoSetupSuccessfulWidget.setBoolean(false);
            e.printStackTrace();
            try {
                auto = new Autonomous(Autonomous.defaultAuto);
            } catch (Exception e1) {
                selectedAutoLabelWidget.setString("Neither selected auto '" + path + "'' nor default auto '" + Autonomous.defaultAuto + "' found. Do not run.");
                e1.printStackTrace();
            }
        }
    }

    /**
     * Sets the current autonomous to the choice selected in Shuffleboard.
     */
    public static void setAutonomous() {
        if (auto == null || auto.path != autoChooserWidget.getSelected()) setAutonomous(autoChooserWidget.getSelected());
    }

    public static Autonomous getAutonomous() {
        return auto;
    }

}