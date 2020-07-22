import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;

public class StoneManager{
    public static void main(String[] arg0){
        System.out.println("Hello, World!");

        LocalDevice btDevice;
        try{
            btDevice = LocalDevice.getLocalDevice();
        }catch(BluetoothStateException e){
            System.out.println(String.format("ERROR : %s", e.toString()));
        }
    }
}
