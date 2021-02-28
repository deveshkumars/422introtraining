package frc.robot.userinterface;

import frc.robot.RobotMap;

/**
 * Contains instances of all UI elements.
 */
public class UserInterface {

    public static final RumbleXboxController operatorController = new RumbleXboxController(RobotMap.operatorXboxController);
    public static final Joystyx leftJoystix = new Joystyx(RobotMap.leftJoystyx);
    public static final Joystyx rightJoystix = new Joystyx(RobotMap.rightJoystyx);
}