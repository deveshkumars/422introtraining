package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;

/**
 * Turns the robot
 */
public class Turn extends CommandBase {

  private double degrees;
  private double speed;
  private boolean isCorrecting = false;

  /**
   * Turns the bot a set number of degrees.
   * @param Degrees The number of degrees to turn - negative to the left, positive to the right.
   * @param Speed The speed at which to turn (0 to 1). Speeds over x are not recommended for maximal accuracy.
   */

    public Turn(double degrees, double speed) {
        setName("Turn");
        addRequirements(Subsystems.driveBase);
        this.degrees = degrees;
        this.speed = speed;
    }
    public void initialize() {
      System.out.println("Starting turn!");
      Subsystems.driveBase.zeroGyroAngle();
      Subsystems.driveBase.zeroEncoderPosition();
  }

  public void execute() {
      if ((degrees > 0) && !isCorrecting) {
          // Turning to the right
          Subsystems.driveBase.setMotors(-speed, speed);
      } else if ((degrees < 0) && !isCorrecting) {
          // Turning to the left
          Subsystems.driveBase.setMotors(speed, -speed);
      } else if (degrees > 0) {
          // Turned to the right, but correcting to the left
          Subsystems.driveBase.setMotors(speed / 1.5, -speed / 1.5);
      } else {
          // Turned to the left, but correcting to the right
          Subsystems.driveBase.setMotors(-speed / 1.5, speed / 1.5);
      }
  }

  public boolean isFinished() {
      double angle = Subsystems.driveBase.getGyroAngle();
      if (degrees > 0) {
          // Turning to the right
          if (!isCorrecting) {
              if (angle > degrees) {
                  isCorrecting = true;
              }
              return false;
          }
          return (angle < degrees);
      } else {
          // Turning to the left
          if (!isCorrecting) {
              if (angle < degrees) {
                  isCorrecting = true;
              }
              return false;
          }
          return (angle > degrees);
      }
  }
}