package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterAndArm extends SubsystemBase{
    private CANSparkMax shooterMotorTop;
    private CANSparkMax shooterMotorBottom;
    private CANSparkMax armRotationMotor;
    private Object armEncoder; // TODO: Need to find out how to get this in code
    private CANSparkMax intakeMotor;
    private SparkMaxLimitSwitch shooterLimitSwitch;

    public ShooterAndArm() {
        shooterMotorTop = new CANSparkMax(Constants.SHOOTER_MOTOR_TOP_ID, MotorType.kBrushless);
        shooterMotorBottom = new CANSparkMax(Constants.SHOOTER_MOTOR_BOTTOM_ID, MotorType.kBrushless);
        armRotationMotor = new CANSparkMax(Constants.ARMR_ROTATION_MOTOR_ID, MotorType.kBrushless);
        intakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR_ID, MotorType.kBrushless);
    }
}
