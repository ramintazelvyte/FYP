package behaviour;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.subsumption.Behavior;
import robotConstructor.RobotConstructor;

public class BackUp implements Behavior{

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

	@Override
	public void action() {
		setSuppressedToFalse();
		Sound.buzz();
		
		// lower down, otherwise the robot will lose the 
		// balance and fall backwards, as one of the legs 
		// slightly extends
		rk.rotate(-25);
		lk.rotate(-25);
		
		la.rotate(-20);
		ra.rotate(30);
		
		int i = 0;
		// while it is not suppressed, back up for 15 secs
		while(i < 2 && !suppressed){
			BackingUp();
			i++;
		}
		
		// get back to normal ankle position
		la.rotate(20);
		ra.rotate(-30);
		
		// lower down before standing up
		// in the gait object
		rk.rotate(6);
		lk.rotate(6);
//		
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		// generate random number(1/0)
//		// to determine which way to turn
//		int val = (int) Math.round(Math.random());
//		
//		if(val == 1){
//			TurnRight();
//		}else{
//			TurnLeft();
//		}
//		
//		rk.rotate(-35);
//		lk.rotate(-35);
		setGoToTrue();
	}

	@Override
	public void suppress() {
		setSuppressedToTrue();
	}
	
	private void BackingUp(){
			lk.rotate(-7);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			la.rotate(-25);
			ra.rotate(25);
			
			la.rotate(-25);
			ra.rotate(25);
	}
	
	@SuppressWarnings("unused")
	private void TurnRight(){
		
		ra.rotate(-30);
		la.rotate(20);
		
		int i = 0;
		// turn for 10 secs
		while(i<3){
			// extend left knee
			rk.rotate(7);
				
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			la.rotate(-25);
			ra.rotate(25);
			
			la.rotate(-25);
			ra.rotate(25);
				
			// lower
			rk.rotate(-4);
			lk.rotate(4);
				
			// reverse right knee
			lk.rotate(-7);
				
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ra.rotate(-25);
			la.rotate(25);
				
			ra.rotate(-25);
			la.rotate(25);
				
			i++;
		}
		
		// get back to normal position
		ra.rotate(30);
		la.rotate(-20);
	}
	
	@SuppressWarnings("unused")
	private void TurnLeft(){

		la.rotate(20);
		ra.rotate(-30);
		
		int i = 0;
		// turn for 10 secs
		while(i<3){
			// extend left knee
			lk.rotate(7);
				
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ra.rotate(-25);
			la.rotate(25);
				
			ra.rotate(-25);
			la.rotate(25);
				
			// lower
			lk.rotate(-5);
			rk.rotate(5);
				
			// reverse right knee
			rk.rotate(-7);
				
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			la.rotate(-25);
			ra.rotate(25);
				
			la.rotate(-25);
			ra.rotate(25);
				
			i++;
		}
		
		// get back to normal position
		la.rotate(20);
		ra.rotate(-30);
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
}
