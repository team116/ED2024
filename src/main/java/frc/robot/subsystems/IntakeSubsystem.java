package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase{
    private CANSparkMax intakeMotor1;
    private CANSparkMax intakeMotor2;

    public IntakeSubsystem() {
        intakeMotor1 = new CANSparkMax(Constants.INTAKE_MOTOR_1_ID, MotorType.kBrushless);
        intakeMotor2 = new CANSparkMax(Constants.INTAKE_MOTOR_2_ID, MotorType.kBrushless);

        intakeMotor1.setInverted(true); //Originally was 
        intakeMotor2.setInverted(true);
    }

    public void runRollersToConsume() {
        intakeMotor1.set(1.0);
        intakeMotor2.set(1.0);
    }

    public void runRollersToVomit() {
        intakeMotor1.set(-0.5);
        intakeMotor2.set(-0.5);
    }

    public void stopMotor() {
        intakeMotor1.set(0);
        intakeMotor2.set(0);
    }
}
