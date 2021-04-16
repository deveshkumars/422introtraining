package frc.robot.userinterface;

/**
 * Contains instances of all UI elements.
 */
public class UserInterface {

     /**
     * <p>The driver controller (black).</p>
     * Used to control the drive base alone in teleop in Controller DriveModes.
     */
    public static final RumbleXboxController driverController = new RumbleXboxController(UIMap.driverXboxController);

    /**
     * <p>The left driver joystick.</p>
     * Used with rightJoystix to control the drive base in teleop in Joystick DriveModes.
     */
    public static final Joystyx leftJoystix = new Joystyx(UIMap.leftJoystyx);

    /**
     * <p>The right driver joystick.</p>
     * Used with leftJoystyx to control the drive base in teleop in Joystick DriveModes.
     */
    public static final Joystyx rightJoystix = new Joystyx(UIMap.rightJoystyx);

    /**
     * <p>The wiimote controller.</p>
     * Used for shenanigans.
     */
    public static final WiimoteController wiimoteController = new WiimoteController(UIMap.wiimoteController);

    /**
     * <p>The operator controller (geen).</p>
     * Used to control all subsystems except the drive base in teleop.
     */
    public static final RumbleXboxController operatorController = new RumbleXboxController(UIMap.operatorXboxController);
}