package page_202.designpatterns.isp.drone005;

import page_202.designpatterns.isp.drone003.FlyController;
import page_202.designpatterns.isp.drone003.FlyControllerManager;

public class Client005_Drone {
    public static void main(String[] args) {

        FlyRemoteControllerDevice flyRemoteControllerDevice = new FlyRemoteControllerDevice();

        FlyController flyController = new FlyControllerManager();
        
        flyRemoteControllerDevice.fly(flyController);
    }
}
