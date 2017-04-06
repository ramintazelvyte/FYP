package logger;

import java.io.IOException;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.util.LogColumn;
import lejos.util.NXTDataLogger;

public class Logging {
	/* this class configures the structure of the
	 * data log file in NXT Charting Logger.
	 * A Bluetooth connection is established between the 
	 * Master NXT brick and the PC logging tool.
	 * */
	
	// a new NXTDataLogger object is instantiated
	// this object will be communicating with the
	// NXT Charting Logger.
	static NXTDataLogger logger = new NXTDataLogger();
	
	// header names and their types are defined for each item
	// to be logged. These items must be looged in the same 
	// order they are defined, otherwise if the log value datatype
	// does not match the column datatype, and error will be thrown
	static LogColumn gyro = new LogColumn("Gyro", LogColumn.DT_INTEGER);
	static LogColumn rk = new LogColumn("RKnee", LogColumn.DT_INTEGER);
	static LogColumn lk = new LogColumn("LKnee", LogColumn.DT_INTEGER);
	static LogColumn ra = new LogColumn("RAnkle", LogColumn.DT_INTEGER);
	static LogColumn la = new LogColumn("LAnkle", LogColumn.DT_INTEGER);
	static LogColumn sr = new LogColumn("RSonic", LogColumn.DT_INTEGER);
	static LogColumn sl = new LogColumn("LSonic", LogColumn.DT_INTEGER);
	
	// assign all the column headers into the LogColumn array
	static LogColumn[] columnDefs = new LogColumn[] { gyro, rk, lk, ra, la, sr, sl };
	
	 public NXTDataLogger logging() throws IOException {

		 // Print to Master's LCD as an instruction
		LCD.drawString("Setting BT Connection", 0, 1);
		LCD.drawString("To log data", 0, 2);

		// create a connection from the brick to the PC tool
		NXTConnection con = Bluetooth.waitForConnection();
		
		// start the logging session
		logger.startRealtimeLog(con);
		
		// assign column names to the data log in the 
		// NXT Charting Logger
		logger.setColumns(columnDefs);

		Sound.beep();

		return logger;
	}
}
