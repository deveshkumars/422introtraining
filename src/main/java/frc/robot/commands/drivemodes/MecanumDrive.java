
package frc.robot.commands.drivemodes;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;
import frc.robot.subsystems.*;

/**
 * Uses joystick values to drive the bot in teleop.
 */
public class MecanumDrive extends CommandBase {


    private double xstrafe; //1 is a place holder for now we need to find this value by testing because the strafing movements will be slower than the driving movements
    private double ystrafe;
    private double rot; //rotation axis which should be able to be combined with our other stuff
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
        xstrafe = UserInterface.driverController.getLeftJoystickX()*1;
        ystrafe = UserInterface.driverController.getLeftJoystickY();
        rot = UserInterface.driverController.getRightJoystickX();

        Subsystems.driveBase.setMotors(xstrafe, ystrafe, rot);
    }

}