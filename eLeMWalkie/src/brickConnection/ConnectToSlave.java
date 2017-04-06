package brickConnection;

import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTCommConnector;
import lejos.nxt.comm.NXTCommDevice;
import lejos.nxt.remote.RemoteNXT;

public class ConnectToSlave extends NXTCommDevice {
	/* This class establishes a connection between
	 * the Master and Slave NXT bricks
	 * */
	
	static RemoteNXT slave;
	
	public static RemoteNXT Connect(){
		
		// provides accesss to the singleton connection 
		// object, only one device
		// can be recognized at a time
		NXTCommConnector c = Bluetooth.getConnector();
		
		// if no devices have been recognized
		// no connection can be made,
		// therefore, the system terminates the 
		// program with an error
		if (c == null) {
			LCD.clear();
			LCD.drawString("No device in range", 0, 0);
			// terminate the program with an error
			System.exit(1);
		}
		
		String fname = "Slave";
		try {
			// try to establish the connection 
			// by passing in the name of the remote NXT brick
			// along with the singleton connection object
			// instantiated previuosly
			slave = new RemoteNXT(fname, c);
		} catch (IOException e) {
			// throw exception if connection
			// failed to establish
			e.printStackTrace();
		}

		String con = "Connected to ";

		LCD.clear();
		LCD.drawString(con, 2, 3);
		LCD.drawString(fname, 6, 4);
		
		return slave;
	}
}
