package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Elevator extends SubsystemBase {

    private TalonSRX elevator;
    private static int position = 0;
    private static final double MOVE_UPWARD = -0.20;
    private static final double BRAKE = 0.0;
    private static final double MOVE_DOWNWARD = 0.20;
    private static final double TURN_OFF = 0;

    public Elevator() {
        elevator = new TalonSRX(15);
    }

    public InstantCommand moveUp() {
        return new InstantCommand(() -> elevator.set(TalonSRXControlMode.PercentOutput, MOVE_UPWARD));//# Starting At 25% Power
    }

    public InstantCommand moveDown() {
        return new InstantCommand(() -> elevator.set(TalonSRXControlMode.PercentOutput, MOVE_DOWNWARD));//Starting At 25% Power
    }

    public InstantCommand stopElevator() {
        return new InstantCommand(() -> elevator.set(TalonSRXControlMode.PercentOutput, TURN_OFF));
    }

    public InstantCommand brakeElevator() {
        return new InstantCommand(() -> elevator.set(TalonSRXControlMode.PercentOutput, BRAKE));
    }

    public SequentialCommandGroup setToLevelZero()      //  Assign to button A
    {
        if (position == 1) {
            return new InstantCommand(() -> {
                position = 0;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_DOWNWARD);
            }).andThen(new WaitCommand(0.5)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        }
        if (position == 2) {
            return new InstantCommand(() -> {
                position = 0;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_DOWNWARD);
            }).andThen(new WaitCommand(1.0)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        } 
        if (position == 3) {
            return new InstantCommand(() -> {
                position = 0;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_DOWNWARD);
            }).andThen(new WaitCommand(1.5)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        }
        if (position == 4) {
            return new InstantCommand(() -> {
                position = 0;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_DOWNWARD);
            }).andThen(new WaitCommand(2.0)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        }

        else return new SequentialCommandGroup(new InstantCommand());
    }

    public SequentialCommandGroup setToLevelOne()       //  Assign to button B
    {
        System.out.println(position);
        if (position == 0) {
            return new InstantCommand(() -> {
                position++;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_UPWARD);
            }).andThen(new WaitCommand(3.0)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        }
        if (position == 2) {
            return new InstantCommand(() -> {
                position--;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_DOWNWARD);
            }).andThen(new WaitCommand(3.0)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        }
        if (position == 3) {
            return new InstantCommand(() -> {
                position -= 2;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_DOWNWARD);
            }).andThen(new WaitCommand(6.0)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        }
        if (position == 4) {
            return new InstantCommand(() -> {
                position -= 3;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_DOWNWARD);
            }).andThen(new WaitCommand(9.0)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        }
        else return new SequentialCommandGroup(new InstantCommand());
    }

    public SequentialCommandGroup setToLevelTwo()       //  Assign to button Y
    {
        if (position == 0) {
            return new InstantCommand(() -> {
                position += 2;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_UPWARD);
            }).andThen(new WaitCommand(6.0)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        } 
        if (position == 1) {
            return new InstantCommand(() -> {
                position++;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_UPWARD);
            }).andThen(new WaitCommand(3.0)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        } 
        if (position == 3) {
            return new InstantCommand(() -> {
                position--;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_DOWNWARD);
            }).andThen(new WaitCommand(3.0)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        } 
        if (position == 4) {
            return new InstantCommand(() -> {
                position -= 2;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_DOWNWARD);
            }).andThen(new WaitCommand(6.0)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        } 
        else return new SequentialCommandGroup(new InstantCommand());
    }
    
    public SequentialCommandGroup setToLevelThree()     //  Assign to button X
    {
        if (position == 0) {
            return new InstantCommand(() -> {
                position = 3;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_UPWARD);
            }).andThen(new WaitCommand(1.5)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        } 
        if (position == 1) {
            return new InstantCommand(() -> {
                position = 3;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_UPWARD);
            }).andThen(new WaitCommand(1.0)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        } 
        if (position == 2) {
            return new InstantCommand(() -> {
                position = 3;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_UPWARD);
            }).andThen(new WaitCommand(0.5)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        } 
        if (position == 4) {
            return new InstantCommand(() -> {
                position = 3;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_DOWNWARD);
            }).andThen(new WaitCommand(0.5)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        } 
        else return new SequentialCommandGroup(new InstantCommand());
    }
    public SequentialCommandGroup setToLevelFour()     //  Assign to button RB
    {
        if (position == 0) {
            return new InstantCommand(() -> {
                position = 4;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_UPWARD);
            }).andThen(new WaitCommand(2.0)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        } 
        if (position == 1) {
            return new InstantCommand(() -> {
                position = 4;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_UPWARD);
            }).andThen(new WaitCommand(1.5)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        } 
        if (position == 2) {
            return new InstantCommand(() -> {
                position = 4;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_UPWARD);
            }).andThen(new WaitCommand(1.0)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        } 
        if (position == 3) {
            return new InstantCommand(() -> {
                position = 4;
                elevator.set(TalonSRXControlMode.PercentOutput, MOVE_UPWARD);
            }).andThen(new WaitCommand(0.5)).andThen(() -> {
                elevator.set(TalonSRXControlMode.PercentOutput, BRAKE);
            });
        } 
        else return new SequentialCommandGroup(new InstantCommand());
    }

    

}
