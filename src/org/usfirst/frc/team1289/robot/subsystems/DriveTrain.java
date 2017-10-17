
package org.usfirst.frc.team1289.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

import org.usfirst.frc.team1289.robot.IOMap;
import org.usfirst.frc.team1289.robot.OperatorInterface;
import org.usfirst.frc.team1289.robot.commands.*;

import edu.wpi.first.wpilibj.Counter;

/**
 *
 */
public class DriveTrain extends Subsystem 
{
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static SpeedController _leftFrontMotor = IOMap.driveTrainMotorLeftFront;
	private static SpeedController _rightFrontMotor = IOMap.driveTrainMotorRightFront;
	private static SpeedController _leftRearMotor = IOMap.driveTrainMotorLeftRear;
	private static SpeedController _rightRearMotor = IOMap.driveTrainMotorRightRear;
	private static Counter _leftEncoder = IOMap.driveTrainLeftEncoder;
	private static Counter _rightEncoder = IOMap.driveTrainRightEncoder;
	private static RobotDrive _robotDrive = IOMap.driveTrainRobotDrive;

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new DriveViaJoystick());
    	//setDefaultCommand(new DriveViaEncoder());
    }
    
    // Sets the motor speed of all motors to the desired setting
    // no rotation value, so no turning. This moves fwd/bkwd only
    public void Move(double speed, double rotate)
    {
    	boolean squareInputs = false; 
    	_robotDrive.arcadeDrive(speed, rotate, squareInputs);
    }

    // Scale the raw value into a piecewise linear equation
    private double ScaleValue(double rawValue)
    {
    	double deadBand = SmartDashboard.getNumber("Drivetrain Deadband", 0.05);
    	if (-deadBand < rawValue && rawValue < deadBand)
    		return 0.0;
    	else
    		return (rawValue < 0.0) ? Math.pow(rawValue + deadBand, 3) : Math.pow(rawValue - deadBand, 3);
    
    }
    
    public void ArcadeDrive()
    {
    	double moveValue = -OperatorInterface.joyStick.getY();
    	double rotateValue = -OperatorInterface.joyStick.getX();
    	
    	SmartDashboard.putNumber("stickRawMoveValue", moveValue);
    	SmartDashboard.putNumber("stickRawRotateValue", rotateValue);
    	
    	moveValue = ScaleValue(moveValue);
    	rotateValue = ScaleValue(rotateValue);
    	
    	SmartDashboard.putNumber("stickScaledMoveValue", moveValue);
    	SmartDashboard.putNumber("stickScaledRotateValue", rotateValue);
    	
    	_robotDrive.arcadeDrive(moveValue, rotateValue);
    }
    
    public void Stop()
    {
    	_robotDrive.stopMotor();
    }
    
    public double GetLeftEncoderDistance()
    {
    	return _leftEncoder.getDistance();
    }
    
    public double GetRightEncoderDistance()
    {
    	return _rightEncoder.getDistance();
    }
    
    public int GetRightEncoderCount()
    {
    	return  _rightEncoder.get();
    }
    
    public int GetLeftEncoderCount()
    {
    	return _leftEncoder.get();
    }
    
   public void ResetEncoders()
   {
	   _leftEncoder.reset();
	   _rightEncoder.reset();
   }
}

