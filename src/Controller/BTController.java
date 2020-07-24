package Controller;

import Utils.Log;

import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Vector;

public class BTController extends Thread implements DiscoveryListener{
    Log log = new Log();
    UUID defaultUUID;

    public int[] settingValues = new int[]{30, 4, 0, 0, 0};

    public LocalDevice localDevice;
    public DiscoveryAgent agent;
    public OutputStream dout;
    public StreamConnection conn;
    public Vector<RemoteDevice> devices;
    public Vector<ServiceRecord> services;

    public BTController(){
        services = new Vector<>();
    }

    @Override
    public void run(){
        findDevices();
    }

    private void findDevices(){
        try{
            devices = new Vector<>();
            LocalDevice local = LocalDevice.getLocalDevice();
            DiscoveryAgent agent = local.getDiscoveryAgent();

            agent.startInquiry(DiscoveryAgent.GIAC, this);
        }catch(Exception e){
            log.printLog(1, e.toString());
        }
    }

    protected void findServices(RemoteDevice device){
        try{
            defaultUUID = new UUID("00001107D10211E19B2300025B00A5A5", false);
        }catch(IllegalArgumentException e){
            System.out.println(e.toString());
        }

        try{
            UUID[] uuids = new UUID[1];
            uuids[0] = defaultUUID;    //The UUID of the each service
            localDevice = LocalDevice.getLocalDevice();
            agent = localDevice.getDiscoveryAgent();

            agent.searchServices(null, uuids, device, this);
        }catch(Exception e){
            log.printLog(1, e.toString());
        }
    }

    public void broadcastCommand(){
        for(ServiceRecord sr : services){
            String url = sr.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);

            conn = null;

            try{
                conn = (StreamConnection) Connector.open(url);
                dout = conn.openOutputStream();

                byte[] arrayOfByte0 = new byte[settingValues.length];
                byte[] arrayOfByte1;
                int i = 0;
                while(true){
                    arrayOfByte1 = arrayOfByte0;
                    if(i < settingValues.length){
                        arrayOfByte0[i] = (byte)settingValues[i];
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

                this.stop();
            }catch(Exception e){
                log.printLog(1, e.toString());
            }
        }
    }


    @Override
    public void deviceDiscovered(RemoteDevice arg0, DeviceClass arg1){
        try{
            String name = arg0.getFriendlyName(true);

            log.printLog(2, "Device Found : " + name);

            if(name.contains("STONE")){
                devices.add(arg0);
            }
        }catch(IOException e){
            log.printLog(1, e.toString());
        }
    }

    @Override
    public void inquiryCompleted(int arg0){
        for(RemoteDevice d :devices){
            System.out.println("FIND DEVICE : " + d.toString());
            findServices(d);
        }
    }

    @Override
    public void serviceSearchCompleted(int arg0, int arg1){
        broadcastCommand();
    }

    @Override
    public void servicesDiscovered(int arg0, ServiceRecord[] arg1){
        Collections.addAll(services, arg1);
    }

    // frame functions are copied from Pantech Official StoneManager Android Application
    static byte[] frame(int paramInt1, int paramInt2, byte[] paramArrayOfbyte, int paramInt3){
        return frame(paramInt1, paramInt2, paramArrayOfbyte, paramInt3, (byte)0);
    }

    static byte[] frame(int paramInt1, int paramInt2, byte[] paramArrayOfbyte, int paramInt3, byte paramByte){
        boolean bool;
        int i = paramArrayOfbyte.length;

        bool = (paramByte & 0x1) != 0;
        if(bool){
            i = 1;
        }else{
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