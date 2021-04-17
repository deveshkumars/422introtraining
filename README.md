#README

* The highest level of the code is Main.java which literally just initializes. Don't touch it.
* Then we have Robot.java which sets up the robot class and initializes subsystems.
* Robot.java's robotInitialize method calls set methods in RobotMap.java and UIMap.java that configure the robot's motor controller ports and the control scheme based on enum inputs
* Subsystem files take motor controller ports and other functionality and combine them into useable subsystems. An instance of each subsystem is then created in Subsystems.java
* Commands combine the various functionalities of a subsystem into well-defined tasks that can be called in autonomous or teleop.
* User interface sets up the ways for drivers to control the robot and its subsystems. It creates instances of the controller types defined in the userinterface folder.
* Autonomous and teleop methods in Robot.java call the commands according to auto plans or input from the driver controls in UserInterface.java
* ShuffleboardControl sets up ShuffleBoard so the drivers can look at something and understand what is happening. It is Kiera's child.
* We rely on WPILib to access motors and the robot as well as create Shuffleboard. If they ever stop maintaining it we are toast.

#Hierarchy

Java/frc/robot:

    Commands
    
        Autonomous

        Circle.java

        DriveStraight.java

        FindGSPath.java - find galactic search path/useless piece of trash

        IntakeGo.java

        IntakeToggle.java - Toggles the intake
        
        ShootSequence.java

        ShootStop.java

        SlowFast.java

        TankDrive.java

        Turn.java

        Vomit.java
    Subsystems

        CellStop.java

        Climber.java
        
        DriveBase.java
        
        Flyboi.java
        
        Intake.java - extends and retracts the intake
        
        Subsystems.java
        
        Transversal.java
        
        Vision.java
        
    Userinterface 

Note:
* Don't mess anything up or else
* Don't fail on master (failing on branches is okay)
* You can do it :)