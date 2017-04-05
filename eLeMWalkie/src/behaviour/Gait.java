package behaviour;

import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.subsumption.Behavior;
import robotConstructor.RobotConstructor;

public class Gait implements Behavior{

	public static boolean go;
	public static boolean suppressed;
	
	private NXTRegulatedMotor rh;
	private RemoteMotor lh;
	private NXTRegulatedMotor rk;
	private RemoteMotor lk;
	private NXTRegulatedMotor ra;
	private RemoteMotor la;
	private int threshold = 55;
	public static int count = 0;
	
	public Gait(RobotConstructor r){
		rh = r.rightHip;
		lh = r.leftHip;
		rk = r.rightKnee;
		lk = r.leftKnee;
		ra = r.rightAnkle;
		la = r.leftAnkle;
		
		rh.setAcceleration(50);
		lh.setAcceleration(50);
		rk.setAcceleration(30);
		lk.setAcceleration(30);
		ra.setAcceleration(30);
		la.setAcceleration(30);
		rh.setSpeed(1500);
		lh.setSpeed(1500);
		rk.setSpeed(30);
		lk.setSpeed(30);
		ra.setSpeed(30);
		la.setSpeed(30);
		setGoToFalse();
	}
	
	public boolean takeControl(){
		if(go) return true;
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void action() {
		setSuppressedToFalse();

		rk.rotate(30);
		lk.rotate(30);
		rk.lock(100);
//		lk.regulateSpeed(true);
		rk.rotate(10);
		lk.rotate(10);
		la.rotate(-27);
		ra.rotate(30);
//		rk.lock(100);
//		lk.stop(true);
 
		while(!suppressed){
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
		
		la.rotate(27);
		ra.rotate(-30);
		
		
		
		setGoToFalse();
	}

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
	
	public void standUp(){
		// Stand up
		rk.rotate(30);
		lk.rotate(30);
	
		// The right motor needs to lock,
		// otherwise, the motors will not
		// have enough power to stand up further 
//		rk.lock(100); 
		rk.rotate(10);
		lk.rotate(10);
	}

	public void ankleTilt(){
		// tilt to the right side
		la.rotate(-27);
		ra.rotate(27);
	}
	
	public void extendRightKnee(){
		rk.rotate(10);
	}
	
	public void extendLeftKnee(){
		lk.rotate(10);
	}
	
	public void ankleShiftLeft(){
		ra.rotate(-27);
		la.rotate(27);
	}
	
//	public void lowerLeft(){
//		lk.rotate(-10);
//		rk.rotate(-14);
//		lk.rotate(-5);
//	}
	
	public void lower(){
		rk.rotate(-8);
		lk.rotate(-8);
	}
	
	public void ankleShiftRight(){
		la.rotate(-27);
		ra.rotate(27);
	}
	
	public void ankleNeutral(){
		la.rotate(27);
		ra.rotate(-27);
	}
}
