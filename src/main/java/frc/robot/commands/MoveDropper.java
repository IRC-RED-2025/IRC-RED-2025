package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Dropper;

public class MoveDropper extends Command {
    private Dropper dropper;
    private double speed;

    public MoveDropper(Dropper dropper, double speed) {
        this.dropper = dropper;
        addRequirements(dropper);
    }

    public void initialize() {
        dropper.rotate(speed);
    }

    public void end(boolean i) {
        dropper.rotate(0);
    }
}