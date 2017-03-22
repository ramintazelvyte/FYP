package behaviour;

import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.subsumption.Behavior;
import robotConstructor.RobotConstructor;

public class Gait implements Behavior{

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
		
		rk.setAcceleration(50);
		lk.setAcceleration(50);
		ra.setAcceleration(50);
		la.setAcceleration(50);
		rk.setSpeed(50);
		lk.setSpeed(50);
		ra.setSpeed(50);
		la.setSpeed(50);
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
		rk.rotate(10);
		lk.rotate(10);
		la.rotate(-20);
		ra.rotate(30);
 
		while(!suppressed){
			lk.rotate(7);

			ra.rotate(-25);
			la.rotate(25);
				
			ra.rotate(-25);
			la.rotate(25);
				
			rk.rotate(-3);
			lk.rotate(-5);
				
			rk.rotate(10);

			la.rotate(-25);
			ra.rotate(25);
				
			la.rotate(-25);
			ra.rotate(25);
			
			rk.rotate(-3);
			lk.rotate(-4);
		}
		
		la.rotate(20);
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
}
