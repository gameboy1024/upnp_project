package f2b506.control;

import java.util.HashMap;

import javax.swing.JTextArea;

import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Argument;
import org.cybergarage.upnp.ArgumentList;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.Service;
import org.cybergarage.upnp.device.NotifyListener;
import org.cybergarage.upnp.device.SearchResponseListener;
import org.cybergarage.upnp.event.EventListener;
import org.cybergarage.upnp.ssdp.SSDPPacket;

import f2b506.ui.PresenceStatusLabel;
import f2b506.ui.TimeLabel;
import f2b506.ui.StepLabel;

public class ControlPoint extends org.cybergarage.upnp.ControlPoint implements
		NotifyListener, EventListener, SearchResponseListener {

	private Device chairDevice;
	private Device bedDevice;
	private Device bandDevice;

	private final static String CHAIR_DEVICE_TYPE = "urn:schemas-upnp-org:device:chair:1";
	private final static String CHAIR_SERVICE_TYPE = "urn:schemas-upnp-org:service:sate:1";

	private final static String BED_DEVICE_TYPE = "urn:schemas-upnp-org:device:bed:1";
	private final static String BED_SERVICE_TYPE = "urn:schemas-upnp-org:service:state:1";

	private final static String BAND_DEVICE_TYPE = "urn:schemas-upnp-org:device:bracelet:1";
	private final static String BAND_SERVICE_TYPE = "urn:schemas-upnp-org:service:power:1";

	private HashMap<String, Object> views;

	public ControlPoint(final HashMap<String, Object> views) {
		this.views = views;
		addNotifyListener(this);
		addSearchResponseListener(this);
		addEventListener(this);

		search();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
						try {
							updateDebugInfo();
							((PresenceStatusLabel) views.get("chair"))
									.setStatus(getDeviceState(chairDevice));
							((PresenceStatusLabel) views.get("bed"))
							.setStatus(getDeviceState(bedDevice));
//							((PresenceStatusLabel) views.get("band"))
//							.setStatus(getDeviceState(bandDevice));
//							((TimeLabel) views.get("walkTime"))
//							.setTime(getBandInfo("walkTime"));
//							((StepLabel) views.get("walkStep"))
//							.setStep(getBandInfo("walkStep"));
//							((TimeLabel) views.get("runTime"))
//							.setTime(getBandInfo("runTime"));
//							((StepLabel) views.get("runStep"))
//							.setStep(getBandInfo("runStep"));
						} catch (DeviceNotAvailableException e) {
							// Nothing
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

	// //////////////////////////////////////////////
	// Listener
	// //////////////////////////////////////////////

	public void deviceNotifyReceived(SSDPPacket packet) {
//		System.out.println("deviceNotifyReceived");
		if (chairDevice == null) {
			chairDevice = getDevice(CHAIR_DEVICE_TYPE);
		}
		if (bedDevice == null) {
			bedDevice = getDevice(BED_DEVICE_TYPE);
		}
		if (bandDevice == null) {
			bandDevice = getDevice(BAND_DEVICE_TYPE);
		}
		
	}

	public void deviceSearchResponseReceived(SSDPPacket packet) {
//		System.out.println("deviceSearchResponseReceived");
		if (chairDevice == null) {
			chairDevice = getDevice(CHAIR_DEVICE_TYPE);
		}
		if (bedDevice == null) {
			bedDevice = getDevice(BED_DEVICE_TYPE);
		}
		if (bandDevice == null) {
			bandDevice = getDevice(BAND_DEVICE_TYPE);
		}
	}

	public void eventNotifyReceived(String uuid, long seq, String name,
			String value) {
//		System.out.println("eventNotifyReceived");
	}

	// //////////////////////////////////////////////
	// Power
	// //////////////////////////////////////////////

	/**
	 * O means the person is not present and 1 otherwise. TODO: Error handling.
	 * 
	 * @return
	 * @throws DeviceNotAvailableException
	 */
	public boolean getDeviceState(Device device) throws DeviceNotAvailableException {
		if (device == null)
			throw new DeviceNotAvailableException();
		Action getState = device.getAction("GetState");
		if (getState.postControlAction() == false)
			throw new DeviceNotAvailableException();
		ArgumentList outArgList = getState.getOutputArgumentList();
		String powerState = outArgList.getArgument(0).getValue();
		return powerState.equals("1");
	}
	
	public String getBandInfo(String actionName) throws DeviceNotAvailableException {
		if (bandDevice == null)
			throw new DeviceNotAvailableException();
		Action getState = bandDevice.getAction(actionName);
		if (getState.postControlAction() == false)
			throw new DeviceNotAvailableException();
		ArgumentList outArgList = getState.getOutputArgumentList();
		return outArgList.getArgument(0).getValue();
	}
	
	public boolean isBandFound(){
		if(bandDevice == null){
			return false;
		}
		return true;
	}

	public void updateDebugInfo() {
		((JTextArea) views.get("debug")).setText(printAllDevices());
	}

	public String printAllDevices() {
		StringBuilder builder = new StringBuilder();
		builder.append("All discovered devices:\n");
		for (Object objDevice : getDeviceList()) {
			Device device = (Device) objDevice;
			builder.append(device.getFriendlyName() + '\n');
		}
		System.out.println(builder.toString());
		return builder.toString();
	}

	public void printDevice(String deviceId) {
		Device device = getDevice(deviceId);
		if (device == null) {
			System.out.println("Device not found: " + deviceId);
			return;
		}
		System.out.println("Device: " + device.getFriendlyName());
		Service service;
		Action action;
		Argument arg;
		System.out.println("Service: ");
		for (Object objService : device.getServiceList()) {
			service = (Service) objService;
			System.out.println("\t" + service.getServiceID() + "   "
					+ service.getServiceType());
			System.out.println("\tAction: ");
			for (Object objAction : service.getActionList()) {
				action = (Action) objAction;
				System.out.println("\t" + action.getName());
				System.out.println("\t\tArgument: ");
				for (Object objArgument : action.getArgumentList()) {
					arg = (Argument) objArgument;
					System.out.println("\t\t\t" + arg.getName() + " "
							+ arg.getValue());
				}
			}
		}

	}

	public HashMap<String, Object> getViews() {
		return views;
	}

	public void setViews(HashMap<String, Object> views) {
		this.views = views;
	}

}
