
package frc.robot.commands.drivemodes;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;

/**
 * Uses joystick values to drive the bot in teleop.
 */
public class MecanumDrive extends CommandBase {


    private double xstrafe = UserInterface.driverController.getLeftJoystickX()*1; //1 is a place holder for now we need to find this value by testing because the strafing movements will be slower than the driving movements
    private double ystrafe = UserInterface.driverController.getLeftJoystickY();
    private double rot = UserInterface.driverController.getRightJoystickX();
    private double updatedFrontLeftPower = 0;
    private double updatedFrontRightPower = 0;
    private double updatedBackLeftPower = 0;
    private double updatedBackRightPower = 0;
    private static final double maxChange = 0.5; //maxChange is acceleration

    public MecanumDrive() {
        setName("MecanumDrive");
        addRequirements(Subsystems.driveBase);
    }

    public void execute() {
        double frontrightpower;
        double frontleftpower;
        double backrightpower;
        double backleftpower;
        double cap = 1;

        if (Math.abs(xstrafe+ystrafe)>1||Math.abs(ystrafe-xstrafe)>1) {
            cap = 1/(Math.max(Math.abs(xstrafe+ystrafe), Math.abs(ystrafe-xstrafe)));
        }

        frontleftpower = ((ystrafe + xstrafe)*cap)+rot;
        frontrightpower = ((ystrafe - xstrafe)*cap)-rot;
        backleftpower = ((ystrafe - xstrafe)*cap)+rot;
        backrightpower = ((ystrafe + xstrafe)*cap)-rot;

        double frontLeftSpeedDifference = frontleftpower - updatedFrontLeftPower;
        if (frontLeftSpeedDifference > maxChange) {
            frontleftpower = updatedFrontLeftPower + maxChange;
        } else if (frontLeftSpeedDifference < -maxChange) {
            frontleftpower = updatedFrontLeftPower - maxChange;
        }
        
        double frontRightSpeedDifference = frontrightpower - updatedFrontRightPower;
        if (frontRightSpeedDifference > maxChange) {
            frontrightpower = updatedFrontRightPower + maxChange;
        } else if (frontRightSpeedDifference < -maxChange) {
            frontrightpower = updatedFrontRightPower - maxChange;
        }

        double backLeftSpeedDifference = backleftpower - updatedFrontLeftPower;
        if (backLeftSpeedDifference > maxChange) {
            backleftpower = updatedBackLeftPower + maxChange;
        } else if (backLeftSpeedDifference < -maxChange) {
            backleftpower = updatedBackLeftPower - maxChange;
        }
        
        double backRightSpeedDifference = backrightpower - updatedFrontRightPower;
        if (backRightSpeedDifference > maxChange) {
            backrightpower = updatedBackRightPower + maxChange;
        } else if (backRightSpeedDifference < -maxChange) {
            backrightpower = updatedBackRightPower - maxChange;
        }

        updatedFrontLeftPower = frontleftpower;
        updatedFrontRightPower = frontrightpower;
        updatedBackLeftPower = backleftpower;
        updatedBackRightPower = backrightpower;

        Subsystems.driveBase.setMotors(frontleftpower*RobotMap.getSpeedCap(), frontrightpower*RobotMap.getSpeedCap(), backleftpower*RobotMap.getSpeedCap(), backrightpower*RobotMap.getSpeedCap());

    }

}