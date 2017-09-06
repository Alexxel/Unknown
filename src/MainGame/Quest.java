package MainGame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Quest 
{
	boolean Complete;
	String Name;
	String Description;
	List<QuestMarker> QuestMarkers;
	
	Quest()
	{
		Complete = false;
		Name = "";
		Description = "";
		QuestMarkers = new ArrayList<QuestMarker>();
	}
	
	Quest(String name, String description)
	{
		Complete = false;
		Name = name;
		Description = description;
		QuestMarkers = new ArrayList<QuestMarker>();
	}
	
	public String getName()
	{
		return Name;
	}
	
	public String getDescription()
	{
		return Description;
	}
	
	public QuestMarker getQuestMarker(int i)
	{
		return QuestMarkers.get(i);
	}
	public void addQuestMarker(QuestMarker qm)
	{
		QuestMarkers.add(qm);
	}
	public void removeQuestMarker(int i)
	{
		QuestMarkers.remove(i);
		if(QuestMarkers.size() == 1)
		{
			Complete = true;
		}
	}
	
	public boolean getComplete()
	{
		return Complete;
	}
	public void setComplete(boolean b)
	{
		Complete = b;
	}
	
	
}

class QuestMarker
{
	Point point;
	
	boolean touchAccept;
	boolean touchCombat;
	
	QuestMarker()
	{
		point = new Point();
		
		touchAccept = false;
		touchCombat = false;
	}
	
	public void setPoint(Point p)
	{
		point = p;
	}
	public Point getPoint()
	{
		return point;
	}
	
	public void setTouchAccept(boolean b)
	{
		touchAccept = b;
	}
	public boolean getTouchAccept()
	{
		return touchAccept;
	}
	
	public void setTouchCombat(boolean b)
	{
		touchCombat = b;
	}
	public boolean getTouchCombat()
	{
		return touchCombat;
	}
}