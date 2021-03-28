package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;

public class Autonomous extends SequentialCommandGroup {

    public enum Path {
        BARREL, SLALOM, BOUNCE
    }

    /** The path to use if an error occurs or none is selected. */
    public static Path defaultAuto = Path.BARREL;

    /** The path used on this specific auto object. */
    public Path path;

    public Autonomous(Path path) {
        this.path = path;
        switch (path) {
            case BARREL: //center halfway between B2 and D2
                addCommands(new DriveStraight(76.5, 0.6));
                addCommands(new Circle(18, 360.65));
                addCommands(new DriveStraight((83-18), 0.6));
                addCommands(new Circle(-18, -305));
                addCommands(new DriveStraight(73, 0.6));
                addCommands(new Circle(-18, -225)); //this boi needs to be tweaked a little
                addCommands(new DriveStraight(230,0.6)); 
                break;
            case SLALOM: //center 5.5 inches above E2
                // addCommands(new Turn(-37, 0.4)); //turn to line up with first gap
                // addCommands(new DriveStraight(91, 0.4)); //drive through first gap
                // addCommands(new Turn(37, 0.4)); //turn to be parallel with line of cones
                // addCommands(new DriveStraight(100, 0.4)); //drive to end of line of cones
                // addCommands(new Turn(37, 0.4)); //turn to line up with second gap
                // addCommands(new DriveStraight(33.98, 0.4)); //drive to tangent point with circle around last cone
                // addCommands(new Turn(55, 0.4));
                // addCommands(new Circle(-30, 360)); //drive circle around last cone
                // addCommands(new DriveStraight(33.98, 0.4)); //drive from circle to start of line of cones
                // addCommands(new Turn (55, 0.4)); //turn to be parallel with line of cones
                // addCommands(new Turn (37, 0.4));
                // addCommands(new DriveStraight(100, 0.4)); //drive to end of line of cones
                // addCommands(new Turn(37, 0.4)); //turn to line up with end position
                // addCommands(new DriveStraight(90, 0.4)); //drive to end position

                addCommands(new DriveStraight(16.5, 0.6));
                addCommands(new Circle(-30, 90));
                addCommands(new Circle(30, 90));
                addCommands(new DriveStraight(120, 0.4)); //drive to end of line of cones
                addCommands(new Circle(30, 90));
                addCommands(new Circle(-30, 360));
                addCommands(new Circle(30, 90));
                addCommands(new DriveStraight(120, 0.4)); //drive to end of line of cones
                addCommands(new Circle(30, 90));
                addCommands(new Circle(-30, 90));
                break;
            case BOUNCE: //center halfway between B2 and D2
                addCommand( new Circle(30,90));
                addCommand(new DriveStraight(30,0.4));
                addCommands(new Turn(-20,.4));
                addCommands(new DriveStraight(-105, .4));
                addCommands(new Circle(-30,160));
                addCommands(new DriveStraight(-90,.4));
                addCommand (new turn(-95));
                addCommand(new drivetraight (-42.42, 0.4))

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
