package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkLimitSwitch;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    private CANSparkMax intakeMotor;

    public Intake() {
        intakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR_ID, MotorType.kBrushless);
    }
}
