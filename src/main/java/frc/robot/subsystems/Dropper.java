package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Dropper extends SubsystemBase{
    VictorSP motor;

    public Dropper() {
        motor = new VictorSP(3);
    } 

    public void rotate(double speed) {
        motor.set(speed);
    }
}
