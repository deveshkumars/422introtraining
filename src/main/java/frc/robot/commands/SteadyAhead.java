package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Subsystems;

public class SteadyAhead extends CommandBase {

    private double angle;
    private double speed = 0.75; //place holder

    public SteadyAhead() {
        setName("SteadyAhead");
        addRequirements(Subsystems.driveBase);
    }

    public void execute() {
        angle = Subsystems.driveBase.getGyroAngle()+(((RobotMap.sideCount)%4)*90);
        if (angle > (((RobotMap.sideCount+1)%4)*90)) {
            Subsystems.driveBase.setMotors(-speed, speed, -speed, speed);
        } else if (angle <(((RobotMap.sideCount+1)%4)*90)) {
            Subsystems.driveBase.setMotors(speed, -speed, speed, -speed);
        } else {
            Subsystems.driveBase.stopMotors();
        }
    }

    public void end() {

        Subsystems.driveBase.zeroGyroAngle();

    }

    public boolean isFinished() {

        return angle == (90*((RobotMap.sideCount)%4));

    }

}