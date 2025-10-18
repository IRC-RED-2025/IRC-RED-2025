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
        double reduction = 0.66 //I don't trust my teammates

        double lBoxY = Math.signum(xbox.getLeftY()) * Math.pow(xbox.getLeftY(), 2) * reduction;
        double lBoxX = Math.signum(xbox.getLeftX()) * Math.pow(xbox.getLeftX(), 2) * reduction;
        double rBoxY = Math.signum(xbox.getRightY()) * Math.pow(xbox.getRightY(), 2) * reduction;
        double lBoxX = Math.signum(xbox.getRightX()) * Math.pow(xbox.getRightX(), 2) * reduction;

        //drivetrain.go(lBoxY, lBoxX); //Marcello idea: one motor joystick X, one motor joystick Y
        //drivetrain.go(lBoxY, rBoxY); //original one motor/one joystick

        if (6 * Math.abs(xbox.getLeftX()) <= Math.abs(xbox.getLeftY()) { //Ethan idea
            drivetrain.go(lBoxY, lBoxY);
        } else if (6 * Math.abs(xbox.getLeftY()) <= Math.abs(xbox.getLeftX()) {
            drivetrain.go(lBoxX, -lBoxX);
        } else { //My idea
            drivetrain.go((lBoxY+lBoxX)/2*, (lBoxY-lBoxX)/2);
        }
    }
}
