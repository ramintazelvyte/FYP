package behaviour;
import lejos.nxt.Button;
import lejos.nxt.Sound;
import lejos.nxt.remote.RemoteNXT;
import lejos.robotics.subsumption.Behavior;
import main.Main;

public class ButtonPress implements Behavior {

	public static boolean suppressed;
	private RemoteNXT s;
	
	public ButtonPress(RemoteNXT slave){
		s = slave;
	}
	
	public boolean takeControl(){
		if(Button.ENTER.isDown()) return true;
		return false;
	}

	public void action() {
		setSuppressedToFalse();
		Sound.beepSequence();
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
