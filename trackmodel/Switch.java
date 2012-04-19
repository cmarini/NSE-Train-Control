package trackmodel;

public class Switch extends Track
{
	private boolean switchState = false; //defult setting
	private String sInfo;
	
	public Switch(double iElevate, double iGrade, int spLimit, String trkID)
	{
		super (iElevate, iGrade, spLimit, trkID);
	}

	public void setSwitchState(boolean state) //set switch state
	{
		switchState = state;
		sInfo = "info: switch state set to: "+switchState;		
	}
	
	public boolean getSwitchState()	//get switch state
	{
		sInfo = "info: sent switch state";
		return switchState;
	}
	
	public String switchInfo() // return switch update
	{
		return sInfo;
	}
}
