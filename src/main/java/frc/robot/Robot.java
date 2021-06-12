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
    
    public Robot() {
        super(0.06);
    }

    public void robotInit() {
        //Choose from AXIDRIVE (Axiom drivebase), TOASTER, PBOT20 (42D2), and COMPETITION
        RobotMap.setBot(RobotMap.BotNames.COMPETITION);
        System.out.println("Initializing" + RobotMap.botName);

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

        // Write the commands here
        new Autonomous().schedule();


    }

    public void autonomousPeriodic() {}

    public void teleopInit() { 
        System.out.println("TeleOp Initalized");
        CommandScheduler.getInstance().cancelAll();
    }

    public void teleopPeriodic() {
    }
}