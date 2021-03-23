package page_202.designpatterns.isp.drone003;

public class Client003_Drone {
    public static void main(String[] args) {

        FlyRemoteControllerDevice flyRemoteControllerDevice = new FlyRemoteControllerDevice();

        FlyController flyController = new FlyControllerManager();

        flyRemoteControllerDevice.fly(flyController);

    }
}
