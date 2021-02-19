package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;

/**
 * Starts the flywheel & waits until it warms up, then runs the transversal and cell stop to feed balls into the flywheel.
 */
public class ShootSequence extends CommandBase {

    public boolean warmedUp = false;
    private int timer = 0;
    
    public ShootSequence() {
        setName("ShootSequence");
        addRequirements(Subsystems.transversal, Subsystems.flyboi, Subsystems.cellStop);
    }

    public void initialize() {
        Subsystems.flyboi.setShootVoltage(Subsystems.flyboi.wheelSpeed);
    }

    public void execute() {
        if (Subsystems.flyboi.getPower() >= Subsystems.flyboi.wheelSpeed - 0.010) {
            if (timer < 6) {
                timer++;
            } else{
                warmedUp = true;
                Subsystems.transversal.setTransversalMotors(0.5);
                Subsystems.cellStop.feedBalls(0.5);
            }
        } else {
            if (warmedUp && Subsystems.flyboi.getPower() < Subsystems.flyboi.wheelSpeed - 0.020) {
                warmedUp = false;
                RobotMap.cellCount--;
            }
            Subsystems.transversal.stopTransversalMotors();
            Subsystems.cellStop.stopFeed();
        }
    }

    public boolean isFinished() {
        return UserInterface.operatorController.getRightTrigger() < 0.4;
    }
}