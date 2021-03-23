package page_202.designpatterns.isp.drone004;

import page_202.designpatterns.isp.drone003.FlyController;

public class Client004_Drone {
    public static void main(String[] args) {

        FlyRemoteControllerDevice flyRemoteControllerDevice = new FlyRemoteControllerDevice();


        FlyController flyController = new ControllerFacade();

        flyRemoteControllerDevice.fly(flyController);

    }
}
