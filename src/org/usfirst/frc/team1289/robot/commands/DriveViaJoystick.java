
package org.usfirst.frc.team1289.robot.commands;

import org.usfirst.frc.team1289.robot.IOMap;
import org.usfirst.frc.team1289.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveViaJoystick extends Command {

    public DriveViaJoystick() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot._drivetrainSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot._drivetrainSubsystem.Stop();
    	IOMap.driveTrainLeftEncoder.reset();
    	IOMap.driveTrainRightEncoder.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot._drivetrainSubsystem.ArcadeDrive();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
		SmartDashboard.putNumber("LeftEncoderCount", Robot._drivetrainSubsystem.GetLeftEncoderCount());
		SmartDashboard.putNumber("RightEncoderCount", Robot._drivetrainSubsystem.GetRightEncoderCount());
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot._drivetrainSubsystem.Stop();
    	IOMap.driveTrainLeftEncoder.reset();
    	IOMap.driveTrainRightEncoder.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    }
}
