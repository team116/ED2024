package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
   private CANSparkMax climberMotor;
   
   public Climber() {
      climberMotor = new CANSparkMax(Constants.CLIMBER_MOTOR_ID, MotorType.kBrushless);
      climberMotor.setInverted(true);
   }

   public void pullUp() {
      climberMotor.set(-1.0);
   }

   public void pullUpSlow() {
      climberMotor.set(-0.5);
   }

   public void stop() {
      climberMotor.set(0);
   }
}
