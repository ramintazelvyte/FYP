package testing;

import lejos.nxt.Button;
import lejos.nxt.Sound;
import lejos.util.NXTDataLogger;

public class TestDataLogger extends Thread{
	/* This is a thread class which runs the 
	 * runnable method.
	 * The runnable logs motor and sensor data 
	 * to the NXT Charting Logger real-time 
	 * logging tool
	 * */
	
	NXTDataLogger log;
	robotConstructor.RobotConstructor robot;
	
	public TestDataLogger(NXTDataLogger l, robotConstructor.RobotConstructor r){
		this.log = l;
		robot = r;
	}
	
	public void run(){
		long startTime ;
		int counter = 1000; // milliseconds
		
		try{
			
			// while the ENTER button is not pressed
			// on the Master NXT brick
			// keep logging data
			while(!Button.ENTER.isDown()){
				// get the start time of the loop iteration
				startTime = System.currentTimeMillis();
				log.writeLog((int)robot.gyro.getAngularVelocity());
				log.writeLog((int)robot.rightKnee.getTachoCount());
				log.writeLog((int)robot.leftKnee.getTachoCount());
				log.writeLog((int)robot.rightAnkle.getTachoCount());
				log.writeLog((int)robot.leftAnkle.getTachoCount());
				log.writeLog((int)robot.sonicRight.getDistance());
				log.writeLog((int)robot.sonicLeft.getDistance());
				log.finishLine(); // finish off the line in real-time logging tool
				
				// if the loop iteration has finished before a second is up
				// the while loop will wait until the second has passed
				if ((System.currentTimeMillis()-startTime)<counter){
					while((System.currentTimeMillis()-startTime)<counter){
					}
				}
			}
		}catch(OutOfMemoryError e){
			// this exception is thrown when the instantiated
			// NXTDataLoger object is in the cache mode
			// and the memory is exhausted
			Sound.buzz();
			Sound.buzz();
			Sound.buzz();
			e.printStackTrace();
		}
	}
}
