package MainGame;

public class MapInfo
{
	
	Group group;
	Inventory inventory;
	int walked;
	int xMapLocation;
	int yMapLocation;
	int xMiniMapLocation;
	int yMiniMapLocation;
	int xMapLocationOld;
	int yMapLocationOld;
	int xMiniMapLocationOld;
	int yMiniMapLocationOld;
	int speed;
	int viewRange;
	boolean moveUp;
	boolean moveDown;
	boolean moveLeft;
	boolean moveRight;
	boolean redraw;
	
	MapInfo()
	{
		group = new Group();
		inventory = new Inventory();
		walked = 0;
		xMapLocation = 0;
		yMapLocation = 0;
		xMapLocationOld = 0;
		yMapLocationOld = 0;
		xMiniMapLocation = 0;
		yMiniMapLocation = 0;
		speed = 0;
		viewRange = 0;
		moveUp = false;
		moveDown = false;
		moveLeft = false;
		moveRight = false;
		redraw = false;
	}
	
	MapInfo(Group G,Inventory I,int MX,int MY,int x, int y,int s,int v)
	{
		group = G;
		inventory = I;
		walked = 0;
		xMapLocation = MX;
		yMapLocation = MY;
		xMiniMapLocation = x;
		yMiniMapLocation = y;
		
		speed = s;
		viewRange = v;
		moveUp = false;
		moveDown = false;
		moveLeft = false;
		moveRight = false;
		redraw = false;
	}
	
	public Group getGroup()
	{
		return group;
	}
	public void setGroup(Group G)
	{
		group = G;
	}
	
	
	public Inventory getInventory()
	{
		return inventory;
	}
	public void setInventory(Inventory I)
	{
		inventory = I;
	}
	
	
	public int getXMiniMapLocation()
	{
		return xMiniMapLocation;
	}
	public void setXMiniMapLocation(int x)
	{
		xMiniMapLocation = x;
	}
	
	
	public int getXMapLocation()
	{
		return xMapLocation;
	}
	public void setXMapLocation(int x)
	{
		xMapLocation = x;
	}
	
	
	
	public int getYMiniMapLocation()
	{
		return yMiniMapLocation;
	}
	public void setYMiniMapLocation(int y)
	{
		yMiniMapLocation = y;
	}
	
	
	public int getYMapLocation()
	{
		return yMapLocation;
	}
	public void setYMapLocation(int y)
	{
		yMapLocation = y;
	}
	
	public int getXMiniMapLocationOld()
	{
		return xMiniMapLocationOld;
	}
	public void setXMiniMapLocationOld(int x)
	{
		xMiniMapLocationOld = x;
	}
	
	
	public int getXMapLocationOld()
	{
		return xMapLocationOld;
	}
	public void setXMapLocationOld(int x)
	{
		xMapLocationOld = x;
	}
	
	
	
	public int getYMiniMapLocationOld()
	{
		return yMiniMapLocationOld;
	}
	public void setYMiniMapLocationOld(int y)
	{
		yMiniMapLocationOld = y;
	}
	
	
	public int getYMapLocationOld()
	{
		return yMapLocationOld;
	}
	public void setYMapLocationOld(int y)
	{
		yMapLocationOld = y;
	}
	
	
	
	public int getSpeed()
	{
		return speed;
	}
	public void setSpeed(int s)
	{
		speed = s;
	}
	
	
	public int getViewRange()
	{
		return viewRange;
	}
	public void setViewRange(int v)
	{
		viewRange = v;
	}
	
	public void findXAndYMiniMapLocation(int xStart, int yStart)
	{
		//X Width is 1200
		//Y Height is 1000
		//X miniMap is 710 + 487
		//Y miniMap is 100 + 700
		
		if(xMapLocation > xStart && xMapLocation < xStart + 487)
		{
			xMiniMapLocation = xStart + (xMapLocation - xStart);
		}
		else
		{
			xMiniMapLocation = 0;
		}
		if(yMapLocation > yStart && yMapLocation < yStart + 700)
		{
			yMiniMapLocation = yStart + (yMapLocation - yStart);
		}
		else
		{
			yMiniMapLocation = 0;
		}
		
		
	}
	public void findXAndYMiniMapLocationOld(int xStart, int yStart)
	{
		//X Width is 1200
		//Y Height is 1000
		//X miniMap is 710 + 487
		//Y miniMap is 100 + 700
		
		if(xMapLocation > xStart && xMapLocation < xStart + 487)
		{
			xMiniMapLocationOld = xStart + (xMapLocation - xStart);
		}
		else
		{
			xMiniMapLocation = 0;
		}
		if(yMapLocation > yStart && yMapLocation < yStart + 700)
		{
			yMiniMapLocationOld = 100 + (yMapLocation - yStart);
		}
		else
		{
			yMiniMapLocation = 0;
		}
		
		
	}
	
	public void setMoveUp(boolean d)
	{
		moveUp = d;
	}
	public void setMoveDown(boolean d)
	{
		moveDown = d;
	}
	public void setMoveLeft(boolean d)
	{
		moveLeft = d;
	}
	public void setMoveRight(boolean d)
	{
		moveRight = d;
	}
	
	public void setMove()
	{
		yMapLocationOld = yMapLocation;
		xMapLocationOld = xMapLocation;
		if(moveUp)
		{
			yMapLocation--;
		}
		else if(moveDown)
		{
			yMapLocation++;
		}
		else if(moveRight)
		{
			xMapLocation++;
		}
		else if(moveLeft)
		{
			xMapLocation--;
		}
		
		System.out.println(xMapLocation +" x & y " + yMapLocation);
	}
	public void update()
	{
		walked++;
		if(walked == speed)
		{
			
			walked = 0;
			setMove();
			redraw = true;
		}

	}
	public void setRedraw(boolean b)
	{
		redraw = b;
	}
	public boolean getRedraw()
	{
		if(redraw)
		{
			redraw = false;
			return true;
		}
		else
		{
			return false;
		}
	}
	public void setWalked (int walk)
	{
		walked = walk;
	}
}