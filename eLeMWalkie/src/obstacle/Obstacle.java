package obstacle;
import java.io.File;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;

public class Obstacle extends Thread{

	static UltrasonicSensor sonicR;
	static UltrasonicSensor sonicL;
	File sound;
	
	public Obstacle(UltrasonicSensor sr, UltrasonicSensor sl){
		setRightSonic(sr);
		setLeftSonic(sl);
	}
	@Override
	public void run(){
		
		long startTime ;
		int counter = 1000; // milliseconds
		
		while(true){
			startTime = System.currentTimeMillis();
			if(sonicR.getDistance()<15 || sonicL.getDistance()<15){
				Sound.playSample(new File("Obstacle6.wav"));
			}
			if ((System.currentTimeMillis()-startTime)<counter){
				while((System.currentTimeMillis()-startTime)<counter){
				}
			}
		}
	}
	
	private static void setRightSonic(UltrasonicSensor us){
		sonicR = us;
	}
	
	private static void setLeftSonic(UltrasonicSensor us){
		sonicL = us;
	}
}
