package page_202.designpatterns.isp.drone002_2;

public class FlyRemoteControllerDevice implements FlyController{

    @Override
    public void fly() {
        System.out.println("FlyRemoteControllerDevice fly");
    }
}
