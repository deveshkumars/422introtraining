package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;

/**
 * Changes the "gear" of the robot to drive fast or slow "slowmode"
 */
public class SlowFast extends CommandBase {

    public SlowFast() {
        setName("SlowFast");
        addRequirements(Subsystems.driveBase);
    }

    public void initialize() {
      if (RobotMap.isSpeedMode) {
        RobotMap.setSpeedAndRotationCaps(0.3, 0.5);
        RobotMap.isSpeedMode = false;
      } else {
        RobotMap.setSpeedAndRotationCaps(1, 1);
        RobotMap.isSpeedMode = true;
      }  
    }

    public boolean isFinished() {
        return true;
    }

}