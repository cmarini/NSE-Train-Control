package trackmodel;

import global.ID;

public class Tunnel extends Track
{
	private boolean isTunnel = false;
	private String tnlInfo;
	
	// Tunnel constructor
	public Tunnel(double iElevate, double iGrade, int spLimit, double blkLen, ID trkID)
	{
		super(iElevate, iGrade, spLimit, blkLen, trkID);
	}
	
	public void setTunnelInfo(boolean iamTunnel) // set tunnel info
	{
		isTunnel = iamTunnel;
		tnlInfo = "info: tunnel parameters set";
	}
	
	public boolean getTunnel()// return Tunnel info
	{
		tnlInfo = "info: sent tunnel identity";
		return isTunnel;
        }
	
	public String getTunnelInfo() // return tunnel update
	{
		return tnlInfo;
	}
}
