package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkLimitSwitch;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterAndArm extends SubsystemBase{
    private CANSparkMax shooterMotor1;  // TODO: Might be "closer"/"further", find out when installed
    private CANSparkMax shooterMotor2;
    private CANSparkMax armRotationMotor;
    private DutyCycleEncoder armEncoder; 
    private CANSparkMax intakeMotor;
    private SparkLimitSwitch shooterLimitSwitch;

    public ShooterAndArm() {
        shooterMotor1 = new CANSparkMax(Constants.SHOOTER_MOTOR_1_ID, MotorType.kBrushless);
        shooterMotor2 = new CANSparkMax(Constants.SHOOTER_MOTOR_2_ID, MotorType.kBrushless);
        armRotationMotor = new CANSparkMax(Constants.ARM_ROTATION_MOTOR_ID, MotorType.kBrushless);
        intakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR_ID, MotorType.kBrushless);
        armEncoder = new DutyCycleEncoder(Constants.ARM_ENCODER_CHANNEL);
    }
}
