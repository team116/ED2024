package frc.robot.autos.primitives;

import frc.robot.subsystems.Shooter;

public class RunShooterAtSpeed extends DurationCommand {
    Shooter shooter;
    public RunShooterAtSpeed(Shooter shooterSubsystem, double duration) {
        super(duration);
        shooter = shooterSubsystem;

        addRequirements(shooter);
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
