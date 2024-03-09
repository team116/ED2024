package frc.robot.autos.primitives;

import frc.robot.subsystems.Intake;

public class RunIntakeMotorAnyDirection extends DurationCommand {
    Intake intake;

    public RunIntakeMotorAnyDirection(Intake intake, int maxTimeout) {
        super(maxTimeout);
        this.intake = intake;
    }

    public void execute() {
        
    }
    
}
