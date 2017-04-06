package slaveShutdown;

import lejos.nxt.Sound;

public class Shutdown {
	/* Simple class to cleanly
	 * shut down the NXT brick.
	 * This program is uploaded to the Slave NXT
	 * brick, which is started before the 
	 * main program is about to shut down
	 * */
	
	public static void main(String[] args){
		Sound.buzz();
		System.exit(0);
	}
}
