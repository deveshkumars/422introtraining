package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

/**
 *  This spins the flywheel, helix, and intake motors backwords to take out power cells.
 */
public class Vomit extends CommandBase {

    public Vomit() {
        setName("Vomit");
        addRequirements(Subsystems.intake);
    }

    public void initialize() {
        Subsystems.flyboi.spinWheel(-0.6); //numbers are placeholder more testing is required.
        Subsystems.transversal.setTransversalMotors(-0.6); 
        Subsystems.intake.setIntakeMotors(-0.6);
    }

    public void end(boolean interrupted) {
        Subsystems.flyboi.stopWheel();
        Subsystems.transversal.stopTransversalMotors(); 
        Subsystems.intake.stopIntakeMotors();
    }

}