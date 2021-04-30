package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Subsystems;
import java.util.function.DoubleSupplier;


public class PIDFeedforwardDrive extends PIDCommand {
   

    public PIDFeedforwardDrive(double inches) {
        super(
        RobotMap.driveSpeedPID,
        RobotMap.driveSpeedPIDmeasurementSupplier,
        RobotMap.convertToTicks(inches),
        (output) -> Subsystems.driveBase.setMotorsVoltage(output, output),
        Subsystems.driveBase
        );
    }


}