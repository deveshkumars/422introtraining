package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {

    public WPI_TalonSRX leftClimber;
    public WPI_TalonSRX rightClimber;

    public Climber() {
        setSubsystem("Climber");
        this.leftClimber = new WPI_TalonSRX(RobotMap.leftClimber);
        this.rightClimber = new WPI_TalonSRX(RobotMap.rightClimber);
    }

}
