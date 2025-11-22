package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Dropper;

public class AutoDrive extends Command {
    private Drivetrain drivetrain;
    private Dropper dropper;
    private double speed1L;
    private double speed1R;
    private double dropLoc;

    public AutoDrive(Drivetrain drivetrain, Dropper dropper, double speed1L, double speed1R, double dropLoc) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
        this.dropper = dropper;
        addRequirements(dropper);
        this.speed1L = speed1L;
        this.speed1R = speed1R;
        this.dropLoc = dropLoc;
    }

    public void execute() {
        drivetrain.go(speed1L, speed1R);
        dropper.setGoal(dropLoc);
    }
}
