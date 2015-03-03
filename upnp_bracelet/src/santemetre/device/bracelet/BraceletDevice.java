package santemetre.device.bracelet;
import java.awt.Component;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.cybergarage.upnp.*;
import org.cybergarage.upnp.device.*;
import org.cybergarage.upnp.control.*;



public class BraceletDevice extends Device implements ActionListener, QueryListener
{
	private final static String DESCRIPTION_FILE_NAME = "description/description.xml";

	private StateVariable powerVar;
	private StateVariable alarmPowerVar;
	private StateVariable alarmTimeVar;
	private Component comp;
	
	private boolean onFlagAlarmPower = false;
	private boolean onFlagPower = false;
	private String alarmTimeFlag="";
	private String synStr = "";
	
	public BraceletDevice() throws InvalidDescriptionException
	{
		super(new File(DESCRIPTION_FILE_NAME));

		//Power Services
		Action getPowerAction = getAction("GetPower");
		getPowerAction.setActionListener(this);
		
		Action setPowerAction = getAction("SetPower");
		setPowerAction.setActionListener(this);
		
		powerVar = getStateVariable("Power");
		Argument powerArg = getPowerAction.getArgument("Power");
		StateVariable powerState = powerArg.getRelatedStateVariable();
		
		
		//Alarm Services
		Action getAlarmPowerAction = getAction("GetAlarmPower");
		getAlarmPowerAction.setActionListener(this);
		
		Action setAlarmPowerAction = getAction("SetAlarmPower");
		setAlarmPowerAction.setActionListener(this);
		
		alarmPowerVar = getStateVariable("AlarmPower");
		Argument alarmPowerArg = getAlarmPowerAction.getArgument("AlarmPower");
		StateVariable alarmPowerState = alarmPowerArg.getRelatedStateVariable();
		

		Action getAlarmTimeAction = getAction("GetAlarmTime");
		getAlarmTimeAction.setActionListener(this);
		
		Action setAlarmTimeAction = getAction("SetAlarmTime");
		setAlarmTimeAction.setActionListener(this);
		
		Action syncAction = getAction("StartSync");
		syncAction.setActionListener(this);
		
		alarmTimeVar = getStateVariable("AlarmTime");
		Argument alarmTimeArg = getAlarmTimeAction.getArgument("AlarmTime");
		StateVariable alarmTimeState = alarmPowerArg.getRelatedStateVariable();
		
		ServiceList serviceList = getServiceList();
		for(int i=0; i<serviceList.size(); i++){	
			Service service = serviceList.getService(i);
			service.setQueryListener(this);
		}
		
	}

	////////////////////////////////////////////////
	//	Component
	////////////////////////////////////////////////

	public void setComponent(Component comp)
	{
		this.comp = comp;	
	}
	public Component getComponent()
	{
		return comp;
	}
	
	////////////////////////////////////////////////
	//	on/off
	////////////////////////////////////////////////
	public void onPower()
	{
		onFlagPower = true;
		powerVar.setValue("on");
	}
	public void offPower()
	{
		onFlagPower = false;
		powerVar.setValue("off");
	}
	public boolean isPowerOn()
	{
		return onFlagPower;
	}
	
	public void setPowerState(String state)
	{
		if (state == null) {
			offPower();
			return;
		}
		if (state.compareTo("1") == 0) {
			onPower();
			return;
		}
		if (state.compareTo("0") == 0) {
			offPower();
			return;
		}
	}
	public String getPowerState()
	{
		if (onFlagPower == true)
			return "1";
		return "0";
	}
	//alarm
	public void onAlarmPower()
	{
		onFlagAlarmPower = true;
		alarmPowerVar.setValue("on");
	}
	public void offAlarmPower()
	{
		onFlagAlarmPower = false;
		alarmPowerVar.setValue("off");
	}
	public boolean isAlarmPowerOn()
	{
		return onFlagAlarmPower;
	}
	public void setAlarmPowerState(String state)
	{
		if (state == null) {
			offAlarmPower();
			return;
		}
		if (state.compareTo("1") == 0) {
			onAlarmPower();
			return;
		}
		if (state.compareTo("0") == 0) {
			offAlarmPower();
			return;
		}
	}
	public String getAlarmPowerState()
	{
		if (onFlagAlarmPower == true)
			return "1";
		return "0";
	}
	public void setAlarmTime(String time)
	{
		alarmTimeFlag=time;
		alarmTimeVar.setValue(alarmTimeFlag);
	}
	public String getAlarmTime()
	{
		return alarmTimeFlag;
	}
	
	public String getResultInfo()
	{
		return synStr;
	}
	////////////////////////////////////////////////
	// ActionListener
	////////////////////////////////////////////////

	public boolean actionControlReceived(Action action)
	{
		String actionName = action.getName();
		boolean ret = false;
		if (actionName.equals("GetPower") == true) {
			String state = getPowerState();
			Argument powerArg = action.getArgument("Power");
			powerArg.setValue(state);
			ret = true;
		}
		else if (actionName.equals("SetPower") == true) {
			Argument powerArg = action.getArgument("Power");
			String state = powerArg.getValue();
			setPowerState(state);
			state = getPowerState();
			Argument resultArg = action.getArgument("ResultPower");
			resultArg.setValue(state);
			ret = true;
		}
		else if (actionName.equals("GetAlarmPower") == true) {
			String state = getAlarmPowerState();
			Argument alarmPowerArg = action.getArgument("AlarmPower");
			alarmPowerArg.setValue(state);
			ret = true;
		}
		else if (actionName.equals("SetAlarmPower") == true) {
			Argument alarmPowerArg = action.getArgument("AlarmPower");
			String state = alarmPowerArg.getValue();
			setAlarmPowerState(state);
			state = getAlarmPowerState();
			Argument resultArg = action.getArgument("ResultAlarmPower");
			resultArg.setValue(state);
			ret = true;
		}
		else if (actionName.equals("SetAlarmTime") == true) {
			Argument alarmTimeArg = action.getArgument("AlarmTime");
			String alarmTime = alarmTimeArg.getValue();
			setAlarmTime(alarmTime);
			alarmTime = getAlarmTime();
			Argument resultArg = action.getArgument("ResultAlarmTime");
			resultArg.setValue(alarmTime);
			ret = true;
		}
		
		
		else if (actionName.equals("StartSync") == true) {
			
			SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");     
			Date curDate=new Date(System.currentTimeMillis()); 
			synStr = "Sync Info:\r    Walking 1,65km \r    Running 0km \rSync Time: "+ formatter.format(curDate);
			Random rn = new Random();
			int walk= rn.nextInt(8);
			int walktime=rn.nextInt(200);
			int run= rn.nextInt(10);
			int runtime=rn.nextInt(80);
			String time= formatter.format(curDate);
			String synStrSend= walk+" "+walktime+" "+run+" "+runtime;
			synStr = "Sync Info:\r    Walking "+walk+"km \r    Running "+run+"km \rSync Time: "+ formatter.format(curDate);
			
			Argument syncArg = action.getArgument("ResultInfo");
			syncArg.setValue(synStrSend);
			ret = true;
		}
		
		comp.repaint();
		return ret;
	}

	////////////////////////////////////////////////
	// QueryListener
	////////////////////////////////////////////////

	public boolean queryControlReceived(StateVariable stateVar)
	{
		stateVar.setValue(getPowerState());
		return true;
	}

	////////////////////////////////////////////////
	// update
	////////////////////////////////////////////////

	public void update()
	{
	}			

}

