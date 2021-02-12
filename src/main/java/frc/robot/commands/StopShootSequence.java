package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;

/**
 * Stop shooting.
 */
public class StopShootSequence extends CommandGroupBase {

    

    public StopShootSequence() {
        setName("StopShootSequence");
        addRequirements(Subsystems.transversal, Subsystems.cellStop, Subsystems.flyboi);
    }

    public void initialize() {

    }

    public void execute() {
        //add stop transversal
        Subsystems.cellStop.stopFeed();
        Subsystems.flyboi.StopFlywheel();
    }

    public void isFinished() {
        return true;
    }
}