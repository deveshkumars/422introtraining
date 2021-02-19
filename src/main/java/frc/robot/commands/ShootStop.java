package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Subsystems;

/**
 * Stops the flyboi, transversal, and the cell stop.
 */
public class ShootStop extends CommandBase{

    public ShootStop() {
        setName("ShootStop");
        addRequirements(Subsystems.transversal, Subsystems.flyboi, Subsystems.cellStop);
    }

    public void initialize() {
        Subsystems.flyboi.stopWheel();
        Subsystems.transversal.stopTransversalMotors();
        Subsystems.cellStop.stopFeed();
    }
    
    public boolean isFinished() {
        return true;
    }
}