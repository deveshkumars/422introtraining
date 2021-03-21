package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;

public class Autonomous extends SequentialCommandGroup {

    public enum PathNames {
        BARREL, SLALOM, BOUNCE
    }

    public static PathNames pathName;

    public Autonomous(){
        pathName = PathNames.BARREL;
        if (pathName == PathNames.BARREL) {
            addCommands(new DriveStraight(76.5, 0.6));
            addCommands(new Circle(18, 360.65));
            addCommands(new DriveStraight((83-18), 0.6));
            addCommands(new Circle(-18, -305));
            addCommands(new DriveStraight(73, 0.6));
            addCommands(new Circle(-18, -225));
            addCommands(new DriveStraight(230,0.6)); //260 is a placeholder
        }
    }
}
