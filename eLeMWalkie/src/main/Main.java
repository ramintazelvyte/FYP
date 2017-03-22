package main;

import java.io.IOException;

import behaviour.BackUp;
import behaviour.ButtonPress;
import behaviour.Gait;
import behaviour.Touch;
import brickConnection.ConnectToSlave;
import lejos.nxt.Sound;
import lejos.nxt.remote.RemoteNXT;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.NXTDataLogger;
import logWriter.DataLogging;
import logger.Logging;
import obstacle.Obstacle;
import robotConstructor.RobotConstructor;

public class Main {

	public static Obstacle obs;
	static Logging logger;
	public static DataLogging test;
	
	public static void main(String[] args) throws IOException{
		
		logger = new Logging();
		NXTDataLogger log = logger.logging();

		
		// connect to remote NXT brick(slave)
		RemoteNXT slave = ConnectToSlave.Connect();
		
		// create class which initializes required motors 
		// and sensors for this project
		RobotConstructor r = new RobotConstructor(slave);
		
		r.gyro.recalibrateOffset();
		
		obs = new Obstacle(r.sonicRight,r.sonicLeft);
		test = new DataLogging(log,r);
		
		test.start();
		obs.start();
		
		long startTime = System.currentTimeMillis();
		while((System.currentTimeMillis()-startTime)<200){
		}
		
		// initializing behaviors
		Behavior b1 = new Gait(r);
		Behavior b2 = new BackUp(r);
		Behavior b3 = new Touch(r.touch);
		Behavior b4 = new ButtonPress(slave);
		
		// set gait.go variable to true, so that 
		// the robot would start walking as soon as the program is started
		Gait.go = true;
		DataLogging.flag = true;
		Sound.twoBeeps();
		Behavior [] bArray = {b1, b2, b3, b4};
		
		// initialize Arbitrator class and start the thread
		Arbitrator a = new Arbitrator(bArray);
		a.start();
	}
}
