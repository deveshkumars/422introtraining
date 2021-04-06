package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;

public class Autonomous extends SequentialCommandGroup {

    public enum Path {
        BARREL, SLALOM, BOUNCE, TEST
    }

    /** The path to use if an error occurs or none is selected. */
    public static Path defaultAuto = Path.BARREL;

    /** The path used on this specific auto object. */
    public Path path;

    public Autonomous(Path path) {
        this.path = path;
        switch (path) {
            case BARREL: //center halfway between B2 and D2
                System.out.print("starting Barrel");
                addCommands(new DriveStraight(73, 0.4));
                addCommands(new Circle(18, 90));
                addCommands(new DriveStraight(1,0.4));
                addCommands(new Circle(16, 90));
                addCommands(new DriveStraight(8,0.4));
                addCommands(new Circle(16, 90));
                addCommands(new DriveStraight(7,0.4));
                addCommands(new Circle(18, 60));
                addCommands(new DriveStraight(52, 0.4));
                addCommands(new Circle(-15, -180));
                addCommands(new DriveStraight(22,0.4));
                addCommands(new Circle(-16, -100));
                addCommands(new DriveStraight(45, 0.4));
                addCommands(new Circle(-16, -45));
                addCommands(new DriveStraight(28,0.4)); 
                addCommands(new Circle(-16, -90));
                addCommands(new DriveStraight(7,0.4)); 
                addCommands(new Circle(-18, -83.3));
                addCommands(new DriveStraight(220,0.7)); 
                break;
            case SLALOM: //center 5.5 inches above E2
                System.out.print("starting Slalom");
                addCommands(new DriveStraight(13,0.4));
                addCommands(new Turn(-50, 0.4)); //turn to line up with first gap
                addCommands(new DriveStraight(91, 0.4)); //drive through first gap
                addCommands(new Turn(55, 0.4)); //turn to be parallel with line of cones
                addCommands(new DriveStraight(86, 0.4)); //drive to end of line of cones
                addCommands(new Turn(60, 0.4)); //turn to line up with second gap
                addCommands(new DriveStraight(38, 0.4)); //drive to tangent point with circle around last cone
                addCommands(new Circle(-20, 100)); //drive circle around last cone
                addCommands(new DriveStraight(10,0.4));
                addCommands(new Circle(-20, 180));
                addCommands(new DriveStraight(40, 0.4)); //drive to tangent point with circle around last cone
                addCommands(new Turn(60, 0.4)); //turn to line up with second gap
                addCommands(new DriveStraight(99+40, 0.4)); //drive to end of line of cones
                addCommands(new Turn(70,0.4));
                addCommands(new DriveStraight(15,.4));
                addCommands(new Circle(-28,-50));
                break;
            case BOUNCE: //center halfway between B2 and D2
                addCommands(new DriveStraight(16.5,.4));
                addCommands(new Circle(-30,90));
                addCommands(new DriveStraight(30,.4));
                addCommands(new Turn(-20,.4));
                addCommands(new DriveStraight(-105, .4));
                addCommands(new Turn(180,0.6));
                addCommands(new Circle(-30,160));
                addCommands(new DriveStraight(-90,.4));
                break;
            case TEST:
                addCommands(new DriveStraight(10, 0.4));
                // addCommands(new Circle(24,360));
                // addCommands(new Turn(360, 0.4));
            
        }
    }
}
