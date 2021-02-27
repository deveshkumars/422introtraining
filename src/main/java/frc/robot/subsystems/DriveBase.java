package frc.robot.subsystems;
 
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
// import com.analog.adis16470.frc.ADIS16470_IMU;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;
 
/**
* The drive base of the robot. Includes all drive train motor controllers as well as sensors such as gyros and encoders, and can use PID to set its motor speeds.
*/
public class DriveBase extends SubsystemBase {
 
    public WPI_TalonSRX leftMiddleMaster;
    public WPI_TalonSRX rightMiddleMaster;
    public WPI_VictorSPX leftFrontFollower;
    public WPI_VictorSPX leftRearFollower;
    public WPI_VictorSPX rightFrontFollower;
    public WPI_VictorSPX rightRearFollower;
    
    public Gyro gyro;
    private SpeedControllerGroup leftSide;
    private SpeedControllerGroup rightSide;
    public DifferentialDrive drive;
    private static final SPI.Port kGyroPort = SPI.Port.kOnboardCS0;
    private final DifferentialDriveOdometry odometry;
    
    public double leftMotorTicks = 0;
    public double rightMotorTicks = 0;
    
    public DriveBase() {
        this.leftMiddleMaster = new WPI_TalonSRX(RobotMap.leftMiddleMaster);
        this.rightMiddleMaster = new WPI_TalonSRX(RobotMap.rightMiddleMaster);

        this.leftFrontFollower = new WPI_VictorSPX(RobotMap.leftFrontFollower);
        this.leftRearFollower = new WPI_VictorSPX(RobotMap.leftRearFollower);
        this.rightFrontFollower = new WPI_VictorSPX(RobotMap.rightFrontFollower);
        this.rightRearFollower = new WPI_VictorSPX(RobotMap.rightRearFollower);

        leftFrontFollower.follow(leftMiddleMaster);
        leftRearFollower.follow(leftMiddleMaster);
        rightFrontFollower.follow(rightMiddleMaster);
        rightRearFollower.follow(rightMiddleMaster);
    
        leftMiddleMaster.setInverted(true);
        leftFrontFollower.setInverted(true);
        leftRearFollower.setInverted(true);
    
        this.gyro = new ADXRS450_Gyro();
    
        // zeroEncoderPosition(); // test this out in case it works
        leftMotorTicks = leftMiddleMaster.getSelectedSensorPosition(0);
        rightMotorTicks = rightMiddleMaster.getSelectedSensorPosition(0);
    
        this.leftSide = new SpeedControllerGroup(leftMiddleMaster, leftFrontFollower, leftRearFollower);
        this.rightSide = new SpeedControllerGroup(rightMiddleMaster, rightFrontFollower, rightRearFollower);
        this.drive = new DifferentialDrive(leftSide, rightSide);
    
        this.odometry = new DifferentialDriveOdometry(gyro.getRotation2d());

        drive.setSafetyEnabled(false);
    }
 
    @Override
    public void periodic() {
        odometry.update(gyro.getRotation2d(), getLeftPosition(), getRightPosition());
    }
    
    public Pose2d getPose() {
        return this.odometry.getPoseMeters();
    }
    
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(leftMiddleMaster.getSelectedSensorVelocity(0), rightMiddleMaster.getSelectedSensorVelocity(0));
    }
    
    public void resetOdometry(Pose2d pose) {
        zeroEncoderPosition();
        this.odometry.resetPosition(pose, gyro.getRotation2d());
    }
    
    public void arcadeDrive(double fwd, double rot) {
        this.drive.arcadeDrive(fwd, rot);
    }
    
    public void tankDriveVolts(double leftVolts, double rightVolts) {
        leftSide.setVoltage(leftVolts);
        rightSide.setVoltage(-rightVolts);
        drive.feed();
    }
    
    public void zeroEncoderPosition() {
        leftMotorTicks = leftMiddleMaster.getSelectedSensorPosition(0);
        rightMotorTicks = rightMiddleMaster.getSelectedSensorPosition(0);
    }
    
    public double getAverageEncoderDistance() {
        return (getLeftPosition() + getRightPosition()) / 2.0;
    }
    
    public void setMaxOutput(double maxOutput) {
        drive.setMaxOutput(maxOutput);
    }
    
    public void zeroHeading() {
        gyro.reset();
    }
    
    public double getHeading() {
        return gyro.getRotation2d().getDegrees();
    }
    
    public double getTurnRate() {
        return -gyro.getRate();
    }
    
    /**
        * @return Left side position in ticks.
        */
    public double getLeftPosition() {
        return leftMiddleMaster.getSelectedSensorPosition(0) - leftMotorTicks;
    }
    
    /**
        * @return Right side position in ticks.
        */
    public double getRightPosition() {
        return rightMiddleMaster.getSelectedSensorPosition(0) - rightMotorTicks;
    }
}