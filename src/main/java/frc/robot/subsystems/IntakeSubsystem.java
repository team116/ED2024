package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase{
    private CANSparkMax intakeMotor1;
    private CANSparkMax intakeMotor2;
    private TalonSRX intakeAssistMotor;

    public IntakeSubsystem() {
        intakeMotor1 = new CANSparkMax(Constants.INTAKE_MOTOR_1_ID, MotorType.kBrushless);
        intakeMotor2 = new CANSparkMax(Constants.INTAKE_MOTOR_2_ID, MotorType.kBrushless);
        intakeAssistMotor = new TalonSRX(Constants.INTAKE_ASSIST_MOTOR_ID);

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

    public void runAssistRollersToConsume() {
        intakeAssistMotor.set(TalonSRXControlMode.PercentOutput, 1.0);
    }

    public void runAssistRollersToConsumeSlow() {
        intakeAssistMotor.set(TalonSRXControlMode.PercentOutput, 0.5);
    }

    public void runAssistRollersToVomit() {
        intakeAssistMotor.set(TalonSRXControlMode.PercentOutput, -1.0);
    }

    public void runAssistRollersToVomitSlow() {
        intakeAssistMotor.set(TalonSRXControlMode.PercentOutput, -0.5);
    }

    public void runBothRollersToConsume() {
        runRollersToConsume();
        runAssistRollersToConsume();
    }

    public void runBothRollersToVomit() {
        runRollersToVomit();
        runAssistRollersToVomit();
    }

    public void stopAssistMotor() {
        intakeAssistMotor.set(TalonSRXControlMode.PercentOutput, 0);
    }

    public void stopBothMotors() {
        stopMotor();
        stopAssistMotor();
    }
}
