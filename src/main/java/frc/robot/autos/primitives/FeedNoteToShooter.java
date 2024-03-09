package frc.robot.autos.primitives;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autos.primitives.RunIntakeMotorAnyDirection.RollerDirection;
import frc.robot.subsystems.IntakeSubsystem;

public class FeedNoteToShooter extends SequentialCommandGroup {
    public FeedNoteToShooter(IntakeSubsystem intakeSubsystem, double duration) {
        RunIntakeMotorAnyDirection feedNoteToSooter = new RunIntakeMotorAnyDirection(intakeSubsystem, duration, RollerDirection.CONSUME);

        addCommands(feedNoteToSooter);
    }

    public FeedNoteToShooter(IntakeSubsystem intakeSubsystem) {
        this(intakeSubsystem, 1.0);
    }
}
