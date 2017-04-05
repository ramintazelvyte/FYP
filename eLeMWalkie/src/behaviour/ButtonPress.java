package behaviour;
import lejos.nxt.Button;
import lejos.nxt.Sound;
import lejos.nxt.remote.RemoteNXT;
import lejos.robotics.subsumption.Behavior;
import main.Main;
import robotConstructor.RobotConstructor;

public class ButtonPress implements Behavior {

	public static boolean suppressed;
	private RemoteNXT s;
	RobotConstructor r;
	public ButtonPress(RobotConstructor robot){
		s = robot.slave;
		r = robot;
	}
	
	public boolean takeControl(){
		if(Button.ENTER.isDown()) return true;
		return false;
	}

	public void action() {
		setSuppressedToFalse();
		Sound.beepSequence();
//		if(Gait.count == 1){
//			r.rightHip.rotate(-1500);
//			r.leftHip.rotate(-1500);
//		}else{
//			r.rightHip.rotate(-1500*Gait.count);
//			r.leftHip.rotate(-1500*Gait.count);
//		}
		Main.test.interrupt();
		Main.obs.interrupt();
		s.startProgram("Shutdown.nxj");
		System.exit(0);
	}

	public void suppress() {
		setSuppressedToTrue();
	}
	
	private static void setSuppressedToTrue(){
		suppressed = true;
	}
	
	private static void setSuppressedToFalse(){
		suppressed = false;
	}
}
