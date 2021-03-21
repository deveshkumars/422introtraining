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
        
        //addCommands(new Turn(-28, 0.4)); //turn to line up with first gap
                // addCommands(new DriveStraight(87, 0.4)); //drive through first gap
                // addCommands(new Turn(28, 0.4)); //turn to be parallel with line of cones
                // addCommands(new DriveStraight(120, 0.4)); //drive to end of line of cones
                // addCommands(new Turn(28, 0.4)); //turn to line up with second gap
                // addCommands(new DriveStraight(33.98, 0.4)); //drive to tangent point with circle around last cone
                // addCommands(new Circle(30, 360)); //drive circle around last cone
                // addCommands(new DriveStraight(33.98, 0.4)); //drive from circle to start of line of cones
                // addCommands(new Turn (28, 0.4)); //turn to be parallel with line of cones
                // addCommands(new DriveStraight(120, 0.4)); //drive to end of line of cones
                // addCommands(new Turn(28, 0.4)); //turn to line up with end position
                // addCommands(new DriveStraight(87, 0.4)); //drive to end position
    }
}
