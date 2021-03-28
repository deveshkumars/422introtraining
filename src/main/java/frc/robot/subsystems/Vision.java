package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.*;

import frc.robot.commands.autonomous.*;

import frc.robot.RobotMap;

public class Vision {
    NetworkTable visionTable;
    NetworkTableEntry cellRunnerEntry;
    NetworkTableEntry cellRotationEntry;
    NetworkTableEntry cellDistanceEntry;
    
    public Vision(){
        this.visionTable = RobotMap.ntinst.getTable("visionTable");
    }

    public GalacticSearch.Path findGSCase() {
        cellRunnerEntry = this.visionTable.getEntry("CellVisionRunner");
        cellRunnerEntry.forceSetBoolean(true);
        cellRotationEntry = this.visionTable.getEntry("CellVisionRotation");
        System.out.println("rotation entry is " + cellRotationEntry.getDouble(0) + " degrees");

        if(cellRotationEntry.getDouble(360) < 5 && cellRotationEntry.getDouble(360) > -5){
            System.out.println("Path Red A");
            return GalacticSearch.Path.RedA;
        }
        else if(cellRotationEntry.getDouble(360) < 27 && cellRotationEntry.getDouble(360) > 17.5){
            System.out.println("Path Blue A");
            return GalacticSearch.Path.BlueA;
        }
        else if(cellRotationEntry.getDouble(360) < -15 && cellRotationEntry.getDouble(360) > -25){
            System.out.println("Path Red B");
            return GalacticSearch.Path.RedB;
        }
        else if(cellRotationEntry.getDouble(360) < 17.5 && cellRotationEntry.getDouble(360) > 8){
            System.out.println("Path Blue B via right powercell");
            return GalacticSearch.Path.BlueB;
        }
        else if(cellRotationEntry.getDouble(360) < -2 && cellRotationEntry.getDouble(360) > -12){
            System.out.println("Path Blue B via left powercell");
            return GalacticSearch.Path.BlueB;
        }
        else {
            System.out.println("something went wrong");
            return null;
        }

        





    }
}
