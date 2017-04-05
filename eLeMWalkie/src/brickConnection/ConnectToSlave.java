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
		
		NXTCommConnector c = Bluetooth.getConnector();
		
		String fname = "Slave";
		try {
			slave = new RemoteNXT(fname, c);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String con = "Connected to ";

		// TODO
		// this should be moved up, before 
		// RemoteNXT constructor is instantiated
		// if no devices, throw an error
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
