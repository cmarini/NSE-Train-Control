package trackmodel;

public class Switch extends Track
{
	public enum SwitchState { LEFT, RIGHT; }
	
	private SwitchState switchState = SwitchState.LEFT; //defult setting
	private String sInfo;
	
	public Switch(double iElevate, double iGrade, int spLimit, String trkID, int X1, int Y1, int X2, int Y2)
	{
		super (iElevate, iGrade, spLimit, trkID, X1, Y1, X2, Y2);
	}

	public void setSwitchState(SwitchState state) //set switch state
	{
		switchState = state;
		sInfo = "info: switch state set to: " + switchState;		
	}
	
	public SwitchState getSwitchState()	//get switch state
	{
		sInfo = "info: sent switch state";
		return switchState;
	}
	
	public String switchInfo() // return switch update
	{
		return sInfo;
	}
}
