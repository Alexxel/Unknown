package MainGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;



public class MainMenu   {
	
	
	
	String pName;
	String gName;
	int diffic;
	
    // Create Buttons then override the default background drawing
    JButton newGame = new JButton("New Game")
    {
    	
		private static final long serialVersionUID = 1L;

		@Override
    	 protected void paintComponent(Graphics g) {
    	      super.paintComponents(g);
    	          g.drawImage(newGameBackground, 0, 0, null);
    }
    };
    JButton loadGame = new JButton("Load Game")
    {
    	private static final long serialVersionUID = 1L;
    	@Override
    	 protected void paintComponent(Graphics g) {
    	      super.paintComponents(g);
    	          g.drawImage(loadGameBackground, 0, 0, null);
    }
    };
    JButton exitGame = new JButton("Exit Game"){
    	private static final long serialVersionUID = 1L;
    	@Override
   	 protected void paintComponent(Graphics g) {
   	      super.paintComponents(g);
   	          g.drawImage(exitGameBackground, 0, 0, null);
   }
   };
   
   //create buttons then leave the default background
   JButton submit = new JButton("Start Game");
   JButton back = new JButton("Back");
   
   //Create radio buttons (Selectors)
   //Add in select value
   JRadioButton easy = new JRadioButton("Easy");
   {
	   easy.setActionCommand("easy");
   }
   
   JRadioButton regular = new JRadioButton("Regular");
   {
       //Set as default selected
   regular.setSelected(true);
   regular.setActionCommand("regular");
   }
   JRadioButton hard = new JRadioButton("Hard");
   {
	   hard.setActionCommand("hard");
   }
   
//Forces the radio buttons into a single group
   ButtonGroup difficulty = new ButtonGroup();
   {
   difficulty.add(easy);
   difficulty.add(regular);
   difficulty.add(hard);
   }
  
  //Create background image location
    BufferedImage mainMenuBackground = null;
    BufferedImage newGameBackground = null;
    BufferedImage loadGameBackground = null;
    BufferedImage exitGameBackground = null;
    
    
    //Try to find image
    //If not found, give error
    {
    	try{
    	mainMenuBackground = ImageIO.read(getClass().getResource("Images/MainMenuBackground.jpg"));
    	newGameBackground = ImageIO.read(getClass().getResource("Images/NewGame.jpg"));
    	loadGameBackground = ImageIO.read(getClass().getResource("Images/LoadGame.png"));
    	exitGameBackground = ImageIO.read(getClass().getResource("Images/ExitGame.jpg"));
    }
    catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    //Create both GUI views
    JFrame userInput = new JFrame();
    JFrame mainMenu = new JFrame(){
    	private static final long serialVersionUID = 1L;
    	
    };
    //Create input boxes
    JTextField playerName = new JTextField("Player Name");
    JTextField GroupName = new JTextField("Group Name");

//Default constructor
    public MainMenu() {
    	
    	
    	
    	
    	pName = "test pName";
    	gName = "";
    	diffic = 0;
    	
    	

    	//Create buttons and add size/Location
        newGame.setBounds(54, 200, 324, 75);
        newGame.setBorderPainted(false);
        loadGame.setBounds(54, 400, 324, 75);
        loadGame.setBorderPainted(false);
        exitGame.setBounds(54, 600, 324, 75);
        exitGame.setBorderPainted(false);
        
        submit.setBounds(270 , 400, 100,50);
        back.setBounds(20 , 400 ,100,50 );
        easy.setBounds(50, 200, 100, 15);
        regular.setBounds(50, 230, 100, 15);
        hard.setBounds(50, 260, 100, 15);
        
        
        //Create textboxes and add size/Location
        playerName.setBounds(50,50,100,50);
        GroupName.setBounds(50,100,100,50);
        
        
        //Add actions to each button
        
        exitGame.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e)
    	    {
    	       System.exit(0);
    	    
    	    }
    	});
        
        loadGame.addActionListener(new ActionListener()
        		{
        	public void actionPerformed(ActionEvent e)
        	{
        		System.out.println("No added function, YET");
        		
        	}
        	
        		});
        submit.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e)
    	    {
    	    	
    	    	 pName = playerName.getText();
    	    	 gName = GroupName.getText();
    	    	String diff = difficulty.getSelection().getActionCommand();
    	    	if(diff == "easy")
    	    	{
    	    		diffic = 1;
    	    	}
    	    	else if(diff == "regular")
    	    	{
    	    		diffic = 2;
    	    	}
    	    	else if(diff == "hard")
    	    	{
    	    		diffic = 3;
    	    	}
    	    	System.out.println(pName);
    	    	System.out.println(gName);
    	    	System.out.println(diff);
    	    	
    	    	mainMenu.dispose();
    	    	userInput.dispose();
    	    	
    	    	new GameRunner(diffic,pName,gName);
    	    	
    	       
    	    
    	    }
    	});
        back.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e)
    	    {
    	    	mainMenu.setVisible(true);
    	    	userInput.setVisible(false);
    	    
    	    }
    	});
        
        newGame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		
        		userInput.setVisible(true);
        		mainMenu.setVisible(false);
        		
        	}
        });
        
           

        // JFrame bounds
        mainMenu.setBounds(800, 800, 432, 768);
        userInput.setBounds(800,800,200,100);

        // Adding to JFrame
      
        newGame.setLayout(null);
        mainMenu.add(newGame);
        mainMenu.add(loadGame);
        mainMenu.add(exitGame);
        mainMenu.add(new JLabel(new ImageIcon(mainMenuBackground)));
        mainMenu.setResizable(false);
        
        userInput.setLayout(null);
        userInput.add(submit);
        userInput.add(back);
        userInput.add(playerName);
        userInput.add(GroupName);
        userInput.add(easy);
        userInput.add(regular);
        userInput.add(hard);
        userInput.setResizable(false);
        

        // JFrame properties
        mainMenu.setSize(432, 800);
        mainMenu.setBackground(Color.BLACK);
        mainMenu.setName("The Warrior's Path");
       

        mainMenu.setLocationRelativeTo(null);
       	mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setVisible(true);
        
        userInput.setSize(400,500);
        userInput.setBackground(Color.BLACK);
        userInput.setName("New Game");
        userInput.setLocationRelativeTo(null);
        userInput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userInput.setVisible(false);
        
        
        
     
       
    }
    
   
   
   public static void main(String[] args) {
		
		new MainMenu();
		
   }
   
   
}


