package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The subsystem moving power cells from the intake to the cell stop.
 */
public class Transversal extends SubsystemBase {

    public WPI_TalonSRX bottomBelt;
    public WPI_TalonSRX middleBelt;

    public Transversal() {
        setSubsystem("Transversal");
        this.bottomBelt = new WPI_TalonSRX(RobotMap.bottomBelt);
        this.middleBelt = new WPI_TalonSRX(RobotMap.middleBelt);
    }

    /**
     * Sets both transversal belts to run at a speed.
     * @param speed The speed to run at (-1 to 1).
     */
    public void setTransversalMotors(double speed) {
        bottomBelt.set(speed);
        middleBelt.set(speed);
    }
    
    /**
     * Stops both transversal belts.
     */
    public void stopTransversalMotors() {
        bottomBelt.set(0);
        middleBelt.set(0);
    }

}
