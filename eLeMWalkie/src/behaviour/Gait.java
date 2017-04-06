package behaviour;

import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.subsumption.Behavior;
import robotConstructor.RobotConstructor;

public class Gait implements Behavior{
	/* this is a behaviour object that 
	 * executes a forward gait
	 * and it is of the lowest priority
	 * out of all the behaviours 
	 * */
	
	public static boolean go;
	public static boolean suppressed;
	
	private NXTRegulatedMotor rk;
	private RemoteMotor lk;
	private NXTRegulatedMotor ra;
	private RemoteMotor la;
	
	public Gait(RobotConstructor r){
		rk = r.rightKnee;
		lk = r.leftKnee;
		ra = r.rightAnkle;
		la = r.leftAnkle;
		
		// setting the acceleration 
		// rate and speed of the motors
		rk.setAcceleration(30);
		lk.setAcceleration(30);
		ra.setAcceleration(30);
		la.setAcceleration(30);
		rk.setSpeed(30);
		lk.setSpeed(30);
		ra.setSpeed(30);
		la.setSpeed(30);
		
		setGoToFalse();
	}
	
	// this behaviour will take control 
	// when go variable is set to true
	public boolean takeControl(){
		if(go) return true;
		return false;
	}

	// this it the method that executes
	// when the behaviour has control over
	// the robot
	@SuppressWarnings("deprecation")
	@Override
	public void action() {
		setSuppressedToFalse();

		//stand up
		rk.rotate(30);
		lk.rotate(30);
		
		// need to lock the motor in
		// otherwise, there wont be 
		// enough power for the robot
		// to rise further
		rk.lock(100);
		rk.rotate(10);
		lk.rotate(10);
		
		// tilt to the right side 
		// to begin the forward gait
		la.rotate(-27);
		ra.rotate(30);
 
		// loop until behaviour of higher 
		// priority will take over
		while(!suppressed){
			walkForwards();
		}
		
		// tilt back to the neutral position
		la.rotate(27);
		ra.rotate(-30);
		
		setGoToFalse();
	}

	// this method is executed when a behaviour
	// of higher priority takes over, therefore,
	// this behaviour will suppress immediately
	@Override
	public void suppress() {
		setSuppressedToTrue();
	}
	
	private static void setGoToFalse(){
		go = false;
	}
	
	private static void setSuppressedToTrue(){
		suppressed = true;
	}
	
	private static void setSuppressedToFalse(){
		suppressed = false;
	}
	
	public void walkForwards(){
		lk.rotate(4);

		ra.rotate(-27);
		la.rotate(27);
			
		ra.rotate(-27);
		la.rotate(27);
			
		rk.rotate(-5);
		lk.rotate(-3);
			
		rk.rotate(10);

		la.rotate(-27);
		ra.rotate(27);
			
		la.rotate(-27);
		ra.rotate(27);
		
		rk.rotate(-5);
		lk.rotate(-3);
	}
}
