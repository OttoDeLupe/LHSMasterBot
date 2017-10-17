package org.usfirst.frc.team1289.robot;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The IOMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

// Right-Left viewpoint is facing the front of the 'bot.

public class IOMap {
	// IO Ports
	private static final int _pwm_DriveTrainLeftFrontMotor = 0;
	private static final int _pwm_DriveTrainRightFrontMotor = 1;
	private static final int _pwm_DriveTrainLeftRearMotor = 2;
	private static final int _pwm_DriveTrainRightRearMotor = 3;
	private static final int _pwm_WinchMotor = 4;
	private static final int _pwm_ShooterMotor = 5;
	
	private static final int _dio_DriveTrainLeftEncoder = 0;
	private static final int _dio_DriveTrainRightEncoder = 1;
	private static final int _dio_WinchLimitSwitch = 2;
	
	public static final int _io_JoystickPort = 0;
	public static final int _io_ButtonStationPort = 0;
	
	public static final int _relay_LightBank = 0;
	
	public static SpeedController driveTrainMotorLeftFront;
	public static SpeedController driveTrainMotorRightFront;
	public static SpeedController driveTrainMotorLeftRear;
	public static SpeedController driveTrainMotorRightRear;
    public static RobotDrive driveTrainRobotDrive;
    public static Counter driveTrainLeftEncoder;
	public static Counter driveTrainRightEncoder;
	public static Relay lightBank;
	
	public static SpeedController shooterMotor;
	
    public static SpeedController winchMotor;
    public static DigitalInput winchLimitSwitch;
    
	private static final boolean _io_DriveTrainSafetyEnabled = true;
	private static final double _io_DriveTrainExpiration = 0.1;
	private static final double _io_DriveTrainSensitivity = 0.1;
	private static final double _io_DriveTrainMaxOutput = 1.0;
	private static final double _io_WheelDiameter = 6.0;
	private static final double _io_EncoderPulsesPerRotation = 6.0; //360.0;
	private static final boolean _io_EncoderLeftReverse = false;
	private static final boolean _io_EncoderRightReverse = true;
	private static double _encoderPulseDistance;
	
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
    
    public static void init()
    {
        driveTrainMotorLeftFront = new Talon(_pwm_DriveTrainLeftFrontMotor);
        LiveWindow.addActuator("DriveTrain", "MotorLeftFront", (Talon) driveTrainMotorLeftFront);
        
        driveTrainMotorRightFront = new Talon(_pwm_DriveTrainRightFrontMotor);
        LiveWindow.addActuator("DriveTrain", "MotorRightFront", (Talon) driveTrainMotorRightFront);
     
        driveTrainMotorLeftRear = new Talon(_pwm_DriveTrainLeftRearMotor);
        LiveWindow.addActuator("DriveTrain", "MotorLeftRear", (Talon) driveTrainMotorLeftRear);
        
        driveTrainMotorRightRear = new Talon(_pwm_DriveTrainRightRearMotor);
        LiveWindow.addActuator("DriveTrain", "MotorRightRear", (Talon) driveTrainMotorRightRear);
     
        driveTrainRobotDrive = new RobotDrive(driveTrainMotorLeftFront, driveTrainMotorLeftRear, 
        									driveTrainMotorRightFront, driveTrainMotorRightRear);
        
        driveTrainRobotDrive.setSafetyEnabled(_io_DriveTrainSafetyEnabled);
        driveTrainRobotDrive.setExpiration(_io_DriveTrainExpiration);
        driveTrainRobotDrive.setSensitivity(_io_DriveTrainSensitivity);
        driveTrainRobotDrive.setMaxOutput(_io_DriveTrainMaxOutput);


        driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        
        driveTrainLeftEncoder = new Counter(_dio_DriveTrainLeftEncoder);
        driveTrainRightEncoder = new Counter(_dio_DriveTrainRightEncoder);

        // Distance Per Pulse = Diameter of Wheel (8.5in * Pi)/360 pulses per revolution = .0741765in
        // NOTE right quadrature encoder turns in opposite direction from left.
        // (8.5 * PI) / 360 = 0.0741765 inches/pulse;
        _encoderPulseDistance = (_io_WheelDiameter * Math.PI) / _io_EncoderPulsesPerRotation; 

        driveTrainLeftEncoder.setDistancePerPulse(_encoderPulseDistance);
        LiveWindow.addSensor("DriveTrain", "LeftEncoder", driveTrainLeftEncoder);
        driveTrainRightEncoder.setDistancePerPulse(_encoderPulseDistance);
        LiveWindow.addSensor("DriveTrain", "RightEncoder", driveTrainRightEncoder);
        
        winchMotor = new Talon(_pwm_WinchMotor);
        winchMotor.setInverted(true);
        LiveWindow.addActuator("Winch", "Winch Motor", (Talon) winchMotor);
        
        winchLimitSwitch = new DigitalInput(_dio_WinchLimitSwitch);
        
        lightBank = new Relay(_relay_LightBank);
        
        shooterMotor = new Talon(_pwm_ShooterMotor);
    }
}
