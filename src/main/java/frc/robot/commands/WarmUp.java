package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

/**
 * Warms up the flyboi before using it.
 */
public class WarmUp extends CommandBase {

    private double speed;
    
    public WarmUp() {
        this(Subsystems.flyboi.wheelSpeed);
    }

    public WarmUp(double Speed) {
        setName("WarmUp");
        addRequirements(Subsystems.flyboi);
        this.speed = Speed;
    }

    public void initialize() {
        Subsystems.flyboi.setShootVoltage(speed);
    }

    public boolean isFinished() {
        return true;
    }
}

