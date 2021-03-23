package page_202.designpatterns.isp.drone001;

public class Client001_Drone {

    public static void main(String[] args) {

        // 하늘 나는 제어장치
        FlyRemoteControllerDevice flyRemoteControllerDevice = new FlyRemoteControllerDevice();

        // 제어장치에게 날 수 있도록 명령한다
        flyRemoteControllerDevice.fly(new DroneController() {
            @Override
            public void fly() {

            }

            @Override
            public void drive() {

            }

            @Override
            public void sail() {

            }

        });

        /** 보다싶이 날고싶은 장치인데, 운행과, 해양 명령도 같이 있다.
         *  클라이언트 입장에서 원치 않는 종속성을 적용해서는 안됩니다
         * */
    }
}
