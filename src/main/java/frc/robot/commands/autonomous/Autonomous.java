package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import edu.wpi.first.wpilibj2.command.WaitCommand;
// import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.robot.commands.*;

// The real file
public class Autonomous extends SequentialCommandGroup {

    public enum PathNames {
        BARREL, SLALOM, BOUNCE, CIRCUIT
    }

    public static PathNames pathName;

    public Autonomous(){
        pathName = PathNames.BARREL;
        if (pathName == PathNames.BARREL) {
            addCommands(new DriveStraight(106.5, 0.6));
            addCommands(new Circle(18, 356.65));
            addCommands(new DriveStraight(83, 0.6));
            addCommands(new Circle(-18, -287));
            addCommands(new DriveStraight(73, 0.6));
            addCommands(new Circle(-18, -225));
            addCommands(new DriveStraight(260,0.6)); //260 is a placeholder
        }
    }
}
