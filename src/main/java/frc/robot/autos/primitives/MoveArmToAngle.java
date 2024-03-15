package frc.robot.autos.primitives;

import frc.robot.subsystems.Arm;

public class MoveArmToAngle extends DurationCommand {
private static final double EPSILON = 0.1;

    private boolean atTargetAngle;
    private Arm armSubsystem;
    private double targetAngle;
    
    public MoveArmToAngle(Arm arm, double duration) {
        super(duration);
        armSubsystem = arm;

        addRequirements(arm);
    }
    
    @Override
    public void initialize() {
        super.initialize();
        atTargetAngle = false;
    }

    @Override
    public void execute() {
        super.execute();
        double previousAngle;
        double currentAngle = armSubsystem.getAngleDegrees();
        double diff = targetAngle - currentAngle;

        if (Math.abs(diff) < EPSILON) {
            armSubsystem.stop();
            atTargetAngle = true;
        } else if (diff < 0) {
            armSubsystem.moveUp();
        }else {
            armSubsystem.moveDown();
        }
    }

    @Override
    public void end(boolean interrupted) {
        armSubsystem.stop();
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished() || atTargetAngle;
    }
}
