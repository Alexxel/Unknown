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
		if((click.getX() > 700 && click.getX() < 1125) && (click.getY() > 130 && click.getY() < 830))
		{
			int xC = 0;
			int yC = 0;
			
			xC = (int) (click.getX() - 700)/70;
			yC = (int) (click.getY() - 130)/70;
			
			if(gameWindow.getInventory().getItem(xC, yC).getItemHere() == true)
			{
			if((release.getX() > 700 && release.getX() < 1125) && (release.getY() > 130 && release.getY() < 830))
			{
				int xR = 0;
				int yR = 0;
				
				xR = (int) (release.getX() - 700)/70;
				yR = (int) (release.getY() - 130)/70;
				
				
				gameWindow.getInventory().itemSwap(xC,yC,xR,yR);
				gameWindow.setRepaintInventory(true);
				
			}
			else if((release.getX() > 115 && release.getX() < 185) && (release.getY() > 200 && release.getY() < 280))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 1).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),1);
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().setItem(tempItem, xC, yC);
					
				}
				else
				{
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().removeItem(xC, yC);
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((release.getX() > 115 && release.getX() < 185) && (release.getY() > 300 && release.getY() < 380))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 2).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),2);
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().setItem(tempItem, xC, yC);
					
				}
				else
				{
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().removeItem(xC, yC);
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((release.getX() > 115 && release.getX() < 185) && (release.getY() > 400  && release.getY() < 480))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 3).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),3);
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().setItem(tempItem, xC, yC);
					
				}
				else
				{
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().removeItem(xC, yC);
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((release.getX() > 210 && release.getX() < 280) && (release.getY() > 350 && release.getY() < 425))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 4).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),4);
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().setItem(tempItem, xC, yC);
					
				}
				else
				{
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().removeItem(xC, yC);
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((release.getX() > 15 && release.getX() < 90) && (release.getY() > 350 && release.getY() < 425))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 5).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),5);
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().setItem(tempItem, xC, yC);
					
				}
				else
				{
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().removeItem(xC, yC);
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((release.getX() > 210 && release.getX() < 280) && (release.getY() > 250 && release.getY() < 325))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 7).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),7);
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().setItem(tempItem, xC, yC);
					
				}
				else
				{
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().removeItem(xC, yC);
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((release.getX() > 15 && release.getX() < 90) && (release.getY() > 250 && release.getY() < 325))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 6).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),6);
					gameWindow.getGroup().addItem(gameWindow.getGroupUnitSelected(), gameWindow.getInventory().getItem(xC, yC));
					gameWindow.getInventory().setItem(tempItem, xC, yC);
					
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
		else if(((release.getX() > 700 && release.getX() < 1125) && (release.getY() > 130 && release.getY() < 830)) && ((click.getX() > 10 && click.getX() < 300) && (click.getY() > 200 && click.getY() < 600)))
		{
			
			int xR = 0;
			int yR = 0;
			
			xR = (int) (release.getX() - 700)/70;
			yR = (int) (release.getY() - 130)/70;
			
			if((click.getX() > 115 && click.getX() < 185) && (click.getY() > 200 && click.getY() < 280))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 1).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),1);
					gameWindow.getInventory().setItem(tempItem, xR, yR);
					gameWindow.getGroup().removeItem(gameWindow.getGroupUnitSelected(), 1);
					
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((click.getX() > 115 && click.getX() < 185) && (click.getY() > 300 && click.getY() < 380))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 2).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),2);
					gameWindow.getInventory().setItem(tempItem, xR, yR);
					gameWindow.getGroup().removeItem(gameWindow.getGroupUnitSelected(), 2);
					
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((click.getX() > 115 && click.getX() < 185) && (click.getY() > 400  && click.getY() < 480))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 3).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),3);
					gameWindow.getInventory().setItem(tempItem, xR, yR);
					gameWindow.getGroup().removeItem(gameWindow.getGroupUnitSelected(), 3);
					
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((click.getX() > 210 && click.getX() < 280) && (click.getY() > 350 && click.getY() < 425))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 4).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),4);
					gameWindow.getInventory().setItem(tempItem, xR, yR);
					gameWindow.getGroup().removeItem(gameWindow.getGroupUnitSelected(), 4);
				}
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((click.getX() > 15 && click.getX() < 90) && (click.getY() > 350 && click.getY() < 425))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 5).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),5);
					gameWindow.getInventory().setItem(tempItem, xR, yR);
					gameWindow.getGroup().removeItem(gameWindow.getGroupUnitSelected(), 5);
				}
				
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((click.getX() > 210 && click.getX() < 280) && (click.getY() > 250 && click.getY() < 325))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 7).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),7);
					gameWindow.getInventory().setItem(tempItem, xR, yR);
					gameWindow.getGroup().removeItem(gameWindow.getGroupUnitSelected(), 7);
					
				}
				
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
			}
			else if((click.getX() > 15 && click.getX() < 90) && (click.getY() > 250 && click.getY() < 325))
			{
				
				if(gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(), 6).getItemHere() == true)
				{
					tempItem = gameWindow.getGroup().getUnitItem(gameWindow.getGroupUnitSelected(),6);
					gameWindow.getInventory().setItem(tempItem, xR, yR);
					gameWindow.getGroup().removeItem(gameWindow.getGroupUnitSelected(), 6);
								}
				
				gameWindow.setRepaintUnit(true);
				gameWindow.setRepaintInventory(true);
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
		}
	}
	else if(showGroup)
	{
	
	if((m.getPoint().getX() > 250 && m.getPoint().getX() < 275)  && (m.getPoint().getY() > 160 && m.getPoint().getY() < 185 ))
	{
		System.out.println("Got click on name change");
		gameWindow.setNameChange(true);
		oldName = gameWindow.getGroup().getUnitName(gameWindow.getGroupUnitSelected());
		newName = gameWindow.getGroup().getUnitName(gameWindow.getGroupUnitSelected());
		gameWindow.setNewName(newName);
		newNameActive = true;
	}
	
	else if((m.getPoint().getX() > 300 && m.getPoint().getX() < 700)  && (m.getPoint().getY() > 135 && m.getPoint().getY() < 835 ))
	{
		System.out.println("Got mouse click on groupTab");
		int x=0;
		int y=0;
		if(m.getPoint().getX() < 500)
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
	else if((m.getPoint().getX() > 135 && m.getPoint().getX() < 165)  && (m.getPoint().getY() > 800 && m.getPoint().getY() < 835 ))
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
		if((m.getPoint().getX() > 10 && m.getPoint().getX() < 90)  && (m.getPoint().getY() > 795 && m.getPoint().getY() < 825 ))
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
		else if(((m.getPoint().getX() > 215 && m.getPoint().getX() < 295)  && (m.getPoint().getY() > 795 && m.getPoint().getY() < 825 )) &&(gameWindow.getAttributesLeft() == 0))
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
			if((m.getPoint().getX() > 85 && m.getPoint().getX() < 105)  && (m.getPoint().getY() > 695 && m.getPoint().getY() < 730 ))
			{
				System.out.println("Click on attack add");
				gameWindow.setAddAttack(gameWindow.getAddAttack() + 1);
				gameWindow.setAttributesLeft(gameWindow.getAttributesLeft() - 1);
				gameWindow.setRepaintUnit(true);
				
			}
			else if((m.getPoint().getX() > 85 && m.getPoint().getX() < 105)  && (m.getPoint().getY() > 645 && m.getPoint().getY() < 665 ))
			{
				gameWindow.setAddHealth(gameWindow.getAddHealth() + 1);
				gameWindow.setAttributesLeft(gameWindow.getAttributesLeft() - 1);
				gameWindow.setRepaintUnit(true);
				
			}
			else if((m.getPoint().getX() > 95 && m.getPoint().getX() < 115)  && (m.getPoint().getY() > 745 && m.getPoint().getY() < 765 ))
			{
				gameWindow.setAddDefense(gameWindow.getAddDefense() + 1);
				gameWindow.setAttributesLeft(gameWindow.getAttributesLeft() - 1);
				gameWindow.setRepaintUnit(true);
				
			}
			else if((m.getPoint().getX() > 230 && m.getPoint().getX() < 250)  && (m.getPoint().getY() > 645 && m.getPoint().getY() < 665 ))
			{
				gameWindow.setAddMoral(gameWindow.getAddMoral() + 1);
				gameWindow.setAttributesLeft(gameWindow.getAttributesLeft() - 1);
				gameWindow.setRepaintUnit(true);
				
			}
			else if((m.getPoint().getX() > 230 && m.getPoint().getX() < 250)  && (m.getPoint().getY() > 695 && m.getPoint().getY() < 725 ))
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
	if((e.getX() > 700 && e.getX() < 1125) && (e.getY() > 130 && e.getY() < 830))
	{
		int xC = 0;
		int yC = 0;
		
		xC = (int) (e.getX() - 700)/70;
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
	else if((e.getX() > 115 && e.getX() < 185) && (e.getY() > 200 && e.getY() < 280))
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
	else if((e.getX() > 115 && e.getX() < 185) && (e.getY() > 300 && e.getY() < 380))
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
	else if((e.getX() > 115 && e.getX() < 185) && (e.getY() > 400  && e.getY() < 480))
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
	else if((e.getX() > 210 && e.getX() < 280) && (e.getY() > 350 && e.getY() < 425))
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
	else if((e.getX() > 15 && e.getX() < 90) && (e.getY() > 350 && e.getY() < 425))
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
	else if((e.getX() > 210 && e.getX() < 280) && (e.getY() > 250 && e.getY() < 325))
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
	else if((e.getX() > 15 && e.getX() < 90) && (e.getY() > 250 && e.getY() < 325))
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
	public void setInventory(Inventory i)
	{
		gameWindow.setInventory(i);
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
	int attributesLeft;
	int addDefense;
	int addAttack;
	int addSpeed;
	int addMoral;
	int addHealth;
	int viewRange;
	int flashCount;
	int itemLocation;
	
	
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
	
	Group Group;
	Inventory inventory;
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
		attributesLeft = 0;
		addDefense = 0;
		addAttack = 0;
		addSpeed = 0;
		addMoral = 0;
		addHealth = 0;
		viewRange = 50;
		flashCount = 0;
		itemLocation = 0;
		
		
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
		Group.add(new Unit(10,10, 10, 10, 10, 10,10,5, "Test5"));
		Group.add(new Unit(10,10, 10, 10, 10, 10,10,1, "Test6"));
		Group.add(new Unit(10,10, 10, 10, 10, 10,10,7, "Test7"));
		Group.add(new Unit(10,10, 10, 10, 10, 10,10,8, "Test8"));
		Group.add(new Unit(10,10, 10, 10, 10, 10,10,9, "Test9"));
		Group.add(new Unit(10,10, 10, 10, 10, 10,10,5, "Test10"));
		Group.add(new Unit(10,10, 10, 10, 10, 10,10,6, "Test11"));
		Group.add(new Unit(10,10, 10, 10, 10, 10,10,7, "Test12"));
		Group.add(new Unit(10,10, 10, 10, 10, 10,10,5, "Test13"));
		Group.add(new Unit(10,10, 10, 10, 10, 10,10,2, "Test14"));
		Group.add(new Unit(10,10, 10, 10, 10, 10,10,3, "Test15"));
		
		
		Group.addItem(1, new Item(5,20,-2, .02,.2,2,10,4,"Images/BasicSword.png","Temp"));
		Group.addItem(1, new Item(5,20,-2, .02,.2,2,10,5,"Images/DragonShield.png","Temp"));
		
		Group.setUnitSavedLevels(5, 2);
		Group.setUnitSavedLevels(4, 1);
		
		inventory = new Inventory();
		for(int y = 0; y < 10; y++)
		{
			for(int x = 0; x<6;x++)
			{
				inventory.addItem(new Item(),x, y);
			}
		}
		inventory.setItem(new Item(5,5,5, 2,2,2,10,1,"Images/BasicSword.png","Basic Sword"), 5,0);
		inventory.setItem(new Item(5,5,5, 2,2,2,10,1,"Images/DragonShield.png","Dragon Shield"),1,2);
		
		
		
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
		return inventory;
	}
	public void setInventory(Inventory i)
	{
		inventory = i;
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
			 
			 
		 }
		 if(nameChange)
		 {
			 
			
			 g2.setColor(new Color(150,50,50));
			 g2.fill(new Rectangle2D.Double(0, 110 ,280,51));
			 g2.setColor(new Color(0,255,0));
			 g2.setFont(groupUnitFont);
			 
			 flashCount++;
			 
			 if(flashCount > 20)
			 {
				 if(flashCount > 40)
				 {
					 flashCount = 0;
				 }
				 g2.drawString(newName + "|", 0, 150);
			 }
			 else
			 {
				 g2.drawString(newName, 0, 150);
			 }
		 }
		
	     
	     if(repaintInventory)
	     {
	    	
	    	 int xPos = 700;
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
	    			
	    			if(inventory.getItem(x,y).getItemHere() == true)
		    		{
		    			
		    			yPos = 100 + ((y)*70);
			    		xPos = 700 + ((x)*70);
		    			g2.drawImage(inventory.getItem(x,y).getItemPicture(),null,xPos + 3,yPos+ 3); 
		    		}
	    		}
	    	}
	    	 
	    	 
	    	 
	     }
	     if(repaintUnit)
	     {
	    	 
	    	 g2.setColor(new Color(150,50,50));
	    	 g2.fill(new Rectangle2D.Double(0, 100,300,700));
	    	 g2.setColor(new Color(0,0,255));
	    	 g2.setFont(groupUnitFont);
	    	 g2.drawString(Group.getUnitName(groupUnitSelected), 50, 150);
	    	 g2.setFont(levelFont);
	    	 g2.drawString("Level " + Integer.toString(Group.getUnitLevel(groupUnitSelected)), 25, 178);
	         g2.fill(new Rectangle2D.Double(250, 125 ,25,25));
	    	 g2.setColor(new Color(0,255,255));
	    	 g2.fill(new Rectangle2D.Double(115, 175 ,70,70));
	    	 if(Group.getUnitItem(groupUnitSelected,1).getItemHere() == true)
	    	 {
	    		 g2.drawImage(Group.getUnitItem(groupUnitSelected, 1).getItemPicture(),null,118,178);
	    	 }
	    	 g2.fill(new Rectangle2D.Double(15, 225,70,70));
	    	 if(Group.getUnitItem(groupUnitSelected,6).getItemHere() == true)
	    	 {
	    		 g2.drawImage(Group.getUnitItem(groupUnitSelected, 6).getItemPicture(),null,18,228);
	    	 }
	    	 g2.fill(new Rectangle2D.Double(210, 225,70,70));
	    	 if(Group.getUnitItem(groupUnitSelected,7).getItemHere() == true)
	    	 {
	    		 g2.drawImage(Group.getUnitItem(groupUnitSelected, 7).getItemPicture(),null,213,228);
	    	 }
	    	 g2.fill(new Rectangle2D.Double(115, 275,70,70));
	    	 if(Group.getUnitItem(groupUnitSelected,2).getItemHere() == true)
	    	 {
	    		 g2.drawImage(Group.getUnitItem(groupUnitSelected, 2).getItemPicture(),null,118,278);
	    	 }
	    	 g2.fill(new Rectangle2D.Double(15, 325,70,70));
	    	 if(Group.getUnitItem(groupUnitSelected,5).getItemHere() == true)
	    	 {
	    		 g2.drawImage(Group.getUnitItem(groupUnitSelected, 5).getItemPicture(),null,18,328);
	    	 }
	    	 g2.fill(new Rectangle2D.Double(210, 325,70,70));
	    	 if(Group.getUnitItem(groupUnitSelected,4).getItemHere() == true)
	    	 {
	    		 g2.drawImage(Group.getUnitItem(groupUnitSelected, 4).getItemPicture(),null,213,328);
	    	 }
	    	 g2.fill(new Rectangle2D.Double(115, 375,70,70));
	    	 if(Group.getUnitItem(groupUnitSelected,3).getItemHere() == true)
	    	 {
	    		 g2.drawImage(Group.getUnitItem(groupUnitSelected, 3).getItemPicture(),null,118,378);
	    	 }
	    	 g2.fill(new Rectangle2D.Double(115, 475,70,70));
	    	 g2.setColor(new Color(0,0,255));
	    	 g2.setFont(unitFont);
	    	 g2.drawString("Health", 20, 625);
	    	 g2.drawString(Double.toString(Group.getUnitHealth(groupUnitSelected)), 20, 650);
	    	 g2.drawString("/" + Double.toString(Group.getUnitMaxHealth(groupUnitSelected)), 60, 650);
	    	 g2.drawString("Moral", 170, 625);
	    	 g2.drawString(Double.toString(Group.getUnitMoral(groupUnitSelected))+" :", 170, 650);
	    	 if(Group.getUnitMoral(groupUnitSelected) < (Group.getTotalGroupMoral()/(Group.getSize() - 1)))
	    	 	{
	    		 g2.drawString("Lower",220,650);
	    	 	}
	    	 else if(Group.getUnitMoral(groupUnitSelected) == (Group.getTotalGroupMoral()/(Group.getSize() - 1)))
	    	 	{
	    		 g2.drawString("Average",220,650);
	    	 	}
	    	 else
	    	 {
	    		
	    		 g2.drawString("Higher",220,650);
	    	 }
	    	 g2.drawString("Attack", 20, 675);
	    	 g2.drawString(Double.toString(Group.getUnitAttack(groupUnitSelected)) + " /", 20, 700);
	    	 g2.drawString(Double.toString(Group.getUnitTotalAttack(groupUnitSelected)), 70, 700);
	    	 g2.drawString("Speed", 170, 675);
	    	 g2.drawString(Double.toString(Group.getUnitSpeed(groupUnitSelected)) + " /", 170, 700);
	    	 g2.drawString(Double.toString(Group.getUnitTotalSpeed(groupUnitSelected)), 220, 700);
	    	 g2.drawString("Defense", 20, 725);
	    	 g2.drawString(Double.toString(Group.getUnitDefense(groupUnitSelected)) + " /", 20, 750);
	    	 g2.drawString(Double.toString(Group.getUnitTotalDefense(groupUnitSelected)), 70, 750);
	    	 g2.drawString("Experience", 170, 725);
	    	 g2.drawString(Double.toString(Group.getUnitExperience(groupUnitSelected)) + " /", 170, 750);
	    	 g2.drawString(Double.toString(Group.getUnitNeededExperience(groupUnitSelected)), 220, 750);
	    
	    	 if(repaintLevelAdd)
	    	 {
	    		 if(attributesLeft > 0)
	    		 {
	    			 g2.setColor(new Color(0,0,255));
	    			 g2.fill(new Rectangle2D.Double(135, 765,30,30));
	    			 g2.fill(new Rectangle2D.Double(10, 765,80,30));
	    			 g2.setColor(new Color(255,0,0));
		    		 g2.drawString("+", 140, 787);
		    		 g2.drawString(Integer.toString(attributesLeft), 152, 787);
		    		 g2.drawString("Cancel", 20, 787);
	    			 
	    		 }
	    		 else
	    		 {
	    			 g2.setColor(new Color(0,0,255));
	    			 g2.fill(new Rectangle2D.Double(135, 765,30,30));
	    			 g2.fill(new Rectangle2D.Double(215, 765,80,30));
	    			 g2.fill(new Rectangle2D.Double(10, 765,80,30));
	    			 g2.setColor(new Color(255,0,0));
		    		 g2.drawString("0", 145, 787);
		    		 g2.drawString("Accept", 225, 787);
		    		 g2.drawString("Cancel", 20, 787);
	    			 
	    		 }
	    		 g2.setColor(new Color(0,0,255));
	    		 g2.fill(new Rectangle2D.Double(85, 660,20,20));
	    		 g2.fill(new Rectangle2D.Double(230, 660,20,20));
	    		 g2.fill(new Rectangle2D.Double(230, 610,20,20));
	    		 g2.fill(new Rectangle2D.Double(85, 610,20,20));
	    		 g2.fill(new Rectangle2D.Double(95, 710,20,20));
	    		 g2.setColor(new Color(255,0,0));
	    		 g2.drawString(Integer.toString(addAttack),91,677);
	    		 g2.drawString(Integer.toString(addSpeed),236,677);
	    		 g2.drawString(Integer.toString(addMoral),236,627);
	    		 g2.drawString(Integer.toString(addHealth),91,627);
	    		 g2.drawString(Integer.toString(addDefense),101,727);
	    	 }
	    	 else
	    	 {
	    		 
	    		 g2.fill(new Rectangle2D.Double(135, 765,30,30));
	    		 g2.setColor(new Color(255,0,0));
	    		 g2.drawString(Integer.toString(Group.getUnitSavedLevels(groupUnitSelected)), 145, 787);
	    	 }
	     }
	     if(repaintGroup)
	     {
	    	 
	    	 g2.setColor(new Color(0,0,255));
	    	 g2.fill(new Rectangle2D.Double(300, 100,400,700));
	    	 int xPos = 300;
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
	    	 g2.draw(new Rectangle2D.Double(299, 99,401,701));
	    	 g2.draw(new Rectangle2D.Double(499, 99,1,701));
	    	 
	    	 
	     }
	     if(mouseDragItem)
	     {
	    
	    	 g2.drawImage(draggedItem.getItemPicture(),null,(int)(MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX() - 32),(int)(MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY() - 32)); 	 
	     }
	        
	        
	}
	
	
	
}

