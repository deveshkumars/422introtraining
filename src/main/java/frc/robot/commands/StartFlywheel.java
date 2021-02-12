package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;

/**
 * Changes the "gear" of the robot to drive fast or slow "slowmode"
 */
public class StartFlywheel extends CommandBase {

    private double speed;

    public StartFlywheel() {
        setName("SlowFast");
        addRequirements(Subsystems.flyboi);
        this.speed = Subsystems.flyboi.wheelSpeed;
    }

    public StartFlywheel(double Speed){
        setName("SlowFast");
        addRequirements(Subsystms.flyboi);
        this.speed = Speed;
    }

    public void initialize(){

    }

    public void execute(){
        Subsystems.flyboi.setShootVoltage(speed); // insert speed
    }

    public boolean isFinished(){
        return true;
    }

}