package behaviour;
import lejos.nxt.Button;
import lejos.nxt.Sound;
import lejos.nxt.remote.RemoteNXT;
import lejos.robotics.subsumption.Behavior;
import main.Main;
import robotConstructor.RobotConstructor;

public class ButtonPress implements Behavior {
	/* this behaviour terminates the program
	 * before it shuts down the bricks,
	 * it will interrup the obstacle and 
	 * data logging threads
	 * */
	
	public static boolean suppressed;
	
	private RemoteNXT s;
	RobotConstructor r;
	
	public ButtonPress(RobotConstructor robot){
		s = robot.slave;
		r = robot;
	}
	
	// takes control when the ENTER button 
	// on the Master NXT brick is pressed down
	public boolean takeControl(){
		if(Button.ENTER.isDown()) return true;
		return false;
	}

	
	public void action() {
		setSuppressedToFalse();
		
		Sound.beepSequence();
		
		// interrupt alive threads
		Main.test.interrupt();
		Main.obs.interrupt();
		
		// shut down the bricks
		s.startProgram("Shutdown.nxj");
		System.exit(0);
		
		// the program is terminated 
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
