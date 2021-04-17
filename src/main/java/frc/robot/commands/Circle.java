package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;

/**
 * Makes the robot go in a circle (experimental and doesn't work)
 */
public class Circle extends CommandBase {

    private double radius; //stores the radius of the circle
    private double angle; //stores the arc angle of the cirle

    private double maxSpeed = 0.4; //stores the maximum speed of one side of the drivebase
    private double otherSpeed; //stores the speed of the other side of the drivebase
    
    /**
     * @param radius The radius of the arc you want to traverse. Positive values turn you right, while negative values turn you left.
     * @param angle The angle you want to turn through on this circle, in degrees.
     */
    public Circle(double radius, double angle) {
        setName("Circle");
        addRequirements(Subsystems.driveBase);
        this.radius = radius;
        this.angle = angle;
        if (radius < 0 && angle > 0) { this.angle *= -1; }
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
    
    /**
     * Computes and sets the speeds of both sides of the drivebase
     */
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
    }
}
