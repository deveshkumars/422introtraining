package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * An example subsystem to copy and paste as starter code. To be deleted after today.
 */
public class ExampleSubsystem extends SubsystemBase {

    public WPI_TalonSRX mainMotor;

    public ExampleSubsystem() {
        setSubsystem("Example Subsystem");
        mainMotor = new WPI_TalonSRX(13);
    }

    /**
     * Some sort of short description, e.g. Sets the speed of the Example.
     * @param speed A short description including the domain of possible values, e.g. The power with which to spin the intake (-1 to 1).
     */
    public void setSpeed(double speed) {
        mainMotor.set(speed);
    }

    /**
     * Stops the speed of the Example.
     */
    public void stopMotor() {
        mainMotor.set(0);
    }

}
