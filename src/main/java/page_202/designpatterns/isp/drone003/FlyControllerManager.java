package page_202.designpatterns.isp.drone003;

public class FlyControllerManager implements FlyController {
    @Override
    public void fly() {
        System.out.println("플라이 컨트롤러 매니저에서 실행하기");
    }
}
