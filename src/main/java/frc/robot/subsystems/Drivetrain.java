package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{
    VictorSP leftMotor;
    VictorSP rightMotor;
    public Drivetrain () {
        leftMotor = new VictorSP(0);
        rightMotor = new VictorSP(1);
        
        leftMotor.setInverted(false);
        rightMotor.setInverted(false);
    } 

    public void go(double lspeed, double rspeed) {
        leftMotor.set(lspeed);
        rightMotor.set(rspeed);
    }
}
