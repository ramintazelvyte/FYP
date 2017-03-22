package logger;
import java.io.IOException;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.util.LogColumn;
import lejos.util.NXTDataLogger;

public class Logging {

	static NXTDataLogger logger = new NXTDataLogger();
	
	static LogColumn gyro = new LogColumn("Gyro", LogColumn.DT_INTEGER);
	static LogColumn rk = new LogColumn("RKnee", LogColumn.DT_INTEGER);
	static LogColumn lk = new LogColumn("LKnee", LogColumn.DT_INTEGER);
	static LogColumn ra = new LogColumn("RAnkle", LogColumn.DT_INTEGER);
	static LogColumn la = new LogColumn("LAnkle", LogColumn.DT_INTEGER);
	static LogColumn sr = new LogColumn("RSonic", LogColumn.DT_INTEGER);
	static LogColumn sl = new LogColumn("LSonic", LogColumn.DT_INTEGER);
	
	static LogColumn[] columnDefs = new LogColumn[] { gyro, rk, lk, ra, la, sr, sl };
	
	 public NXTDataLogger logging() throws IOException {

		LCD.drawString("Setting BT Connection", 0, 1);
		LCD.drawString("To log data", 0, 2);

		NXTConnection con = Bluetooth.waitForConnection();
		logger.startRealtimeLog(con);
		logger.setColumns(columnDefs);

		Sound.beep();

		return logger;
	}
}
