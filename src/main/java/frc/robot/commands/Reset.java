package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Subsystems;

public class Reset extends CommandBase {

    private double angle;
    private double oldSideCount;
    private double speed = 0.75; //place holder

    public Reset() {
        setName("Reset");
        addRequirements(Subsystems.driveBase);
    }

    public void initialize() {
        oldSideCount = RobotMap.sideCount;
        RobotMap.sideCount = 0;
    }

    public void execute() {
        angle = Subsystems.driveBase.getGyroAngle()+(((oldSideCount)%4)*90);
        if (angle > (((RobotMap.sideCount)%4)*90)) {
            Subsystems.driveBase.setMotors(-speed, speed, -speed, speed);
        } else if (angle <(((RobotMap.sideCount)%4)*90)) {
            Subsystems.driveBase.setMotors(speed, -speed, speed, -speed);
        } else {
            Subsystems.driveBase.stopMotors();
        }
    }

    public void end() {

        Subsystems.driveBase.zeroGyroAngle();

    }

    public boolean isFinished() {

        return (Subsystems.driveBase.getGyroAngle() == 0);
        
    }

}