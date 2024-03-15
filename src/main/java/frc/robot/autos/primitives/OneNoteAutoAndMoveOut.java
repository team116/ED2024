package frc.robot.autos.primitives;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autos.primitives.DriveDistanceAtAngle.Direction;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SwerveSubsystem;

public class OneNoteAutoAndMoveOut extends SequentialCommandGroup {
    public OneNoteAutoAndMoveOut(SwerveSubsystem swerve, IntakeSubsystem intakeSubsystem, Shooter shooter, int timeToWaitAtStart) {
        DurationCommand waitToShoot = new DurationCommand(timeToWaitAtStart);
        RunShooterAtSpeed spinMotorUpToSpeed = new RunShooterAtSpeed(shooter, 20, 10); // TODO: need to find out what the actual speed is

        DurationCommand timeToWaitToSendNoteToShooter = new DurationCommand(2); // TODO: need to find out the cottect time
        IntakeCommand sendNoteToShooter = new IntakeCommand(intakeSubsystem, timeToWaitAtStart);
        ParallelCommandGroup shootingSequence = new ParallelCommandGroup(spinMotorUpToSpeed, new SequentialCommandGroup(timeToWaitToSendNoteToShooter, sendNoteToShooter));

        DriveDistanceAtAngle driveBackASmallDistance = new DriveDistanceAtAngle(swerve, 3, Direction.REVERSE);

        DriveDistanceAtAngle driveOutOfZone = new DriveDistanceAtAngle(swerve, 84, Direction.REVERSE);

        addCommands(waitToShoot,
                    spinMotorUpToSpeed,
                    shootingSequence,
                    driveBackASmallDistance,
                    driveOutOfZone);
    }
}
