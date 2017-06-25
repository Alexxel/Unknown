package MainGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.Timer;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;




public class GameRunner implements KeyListener , MouseListener
{
	
	
	
	private static final long serialVersionUID = 1L;
	
	
	boolean soundDone;
	boolean newInput;
	
	
	int gameDifficulty;
	
	
	
	
	
	String PlayerName;
	String GroupName;
	String newName;
	String oldName;
	
	
	
	
	Timer timer;
	
	
	
	
	
	
	JButton settingsButton;
	JButton mapButton;
	
	Scanner keyboard;
	
	Sounds Sounds;
	
	GridBagConstraints layout;
	
	Time time;
	
	Inventory inventory;
	
	
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
		
		time = new Time();
		
		inventory = new Inventory();
		
		soundDone = false;
		newInput  = false;
		
		
		
		
		
		
		
		gameDifficulty = gD;
		PlayerName = pN;
		GroupName = gN;
		newName = "";
		oldName = "";
		
	
		
	
		
       
        
		
		gameWindow = new MainGameWindow(this,this);
		
		
		timer = new Timer(50, new ActionListener(){
			public void actionPerformed(ActionEvent evt)
			{
				newInput = false;
				gameWindow.repaint();
				
			}
		});
		timer.start();
		
				
			
				
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
				 gameWindow.setNameInput(false);
				 gameWindow.repaint();
				 
			 }
			
		 }
		 
		 else if(key == 10)
		 {
			 if(gameWindow.getNameChange() == true)
			 {
				 gameWindow.setNameChange(false);
				 gameWindow.setRepaintUnit(true);
				 newName = gameWindow.getNameInput();
				 if(newName.length() > 10)
				 {
				
					 gameWindow.setNameInput(false);
					 gameWindow.repaint();
				 }
				 else
				 {
					 gameWindow.getGroup().setUnitName(gameWindow.getGroupUnitSelected(), newName);
					 gameWindow.setNameInput(false);
					 gameWindow.repaint();
					 
				 }
				 
			 }
		 }
		 else if(key == 38 &&  newInput == false)
		 {
			 gameWindow.setOldPlayerYLocation(gameWindow.getPlayerYLocation());
			 gameWindow.setPlayerYLocation(gameWindow.getPlayerYLocation() - 1);
			 gameWindow.setRepaintMap(true);
			 newInput = true;
			
		 }
		 else if(key == 37 &&  newInput == false)
		 {
			 gameWindow.setOldPlayerXLocation(gameWindow.getPlayerXLocation());
			 gameWindow.setPlayerXLocation(gameWindow.getPlayerXLocation() - 1);
			 gameWindow.setRepaintMap(true);
			 newInput = true;
			 
		 }
		 else if(key == 39 &&  newInput == false)
		 {
			 gameWindow.setOldPlayerXLocation(gameWindow.getPlayerXLocation());
			 gameWindow.setPlayerXLocation(gameWindow.getPlayerXLocation() + 1);
			 gameWindow.setRepaintMap(true);
			 newInput = true;
			 
		 }
		 else if(key == 40&&  newInput == false )
		 {
			 gameWindow.setOldPlayerYLocation(gameWindow.getPlayerYLocation());
			 gameWindow.setPlayerYLocation(gameWindow.getPlayerYLocation() + 1);
			 gameWindow.setRepaintMap(true);
			 newInput = true;
			 
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
	

	
	
	
	
	public void main()
	{
		gameWindow.repaint();

	}
	
	
	
	






@Override
public void mouseClicked(MouseEvent m) {
	System.out.println("Got mouse click at " + m.getPoint() );
	
	
	if((m.getPoint().getX() > 650 && m.getPoint().getX() < 680)  && (m.getPoint().getY() > 800 && m.getPoint().getY() < 830 ))
	{
		System.out.println("Got mouse click on inventory one");
		gameWindow.setInventoryOne(true);
		gameWindow.setRepaintInventory(true);
		gameWindow.repaint();
	}
	else if((m.getPoint().getX() > 680 && m.getPoint().getX() < 710)  && (m.getPoint().getY() > 800 && m.getPoint().getY() < 830 ))
	{
		System.out.println("Got mouse click on inventory two");
		gameWindow.setInventoryOne(false);
		gameWindow.setRepaintInventory(true);
		gameWindow.repaint();
	}
	else if((m.getPoint().getX() > 250 && m.getPoint().getX() < 275)  && (m.getPoint().getY() > 125 && m.getPoint().getY() < 150 ))
	{
		System.out.println("Got click on name change");
		gameWindow.setNameChange(true);
		oldName = gameWindow.getGroup().getUnitName(gameWindow.getGroupUnitSelected());
		newName = "";
		gameWindow.repaint();
	}
	else if((m.getPoint().getX() > 300 && m.getPoint().getX() < 500)  && (m.getPoint().getY() > 100 && m.getPoint().getY() < 800 ))
	{
		System.out.println("Selecting Unit");
		int yPos = (int)(((m.getPoint().getY() - 100)/69) + 1);
		System.out.println("Getting unit " + yPos);
		if(gameWindow.getGroupOne() == true)
		{
			System.out.println("Inside group one");
			gameWindow.setGroupUnitSelected(yPos);
			System.out.println("Unit Selected is " + gameWindow.getGroupUnitSelected());
			if(gameWindow.getGroupUnitSelected() > gameWindow.getGroup().getSize())
			{
				gameWindow.setGroupUnitSelected(gameWindow.getGroup().getSize());
			}
		}
		else
		{
			System.out.println("Inside group two");
			gameWindow.setGroupUnitSelected(yPos + 10);
			System.out.println("Unit Selected is " + gameWindow.getGroupUnitSelected());
			if(gameWindow.getGroupUnitSelected() > gameWindow.getGroup().getSize())
			{
				gameWindow.setGroupUnitSelected(gameWindow.getGroup().getSize());
			}
		}
		gameWindow.setRepaintGroup(true);
		gameWindow.setRepaintUnit(true);
		gameWindow.setNameChange(false);
		gameWindow.setNameInput(false);
		gameWindow.setRepaintLevelAdd(false);
		gameWindow.repaint();
	}
	else if((m.getPoint().getX() > 135 && m.getPoint().getX() < 165)  && (m.getPoint().getY() > 765 && m.getPoint().getY() < 795 ))
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
		gameWindow.repaint();	
		}
	}
	else if(gameWindow.getRepaintLevelAdd() == true)
	{
		System.out.println("Inside repaintLevelAdd Mouse click");
		if((m.getPoint().getX() > 10 && m.getPoint().getX() < 90)  && (m.getPoint().getY() > 765 && m.getPoint().getY() < 795 ))
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
			gameWindow.repaint();	
		}
		else if(((m.getPoint().getX() > 215 && m.getPoint().getX() < 295)  && (m.getPoint().getY() > 765 && m.getPoint().getY() < 795 )) &&(gameWindow.getAttributesLeft() == 0))
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
		gameWindow.repaint();	
		
		}
		else if(gameWindow.getAttributesLeft() > 0)
		{
			if((m.getPoint().getX() > 85 && m.getPoint().getX() < 105)  && (m.getPoint().getY() > 660 && m.getPoint().getY() < 680 ))
			{
				System.out.println("Click on attack add");
				gameWindow.setAddAttack(gameWindow.getAddAttack() + 1);
				gameWindow.setAttributesLeft(gameWindow.getAttributesLeft() - 1);
				gameWindow.setRepaintUnit(true);
				gameWindow.repaint();
			}
			else if((m.getPoint().getX() > 85 && m.getPoint().getX() < 105)  && (m.getPoint().getY() > 610 && m.getPoint().getY() < 630 ))
			{
				gameWindow.setAddHealth(gameWindow.getAddHealth() + 1);
				gameWindow.setAttributesLeft(gameWindow.getAttributesLeft() - 1);
				gameWindow.setRepaintUnit(true);
				gameWindow.repaint();
			}
			else if((m.getPoint().getX() > 95 && m.getPoint().getX() < 115)  && (m.getPoint().getY() > 710 && m.getPoint().getY() < 730 ))
			{
				gameWindow.setAddDefense(gameWindow.getAddDefense() + 1);
				gameWindow.setAttributesLeft(gameWindow.getAttributesLeft() - 1);
				gameWindow.setRepaintUnit(true);
				gameWindow.repaint();
			}
			else if((m.getPoint().getX() > 230 && m.getPoint().getX() < 250)  && (m.getPoint().getY() > 610 && m.getPoint().getY() < 630 ))
			{
				gameWindow.setAddMoral(gameWindow.getAddMoral() + 1);
				gameWindow.setAttributesLeft(gameWindow.getAttributesLeft() - 1);
				gameWindow.setRepaintUnit(true);
				gameWindow.repaint();
			}
			else if((m.getPoint().getX() > 230 && m.getPoint().getX() < 250)  && (m.getPoint().getY() > 660 && m.getPoint().getY() < 680 ))
			{
				gameWindow.setAddSpeed(gameWindow.getAddSpeed() + 1);
				gameWindow.setAttributesLeft(gameWindow.getAttributesLeft() - 1);
				gameWindow.setRepaintUnit(true);
				gameWindow.repaint();
			}
		}
	}
	
	else if((m.getPoint().getX() > 440 && m.getPoint().getX() < 469)  && (m.getPoint().getY() > 800 && m.getPoint().getY() < 830 ))
		{
		if(gameWindow.getGroup().getSize() > 10)
		{
			System.out.println("Got mouse click on group tab one");
			gameWindow.setRepaintGroup(true);
			gameWindow.setGroupOne(true);
			gameWindow.setRepaintUnit(true);
			gameWindow.setRepaintLevelAdd(false);
			gameWindow.repaint();
		}
		}
		else if((m.getPoint().getX() > 470 && m.getPoint().getX() < 500)  && (m.getPoint().getY() > 800 && m.getPoint().getY() < 830 ))
		{
			if(gameWindow.getGroup().getSize() > 10)
			{
			System.out.println("Got mouse click on group tab two");
			gameWindow.setRepaintGroup(true);
			gameWindow.setGroupOne(false);
			gameWindow.setGroupUnitSelected(11);
			gameWindow.setRepaintUnit(true);
			gameWindow.setRepaintLevelAdd(false);
			gameWindow.repaint();
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
	// TODO Auto-generated method stub
	
}
@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}


}



class MainGameWindow extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	int playerXLocation;
	int playerYLocation;
	int oldPlayerXLocation;
	int oldPlayerYLocation;
	int xStart;
	int yStart;
	int xEnd;
	int yEnd;
	//int redrawXStart;
	//int redrawYStart;
	//int redrawXEnd;
	//int redrawYEnd;
	int groupUnitSelected;
	int attributesLeft;
	int addDefense;
	int addAttack;
	int addSpeed;
	int addMoral;
	int addHealth;
	
	Font groupUnitFont;
	Font unitFont;
	Font levelFont;
	
	Image MainPictures;
	List<MapInfo> oldMapInfo;
	Map map;
	Group Group;
	Group tempGroup;
	Inventory tempInventory;
	
	BufferedImage tempImage;
	BufferedImage playerImage;
	
	JTextField nameInput;

	
	boolean repaintInventory;
	boolean inventoryOne;
	boolean repaintUnit;
	boolean repaintGroup;
	boolean groupOne;
	boolean nameChange;
	boolean repaintLevelAdd;
	boolean repaintMap;
	boolean paintMap;
	
	MainGameWindow(KeyListener key, MouseListener mouse)
	{
		playerXLocation = 938;
		playerYLocation = 400;
		oldPlayerXLocation = playerXLocation;
		oldPlayerYLocation = playerYLocation;
		xStart = 50;
		yStart = 50;
		xEnd = 100;
		yEnd = 100;
		//redrawXStart = 50;
		//redrawYStart = 50;
		//redrawXEnd = 100;
		//redrawYEnd = 100;
		groupUnitSelected = 1;
		attributesLeft = 0;
		addDefense = 0;
		addAttack = 0;
		addSpeed = 0;
		addMoral = 0;
		addHealth = 0;
		
		oldMapInfo = new ArrayList<MapInfo>();
		
		
		repaintInventory = false;
		inventoryOne = false;
		repaintUnit = false;
		repaintGroup = false;
		groupOne = false;
		nameChange = false;
		repaintLevelAdd = false;
		repaintMap = true;
		paintMap = true;
		
		
		setPreferredSize(new Dimension(1200, 1000));
		addKeyListener(key);
		addMouseListener(mouse);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setTitle("Temp Game Name");
		setResizable(false);
		setSize(1000, 1000);
		setMinimumSize(new Dimension(1280, 1024));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		setLocationRelativeTo(null);
		setVisible(true);
		
		MainPictures = new Image();
		MainPictures.add("Images/MainMap.jpg");
		MainPictures.add("Images/MainBackground.jpg");
		MainPictures.add("Images/SettingsWindowBackground.jpg");
		MainPictures.add("Images/TopBarWindow.jpg");
		MainPictures.add("Images/PlayerMapIcon.jpg");
		MainPictures.add("Images/GameMap.jpg");
		
		groupUnitFont = new Font("TimesRoman",Font.ITALIC,40);
        unitFont = new Font("TimesRoman",Font.BOLD,20);
        levelFont = new Font("TimesRoman",Font.ITALIC,25);
        
        playerImage = MainPictures.getImage(4);
        
        nameInput = new JTextField("");
        
        tempGroup = new Group();
		tempGroup.setImage("Images/enemyMapIcon.jpg");
		
		tempInventory = new Inventory();
        map = new Map();
		
		map.setXStart(710);
		map.setYStart(100);
		
		map.add(new MapInfo(tempGroup,tempInventory,800,200,800,200,1,10));
		map.add(new MapInfo(tempGroup,tempInventory,900,650,900,650,1,10));

		map.setMoveDown(0, true);
		map.setMoveUp(1, true);
		
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
		
		Group.addItem(1, new Item(5,20,-2, .02,.2,2,10,5,"Images/MainMap.jpg","Temp"));
		Group.setUnitSavedLevels(5, 2);
		Group.setUnitSavedLevels(4, 1);
		
		
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
	
	public Group getGroup()
	{
		return Group;
	}
	
	public void setNameInput(boolean b)
	{
		nameInput.setVisible(false);
	}
	public String getNameInput()
	{
		return nameInput.getText();
	}
	public Map getMap()
	{
		return map;
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
	public void setInventoryOne(boolean b)
	{
		inventoryOne = b;
	}
	public void setRepaintGroup(boolean b)
	{
		repaintGroup = b;
	}
	public void setRepaintUnit(boolean b)
	{
		repaintUnit = b;
	}
	public void setGroupOne(boolean b)
	{
		groupOne = b;
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
	public boolean getInventoryOne()
	{
		return inventoryOne;
	}
	public boolean getRepaintGroup()
	{
		return repaintGroup;
	}
	public boolean getRepaintUnit()
	{
		return repaintUnit;
	}
	public boolean getGroupOne()
	{
		return groupOne;
	}
	public boolean getNameChange()
	{
		return nameChange;
	}
	public boolean getRepaintLevelAdd()
	{
		return repaintLevelAdd;
	}
	public boolean setRepaintMap()
	{
		return repaintMap;
	}
	public boolean getPaintMap()
	{
		return paintMap;
	}
	
	
	public void changeName()
	{
		//nameInput.setText(Group.getUnitName(groupUnitSelected));
		nameInput.setVisible(true);
	}
	
	
	public void paint(Graphics g){
		 Graphics2D g2 = (Graphics2D) g;
		
		 
		 if(paintMap)
		 {
			
			 paintMap = false;
			
			 g2.drawImage(MainPictures.getImage(5), null,0,0);
			 g2.drawImage(playerImage, null, playerXLocation-15,playerYLocation-15);
			  
		 }
		 if(repaintMap)
		 {
			 
			
			
			 
			 if(playerXLocation < 0)
			 {
				 playerXLocation = 0;
			 }
			 else if(playerXLocation > 1250)
			 {
				 playerXLocation = 1250;
			 }
			 
			 if(playerYLocation < 30)
			 {
				 playerYLocation = 30;
			 }
			 else if(playerYLocation > 994)
			 {
				 playerYLocation = 994;
			 }
			 
			 //Setting playerView for x and y
			 if(playerXLocation < 1030 && playerXLocation > 250)
			 {
				 xStart = playerXLocation - 250;
				 xEnd = playerXLocation + 250;
			 }
			 else if(playerXLocation <= 250)
			 {
				 xStart = 0;
				 xEnd = playerXLocation + 250;
			 }
			 else
			 {
				 xStart = playerXLocation - 250;
				 xEnd = 1280;
			 }
			 
			 if(playerYLocation < 774 && playerYLocation > 250)
			 {
				 yStart = playerYLocation - 250;
				 yEnd = playerYLocation + 250;
			 }
			 else if(playerYLocation <= 250)
			 {
				 yStart = 0;
				 yEnd = playerYLocation + 250;
			 }
			 else
			 {
				 yStart = playerYLocation - 250;
				 yEnd = 1024;
			 }
			 
			 
			 //Draw in map elements inside player View
			 for(int i = 0; i < map.getSize();i++)
			 {
				 if(map.getRedraw(1) == true)
				 {
					 oldMapInfo.add(map.getMapInfo(i));
				 }
			 }
			 for(int i = 0; i < oldMapInfo.size();i++)
			 {
				
				
				 tempImage = MainPictures.getImageClip(5,oldMapInfo.get(i).getXMapLocation()-20,oldMapInfo.get(i).getYMapLocation()-20,40,40);
				 g2.drawImage(tempImage,null,oldMapInfo.get(i).getXMapLocation()-20,oldMapInfo.get(i).getYMapLocation()-20);
					 
				 
			 }
			 oldMapInfo.removeAll(oldMapInfo);
			
			 for(int i = 0; i < map.getSize();i++)
			 {
				 if(map.getMapInfo(i).getXMapLocation() > xStart && map.getMapInfo(i).getXMapLocation() < xEnd )
				 {
					 if(map.getMapInfo(i).getYMapLocation() > yStart && map.getMapInfo(i).getYMapLocation() < yEnd)
					 {
						 if(map.getRedraw(i) == true)
						 {
						 g2.drawImage(map.getGroup(i).getImage(), null,map.getXLocation(i)-15,map.getYLocation(i)-15);
						 map.setRedraw(i, false);
						 }
					 }
				 }
			 }
			 //Draw player Icon
			 
			 if(oldPlayerXLocation != playerXLocation || playerYLocation != oldPlayerYLocation)
			 {
			 tempImage = MainPictures.getImageClip(5,oldPlayerXLocation-20,oldPlayerYLocation-20,40,40);
			 g2.drawImage(tempImage, null, oldPlayerXLocation-20,oldPlayerYLocation-20);
			 
			 g2.drawImage(playerImage, null, playerXLocation-15,playerYLocation-15);
			 
			 oldPlayerXLocation = playerXLocation;
			 oldPlayerYLocation = playerYLocation;
			 }
			 
			
			 
		 }
			 
			 
			 
			 
			
	
			 
		 
		 
		 if(nameChange)
		 {
			 g2.setColor(new Color(150,50,50));
			 g2.fill(new Rectangle2D.Double(0, 110 ,280,45));
			 changeName();
   		 
		 }
		
	     
	     if(repaintInventory)
	     {
	    	 
	    	
	    	 repaintInventory = false;
	    	 int xPos = 500;
	    	 int yPos = 100;
	    	 if(inventoryOne)
	    	 {
	    		
	    		 //Inventory Page 1
	    		 g2.setColor(new Color(165,42,42));
	    		 for(int i = 0; i < 10; i++)
	    		 {
	    			 g2.fill(new Rectangle2D.Double(xPos, yPos,70,70));
	    			 g2.fill(new Rectangle2D.Double(xPos + 70, yPos,70,70));
	    			 g2.fill(new Rectangle2D.Double(xPos + 140, yPos,70,70));
	    			 yPos = yPos+ 70;
	    			 
	    			 
	    			 
	    		 }
	    		 g2.setColor(new Color(255,0,0));
	    		 g2.fill(new Rectangle2D.Double(xPos + 150, yPos,30,30));
	    		 g2.setColor(new Color(0,255,0));
	    		 g2.fill(new Rectangle2D.Double(xPos + 180, yPos,30,30));
	    		 
	    		 yPos = 100;
	    		 g2.setColor(new Color(100,100,100));
	    		 for(int i = 0; i < 10; i++)
	    		 {
	    			 g2.draw(new Rectangle2D.Double(xPos, yPos,70,70));
	    			 g2.draw(new Rectangle2D.Double(xPos + 70, yPos,70,70));
	    			 g2.draw(new Rectangle2D.Double(xPos + 140, yPos,70,70));
	    			 yPos = yPos+ 70;
	    			 
	    			 
	    		 }
	    	 }
	    	 else
	    	 {
	    		 //Inventory Page 2
	    	
	    		 g2.setColor(new Color(165,42,42));
	    		 for(int i = 0; i < 10; i++)
	    		 {
	    			 g2.fill(new Rectangle2D.Double(xPos, yPos,70,70));
	    			 g2.fill(new Rectangle2D.Double(xPos + 70, yPos,70,70));
	    			 g2.fill(new Rectangle2D.Double(xPos + 140, yPos,70,70));
	    			 yPos = yPos+ 70;
	    			 
	    			 
	    		 }
	    		 g2.setColor(new Color(0,255,0));
	    		 g2.fill(new Rectangle2D.Double(xPos + 150, yPos,30,30));
	    		 g2.setColor(new Color(255,0,0));
	    		 g2.fill(new Rectangle2D.Double(xPos + 180, yPos,30,30));
	    		 yPos = 100;
	    		 g2.setColor(new Color(100,100,100));
	    		 for(int i = 0; i < 10; i++)
	    		 {
	    			 g2.draw(new Rectangle2D.Double(xPos, yPos,70,70));
	    			 g2.draw(new Rectangle2D.Double(xPos + 70, yPos,70,70));
	    			 g2.draw(new Rectangle2D.Double(xPos + 140, yPos,70,70));
	    			 yPos = yPos+ 70;
		    			 
		    			 
		    		 }
	    		 
	    	 }
	    	 
	     }
	     if(repaintUnit)
	     {
	    	 repaintUnit = false;
	    	 g2.setColor(new Color(150,50,50));
	    	 g2.fill(new Rectangle2D.Double(0, 100,300,700));
	    	 g2.setColor(new Color(0,0,255));
	    	 g2.setFont(groupUnitFont);
	    	 g2.drawString(Group.getUnitName(groupUnitSelected), 50, 150);
	    	 g2.setFont(levelFont);
	    	 g2.drawString("Level " + Integer.toString(Group.getUnitLevel(groupUnitSelected)), 25, 175);
	         g2.fill(new Rectangle2D.Double(250, 125 ,25,25));
	    	 g2.setColor(new Color(0,255,255));
	    	 g2.fill(new Rectangle2D.Double(115, 175 ,70,70));
	    	 g2.fill(new Rectangle2D.Double(15, 225,70,70));
	    	 g2.fill(new Rectangle2D.Double(210, 225,70,70));
	    	 g2.fill(new Rectangle2D.Double(115, 275,70,70));
	    	 g2.fill(new Rectangle2D.Double(15, 325,70,70));
	    	 g2.fill(new Rectangle2D.Double(210, 325,70,70));
	    	 g2.fill(new Rectangle2D.Double(115, 375,70,70));
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
	    	 repaintGroup = false;
	    	 g2.setColor(new Color(0,0,255));
	    	 g2.fill(new Rectangle2D.Double(300, 100,200,700));
	    	 g2.setColor(new Color(0,255,0));
	    	 g2.draw(new Rectangle2D.Double(299, 99,201,701));
	    	 int xPos = 300;
	    	 int yPos = 100;
	    
	    	 for(int i = 1; i < Group.getSize();i++)
	    	 {
	    		 Group.checkUnitLevel(i);
	    	 }
	    	 if(Group.getSize() > 11)
	    	 {
	    		 g2.fill(new Rectangle2D.Double(439, 801,30,30));
	    		 g2.fill(new Rectangle2D.Double(470, 801,30,30));
	    	 }
	    	 if(Group.getSize() < 12)
	    	 {
	    	 for(int i = 0; i < Group.getSize() - 1;i++)
	    	 {
	    		
	    		 g2.setColor(new Color(255,0,0));
		    	 g2.fill(new Rectangle2D.Double(xPos, yPos,200,69));
		    	 g2.setColor(new Color(0,255,0));
		    	 g2.setFont(unitFont);
		    	 g2.drawString(Group.getUnitName(i + 1), xPos + 20, yPos + 5);
		    	 yPos += 70; 
	    	 }
	    	 }
	    	 else if(groupOne)
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
	    		 
	    	 }
	    	 else
	    	 {
	    		 
	    		 for(int i = 11; i < Group.getSize();i++)
		    	 {
		    		
		    		 g2.setColor(new Color(255,0,0));
			    	 g2.fill(new Rectangle2D.Double(xPos, yPos,200,69));
			    	 g2.setColor(new Color(0,255,0));
			    	 g2.setFont(unitFont);
			    	 g2.drawString(Group.getUnitName(i), xPos + 20, yPos + 20);

			    	 yPos += 70; 
		    	 }
	    		 
	    	 }
	    	 g2.setColor(new Color(00,255,0));
	    	 if(groupUnitSelected > 10)
	    	 {
	    		 g2.fill(new Rectangle2D.Double(xPos, 100+(70*(groupUnitSelected-11)),200,69));
	    	 }
	    	 else
	    	 {
	    		 g2.fill(new Rectangle2D.Double(xPos, 100+(70*(groupUnitSelected-1)),200,69));
	    	 }
	    	
	    	 
	    	 
	     }
	        
	        
	}

	
}

