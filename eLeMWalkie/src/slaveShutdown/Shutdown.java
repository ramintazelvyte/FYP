package slaveShutdown;
import lejos.nxt.Sound;

public class Shutdown {
	public static void main(String[] args){
		Sound.buzz();
		System.exit(0);
	}
}
