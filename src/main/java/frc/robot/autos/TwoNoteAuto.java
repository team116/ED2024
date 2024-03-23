package frc.robot.autos;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.autos.primitives.DriveDistanceAtAngle;
import frc.robot.autos.primitives.DriveDistanceAtAngle.Direction;
import frc.robot.autos.primitives.DriveDistanceAtAngle.Speed;
import frc.robot.autos.primitives.DurationCommand;
import frc.robot.autos.primitives.IntakeCommand;
import frc.robot.autos.primitives.MoveArmToAngle;
import frc.robot.autos.primitives.PrepNoteToShoot;
import frc.robot.autos.primitives.RunShooterAtPowerAndDuration;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SwerveSubsystem;

public class TwoNoteAuto extends SequentialCommandGroup {
    public TwoNoteAuto(SwerveSubsystem swerve, Arm arm, IntakeSubsystem intakeSubsystem, Shooter shooter, double timeToWaitAtStart) {

        IntakeCommand intakeCommand = new IntakeCommand(intakeSubsystem, 3);
        MoveArmToAngle moveArmToShootingAngle = new MoveArmToAngle(arm, Constants.SPEAKER_SHOOTING_ARM_ANGLE, 3.0);
        PrepNoteToShoot prepNoteToShoot = new PrepNoteToShoot(intakeSubsystem);

        DriveDistanceAtAngle driveForwardToSpeaker = new DriveDistanceAtAngle(swerve, 93, Direction.FORWARD);  // Original 111
        DriveDistanceAtAngle driveOutOfZone = new DriveDistanceAtAngle(swerve, 93, Direction.REVERSE, Speed.FAST);  // Original 111

        ParallelCommandGroup intakeNoteAndPrepareToShoot = new ParallelCommandGroup(
            driveForwardToSpeaker,
            new SequentialCommandGroup(
                intakeCommand,
                moveArmToShootingAngle,
                prepNoteToShoot
            ) 
        );

        RunShooterAtPowerAndDuration runShooterAtPowerAndDuration =
            new RunShooterAtPowerAndDuration(shooter, Constants.SPEAKER_SHOOTING_POWER, 2);

        ParallelCommandGroup shootingSequence = new ParallelCommandGroup(
            runShooterAtPowerAndDuration,
            new SequentialCommandGroup(
                new DurationCommand(1.5),
                new IntakeCommand(intakeSubsystem, 0.5)
            )
        );

        RunShooterAtPowerAndDuration secondRunShooterAtPowerAndDuration =
            new RunShooterAtPowerAndDuration(shooter, Constants.SPEAKER_SHOOTING_POWER, 2);

        ParallelCommandGroup secondShootingSequence = new ParallelCommandGroup(
            secondRunShooterAtPowerAndDuration,
            new SequentialCommandGroup(
                new DurationCommand(1.5),
                new IntakeCommand(intakeSubsystem, 0.5)
            )
        );

        addCommands(
            new InstantCommand(() -> SmartDashboard.putNumber("autoStart TwoNote", Timer.getFPGATimestamp())),
            shootingSequence,
            new InstantCommand(() -> intakeSubsystem.runAssistRollersToVomitSlow()),
            driveOutOfZone,
            new MoveArmToAngle(arm, Constants.FLOOR_INTAKE_ARM_ANGLE, 2.0),
            intakeNoteAndPrepareToShoot,
            secondShootingSequence,
            new InstantCommand(() -> SmartDashboard.putNumber("autoFinish TwoNote", Timer.getFPGATimestamp()))
        );
    }
}
