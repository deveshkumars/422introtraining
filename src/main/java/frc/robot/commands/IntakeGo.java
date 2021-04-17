package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;

/**
 * spins the intake motors to intake a ball
 */

public class IntakeGo extends CommandBase {
    private double speed;

    public IntakeGo(double speed) {
        setName("IntakeGo");
        addRequirements(Subsystems.intake);
        this.speed = speed;
    }
    public void initialize() {
    }

    public void execute() {
        Subsystems.intake.setIntakeMotors(speed);
    }

    public boolean isFinished() {
        return true;
    }

    
}
