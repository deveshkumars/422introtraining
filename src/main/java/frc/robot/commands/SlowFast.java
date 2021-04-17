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

    public void initialize() { // Checks to see if the robot is in fast mode
      if (RobotMap.isSpeedMode) { // Checking if the robot is set to speed mode
        RobotMap.setSpeedAndRotationCaps(0.3, 0.5);
        RobotMap.isSpeedMode = false;
      } else { // if it is not then return to slow mode
        RobotMap.setSpeedAndRotationCaps(0.8, 0.7);
        RobotMap.isSpeedMode = true;
      }  
    }

    public boolean isFinished() { // and lo it is complete
        return true;
    }

}