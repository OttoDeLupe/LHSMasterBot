package org.usfirst.frc.team1289.robot.subsystems;

import org.usfirst.frc.team1289.robot.IOMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Servo;

/**
 *
 */
public class Shooter extends Subsystem {
	private SpeedController _shooterMotor = IOMap.shooterMotor;
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void Start()
	{
		_shooterMotor.set(1.0);
	}
	
	public void Stop()
	{
		_shooterMotor.stopMotor();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

