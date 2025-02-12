// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.autos.AutoRoutines;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.Elevator;
//#More Imports in Justin's Code if Necessary

public class RobotContainer {
    //private double MaxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
    //private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity

    /* Setting up bindings for necessary control of the swerve drive platform */
    //private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            //.withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
            //.withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
    //private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    //private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();

    private final Telemetry logger = new Telemetry(Constants.MaxSpeed);

    private final CommandXboxController joystick0 = new CommandXboxController(0); //#This Joystick Controls Direction Motion of the Robot
    //#Buttons a(break), b(shift wheels?), bumper(switch forward), forward and backward(direction), forward and backward(turning)
    private final CommandXboxController joystick1 = new CommandXboxController(1);//#This Joystick Will Control the Elevator
    //#Buttons y(up), and a(down)

    public final Encoder encoder = new Encoder(0, 1);

    public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();

    public final Elevator elevator = new Elevator();

    private int alliance;//for Auto

    public RobotContainer(int all) //DO WE NEED TO SET FIELD RELATIVE AUTO
    {
        configureBindings(all);
    }

    private void configureBindings(int all) {
        this.alliance=all;
        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.
        drivetrain.setDefaultCommand(
            // Drivetrain will execute this command periodically
            drivetrain.applyRequest(() ->
                Constants.drive.withVelocityX(-joystick0.getLeftY() * Constants.MaxSpeed * 0.5 * Math.pow(-1, alliance)) // Drive forward with negative Y (forward) #SpeedHalved
                    .withVelocityY(-joystick0.getLeftX() * Constants.MaxSpeed * 0.5 * Math.pow(-1, alliance)) // Drive left with negative X (left) #SpeedHalved
                    .withRotationalRate(-joystick0.getRightX() * Constants.MaxAngularRate * 0.5) // Drive counterclockwise with negative X (left) #SpeedHalved
            ) 
            
            //# When Command Is Executed, Request Is Applied Based on What Joystick Buttons Are Being Pressed

        );

        //joystick0.a().whileTrue(drivetrain.applyRequest(() -> brake));
        //#Constructs a Trigger around Button A that requests the DriveTrain Break
        //joystick0.b().whileTrue(drivetrain.applyRequest(() ->
        //point.withModuleDirection(new Rotation2d(-joystick0.getLeftY(), -joystick0.getLeftX()))
        //));
         //#Creates a Trigger around Button B that Points Wheels in Direction of Joystick - (Not Sure of Functionality)

        // Run SysId routines when holding back/start and X/Y.
        // Note that each routine should be run exactly once in a single log.
        joystick0.back().and(joystick0.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
        joystick0.back().and(joystick0.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
        joystick0.start().and(joystick0.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
        joystick0.start().and(joystick0.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));
        //#These Triggers Tell Whether Going Forwards or Backwards For Both Turning and Direction


        // reset the field-centric heading on left bumper press
        joystick0.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldCentric()));
        //#Left Bumper Reverses Forward and Backward)

        
        //joystick0.a().onTrue(elevator.setToLevelOne()).onFalse(elevator.stopElevator());       
        //joystick0.b().onTrue(elevator.setToLevelTwo()).onFalse(elevator.stopElevator());        
        //joystick0.y().onTrue(elevator.setToLevelThree());       
        //joystick0.x().onTrue(elevator.setToLevelFour());      
        //joystick0.rightBumper().onTrue(elevator.setToLevelZero());  
        //joystick0.x().onTrue(elevator.moveDown()).onFalse(elevator.stopElevator()); 
        //joystick0.y().onTrue(elevator.moveUp()).onFalse(elevator.stopElevator()); 

        drivetrain.registerTelemetry(logger::telemeterize);
    }

    public Command getAutonomousCommand() 
    {
        return AutoRoutines.Taxi.routine(drivetrain, alliance);
    }
}
