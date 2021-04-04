package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;
import frc.robot.RobotMap;

public class Autonomous extends SequentialCommandGroup {

    public enum Path {
        BARREL, SLALOM, BOUNCE
    }

    /** The path to use if an error occurs or none is selected. */
    public static Path defaultAuto = Path.BOUNCE;

    /** The path used on this specific auto object. */
    public Path path;

    public Autonomous(Path path) {
        this.path = path;
        switch (path) {
            case BARREL: //robot's front center is halfway between B2 and D2
            if(RobotMap.botName == RobotMap.BotNames.PBOT20){
                addCommands(new DriveStraight(76.5, 0.6));
                addCommands(new Circle(18, 355));
                addCommands(new DriveStraight((83-15), 0.6)); //change
                addCommands(new Circle(-18, -308));
                addCommands(new DriveStraight(36, 0.6));
                addCommands(new Circle(-18, -218)); 
                addCommands(new DriveStraight(240,0.6)); 
            } else if(RobotMap.botName == RobotMap.BotNames.COMPETITION){
                //ADD COMPETITION BOT COMMANDS HERE
            }
                break;
            case SLALOM: 
                //left front wheel is 8.5 inches from D2
                if(RobotMap.botName == RobotMap.BotNames.PBOT20){
                addCommands(new DriveStraight(13,0.4));
                addCommands(new Turn(-50, 0.4)); //turn to line up with first gap
                addCommands(new DriveStraight(91, 0.4)); //drive through first gap
                addCommands(new Turn(55, 0.4)); //turn to be parallel with line of cones
                addCommands(new DriveStraight(86, 0.4)); //drive to end of line of cones, 99
                addCommands(new Turn(60, 0.4)); //turn to line up with second gap
                addCommands(new DriveStraight(38, 0.4)); //drive to tangent point with circle around last cone
                addCommands(new Circle(-20, 300)); //drive circle around last cone
                addCommands(new DriveStraight(40, 0.4)); //drive to tangent point with circle around last cone
                addCommands(new Turn(60, 0.4)); //turn to line up with second gap
                addCommands(new DriveStraight(99+40, 0.4)); //drive to end of line of cones
                addCommands(new Turn(70,0.4));
                addCommands(new DriveStraight(15,.4)); 
                addCommands(new Circle(-28,-50));
                } else if(RobotMap.botName == RobotMap.BotNames.COMPETITION){
                    //ADD COMPETITION BOT COMMANDS HERE
                }
                break;
            case BOUNCE: //center halfway between B2 and D2
            if(RobotMap.botName == RobotMap.BotNames.PBOT20){
                addCommands(new DriveStraight(3,.4));
                addCommands(new Circle(-28,90));
                addCommands(new DriveStraight(-30,.4));
                addCommands(new Turn(-20,.4));
                addCommands(new DriveStraight(-55, .4));
                addCommands(new Turn(180,0.6));
                addCommands(new Circle(-30,160));
                addCommands(new DriveStraight(90,.4));
                } else if(RobotMap.botName == RobotMap.BotNames.COMPETITION){
                    //ADD COMPETITION BOT COMMANDS HERE
                }
            }
    }
}
