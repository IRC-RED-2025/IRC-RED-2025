package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drivetrain;

public class Drive extends Command {
    private Drivetrain drivetrain;
    private CommandXboxController xbox;

    public Drive(Drivetrain drivetrain, CommandXboxController xbox) {
        this.drivetrain = drivetrain;
        this.xbox = xbox;
        addRequirements(drivetrain);
    }

    public void execute() {
        double reduction = 0.66; // I don't trust my teammates
        double yegg = 90 - 15;
        double xegg = 0 + 15;

        double lBoxY = Math.signum(xbox.getLeftY()) * Math.pow(xbox.getLeftY(), 2) * -1;
        double lBoxX = Math.signum(xbox.getLeftX()) * Math.pow(xbox.getLeftX(), 2) * -1;
        double rBoxY = Math.signum(xbox.getRightY()) * Math.pow(xbox.getRightY(), 2) * -1;
        double rBoxX = Math.signum(xbox.getRightX()) * Math.pow(xbox.getRightX(), 2) * -1;

        // drivetrain.go(lBoxY * reduction, lBoxX * reduction); //Marcello idea: one
        // motor joystick X, one motor joystick Y
        // drivetrain.go(lBoxY * reduction, rBoxY * reduction); //original one motor/one
        // joystick

        if (Math.abs(Math.atan2(lBoxY, lBoxX)) >= yegg) { // Ethan idea
            drivetrain.go(lBoxY * reduction, lBoxY * reduction);
        } else if (Math.abs(Math.atan2(lBoxY, lBoxX)) <= xegg) {
            drivetrain.go(lBoxX * reduction, -lBoxX * reduction);
        } else { // My idea
            drivetrain.go((lBoxY + lBoxX) / 2, (lBoxY - lBoxX) / 2);
        }
    }
}
