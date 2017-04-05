package behaviour;

import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.subsumption.Behavior;
import robotConstructor.RobotConstructor;

public class Gait2 implements Behavior{

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
	
	public Gait2(RobotConstructor r){
		rh = r.rightHip;
		lh = r.leftHip;
		rk = r.rightKnee;
		lk = r.leftKnee;
		ra = r.rightAnkle;
		la = r.leftAnkle;
		
		rh.setAcceleration(50);
		lh.setAcceleration(50);
		rk.setAcceleration(40);
		lk.setAcceleration(40);
		ra.setAcceleration(40);
		la.setAcceleration(40);
		rh.setSpeed(1500);
		lh.setSpeed(1500);
		rk.setSpeed(40);
		lk.setSpeed(40);
		ra.setSpeed(40);
		la.setSpeed(40);
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
		
		standUp();
		ankleTilt();
 
//		while(!suppressed){
////			if (rk.getTachoCount()>threshold || lk.getTachoCount()>threshold){
////				rh.rotate(1500);
////				lh.rotate(1500);
////				threshold = 90;
////				count += 1;
////			}
//			extendLeftKnee();
//
//			// slowly shift to the left
//			ankleShiftLeft();
//			ankleShiftLeft();
//				
//			lower();
//				
//			extendRightKnee();
//
//			// slowly shift to the right
//			ankleShiftRight();
//			ankleShiftRight();
//			
////			lower();
//		}
		
		// get back to neutral ankle position
		ankleNeutral();
		
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
