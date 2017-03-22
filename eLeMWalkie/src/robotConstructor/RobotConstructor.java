package robotConstructor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.GyroSensor;
import lejos.nxt.remote.RemoteMotor;
import lejos.nxt.remote.RemoteNXT;

public class RobotConstructor {

	public GyroSensor gyro;
	public UltrasonicSensor sonicRight;
	public UltrasonicSensor sonicLeft;
	public TouchSensor touch;
	public NXTRegulatedMotor rightKnee;
	public NXTRegulatedMotor rightAnkle;
	public RemoteMotor leftKnee;
	public RemoteMotor leftAnkle;
	RemoteNXT slave;
	
	public RobotConstructor(RemoteNXT s){
		slave = s;
		gyro = new GyroSensor(SensorPort.S2);
		sonicRight = new UltrasonicSensor(SensorPort.S1);
		sonicLeft = new UltrasonicSensor(SensorPort.S3);
		touch = new TouchSensor(SensorPort.S4);
		rightKnee = Motor.B;
		rightAnkle = Motor.C;
		leftKnee = slave.B;
		leftAnkle = slave.C;
	}
}
