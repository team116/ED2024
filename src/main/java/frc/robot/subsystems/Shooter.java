package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    private CANSparkMax shooterMotor1;  // TODO: Might be "closer"/"further", find out when installed
    private CANSparkMax shooterMotor2;

    private RelativeEncoder shooter1Encoder;
    private RelativeEncoder shooter2Encoder;

    public Shooter() {
        shooterMotor1 = new CANSparkMax(Constants.SHOOTER_MOTOR_1_ID, MotorType.kBrushless);
        shooterMotor2 = new CANSparkMax(Constants.SHOOTER_MOTOR_2_ID, MotorType.kBrushless);

        shooterMotor1.setIdleMode(IdleMode.kCoast);
        shooterMotor2.setIdleMode(IdleMode.kCoast);

        shooterMotor2.setInverted(true);

        shooter1Encoder = shooterMotor1.getEncoder();
        shooter2Encoder = shooterMotor2.getEncoder();
    }

    public double getMotor1Velocity() {
        return shooter1Encoder.getVelocity();
    }

    public double getMotor2Velocity() {
        return shooter2Encoder.getVelocity();
    }

    public void setAllMotorPower(double percentagePower) {
        setMotor1Power(percentagePower);
        setMotor2Power(percentagePower);
    }

    public void setMotor1Power(double percentagePower) {
        shooterMotor1.set(percentagePower);
    }

    public void setMotor2Power(double percentagePower) {
        shooterMotor2.set(percentagePower);
    }

    public void stop() {
        shooterMotor1.set(0.0);
        shooterMotor2.set(0.0);
    }
}
