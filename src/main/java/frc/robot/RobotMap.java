package frc.robot;

/**
 * Ports for motor controllers, caps for speed and rotation, booleans for toggles, and turn direction.
 */
public class RobotMap {

    // Robot settings & toggles (mutable)

    private static double speedCap = 0.8;
    private static double rotationCap = 0.7;

    public static double sideCount = 0; //note using double because mod doesn't like int for some reason
    public static double trueAngle = 0;

    public static boolean isSpeedMode = true;
    public static boolean isFirstCamera = true;
    public static boolean isIntakeDown = false;
    public static int cellCount = 3;

    // Drive base ports

    public static int rightFront;
    public static int rightBack;
    public static int leftFront;
    public static int leftBack;

    public static int wheelDiameter;

    public enum BotNames {
        MECANUM
    }

    public static BotNames botName;

    /**
     * Sets the bot's ports based off of the bot's name. No further robot-specific setting is needed.
     * @param bot The name of the bot.
     */
    public static void setBot(BotNames bot) {
        botName = bot;
        if (bot == BotNames.MECANUM) {
            rightFront = 12;
            rightBack = 14;
            leftFront = 10;
            leftBack = 11;
            wheelDiameter = 4;
        }
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

}