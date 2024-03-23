package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.HelperFunctions;
import frc.robot.autos.primitives.ConfirmShooterSpeed;
import frc.robot.autos.primitives.IntakeCommand;
import frc.robot.autos.primitives.MoveArmToAngle;
import frc.robot.autos.primitives.RunShooterAtSplitPowerAndDuration;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Shooter;

public class ShootAssistSplitSpeed extends SequentialCommandGroup {

    public ShootAssistSplitSpeed(Shooter shooter, IntakeSubsystem intake, Arm arm, double angle, double power) {
        RunShooterAtSplitPowerAndDuration runShooterAtSplitPowerAndDuration = new RunShooterAtSplitPowerAndDuration(shooter, power, Double.POSITIVE_INFINITY);
        MoveArmToAngle moveArmToAngle = new MoveArmToAngle(arm, angle, 2.0);

        double desiredShooterSpeed = HelperFunctions.getShooterRPMsFromShooterPower(power);
        
        ConfirmShooterSpeed confirmShooterSpeed = new ConfirmShooterSpeed(shooter, desiredShooterSpeed, 2.0);
        IntakeCommand shootNote = new IntakeCommand(intake, 0.5);

        ParallelCommandGroup moveArmAndConfirmSpeed = new ParallelCommandGroup(moveArmToAngle, confirmShooterSpeed);
        SequentialCommandGroup aimReadyFire = new SequentialCommandGroup(
            moveArmAndConfirmSpeed,
            shootNote
        );
        
        addCommands(
            new ParallelDeadlineGroup(
                aimReadyFire, 
                runShooterAtSplitPowerAndDuration
            )
        );
    }    
    
}
