package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Shooter;

public class TrapScoring extends SequentialCommandGroup {

    public TrapScoring(Shooter shooter, IntakeSubsystem intake, Arm arm) {
        ShootAssistSplitSpeed shootAssist = new ShootAssistSplitSpeed(shooter, intake, arm, Constants.TRAP_SCORING_ARM_ANGLE, Constants.TRAP_SCORING_POWER);

        addCommands(shootAssist);
    }
}
