package frc.robot.autos;

import com.ctre.phoenix.sensors.Pigeon2;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autos.primitives.RotateInPlaceByEncoders;
import frc.robot.subsystems.SwerveSubsystem;

public class TestRotationByEncoders extends SequentialCommandGroup{
    public TestRotationByEncoders(SwerveSubsystem swerveSubsystem, Pigeon2 gyro){
        RotateInPlaceByEncoders rotate180 = new RotateInPlaceByEncoders(swerveSubsystem, 180.0);
        
        addCommands(rotate180);
    }
}
