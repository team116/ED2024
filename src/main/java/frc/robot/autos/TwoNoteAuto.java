package frc.robot.autos;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.autos.primitives.DriveDistanceAtAngle;
import frc.robot.autos.primitives.DriveDistanceAtAngle.Direction;
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

        DriveDistanceAtAngle driveForwardToSpeaker = new DriveDistanceAtAngle(swerve, 111, Direction.FORWARD);

        ParallelCommandGroup intakeNoteAndPrepareToShoot = new ParallelCommandGroup(
            driveForwardToSpeaker,
            new SequentialCommandGroup(
                intakeCommand,
                moveArmToShootingAngle,
                prepNoteToShoot
            ) 
        );

        RunShooterAtPowerAndDuration runShooterAtPowerAndDuration =
            new RunShooterAtPowerAndDuration(shooter, 0.8, 5);

        ParallelCommandGroup shootingSequence = new ParallelCommandGroup(
            runShooterAtPowerAndDuration,
            new SequentialCommandGroup(
                new DurationCommand(2),
                new IntakeCommand(intakeSubsystem, 1.0)
            )
        );

        addCommands(
            new InstantCommand(() -> SmartDashboard.putNumber("autoStart TwoNote", Timer.getFPGATimestamp())),
            new OneNoteAutoAndMoveOut(swerve, arm, intakeSubsystem, shooter, timeToWaitAtStart),
            new MoveArmToAngle(arm, 10.0, 2.0),
            intakeNoteAndPrepareToShoot,
            shootingSequence,
            new InstantCommand(() -> SmartDashboard.putNumber("autoFinish TwoNote", Timer.getFPGATimestamp()))
        );
    }
}
