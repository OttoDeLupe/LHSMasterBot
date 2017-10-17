package org.usfirst.frc.team1289.robot.subsystems;

import org.usfirst.frc.team1289.robot.IOMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 */
public class LightBank extends Subsystem {
	private static Relay _lightBank = IOMap.lightBank;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void Stop()
    {
    	_lightBank.set(Relay.Value.kOff);
    }
    
    public void SetABank()
    {
    	_lightBank.set(Relay.Value.kForward);
    }
    
    public void SetBBank()
    {
    	_lightBank.set(Relay.Value.kReverse);
    }
}

