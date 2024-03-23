package frc.robot.autos.primitives;

import frc.robot.subsystems.Shooter;

public class RunShooterAtSplitPowerAndDuration extends DurationCommand {

    private static final double VELOCITY_EPSILON = 0.01;

    private Shooter shooter;
    private double targetPower;

    public RunShooterAtSplitPowerAndDuration(Shooter shooterSubsystem, double power, double duration) {
        super(duration);
        shooter = shooterSubsystem;
        this.targetPower = power;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        super.initialize();
        shooter.setMotor1Power(targetPower * 0.5);
        shooter.setMotor2Power(targetPower);
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stop();
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }

}
