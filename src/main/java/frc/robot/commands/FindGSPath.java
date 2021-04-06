package frc.robot.commands;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.commands.autonomous.GalacticSearch;

public class FindGSPath extends CommandBase {
    public FindGSPath() {
        setName("FindGSPath");
        addRequirements(Subsystems.intake);
    }

    public void initialize() {
        // GalacticSearch.path = Subsystems.vision.findGSCase();
    }

    public boolean isFinished() {
        return true;
    }
}