package frc.robot.userinterface;
 
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Joystyx {
    private Joystick joystick;
    public JoystickButton bumper;
 
    public Joystyx(int port) {
        this.joystick = new Joystick(port);
        this.bumper = new JoystickButton(joystick, 1);
    }
 
    public double getJoystickY() { return joystick.getRawAxis(1); }
 
}