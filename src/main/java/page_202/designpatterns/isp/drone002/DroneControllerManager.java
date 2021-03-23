package page_202.designpatterns.isp.drone002;

public class DroneControllerManager implements DroneController {

    @Override
    public void drive() {

    }

    @Override
    public void fly() {
        System.out.println("드론 매니저를 통한 날기명령");
    }

    @Override
    public void sail() {

    }
}
