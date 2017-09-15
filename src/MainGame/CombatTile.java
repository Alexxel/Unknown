package MainGame;


public class CombatTile
{
	boolean blocked;
	boolean hasUnit;
	boolean hasDeadUnit;
	Unit unit;
	CombatTile()
	{
		blocked = false;
		hasUnit = false;
		hasDeadUnit = false;
		unit = new Unit();
	}
	
	public boolean getBlocked()
	{
		return blocked;
	}
	public void setBlocked(boolean b)
	{
		blocked = b;
	}
	
	public void setHasUnit(boolean b)
	{
		hasUnit = b;
	}
	public Boolean getHasUnit()
	{
		return hasUnit;
	}
	
	public void setHasDeadUnit(boolean b)
	{
		hasDeadUnit = b;
	}
	public Boolean getHasDeadUnit()
	{
		return hasDeadUnit;
	}
	
	public void setUnit(Unit u)
	{
		unit = u;
	}
	public Unit getUnit()
	{
		return unit;
	}
}