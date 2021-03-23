package page_202.designpatterns.isp.drone005;

import page_202.designpatterns.isp.drone003.DriveController;
import page_202.designpatterns.isp.drone003.FlyController;
import page_202.designpatterns.isp.drone003.SailController;

public interface DroneController extends FlyController, DriveController, SailController {

}
