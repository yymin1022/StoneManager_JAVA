import Utils.Log;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;

public class StoneManager{
    public static void main(String[] arg0){
        Log log = new Log();

        LocalDevice btDevice;
        try{
            btDevice = LocalDevice.getLocalDevice();

            log.printLog(2, "MAC Addr is " + btDevice.getBluetoothAddress());
        }catch(BluetoothStateException e){
            log.printLog(1, e.toString());
        }
    }
}
