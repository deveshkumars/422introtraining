package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;

/**
 * The robot's climber mechanism.
 */
public class Climber extends SubsystemBase {

    public WPI_TalonSRX leftClimber;
    public WPI_TalonSRX rightClimber;

    public Climber() {
        setSubsystem("Climber");
        this.leftClimber = new WPI_TalonSRX(RobotMap.leftClimber);
        this.rightClimber = new WPI_TalonSRX(RobotMap.rightClimber);
    }

    /**
     * Extends the climber upwards. This lowers the robot if it is in the air.
     * @param speed The speed at which to extend (0 to 1).
     */
    public void extendClimber(double speed) {
        leftClimber.set(speed);
        rightClimber.set(speed);
    }

    /**
     * Retracts the climber. This raises the robot if the climber is attached to something.
     * @param speed The speed at which to retract (0 to 1).
     */
    public void retractClimber(double speed) {
        leftClimber.set(-speed);
        rightClimber.set(-speed);
    }

    /**
     * Stops the climber from moving up or down.
     */
    public void stopClimber() {
        leftClimber.stopMotor();
        rightClimber.stopMotor();
    }
}
