package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkLimitSwitch;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
    private DutyCycleEncoder armEncoder;
    private CANSparkMax armRotationMotor;
    private SparkLimitSwitch arm1LimitSwitch;
    private SparkLimitSwitch arm2LimitSwitch;

    public Arm() {
        armEncoder = new DutyCycleEncoder(Constants.ARM_ENCODER_CHANNEL);
        armRotationMotor = new CANSparkMax(Constants.ARM_ROTATION_MOTOR_ID, MotorType.kBrushless);
    }
}
