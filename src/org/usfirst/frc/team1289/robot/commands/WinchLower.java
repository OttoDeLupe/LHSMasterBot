package org.usfirst.frc.team1289.robot.commands;

import org.usfirst.frc.team1289.robot.OperatorInterface;
import org.usfirst.frc.team1289.robot.Robot;
import org.usfirst.frc.team1289.robot.commands.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WinchLower extends Command {

    public WinchLower() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot._winchSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot._winchSubsystem.Stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot._winchSubsystem.Lower();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot._winchSubsystem.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	end();
    }
}
