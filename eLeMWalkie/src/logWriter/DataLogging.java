package logWriter;
import lejos.nxt.Sound;
import lejos.util.NXTDataLogger;
import robotConstructor.RobotConstructor;

public class DataLogging extends Thread{

	NXTDataLogger log;
	RobotConstructor robot;
	
	public DataLogging(NXTDataLogger l, RobotConstructor r){
		this.log = l;
		robot = r;
	}
	
	public void run(){
		long startTime ;
		int counter = 1000; // milliseconds
		
		try{

			while(true){
				startTime = System.currentTimeMillis();
				log.writeLog((int)robot.gyro.getAngularVelocity());
				log.writeLog((int)robot.rightKnee.getTachoCount());
				log.writeLog((int)robot.leftKnee.getTachoCount());
				log.writeLog((int)robot.rightAnkle.getTachoCount());
				log.writeLog((int)robot.leftAnkle.getTachoCount());
				log.writeLog((int)robot.sonicRight.getDistance());
				log.writeLog((int)robot.sonicLeft.getDistance());
				log.finishLine();
				if ((System.currentTimeMillis()-startTime)<counter){
					while((System.currentTimeMillis()-startTime)<counter){
					}
				}
			}
		}catch(OutOfMemoryError e){
			Sound.buzz();
			Sound.buzz();
			Sound.buzz();
			e.printStackTrace();
		}
	}
}
