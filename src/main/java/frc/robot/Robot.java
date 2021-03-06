package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.*;
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
        //Choose from AXIDRIVE (Axiom drivebase), TOASTER, PBOT20 (42D2), and COMPETITION
        RobotMap.setBot(RobotMap.BotNames.COMPETITION);
        System.out.println("Initializing" + RobotMap.botName);

        //drive settings
        Subsystems.driveBase.setDefaultCommand(new TankDrive());
        Subsystems.driveBase.cheesyDrive.setSafetyEnabled(false);

        //driver controls (buttons)
        UserInterface.driverController.RB.whenPressed(new SlowFast());

        //operator controls (buttons)
        UserInterface.operatorController.LB.whileHeld(new Vomit());
        UserInterface.operatorController.RB.whenPressed(new IntakeToggle());
        
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

        // Left joystick - intake & transversal
        if (UserInterface.operatorController.getRightJoystickY() >= 0.4) {
            Subsystems.intake.setIntakeMotors(0.9);
            Subsystems.transversal.setTransversalMotors(0.8);
        } else if (UserInterface.operatorController.getRightJoystickY() <= -0.4) {
            Subsystems.intake.setIntakeMotors(-0.9);
            Subsystems.transversal.setTransversalMotors(-0.8);
        } else {
            Subsystems.intake.stopIntakeMotors();
            Subsystems.transversal.stopTransversalMotors();
        }

        // Left trigger - hold for warmup flywheel
        boolean isLeftTriggerOn = UserInterface.operatorController.getLeftTrigger() >= 0.4;
        if (isLeftTriggerOn && !oldLeftTriggerOn) {
            Subsystems.flyboi.spinWheel(0.5);
        } else if (!isLeftTriggerOn && oldLeftTriggerOn) {
            Subsystems.flyboi.stopWheel();
        }
        oldLeftTriggerOn = isLeftTriggerOn;
        
        // Right trigger - hold for shoot sequence
        boolean isRightTriggerOn = UserInterface.operatorController.getRightTrigger() >= 0.4;
        if (isRightTriggerOn && !oldRightTriggerOn) {
            new ShootSequence().schedule();
        } else if (!isRightTriggerOn && oldRightTriggerOn) {
            new ShootStop().schedule();
        }
        oldRightTriggerOn = isRightTriggerOn;

        // D-Pad - climb
        int direction = UserInterface.operatorController.getPOVAngle();
        if (direction == 0) { //DPad up
            Subsystems.climber.extendClimber(0.5);
        } else if (direction == 180) { // DPad down
            Subsystems.climber.retractClimber(0.5);
        } else {
            Subsystems.climber.stopClimber();
        }
    }
}