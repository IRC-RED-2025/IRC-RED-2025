package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drivetrain;

public class Drive extends Command {
    private Drivetrain drivetrain;
    private CommandXboxController xbox;
    boolean wD = false; // wasDriving
    double pD = 1; // prevDirection

    public Drive(Drivetrain drivetrain, CommandXboxController xbox) {
        this.drivetrain = drivetrain;
        this.xbox = xbox;
        addRequirements(drivetrain);
    }

    public void execute() {
        double reduction = 0.66; // I don't trust my teammates
        double yegg = Math.toRadians(90 - 15);
        double xegg = Math.toRadians(0 + 15);

        double lBoxY = Math.signum(xbox.getLeftY()) * Math.pow(xbox.getLeftY(), 2) * -1;
        double lBoxX = Math.signum(xbox.getLeftX()) * Math.pow(xbox.getLeftX(), 2) * -1;
        double rBoxY = Math.signum(xbox.getRightY()) * Math.pow(xbox.getRightY(), 2) * -1;
        double rBoxX = Math.signum(xbox.getRightX()) * Math.pow(xbox.getRightX(), 2) * -1;

        // Projecting the circular input map onto a square
        double sqCorrection = Math.sqrt(Math.pow(Math.min(Math.tan(Math.atan2(Math.abs(lBoxX), Math.abs(lBoxY))), 1), 2)
                + Math.pow(Math.min(1 / Math.tan(Math.atan2(Math.abs(lBoxX), Math.abs(lBoxY))), 1), 2));
        // See https://www.desmos.com/calculator/of98m4ayna for somewhat explanation.
        // See https://www.desmos.com/calculator/owzq8oe5vr for animation.
        double sqLBoxX = lBoxX * sqCorrection;
        double sqLBoxY = lBoxY * sqCorrection;

        // drivetrain.go(lBoxY * reduction, lBoxX * reduction); //Marcello idea: one
        // motor joystick X, one motor joystick Y
        // drivetrain.go(lBoxY * reduction, rBoxY * reduction); //original one motor/one
        // joystick

        if (wD == true && Math.abs(lBoxY) <= 0.01 && Math.abs(lBoxX) <= 0.01) {
            drivetrain.go(Math.signum(pD) * 0.5, Math.signum(pD) * 0.5);
        }

        if (Math.atan2(lBoxY, lBoxX) >= yegg || Math.atan2(lBoxY, -lBoxX) >= yegg
                || Math.atan2(-lBoxY, lBoxX) >= yegg || Math.atan2(-lBoxY, -lBoxX) >= yegg) { // Ethan idea

            drivetrain.go(sqLBoxY * reduction, sqLBoxY * reduction);
            pD = sqLBoxY;

        } else if (Math.atan2(lBoxY, lBoxX) >= xegg || Math.atan2(lBoxY, -lBoxX) >= xegg
                || Math.atan2(-lBoxY, lBoxX) >= xegg || Math.atan2(-lBoxY, -lBoxX) >= xegg) {

            drivetrain.go(sqLBoxX * reduction, -sqLBoxX * reduction);

        } else { // My idea
            drivetrain.go((sqLBoxY + sqLBoxY) / 2, (sqLBoxY - sqLBoxX) / 2);
        }

        if (Math.abs(lBoxX) >= 0.01 || Math.abs(lBoxY) >= 0.01) {
            wD = true;
        }
    }
}
