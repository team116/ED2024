package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autos.primitives.DriveDistanceAtAngle;
import frc.robot.autos.primitives.DriveDistanceAtAngle.Direction;
import frc.robot.autos.primitives.IntakeCommand;
import frc.robot.autos.primitives.MoveArmToAngle;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SwerveSubsystem;

public class TwoNoteAuto extends SequentialCommandGroup {
    public TwoNoteAuto(SwerveSubsystem swerve, Arm arm, IntakeSubsystem intakeSubsystem, Shooter shooter, double timeToWaitAtStart) {

        MoveArmToAngle moveArmForCollection = new MoveArmToAngle(arm, 10.0, 2.0);
        IntakeCommand intakeCommand = new IntakeCommand(intakeSubsystem, 3);

        //DriveDistanceAtAngle driveBackToSpeaker = new DriveDistanceAtAngle(swerve, 108, returnDirection);

        DriveDistanceAtAngle driveBackASmallDistance = new DriveDistanceAtAngle(swerve, 3, Direction.REVERSE);


        addCommands(new OneNoteAutoAndMoveOut(swerve, arm, intakeSubsystem, shooter, timeToWaitAtStart));
    }
}
