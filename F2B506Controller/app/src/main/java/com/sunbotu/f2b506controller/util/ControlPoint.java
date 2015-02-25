package com.sunbotu.f2b506controller.util;

import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Argument;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.Service;
import org.cybergarage.upnp.device.NotifyListener;
import org.cybergarage.upnp.device.SearchResponseListener;
import org.cybergarage.upnp.event.EventListener;
import org.cybergarage.upnp.ssdp.SSDPPacket;

/**
 * Created by sbt on 2/19/15.
 */
public class ControlPoint extends org.cybergarage.upnp.ControlPoint implements NotifyListener,
        EventListener, SearchResponseListener {
    DeviceDiscoverTarget target;
    public ControlPoint(DeviceDiscoverTarget target) {
        this.target = target;
        addNotifyListener(this);
        addSearchResponseListener(this);
        addEventListener(this);
    }

    @Override
    public void deviceSearchResponseReceived(SSDPPacket ssdpPacket) {
        target.deviceDiscovered();
        String uuid = ssdpPacket.getUSN();
        String target = ssdpPacket.getST();
        String location = ssdpPacket.getLocation();
//        System.out.println("New device found!");
//        System.out.println(uuid);
//        System.out.println(target);
//        System.out.println(location);
    }

    @Override
    public void eventNotifyReceived(String uuid, long seq, String varName,
                                    String value) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deviceNotifyReceived(SSDPPacket ssdpPacket) {

//        System.out.println("Device message received!");
        String uuid = ssdpPacket.getUSN();
        String target = ssdpPacket.getNT();
        String subType = ssdpPacket.getNTS();
        String location = ssdpPacket.getLocation();
//        System.out.println(uuid);
//        System.out.println(target);
//        System.out.println(subType);
//        System.out.println(location);

    }

    public String printAllDevices() {
        StringBuilder buf = new StringBuilder();
        buf.append("All discovered devices:\n");
        System.out.println();
        for(Object objDevice : getDeviceList()) {
            Device device = (Device) objDevice;
            buf.append(device.getFriendlyName() + '\n');
        }
        return buf.toString();
    }

    public String printDevice(String deviceId) {
        StringBuilder buf = new StringBuilder();
        Device device = getDevice(deviceId);
        if (device == null) {
            buf.append("Device not found: " + deviceId + '\n');
            return buf.toString();
        }
        buf.append("Device: " + device.getFriendlyName() + '\n');
        Service service;
        Action action;
        Argument arg;
        buf.append("Service: \n");
        for (Object objService: device.getServiceList()) {
            service = (Service) objService;
            buf.append("\t" + service.getServiceID() + "   " + service.getServiceType() + '\n');
            buf.append("\tAction: \n");
            for (Object objAction : service.getActionList()) {
                action = (Action) objAction;
                buf.append("\t" + action.getName());
                buf.append("\t\tArgument: \n");
                for (Object objArgument : action.getArgumentList()) {
                    arg = (Argument) objArgument;
                    buf.append("\t\t\t"+ arg.getName() + " " + arg.getValue() + '\n');
                }
            }
        }
        return buf.toString();
    }
}
