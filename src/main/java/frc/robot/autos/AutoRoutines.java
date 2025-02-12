package frc.robot.autos;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.CommandSwerveDrivetrain;

public class AutoRoutines 
{
      public static final class Taxi
      {
        public static SequentialCommandGroup routine(CommandSwerveDrivetrain drivetrain, int alliance){
            return new SequentialCommandGroup
            (
            new InstantCommand(() -> drivetrain.tareEverything(), drivetrain).withTimeout(1),
            new InstantCommand(() -> drivetrain.setControl(
              Constants.drive.withVelocityX(Constants.MaxSpeed * 0.5 * Math.pow(-1, alliance))
              )),
            new WaitCommand(2),
            new InstantCommand(() -> drivetrain.setControl(
              Constants.drive.withRotationalRate(Constants.MaxAngularRate * 0.5)
              )),
            new WaitCommand(2),
            new InstantCommand(() -> drivetrain.setControl(
              Constants.drive.withVelocityX(Constants.MaxSpeed * 0.5 * Math.pow(-1, alliance))
              )),
            new WaitCommand(2),
            new InstantCommand(() -> drivetrain.applyRequest(() -> Constants.brake)));
      
        } 
      }
}
