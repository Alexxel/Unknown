package MainGame;

import java.util.ArrayList;
import java.util.List;

public class Hireable
{
	List<Unit> Group;
	Unit tempUnit;
	
	Hireable()
	{
		Group = new ArrayList<Unit>();
		tempUnit = new Unit();
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
	public Unit hireUnit(int i)
	{
		tempUnit = Group.get(i);
		
		Group.remove(i);
		
		return tempUnit;
	}
	public List<Unit> getAll()
	{
		return Group;
	}

}