package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drivetrain;

public class Drive extends Command{
    private Drivetrain drivetrain;
    private CommandXboxController xbox;

    public Drive(Drivetrain drivetrain, CommandXboxController xbox) {
        this.drivetrain = drivetrain;
        this.xbox = xbox;
        addRequirements(drivetrain);
    }
    
    public void execute() {
        drivetrain.go(Math.signum(xbox.getLeftY()) * Math.pow(xbox.getLeftY(), 2)*0.66, Math.signum(xbox.getRightY()) * Math.pow(xbox.getRightY(), 2)*0.66);
    }
}
