package MainGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;




public class GameRunner implements KeyListener , MouseListener
{
	
	
	
	private static final long serialVersionUID = 1L;
	
	
	boolean soundDone;
	boolean newInput;
	boolean showGroup;
	boolean newNameActive;
	boolean mouseDragEvent;
	
	int gameDifficulty;
	int movementSpeed;
	int currentMoves;
	int xC;
	int yC;
	
	
	Point mouseClicked;
	Point mouseReleased;
	
	Item tempItem;
	
	
	String PlayerName;
	String GroupName;
	String newName;
	String oldName;
	
	
	
	
	Timer timer;
	
	
	
	
	
	
	JButton settingsButton;
	JButton mapButton;
	
	//Scanner keyboard;
	
	Sounds Sounds;
	
	GridBagConstraints layout;
	
	
	
	
	
	MainGameWindow gameWindow;
	
	
	GameRunner()
	{
		gameDifficulty = 0;
		PlayerName = "";
		GroupName = "";
		
	}
	GameRunner(int gD, String pN, String gN)
	{
			
		
			
			
			Sounds = new Sounds();
			Sounds.addSound(new File("C:/Users/Alex's/Downloads/SoundTest1.wav"));
		
		
		
		
		soundDone = false;
		newInput  = false;
		showGroup = false;
		newNameActive = false;
		mouseDragEvent = false;
	
		
		
		
		
		
		
		
		gameDifficulty = gD;
		PlayerName = pN;
		GroupName = gN;
		movementSpeed = 3;
		currentMoves = 0;
		xC = 0;
		yC = 0;
		newName = "";
		oldName = "";
		
		mouseClicked = new Point(0,0);
		mouseReleased = new Point(0,0);
	
		tempItem = new Item();
		
		
		
       
        
		
		gameWindow = new MainGameWindow(this,this);
		
		
		timer = new Timer(10, new ActionListener(){
			public void actionPerformed(ActionEvent evt)
			{
				if(mouseDragEvent)
				{
					mouseDragEvent = false;
					mouseDragEventHandler(mouseClicked,mouseReleased);
				}
				gameWindow.collisionDetection();
				newInput = false;
				gameWindow.doUpdate();
				
			}
		});
		timer.start();
		
				
			
				
	}
	
	public void mouseDragEventHandler(Point click,Point release)
	{
		System.out.println("Starting mouse Drag Event with " + click + " " + release);
	if(showGroup)
	{
		if((click.getX() > 777 && click.getX() < 1202) && (click.getY() > 130 && click.getY() < 830))
		{
			
			
			
			xC = (int) (click.getX() - 777)/70;
			yC = (int) (click.getY() - 130)/70;
			
			if(gameWindow.getInventory().getItem(xC, yC).getItemHere() == true)
			{
			if((release.getX() > 777 && release.getX() < 1202) && (release.getY() > 130 && release.getY() < 830))
			{
				int xR = 0;
				int yR = 0;
				
				xR = (int) (release.getX() - 777)/70;
				yR = (int) (release.getY() - 130)/70;
				
				
				gameWindow.getInventory().itemSwap(xC,yC,xR,yR);
				gameWindow.setRepaintInventory(true);
				
			}
			else if((release.getX() > 192 && release.getX() < 262) && (release.getY() > 200 && release.getY() < 280))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 1).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),1);
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().addItem(tempItem, xC, yC);
					
				}
				else
				{
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().removeItem(xC, yC);
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((release.getX() > 192 && release.getX() < 262) && (release.getY() > 300 && release.getY() < 380))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 2).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),2);
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().addItem(tempItem, xC, yC);
					
				}
				else
				{
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().removeItem(xC, yC);
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((release.getX() > 192 && release.getX() < 262) && (release.getY() > 400  && release.getY() < 480))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 3).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),3);
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().addItem(tempItem, xC, yC);
					
				}
				else
				{
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().removeItem(xC, yC);
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((release.getX() > 287 && release.getX() < 357) && (release.getY() > 350 && release.getY() < 425))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 4).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),4);
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().addItem(tempItem, xC, yC);
					
				}
				else
				{
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().removeItem(xC, yC);
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((release.getX() > 102 && release.getX() < 167) && (release.getY() > 350 && release.getY() < 425))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 5).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),5);
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().addItem(tempItem, xC, yC);
					
				}
				else
				{
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().removeItem(xC, yC);
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((release.getX() > 287 && release.getX() < 357) && (release.getY() > 250 && release.getY() < 325))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 7).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),7);
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().addItem(tempItem, xC, yC);
					
				}
				else
				{
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().removeItem(xC, yC);
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((release.getX() > 102 && release.getX() < 167) && (release.getY() > 250 && release.getY() < 325))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 6).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),6);
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().addItem(tempItem, xC, yC);
					
				}
				else
				{
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().removeItem(xC, yC);
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			
		  }
		}
		else if(((release.getX() > 777 && release.getX() < 1202) && (release.getY() > 130 && release.getY() < 830)) && ((click.getX() > 87 && click.getX() < 377) && (click.getY() > 200 && click.getY() < 600)))
		{
			
			int xR = 0;
			int yR = 0;
			
			xR = (int) (release.getX() - 777)/70;
			yR = (int) (release.getY() - 130)/70;
			
			if((click.getX() > 202 && click.getX() < 262) && (click.getY() > 200 && click.getY() < 280))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 1).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),1);
					gameWindow.getInventory().addItem(tempItem, xR, yR);
					gameWindow.getGroup().removeItem(gameWindow.getGroupUnitSelected(), 1);
					
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((click.getX() > 202 && click.getX() < 262) && (click.getY() > 300 && click.getY() < 380))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 2).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),2);
					gameWindow.getInventory().addItem(tempItem, xR, yR);
					gameWindow.getGroup().removeItem(gameWindow.getGroupUnitSelected(), 2);
					
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((click.getX() > 202 && click.getX() < 262) && (click.getY() > 400  && click.getY() < 480))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 3).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),3);
					gameWindow.getInventory().addItem(tempItem, xR, yR);
					gameWindow.getGroup().removeItem(gameWindow.getGroupUnitSelected(), 3);
					
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((click.getX() > 287 && click.getX() < 357) && (click.getY() > 350 && click.getY() < 425))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 4).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),4);
					gameWindow.getInventory().addItem(tempItem, xR, yR);
					gameWindow.getGroup().removeItem(gameWindow.getGroupUnitSelected(), 4);
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((click.getX() > 92 && click.getX() < 167) && (click.getY() > 350 && click.getY() < 425))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 5).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),5);
					gameWindow.getInventory().addItem(tempItem, xR, yR);
					gameWindow.getGroup().removeItem(gameWindow.getGroupUnitSelected(), 5);
				}
				
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((click.getX() > 287 && click.getX() < 357) && (click.getY() > 250 && click.getY() < 325))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 7).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),7);
					gameWindow.getInventory().addItem(tempItem, xR, yR);
					gameWindow.getGroup().removeItem(gameWindow.getGroupUnitSelected(), 7);
					
				}
				
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((click.getX() > 92 && click.getX() < 167) && (click.getY() > 250 && click.getY() < 325))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 6).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),6);
					gameWindow.getInventory().addItem(tempItem, xR, yR);
					gameWindow.getGroup().removeItem(gameWindow.getGroupUnitSelected(), 6);
								}
				
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
		}
	}
	else if(gameWindow.getPaintTownInterfaceShop())
	{
		if((click.getX() > 722 && click.getX() < 1142 ) && (click.getY() > 220 && click.getY() < 920))
		{
			
			
			 
			xC = (int) (click.getX() - 722)/70;
			yC = (int) (click.getY() - 220)/70;
			
			if(gameWindow.getInventory().getItem(xC, yC).getItemHere() == true)
			{
			if((release.getX() > 722 && release.getX() < 1142) && (release.getY() > 220 && release.getY() < 920))
			{
				int xR = 0;
				int yR = 0;
				
				xR = (int) (release.getX() - 722)/70;
				yR = (int) (release.getY() - 220)/70;
				
				gameWindow.getInventory().itemSwap(xC,yC,xR,yR);
				
			}
			else if((release.getX() > 292 && release.getX() < 712) && (release.getY() > 220 && release.getY() < 920))
			{
				System.out.println(gameWindow.getGroup().getGold());
				if(gameWindow.getShop().sellItem(gameWindow.getInventory().getItem(xC, yC),gameWindow.getInventory().getItem(xC, yC).getGoldValue()))
				{
					System.out.println("Sold item!");
					gameWindow.getGroup().setGold(gameWindow.getGroup().getGold() + gameWindow.getInventory().getItem(xC, yC).getGoldValue());
					System.out.println(gameWindow.getGroup().getGold());
					gameWindow.getGroup().getInventory().addItem(new Item(), xC, yC);
					gameWindow.setPaintTownInterfaceShop(true);
				}
				else
				{
					System.out.println("Item not sold");
				}
				
			}
		  }
		}
		else if((click.getX() > 292 && click.getX() < 712) && (click.getY() > 220 && click.getY() < 920))
		{
			
			
			 
			xC = (int) (click.getX() - 292)/70;
			yC = (int) (click.getY() - 220)/70;
			
			if((release.getX() > 722 && release.getX() < 1142) && (release.getY() > 220 && release.getY() < 920))
			{

				System.out.println(gameWindow.getGroup().getGold());
				if(gameWindow.getGroup().getGold() >= gameWindow.getShop().getInventory().getItem(xC, yC).getGoldValue())
				{
					gameWindow.getGroup().setGold(gameWindow.getGroup().getGold() - gameWindow.getShop().getInventory().getItem(xC, yC).getGoldValue());
					
					if(gameWindow.getGroup().getInventory().addNextItem(gameWindow.getShop().buyItem(xC, yC)))
					{
						System.out.println("Item bought!");
					}
					else
					{
						System.out.println("Item not bought due to lack of space");
					}
					
				}
				else
				{
					System.out.println("Item not bought due to lack of funds");
				}
				
				
			}
		}
	}
	
	}
	
	public void keyPressed(KeyEvent e)
	{
		 int key = e.getKeyCode();
		 
		 if(key == 27)
		 {
			
		 if(gameWindow.getNameChange() == true)
			 {
				 gameWindow.setNameChange(false);
				 gameWindow.setRepaintUnit(true);
				 newName = oldName;
				 gameWindow.getGroup().setUnitName(gameWindow.getGroupUnitSelected(), newName);
				 newNameActive = false;
				 
			 }
		 else if(showGroup == true)
			 {
				 showGroup = false;
				 gameWindow.setPaintMap(true);
				 gameWindow.setRepaintInventory(false);
				 gameWindow.setRepaintGroup(false);
				 gameWindow.setRepaintUnit(false);
				 gameWindow.setMouseDragItem(false);
				 gameWindow.setActive(true);
			 }
		 else if(gameWindow.getPaintTownInterface())
		 	{
			 	
				gameWindow.setPaintTownInterface(false);
				gameWindow.setPaintMap(true);
				gameWindow.setActive(true);
		 	}
			
		 }
		 
		 else if(key == 10)
		 {
			 if(gameWindow.getNameChange() == true)
			 {
				 newNameActive = false;
				 gameWindow.setNameChange(false);
				 gameWindow.setRepaintUnit(true);
				 if(newName.length() > 9)
				 {
				
					System.out.println("To long of a name error");
					 
				 }
				 else if(newName.length() == 0)
				 {
					 System.out.println("No name exists error");
				 }
				 else
				 {
					 gameWindow.getGroup().setUnitName(gameWindow.getGroupUnitSelected(), newName);
					
					 
					 
				 }
				 
			 }
		 }
	else if(gameWindow.getActive())
	{
		 if(key == 38 &&  newInput == false)
		 {
			 if(currentMoves >= movementSpeed)
			 {
				 gameWindow.setOldPlayerYLocation(gameWindow.getPlayerYLocation());
				 gameWindow.setPlayerYLocation(gameWindow.getPlayerYLocation() - 1); 
				 currentMoves = 0;
			 }
			 else
			 {
				 currentMoves++;
			 }
			 gameWindow.setRepaintMap(true);
			 newInput = true;
			
		 }
		 else if(key == 37 &&  newInput == false)
		 {
			 if(currentMoves >= movementSpeed)
			 {
				
				 gameWindow.setPlayerXLocation(gameWindow.getPlayerXLocation() - 1); 
				 currentMoves = 0;
			 }
			 else
			 {
				 currentMoves++;
			 }
			 gameWindow.setRepaintMap(true);
			 newInput = true;
			 
		 }
		 else if(key == 39 &&  newInput == false)
		 {
			 if(currentMoves >= movementSpeed)
			 {
				
				 gameWindow.setPlayerXLocation(gameWindow.getPlayerXLocation() + 1); 
				 currentMoves = 0;
			 }
			 else
			 {
				 currentMoves++;
			 }
			 gameWindow.setRepaintMap(true);
			 newInput = true;
			 
		 }
		 else if(key == 40&&  newInput == false )
		 {
			 if(currentMoves >= movementSpeed)
			 {
				 gameWindow.setOldPlayerYLocation(gameWindow.getPlayerYLocation());
				 gameWindow.setPlayerYLocation(gameWindow.getPlayerYLocation() + 1); 
				 currentMoves = 0;
			 }
			 else
			 {
				 currentMoves++;
			 }
			 gameWindow.setRepaintMap(true);
			 newInput = true;
			 
		 }
	}
		 else if(newNameActive == true)
		 {
			 if(key == 8)
			 {
				 if(newName.length() > 0 )
				 {
				 newName = newName.substring(0,newName.length()-1);
				 gameWindow.setNewName(newName);
				 }
			 }
			 else if (key >= 65 && key <=90)
			 {
				 if(newName.length() < 9)
				 {
			 newName = newName + KeyEvent.getKeyText(key);
			 newName = newName.toLowerCase();
			 newName = newName.substring(0,1).toUpperCase() + newName.substring(1,newName.length());
			 gameWindow.setNewName(newName);
				 }
			 }
		 }
		}
		
	
	
	public void keyReleased(KeyEvent e)
	{
		System.out.println("keyReleased");
		
	}
	public void keyTyped(KeyEvent e)
	{
		System.out.println("keyTyped");
		
	}
	

	
	
	
	
	
	
	
	
	






@Override
public void mouseClicked(MouseEvent m) {
	System.out.println("Got mouse click at " + m.getPoint() );
if(gameWindow.getActive())
{
	if((m.getPoint().getX() > 1028 && m.getPoint().getX() < 1279)  && (m.getPoint().getY() > 30 && m.getPoint().getY() < 80 ))
	{
		System.out.println("Got a mouse click on open Group Page");
		showGroup = true;
		gameWindow.setRepaintInventory(true);
		gameWindow.setRepaintGroup(true);
		gameWindow.setRepaintUnit(true);
		gameWindow.setActive(false);
	}
	if((m.getPoint().getX() > 64 && m.getPoint().getX() < 192)  && (m.getPoint().getY() > 330 && m.getPoint().getY() < 458 ) && gameWindow.getTownTrigger())
	{	
		System.out.println("Mouse click on open town page");
		gameWindow.setTownTrigger(false);
		gameWindow.setPaintTownInterface(true);
		gameWindow.setActive(false);
	}
}	
	else if(gameWindow.getPaintTownInterface())
	{
		if((m.getPoint().getX() > 100 && m.getPoint().getX() < 292)  && (m.getPoint().getY() > 180 && m.getPoint().getY() < 220 ))
		{	
			System.out.println("Got a click on exit town interface");
			gameWindow.setPaintTownInterface(false);
			gameWindow.setPaintMap(true);
			gameWindow.setActive(true);
			gameWindow.setPaintTownInterfaceShop(false);
			gameWindow.setPaintTownInterfaceHire(false);
			gameWindow.setPaintTownInterfaceQuest(false);
		}
		else if ((m.getPoint().getX() > 100 && m.getPoint().getX() < 292)  && (m.getPoint().getY() > 220 && m.getPoint().getY() < 320 ))
		{
			gameWindow.setPaintTownInterfaceShop(true);
			gameWindow.setPaintTownInterfaceHire(false);
			gameWindow.setPaintTownInterfaceQuest(false);
			gameWindow.setPaintMap(true);
		}
		else if ((m.getPoint().getX() > 100 && m.getPoint().getX() < 292)  && (m.getPoint().getY() > 321 && m.getPoint().getY() < 420 ))
		{
			gameWindow.setPaintTownInterfaceHire(true);
			gameWindow.setPaintTownInterfaceShop(false);
			gameWindow.setPaintTownInterfaceQuest(false);
			gameWindow.setPaintMap(true);
		}
		else if ((m.getPoint().getX() > 100 && m.getPoint().getX() < 292)  && (m.getPoint().getY() > 421 && m.getPoint().getY() < 520 ))
		{
			gameWindow.setPaintTownInterfaceHire(false);
			gameWindow.setPaintTownInterfaceShop(false);
			gameWindow.setPaintTownInterfaceQuest(true);
			gameWindow.setPaintMap(true);
		}
	}
	else if(showGroup)
	{
	
	if((m.getPoint().getX() > 327 && m.getPoint().getX() < 352)  && (m.getPoint().getY() > 160 && m.getPoint().getY() < 185 ))
	{
		System.out.println("Got click on name change");
		gameWindow.setNameChange(true);
		oldName = gameWindow.getGroup().getUnitName(gameWindow.getGroupUnitSelected());
		newName = gameWindow.getGroup().getUnitName(gameWindow.getGroupUnitSelected());
		gameWindow.setNewName(newName);
		newNameActive = true;
	}
	
	else if((m.getPoint().getX() > 377 && m.getPoint().getX() < 777)  && (m.getPoint().getY() > 135 && m.getPoint().getY() < 835 ))
	{
		System.out.println("Got mouse click on groupTab");
		int x=0;
		int y=0;
		if(m.getPoint().getX() < 577)
		{
			x = 0;
		}
		else
		{
			x = 10;
		}
		y = (int)((m.getPoint().getY()-135)/70)+1;
		if(gameWindow.getGroup().getSize()-1 >= x+y)
		{
			System.out.println("Unit exists");
			gameWindow.setGroupUnitSelected(x+y);
		}
		else
		{
			System.out.println("Unit doesnt exist");
		}
		gameWindow.setRepaintGroup(true);
		gameWindow.setRepaintUnit(true);
		gameWindow.setNameChange(false);
		
		gameWindow.setRepaintLevelAdd(false);
		
	}
	else if((m.getPoint().getX() > 212 && m.getPoint().getX() < 242)  && (m.getPoint().getY() > 800 && m.getPoint().getY() < 835 ))
	{
		System.out.println("Got mouse click on level-up point spend");
		if(gameWindow.getGroup().getUnitSavedLevels(gameWindow.getGroupUnitSelected()) > 0)
		{
		gameWindow.setRepaintUnit(true);
		gameWindow.setRepaintLevelAdd(true);
		gameWindow.setAttributesLeft(gameWindow.getGroup().getUnitSavedLevels(gameWindow.getGroupUnitSelected()) * 2);
		gameWindow.setAddAttack(0);
		gameWindow.setAddDefense(0);
		gameWindow.setAddSpeed(0);
		gameWindow.setAddMoral(0);
		gameWindow.setAddHealth(0);
		
		}
	}
	else if(gameWindow.getRepaintLevelAdd() == true)
	{
		System.out.println("Inside repaintLevelAdd Mouse click");
		if((m.getPoint().getX() > 87 && m.getPoint().getX() < 167)  && (m.getPoint().getY() > 795 && m.getPoint().getY() < 825 ))
		{
			System.out.println("Stopping the level-up");
			gameWindow.setRepaintUnit(true);
			gameWindow.setRepaintLevelAdd(false);
			gameWindow.setAttributesLeft(0);
			gameWindow.setAddAttack(0);
			gameWindow.setAddDefense(0);
			gameWindow.setAddSpeed(0);
			gameWindow.setAddMoral(0);
			gameWindow.setAddHealth(0);
			
		}
		else if(((m.getPoint().getX() > 292 && m.getPoint().getX() < 372)  && (m.getPoint().getY() > 795 && m.getPoint().getY() < 825 )) &&(gameWindow.getAttributesLeft() == 0))
		{
		System.out.println("Finishing the level-up");	
		gameWindow.setRepaintUnit(true);
		gameWindow.setRepaintLevelAdd(false);
		gameWindow.setAttributesLeft(0);
		gameWindow.getGroup().setUnitSavedLevels(gameWindow.getGroupUnitSelected(),0);
		gameWindow.getGroup().setUnitAttack(gameWindow.getGroupUnitSelected(), (int) (gameWindow.getGroup().getUnitAttack(gameWindow.getGroupUnitSelected()) + (gameWindow.getAddAttack()*5)));
		gameWindow.getGroup().setUnitDefense(gameWindow.getGroupUnitSelected(), (int) (gameWindow.getGroup().getUnitDefense(gameWindow.getGroupUnitSelected()) + (gameWindow.getAddDefense()*5)));
		gameWindow.getGroup().setUnitSpeed(gameWindow.getGroupUnitSelected(), (int) (gameWindow.getGroup().getUnitSpeed(gameWindow.getGroupUnitSelected()) + (gameWindow.getAddSpeed()*5)));
		gameWindow.getGroup().setUnitMoral(gameWindow.getGroupUnitSelected(), (int) (gameWindow.getGroup().getUnitMoral(gameWindow.getGroupUnitSelected()) + (gameWindow.getAddMoral()*5)));
		gameWindow.getGroup().setUnitHealth(gameWindow.getGroupUnitSelected(), (int) (gameWindow.getGroup().getUnitHealth(gameWindow.getGroupUnitSelected()) + (gameWindow.getAddHealth()*5)));
		gameWindow.setAddAttack(0);
		gameWindow.setAddDefense(0);
		gameWindow.setAddSpeed(0);
		gameWindow.setAddMoral(0);
		gameWindow.setAddHealth(0);
		
		
		}
		else if(gameWindow.getAttributesLeft() > 0)
		{
			if((m.getPoint().getX() > 162 && m.getPoint().getX() < 182)  && (m.getPoint().getY() > 695 && m.getPoint().getY() < 730 ))
			{
				System.out.println("Click on attack add");
				gameWindow.setAddAttack(gameWindow.getAddAttack() + 1);
				gameWindow.setAttributesLeft(gameWindow.getAttributesLeft() - 1);
				gameWindow.setRepaintUnit(true);
				
			}
			else if((m.getPoint().getX() > 162 && m.getPoint().getX() < 182)  && (m.getPoint().getY() > 645 && m.getPoint().getY() < 665 ))
			{
				gameWindow.setAddHealth(gameWindow.getAddHealth() + 1);
				gameWindow.setAttributesLeft(gameWindow.getAttributesLeft() - 1);
				gameWindow.setRepaintUnit(true);
				
			}
			else if((m.getPoint().getX() > 172 && m.getPoint().getX() < 192)  && (m.getPoint().getY() > 745 && m.getPoint().getY() < 765 ))
			{
				gameWindow.setAddDefense(gameWindow.getAddDefense() + 1);
				gameWindow.setAttributesLeft(gameWindow.getAttributesLeft() - 1);
				gameWindow.setRepaintUnit(true);
				
			}
			else if((m.getPoint().getX() > 307 && m.getPoint().getX() < 327)  && (m.getPoint().getY() > 645 && m.getPoint().getY() < 665 ))
			{
				gameWindow.setAddMoral(gameWindow.getAddMoral() + 1);
				gameWindow.setAttributesLeft(gameWindow.getAttributesLeft() - 1);
				gameWindow.setRepaintUnit(true);
				
			}
			else if((m.getPoint().getX() > 307 && m.getPoint().getX() < 327)  && (m.getPoint().getY() > 695 && m.getPoint().getY() < 725 ))
			{
				gameWindow.setAddSpeed(gameWindow.getAddSpeed() + 1);
				gameWindow.setAttributesLeft(gameWindow.getAttributesLeft() - 1);
				gameWindow.setRepaintUnit(true);
				
			}
		}
	
	
	}
	
	
	}
}
@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mousePressed(MouseEvent e) {
	mouseClicked = e.getPoint();
if(showGroup == true)
{
	if((e.getX() > 777 && e.getX() < 1202) && (e.getY() > 130 && e.getY() < 830))
	{
		
		
		
		xC = (int) (e.getX() - 777)/70;
		yC = (int) (e.getY() - 130)/70;
		
		if(gameWindow.getInventory().getItem(xC, yC).getItemHere() == true)
		{
			
			gameWindow.setDraggedItem(gameWindow.getInventory().getItem(xC, yC));
			gameWindow.setMouseDragItem(true);
			gameWindow.setRepaintGroup(true);
			gameWindow.setRepaintUnit(true);
			gameWindow.setRepaintInventory(true);
			gameWindow.setPaintMap(true);
			
		}
		
	}
	else if((e.getX() < 77 || e.getX() > 1202) || (e.getY() < 130 || e.getY() > 830))
	{
		 showGroup = false;
		 gameWindow.setPaintMap(true);
		 gameWindow.setRepaintInventory(false);
		 gameWindow.setRepaintGroup(false);
		 gameWindow.setRepaintUnit(false);
		 gameWindow.setMouseDragItem(false);
		 gameWindow.setActive(true);
	}
	else if((e.getX() > 192 && e.getX() < 262) && (e.getY() > 200 && e.getY() < 280))
	{
		
		if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 1).getItemHere() == true)
		{
			
			gameWindow.setDraggedItem(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 1));
			gameWindow.setMouseDragItem(true);
			gameWindow.setRepaintGroup(true);
			gameWindow.setRepaintUnit(true);
			gameWindow.setRepaintInventory(true);
			gameWindow.setPaintMap(true);
			
		}
	}
	else if((e.getX() > 192 && e.getX() < 262) && (e.getY() > 300 && e.getY() < 380))
	{
		
		if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 2).getItemHere() == true)
		{
			
			gameWindow.setDraggedItem(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 2));
			gameWindow.setMouseDragItem(true);
			gameWindow.setRepaintGroup(true);
			gameWindow.setRepaintUnit(true);
			gameWindow.setRepaintInventory(true);
			gameWindow.setPaintMap(true);
			
		}
	}
	else if((e.getX() > 192 && e.getX() < 262) && (e.getY() > 400  && e.getY() < 480))
	{
		
		if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 3).getItemHere() == true)
		{
			
			gameWindow.setDraggedItem(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 3));
			gameWindow.setMouseDragItem(true);
			gameWindow.setRepaintGroup(true);
			gameWindow.setRepaintUnit(true);
			gameWindow.setRepaintInventory(true);
			gameWindow.setPaintMap(true);
			
		}
	}
	else if((e.getX() > 287 && e.getX() < 357) && (e.getY() > 350 && e.getY() < 425))
	{
		
		if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 4).getItemHere() == true)
		{
			
			gameWindow.setDraggedItem(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 4));
			gameWindow.setMouseDragItem(true);
			gameWindow.setRepaintGroup(true);
			gameWindow.setRepaintUnit(true);
			gameWindow.setRepaintInventory(true);
			gameWindow.setPaintMap(true);
			
		}
	}
	else if((e.getX() > 92 && e.getX() < 167) && (e.getY() > 350 && e.getY() < 425))
	{
		
		if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 5).getItemHere() == true)
		{
			
			gameWindow.setDraggedItem(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 5));
			gameWindow.setMouseDragItem(true);
			gameWindow.setRepaintGroup(true);
			gameWindow.setRepaintUnit(true);
			gameWindow.setRepaintInventory(true);
			gameWindow.setPaintMap(true);
			
		}
	}
	else if((e.getX() > 287 && e.getX() < 357) && (e.getY() > 250 && e.getY() < 325))
	{
		
		if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 7).getItemHere() == true)
		{
			
			gameWindow.setDraggedItem(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 7));
			gameWindow.setMouseDragItem(true);
			gameWindow.setRepaintGroup(true);
			gameWindow.setRepaintUnit(true);
			gameWindow.setRepaintInventory(true);
			gameWindow.setPaintMap(true);
			
		}
	}
	else if((e.getX() > 92 && e.getX() < 167) && (e.getY() > 250 && e.getY() < 325))
	{
		
		if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 6).getItemHere() == true)
		{
			
			gameWindow.setDraggedItem(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 6));
			gameWindow.setMouseDragItem(true);
			gameWindow.setRepaintGroup(true);
			gameWindow.setRepaintUnit(true);
			gameWindow.setRepaintInventory(true);
			gameWindow.setPaintMap(true);
			
		}
	}
}
else if(gameWindow.getPaintTownInterfaceShop())
{
	if((e.getX() > 722 && e.getX() < 1142) && (e.getY() > 220 && e.getY() < 920))
	{
		
		
		
		xC = (int) (e.getX() - 722)/70;
		yC = (int) (e.getY() - 220)/70;
		
		if(gameWindow.getInventory().getItem(xC, yC).getItemHere() == true)
		{
			
			gameWindow.setDraggedItem(gameWindow.getInventory().getItem(xC, yC));
			gameWindow.setMouseDragItem(true);
		}
	}
	if((e.getX() > 292 && e.getX() < 712) && (e.getY() > 220 && e.getY() < 920))
	{
		
		
		
		xC = (int) (e.getX() - 292)/70;
		yC = (int) (e.getY() - 220)/70;
		
		if(gameWindow.getShop().getInventory().getItem(xC, yC).getItemHere() == true)
		{
			
			gameWindow.setDraggedItem(gameWindow.getShop().getInventory().getItem(xC, yC));
			gameWindow.setMouseDragItem(true);
		}
	}
}
else if(gameWindow.getPaintTownInterfaceHire())
{
	if((e.getX() > 292 && e.getX() < 592) && (e.getY() > 220 && e.getY() < 920))
	{
		yC = (int) (e.getY() - 220)/70;
		System.out.println(gameWindow.getHireableAmount());
		if (yC <= gameWindow.getHireableAmount())
		{
			gameWindow.setHireUnitSelected(yC);
		}
	}
	else if(gameWindow.getHireUnitSelected() >= 0)
	{
	if((e.getX() > 662 && e.getX() < 762) && (e.getY() > 870 && e.getY() < 910))
	{
		if(gameWindow.getGroup().getGold() >= gameWindow.getHireable().getUnit(gameWindow.getHireUnitSelected()).getCostToBuy())
		{
			gameWindow.getGroup().setGold(gameWindow.getGroup().getGold() - gameWindow.getHireable().getUnit(gameWindow.getHireUnitSelected()).getCostToBuy() );
			gameWindow.getGroup().add(gameWindow.getHireable().hireUnit(gameWindow.getHireUnitSelected()));
			if(gameWindow.getHireableAmount() == 0)
			{
				gameWindow.setHireUnitSelected(-1);
			}
			else
			{
				gameWindow.setHireUnitSelected(0);
			}
		}
	}
	}
}
else if(gameWindow.getPaintTownInterfaceQuest())
{
	if((e.getX() > 292 && e.getX() < 592) && (e.getY() > 220 && e.getY() < 920))
	{
		yC = (int) (e.getY() - 220)/70;
		if (yC <= gameWindow.getQuests().getAll().size())
		{
			gameWindow.setQuestSelected(yC);
		}
	}
}
}

@Override
public void mouseReleased(MouseEvent e) {
	mouseReleased = e.getPoint();
	
	if(!(mouseReleased.getX() == mouseClicked.getX()) || !(mouseReleased.getY() == mouseClicked.getY()))
	{
		mouseDragEvent = true;
	}
	
	if(gameWindow.getMouseDragItem() == true)
	{
		gameWindow.setMouseDragItem(false);
	}
}



}



class MainGameWindow extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	Rectangle playerRect;
	Rectangle townRect;
	Rectangle target;
	
	
	
	
	
	
	List<MapInfo> oldMapInfo;
	
	
	MyPanel gameWindow;
	
	
	
	
	

	
	
	
	MainGameWindow(KeyListener key, MouseListener mouse)
	{
		
		
		oldMapInfo = new ArrayList<MapInfo>();
		
		
		
		
		gameWindow = new MyPanel();
		
		townRect = new Rectangle(210,399,122,138);
		
		
		addKeyListener(key);
		addMouseListener(mouse);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setTitle("Temp Game Name");
		setPreferredSize(new Dimension(1280, 1024));
		setResizable(false);
		setSize(1280, 1024);
		setMinimumSize(new Dimension(1280, 1024));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		getContentPane().add(gameWindow);
		pack();
		setVisible(true);
		
		
		
		
        
        
        
       
        
       
       
		
		
		
		
	}
	public int getViewRange()
	{
		return gameWindow.getViewRange();
	}
	public void setViewRange(int v)
	{
		gameWindow.setViewRange(v);
	}
	public void doUpdate()
	{
		gameWindow.repaint();
	}
	
	public int getAttributesLeft()
	{
		return gameWindow.getAttributesLeft();
	}
	public void setAttributesLeft(int a)
	{
		gameWindow.setAttributesLeft(a);
	}
	public int getAddDefense()
	{
		return gameWindow.getAddDefense();
	}
	public void setAddDefense(int d)
	{
		gameWindow.setAddDefense(d);
	}
	public int getAddAttack()
	{
		return gameWindow.getAddAttack();
	}
	public void setAddAttack(int a)
	{
		gameWindow.setAddAttack(a);;
	}
	public int getAddSpeed()
	{
		return gameWindow.getAddSpeed();
	}
	public void setAddSpeed(int s)
	{
		gameWindow.setAddSpeed(s);;
	}
	public int getAddHealth()
	{
		return gameWindow.getAddHealth();
	}
	public void setAddHealth(int h)
	{
		gameWindow.setAddHealth(h);;
	}
	public int getAddMoral()
	{
		return gameWindow.getAddMoral();
	}
	public void setAddMoral(int m)
	{
		gameWindow.setAddMoral(m);;
	}
	
	public Group getGroup()
	{
		return gameWindow.getGroup();
	}
	
	
	public Map getMap()
	{
		return gameWindow.getMap();
	}
	
	public void setPlayerXLocation(int x)
	{
		gameWindow.setPlayerXLocation(x);
	}
	public void setPlayerYLocation(int y)
	{
		gameWindow.setPlayerYLocation(y);
	}
	public void setOldPlayerXLocation(int oX)
	{
		gameWindow.setOldPlayerXLocation(oX);
	}
	public void setOldPlayerYLocation(int oY)
	{
		gameWindow.setOldPlayerYLocation(oY);
	}
	public int getPlayerXLocation()
	{
		return gameWindow.getPlayerXLocation();
	}
	public int getPlayerYLocation()
	{
		return gameWindow.getPlayerYLocation();
	}
	public int getOldPlayerXLocation()
	{
		return gameWindow.getOldPlayerXLocation();
	}
	public int getOldPlayerYLocation()
	{
		return gameWindow.getOldPlayerYLocation();
	}
	
	public int getXStart()
	{
		return gameWindow.getXStart();
	}
	public int getYStart()
	{
		return gameWindow.getYStart();
	}
	public int getXEnd()
	{
		return gameWindow.getXEnd();
	}
	public int getYEnd()
	{
		return gameWindow.getYEnd();
	}
	public void setXStart(int x)
	{
		gameWindow.setXStart(x);
	}
	public void setYStart(int y)
	{
		gameWindow.setYStart(y);
	}
	public void setXEnd(int x)
	{
		gameWindow.setXEnd(x);
	}
	public void setYEnd(int y)
	{
		gameWindow.setYEnd(y);
	}
	
	public void setGroupUnitSelected(int p)
	{
		gameWindow.setGroupUnitSelected(p);
	}
	public int getGroupUnitSelected()
	{
		return gameWindow.getGroupUnitSelected();
	}
	
	
	public void setRepaintInventory(boolean b)
	{
		gameWindow.setRepaintInventory(b);
	}
	
	public void setRepaintGroup(boolean b)
	{
		gameWindow.setRepaintGroup(b);
	}
	public void setRepaintUnit(boolean b)
	{
		gameWindow.setRepaintUnit(b);
	}
	
	public void setNameChange(boolean b)
	{
		gameWindow.setNameChange(b);
	}
	public void setRepaintLevelAdd(boolean b)
	{
		gameWindow.setRepaintLevelAdd(b);
	}
	public void setRepaintMap(boolean b)
	{
		gameWindow.setRepaintMap(b);
	}
	public void setPaintMap(boolean b)
	{
		gameWindow.setPaintMap(b);
	}
	public boolean getRepaintInventory()
	{
		return gameWindow.getRepaintInventory();
	}
	
	public boolean getRepaintGroup()
	{
		return gameWindow.getRepaintGroup();
	}
	public boolean getRepaintUnit()
	{
		return gameWindow.getRepaintUnit();
	}
	
	public boolean getNameChange()
	{
		return gameWindow.getNameChange();
	}
	public boolean getRepaintLevelAdd()
	{
		return gameWindow.getRepaintLevelAdd();
	}
	public boolean getRepaintMap()
	{
		return gameWindow.getRepaintMap();
	}
	public boolean getPaintMap()
	{
		return gameWindow.getPaintMap();
	}
	public void setActive(boolean a)
	{
		gameWindow.setActive(a);
	}
	public boolean getActive()
	{
		return gameWindow.getActive();
	}
	public String getNewName()
	{
		return gameWindow.getNewName();
	}
	public void setNewName(String n)
	{
		gameWindow.setNewName(n);
	}
	
	public Inventory getInventory()
	{
		return gameWindow.getInventory();
	}
	public void setMouseDragItem(boolean b)
	{
		gameWindow.setMouseDragItem(b);
	}
	public boolean getMouseDragItem()
	{
		return gameWindow.getMouseDragItem();
	}
	public void setDraggedItem(Item i)
	{
		gameWindow.setDraggedItem(i);
	}
	public Item getDraggedItem()
	{
		return gameWindow.getDraggedItem();
	}
	public void setTownTrigger(boolean b)
	{
		gameWindow.setTownTrigger(b);
	}
	public boolean getTownTrigger()
	{
		return gameWindow.getTownTrigger();
	}
	public void setPaintTownInterface(boolean b)
	{
		gameWindow.setPaintTownInterface(b);
	}
	public boolean getPaintTownInterface()
	{
		return gameWindow.getPaintTownInterface();
	}
	public void setPaintTownInterfaceShop(boolean b)
	{
		gameWindow.setPaintTownInterfaceShop(b);
	}
	public boolean getPaintTownInterfaceShop()
	{
		return gameWindow.getPaintTownInterfaceShop();
	}
	public boolean getPaintTownInterfaceHire()
	{
		return gameWindow.getPaintTownInterfaceHire();
	}
	public void setPaintTownInterfaceHire(boolean b)
	{
		gameWindow.setPaintTownInterfaceHire(b);
	}
	public boolean getPaintTownInterfaceQuest()
	{
		return gameWindow.getPaintTownInterfaceQuest();
	}
	public void setPaintTownInterfaceQuest(boolean b)
	{
		gameWindow.setPaintTownInterfaceQuest(b);
	}
	public Shop getShop()
	{
		return gameWindow.getShop();
	}
	public QuestSystem getQuests()
	{
		return gameWindow.getQuests();
	}
	public int getHireableAmount()
	{
		return gameWindow.getHireableAmount();
	}
	public void setHireUnitSelected(int u)
	{
		gameWindow.setHireUnitSelected(u);
	}
	public int getHireUnitSelected()
	{
		return gameWindow.getHireUnitSelected();
	}
	public void setQuestSelected(int u)
	{
		gameWindow.setQuestSelected(u);
	}
	public int getQuestSelected()
	{
		return gameWindow.getQuestSelected();
	}
	public Hireable getHireable()
	{
		return gameWindow.getHireable();
	}
	public void collisionDetection()
	{
	playerRect = new Rectangle(gameWindow.getPlayerXLocation() - 15, gameWindow.getPlayerYLocation() - 15,30,30);
	for(int i = 0; i < gameWindow.getMap().getSize();i++)
	{
		target = new Rectangle(gameWindow.getMap().getMapInfo(i).getXMapLocation()-15,gameWindow.getMap().getMapInfo(i).getYMapLocation()-15,30,30);
		
		if(playerRect.intersects(target))
		{
			System.out.println("Player intersects " + i  );
		}
	}
	if(playerRect.intersects(townRect))
	{
		if(gameWindow.getPaintTownInterface())
		{
			gameWindow.setTownTrigger(false);
			gameWindow.setStopTownTrigger(true);
		}
		else 
		{
			gameWindow.setTownTrigger(true);
		}
	}
	else if(gameWindow.getTownTrigger() == true)
	{
		gameWindow.setTownTrigger(false);
		gameWindow.setStopTownTrigger(true);
	}
		
	}
	
	public void paint(Graphics g)
	{
		
	}
	
	
	
	

	
}

class MyPanel extends JPanel
{
	
	int playerXLocation;
	int playerYLocation;
	int oldPlayerXLocation;
	int oldPlayerYLocation;
	int xStart;
	int yStart;
	int xEnd;
	int yEnd;
	int groupUnitSelected;
	int hireUnitSelected;
	int questSelected;
	int attributesLeft;
	int addDefense;
	int addAttack;
	int addSpeed;
	int addMoral;
	int addHealth;
	int viewRange;
	int flashCount;
	int itemLocation;
	int hireableAmount;
	
	
	boolean repaintInventory;
	
	boolean repaintUnit;
	boolean repaintGroup;
	
	boolean nameChange;
	boolean repaintLevelAdd;
	boolean repaintMap;
	boolean paintMap;
	boolean flashReset;
	boolean mouseDragItem;
	boolean townTrigger;
	boolean stopTownTrigger;
	boolean paintTownInterface;
	boolean paintTownInterfaceShop;
	boolean paintTownInterfaceHire;
	boolean paintTownInterfaceQuest;
	
	Time time;
	
	
	Image MainPictures;
	Map map;
	Group tempGroup;
	Inventory tempInventory;
	
	BufferedImage playerImage;
	BufferedImage tempImage;
	
	
	
	Font groupUnitFont;
	Font unitFont;
	Font levelFont;
	
	Shop shop;
	Hireable hireable;
	QuestSystem quests;
	
	Group Group;
	Item draggedItem;
	
	String newName;
	String oldName;
	
	MyPanel()
	{
		
		setPreferredSize(new Dimension(1280, 1024));
		setSize(1280, 1024);
		setMinimumSize(new Dimension(1280, 1024));
		
		
	
		
		playerXLocation = 356;
		playerYLocation = 567;
		oldPlayerXLocation = playerXLocation;
		oldPlayerYLocation = playerYLocation;
		xStart = 50;
		yStart = 50;
		xEnd = 100;
		yEnd = 100;
		groupUnitSelected = 1;
		hireUnitSelected = 0;
		questSelected = 0;
		attributesLeft = 0;
		addDefense = 0;
		addAttack = 0;
		addSpeed = 0;
		addMoral = 0;
		addHealth = 0;
		viewRange = 50;
		flashCount = 0;
		itemLocation = 0;
		hireableAmount = -1;
		
		
		repaintInventory = false;
		
		repaintUnit = false;
		repaintGroup = false;
		
		nameChange = false;
		repaintLevelAdd = false;
		repaintMap = true;
		paintMap = true;
		flashReset = false;
		mouseDragItem = false;
		townTrigger = false;
		paintTownInterface = false;
		stopTownTrigger = false;
		paintTownInterfaceShop = false;
		paintTownInterfaceHire = false;
		paintTownInterfaceQuest = false;
		
		draggedItem = new Item();
				
		
		MainPictures = new Image();
		MainPictures.add("Images/MainMap.jpg");
		MainPictures.add("Images/MainBackground.jpg");
		MainPictures.add("Images/SettingsWindowBackground.jpg");
		MainPictures.add("Images/TopBarWindow.jpg");
		MainPictures.add("Images/PlayerMapIcon.jpg");
		MainPictures.add("Images/GameMap.jpg");
		
		playerImage = MainPictures.getImage(4);
		newName = "";
		oldName = "";
		
		tempGroup = new Group();
		tempGroup.setImage("Images/enemyMapIcon.jpg");
			
		tempInventory = new Inventory();
		
		time = new Time();
		
		map = new Map();
			
		map.setXStart(710);
		map.setYStart(100);
		
		map.add(new MapInfo(tempGroup,tempInventory,800,200,800,200,1,10));
		map.add(new MapInfo(tempGroup,tempInventory,900,650,900,650,1,10));

		map.setMoveDown(0, true);
		map.setMoveUp(1, true);
		
		
		
		groupUnitFont = new Font("TimesRoman",Font.ITALIC,40);
        unitFont = new Font("TimesRoman",Font.BOLD,20);
        levelFont = new Font("TimesRoman",Font.ITALIC,25);
        
        Group = new Group(3);
		
		Group.add(new Unit());
		Group.add(new Unit(10,10, 10, 10, 10,10,20,1, "Test1"));
		Group.add(new Unit(10,10, 10, 10, 10, 10,10,1, "Test2"));
		Group.add(new Unit(10,10, 10, 10, 10,10, 10, 2,"Test3"));
		Group.add(new Unit(10,10, 10, 10, 10, 10,10,3, "Test4"));
		
		
		
		Group.addItem(1, new Item(5,20,-2, .02,.2,2,10,4,10,"Images/BasicSword.png","Temp"));
		Group.addItem(1, new Item(5,20,-2, .02,.2,2,10,5,100,"Images/DragonShield.png","Temp"));
		
		Group.setUnitSavedLevels(4, 2);
		Group.setUnitSavedLevels(2, 1);
		
		Group.getInventory().addItem(new Item(5,5,5, 2,2,2,10,1,10,"Images/BasicSword.png","Basic Sword"), 5,0);
		Group.getInventory().addItem(new Item(5,5,5, 2,2,2,10,1,100,"Images/DragonShield.png","Dragon Shield"),1,2);
		
		Group.setGold(100);
		
		shop = new Shop();
		
		shop.getInventory().addItem(new Item(5,5,5, 2,2,2,10,1,10,"Images/BasicSword.png","Basic Sword"), 3,7);
		shop.getInventory().addItem(new Item(5,5,5, 2,2,2,10,1,100,"Images/DragonShield.png","Dragon Shield"),4,8);
		
		hireable = new Hireable();
		
		hireable.addUnit(new Unit(10,10,10, 10, 10, 10,10,20,1, "TestHire1"));
		hireable.addUnit(new Unit(20,10,10, 10, 10, 10, 10,10,1, "TestHire2"));
		hireable.addUnit(new Unit(100,10,10, 10, 10, 10,10, 10, 2,"TestHire3"));
		
		hireable.getUnit(2).addItem(new Item(5,20,-2, .02,.2,2,10,4,10,"Images/BasicSword.png","Temp"));
		hireable.getUnit(2).addItem( new Item(5,20,-2, .02,.2,2,10,5,100,"Images/DragonShield.png","Temp"));
		
		quests = new QuestSystem();
		
		quests.addQuest(new Quest("Test Name 1","Quest Description 1"));
		quests.addQuest(new Quest("Test Name 2","Quest Description 2"));
		quests.addQuest(new Quest("Test Name 3","Quest Description 3"));
		
		
		
	}
	public int getQuestSelected()
	{
		return questSelected;
	}
	public void setQuestSelected(int q)
	{
		questSelected = q;
	}
	public QuestSystem getQuests()
	{
		return quests;
	}
	public int getHireableAmount()
	{
		return hireableAmount;
	}
	public void setHireUnitSelected(int u)
	{
		hireUnitSelected = u;
	}
	public int getHireUnitSelected()
	{
		return hireUnitSelected;
	}
	public Hireable getHireable()
	{
		return hireable;
	}
	public Shop getShop()
	{
		return shop;
	}
	public void setStopTownTrigger(boolean b)
	{
		stopTownTrigger = b;
	}
	public boolean getStopTownTrigger()
	{
		return stopTownTrigger;
	}
	public void setTownTrigger(boolean b)
	{
		townTrigger = b;
	}
	public boolean getTownTrigger()
	{
		return townTrigger;
	}
	public void setPaintTownInterface(boolean b)
	{
		paintTownInterface = b;
	}
	public boolean getPaintTownInterface()
	{
		return paintTownInterface;
	}
	public void setPaintTownInterfaceShop(boolean b)
	{
		paintTownInterfaceShop = b;
	}
	public boolean getPaintTownInterfaceShop()
	{
		return paintTownInterfaceShop;
	}
	public void setPaintTownInterfaceQuest(boolean b)
	{
		paintTownInterfaceQuest = b;
	}
	public boolean getPaintTownInterfaceQuest()
	{
		return paintTownInterfaceQuest;
	}
	public boolean getPaintTownInterfaceHire()
	{
		return paintTownInterfaceHire;
	}
	public void setPaintTownInterfaceHire(boolean b)
	{
		paintTownInterfaceHire = b;
	}
	public void setMouseDragItem(boolean b)
	{
		mouseDragItem = b;
	}
	public boolean getMouseDragItem()
	{
		return mouseDragItem;
	}
	public void setDraggedItem(Item i)
	{
		draggedItem = i;
	}
	public Item getDraggedItem()
	{
		return draggedItem;
	}
	public Inventory getInventory()
	{
		return Group.getInventory();
	}
	public String getNewName()
	{
		return newName;
	}
	public void setNewName(String n)
	{
		oldName = newName;
		newName = n;
	}
	public void setActive(boolean a)
	{
		map.setActive(a);
	}
	public boolean getActive()
	{
		return map.getActive();
	}
	public int getViewRange()
	{
		return viewRange;
	}
	public void setViewRange(int v)
	{
		viewRange = v;
	}
	public Group getGroup()
	{
		return Group;
	}
	public int getAttributesLeft()
	{
		return attributesLeft;
	}
	public void setAttributesLeft(int a)
	{
		attributesLeft = a;
	}
	public int getAddDefense()
	{
		return addDefense;
	}
	public void setAddDefense(int d)
	{
		addDefense = d;
	}
	public int getAddAttack()
	{
		return addAttack;
	}
	public void setAddAttack(int a)
	{
		addAttack = a;
	}
	public int getAddSpeed()
	{
		return addSpeed;
	}
	public void setAddSpeed(int s)
	{
		addSpeed = s;
	}
	public int getAddHealth()
	{
		return addHealth;
	}
	public void setAddHealth(int h)
	{
		addHealth = h;
	}
	public int getAddMoral()
	{
		return addMoral;
	}
	public void setAddMoral(int m)
	{
		addMoral = m;
	}
	
	
	public void setPlayerXLocation(int x)
	{
		playerXLocation = x;
	}
	public void setPlayerYLocation(int y)
	{
		playerYLocation = y;
	}
	public void setOldPlayerXLocation(int oX)
	{
		oldPlayerXLocation = oX;
	}
	public void setOldPlayerYLocation(int oY)
	{
		oldPlayerYLocation = oY;
	}
	public int getPlayerXLocation()
	{
		return playerXLocation;
	}
	public int getPlayerYLocation()
	{
		return playerYLocation;
	}
	public int getOldPlayerXLocation()
	{
		return oldPlayerXLocation;
	}
	public int getOldPlayerYLocation()
	{
		return oldPlayerYLocation;
	}
	
	public int getXStart()
	{
		return xStart;
	}
	public int getYStart()
	{
		return yStart;
	}
	public int getXEnd()
	{
		return xEnd;
	}
	public int getYEnd()
	{
		return yEnd;
	}
	public void setXStart(int x)
	{
		xStart = x;
	}
	public void setYStart(int y)
	{
		yStart = y;
	}
	public void setXEnd(int x)
	{
		xEnd = x;
	}
	public void setYEnd(int y)
	{
		yEnd = y;
	}
	
	public void setGroupUnitSelected(int p)
	{
		groupUnitSelected = p;
	}
	public int getGroupUnitSelected()
	{
		return groupUnitSelected;
	}
	
	public void setRepaintInventory(boolean b)
	{
		repaintInventory = b;
	}
	
	public void setRepaintGroup(boolean b)
	{
		repaintGroup = b;
	}
	public void setRepaintUnit(boolean b)
	{
		repaintUnit = b;
	}
	
	public void setNameChange(boolean b)
	{
		nameChange = b;
	}
	public void setRepaintLevelAdd(boolean b)
	{
		repaintLevelAdd = b;
	}
	public void setRepaintMap(boolean b)
	{
		repaintMap = b;
	}
	public void setPaintMap(boolean b)
	{
		paintMap = b;
	}
	public boolean getRepaintInventory()
	{
		return repaintInventory;
	}
	
	public boolean getRepaintGroup()
	{
		return repaintGroup;
	}
	public boolean getRepaintUnit()
	{
		return repaintUnit;
	}
	
	public boolean getNameChange()
	{
		return nameChange;
	}
	public boolean getRepaintLevelAdd()
	{
		return repaintLevelAdd;
	}
	public boolean getRepaintMap()
	{
		return repaintMap;
	}
	public boolean getPaintMap()
	{
		return paintMap;
	}
	
	public Map getMap()
	{
		return map;
	}
	
	
	
	
	public void paintComponent(Graphics g){
		//super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//System.out.println(MouseInfo.getPointerInfo().getLocation());
		
		
		 if(paintMap)
		 {
			 paintMap = false;
			 g2.drawImage(MainPictures.getImage(5), null,0,0);
			 g2.drawImage(playerImage, null, playerXLocation-15,playerYLocation-15);
			 g2.fillRect(1027, 0, 250, 50); 
			 g2.setColor(new Color(100,250,50));
			 g2.setFont(unitFont);
			 g2.drawString("Group tab", 1110, 20);
			 g2.fillRect(1027, 50, 250, 130); 
			 g2.setColor(new Color(250,25,25));
			 g2.drawString(time.getShortTime(), 1080, 70);
			 g2.fillRect(1027, 180, 250, 750); 
			 
			 
		 }
		 if(repaintMap)
		 {
			 
			
			
			 
			 if(playerXLocation < 45)
			 {
				 playerXLocation = 45;
			 }
			 else if(playerXLocation > 1230)
			 {
				 playerXLocation = 1230;
			 }
			 
			 if(playerYLocation < 45)
			 {
				 playerYLocation = 45;
			 }
			 else if(playerYLocation > 945)
			 {
				 playerYLocation = 945;
			 }
			 
			 //Setting playerView for x and y
			 if(playerXLocation < 1280-viewRange && playerXLocation > viewRange)
			 {
				 xStart = playerXLocation - viewRange;
				 xEnd = playerXLocation + viewRange;
			 }
			 else if(playerXLocation <= viewRange)
			 {
				 xStart = 0;
				 xEnd = playerXLocation + viewRange;
			 }
			 else
			 {
				 xStart = playerXLocation - viewRange;
				 xEnd = 1280;
			 }
			 
			 if(playerYLocation < 1024 - viewRange && playerYLocation > viewRange)
			 {
				 yStart = playerYLocation - viewRange;
				 yEnd = playerYLocation + viewRange;
			 }
			 else if(playerYLocation <= viewRange)
			 {
				 yStart = 0;
				 yEnd = playerYLocation + viewRange;
			 }
			 else
			 {
				 yStart = playerYLocation - viewRange;
				 yEnd = 1024;
			 }
			 
			 //Draw player Icon
			 
			 if(oldPlayerXLocation != playerXLocation || playerYLocation != oldPlayerYLocation)
			 {
			 tempImage = MainPictures.getImageClip(5,oldPlayerXLocation-15,oldPlayerYLocation-15,30,30);
			 g2.drawImage(tempImage, null, oldPlayerXLocation-15,oldPlayerYLocation-15);
			 
			 g2.drawImage(playerImage, null, playerXLocation-15,playerYLocation-15);
			 
			 oldPlayerXLocation = playerXLocation;
			 oldPlayerYLocation = playerYLocation;
			 }
			 //Draw in map elements inside player View
			 for(int i = 0; i < map.getSize();i++)
			 {
				 if(map.getMapInfo(i).getXMapLocation() > xStart && map.getMapInfo(i).getXMapLocation() < xEnd )
				 {
					 if(map.getMapInfo(i).getYMapLocation() > yStart && map.getMapInfo(i).getYMapLocation() < yEnd)
					 {
						 if(map.getRedraw(i) == true)
						 {
						 tempImage = MainPictures.getImageClip(5,map.getMapInfo(i).getXMapLocationOld()-15,map.getMapInfo(i).getYMapLocationOld()-15,30,30);
						 g2.drawImage(tempImage,null,map.getMapInfo(i).getXMapLocationOld()-15,map.getMapInfo(i).getYMapLocationOld()-15);
						 
						 g2.drawImage(map.getGroup(i).getImage(), null,map.getXLocation(i)-15,map.getYLocation(i)-15);
						 map.setRedraw(i, false);
						 }
					 }
				 }
				 if(map.getRedraw(i) == true)
				 {
					 tempImage = MainPictures.getImageClip(5,map.getMapInfo(i).getXMapLocationOld()-15,map.getMapInfo(i).getYMapLocationOld()-15,30,30);
					 g2.drawImage(tempImage,null,map.getMapInfo(i).getXMapLocationOld()-15,map.getMapInfo(i).getYMapLocationOld()-15); 
				 } 
			}
		 }
		 if(townTrigger)
		 {
			 g2.setColor(new Color(150,50,50));
			 g2.fill(new Rectangle2D.Double(64, 300 ,128,128));
		 }
		 if(stopTownTrigger)
		 {	
			 stopTownTrigger = false;
			 tempImage = MainPictures.getImageClip(5,64,300,128,128);
			 g2.drawImage(tempImage, null, 64,300);
		 }
		 if(paintTownInterface)
		 {
			 townTrigger = false;
			 g2.setColor(new Color(150,50,50));
			 g2.fill(new Rectangle2D.Double(100, 150 ,192,40));
			 g2.setColor(new Color(150,150,50));
			 g2.fill(new Rectangle2D.Double(100, 190 ,192,100));
			 g2.setColor(new Color(150,50,150));
			 g2.fill(new Rectangle2D.Double(100, 290 ,192,100));
			 g2.setColor(new Color(50,50,50));
			 g2.fill(new Rectangle2D.Double(100, 390 ,192,100));
			 
			 if(paintTownInterfaceShop)
			 {
				 int xPos = 292;
		    	 int yPos = 190;
		    	 
		    	 g2.setColor(new Color(165,42,42));
    			 g2.fill(new Rectangle2D.Double(342, 140,150,50));
    			 g2.setColor(new Color(255,255,255));
    			 g2.setFont(groupUnitFont);
    			 g2.drawString(Integer.toString(shop.getGold()), 352, 175);
    			 
		    		 for(int i = 0; i < 10; i++)
		    		 {
		    			 g2.setColor(new Color(165,42,42));
		    			 g2.fill(new Rectangle2D.Double(xPos, yPos,70,70));
		    			 g2.fill(new Rectangle2D.Double(xPos + 70, yPos,70,70));
		    			 g2.fill(new Rectangle2D.Double(xPos + 140, yPos,70,70));
		    			 g2.fill(new Rectangle2D.Double(xPos + 210, yPos,70,70));
		    			 g2.fill(new Rectangle2D.Double(xPos + 280, yPos,70,70));
		    			 g2.fill(new Rectangle2D.Double(xPos + 350, yPos,70,70));
		    			 g2.setColor(new Color(100,100,100));
		    			 g2.draw(new Rectangle2D.Double(xPos, yPos,70,70));
		    			 g2.draw(new Rectangle2D.Double(xPos + 70, yPos,70,70));
		    			 g2.draw(new Rectangle2D.Double(xPos + 140, yPos,70,70));
		    			 g2.draw(new Rectangle2D.Double(xPos + 210, yPos,70,70));
		    			 g2.draw(new Rectangle2D.Double(xPos + 280, yPos,70,70));
		    			 g2.draw(new Rectangle2D.Double(xPos + 350, yPos,70,70));
		    			 yPos = yPos+ 70;
		    
		    		 }
		    	for(int y = 0; y < 10; y++)
		    	{
		    		for(int x = 0; x < 6; x++)
		    		{
		    			
		    			if(shop.getInventory().getItem(x,y).getItemHere() == true)
			    		{
			    			
			    			yPos = 190 + ((y)*70);
				    		xPos = 292 + ((x)*70);
			    			g2.drawImage(shop.getInventory().getItem(x,y).getItemPicture(),null,xPos + 3,yPos+ 3); 
			    		}
		    		}
		    	}
		    	
		    		xPos = 722;
		    		yPos = 190;
		    		
		    		 g2.setColor(new Color(165,42,42));
	    			 g2.fill(new Rectangle2D.Double(772, 140,150,50));
	    			 g2.setColor(new Color(255,255,255));
	    			 g2.setFont(groupUnitFont);
	    			 g2.drawString(Integer.toString(Group.getGold()), 782, 175);
	    			 
		    		 for(int i = 0; i < 10; i++)
		    		 {
		    			 g2.setColor(new Color(165,42,42));
		    			 g2.fill(new Rectangle2D.Double(xPos, yPos,70,70));
		    			 g2.fill(new Rectangle2D.Double(xPos + 70, yPos,70,70));
		    			 g2.fill(new Rectangle2D.Double(xPos + 140, yPos,70,70));
		    			 g2.fill(new Rectangle2D.Double(xPos + 210, yPos,70,70));
		    			 g2.fill(new Rectangle2D.Double(xPos + 280, yPos,70,70));
		    			 g2.fill(new Rectangle2D.Double(xPos + 350, yPos,70,70));
		    			 g2.setColor(new Color(100,100,100));
		    			 g2.draw(new Rectangle2D.Double(xPos, yPos,70,70));
		    			 g2.draw(new Rectangle2D.Double(xPos + 70, yPos,70,70));
		    			 g2.draw(new Rectangle2D.Double(xPos + 140, yPos,70,70));
		    			 g2.draw(new Rectangle2D.Double(xPos + 210, yPos,70,70));
		    			 g2.draw(new Rectangle2D.Double(xPos + 280, yPos,70,70));
		    			 g2.draw(new Rectangle2D.Double(xPos + 350, yPos,70,70));
		    			 yPos = yPos+ 70;
		    
		    		 }
		    	for(int y = 0; y < 10; y++)
		    	{
		    		for(int x = 0; x < 6; x++)
		    		{
		    			
		    			if(Group.getInventory().getItem(x,y).getItemHere() == true)
			    		{
			    			
			    			yPos = 190 + ((y)*70);
				    		xPos = 722 + ((x)*70);
			    			g2.drawImage(Group.getInventory().getItem(x,y).getItemPicture(),null,xPos + 3,yPos+ 3); 
			    		}
		    		}
		    	}
			 }
			 else if(paintTownInterfaceHire)
			 {
				 int xPos = 292;
		    	 int yPos = 190;
		    	 hireableAmount = -1;
		    	 
				 g2.setColor(new Color(0,0,255));
		    	 g2.fill(new Rectangle2D.Double(xPos, yPos,200,700));
		    	 
		    	 for(int i = 0; hireable.getAll().size() > i ;i++)
		    	 {
		    		 hireableAmount++;
		    		 g2.setColor(new Color(255,0,0));
			    	 g2.fill(new Rectangle2D.Double(xPos, yPos,200,69));
			    	 g2.setColor(new Color(0,255,0));
			    	 g2.setFont(unitFont);
			    	 g2.drawString(hireable.getUnit(i).getUnitName(), xPos + 20, yPos + 20);
			    	 yPos += 70; 
		    	 }
		    	 
		    	 if(hireUnitSelected >= 0)
		    	 {
		    	 g2.fill(new Rectangle2D.Double(xPos, 190+(70*(hireUnitSelected)),200,69));
		    	 }
		    	 yPos = 190;
		    	 
		    	 g2.setColor(new Color(150,50,50));
		    	 g2.fill(new Rectangle2D.Double(492, 190,300,700));
		    	 g2.fill(new Rectangle2D.Double(792, 190,100,40));		
		    	 g2.setColor(new Color(200,250,25));
		    	 g2.drawString(Group.getGold() + "", 800, 210);
		    	 if(hireUnitSelected >= 0)
		    	 {
		    	 g2.setColor(new Color(0,0,255));
		    	 g2.setFont(groupUnitFont);
		    	 g2.drawString(hireable.getUnit(hireUnitSelected).getUnitName(), 542, 240);
		    	 g2.setFont(levelFont);
		    	 g2.drawString("Level " + Integer.toString(hireable.getUnit(hireUnitSelected).getLevel()), 517, 268);
		    	 g2.setColor(new Color(0,255,255));
		    	 g2.fill(new Rectangle2D.Double(607, 265 ,70,70));
		    	 if(hireable.getUnit(hireUnitSelected).getItem(1).getItemHere() == true)
		    	 {
		    		 g2.drawImage(hireable.getUnit(hireUnitSelected).getItem(1).getItemPicture(),null,610,268);
		    	 }
		    	 g2.fill(new Rectangle2D.Double(507, 315,70,70));
		    	 if(hireable.getUnit(hireUnitSelected).getItem(6).getItemHere() == true)
		    	 {
		    		 g2.drawImage(hireable.getUnit(hireUnitSelected).getItem(6).getItemPicture(),null,510,318);
		    	 }
		    	 g2.fill(new Rectangle2D.Double(702, 315,70,70));
		    	 if(hireable.getUnit(hireUnitSelected).getItem(7).getItemHere() == true)
		    	 {
		    		 g2.drawImage(hireable.getUnit(hireUnitSelected).getItem(7).getItemPicture(),null,705,318);
		    	 }
		    	 g2.fill(new Rectangle2D.Double(607, 365,70,70));
		    	 if(hireable.getUnit(hireUnitSelected).getItem(2).getItemHere() == true)
		    	 {
		    		 g2.drawImage(hireable.getUnit(hireUnitSelected).getItem(2).getItemPicture(),null,610,368);
		    	 }
		    	 g2.fill(new Rectangle2D.Double(507, 415,70,70));
		    	 if(hireable.getUnit(hireUnitSelected).getItem(5).getItemHere() == true)
		    	 {
		    		 g2.drawImage(hireable.getUnit(hireUnitSelected).getItem(5).getItemPicture(),null,510,418);
		    	 }
		    	 g2.fill(new Rectangle2D.Double(702, 415,70,70));
		    	 if(hireable.getUnit(hireUnitSelected).getItem(4).getItemHere() == true)
		    	 {
		    		 g2.drawImage(hireable.getUnit(hireUnitSelected).getItem(4).getItemPicture(),null,705,418);
		    	 }
		    	 g2.fill(new Rectangle2D.Double(607, 465,70,70));
		    	 if(hireable.getUnit(hireUnitSelected).getItem(3).getItemHere() == true)
		    	 {
		    		 g2.drawImage(hireable.getUnit(hireUnitSelected).getItem(3).getItemPicture(),null,610,468);
		    	 }
		    	 g2.fill(new Rectangle2D.Double(607, 565,70,70));
		    	 g2.setColor(new Color(0,0,255));
		    	 g2.setFont(unitFont);
		    	 g2.drawString("Health", 512, 715);
		    	 g2.drawString(Double.toString(hireable.getUnit(hireUnitSelected).getHealth()), 512, 740);
		    	 g2.drawString("/" + Double.toString(hireable.getUnit(hireUnitSelected).getMaxHealth()), 552, 740);
		    	 g2.drawString("Moral", 662, 715);
		    	 g2.drawString(Double.toString(hireable.getUnit(hireUnitSelected).getMoral()), 662, 740);
		    	 g2.drawString("Attack", 512, 765);
		    	 g2.drawString(Double.toString(hireable.getUnit(hireUnitSelected).getBaseAttack()) + " /", 512, 790);
		    	 g2.drawString(Double.toString(hireable.getUnit(hireUnitSelected).getItemAttackChange()), 562, 790);
		    	 g2.drawString("Speed", 662, 765);
		    	 g2.drawString(Double.toString(hireable.getUnit(hireUnitSelected).getSpeed()) + " /", 662, 790);
		    	 g2.drawString(Double.toString(hireable.getUnit(hireUnitSelected).getItemSpeedChange()), 712, 790);
		    	 g2.drawString("Defense", 512, 815);
		    	 g2.drawString(Double.toString(hireable.getUnit(hireUnitSelected).getBaseDefense()) + " /", 512, 840);
		    	 g2.drawString(Double.toString(hireable.getUnit(hireUnitSelected).getItemDefenseChange()), 562, 840);
		    	 
		    	 g2.fill(new Rectangle2D.Double(662, 840,100,40));		
		    	 g2.setColor(new Color(200,250,25));
		    	 g2.drawString(hireable.getUnit(hireUnitSelected).getCostToBuy() + "", 692, 865);
		    	 }
			 }
			 else if(paintTownInterfaceQuest)
			 {
				 int xPos = 292;
		    	 int yPos = 190;
		    	 
				 g2.setColor(new Color(0,0,255));
		    	 g2.fill(new Rectangle2D.Double(xPos, yPos,200,700));
		    	 
		    	 for(int i = 0; quests.getAll().size() > i ;i++)
		    	 {
		    		 g2.setColor(new Color(255,0,0));
			    	 g2.fill(new Rectangle2D.Double(xPos, yPos,200,69));
			    	 g2.setColor(new Color(0,255,0));
			    	 g2.setFont(unitFont);
			    	 g2.drawString(quests.getQuest(i).getName(), xPos + 20, yPos + 20);
			    	 yPos += 70; 
		    	 }
		    	 if(questSelected >= 0)
		    	 {
		    	 g2.fill(new Rectangle2D.Double(xPos, 190+(70*(questSelected)),200,69));
		    	 }
		    	 
			 }
		 }
	     if(repaintInventory)
	     {
	    	
	    	 int xPos = 777;
	    	 int yPos = 100;
	    		 for(int i = 0; i < 10; i++)
	    		 {
	    			 g2.setColor(new Color(165,42,42));
	    			 g2.fill(new Rectangle2D.Double(xPos, yPos,70,70));
	    			 g2.fill(new Rectangle2D.Double(xPos + 70, yPos,70,70));
	    			 g2.fill(new Rectangle2D.Double(xPos + 140, yPos,70,70));
	    			 g2.fill(new Rectangle2D.Double(xPos + 210, yPos,70,70));
	    			 g2.fill(new Rectangle2D.Double(xPos + 280, yPos,70,70));
	    			 g2.fill(new Rectangle2D.Double(xPos + 350, yPos,70,70));
	    			 g2.setColor(new Color(100,100,100));
	    			 g2.draw(new Rectangle2D.Double(xPos, yPos,70,70));
	    			 g2.draw(new Rectangle2D.Double(xPos + 70, yPos,70,70));
	    			 g2.draw(new Rectangle2D.Double(xPos + 140, yPos,70,70));
	    			 g2.draw(new Rectangle2D.Double(xPos + 210, yPos,70,70));
	    			 g2.draw(new Rectangle2D.Double(xPos + 280, yPos,70,70));
	    			 g2.draw(new Rectangle2D.Double(xPos + 350, yPos,70,70));
	    			 yPos = yPos+ 70;
	    
	    		 }
	    	for(int y = 0; y < 10; y++)
	    	{
	    		for(int x = 0; x < 6; x++)
	    		{
	    			
	    			if(Group.getInventory().getItem(x,y).getItemHere() == true)
		    		{
		    			
		    			yPos = 100 + ((y)*70);
			    		xPos = 777 + ((x)*70);
		    			g2.drawImage(Group.getInventory().getItem(x,y).getItemPicture(),null,xPos + 3,yPos+ 3); 
		    		}
	    		}
	    	}
	    	 
	    	 
	    	 
	     }
	     if(repaintUnit)
	     {
	    	 
	    	 g2.setColor(new Color(150,50,50));
	    	 g2.fill(new Rectangle2D.Double(77, 100,300,700));
	    	 g2.setColor(new Color(0,0,255));
	    	 g2.setFont(groupUnitFont);
	    	 g2.drawString(Group.getUnitName(groupUnitSelected), 127, 150);
	    	 g2.setFont(levelFont);
	    	 g2.drawString("Level " + Integer.toString(Group.getUnitLevel(groupUnitSelected)), 102, 178);
	         g2.fill(new Rectangle2D.Double(327, 125 ,25,25));
	    	 g2.setColor(new Color(0,255,255));
	    	 g2.fill(new Rectangle2D.Double(192, 175 ,70,70));
	    	 if(Group.getUnitItem(groupUnitSelected,1).getItemHere() == true)
	    	 {
	    		 g2.drawImage(Group.getUnitItem(groupUnitSelected, 1).getItemPicture(),null,195,178);
	    	 }
	    	 g2.fill(new Rectangle2D.Double(92, 225,70,70));
	    	 if(Group.getUnitItem(groupUnitSelected,6).getItemHere() == true)
	    	 {
	    		 g2.drawImage(Group.getUnitItem(groupUnitSelected, 6).getItemPicture(),null,95,228);
	    	 }
	    	 g2.fill(new Rectangle2D.Double(287, 225,70,70));
	    	 if(Group.getUnitItem(groupUnitSelected,7).getItemHere() == true)
	    	 {
	    		 g2.drawImage(Group.getUnitItem(groupUnitSelected, 7).getItemPicture(),null,290,228);
	    	 }
	    	 g2.fill(new Rectangle2D.Double(192, 275,70,70));
	    	 if(Group.getUnitItem(groupUnitSelected,2).getItemHere() == true)
	    	 {
	    		 g2.drawImage(Group.getUnitItem(groupUnitSelected, 2).getItemPicture(),null,195,278);
	    	 }
	    	 g2.fill(new Rectangle2D.Double(92, 325,70,70));
	    	 if(Group.getUnitItem(groupUnitSelected,5).getItemHere() == true)
	    	 {
	    		 g2.drawImage(Group.getUnitItem(groupUnitSelected, 5).getItemPicture(),null,95,328);
	    	 }
	    	 g2.fill(new Rectangle2D.Double(287, 325,70,70));
	    	 if(Group.getUnitItem(groupUnitSelected,4).getItemHere() == true)
	    	 {
	    		 g2.drawImage(Group.getUnitItem(groupUnitSelected, 4).getItemPicture(),null,290,328);
	    	 }
	    	 g2.fill(new Rectangle2D.Double(192, 375,70,70));
	    	 if(Group.getUnitItem(groupUnitSelected,3).getItemHere() == true)
	    	 {
	    		 g2.drawImage(Group.getUnitItem(groupUnitSelected, 3).getItemPicture(),null,195,378);
	    	 }
	    	 g2.fill(new Rectangle2D.Double(192, 475,70,70));
	    	 g2.setColor(new Color(0,0,255));
	    	 g2.setFont(unitFont);
	    	 g2.drawString("Health", 97, 625);
	    	 g2.drawString(Double.toString(Group.getUnitHealth(groupUnitSelected)), 97, 650);
	    	 g2.drawString("/" + Double.toString(Group.getUnitMaxHealth(groupUnitSelected)), 137, 650);
	    	 g2.drawString("Moral", 247, 625);
	    	 g2.drawString(Double.toString(Group.getUnitMoral(groupUnitSelected))+" :", 247, 650);
	    	 if(Group.getUnitMoral(groupUnitSelected) < (Group.getTotalGroupMoral()/(Group.getSize() - 1)))
	    	 	{
	    		 g2.drawString("Lower",297,650);
	    	 	}
	    	 else if(Group.getUnitMoral(groupUnitSelected) == (Group.getTotalGroupMoral()/(Group.getSize() - 1)))
	    	 	{
	    		 g2.drawString("Average",297,650);
	    	 	}
	    	 else
	    	 {
	    		
	    		 g2.drawString("Higher",297,650);
	    	 }
	    	 g2.drawString("Attack", 97, 675);
	    	 g2.drawString(Double.toString(Group.getUnitAttack(groupUnitSelected)) + " /", 97, 700);
	    	 g2.drawString(Double.toString(Group.getUnitTotalAttack(groupUnitSelected)), 147, 700);
	    	 g2.drawString("Speed", 247, 675);
	    	 g2.drawString(Double.toString(Group.getUnitSpeed(groupUnitSelected)) + " /", 247, 700);
	    	 g2.drawString(Double.toString(Group.getUnitTotalSpeed(groupUnitSelected)), 297, 700);
	    	 g2.drawString("Defense", 97, 725);
	    	 g2.drawString(Double.toString(Group.getUnitDefense(groupUnitSelected)) + " /", 97, 750);
	    	 g2.drawString(Double.toString(Group.getUnitTotalDefense(groupUnitSelected)), 147, 750);
	    	 g2.drawString("Experience", 247, 725);
	    	 g2.drawString(Double.toString(Group.getUnitExperience(groupUnitSelected)) + " /", 247, 750);
	    	 g2.drawString(Double.toString(Group.getUnitNeededExperience(groupUnitSelected)), 297, 750);
	    
	    	 if(repaintLevelAdd)
	    	 {
	    		 if(attributesLeft > 0)
	    		 {
	    			 g2.setColor(new Color(0,0,255));
	    			 g2.fill(new Rectangle2D.Double(212, 765,30,30));
	    			 g2.fill(new Rectangle2D.Double(87, 765,80,30));
	    			 g2.setColor(new Color(255,0,0));
		    		 g2.drawString("+", 217, 787);
		    		 g2.drawString(Integer.toString(attributesLeft), 229, 787);
		    		 g2.drawString("Cancel", 97, 787);
	    			 
	    		 }
	    		 else
	    		 {
	    			 g2.setColor(new Color(0,0,255));
	    			 g2.fill(new Rectangle2D.Double(212, 765,30,30));
	    			 g2.fill(new Rectangle2D.Double(292, 765,80,30));
	    			 g2.fill(new Rectangle2D.Double(87, 765,80,30));
	    			 g2.setColor(new Color(255,0,0));
		    		 g2.drawString("0", 222, 787);
		    		 g2.drawString("Accept", 302, 787);
		    		 g2.drawString("Cancel", 97, 787);
	    			 
	    		 }
	    		 g2.setColor(new Color(0,0,255));
	    		 g2.fill(new Rectangle2D.Double(162, 660,20,20));
	    		 g2.fill(new Rectangle2D.Double(307, 660,20,20));
	    		 g2.fill(new Rectangle2D.Double(307, 610,20,20));
	    		 g2.fill(new Rectangle2D.Double(162, 610,20,20));
	    		 g2.fill(new Rectangle2D.Double(172, 710,20,20));
	    		 g2.setColor(new Color(255,0,0));
	    		 g2.drawString(Integer.toString(addAttack),168,677);
	    		 g2.drawString(Integer.toString(addSpeed),313,677);
	    		 g2.drawString(Integer.toString(addMoral),313,627);
	    		 g2.drawString(Integer.toString(addHealth),168,627);
	    		 g2.drawString(Integer.toString(addDefense),178,727);
	    	 }
	    	 else
	    	 {
	    		 
	    		 g2.fill(new Rectangle2D.Double(212, 765,30,30));
	    		 g2.setColor(new Color(255,0,0));
	    		 g2.drawString(Integer.toString(Group.getUnitSavedLevels(groupUnitSelected)), 222, 787);
	    	 }
	     }
	     if(repaintGroup)
	     {
	    	 
	    	 g2.setColor(new Color(0,0,255));
	    	 g2.fill(new Rectangle2D.Double(377, 100,400,700));
	    	 int xPos = 377;
	    	 int yPos = 100;
	    
	    	 for(int i = 1; i < Group.getSize();i++)
	    	 {
	    		 Group.checkUnitLevel(i);
	    	 }
	    	 if(Group.getSize() < 12)
	    	 {
	    	 for(int i = 0; i < Group.getSize() - 1;i++)
	    	 {
	    		
	    		 g2.setColor(new Color(255,0,0));
		    	 g2.fill(new Rectangle2D.Double(xPos, yPos,200,69));
		    	 g2.setColor(new Color(0,255,0));
		    	 g2.setFont(unitFont);
		    	 g2.drawString(Group.getUnitName(i + 1), xPos + 20, yPos + 20);
		    	 yPos += 70; 
	    	 }
	    	 for(int i = Group.getSize()-1; i < 10;i++)
	    	 {
	    		
	    		 g2.setColor(new Color(255,0,0));
		    	 g2.fill(new Rectangle2D.Double(xPos, yPos,200,69));
		    	 yPos += 70; 
	    	 }
	    	 yPos = 100;
	    	 for(int i = 0; i < 10;i++)
	    	 {
	    		
	    		 g2.setColor(new Color(255,0,0));
		    	 g2.fill(new Rectangle2D.Double(xPos+200, yPos,200,69));
		    	 yPos += 70; 
	    	 }
	    	 }
	    	 else
	    	 {
	    	 	for(int i = 0; i < 10;i++)
		    	 {
		    		
		    		 g2.setColor(new Color(255,0,0));
			    	 g2.fill(new Rectangle2D.Double(xPos, yPos,200,69));
			    	 g2.setColor(new Color(0,255,0));
			    	 g2.setFont(unitFont);
			    	 g2.drawString(Group.getUnitName(i + 1), xPos + 20, yPos + 20);

			    	 yPos += 70; 
		    	 }
	    		 
	    		 yPos = 100;
	    		 for(int i = 11; i < Group.getSize();i++)
		    	 {
		    		
		    		 g2.setColor(new Color(255,0,0));
			    	 g2.fill(new Rectangle2D.Double(200+xPos, yPos,200,69));
			    	 g2.setColor(new Color(0,255,0));
			    	 g2.setFont(unitFont);
			    	 g2.drawString(Group.getUnitName(i), xPos + 220, yPos + 20);

			    	 yPos += 70; 
		    	 }
	    		 for(int i = Group.getSize()-1; i < 20;i++)
		    	 {
		    		
		    		 g2.setColor(new Color(255,0,0));
			    	 g2.fill(new Rectangle2D.Double(200+xPos, yPos,200,69));
			    	 yPos += 70; 
		    	 }
	    		 
	    	 }
	    	 g2.setColor(new Color(00,255,0));
	    	 if(groupUnitSelected > 10)
	    	 {
	    		 
	    		 
	    		 g2.fill(new Rectangle2D.Double(xPos+200, 100+(70*(groupUnitSelected-11)),200,69));
	    	 }
	    	 else
	    	 {
	    		 g2.fill(new Rectangle2D.Double(xPos, 100+(70*(groupUnitSelected-1)),200,69));
	    	 }
	    	 g2.setColor(new Color(0,255,0));
	    	 g2.draw(new Rectangle2D.Double(376, 99,401,701));
	    	 g2.draw(new Rectangle2D.Double(578, 99,1,701));
	    	 
	    	 
	     }
	     if(nameChange)
		 {
			 g2.setColor(new Color(150,50,50));
			 g2.fill(new Rectangle2D.Double(80, 110 ,280,51));
			 g2.setColor(new Color(0,255,0));
			 g2.setFont(groupUnitFont);
			 
			 flashCount++;
			 
			 if(flashCount > 20)
			 {
				 if(flashCount > 40)
				 {
					 flashCount = 0;
				 }
				 g2.drawString(newName + "|", 80, 150);
			 }
			 else
			 {
				 g2.drawString(newName, 80, 150);
			 }
		 }
	     if(mouseDragItem)
	     {
	    
	    	 g2.drawImage(draggedItem.getItemPicture(),null,(int)(MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX() - 32),(int)(MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY() - 32)); 	 
	     }
	        
	        
	}
	
	
	
}

