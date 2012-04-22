package wayside;

import java.util.*;
import global.*;
import trackmodel.*;
import trainmodel.*;

interface WaysideInterface
{
	void setAuthority(ID trackID, int auth);
	
	void setDispatchLimit(ID trackID, int speed);
	
	boolean hasTrain();
	
	boolean clearToReceiveFrom(Wayside w);
	
	void addTrack(Track t);
	
	void setWaysideNextLeft(Wayside w);
	
	void setWaysideNextRight(Wayside w);
	
	void setWaysidePrevLeft(Wayside w);
	
	void setWaysidePrevRight(Wayside w);
	
	ID getID();
}
