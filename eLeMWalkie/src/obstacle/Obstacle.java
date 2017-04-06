package obstacle;

import java.io.File;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;

public class Obstacle extends Thread{
	/* Thread class that is started in the Main
	 * The thread runable method, constantly 
	 * is scanning for approaching obstacles. 
	 * If an obstacle is detected, a sound file
	 * is played as a warning that the obstacle
	 * is within 15cm.
	 * */
	
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
		
		// loop until this thread is interrupted
		while(true){
			// get the start time of the loop iteration
			startTime = System.currentTimeMillis();
			
			// if either ultrasonic sensor detects an obstacle
			// within 15cm, a warning is played
			if(sonicR.getDistance()<15 || sonicL.getDistance()<15){
				Sound.playSample(new File("Obstacle6.wav"));
			}
			
			// if the loop iteration has finished before a second is up
			// the while loop will wait until the second has passed
			if ((System.currentTimeMillis()-startTime)<counter){
				while((System.currentTimeMillis()-startTime)<counter){
				}
			}
		}
	}
	
	// assign the passed in variable from another class
	// to the local variable
	private static void setRightSonic(UltrasonicSensor us){
		sonicR = us;
	}
	
	// assign the passed in variable from another class
	// to the local variable
	private static void setLeftSonic(UltrasonicSensor us){
		sonicL = us;
	}
}
