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
	/* This is the main class of the source code
	 * In order to begin the program, this program 
	 * has to be run as a Lejos NXJ application
	 * 
	 * the NXT Charting Logger tool must be launched 
	 * before hand, this can be done by running the 
	 * 'nxjchartinglogger.bat' file. 
	 * This file can be found in the directory
	 * where leJOS NXJ is installed 
	 * e.g 'C:\Program Files (x86)\LeJOS NXJ\bin'
	 * 
	 * Enter the nameof the NXT brick: Master,
	 * and set the location where data logs should 
	 * be stored
	 * 
	 * */
	
	
	// instantiate the required 
	// variables for this class 
	static Logging logger;
	public static NXTDataLogger log;
	private static RemoteNXT slave;
	private static RobotConstructor r;
	public static Obstacle obs;
	public static DataLogging test;
	private static Behavior b1;
	private static Behavior b2;
	private static Behavior b3;
	private static Behavior b4;
	
	public static void main(String[] args) throws IOException{
		
		// instantiate the Logging object
		// and call the logging() to configure
		// the structure of the real-time log
		logger = new Logging();
		log = logger.logging();

		
		// connect to remote NXT brick(slave)
		slave = ConnectToSlave.Connect();
		
		// create class which initializes required motors 
		// and sensors for this project
		r = new RobotConstructor(slave);
		
		// calibrate the gyroscope value to 0, 
		// for more accurate and readable result
		r.gyro.recalibrateOffset();
		
		// Create and start threads for obstacle detection and data logging
		obs = new Obstacle(r.sonicRight,r.sonicLeft);
		test = new DataLogging(log,r);
		
		test.start();
		obs.start();
		
		// Wait 200 miliseconds for the PC tool to start logging
		long startTime = System.currentTimeMillis();
		while((System.currentTimeMillis()-startTime)<200){
		}
		
		// initializing behaviors
		b1 = new Gait(r);
		b2 = new BackUp(r);
		b3 = new Touch(r.touch);
		b4 = new ButtonPress(r);
		
		// set gait.go variable to true, so that 
		// the robot would start walking as soon as the program is started
		Gait.go = true;
		Sound.twoBeeps();
		Behavior [] bArray = {b1, b2, b3, b4};
		
		// initialize Arbitrator class and start the thread
		Arbitrator a = new Arbitrator(bArray);
		a.start();
		
		// there is no way to interrupt the Arbitrator thread
		// therefore the program has to be shut down in one of the behaviours
	}
}
