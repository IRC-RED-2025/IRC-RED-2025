package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Dropper extends SubsystemBase{
    private TalonFX falcon;
    private PositionVoltage positionVoltage;

    
    public Dropper() {
        falcon = new TalonFX(0);
    
         Slot0Configs config = new Slot0Configs();
         config.kP = Constants.DropperConstants.kP;
         config.kD = Constants.DropperConstants.kD;
         config.kI = Constants.DropperConstants.kI;
         falcon.getConfigurator().apply(config);
         positionVoltage = new PositionVoltage(0).withSlot(0);
    } 

    public void setGoal(double angle){
        falcon.setControl(positionVoltage.withPosition((angle + Constants.DropperConstants.startAngle) * 5 * Constants.DropperConstants.gearRatio));
    }

    @Override
    public void periodic(){
        System.out.println(falcon.getPosition().getValueAsDouble());
    }
    
}
