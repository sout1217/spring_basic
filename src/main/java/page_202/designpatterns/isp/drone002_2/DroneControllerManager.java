package page_202.designpatterns.isp.drone002_2;

public class DroneControllerManager implements DroneController {

    public DriveController driveController = new DriveRemoteControllerDevice();
    public FlyController flyController = new FlyRemoteControllerDevice();
    public SailController sailController = new SailRemoteControllerDevice();

    @Override
    public void drive() {
        driveController.drive();
    }

    @Override
    public void fly() {
        flyController.fly();
    }

    @Override
    public void sail() {
        sailController.sail();
    }

    /* ISP 해결, SRP 위반 */

}
