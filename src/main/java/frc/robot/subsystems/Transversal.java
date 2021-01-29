package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Transversal extends SubsystemBase {

    public WPI_TalonSRX bottomBelt;
    public WPI_TalonSRX middleBelt;

    public Transversal() {
        setSubsystem("Transversal");
        this.bottomBelt = new WPI_TalonSRX(RobotMap.bottomBelt);
        this.middleBelt = new WPI_TalonSRX(RobotMap.middleBelt);
    }
    
}
