package frc.robot;
 
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.subsystems.Subsystems;
import frc.robot.RobotMap;
import java.util.List;
 
public class RobotContainer {
 
  public Command getAutonomousCommand() {
      var autoVoltageConstraint =
           new DifferentialDriveVoltageConstraint(
               new SimpleMotorFeedforward(
                   RobotMap.ksVolts,
                   RobotMap.kvVoltSecondsPerMeter,
                   RobotMap.kaVoltSecondsSquaredPerMeter
               ),
               RobotMap.kDriveKinematics,
               10);
 
       TrajectoryConfig config =
           new TrajectoryConfig(
               RobotMap.kMaxSpeedMetersPerSecond,
               RobotMap.kMaxAccelerationMetersPerSecondSquared)
               .setKinematics(RobotMap.kDriveKinematics)
               .addConstraint(autoVoltageConstraint);
 
       Trajectory traj =
           TrajectoryGenerator.generateTrajectory(
               new Pose2d(0, 0, new Rotation2d(0)),
               List.of(new Translation2d(0,1), new Translation2d(1,1), 
               new Translation2d(2, 1)),
               new Pose2d(3,1, new Rotation2d(0)),
               config);
 
       RamseteCommand ramseteCommand =
           new RamseteCommand(
               traj,
               Subsystems.driveBase::getPose,
               new RamseteController(RobotMap.kRamseteB, RobotMap.kRamseteZeta),
               new SimpleMotorFeedforward(
                   RobotMap.ksVolts,
                   RobotMap.kvVoltSecondsPerMeter,
                   RobotMap.kaVoltSecondsSquaredPerMeter),
               RobotMap.kDriveKinematics,
               Subsystems.driveBase::getWheelSpeeds,
               new PIDController(RobotMap.kPDriveVel, 0, 0),
               new PIDController(RobotMap.kPDriveVel, 0, 0),
               Subsystems.driveBase::tankDriveVolts,
               Subsystems.driveBase);
 
       Subsystems.driveBase.resetOdometry(traj.getInitialPose());
 
       return ramseteCommand.andThen(() -> Subsystems.driveBase.tankDriveVolts(0, 0));
  }
}