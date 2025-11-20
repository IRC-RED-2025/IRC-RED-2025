package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Dropper;

public class MoveDropper extends Command {
    private Dropper dropper;
    private double angle;

    public MoveDropper(Dropper dropper, double angle) {
        this.dropper = dropper;
        addRequirements(dropper);
    }

    public void initialize() {
        dropper.setGoal(angle);
    }

    public void end(boolean i) {
        dropper.setGoal(0);
    }
}