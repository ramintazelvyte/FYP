package behaviour;

import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.subsumption.Behavior;
import robotConstructor.RobotConstructor;

public class BackUp implements Behavior{
	/* this is a behaviour object that 
	 * executes a backwards gait
	 * and it is of the second lowest priority
	 * out of all the behaviours 
	 * */
	
	public static boolean suppressed;
	
	private NXTRegulatedMotor rk;
	private RemoteMotor lk;
	private NXTRegulatedMotor ra;
	private RemoteMotor la;
	
	private UltrasonicSensor usr;
	private UltrasonicSensor usl;
		
	public BackUp(RobotConstructor r){
		usr = r.sonicRight;
		usl = r.sonicLeft;
		
		rk = r.rightKnee;
		lk = r.leftKnee;
		ra = r.rightAnkle;
		la = r.leftAnkle;
		
		// setting the acceleration 
		// rate and speed of the motors
		rk.setAcceleration(50);
		lk.setAcceleration(50);
		ra.setAcceleration(50);
		la.setAcceleration(50);
		rk.setSpeed(50);
		lk.setSpeed(50);
		ra.setSpeed(50);
		la.setSpeed(50);
	}
	
	public boolean takeControl(){
		// takes control whenever an obstacle is detected
		// by either ultrasonic sensor within 15cm distance
		if(usr.getDistance()<15 || usl.getDistance()<15) return true;
		return false;
	}

	// this it the method that executes
	// when the behaviour has control over
	// the robot
	@Override
	public void action() {
		setSuppressedToFalse();
		Sound.buzz();
		
		// lower down, otherwise the robot will lose the 
		// balance and fall backwards, as one of the legs 
		// slightly extends
		rk.rotate(-25);
		lk.rotate(-25);
		
		// tilt the right, shifting the weight
		la.rotate(-20);
		ra.rotate(30);
		
		int i = 0;
		
		// while it is not suppressed, back up 
		// for 2 backwards gait cycles
		while(i < 2 && !suppressed){
			BackingUp();
			i++;
		}
		
		// get back to normal ankle position
		la.rotate(20);
		ra.rotate(-30);
		
		// stand up slightly before the robot
		// stands up fully in the gait behaviour object
		rk.rotate(6);
		lk.rotate(6);

		setGoToTrue();
	}

	// this method is executed when a behaviour
	// of higher priority takes over, therefore,
	// this behaviour will suppress immediately
	@Override
	public void suppress() {
		setSuppressedToTrue();
	}
	
	private static void setGoToTrue(){
		Gait.go = true;
	}
	
	private static void setSuppressedToTrue(){
		suppressed = true;
	}
	
	private static void setSuppressedToFalse(){
		suppressed = false;
	}
	
	private void BackingUp(){
			lk.rotate(-7);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			ra.rotate(-25);
			la.rotate(25);
			
			ra.rotate(-25);
			la.rotate(25);
			
			rk.rotate(-7);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			la.rotate(-25);
			ra.rotate(25);
			
			la.rotate(-25);
			ra.rotate(25);
	}
}
