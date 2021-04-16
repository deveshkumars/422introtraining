package frc.robot.commands.drivemodes;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;

/**
 * Uses joystick values to drive the bot in teleop.
 */
public class WiiDrive extends CommandBase {

    private double updatedSpeed = 0;
    private double updatedRotation = 0;
    private final double maxChange = 0.5; //maxChange is acceleration

    public WiiDrive() {
        setName("WiiDrive");
        addRequirements(Subsystems.driveBase);
    }

    public void execute() {
        double speed = 0;
        double rotation;

        /* Sets throttle for driveBase to the left stick Y-axis and sets the rotation
        * for driveBase to the right stick X-axis on on the driverXboxController */
        if (UserInterface.wiimoteController.getButton2()) {
            speed = updatedSpeed + maxChange;
            if (speed > 1){
                speed = 1.0;
            }
        }
        else if (UserInterface.wiimoteController.getButton1()){
            speed = updatedSpeed - maxChange;
            if (speed < -1){
                speed = -1.0;
            }
        else{
            if (updatedSpeed < 0){
                if (updatedSpeed > -maxChange){
                    speed = 0;
                }
                else{
                    speed = updatedSpeed + maxChange;
                }
            }
            if (updatedSpeed > 0){
                if (updatedSpeed < maxChange){
                    speed = 0;
                }
                else{
                    speed = updatedSpeed - maxChange;
                }
            }
            else{
                speed = 0;
            }
        }

        }
        if (UserInterface.wiimoteController.getXRotation() < -0.05) {
            rotation = (Math.pow(UserInterface.wiimoteController.getXRotation(), 5));
        } else if (UserInterface.wiimoteController.getXRotation() > 0.05) {
            rotation = (Math.pow(UserInterface.wiimoteController.getXRotation(), 5));
        } else {
            rotation = 0;
        }
        
        double rotationDifference = rotation - updatedRotation;
        if (rotationDifference > maxChange) {
            rotation = updatedRotation + maxChange;
        } else if (rotationDifference < -maxChange) {
            rotation = updatedRotation - maxChange;
        }

        updatedSpeed = speed;
        updatedRotation = rotation;

        /*  Because of a weird glitch with how curvatureDrive is set up,
         *  the rotation actually goes in as the first input, followed by the speed,
         *  rather than speed then rotation */
        // Subsystems.driveBase.cheesyDrive.curvatureDrive(RobotMap.getRotationCap() * rotation, RobotMap.getSpeedCap() * speed, true);
        
        // For Comp Bot, otherwise comment out and uncomment the other one
        Subsystems.driveBase.cheesyDrive.curvatureDrive(RobotMap.getSpeedCap() * speed, RobotMap.getRotationCap() * rotation, true);
    }
}