package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;

/**
 * The code that will be run during the autonomous period
 */
public class Autonomous extends SequentialCommandGroup {

    public Autonomous() {
        addCommands(new Drivestraight(10, 0.4));
    }
}