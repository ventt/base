package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		computeAlarmState(this.speedLimit, controller.getReferenceSpeed());
		controller.setSpeedLimit(speedLimit);
	}

	public void computeAlarmState(int speedLimit, int referenceSpeed){
		if(speedLimit < 0 || speedLimit > 500){
			user.setAlarmState(true);
		} else if( speedLimit <= referenceSpeed/2){
			user.setAlarmState(true);
		} else {
			user.setAlarmState(false);
		}
	}

}
