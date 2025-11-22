package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Dropper;

public class BasicAuto extends SequentialCommandGroup {
    public BasicAuto(Drivetrain drivetrain, Dropper dropper) {
        addCommands(
            //drive backward at half speed and move the bucket upwards for 1.5 seconds
            new AutoDrive(drivetrain, dropper, -0.5, -0.5, 0.5).withTimeout(1.5)
        );
    }
}