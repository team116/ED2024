package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase{
    private CANSparkMax intakeMotor;

    public IntakeSubsystem() {
        intakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR_ID, MotorType.kBrushless);
    }
}
