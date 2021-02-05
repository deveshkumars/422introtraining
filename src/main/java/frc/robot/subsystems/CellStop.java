package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;

/**
 * The mechanism that feeds balls into the flywheel.
 */
public class CellStop extends SubsystemBase {
    
    private CANSparkMax feederWheel;

    public CellStop(){
        setSubsystem("CellStop");
        this.feederWheel = new CANSparkMax(RobotMap.feederWheel, MotorType.kBrushless);
    }

    /**
     * Sets the feeder wheel's speed.
     * @param speed The speed to set (-1 to 1).
     */
    public void feedBalls(double speed) {
        feederWheel.set(speed);
    }

    /**
     * Stops the feeder wheel.
     */
    public void stopFeed() {
        feederWheel.set(0);
    }
}