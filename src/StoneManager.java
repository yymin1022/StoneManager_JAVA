import Controller.BTController;
import Utils.Log;
import View.StoneManagerView;

import javax.bluetooth.*;

public class StoneManager{
    public static void main(String[] arg0) {
        new StoneManagerView();
//        Log log = new Log();
//
//        LocalDevice btDevice;
//        try {
//            btDevice = LocalDevice.getLocalDevice();
//
//            log.printLog(2, "MAC Addr is " + btDevice.getBluetoothAddress());
//        } catch (BluetoothStateException e) {
//            log.printLog(1, e.toString());
//        }
//
//        BTController btController = new BTController();
//        btController.start();
//
//        while(true);
    }
}