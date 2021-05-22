package frc.robot.userinterface;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class WiimoteController extends GenericHID{

    public enum Button {
        kNumber1(1),
        kNumber2(2),
        kA(3),
        kB(4),
        kPlus(5),
        kMinus(6),
        kHome(7);
    
        @SuppressWarnings({"MemberName", "PMD.SingularField"})
        public final int value;
    
        Button(int value) {
          this.value = value;
        }
    }

    public enum Axis {
        kXAxis(0),
        kYAxis(1),
        kZAxis(2),
        kXRotate(3),
        kYRotate(4),
        kZRotate(5);
    
        @SuppressWarnings({"MemberName", "PMD.SingularField"})
        public final int value;
    
        Axis(int value) {
          this.value = value;
        }
    }

    public final JoystickButton A, B, Number1, Number2, Plus, Minus, Home;

    

    public WiimoteController(int port){
        super(port);
        this.Number1 = new JoystickButton(this, Button.kNumber1.value);
        this.Number2 = new JoystickButton(this, Button.kNumber2.value);
        this.A = new JoystickButton(this, Button.kA.value);
        this.B = new JoystickButton(this, Button.kB.value);
        this.Plus = new JoystickButton(this, Button.kPlus.value);
        this.Minus = new JoystickButton(this, Button.kMinus.value);
        this.Home = new JoystickButton(this, Button.kHome.value);
    }

    public double getX(Hand hand){
        return getRawAxis(Axis.kXAxis.value);
    }
    public double getY(Hand hand){
        return getRawAxis(Axis.kXAxis.value);
    }
    public double getXRotation(){
        return getRawAxis(Axis.kXRotate.value);
    }
    public double getYRotation(){
        return getRawAxis(Axis.kYRotate.value);
    }
    public boolean getButton2(){
        return getRawButton(Button.kNumber2.value);
    }
    public boolean getButton1(){
        return getRawButton(Button.kNumber1.value);
    }
    public boolean getButtonA(){
        return getRawButton(Button.kA.value);
    }
    public boolean getButtonB(){
        return getRawButton(Button.kB.value);
    }
    public boolean getButtonPlus(){
        return getRawButton(Button.kPlus.value);
    }
    public boolean getButtonMinus(){
        return getRawButton(Button.kMinus.value);
    }
    public boolean getButtonHome(){
        return getRawButton(Button.kHome.value);
    }

}