package Controller;

import Utils.Log;

import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

public class BTController extends Thread implements DiscoveryListener{
    UUID defaultUUID;

    /**
     * Local bluetooth device.
     */
    private LocalDevice local;

    /**
     * Agent responsible for the discovery of bluetooth devices.
     */
    private DiscoveryAgent agent;

    /**
     * Output stream used to send information to the bluetooth.
     */
    private OutputStream dout;

    /**
     * Bluetooth Connection.
     */
    private StreamConnection conn;

    /**
     * List of bluetooth devices of interest. (name starting with the defined token)
     */
    private Vector<RemoteDevice> devices;

    /**
     * Services of interest (defined in UUID) of each device.
     */
    private Vector<ServiceRecord> services;

    public BTController(){
        services = new Vector<ServiceRecord>();
    }

    @Override
    public void run() {
        findDevices();
    }

    /**
     * Find all the discoverable devices in range.
     */
    private void findDevices(){
        try{
            devices              = new Vector<RemoteDevice>();
            LocalDevice local    = LocalDevice.getLocalDevice();
            DiscoveryAgent agent = local.getDiscoveryAgent();

            agent.startInquiry(DiscoveryAgent.GIAC, this);
            debugString("Starting device discovery...");
        }catch(Exception e) {
            debugString("Error initiating discovery.");
        }
    }

    /**
     * Obtains a list of services with the UUID defined from a device.
     *
     * @param device
     *      Device to obtain the service list.
     */
    protected void findServices(RemoteDevice device){
        try {
            defaultUUID = new UUID("00001107D10211E19B2300025B00A5A5", false);
        }catch(IllegalArgumentException e){
            System.out.println(e.toString());
        }

        try{
            UUID[] uuids  = new UUID[1];
            uuids[0]      = defaultUUID;    //The UUID of the each service
            local         = LocalDevice.getLocalDevice();
            agent         = local.getDiscoveryAgent();

            agent.searchServices(null, uuids, device, this);
            debugString("Starting Service Discovery...");
        }catch(Exception e){
            debugString("Error finding services.");
        }
    }

    /**
     * Sends a message to all the devices. (using the service)
     *
     * @param str
     *      Byte array which represents a string.
     */
    public void broadcastCommand(String str) {
        for(ServiceRecord sr : services) {
            String url = sr.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);

            conn = null;

            try {
                debugString("Sending command to " + url);

                conn = (StreamConnection) Connector.open(url);
                dout = conn.openOutputStream();

//                dout.writeUTF(str);
                int[] paramVarArgs = new int[] {100, 3, 0, 0, 0};
                byte[] arrayOfByte0 = new byte[paramVarArgs.length];
                byte[] arrayOfByte1;
                int i = 0;
                while (true) {
                    arrayOfByte1 = arrayOfByte0;
                    if (i < paramVarArgs.length) {
                        arrayOfByte0[i] = (byte)paramVarArgs[i];
                        i++;
                        continue;
                    }

                    break;
                }

                byte[] arrayOfByte = frame(20564, 530, arrayOfByte1, 5);
                dout.write(arrayOfByte);

                dout.flush();
                dout.close();
                conn.close();

                debugString("Sent. Connection Closed.");

            } catch (Exception e) {
                debugString("Failed to connect to " + url);
                e.printStackTrace();
            }
        }
    }


    @Override
    public void deviceDiscovered(RemoteDevice arg0, DeviceClass arg1) {
        try {
            String name = arg0.getFriendlyName(true);

            debugString("Found device: " + name);

            if(name.startsWith("STONE")) {
                devices.add(arg0);
            }
        } catch (IOException e) {
            debugString("Failed to get remoteDevice Name.");
        }
    }

    @Override
    public void inquiryCompleted(int arg0) {
        debugString("Inquiry Completed.");

        // Start service probing
        for(RemoteDevice d :devices) {
            System.out.println("FIND DEVICE : " + d.toString());
            findServices(d);
        }
    }

    @Override
    public void serviceSearchCompleted(int arg0, int arg1) {
        debugString("Service search completed.");

        broadcastCommand(new String("Hello world!"));
    }

    @Override
    public void servicesDiscovered(int arg0, ServiceRecord[] arg1) {
        for(ServiceRecord x : arg1) {
            services.add(x);
        }
    }

    /**
     * Helper to format a debug string for output.
     *
     * @param str
     *      Debug Message
     */
    protected void debugString(String str) {
        System.out.println(String.format("%s :: %s", this.getName(), str));
    }

    static byte[] frame(int paramInt1, int paramInt2, byte[] paramArrayOfbyte, int paramInt3){
        return frame(paramInt1, paramInt2, paramArrayOfbyte, paramInt3, (byte)0);
    }

    static byte[] frame(int paramInt1, int paramInt2, byte[] paramArrayOfbyte, int paramInt3, byte paramByte){
        boolean bool;
        int i = paramArrayOfbyte.length;

        if ((paramByte & 0x1) != 0) {
            bool = true;
        } else {
            bool = false;
        }
        if (bool) {
            i = 1;
        } else {
            i = 0;
        }
        i = paramInt3 + 8 + i;
        byte[] arrayOfByte = new byte[i];
        arrayOfByte[0] = -1;
        arrayOfByte[1] = 1;
        arrayOfByte[2] = paramByte;
        arrayOfByte[3] = (byte)paramInt3;
        arrayOfByte[4] = (byte)(paramInt1 >> 8);
        arrayOfByte[5] = (byte)paramInt1;
        arrayOfByte[6] = (byte)(paramInt2 >> 8);
        arrayOfByte[7] = (byte)paramInt2;
        for (paramInt1 = 0; paramInt1 < paramInt3; paramInt1++)
            arrayOfByte[paramInt1 + 8] = paramArrayOfbyte[paramInt1];
        if (bool) {
            paramByte = 0;
            for (paramInt1 = 0; paramInt1 < i - 1; paramInt1++)
                paramByte = (byte)(arrayOfByte[paramInt1] ^ paramByte);
            arrayOfByte[i - 1] = paramByte;
        }
        return arrayOfByte;
    }
}