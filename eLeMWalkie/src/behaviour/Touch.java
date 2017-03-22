package behaviour;
import lejos.nxt.TouchSensor;
import lejos.robotics.subsumption.Behavior;

public class Touch implements Behavior {

	public static boolean suppressed;
	private TouchSensor t;
	
	public Touch(TouchSensor ts){
		t = ts;
	}
	
	public boolean takeControl(){
		return (t.isPressed());
	}

	public void action() {
		setSuppressedToFalse();
		setGoToTrue();
	}

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
