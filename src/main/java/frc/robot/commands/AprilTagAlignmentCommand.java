package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Swerve;

public class AprilTagAlignmentCommand extends Command {
    private Swerve swerve;
    private Limelight limelight;
    private int stabilizedCount;
    private boolean isActivelyMoving;
    private double startTime;

    public AprilTagAlignmentCommand(Swerve swerveSubstem, Limelight limelightSubsystem) {
        this.swerve = swerveSubstem;
        this.limelight = limelightSubsystem;
        addRequirements(swerveSubstem, limelightSubsystem);
    }

    @Override
    public void initialize() {
        startTime = Timer.getFPGATimestamp();
        stabilizedCount = 0;
        isActivelyMoving = false;
        limelight.ledOn();  // NOTE: Not sure what the delay is between asking to turn on and it being on
    }

    @Override
    public void execute() {
        SmartDashboard.putNumber("ta", limelight.getTa());
        SmartDashboard.putNumber("tx", limelight.getTx());
        SmartDashboard.putNumber("ty", limelight.getTy());

        double offsetAngleDegrees = limelight.horizontalOffsetFromCrosshairAsDegrees();
        // double offsetDistanceMeters = limelight.
        if (stillNeedToMove(offsetAngleDegrees)) {
            if (!isActivelyMoving) {
                isActivelyMoving = true;
                stabilizedCount = 0;
                double metersPerSecondSpeed = 0.125d;
                if (offsetAngleDegrees > 0.0d) {
                    metersPerSecondSpeed = -metersPerSecondSpeed;
                }
                swerve.drive(new Translation2d(0.0d, 0.0d), metersPerSecondSpeed, false, true);
            }
        } else {
            isActivelyMoving = false;
            swerve.drive(new Translation2d(0.0d, 0.0d), 0, false, true);
            ++stabilizedCount;
        }
    }

    @Override
    public boolean isFinished() {
          // Only exit after 3 non-movements, or timer hits half second
        return (stabilizedCount > 3 || (Timer.getFPGATimestamp() - startTime > 1.5d));
    }

    @Override
    public void end(boolean interrupted) {
        limelight.ledOff();
    }

    private boolean stillNeedToMove(double offsetAngleDegrees) {
        return limelight.hasValidTarget() &&
               Math.abs(limelight.horizontalOffsetFromCrosshairAsDegrees()) > 0.75d;
    }
}
