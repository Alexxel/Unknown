package MainGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;



public class GameRunner extends JFrame implements KeyListener , MouseListener
{
	
	
	
	private static final long serialVersionUID = 1L;
	
	
	
	List<MapInfo> oldMapInfo;
	
	boolean showTopBar;
	boolean showSettingsWindow;
	boolean showMapWindow;
	boolean soundDone;
	boolean repaintInventory;
	boolean inventoryOne;
	boolean repaintUnit;
	boolean repaintGroup;
	boolean groupOne;
	boolean nameChange;
	boolean repaintLevelAdd;
	boolean repaintMap;
	boolean paintMap;
	
	int gameDifficulty;
	int WidthOfGame;
	int HeightOfGame;
	int groupUnitSelected;
	int attributesLeft;
	int addDefense;
	int addAttack;
	int addSpeed;
	int addMoral;
	int addHealth;
	int playerXLocation;
	int playerYLocation;
	int oldPlayerXLocation;
	int oldPlayerYLocation;
	int xPlayerMiniMap;
	int yPlayerMiniMap;
	int xStart;
	int yStart;
	int xEnd;
	int yEnd;
	int redrawXStart;
	int redrawYStart;
	int redrawXEnd;
	int redrawYEnd;
	String PlayerName;
	String GroupName;
	String newName;
	String oldName;
	Group Group;
	
	BufferedImage tempImage;
	
	Image MainPictures;
	JPanel mapWindow;
	JPanel settingsWindow;
	//JPanel topBarWindow;
	
	JTextField nameInput;
	
	JButton settingsButton;
	JButton mapButton;
	
	Scanner keyboard;
	
	Sounds Sounds;
	
	GridBagConstraints layout;
	
	Time time;
	
	Inventory inventory;
	
	Font groupUnitFont;
	Font unitFont;
	Font levelFont;
	
	Map map;
	Group tempGroup;
	Inventory tempInventory;
	
	
	GameRunner()
	{
		gameDifficulty = 0;
		PlayerName = "";
		GroupName = "";
		Group = new Group();
		MainPictures = new Image();
		System.out.println("Default gameRunner Const");
	}
	GameRunner(int gD, String pN, String gN)
	{
			
		oldMapInfo = new ArrayList<MapInfo>();
			
			
			Sounds = new Sounds();
			Sounds.addSound(new File("C:/Users/Alex's/Downloads/SoundTest1.wav"));
		
		time = new Time();
		
		inventory = new Inventory();
		
		soundDone = false;
		showSettingsWindow = false;
		showMapWindow = false;
		showTopBar = false;
		repaintInventory = false;
		inventoryOne = false;
		repaintUnit = false;
		repaintGroup = false;
		groupOne = false;
		nameChange = false;
		repaintLevelAdd = false;
		repaintMap = true;
		paintMap = true;
		WidthOfGame = 0;
		HeightOfGame = 0;
		groupUnitSelected = 1;
		attributesLeft = 0;
		addDefense = 0;
		addAttack = 0;
		addSpeed = 0;
		addMoral = 0;
		addHealth = 0;
		gameDifficulty = gD;
		PlayerName = pN;
		GroupName = gN;
		playerXLocation = 938;
		playerYLocation = 400;
		oldPlayerXLocation = playerXLocation;
		oldPlayerYLocation = playerYLocation;
		xPlayerMiniMap = 0;
		yPlayerMiniMap = 0;
		xStart = 50;
		yStart = 50;
		xEnd = 100;
		yEnd = 100;
		redrawXStart = 50;
		redrawYStart = 50;
		redrawXEnd = 100;
		redrawYEnd = 100;
		newName = "";
		oldName = "";
		Group = new Group(3);
		MainPictures = new Image();
		MainPictures.add("Images/MainMap.jpg");
		MainPictures.add("Images/MainBackground.jpg");
		MainPictures.add("Images/SettingsWindowBackground.jpg");
		MainPictures.add("Images/TopBarWindow.jpg");
		MainPictures.add("Images/PlayerMapIcon.jpg");
		MainPictures.add("Images/GameMap.jpg");
		System.out.println("gD,pN,gN gameRunner Const");
		setPreferredSize(new Dimension(WidthOfGame, HeightOfGame));
		addKeyListener(this);
		addMouseListener(this);
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
       
        groupUnitFont = new Font("TimesRoman",Font.ITALIC,40);
        unitFont = new Font("TimesRoman",Font.BOLD,20);
        levelFont = new Font("TimesRoman",Font.ITALIC,25);
		
		layout  = new GridBagConstraints();
		
		nameInput = new JTextField("New Name");
		nameInput.setSize(150, 45);
		nameInput.setLocation(10, 75);
		nameInput.setVisible(false);
		nameInput.addKeyListener(this);
		
		
        settingsButton = new JButton("Settings");
        settingsButton.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e)
    	    {
    	    	toggleSettingsWindow();
    	    }
    	});
        layout.gridx = 6;
        layout.gridy = 1;
       // add(settingsButton,layout);
		
       
		
		
		settingsWindow = new JPanel();
		settingsWindow.setSize(800,400);
		settingsWindow.add(new JLabel(new ImageIcon(MainPictures.getImage(2))));
		settingsWindow.setVisible(false);
		
		keyboard = new Scanner(System.in);
	
		add(settingsWindow);
		add(nameInput);
		
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
		
		map = new Map();
		
		map.setXStart(710);
		map.setYStart(100);
		
		tempGroup = new Group();
		tempGroup.setImage("Images/enemyMapIcon.jpg");
		
		tempInventory = new Inventory();
		map.add(new MapInfo(tempGroup,tempInventory,800,200,800,200,1,10));
		map.add(new MapInfo(tempGroup,tempInventory,900,650,900,650,1,10));
		
		map.setMoveDown(0, true);
		map.setMoveUp(1, true);
		
		tempImage = null;
		
		pack();
		repaint();
		main();
		
		
	}
	
	public void toggleMapWindow()
	{
		if(showMapWindow == false)
	       {
	    	   showMapWindow = true;
	    	   mapWindow.setVisible(true);
	    	
	       }
	       else
	       {
	    	   showMapWindow = false;
	    	   mapWindow.setVisible(false);
	       }
	 }
	public void toggleSettingsWindow()
	{
		if(showSettingsWindow == false)
		 {
			 settingsWindow.setVisible(true);
			 showSettingsWindow = true;
			 Sounds.playSound(0);
		 }
		 else
		 {
			 showSettingsWindow = false;
			 settingsWindow.setVisible(false);
			 
		 }
		
	}
	
	
	public void keyPressed(KeyEvent e)
	{
		 int key = e.getKeyCode();
		 System.out.println("Key typed is " + key);
		 
		 if(key == 27)
		 {
			 if(nameChange = true)
			 {
				 nameChange = false;
				 repaintUnit = true;
				 newName = oldName;
				 Group.setUnitName(groupUnitSelected, newName);
				 nameInput.setVisible(false);
				 repaint();
				 
			 }
			 else if(showSettingsWindow == false)
			 {
			 settingsWindow.setVisible(true);
			 showSettingsWindow = true;
			 Sounds.playSound(0);
			 }
			 else
			 {
			showSettingsWindow = false;
		    settingsWindow.setVisible(false);
			 }
		 }
		 
		 else if(key == 10)
		 {
			 if(nameChange)
			 {
				 nameChange = false;
				 repaintUnit = true;
				 newName = nameInput.getText();
				 if(newName.length() > 10)
				 {
					 System.out.println("Name is too long");
					 nameInput.setVisible(false);
					 repaint();
				 }
				 else
				 {
					 Group.setUnitName(groupUnitSelected, newName);
					 nameInput.setVisible(false);
					 repaint();
					 
				 }
				 
			 }
		 }
		 else if(key == 38 )
		 {
			 oldPlayerYLocation = playerYLocation;
			 playerYLocation--;
			 repaintMap = true;
			 for(int i = 0;i < map.getSize();i++)
			 {
				 map.setRedraw(i,true);
				 
			 }
		 }
		 else if(key == 37 )
		 {
			 oldPlayerXLocation = playerXLocation;
			 playerXLocation--;
			 repaintMap = true;
			 for(int i = 0;i < map.getSize();i++)
			 {
				 map.setRedraw(i,true);
				 
			 }
		 }
		 else if(key == 39 )
		 {
			 oldPlayerXLocation = playerXLocation;
			 playerXLocation++;
			 repaintMap = true;
			 for(int i = 0;i < map.getSize();i++)
			 {
				 map.setRedraw(i,true);
				 
			 }
		 }
		 else if(key == 40 )
		 {
			 oldPlayerYLocation = playerYLocation;
			 playerYLocation++;
			 repaintMap = true;
			 for(int i = 0;i < map.getSize();i++)
			 {
				 map.setRedraw(i,true);
				 
			 }
		 }
		 
		
	}
	public void changeName()
	{
		nameInput.setText(Group.getUnitName(groupUnitSelected));
		nameInput.setVisible(true);
	}
	public void keyReleased(KeyEvent e)
	{
		System.out.println("keyReleased");
		
	}
	public void keyTyped(KeyEvent e)
	{
		System.out.println("keyTyped");
		
	}
	public void paint(Graphics g){
		 Graphics2D g2 = (Graphics2D) g;
		 
		 if(paintMap)
		 {
			 System.out.println("Drawing paintMap");
			 paintMap = false;
			
			 
			 //g2.setColor(new Color(100,100,100));
			 //g2.fillRect(0, 0, 1280, 1024);
			 g2.drawImage(MainPictures.getImage(5), null,0,0);
			  
		 }
		 if(repaintMap)
		 {
			 //tempImage = MainPictures.getImageClip(5, xStart, yStart, xEnd - xStart, yEnd - yStart);
			 //g2.drawImage(tempImage,null,xStart,yStart);
			
			
			 
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
			 
			 //System.out.println("Drawing x " +xStart +" to"+ xEnd + " And y " + yStart + " " + yEnd);
			 //System.out.println(playerXLocation + " " + playerYLocation);
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
			 
			 g2.drawImage(MainPictures.getImage(4), null, playerXLocation-15,playerYLocation-15);
			 
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
	    	 System.out.println("Print Inv");
	    	
	    	 repaintInventory = false;
	    	 int xPos = 500;
	    	 int yPos = 100;
	    	 if(inventoryOne)
	    	 {
	    		 System.out.println("Print Inv 1");
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
	    		 System.out.println("Print Inv 2 ");
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
	    		 System.out.println("Total group moral is " +Group.getTotalGroupMoral());
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
	    	 System.out.println(repaintLevelAdd);
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
	    	 System.out.println("OutsideGroup");
	    	 System.out.println(Group.getSize());
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
	    		 System.out.println("InsideGrouptest");
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
		    		 System.out.println("InsideGroup");
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
		    		 System.out.println("InsideGroup");
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
	        
	        
		main();
	}

	
	
	
	
	public void main()
	{
		repaint();

	}
	
	
	
	






@Override
public void mouseClicked(MouseEvent m) {
	System.out.println("Got mouse click at " + m.getPoint() );
	System.out.println("RepaintLevelAdd is " + repaintLevelAdd);
	
	if((m.getPoint().getX() > 650 && m.getPoint().getX() < 680)  && (m.getPoint().getY() > 800 && m.getPoint().getY() < 830 ))
	{
		System.out.println("Got mouse click on inventory one");
		inventoryOne = true;
		repaintInventory = true;
		repaint();
	}
	else if((m.getPoint().getX() > 680 && m.getPoint().getX() < 710)  && (m.getPoint().getY() > 800 && m.getPoint().getY() < 830 ))
	{
		System.out.println("Got mouse click on inventory two");
		inventoryOne = false;
		repaintInventory = true;
		repaint();
	}
	else if((m.getPoint().getX() > 250 && m.getPoint().getX() < 275)  && (m.getPoint().getY() > 125 && m.getPoint().getY() < 150 ))
	{
		System.out.println("Got click on name change");
		nameChange = true;
		oldName = Group.getUnitName(groupUnitSelected);
		newName = "";
		repaint();
	}
	else if((m.getPoint().getX() > 300 && m.getPoint().getX() < 500)  && (m.getPoint().getY() > 100 && m.getPoint().getY() < 800 ))
	{
		System.out.println("Selecting Unit");
		int yPos = (int)(((m.getPoint().getY() - 100)/69) + 1);
		System.out.println("Getting unit " + yPos);
		if(groupOne)
		{
			System.out.println("Inside group one");
			groupUnitSelected = yPos;
			System.out.println("Unit Selected is " + groupUnitSelected);
			if(groupUnitSelected > Group.getSize())
			{
				groupUnitSelected = Group.getSize();
			}
		}
		else
		{
			System.out.println("Inside group two");
			groupUnitSelected = (yPos + 10);
			System.out.println("Unit Selected is " + groupUnitSelected);
			if(groupUnitSelected > Group.getSize())
			{
				groupUnitSelected = Group.getSize();
			}
		}
		repaintGroup = true;
		repaintUnit = true;
		nameChange = false;
		nameInput.setVisible(false);
		repaintLevelAdd = false;
		repaint();
	}
	else if((m.getPoint().getX() > 135 && m.getPoint().getX() < 165)  && (m.getPoint().getY() > 765 && m.getPoint().getY() < 795 ))
	{
		System.out.println("Got mouse click on level-up point spend");
		if(Group.getUnitSavedLevels(groupUnitSelected) > 0)
		{
		repaintUnit = true;
		repaintLevelAdd = true;
		attributesLeft = Group.getUnitSavedLevels(groupUnitSelected) * 2;
		addAttack = 0;
		addDefense = 0;
		addSpeed = 0;
		addMoral = 0;
		addHealth = 0;
		repaint();	
		}
	}
	else if(repaintLevelAdd)
	{
		System.out.println("Inside repaintLevelAdd Mouse click");
		if((m.getPoint().getX() > 10 && m.getPoint().getX() < 90)  && (m.getPoint().getY() > 765 && m.getPoint().getY() < 795 ))
		{
			System.out.println("Stopping the level-up");
			repaintUnit = true;
			repaintLevelAdd = false;
			attributesLeft = 0;
			addAttack = 0;
			addDefense = 0;
			addSpeed = 0;
			addMoral = 0;
			addHealth = 0;
			repaint();	
		}
		else if(((m.getPoint().getX() > 215 && m.getPoint().getX() < 295)  && (m.getPoint().getY() > 765 && m.getPoint().getY() < 795 )) &&(attributesLeft == 0))
		{
		System.out.println("Finishing the level-up");	
		repaintUnit = true;
		repaintLevelAdd = false;
		attributesLeft = 0;
		Group.setUnitSavedLevels(groupUnitSelected,0);
		Group.setUnitAttack(groupUnitSelected, (int) (Group.getUnitAttack(groupUnitSelected) + (addAttack*5)));
		Group.setUnitDefense(groupUnitSelected, (int) (Group.getUnitDefense(groupUnitSelected) + (addDefense*5)));
		Group.setUnitSpeed(groupUnitSelected, (int) (Group.getUnitSpeed(groupUnitSelected) + (addSpeed*5)));
		Group.setUnitMoral(groupUnitSelected, (int) (Group.getUnitMoral(groupUnitSelected) + (addMoral*5)));
		Group.setUnitHealth(groupUnitSelected, (int) (Group.getUnitHealth(groupUnitSelected) + (addHealth*5)));
		addAttack = 0;
		addDefense = 0;
		addSpeed = 0;
		addMoral = 0;
		addHealth = 0;
		repaint();	
		
		}
		else if(attributesLeft > 0)
		{
			if((m.getPoint().getX() > 85 && m.getPoint().getX() < 105)  && (m.getPoint().getY() > 660 && m.getPoint().getY() < 680 ))
			{
				System.out.println("Click on attack add");
				addAttack++;
				attributesLeft--;
				repaintUnit = true;
				repaint();
			}
			else if((m.getPoint().getX() > 85 && m.getPoint().getX() < 105)  && (m.getPoint().getY() > 610 && m.getPoint().getY() < 630 ))
			{
				addHealth++;
				repaintUnit = true;
				attributesLeft--;
				repaint();
			}
			else if((m.getPoint().getX() > 95 && m.getPoint().getX() < 115)  && (m.getPoint().getY() > 710 && m.getPoint().getY() < 730 ))
			{
				addDefense++;
				attributesLeft--;
				repaintUnit = true;
				repaint();
			}
			else if((m.getPoint().getX() > 230 && m.getPoint().getX() < 250)  && (m.getPoint().getY() > 610 && m.getPoint().getY() < 630 ))
			{
				addMoral++;
				attributesLeft--;
				repaintUnit = true;
				repaint();
			}
			else if((m.getPoint().getX() > 230 && m.getPoint().getX() < 250)  && (m.getPoint().getY() > 660 && m.getPoint().getY() < 680 ))
			{
				addSpeed++;
				attributesLeft--;
				repaintUnit = true;
				repaint();
			}
		}
	}
	
	else if((m.getPoint().getX() > 440 && m.getPoint().getX() < 469)  && (m.getPoint().getY() > 800 && m.getPoint().getY() < 830 ))
		{
		if(Group.getSize() > 10)
		{
			System.out.println("Got mouse click on group tab one");
			repaintGroup = true;
			groupOne = true;
			repaintUnit = true;
			repaintLevelAdd = false;
			repaint();
		}
		}
		else if((m.getPoint().getX() > 470 && m.getPoint().getX() < 500)  && (m.getPoint().getY() > 800 && m.getPoint().getY() < 830 ))
		{
			if(Group.getSize() > 10)
			{
			System.out.println("Got mouse click on group tab two");
			repaintGroup = true;
			groupOne = false;
			groupUnitSelected = 11;
			repaintUnit = true;
			repaintLevelAdd = false;
			repaint();	
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

