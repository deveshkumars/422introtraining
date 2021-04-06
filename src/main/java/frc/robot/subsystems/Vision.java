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
        else {
            System.out.println("Path Red B");
            return GalacticSearch.Path.RedB;
        }

        





    }
}
