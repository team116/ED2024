package frc.robot.autos;

import javax.naming.directory.DirContext;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autos.primitives.DriveDistanceAtAngle;
import frc.robot.autos.primitives.DurationCommand;
import frc.robot.autos.primitives.IntakeCommand;
import frc.robot.autos.primitives.MoveArmDownForDurationr;
import frc.robot.autos.primitives.RunShooterAtPowerAndDuration;
import frc.robot.autos.primitives.RunShooterAtSpeed;
import frc.robot.autos.primitives.DriveDistanceAtAngle.Direction;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SwerveSubsystem;

public class OneNoteAutoAndMoveOut extends SequentialCommandGroup {

    public OneNoteAutoAndMoveOut(SwerveSubsystem swerve, Arm arm, IntakeSubsystem intakeSubsystem, Shooter shooter, double timeToWaitAtStart) {
        this(swerve, arm, intakeSubsystem, shooter, timeToWaitAtStart, Direction.REVERSE);
    }

    public OneNoteAutoAndMoveOut(SwerveSubsystem swerve, Arm arm, IntakeSubsystem intakeSubsystem, Shooter shooter, double timeToWaitAtStart, Direction leaveDirection) {
        DurationCommand waitToShoot = new DurationCommand(timeToWaitAtStart);
        RunShooterAtSpeed spinMotorUpToSpeed = new RunShooterAtSpeed(shooter, 20, 10); // TODO: need to find out what the actual speed is

        DurationCommand timeToWaitToSendNoteToShooter = new DurationCommand(2); // TODO: need to find out the cottect time
        IntakeCommand sendNoteToShooter = new IntakeCommand(intakeSubsystem, timeToWaitAtStart);
        //ParallelCommandGroup shootingSequence = new ParallelCommandGroup(spinMotorUpToSpeed, new SequentialCommandGroup(timeToWaitToSendNoteToShooter, sendNoteToShooter));

        RunShooterAtPowerAndDuration runShooterAtPowerAndDuration =
            new RunShooterAtPowerAndDuration(shooter, 0.8, 5);

        ParallelCommandGroup shootingSequence = new ParallelCommandGroup(
            runShooterAtPowerAndDuration,
            new SequentialCommandGroup(
                new DurationCommand(2),
                new IntakeCommand(intakeSubsystem, 1.0)
            )
        );

        DriveDistanceAtAngle driveBackASmallDistance = new DriveDistanceAtAngle(swerve, 3, Direction.REVERSE);

        DriveDistanceAtAngle driveOutOfZone = new DriveDistanceAtAngle(swerve, 108, leaveDirection);

       // ParallelDeadlineGroup driveBackAndLowerShooter = new ParallelDeadlineGroup(
       //     driveOutOfZone,
       //     new MoveArmDownForDurationr(arm, 2.0));  

        addCommands(
                    waitToShoot,
                    shootingSequence,
                    driveBackASmallDistance,
                    driveOutOfZone);
                    //driveBackAndLowerShooter);
        
    }
}