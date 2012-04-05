package Track;

public class Tunnel extends TrackModule
{
	private boolean isTunnel = false;
	private int tunnelX, tunnelY;
	private String tnlInfo;
	
	// Tunnel constructor
	public Tunnel(double iElevate, double iGrade, int spLimit, String trkID, int X1, int Y1, int X2, int Y2)
	{
		super(iElevate, iGrade, spLimit, trkID, X1, Y1, X2, Y2);
	}
	
	public void setTunnelInfo(boolean iamTunnel, int X, int Y) // set tunnel info
	{
		isTunnel = iamTunnel;
		tunnelX = X;
		tunnelY = Y;
		tnlInfo = "info: tunnel parameters set";
	}
	
	public boolean getTunnel()// return Tunnel info
	{
		tnlInfo = "info: sent tunnel identity";
		return isTunnel;
	}
	
	public int getTunnelPosX() // return Tunnel Transponder position
	{
		tnlInfo = "sent tunnel position X";
		if(isTunnel){ return tunnelX; }
		else{ return 0; }
	}
	
	public int getTunnelPosY() // return Tunnel Transponder position
	{
		tnlInfo = "info: sent tunnel position Y";
		if(isTunnel){ return tunnelY; }
		else{ return 0; }
	}
	
	public String getTunnelInfo() // return tunnel update
	{
		return tnlInfo;
	}
}