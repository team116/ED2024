package frc.robot.autos.primitives;

import frc.robot.subsystems.Arm;

public class MoveArmToAngle extends DurationCommand {
    Arm armSubsystem;
    
    public MoveArmToAngle(Arm arm, double duration) {
        super(duration);
        armSubsystem = arm;

        addRequirements(arm);
    }
}
