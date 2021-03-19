package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;

public class Circle extends CommandBase {

    private double radius;
    private double angle;

    private double maxSpeed = 0.8;
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
        driveBase.zeroGyroAngle();
    }

    public void execute() {
        setSpeeds();
    }

    public boolean isFinished() {
        return (driveBase.getGyroAngle() > this.angle);
    }
    
    private void setSpeeds() {
        otherSpeed = (maxSpeed / (radius + (RobotMap.robotWidth / 2))) * (radius - (RobotMap.robotWidth / 2));
        
        if (radius > 0) {
            driveBase.setMotors(maxSpeed, otherSpeed);
        } else {
            driveBase.setMotors(otherSpeed, maxSpeed);
        }
    }
}
