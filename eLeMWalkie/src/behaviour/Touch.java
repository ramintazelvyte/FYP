package behaviour;
import lejos.nxt.TouchSensor;
import lejos.robotics.subsumption.Behavior;

public class Touch implements Behavior {
	/* this class can terminate the 
	 * backwards gait before it has 
	 * finished executing. 
	 * This object is of a second 
	 * highest priority
	 * */
	
	public static boolean suppressed;
	private TouchSensor t;
	
	public Touch(TouchSensor ts){
		t = ts;
	}
	
	// takes control when the touch 
	// sensor is pressed
	public boolean takeControl(){
		return (t.isPressed());
	}

	// sets 'go' variable in Gait behaviour to 'true'
	public void action() {
		setSuppressedToFalse();
		setGoToTrue();
	}

	// this method is executed when a behaviour
	// of higher priority takes over, therefore,
	// this behaviour will suppress immediately
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
	
}
