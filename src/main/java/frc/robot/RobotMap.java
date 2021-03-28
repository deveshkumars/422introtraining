package frc.robot;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.*;

/**
 * Ports for motor controllers, caps for speed and rotation, booleans for toggles, and turn direction.
 */
public class RobotMap {

    // Robot settings & toggles (mutable)

    private static double speedCap = 0.8;
    private static double rotationCap = 0.7;

    public static boolean isSpeedMode = true;
    public static boolean isFirstCamera = true;
    public static boolean isIntakeDown = false;
    public static int cellCount = 3;
    
    public static double robotWidth = 1000;

    // Drive base ports

    public static int leftFrontFollower;
    public static int leftMiddleMaster;
    public static int leftRearFollower;
    public static int rightFrontFollower;
    public static int rightMiddleMaster;
    public static int rightRearFollower;

    public static int wheelDiameter;

    // Subsystem motor ports

    public static int leftFlywheel;
    public static int rightFlywheel;
    public static int intakeMotor;
    public static int bottomBelt;
    public static int middleBelt;
    public static int feederWheel;

    public static int leftClimber;
    public static int rightClimber;

    // Double solenoid ports

    public static int intakeExtensionOut;
    public static int intakeExtensionIn;

    // Sensor ports 

    // UI Ports

    public static final int driverXboxController = 1;
    public static final int operatorXboxController = 2;

    //CameraSever and NetworkTable
    public static CameraServer csinst;
    public static NetworkTableInstance ntinst;

    public enum BotNames {
        COMPETITION, TOASTER, AXIDRIVE, PBOT20
    }

    public static BotNames botName;

    /**
     * Sets the bot's ports based off of the bot's name. No further robot-specific setting is needed.
     * @param bot The name of the bot.
     */
    public static void setBot(BotNames bot) {
        botName = bot;
        if (bot == BotNames.COMPETITION) {
            rightFrontFollower = 7;
            rightMiddleMaster = 29;
            rightRearFollower = 8;
            leftFrontFollower = 9;
            leftMiddleMaster = 58;
            leftRearFollower = 45;
            wheelDiameter = 6;

            leftFlywheel = 422;
            rightFlywheel = 422;
            intakeMotor = 32;
            bottomBelt = 12;
            middleBelt = 422;
            feederWheel = 422;

            leftClimber = 422;
            rightClimber = 422;

            intakeExtensionIn = 0;
            intakeExtensionOut = 7;
        } else if (bot == BotNames.TOASTER){
            leftMiddleMaster = 2;
            leftFrontFollower = 3;
            leftRearFollower = 6;
            rightMiddleMaster = 14;
            rightFrontFollower = 5; 
            rightRearFollower = 4;

            wheelDiameter = 6;

            leftFlywheel = 422;
            rightFlywheel = 422;
            intakeMotor = 422;
            bottomBelt = 422;
            middleBelt = 422;
            feederWheel = 422;

            leftClimber = 422;
            rightClimber = 422;

            intakeExtensionIn = 0;
            intakeExtensionOut = 1;
        } else if (bot == BotNames.AXIDRIVE){
            leftMiddleMaster = 1;
            leftFrontFollower = 2;
            leftRearFollower = 3;
            rightMiddleMaster = 4;
            rightFrontFollower = 5; 
            rightRearFollower = 6;

            wheelDiameter = 6;

            leftFlywheel = 422;
            rightFlywheel = 422;
            intakeMotor = 422;
            bottomBelt = 422;
            middleBelt = 422;
            feederWheel = 422;

            leftClimber = 422;
            rightClimber = 422;

            intakeExtensionIn = 0;
            intakeExtensionOut = 1;
        }else if (bot == BotNames.PBOT20){
        //jenkyboi tm
        leftMiddleMaster = 34;
        leftFrontFollower = 3;
        leftRearFollower = 6;
        rightMiddleMaster = 31;
        rightFrontFollower = 5; 
        rightRearFollower = 4;

        wheelDiameter = 6;

        leftFlywheel = 422;
        rightFlywheel = 422;
        intakeMotor = 422;
        bottomBelt = 422;
        middleBelt = 422;
        feederWheel = 422;

        leftClimber = 422;
        rightClimber = 422;

        intakeExtensionIn = 0;
        intakeExtensionOut = 1;
    }

    //set up  networktables
    csinst = CameraServer.getInstance();
    ntinst = NetworkTableInstance.getDefault();
}

    /**
     * @return The speed cap for the drive base in teleop.
     */
    public static double getSpeedCap() {
        return speedCap;
    }

    /**
     * @return The rotation speed cap for the drive base in teleop.
     */
    public static double getRotationCap() {
        return rotationCap;
    }

    /**
     * Sets the caps on speed & rotation for the drive base in teleop.
     * @param newSpeedCap The speed cap to set (0 to 1).
     * @param newRotationCap The rotation speed cap to set (0 to 1).
     */
    public static void setSpeedAndRotationCaps(double newSpeedCap, double newRotationCap) {
        speedCap = (newSpeedCap > 1) ? 1 : newSpeedCap;
        rotationCap = (newRotationCap > 1) ? 1 : newRotationCap;
    }

    /**
     * @param inches Inches to convert.
     * @return The equivalent distance in ticks.
     */
    public static double convertToTicks(double inches) {
        return (4096 / (wheelDiameter * 3.1415926) * inches);
    }

}