package brickConnection;


import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTCommConnector;
import lejos.nxt.comm.NXTCommDevice;
import lejos.nxt.remote.RemoteNXT;

public class ConnectToSlave extends NXTCommDevice {

	static RemoteNXT slave;
	
	public static RemoteNXT Connect(){

		String fname = "Slave";

		NXTCommConnector c = Bluetooth.getConnector();
		
		try {
			slave = new RemoteNXT(fname, c);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String con = "Connected to ";

		if (c != null) {
			LCD.clear();
			LCD.drawString(con, 2, 3);
			LCD.drawString(fname, 6, 4);
		} else {
			LCD.drawString("No devices", 2, 3);
		}
		return slave;
	}
}
