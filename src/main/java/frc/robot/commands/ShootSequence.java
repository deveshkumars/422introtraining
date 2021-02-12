package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;

/**
 * Start shooting.
 */
public class Shoot extends CommandGroupBase {

    public Shoot() {
        setName("Shoot");
        addRequirements(Subsystems.transversal, Subsystems.flyboi, Subsystems.cellStop);
    }

    public void initialize() {
        addCommands(new (.5); // cell stop move
        addCommands(new (.5)); // transversal move
    }

    public boolean isFinished() {
        return true;
    }
}