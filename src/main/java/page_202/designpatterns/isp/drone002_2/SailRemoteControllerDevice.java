package page_202.designpatterns.isp.drone002_2;

public class SailRemoteControllerDevice implements SailController{

    @Override
    public void sail() {
        System.out.println("SailRemoteControllerDevice sail");
    }
}
