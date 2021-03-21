package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;

public class Circle extends CommandBase {

    private double radius;
    private double angle;

    private double maxSpeed = 0.4;
    private double otherSpeed;
    
    /**
     * @param radius The radius of the arc you want to traverse. Positive values turn you right, while negative values turn you left.
     * @param angle The angle you want to turn through on this circle, in degrees.
     */
    public Circle(double radius, double angle) {
        setName("Circle");
        addRequirements(Subsystems.driveBase);
        this.radius = radius;
        this.angle = angle;
    }

    public void initialize() {
        Subsystems.driveBase.zeroGyroAngle();
    }

    public void execute() {
        setSpeeds();
    }

    public boolean isFinished() {
        if (angle>0) {
            return (Subsystems.driveBase.getGyroAngle() > this.angle);
        } else {
            return (Subsystems.driveBase.getGyroAngle() < this.angle);
        }
        
    }
    
    private void setSpeeds() {
        if(radius > 0){
            otherSpeed = (maxSpeed / (radius + (RobotMap.robotWidth / 2))) * (radius - (RobotMap.robotWidth / 2));
        } else {
            otherSpeed = (maxSpeed / (radius - (RobotMap.robotWidth / 2))) * (radius + (RobotMap.robotWidth / 2));
        }
        
        if (radius > 0) {
            Subsystems.driveBase.setMotors(-maxSpeed, -otherSpeed);
        } else {
            Subsystems.driveBase.setMotors(-otherSpeed, -maxSpeed);        
        }
        System.out.println(Subsystems.driveBase.getGyroAngle());
    }
}
