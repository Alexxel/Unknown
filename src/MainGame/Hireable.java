package MainGame;

import java.util.ArrayList;
import java.util.List;

public class Hireable
{
	List<Unit> Group;
	
	Hireable()
	{
		Group = new ArrayList<Unit>();
	}
	
	public Unit getUnit(int i)
	{
		return Group.get(i);
	}
	public void addUnit(Unit u)
	{
		Group.add(u);
	}
	public void removeUnit(int i)
	{
		Group.remove(i);
	}
	public List<Unit> getAll()
	{
		return Group;
	}

}