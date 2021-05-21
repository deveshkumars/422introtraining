package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
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
        angle =RobotMap.trueAngle + Subsystems.driveBase.getGyroAngle();
        if (angle > 0) {
            Subsystems.driveBase.setMotors(-speed, speed, -speed, speed);
        } else if (angle < 0) {
            Subsystems.driveBase.setMotors(speed, -speed, speed, -speed);
        } else {
            Subsystems.driveBase.stopMotors();
        }
        System.out.println(angle);
    }

    public void end() {
        RobotMap.trueAngle = RobotMap.trueAngle + Subsystems.driveBase.getGyroAngle();
        Subsystems.driveBase.zeroGyroAngle();

    }

    public boolean isFinished() {

        return (angle == 0);

    }

}