package frc.robot.autos.primitives;

import frc.robot.subsystems.IntakeSubsystem;

public class RunIntakeMotorAnyDirection extends DurationCommand {
    private IntakeSubsystem intake;
    private double speed;
    private double distance;

    public enum RollerDirection {
        CONSUME,
        EXPEL
    }

    private final RollerDirection direction;

    public RunIntakeMotorAnyDirection(IntakeSubsystem intake, double maxTimeout, RollerDirection direction) {
        super(maxTimeout);
        this.intake = intake;
        this.direction = direction;
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
    
}
