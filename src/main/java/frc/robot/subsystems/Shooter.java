package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    private CANSparkMax shooterMotor1;  // TODO: Might be "closer"/"further", find out when installed
    private CANSparkMax shooterMotor2;

    public Shooter() {
        shooterMotor1 = new CANSparkMax(Constants.SHOOTER_MOTOR_1_ID, MotorType.kBrushless);
        shooterMotor2 = new CANSparkMax(Constants.SHOOTER_MOTOR_2_ID, MotorType.kBrushless);

        shooterMotor1.setIdleMode(IdleMode.kCoast);
        shooterMotor2.setIdleMode(IdleMode.kCoast);
    }
}
