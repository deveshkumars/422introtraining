package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The main Robot class whence all things come.
 */
public class Robot extends TimedRobot {

    public void robotInit() {
        // Setup bot choice, camera feed(s) - see FRC-20

        //drive settings
        // Subsystems.driveBase.cheesyDrive.setSafetyEnabled(false);

        //driver controls (buttons)

        //operator controls (buttons)
    }

    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    public void disabledInit() {
        System.out.println("Disabled Initialized");
        CommandScheduler.getInstance().cancelAll();
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
        // Continuous driver & operator controls go here
    }
}
