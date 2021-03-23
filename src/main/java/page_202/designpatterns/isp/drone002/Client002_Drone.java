package page_202.designpatterns.isp.drone002;

public class Client002_Drone {
    public static void main(String[] args) {

        FlyRemoteControllerDevice flyRemoteControllerDevice = new FlyRemoteControllerDevice();

        DroneControllerManager droneControllerManager = new DroneControllerManager();

        // 하늘 나는 장치에게 드론이 날 수 있게 명령을 날린다
        flyRemoteControllerDevice.fly(droneControllerManager);

    }
}
