
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
    /*	double x1, x2, x3, x4;
    	double y1, y2, y3, y4;
    	double m1, m2, m3, b1, b2, b3;
    	
    	// x1 is dead band limit - modify to suite needs
        x1 = SmartDashboard.getNumber("Drivetrain Deadband", 0.05);
    	y1 = 0.0;
    	
    	// first break point, and slope from deadband to the breakpoint
    	// modify to suite needs
    	x2 = SmartDashboard.getNumber("Drivetrain BP1", 0.2);
    	m1 = SmartDashboard.getNumber("Drivetrain M1", 1.0);
    	
    	// calculate intercept & y2
    	b1 = y1 - (m1 * x1);
    	y2 = (m1 * x2) + b1;
    	
    	// second breakpoint & slope from first breakpoint to second breakpoint
    	// modify to suite needs
    	x3 = SmartDashboard.getNumber("Drivetrain BP2", 0.7);
    	m2 = SmartDashboard.getNumber("Drivetrain M2", 0.5);
    	
    	// calculate intercept & y3
    	b2 = y2 - (m2 * x2);
    	y3 = (m2 * x3) + b2;
    	
    	// end point
    	x4 = y4 = 1.0;
    	
    	// calculate slope from 2nd breakpoint to endpoint & then calculate the intercept
    	m3 = (y4 - y3)/(x4 - x3);
    	b3 = y4 - (m3 * x4);
    	
    	// return scaled value based on raw value
    	if (-x1 <= rawValue && rawValue <= x1) 
    		return 0.0;
    	else if (x1 < rawValue && rawValue <= x2) 
    		return (m1 * rawValue) + b1;
    	else if (x2 < rawValue && rawValue <= x3) 
    		return (m2 * rawValue) + b2;
    	else if (x3 < rawValue && rawValue <= x4) 
    		return (m3 * rawValue) + b3;
    	else if (-x2 <= rawValue && rawValue < -x1) 
    		return -((m1 * rawValue) - b1);
    	else if (-x3 <= rawValue && rawValue < -x2) 
    		return -((m2 * rawValue) - b2);
    	else if (-x4 <= rawValue && rawValue < -x3) 
    		return -((m2 * rawValue) - b3);
    	else
    		// unknown - return zero for safety's sake
    		return 0.0;
  */
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

