package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.FindGSPath;
import frc.robot.commands.IntakeToggle;
import frc.robot.commands.IntakeGo;
import frc.robot.commands.Turn;
import frc.robot.subsystems.Subsystems;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class GalacticSearch extends SequentialCommandGroup{
    
    public enum Path {
        RedA, BlueA, RedB, BlueB
    }

    /** The path to use if an error occurs or none is selected. */
    public static Path defaultAuto = Path.RedA;

     /** The path used */
     public Path path;

    public GalacticSearch (){
        addCommands(new IntakeToggle());
        addCommands(new WaitCommand(1));

        SequentialCommandGroup redA = new SequentialCommandGroup(
            (new DriveStraight(36, 0.5)),
            (new IntakeGo(.8)),
            (new DriveStraight(12, 0.2)),
            (new IntakeGo(.8)),
            (new Turn(19, 0.5)),
            (new DriveStraight(53, 0.5)),
            (new IntakeGo(.8)),
            (new DriveStraight(12, 0.2)),
            (new IntakeGo(.8)),
            (new Turn(-96, 0.5)),
            (new DriveStraight(62, 0.5)),
            (new IntakeGo(.8)),
            (new DriveStraight(12, 0.2)),
            (new IntakeGo(.8)),
            (new Turn (84, 0.5)),
            (new DriveStraight(140, 0.5)));
        
        SequentialCommandGroup blueA = new SequentialCommandGroup(
            (new Turn(23, 0.4)),
            (new DriveStraight(115, 0.4)),
            (new IntakeGo(.8)),
            (new DriveStraight(12, 0.2)),
            (new IntakeGo(.8)),
            (new Turn(-64, 0.4)),
            (new DriveStraight(82, 0.4)),
            (new IntakeGo(.8)),
            (new DriveStraight(12, 0.2)),
            (new IntakeGo(.8)),
            (new Turn(96, 0.4)),
            (new DriveStraight(64, 0.4)),
            (new IntakeGo(.8)),
            (new DriveStraight(12, 0.2)),
            (new IntakeGo(.8)),
            (new DriveStraight(76, 0.4)));

        SequentialCommandGroup redB = new SequentialCommandGroup(
            (new Turn(-28, 0.4)),
            (new DriveStraight(42, 0.5)),

            (new IntakeGo(.8)),
            (new DriveStraight(12, 0.2)),
            (new IntakeGo(.8)),

            (new Turn(69, 0.5)),
            (new DriveStraight(61, 0.5)),

            (new IntakeGo(.8)),
            (new DriveStraight(12, 0.2)),
            (new IntakeGo(.8)),

            (new Turn(-90, 0.5)),
            (new DriveStraight(61, 0.5)),

            (new IntakeGo(.8)),
            (new DriveStraight(12, 0.2)),
            (new IntakeGo(.8)),

            (new Turn (58, 0.5)),
            (new DriveStraight(130, 0.5)));


        SequentialCommandGroup blueB = new SequentialCommandGroup(
            (new Turn(11, 0.4)),
            (new DriveStraight(128, 0.4)),

            (new IntakeGo(.8)),
            (new DriveStraight(12, 0.2)),
            (new IntakeGo(.8)),

            (new Turn(-37, 0.4)),
            (new DriveStraight(90, 0.4)),

            (new IntakeGo(.8)),
            (new DriveStraight(12, 0.2)),
            (new IntakeGo(.8)),

            (new Turn(57, 0.4)),
            (new DriveStraight(60, 0.4)),

            (new IntakeGo(.8)),
            (new DriveStraight(12, 0.2)),
            (new IntakeGo(.8)),

            (new DriveStraight(32, 0.4)));
    
        addCommands(new ConditionalCommand (
            redA,
            new ConditionalCommand(
                blueA,
                new ConditionalCommand(
                    redB,
                    blueB,
                    () -> { return Subsystems.vision.findGSCase() == Path.RedB; }
                ),
                () -> { return Subsystems.vision.findGSCase() == Path.BlueA; }),
            () -> { return Subsystems.vision.findGSCase() == Path.RedA; }));
    }
}