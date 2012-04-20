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
	
	void addTrack(Track t);
	
	void addWaysideNextLeft(Wayside w);
	
	void addWaysideNextRight(Wayside w);
	
	void addWaysidePrevLeft(Wayside w);
	
	void addWaysidePrevRight(Wayside w);
	
	ID getID();
}
