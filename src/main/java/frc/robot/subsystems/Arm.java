package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkLimitSwitch.Type;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkLimitSwitch;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
    private DutyCycleEncoder armEncoder;

    private CANSparkMax armRotationMotor;
    private SparkPIDController armMotorController;

    private SparkLimitSwitch arm1LimitSwitch;
    private SparkLimitSwitch arm2LimitSwitch;

    public Arm() {
        armEncoder = new DutyCycleEncoder(Constants.ARM_ENCODER_CHANNEL);
        armRotationMotor = new CANSparkMax(Constants.ARM_ROTATION_MOTOR_ID, MotorType.kBrushless);

        armRotationMotor.setIdleMode(IdleMode.kBrake);

        arm1LimitSwitch = armRotationMotor.getForwardLimitSwitch(Type.kNormallyClosed);
        arm2LimitSwitch = armRotationMotor.getReverseLimitSwitch(Type.kNormallyClosed);

        arm1LimitSwitch.enableLimitSwitch(true);
        arm2LimitSwitch.enableLimitSwitch(true);

        armMotorController = armRotationMotor.getPIDController();

        armRotationMotor.setInverted(true); //Originally  was false 

        enableLimitSwitches();

        armEncoder.setDistancePerRotation(360);

        armRotationMotor.burnFlash();
    }

    public void moveUpFast() {
        armRotationMotor.set(1.0);
    }

    public void moveUp() {
        armRotationMotor.set(0.65); // TODO: Find a good speed for this
    }

    public void moveUpSlow() {
        armRotationMotor.set(0.1);
    }

    public void moveDown() {
        armRotationMotor.set(-0.65); // TODO: Find a good speed for this
    }

    public void moveDownSlow() {
        armRotationMotor.set(-0.1);
    }

    public void moveDownFast() {
        armRotationMotor.set(-1.0);
    }

    public void stop() {
        armRotationMotor.set(0);
    }

    public void move(double power) {
        armRotationMotor.set(power);
    }

    public void enableLimitSwitches() {
        arm1LimitSwitch.enableLimitSwitch(true);
        arm2LimitSwitch.enableLimitSwitch(true);
    }

    public void disableLimitSwitches() {
        arm1LimitSwitch.enableLimitSwitch(false);
        arm2LimitSwitch.enableLimitSwitch(false);
    }

    public double getAngleDegrees() {
        return armEncoder.getDistance();
    }
}
