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
    UIController uiController;
    UUID defaultUUID;

    public int[] settingValues = new int[]{50, 1, 255, 255, 255};

    public LocalDevice localDevice;
    public DiscoveryAgent agent;
    public OutputStream dout;
    public StreamConnection conn;
    public Vector<RemoteDevice> devices;
    public Vector<ServiceRecord> services;

    public BTController(UIController uiController){
        services = new Vector<>();
        this.uiController = uiController;
    }

    @Override
    public void run(){
        uiController.enableControl(false);
        uiController.labelState.setText("Bluetooth 서비스에 연결하는 중...");
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
        uiController.labelState.setText("STONE에 설정을 저장하는 중...");
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

                byte[] arrayOfByte = frame(arrayOfByte1);
                dout.write(arrayOfByte);

                dout.flush();
                dout.close();
                conn.close();

                uiController.labelState.setText("STONE에 설정이 적용되었습니다!");
                uiController.enableControl(true);
                this.stop();
            }catch(Exception e){
                log.printLog(1, e.toString());
            }
        }
    }


    @Override
    public void deviceDiscovered(RemoteDevice arg0, DeviceClass arg1){
        uiController.labelState.setText("STONE을 탐색하는 중...");
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

    // frame function is copied from Pantech Official StoneManager Android Application
    static byte[] frame(byte[] paramArrayOfbyte){
        byte[] arrayOfByte = new byte[13];
        
        arrayOfByte[0] = -1;
        arrayOfByte[1] = 1;
        arrayOfByte[2] = (byte)0;
        arrayOfByte[3] = (byte)5;
        arrayOfByte[4] = (byte)(20564 >> 8);
        arrayOfByte[5] = (byte)20564;
        arrayOfByte[6] = (byte)(530 >> 8);
        arrayOfByte[7] = (byte)530;
        System.arraycopy(paramArrayOfbyte, 0, arrayOfByte, 8, 5);

        return arrayOfByte;
    }
}