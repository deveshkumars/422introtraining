package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.RobotMap;
import frc.robot.subsystems;

public class DriveStraight extends CommandBase {
    private double ticks;
    private boolean forward;
    private double speed;

    public DriveStraight(double inches, double speed){
        setName("DriveStraight");
        addRequirements(Subsystems.driveBase);
        this.ticks = RobotMap.convertToTicks(inches);
        this.foward = inches > 0;
        this.speed = speed;
    }
    
    public void initialize(){
        Subsystems.driveBase.zeroEncoderPosition();
        Subsystems.driveBase.zeroGyroAngle();
    }

    public void execute(){
        //Find correction to represent how far off robot is from straight linbe
        double correction = Subsystems.driveBase.getGyroAngle();
        correction *= 0.05;
        correction += 1.0;

        if (this.forward) {
            Subsystems.driveBase.setMotors(this.speed, this.speed * correction);
        } else {
            Subsystems.driveBase.setMotors(-this.speed * correction, -this.speed);
        }
    }

    public boolean isFinished() {
        double leftPosition = Math.abs(Subsystems.driveBase.getLeftPosition());
        double rightPosition = Math.abs(Subsystems.driveBase.getRightPosition());
        return (leftPosition > this.ticks) || (rightPosition > this.ticks);
    }

    public void end(boolean interrupted) {
        Subsystems.driveBase.setMotors(0,0);
    }
}