package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drivetrain;

public class Drive extends Command {
    private Drivetrain drivetrain;
    private CommandXboxController xbox;
    //private boolean wD = false; // wasDriving
    //private double pD = 1; // prevDirection
    private double bottomDegree = Math.toRadians(0 + 15);
    private double topDegree = Math.toRadians(90) - bottomDegree;

    public Drive(Drivetrain drivetrain, CommandXboxController xbox) {
        this.drivetrain = drivetrain;
        this.xbox = xbox;
        addRequirements(drivetrain);
    }

    public double steerShift(double X, double Y) {
        return (Math.atan2(Math.abs(Y), 
                           Math.abs(X)) - 
                           Math.toRadians(45)) / 
                           Math.toRadians(45 - bottomDegree) * 
                           Math.hypot(X, Y) * 
                           -1 * Math.signum(Y); //Apparently -1 is forward so *-1 accounts for that.
    }

    public void execute() {

        double leftJoyY = Math.signum(xbox.getLeftY()) * Math.pow(xbox.getLeftY(), 2);
        double leftJoyX = Math.signum(xbox.getLeftX()) * Math.pow(xbox.getLeftX(), 2);
        //double rBoxY = Math.signum(xbox.getRightY()) * Math.pow(xbox.getRightY(), 2) * -1;
        //double rBoxX = Math.signum(xbox.getRightX()) * Math.pow(xbox.getRightX(), 2) * -1;

        // drivetrain.go(leftJoyY, leftJoyX); //Marcello idea: one
        // motor joystick X, one motor joystick Y
        // drivetrain.go(leftJoyY, rBoxY); //original one motor/one
        // joystick

        // if (wD == true && Math.abs(leftJoyY) <= 0.01 && Math.abs(leftJoyX) <= 0.01) {
        //     drivetrain.go(Math.signum(pD) * 0.5, Math.signum(pD) * 0.75);
        // }
        if (Math.hypot(leftJoyX, leftJoyY) >= 0.05) {
            if (Math.atan2(Math.abs(leftJoyY), Math.abs(leftJoyX)) >= topDegree) { // Ethan idea

                drivetrain.go(leftJoyY, leftJoyY);
                
            } else if (Math.atan2(Math.abs(leftJoyY), Math.abs(leftJoyX)) <= bottomDegree) {

                drivetrain.go(leftJoyX, -leftJoyX);
                
            } else { // My idea
                if (leftJoyX >= 0 && leftJoyY >= 0) {
                    drivetrain.go(-1, steerShift(leftJoyX, leftJoyY));
                } else if (leftJoyX >= 0 && leftJoyY < 0) {
                    drivetrain.go(steerShift(leftJoyX, leftJoyY), 1);
                } else if (leftJoyX <= 0 && leftJoyY >= 0) {
                    drivetrain.go(steerShift(leftJoyX, leftJoyY), -1);
                } else if (leftJoyX <= 0 && leftJoyY < 0) {
                    drivetrain.go(1, steerShift(leftJoyX, leftJoyY));
                }
                //drivetrain.go(leftJoyY, (leftJoyY - leftJoyX) / 2);
            }
            System.out.println(steerShift(leftJoyX, leftJoyX));
            //pD = leftJoyY;
            
            // if (Math.abs(leftJoyX) >= 0.01 || Mathx.abs(leftJoyY) >= 0.01) {
            //     wD = true;
            // }
        }
    }
}
