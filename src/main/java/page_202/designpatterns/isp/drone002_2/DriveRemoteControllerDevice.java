package page_202.designpatterns.isp.drone002_2;

public class DriveRemoteControllerDevice implements DriveController {

    @Override
    public void drive() {
        System.out.println("DriveRemoteControllerDevice drive");
    }
}
