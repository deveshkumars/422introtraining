package frc.robot.commands.drivemodes;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;

/**
 * Uses joystick values to drive the bot in teleop.
 */
public class ControllerTankFancyDrive extends CommandBase {

    private double updatedLeftSpeed = 0;
    private double updatedRightSpeed = 0;
    private static final double maxChange = 0.5; //maxChange is acceleration
    private double heading = 0; //stores last gyro angle before a drive straight

    public ControllerTankFancyDrive() {
        setName("ControllerTankFancyDrive");
        addRequirements(Subsystems.driveBase);
    }

    public void initialize(){
        Subsystems.driveBase.zeroGyroAngle();
    }

    public void execute() {
        double leftSpeed, rightSpeed;
        double leftInput = UserInterface.driverController.getLeftJoystickY();
        double rightInput = UserInterface.driverController.getRightJoystickY();

        //if outside dead zone, square left stick input to get left speed
        if (leftInput < -0.1) {
            leftSpeed = -(Math.pow(leftInput, 2));
        } else if (leftInput > 0.1) {
            leftSpeed = (Math.pow(leftInput, 2));
        } else {
            leftSpeed = 0;
        }

        //if outside dead zone, square right stick input to get right speed
        if (rightInput < -0.1) {
            rightSpeed = -(Math.pow(rightInput, 2));
        } else if (rightInput > 0.1) {
            rightSpeed = (Math.pow(rightInput, 2));
        } else {
            rightSpeed = 0;
        }

        //adjust left speed for acceleration cap
        double leftSpeedDifference = leftSpeed - updatedLeftSpeed;
        if (leftSpeedDifference > maxChange) {
            leftSpeed = updatedLeftSpeed + maxChange;
        } else if (leftSpeedDifference < -maxChange) {
            leftSpeed = updatedLeftSpeed - maxChange;
        }
        
        //adjust right speed for acceleration cap
        double rightSpeedDifference = rightSpeed - updatedRightSpeed;
        if (rightSpeedDifference > maxChange) {
            rightSpeed = updatedRightSpeed + maxChange;
        } else if (rightSpeedDifference < -maxChange) {
            rightSpeed = updatedRightSpeed - maxChange;
        }

        updatedLeftSpeed = leftSpeed;
        updatedRightSpeed = rightSpeed;

        System.out.println("right speed is " + rightSpeed + "left speed is " + leftSpeed);

        //now that we have speeds, use the raw controller inputs to determine how the robot is going to move

        if (Math.abs(leftInput - rightInput) >= 0.05) { //driving straight forward or backward robot oriented 
            //determine direction
            boolean forward;
            if (leftInput > 0 && rightInput > 0) {
                forward = true;
            } else {
                forward = false;
            }

            //find the the angle between the robot's current heading and the last heading before the drivestraight, with some math adjustments
            double correction = Subsystems.driveBase.getGyroAngle() - heading;
            correction *= 0.05;
            correction += 1.0;

            if (forward) {
               rightSpeed *= correction;
               if (rightSpeed > 1) {
                   rightSpeed = 1;
               }
            } else {
                leftSpeed *= correction;
                if (leftSpeed < -1) {
                    leftSpeed = -1;
                }
            }

            Subsystems.driveBase.cheesyDrive.tankDrive(leftSpeed * RobotMap.getSpeedCap(), rightSpeed * RobotMap.getSpeedCap());
        }

        else if (rightSpeed * leftSpeed < 0){ //rotating
            Subsystems.driveBase.cheesyDrive.tankDrive(leftSpeed * RobotMap.getRotationCap(), rightSpeed * RobotMap.getRotationCap());
            System.out.println("turning with Rotation cap " + RobotMap.getRotationCap());
            heading = Subsystems.driveBase.getGyroAngle();
        }

        else if (Math.abs(leftInput - rightInput) >= 0.05 && UserInterface.driverController.getRightJoystickX() >= 0.4){ //driving straight forward or backward field oriented
            //determine direction
            boolean forward;
            if (leftInput > 0 && rightInput > 0) {
                forward = true;
            } else {
                forward = false;
            }

            //find the the angle between the robot's current heading and the robot's starting heading (field heading)
            double correction = Subsystems.driveBase.getGyroAngle();
            correction *= 0.05;
            correction += 1.0;

            if (forward) {
               rightSpeed *= correction;
               if (rightSpeed > 1) {
                   rightSpeed = 1;
               }
            } else {
                leftSpeed *= correction;
                if (leftSpeed < -1) {
                    leftSpeed = -1;
                }
            }

            Subsystems.driveBase.cheesyDrive.tankDrive(leftSpeed * RobotMap.getSpeedCap(), rightSpeed * RobotMap.getSpeedCap());
        }

        else { //curving forwards or backwards
            Subsystems.driveBase.cheesyDrive.tankDrive(leftSpeed * RobotMap.getSpeedCap(), rightSpeed * RobotMap.getSpeedCap());
            heading = Subsystems.driveBase.getGyroAngle();
        }

    }
}