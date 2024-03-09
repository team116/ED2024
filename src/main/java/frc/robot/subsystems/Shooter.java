package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkLimitSwitch;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase{
    private CANSparkMax shooterMotor1;  // TODO: Might be "closer"/"further", find out when installed
    private CANSparkMax shooterMotor2;
    private SparkLimitSwitch shooterLimitSwitch;

    public Shooter() {
        shooterMotor1 = new CANSparkMax(Constants.SHOOTER_MOTOR_1_ID, MotorType.kBrushless);
        shooterMotor2 = new CANSparkMax(Constants.SHOOTER_MOTOR_2_ID, MotorType.kBrushless);
    }
}
