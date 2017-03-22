package logWriter;
import lejos.nxt.Sound;
import lejos.util.NXTDataLogger;
import robotConstructor.RobotConstructor;

public class DataLogging extends Thread{

	NXTDataLogger log;
	public static Boolean flag;
	RobotConstructor robot;
	
	public DataLogging(NXTDataLogger l, RobotConstructor r){
		this.log = l;
		robot = r;
	}
	
	public void run(){
		flag = false;
		long startTime ;
		int counter = 1000; // milliseconds
		
		try{
			while(!flag){
				startTime = System.currentTimeMillis();
				log.writeLog((int)robot.gyro.getAngularVelocity());
				log.writeLog(0);
				log.writeLog(0);
				log.writeLog(0);
				log.writeLog(0);
				log.writeLog((int)robot.sonicRight.getDistance());
				log.writeLog((int)robot.sonicLeft.getDistance());
				log.finishLine();
				if ((System.currentTimeMillis()-startTime)<counter){
					while((System.currentTimeMillis()-startTime)<counter){
					}
				}
			}
			
			while(flag){
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
			System.exit(0);
		}
	}
}
