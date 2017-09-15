package MainGame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CombatSystem
{
	CombatTile[][] Field;
	List<Unit> Units;
	int random;
	int currentUnit;
	int totalUnits;
	
	CombatSystem()
	{
		Field = new CombatTile[75][50];
		Units = new ArrayList<Unit>();
		random = 0;
		currentUnit = 0;
		totalUnits = 0;
		
		for(int y = 0; y < 50; y++)
		{
			for(int x = 0; x < 75;x++)
			{
				Field[x][y] = new CombatTile();
			}
		}
	}
	
	public CombatTile getCombatTile(int x, int y)
	{
		return Field[x][y];
	}
	public void startCombat(Group player, Group enemy)
	{
		Units.clear();
		for(int i = 0;i < player.getSize() ;i++)
		{
			Units.add(player.getUnit(i));
			Field[0][i].setHasUnit(true);
			Field[0][i].setUnit(player.getUnit(i));
			Field[0][i].getUnit().setUnitPoint(new Point(0,i));
		}
		for(int i = 0;i < enemy.getSize() ;i++)
		{
			Units.add(enemy.getUnit(i));
			Field[74][i].setHasUnit(true);
			Field[74][i].setUnit(enemy.getUnit(i));
			Field[74][i].getUnit().setUnitPoint(new Point(74,i));
			
		}
	    Unit temp = new Unit();  
	    for(int i=0; i < Units.size(); i++){  
	     for(int j=1; j < (Units.size()-i); j++){  
	              if(Units.get(j-1).getSpeed() > Units.get(j).getSpeed()){  
	                     temp = Units.get(j-1);  
	                     Units.set(j-1, Units.get(j));
	                     Units.set(j, temp) ;
	             }    
	     	   }  
	         }

	    totalUnits = Units.size();
	    currentUnit = 0;
	}
	public Point findCombat(Unit unit)
	{
		if(unit.getPlayerUnit())
		{
			if((unit.getUnitNewPoint().getX() >= 10 && unit.getUnitNewPoint().getX() <= 64) && (unit.getUnitNewPoint().getY() >= 10 && unit.getUnitNewPoint().getY() <= 39))
			{
				for(int x = -10; x <= 10; x++)
				{
					for(int y = -10; y <= 10; y++)
					{
						if(Field[(int)unit.getUnitNewPoint().getX() + x][(int)unit.getUnitNewPoint().getY() + y].getHasUnit() && !Field[(int)unit.getUnitNewPoint().getX() + x][(int)unit.getUnitNewPoint().getY() + y].getUnit().getPlayerUnit())
						{
							return Field[(int)unit.getUnitNewPoint().getX() + x][(int)unit.getUnitNewPoint().getY() + y].getUnit().getUnitNewPoint();
						}
					}
				}
			}
			else
			{
				int xLimitMin = -10;
				int xLimitMax = 10;
				int yLimitMin = -10;
				int yLimitMax = 10;
				if(!(unit.getUnitNewPoint().getX() >= 10 && unit.getUnitNewPoint().getX() <= 64))
				{
					if(!(unit.getUnitNewPoint().getX() >= 10))
					{
						xLimitMin = -(int)unit.getUnitNewPoint().getX();
						
					}
					else
					{
						xLimitMax = 74 - (int)unit.getUnitNewPoint().getX();	
					}
				}
				if(!(unit.getUnitNewPoint().getY() >= 10 && unit.getUnitNewPoint().getY() <= 39))
				{
					if(!(unit.getUnitNewPoint().getY() >= 10))
					{
						yLimitMin = -(int)unit.getUnitNewPoint().getY();
					}
					else
					{
						yLimitMax = 49 - (int)unit.getUnitNewPoint().getY();	
					}
				}
				for(int x = xLimitMin; x <= xLimitMax; x++)
				{
					for(int y = yLimitMin; y <= yLimitMax; y++)
					{
						if(Field[(int)unit.getUnitNewPoint().getX() + x][(int)unit.getUnitNewPoint().getY() + y].getHasUnit() && !Field[(int)unit.getUnitNewPoint().getX() + x][(int)unit.getUnitNewPoint().getY() + y].getUnit().getPlayerUnit())
						{
							return new Point((int)(unit.getUnitNewPoint().getX() + x) ,(int)(unit.getUnitNewPoint().getY() + y));

						}
					}
				}
			}
		}
		else
		{
			if((unit.getUnitNewPoint().getX() >= 10 && unit.getUnitNewPoint().getX() <= 64) && (unit.getUnitNewPoint().getY() >= 10 && unit.getUnitNewPoint().getY() <= 39))
			{
				for(int x = -10; x < 10; x++)
				{
					for(int y = -10; y < 10; y++)
					{
						if(Field[(int)unit.getUnitNewPoint().getX() + x][(int)unit.getUnitNewPoint().getY() + y].getHasUnit() && Field[(int)unit.getUnitNewPoint().getX() + x][(int)unit.getUnitNewPoint().getY() + y].getUnit().getPlayerUnit())
						{
							return Field[(int)unit.getUnitNewPoint().getX() + x][(int)unit.getUnitNewPoint().getY() + y].getUnit().getUnitNewPoint();
						}
					}
				}
			}
			else
			{
				int xLimitMin = 10;
				int xLimitMax = 10;
				int yLimitMin = 10;
				int yLimitMax = 10;
				if(!(unit.getUnitNewPoint().getX() >= 10 && unit.getUnitNewPoint().getX() <= 64))
				{
					if(!(unit.getUnitNewPoint().getX() >= 10))
					{
						xLimitMin = (int)unit.getUnitNewPoint().getX();
						
					}
					else
					{
						xLimitMax = 74 - (int)unit.getUnitNewPoint().getX();	
					}
				}
				if(!(unit.getUnitNewPoint().getY() >= 10 && unit.getUnitNewPoint().getY() <= 39))
				{
					if(!(unit.getUnitNewPoint().getY() >= 10))
					{
						yLimitMin = (int)unit.getUnitNewPoint().getY();
					}
					else
					{
						yLimitMax = 49 - (int)unit.getUnitNewPoint().getY();	
					}
				}
				for(int x = -xLimitMin; x <= xLimitMax; x++)
				{
					for(int y = -yLimitMin; y <= yLimitMax; y++)
					{
						if(Field[(int)unit.getUnitNewPoint().getX() + x][(int)unit.getUnitNewPoint().getY() + y].getHasUnit() && !Field[(int)unit.getUnitNewPoint().getX() + x][(int)unit.getUnitNewPoint().getY() + y].getUnit().getPlayerUnit())
						{
							return Field[(int)unit.getUnitNewPoint().getX() + x][(int)unit.getUnitNewPoint().getY() + y].getUnit().getUnitNewPoint();
						}
					}
				}
			}	
		}
		return new Point(0,0);
	}
	public void startCombat(Unit unit1,Unit unit2)
	{
		int attack = ThreadLocalRandom.current().nextInt(unit1.getBaseAttack(), unit1.getItemAttackChange() +1);	
		int defense = ThreadLocalRandom.current().nextInt(unit2.getBaseDefense(), unit2.getItemDefenseChange() +1);
		
		if(attack > defense)
		{
			unit2.changeHealth((int)(- (1*(attack-defense))));
			if(unit2.getHealth() <= 0)
			{
				Field[(int)unit2.getUnitNewPoint().getX()][(int)unit2.getUnitNewPoint().getY()].setHasUnit(false);
				Field[(int)unit2.getUnitNewPoint().getX()][(int)unit2.getUnitNewPoint().getY()].setHasDeadUnit(true);
				Field[(int)unit2.getUnitNewPoint().getX()][(int)unit2.getUnitNewPoint().getY()].setUnit(new Unit());
				List<Unit> Unit = new ArrayList<Unit>();
				System.out.println(Units.size());
				for(int i = 0; i < Units.size();i++)
				{
					if(!(Units.get(i) == unit2))
					{
						Unit.add(Units.get(i));
					}
				}
				Units = Unit;
				System.out.println(Units.size());
				totalUnits = Units.size();
				if(currentUnit >= totalUnits)
				{
					currentUnit = 0;
				}
			}
		}
	}
	public boolean inCombat(Unit unit)
	{
		if(unit.getPlayerUnit())
		{
			if(unit.getUnitNewPoint().getX() != 74)
			{
				if(Field[(int)unit.getUnitNewPoint().getX() + 1][(int)unit.getUnitNewPoint().getY()].getHasUnit() && !Field[(int)unit.getUnitNewPoint().getX() + 1][(int)unit.getUnitNewPoint().getY()].getUnit().getPlayerUnit())
				{
					startCombat(unit,Field[(int)unit.getUnitNewPoint().getX() + 1][(int)unit.getUnitNewPoint().getY()].getUnit());
					return true;
				}
			}
			if(unit.getUnitNewPoint().getX() != 0)
			{
				if(Field[(int)unit.getUnitNewPoint().getX() - 1][(int)unit.getUnitNewPoint().getY()].getHasUnit() && !Field[(int)unit.getUnitNewPoint().getX() - 1][(int)unit.getUnitNewPoint().getY()].getUnit().getPlayerUnit())
				{
					startCombat(unit,Field[(int)unit.getUnitNewPoint().getX() - 1][(int)unit.getUnitNewPoint().getY()].getUnit());
					return true;
				}
			}
			if(unit.getUnitNewPoint().getY() != 0)
			{
				if(Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY() - 1].getHasUnit() && !Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY() - 1].getUnit().getPlayerUnit())
				{
					startCombat(unit,Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY() - 1].getUnit());
					return true;
				}
			}
			if(unit.getUnitNewPoint().getY() != 49)
			{
				if(Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY() + 1].getHasUnit() && !Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY() + 1].getUnit().getPlayerUnit())
				{
					startCombat(unit,Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY() + 1].getUnit());
					return true;
				}
			}
		}
		else
		{
			if(unit.getUnitNewPoint().getX() != 74)
			{
				if(Field[(int)unit.getUnitNewPoint().getX() + 1][(int)unit.getUnitNewPoint().getY()].getHasUnit() && Field[(int)unit.getUnitNewPoint().getX() + 1][(int)unit.getUnitNewPoint().getY()].getUnit().getPlayerUnit())
				{
					startCombat(unit,Field[(int)unit.getUnitNewPoint().getX() + 1][(int)unit.getUnitNewPoint().getY()].getUnit());
					return true;
				}
			}
			if(unit.getUnitNewPoint().getX() != 0)
			{
				if(Field[(int)unit.getUnitNewPoint().getX() - 1][(int)unit.getUnitNewPoint().getY()].getHasUnit() && Field[(int)unit.getUnitNewPoint().getX() - 1][(int)unit.getUnitNewPoint().getY()].getUnit().getPlayerUnit())
				{
					startCombat(unit,Field[(int)unit.getUnitNewPoint().getX() - 1][(int)unit.getUnitNewPoint().getY()].getUnit());
					return true;
				}
			}
			if(unit.getUnitNewPoint().getY() != 0)
			{
				if(Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY() - 1].getHasUnit() && Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY() - 1].getUnit().getPlayerUnit())
				{
					startCombat(unit,Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY() - 1].getUnit());
					return true;
				}
			}
			if(unit.getUnitNewPoint().getY() != 49)
			{
				if(Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY() + 1].getHasUnit() && Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY() + 1].getUnit().getPlayerUnit())
				{
					startCombat(unit,Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY() + 1].getUnit());
					return true;
				}
			}
		}
		return false;
	}
	public boolean isStuck(Unit unit)
	{
		if(unit.getUnitNewPoint().getX() == 74)
		{
			if(unit.getUnitNewPoint().getY() == 0)
			{
				if((Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getHasUnit()) && (Field[(int)unit.getUnitNewPoint().getX()-1][(int)unit.getUnitNewPoint().getY()].getHasUnit()))
				{
					return true;
				}
			}
			else if(unit.getUnitNewPoint().getY() == 49)
			{
				if((Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getHasUnit()) && (Field[(int)unit.getUnitNewPoint().getX()-1][(int)unit.getUnitNewPoint().getY()].getHasUnit()))
				{
					return true;
				}
			}
			else{
				if((Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getHasUnit() && Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getHasUnit()) && (Field[(int)unit.getUnitNewPoint().getX()-1][(int)unit.getUnitNewPoint().getY()].getHasUnit()))
				{
					return true;
				}
			}
		}
		else if(unit.getUnitNewPoint().getX() == 0)
		{
			if(unit.getUnitNewPoint().getY() == 0)
			{
				if((Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getHasUnit()) && (Field[(int)unit.getUnitNewPoint().getX()+1][(int)unit.getUnitNewPoint().getY()].getHasUnit()))
				{
					return true;
				}
			}
			else if(unit.getUnitNewPoint().getY() == 49)
			{
				if((Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getHasUnit()) && (Field[(int)unit.getUnitNewPoint().getX()+1][(int)unit.getUnitNewPoint().getY()].getHasUnit()))
				{
					return true;
				}
			}
			else{
				if((Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getHasUnit() && Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getHasUnit()) && (Field[(int)unit.getUnitNewPoint().getX()+1][(int)unit.getUnitNewPoint().getY()].getHasUnit()))
				{
					return true;
				}
			}
		}
		else if(unit.getUnitNewPoint().getY() == 0)
		{
			if(unit.getUnitNewPoint().getX() == 74)
			{
				if((Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getHasUnit()) && (Field[(int)unit.getUnitNewPoint().getX()-1][(int)unit.getUnitNewPoint().getY()].getHasUnit()))
				{
					return true;
				}
			}
			else if(unit.getUnitNewPoint().getX() == 0)
			{
				if((Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getHasUnit()) && (Field[(int)unit.getUnitNewPoint().getX()+1][(int)unit.getUnitNewPoint().getY()].getHasUnit()))
				{
					return true;
				}
			}
			else
			{
				if((Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getHasUnit()) && (Field[(int)unit.getUnitNewPoint().getX()+1][(int)unit.getUnitNewPoint().getY()].getHasUnit() && Field[(int)unit.getUnitNewPoint().getX()-1][(int)unit.getUnitNewPoint().getY()].getHasUnit()))
				{
					return true;
				}
			}
		}
		else if(unit.getUnitNewPoint().getY() == 49)
		{
			if(unit.getUnitNewPoint().getX() == 74)
			{
				if((Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getHasUnit()) && (Field[(int)unit.getUnitNewPoint().getX()-1][(int)unit.getUnitNewPoint().getY()].getHasUnit()))
				{
					return true;
				}
			}
			else if(unit.getUnitNewPoint().getX() == 0)
			{
				if((Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getHasUnit()) && (Field[(int)unit.getUnitNewPoint().getX()+1][(int)unit.getUnitNewPoint().getY()].getHasUnit()))
				{
					return true;
				}
			}
			else
			{
				if((Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getHasUnit()) && (Field[(int)unit.getUnitNewPoint().getX()+1][(int)unit.getUnitNewPoint().getY()].getHasUnit() && Field[(int)unit.getUnitNewPoint().getX()-1][(int)unit.getUnitNewPoint().getY()].getHasUnit()))
				{
					return true;
				}
			}
		}
		else
		{
			if((Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getHasUnit() && Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getHasUnit()) && (Field[(int)unit.getUnitNewPoint().getX()+1][(int)unit.getUnitNewPoint().getY()].getHasUnit() && Field[(int)unit.getUnitNewPoint().getX()-1][(int)unit.getUnitNewPoint().getY()].getHasUnit()))
			{
				return true;
			}
		}
		
		return false;
	}
	public void updateUnit(int i)
	{
		Unit unit = Units.get(i);
		Point point = findCombat(unit);
		if(unit.getPlayerUnit())
		{
			if(!inCombat(unit))
			{
				if(!isStuck(unit))
				{
					if(!(point.getX() == 0 && point.getY() == 0))
					{
						if(Math.abs(point.getX() - unit.getUnitNewPoint().getX()) > Math.abs(point.getY() - unit.getUnitNewPoint().getY()) )
						{
							if(!(Field[(int)unit.getUnitNewPoint().getX()+1][(int)unit.getUnitNewPoint().getY()].getHasUnit() || Field[(int)unit.getUnitNewPoint().getX()+1][(int)unit.getUnitNewPoint().getY()].getBlocked()))
							{
								Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setHasUnit(false);
								Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setUnit(new Unit());
								Field[(int)unit.getUnitNewPoint().getX()+1][(int)unit.getUnitNewPoint().getY()].setHasUnit(true);
								Field[(int)unit.getUnitNewPoint().getX()+1][(int)unit.getUnitNewPoint().getY()].setUnit(unit);
								unit.setUnitPoint(new Point((int)unit.getUnitNewPoint().getX()+1,(int)unit.getUnitNewPoint().getY()));
								Units.set(i, unit);
							}
							else
							{
								if((!(unit.getUnitNewPoint().getY() == 0) && (Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getHasUnit() || Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getBlocked())))
								{
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setHasUnit(false);
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setUnit(new Unit());
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].setHasUnit(true);
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].setUnit(unit);
									unit.setUnitPoint(new Point((int)unit.getUnitNewPoint().getX(),(int)unit.getUnitNewPoint().getY()-1));
									Units.set(i, unit);
								}
								else
								{
									if(!((unit.getUnitNewPoint().getY() == 49) && (Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getHasUnit() || Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getBlocked())))
									{
										Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setHasUnit(false);
										Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setUnit(new Unit());
										Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].setHasUnit(true);
										Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].setUnit(unit);
										unit.setUnitPoint(new Point((int)unit.getUnitNewPoint().getX(),(int)unit.getUnitNewPoint().getY()+1));
										Units.set(i, unit);
									}
								}
							}
						}
						else
						{
							if(point.getY() - unit.getUnitNewPoint().getY() > 0)
							{
								if(!((unit.getUnitNewPoint().getY() == 49) && (Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getHasUnit() || Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getBlocked())))
								{
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setHasUnit(false);
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setUnit(new Unit());
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].setHasUnit(true);
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].setUnit(unit);
									unit.setUnitPoint(new Point((int)unit.getUnitNewPoint().getX(),(int)unit.getUnitNewPoint().getY()+1));
									Units.set(i, unit);
								}	
							}
							else
							{
								if(!((unit.getUnitNewPoint().getY() == 0) && (Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getHasUnit() || Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getBlocked())))
								{
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setHasUnit(false);
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setUnit(new Unit());
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].setHasUnit(true);
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].setUnit(unit);
									unit.setUnitPoint(new Point((int)unit.getUnitNewPoint().getX(),(int)unit.getUnitNewPoint().getY()-1));
									Units.set(i, unit);
								}
							}
						}
				}
				else
				{
				random = ThreadLocalRandom.current().nextInt(1, 3 + 1);	
				if(random == 1)
				{
					if(unit.getUnitNewPoint().getY() == 0)
					{
						updateUnit(i);
					}
					else if(Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getHasUnit() || Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getBlocked())
					{
						updateUnit(i);
					}
					else
					{
						Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setHasUnit(false);
						Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setUnit(new Unit());
						Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].setHasUnit(true);
						Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].setUnit(unit);
						unit.setUnitPoint(new Point((int)unit.getUnitNewPoint().getX(),(int)unit.getUnitNewPoint().getY()-1));
						Units.set(i, unit);
					}
				}
				else if(random == 2)
				{
					if(unit.getUnitNewPoint().getX() == 74)
					{
						updateUnit(i);
					}
					else if(Field[(int)unit.getUnitNewPoint().getX()+1][(int)unit.getUnitNewPoint().getY()].getHasUnit() || Field[(int)unit.getUnitNewPoint().getX()+1][(int)unit.getUnitNewPoint().getY()].getBlocked())
					{
						updateUnit(i);
					}
					else
					{
						Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setHasUnit(false);
						Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setUnit(new Unit());
						Field[(int)unit.getUnitNewPoint().getX()+1][(int)unit.getUnitNewPoint().getY()].setHasUnit(true);
						Field[(int)unit.getUnitNewPoint().getX()+1][(int)unit.getUnitNewPoint().getY()].setUnit(unit);
						unit.setUnitPoint(new Point((int)unit.getUnitNewPoint().getX()+1,(int)unit.getUnitNewPoint().getY()));
						Units.set(i, unit);
					}
				}
				else if(random == 3)
				{
					if(unit.getUnitNewPoint().getY() == 49)
					{
						updateUnit(i);
					}
					else if(Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getHasUnit() || Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getBlocked())
					{
						updateUnit(i);
					}
					else
					{
						Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setHasUnit(false);
						Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setUnit(new Unit());
						Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].setHasUnit(true);
						Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].setUnit(unit);
						unit.setUnitPoint(new Point((int)unit.getUnitNewPoint().getX(),(int)unit.getUnitNewPoint().getY()+1));
						Units.set(i, unit);
					}
				}
			  }
			}
		}
	}
	else
	{	
		if(!inCombat(unit))
			{
				if(!isStuck(unit))
				{
					if(!(point.getX() == 0 && point.getY() == 0))
					{
						if(Math.abs(point.getX() - unit.getUnitNewPoint().getX()) > Math.abs(point.getY() - unit.getUnitNewPoint().getY()) )
						{
							if(!(Field[(int)unit.getUnitNewPoint().getX()-1][(int)unit.getUnitNewPoint().getY()].getHasUnit() || Field[(int)unit.getUnitNewPoint().getX()-1][(int)unit.getUnitNewPoint().getY()].getBlocked()))
							{
								Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setHasUnit(false);
								Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setUnit(new Unit());
								Field[(int)unit.getUnitNewPoint().getX()-1][(int)unit.getUnitNewPoint().getY()].setHasUnit(true);
								Field[(int)unit.getUnitNewPoint().getX()-1][(int)unit.getUnitNewPoint().getY()].setUnit(unit);
								unit.setUnitPoint(new Point((int)unit.getUnitNewPoint().getX()-1,(int)unit.getUnitNewPoint().getY()));
								Units.set(i, unit);
							}
							else
							{
								if((!(unit.getUnitNewPoint().getY() == 0) && (Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getHasUnit() || Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getBlocked())))
								{
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setHasUnit(false);
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setUnit(new Unit());
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].setHasUnit(true);
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].setUnit(unit);
									unit.setUnitPoint(new Point((int)unit.getUnitNewPoint().getX(),(int)unit.getUnitNewPoint().getY()-1));
									Units.set(i, unit);
								}
								else
								{
									if(!((unit.getUnitNewPoint().getY() == 49) && (Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getHasUnit() || Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getBlocked())))
									{
										Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setHasUnit(false);
										Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setUnit(new Unit());
										Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].setHasUnit(true);
										Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].setUnit(unit);
										unit.setUnitPoint(new Point((int)unit.getUnitNewPoint().getX(),(int)unit.getUnitNewPoint().getY()+1));
										Units.set(i, unit);
									}
								}
							}
						}
						else 
						{
							random = ThreadLocalRandom.current().nextInt(1, 3 + 1);	
							if(random == 1)
							{
								if(unit.getUnitNewPoint().getY() == 0)
								{
									updateUnit(i);
								}
								else if(Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getHasUnit() || Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].getBlocked())
								{
									updateUnit(i);
								}
								else
								{
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setHasUnit(false);
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setUnit(new Unit());
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].setHasUnit(true);
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()-1].setUnit(unit);
									unit.setUnitPoint(new Point((int)unit.getUnitNewPoint().getX(),(int)unit.getUnitNewPoint().getY()-1));
									Units.set(i, unit);
								}
							}
							else if(random == 2)
							{
								if(unit.getUnitNewPoint().getX() == 0)
								{
									updateUnit(i);
								}
								else if(Field[(int)unit.getUnitNewPoint().getX()-1][(int)unit.getUnitNewPoint().getY()].getHasUnit() || Field[(int)unit.getUnitNewPoint().getX()-1][(int)unit.getUnitNewPoint().getY()].getBlocked())
								{
									updateUnit(i);
								}
								else
								{
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setHasUnit(false);
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setUnit(new Unit());
									Field[(int)unit.getUnitNewPoint().getX()-1][(int)unit.getUnitNewPoint().getY()].setHasUnit(true);
									Field[(int)unit.getUnitNewPoint().getX()-1][(int)unit.getUnitNewPoint().getY()].setUnit(unit);
									unit.setUnitPoint(new Point((int)unit.getUnitNewPoint().getX()-1,(int)unit.getUnitNewPoint().getY()));
									Units.set(i, unit);
								}
							}
							else if(random == 3)
							{
								if(unit.getUnitNewPoint().getY() == 49)
								{
									updateUnit(i);
								}
								else if(Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getHasUnit() || Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].getBlocked())
								{
									updateUnit(i);
								}
								else
								{
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setHasUnit(false);
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()].setUnit(new Unit());
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].setHasUnit(true);
									Field[(int)unit.getUnitNewPoint().getX()][(int)unit.getUnitNewPoint().getY()+1].setUnit(unit);
									unit.setUnitPoint(new Point((int)unit.getUnitNewPoint().getX(),(int)unit.getUnitNewPoint().getY()+1));
									Units.set(i, unit);
								}
							}
						}
					}
				}
			}
		}
	}
	public void updateCombat()
	{		
		if(currentUnit > totalUnits - 1)
		{
			Units.get(currentUnit - 1).setUnitPoint(Units.get(currentUnit - 1).getUnitNewPoint());
			currentUnit = 0;
		}
		else if(currentUnit == 0)
		{
			
		}
		else
		{	
			Units.get(currentUnit - 1).setUnitPoint(Units.get(currentUnit - 1).getUnitNewPoint());	
		}
			updateUnit(currentUnit);
			currentUnit++;
	}
}