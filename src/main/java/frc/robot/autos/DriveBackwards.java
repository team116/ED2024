package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autos.primitives.DriveDistance;
import frc.robot.autos.primitives.RotateInPlaceByEncoders;
import frc.robot.subsystems.SwerveSubsystem;

public class DriveBackwards extends SequentialCommandGroup{
    public DriveBackwards(SwerveSubsystem swerveSubsystem){
        DriveDistance driveBackAndOutOfTheStartingZone = new DriveDistance(swerveSubsystem, -60);
        
        addCommands(driveBackAndOutOfTheStartingZone);
    }
}
