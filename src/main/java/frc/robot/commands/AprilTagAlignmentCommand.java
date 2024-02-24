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
    private double desiredDistanceFromAprilTagFeet = 10;

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
        SmartDashboard.putNumber("Distance From Limelight in Feet", limelight.distanceFromAprilTagFeet());
        SmartDashboard.putNumber("ta", limelight.getTa());
        SmartDashboard.putNumber("tx", limelight.getTx());
        SmartDashboard.putNumber("ty", limelight.getTy());
        
        double offsetAngleDegrees = limelight.horizontalOffsetFromCrosshairAsDegrees();
        // double offsetDistanceMeters = limelight.

        if (stillNeedToRotate(offsetAngleDegrees) || stillNeedToMove(desiredDistanceFromAprilTagFeet)) {
            // if (!isActivelyMoving) {
                isActivelyMoving = true;
                stabilizedCount = 0;
                double degreesPerSecondSpeed = 0.125d;
                double metersPerSecondSpeed = 0.125d;
                if (stillNeedToRotate(offsetAngleDegrees)){
                    if (offsetAngleDegrees > 0.0d) {
                        degreesPerSecondSpeed = -degreesPerSecondSpeed;
                    }
                } else {
                    degreesPerSecondSpeed = 0.0d;
                }
                if (stillNeedToMove(offsetAngleDegrees)) {
                    if (needToMoveBackward(desiredDistanceFromAprilTagFeet)) {
                        metersPerSecondSpeed = -metersPerSecondSpeed;
                    }
                } else {
                    metersPerSecondSpeed = 0.0d;
                }
                swerve.drive(new Translation2d(metersPerSecondSpeed, 0.0d), degreesPerSecondSpeed, false, true);
            // }
        } else {
            isActivelyMoving = false;
            swerve.drive(new Translation2d(0.0d, 0.0d), 0, false, true);
            ++stabilizedCount;
        }
    }

    @Override
    public boolean isFinished() {
          // Only exit after 3 non-movements, or timer hits half second
        return (stabilizedCount > 20 || (Timer.getFPGATimestamp() - startTime > 10d));
    }

    @Override
    public void end(boolean interrupted) {
        limelight.ledOff();
    }

    private boolean stillNeedToRotate(double offsetAngleDegrees) {
        return limelight.hasValidTarget() &&
               Math.abs(limelight.horizontalOffsetFromCrosshairAsDegrees()) > 0.75d;
    }

    private boolean stillNeedToMove(double distanceFeet) {
        return needToMoveForward(distanceFeet) || needToMoveBackward(distanceFeet);
    }

    private boolean needToMoveForward(double desiredDistanceMeters) {
        return limelight.hasValidTarget() && desiredDistanceMeters < limelight.distanceFromAprilTagFeet();
    }

    private boolean needToMoveBackward(double desiredDistanceMeters) {
        return limelight.hasValidTarget() && desiredDistanceMeters > limelight.distanceFromAprilTagFeet();
    }

    public void setDesiredDistanceFromAprilTag(double distanceFeet) {
        desiredDistanceFromAprilTagFeet = distanceFeet;
    }
}
