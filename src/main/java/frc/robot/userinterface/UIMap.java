package frc.robot.userinterface;

import frc.robot.subsystems.*;
import frc.robot.commands.drivemodes.*;

/**
 * Ports for UI devices.
 */
public class UIMap {
    
    // UI Ports
    public static int driverXboxController;
    public static int leftJoystyx;
    public static int rightJoystyx;
    public static int wiimoteController;
    public static int operatorXboxController = 2;

    //driver control schemes
    public enum DriveMode {
        ControllerTank, //controller's left stick drives left side, right stick drives right side
        ControllerTankFancy, //ControllerTank with experimental features
        JoystickTank, //left joystick drives left side, right joystick drives right side
        ControllerSplitArcade, //left joystick controls speed, right joystick controls heading
        ControllerSplitCurvature, //left joystick controls speed, right joystick controls curvature and heading when right trigger held
        Wii, //2 drives forwards, 1 drives backwards, rotation controls heading
        MecanumDrive
    }

    public static DriveMode driveMode;

    /**
     * Sets the way the drivebase is controlled.
     * @param bot The desired control scheme.
     */
    public static void setDriveMode(DriveMode mode){
        driveMode = mode;

        //set up UI ports based on controller used by selected driveMode
        if (mode == DriveMode.MecanumDrive){
            driverXboxController = 1;
            leftJoystyx = 422;
            rightJoystyx = 422;
            wiimoteController = 0;
        }
        // else if (mode == DriveMode.Wii){
        //     driverXboxController = 1;
        //     leftJoystyx = 422;
        //     rightJoystyx = 422;
        //     wiimoteController = 3;
        // }
        // else {
        //     driverXboxController = 1;
        //     leftJoystyx = 422;
        //     rightJoystyx = 422;
        //     wiimoteController = 422;
        // }

        //set default drivebase command based on selected driveMode
        switch (mode) {
            // case ControllerTank:
            //     Subsystems.driveBase.setDefaultCommand(new ControllerTankDrive());
            //     break;
            // case ControllerTankFancy:
            //     Subsystems.driveBase.setDefaultCommand(new ControllerTankFancy());
            //     break;
            // case JoystickTank:
            //     Subsystems.driveBase.setDefaultCommand(new JoystickTankDrive());
            //     break;
            // case ControllerSplitArcade:
            //     Subsystems.driveBase.setDefaultCommand(new ControllerSplitArcadeDrive());
            //     break;
            // case ControllerSplitCurvature:
            //     Subsystems.driveBase.setDefaultCommand(new ControllerSplitCurvatureDrive());
            //     break;
            // case Wii:
            //     Subsystems.driveBase.setDefaultCommand(new WiiDrive());
            //     break;
            case MecanumDrive:
                Subsystems.driveBase.setDefaultCommand(new MecanumDrive());

        }
    }

}
