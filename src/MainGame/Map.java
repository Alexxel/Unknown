package MainGame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class Map extends TimerTask
{
	List<MapInfo> mapInfo;
	Timer timer;
	int xStart;
	int yStart;
	int xStartOld;
	int yStartOld;
	List<Point> hotSpotList;
	List<Point> townList;
	
	MapBackground[][] mapBackground;
	boolean active;
	
	
	
	Map()
	{
		mapInfo = new ArrayList<MapInfo>();
		hotSpotList = new ArrayList<Point>();
		townList = new ArrayList<Point>();
		timer = new Timer();
		xStart = 0;
		yStart = 0;
		mapBackground = new MapBackground[1280][1024];
		active = true;
		for(int x = 0; x < 1280;x++)
		{
			for(int y = 0; y < 1024; y++ )
			{
				mapBackground[x][y] = new MapBackground();
			}
		}
		//createMapBackground();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run(){
				
				if(active == true)
				{
				update();
				}
				
				
				
			}
		
		},100,100);
	}
	public void setActive(boolean a)
	{
		active = a;
	}
	public boolean getActive()
	{
		return active;
	}
	public Group getGroup(int p)
	{
		return mapInfo.get(p).getGroup();
	}
	public void add(MapInfo m)
	{
		mapInfo.add(m);
	}
	public void add(MapInfo m, int p)
	{
		mapInfo.add(p, m);
	}
	
	public MapInfo getMapInfo(int p)
	{
		return mapInfo.get(p);
	}
	
	public void removeMapInfo(int p)
	{
		mapInfo.remove(p);
	}
	
	public int getXStart()
	{
		return xStart;
	}
	public int getYStart()
	{
		return yStart;
	}
	
	public int getXLocation(int p)
	{
		return mapInfo.get(p).getXMapLocation();
	}
	public int getYLocation(int p)
	{
		return mapInfo.get(p).getYMapLocation();
	}
	
	public void setXLocation(int p,int x)
	{
		mapInfo.get(p).setXMapLocation(x);
	}
	public void setYLocation(int p,int y)
	{
		mapInfo.get(p).setYMapLocation(y);
	}
	
	public int getXMiniLocation(int p)
	{
		return mapInfo.get(p).getXMiniMapLocation();
	}
	public int getYMiniLocation(int p)
	{
		return mapInfo.get(p).getYMiniMapLocation();
	}
	
	public void setXMiniLocation(int p,int x)
	{
		mapInfo.get(p).setXMiniMapLocation(x);
	}
	public void setYMiniLocation(int p,int y)
	{
		mapInfo.get(p).setYMiniMapLocation(y);
	}
	
	public int getSpeed(int p)
	{
		return mapInfo.get(p).getSpeed();
	}
	public void setSpeed(int p,int s)
	{
		mapInfo.get(p).setSpeed(s);
	}
	
	public int getViewRange(int p)
	{
		return mapInfo.get(p).getViewRange();
	}
	public void setViewRange(int p,int v)
	{
		mapInfo.get(p).setViewRange(v);
	}
	
	public void setXStart(int x)
	{
		xStart = x;
	}
	public void setYStart(int y)
	{
		yStart = y;
	}
	
	public void setXStartOld(int x)
	{
		xStartOld = x;
	}
	public void setYStartOld(int y)
	{
		yStartOld = y;
	}
	
	public void setMoveUp(int p, boolean d)
	{
		mapInfo.get(p).setMoveUp(d);
	}
	public void setMoveDown(int p,boolean d)
	{
		mapInfo.get(p).setMoveDown(d);
	}
	public void setMoveLeft(int p,boolean d)
	{
		mapInfo.get(p).setMoveLeft(d);
	}
	public void setMoveRight(int p, boolean d)
	{
		mapInfo.get(p).setMoveRight(d);
	}
	public void setMove()
	{
		for(int i = 0; i < mapInfo.size(); i++)
		{
			mapInfo.get(i).setMove();
		}
	}
	public void update()
	{
		for(int i = 0; i < mapInfo.size(); i++)
		{
			mapInfo.get(i).update();
		}
	}
	public boolean getRedraw(int p)
	{
		return mapInfo.get(p).getRedraw();
	}
	public void setRedraw(int p,boolean b)
	{
		mapInfo.get(p).setRedraw(b);
	}
	
	public void findXandYMiniMapLocation(int xStart, int yStart)
	{
		for(int i = 0; i < mapInfo.size(); i++)
		{
			mapInfo.get(i).findXAndYMiniMapLocation(xStart, yStart);
		}
	}
	public void findXandYMiniMapLocationOld(int xStart, int yStart)
	{
		for(int i = 0; i < mapInfo.size(); i++)
		{
			mapInfo.get(i).findXAndYMiniMapLocationOld(xStart, yStart);
		}
	}
	public int getSize()
	{
		return mapInfo.size();
	}
	
	
	public void createMapBackground()
	{
		Random n = new Random();
		int hotSpots = 0;
		
		int x = 0;
		int y = 0;
		
		
		while(hotSpots < 30 )
		{
			hotSpots = 0;
			
		
		
		for(int i = 0; i < n.nextInt(50) + 1; i++)
		{
			mapBackground[x = n.nextInt(1120)+80][ y = n.nextInt(864)+80].setHotSpot(true);
			
			
			
			System.out.println("New hotSpot");
		}
		
		for(x = 80; x < 1200;x++)
		{
			for(y = 80; y < 944; y++ )
			{
				if(mapBackground[x][y].getHotSpot() == true)
				{
					
					
					
					for(int x1 = x - 80; x1 < x+80 ;x1++)
					{
						for(int y1 = y-80; y1 < y+80; y1++ )
						{
						
							if((mapBackground[x1][y1].getHotSpot() == true) && (x1 != x && y1 != y))
							{
								mapBackground[x1][y1].setHotSpot(false);
								System.out.println(x1+" "+y1+" Is a rejected hotSpot");
							}
						}
					}
					
					System.out.println(x + " " + y + " Is an accepted hotSpot");
					
					
					hotSpots++;
					}
				
				
				
			}
		}
		}
		for(x = 80; x < 1190;x++)
		{
			for(y = 80; y < 946; y++ )
			{
				if(mapBackground[x][y].getHotSpot() == true)
				{
					hotSpotList.add(new Point(x,y));
				}
				}
			}
		
			System.out.println("Amount of hotSpots: " + hotSpotList.size());
			x = n.nextInt(4)+5;
			System.out.println(x);
			for(int i = 0; i < x; i++)
			{
				y = n.nextInt(hotSpotList.size() - 1);
				mapBackground[(int) hotSpotList.get(y).getX()][(int) hotSpotList.get(y).getY()].setTown(true);
				townList.add(new Point((int)hotSpotList.get(y).getX(),(int)hotSpotList.get(y).getY()));
				hotSpotList.remove(y);
				System.out.println("New town at " + hotSpotList.get(y).getX() + " " + hotSpotList.get(y).getY());
			}
			
			System.out.println("Amount of hotSpots: " + hotSpotList.size());
			x = n.nextInt(2)+1;
			System.out.println(x);
			for(int i = 0; i < x; i++)
			{
				y = n.nextInt(hotSpotList.size() - 1);
				mapBackground[(int) hotSpotList.get(y).getX()][(int) hotSpotList.get(y).getY()].setWater(true);
				hotSpotList.remove(y);
				System.out.println("New water at " + hotSpotList.get(y).getX() + " " + hotSpotList.get(y).getY());
			}
			
			System.out.println("Amount of hotSpots: " + hotSpotList.size());
			x = n.nextInt(2)+1;
			System.out.println(x);
			for(int i = 0; i < x; i++)
			{
				y = n.nextInt(hotSpotList.size() - 1);
				mapBackground[(int) hotSpotList.get(y).getX()][(int) hotSpotList.get(y).getY()].setMountain(true);
				hotSpotList.remove(y);
				System.out.println("New mountain at " + hotSpotList.get(y).getX() + " " + hotSpotList.get(y).getY());
			}
			
			// Build roads
		
			int times = townList.size();
			Point temp = new Point();
			 for(int i=0; i < times; i++){
                 for(int j=1; j < (times-i); j++){
                        
                         if(townList.get(j-1).getX()+townList.get(j-1).getY() > townList.get(j).getX()+townList.get(j).getY()){
                                 //swap the elements!
                                 temp = townList.get(j-1);
                                 townList.get(j-1).setLocation(townList.get(j).getLocation());
                                 townList.get(j).setLocation(temp);
                         }
                        
                 }
         }
			for(int i = 0; i < times;i++)
			{
				if(i + 1 < times)
				{
					System.out.println("Building Roads");
				for(x = (int) townList.get(i).getX(); x < townList.get(i+1).getX();x++)
				{
					mapBackground[x][(int)townList.get(i).getY()].setRoad(true);
					
				}
				}
			}
			
			
			
			
			
		
		
		
		
		
	}
	public MapBackground getMapBackground(int x,int y)
	{
		return mapBackground[x][y];
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		findXandYMiniMapLocation(xStart,yStart);
	}
	
}