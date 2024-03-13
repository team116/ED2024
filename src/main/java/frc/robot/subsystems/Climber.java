package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
   private CANSparkMax climberMotor;
   
   public Climber() {
      climberMotor = new CANSparkMax(Constants.CLIMBER_MOTOR_ID, MotorType.kBrushless);
   }

   public void pullUp() {
      climberMotor.set(0.25);
   }

   public void pullDown() {
      climberMotor.set(-0.25);
   }
}
