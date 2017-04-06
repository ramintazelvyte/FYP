package testing;

import java.io.IOException;
import lejos.nxt.remote.RemoteNXT;
import lejos.util.NXTDataLogger;

public class Testing {
	/* This class is used to log all the required
	 * motor and sensor data of the robot,
	 * as it is being guided through the
	 * most optimum gait
	 * */
	
	public static void main(String[] args) throws IOException{
		
		// instantiate the Logging object
		// and call the logging() to configure
		// the structure of the real-time log
		logger.Logging l = new logger.Logging();
		NXTDataLogger log = l.logging();
		
		// connect to remote NXT brick(slave)
		RemoteNXT slave = brickConnection.ConnectToSlave.Connect();
			
		// create class which initializes required motors 
		// and sensors for this project
		robotConstructor.RobotConstructor r = new robotConstructor.RobotConstructor(slave);

		// calibrate the gyroscope value to 0, 
		// for more accurate and readable result
		r.gyro.recalibrateOffset();
		
		// Create and start thread data logging
		TestDataLogger t = new TestDataLogger(log, r);
		
		t.start();
	}
}
