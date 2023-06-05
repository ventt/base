package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {
    TrainSensorImpl trainSensorImpl;
    TrainController controllerMock;
    TrainUser userMock;

    @Before
    public void setUp(){
        controllerMock = mock(TrainController.class);

        userMock = mock(TrainUser.class);

        trainSensorImpl = new TrainSensorImpl(controllerMock, userMock);
    }
    @Test
    public void itShouldSetAlarmSignToUserWhenTheSpeedLimitIsOverFiveHundred(){
        when(controllerMock.getReferenceSpeed()).thenReturn(50);
        trainSensorImpl.overrideSpeedLimit(501);
        verify(userMock, times(1)).setAlarmState(true);
        
    }
    @Test
    public void itShouldSetAlarmSignToUserWhenTheSpeedLimitIsLowerZero(){
        when(controllerMock.getReferenceSpeed()).thenReturn(50);
        trainSensorImpl.overrideSpeedLimit(0);
        verify(userMock, times(1)).setAlarmState(true);
    }
    @Test
    public void itShouldSetAlarmSignToUserWhenHalfOfTheSpeedLimitIsLowerThenTheReferenceSpeed(){
        when(controllerMock.getReferenceSpeed()).thenReturn(100);
        trainSensorImpl.overrideSpeedLimit(49);
        verify(userMock, times(1)).setAlarmState(true);
    }
    @Test
    public void itShouldntSetAlarmSignToUser(){
        when(controllerMock.getReferenceSpeed()).thenReturn(100);
        trainSensorImpl.overrideSpeedLimit(105);
        verify(userMock, times(1)).setAlarmState(false);
    }
}
