package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.IntakeToggle;
import frc.robot.commands.IntakeGo;
import frc.robot.commands.Turn;
import frc.robot.subsystems.Subsystems;

public class GalacticSearch extends SequentialCommandGroup{
    
    public enum Path {
        RedA, BlueA, RedB, BlueB
    }

    /** The path to use if an error occurs or none is selected. */
    public static Path defaultAuto = Path.RedA;

     /** The path used on this specific auto object. */
     public Path path;

    public GalacticSearch (){
        // addCommands(new IntakeToggle());
        this.path = Subsystems.vision.findGSCase();
        switch(path) {
            case RedA:
            addCommands(new DriveStraight(36, 0.4));
            addCommands(new IntakeGo(.8));
            addCommands(new DriveStraight(12, 0.2));
            addCommands(new IntakeGo(.8));
            addCommands(new Turn(19, 0.4));
            addCommands(new DriveStraight(53, 0.4));
            addCommands(new IntakeGo(.8));
            addCommands(new DriveStraight(12, 0.2));
            addCommands(new IntakeGo(.8));
            addCommands(new Turn(-96, 0.4));
            addCommands(new DriveStraight(62, 0.4));
            addCommands(new IntakeGo(.8));
            addCommands(new DriveStraight(12, 0.2));
            addCommands(new IntakeGo(.8));
            addCommands(new Turn (84, 0.4));
            addCommands(new DriveStraight(140, 0.4));
            break;

            case BlueA:
            addCommands(new Turn(23, 0.4));
            addCommands(new DriveStraight(115, 0.4));
            addCommands(new IntakeGo(.8));
            addCommands(new DriveStraight(12, 0.2));
            addCommands(new IntakeGo(.8));
            addCommands(new Turn(-64, 0.4));
            addCommands(new DriveStraight(82, 0.4));
            addCommands(new IntakeGo(.8));
            addCommands(new DriveStraight(12, 0.2));
            addCommands(new IntakeGo(.8));
            addCommands(new Turn(96, 0.4));
            addCommands(new DriveStraight(64, 0.4));
            addCommands(new IntakeGo(.8));
            addCommands(new DriveStraight(12, 0.2));
            addCommands(new IntakeGo(.8));
            addCommands(new DriveStraight(76, 0.4));
            break;

            case RedB:
            addCommands(new Turn(-23, 0.4));
            addCommands(new DriveStraight(42, 0.4));

            addCommands(new IntakeGo(.8));
            addCommands(new DriveStraight(12, 0.2));
            addCommands(new IntakeGo(.8));

            addCommands(new Turn(54, 0.4));
            addCommands(new DriveStraight(61, 0.4));

            addCommands(new IntakeGo(.8));
            addCommands(new DriveStraight(12, 0.2));
            addCommands(new IntakeGo(.8));

            addCommands(new Turn(-65, 0.4));
            addCommands(new DriveStraight(61, 0.4));

            addCommands(new IntakeGo(.8));
            addCommands(new DriveStraight(12, 0.2));
            addCommands(new IntakeGo(.8));

            addCommands(new Turn (33, 0.4));
            addCommands(new DriveStraight(130, 0.4));
            break;

            case BlueB:
            addCommands(new Turn(11, 0.4));
            addCommands(new DriveStraight(128, 0.4));

            addCommands(new IntakeGo(.8));
            addCommands(new DriveStraight(12, 0.2));
            addCommands(new IntakeGo(.8));

            addCommands(new Turn(-37, 0.4));
            addCommands(new DriveStraight(90, 0.4));

            addCommands(new IntakeGo(.8));
            addCommands(new DriveStraight(12, 0.2));
            addCommands(new IntakeGo(.8));

            addCommands(new Turn(57, 0.4));
            addCommands(new DriveStraight(60, 0.4));

            addCommands(new IntakeGo(.8));
            addCommands(new DriveStraight(12, 0.2));
            addCommands(new IntakeGo(.8));

            addCommands(new DriveStraight(32, 0.4));
            break;
            
    }
    System.out.println(this.path);
    }}