package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.*;
import frc.robot.userinterface.UIMap;
import frc.robot.userinterface.UserInterface;
import frc.robot.subsystems.*;

/**
 * The main Robot class whence all things come.
 */
public class Robot extends TimedRobot {
    
    private boolean oldLeftTriggerOn = false;
    private boolean oldRightTriggerOn = false;

    public Robot() {
        super(0.06);
    }

    public void robotInit() {
        RobotMap.setBot(RobotMap.BotNames.MECANUM);
        System.out.println("Initializing" + RobotMap.botName);

        //Choose from ControllerTank, ControllerTankFO (Field Oriented extras), JoystickTank, ControllerSplitArcade, ControllerSplitCurvature, and Wii
        UIMap.setDriveMode(UIMap.DriveMode.ControllerTank);
        System.out.println("Initializing" + UIMap.driveMode);

        //drive settings
        Subsystems.driveBase.cheesyDrive.setSafetyEnabled(false);

        //driver controls (buttons)
        UserInterface.driverController.RB.whenPressed(new SlowFast());
    
        ShuffleboardControl.layoutShuffleboard();
    }

    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        ShuffleboardControl.printDataToShuffleboard();
    }

    public void disabledInit() {
        System.out.println("Disabled Initialized");
        CommandScheduler.getInstance().cancelAll();
        ShuffleboardControl.disabledPeriodic();
    }

    public void disabledPeriodic() {}

    public void autonomousInit() {
        System.out.println("Autonomous Initalized");
        CommandScheduler.getInstance().cancelAll();

        // Schedule autonomous command to run
    }

    public void autonomousPeriodic() {}

    public void teleopInit() { 
        System.out.println("TeleOp Initalized");
        CommandScheduler.getInstance().cancelAll();
    }

    public void teleopPeriodic() {

        // double xcomp = UserInterface.driverController.getLeftJoystickX();
        // double ycomp = UserInterface.driverController.getLeftJoystickY();
        // double magnitude = Math.sqrt(xcomp*xcomp + ycomp*ycomp);
        double direction = UserInterface.driverController.getRadians(); // make sure it is driver controller
        double x = Math.cos(direction);
        double y = Math.sin(direction);
        double magnitude = UserInterface.driverController.getMagnitude();

        if (magnitude > 1) {
            magnitude =  1 //magnitude / 1.415 // ceiling of sqrt(2)
        }
        // if (Math.abs(xcomp+ycomp)>1||Math.abs(ycomp-xcomp)>1) {
        //     double cap
        //     cap = 1/(Math.Maximum(Math.abs(xcomp+ycomp), Math.abs(ycomp-xcomp)));
        // }
        // Subsystems.driveBase.setMotor((y + x)caprot, (y - x)caprot, (y - x)caprot, (y + x)caprot);

        // if (Math.abs(xcomp+ycomp)>1||Math.abs(ycomp-xcomp)>1) {
        //     double cap
        //     cap = 1/(Math.Maximum(Math.abs(xcomp+ycomp), Math.abs(ycomp-xcomp)));
        // }
    }
}