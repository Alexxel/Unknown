package MainGame;

import java.util.Timer;
import java.util.TimerTask;


public class Time extends TimerTask{
	
	//Initilize 
	int year;
	int month;
	int day;
	int hour;
	Timer timer;
	
	//Default constructor
	public Time()
	{
		year = 1800;
		month= 1;
		day = 1;
		hour = 1;
		
		
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run(){
				updateTime();
				System.out.println("Running Time timer task");
			}
		
		},1*60*1000,1*60*1000);

	}
	
	//Constructor with Time elements
	public Time(int Y, int M, int D , int H)
	{
		year = Y;
		month = M;
		day = D;
		hour = H;
		
	}
	//Adds one day to total time
	public void updateTime()
	{
		day++;
		
		if (day > 30 && month < 12)
		{
			month++;
			day = 1;
		}
		else if(day > 30)
		{
			day = 1;
			month = 1;
			year++;
		}
	}
	
	
	//Returns time in a date format
	public String getCurrentFancyTime()
	{
		String fancyTime = "";
		if(day == 1)
		{
			fancyTime = "Today is the 1st of ";
		}
		else if(day == 2)
		{
			
			fancyTime = "Today is the 2nd of ";
			
		}
		else if(day == 3)
		{
			
			fancyTime = "Today is the 3rd of ";
			
		}
		
		else
		{
			
			fancyTime = "Today is the " + day + "th of ";
			
		}
		switch (month)
		{
		case 1: fancyTime = fancyTime + "January in the year of " + year;
		break;
		case 2: fancyTime = fancyTime + "Febuary in the year of " + year;
		break;
		case 3: fancyTime = fancyTime + "March in the year of " + year;
		break;
		case 4: fancyTime = fancyTime + "April in the year of " + year;
		break;
		case 5: fancyTime = fancyTime + "May in the year of " + year;
		break;
		case 6: fancyTime = fancyTime + "June in the year of " + year;
		break;
		case 7: fancyTime = fancyTime + "July in the year of " + year;
		break;
		case 8: fancyTime = fancyTime + "August in the year of " + year;
		break;
		case 9: fancyTime = fancyTime + "September in the year of " + year;
		break;
		case 10: fancyTime = fancyTime + "October in the year of " + year;
		break;
		case 11: fancyTime = fancyTime + "November in the year of " + year;
		break;
		case 12: fancyTime = fancyTime + "December in the year of " + year;
		break;
		}
		return fancyTime;
	}
	
	//Returns a future time as a date format
	public String getFutureFancyTime(int days)
	{
		int oDay = day;
		int oHour = hour;
		int oYear = year;
		int oMonth = month;
		
		for(int x = days; x > 0; x--)
		{
			updateTime();
		}
		
		String fancyTime = "";
		if(day == 1)
		{
			fancyTime = "On the 1st of ";
		}
		else if(day == 2)
		{
			
			fancyTime = "On the 2nd of ";
			
		}
		else if(day == 3)
		{
			
			fancyTime = "On the 3rd of ";
			
		}
		
		else
		{
			
			fancyTime = "On the " + day + "th of ";
			
		}
		switch (month)
		{
		case 1: fancyTime = fancyTime + "January in the year of " + year;
		break;
		case 2: fancyTime = fancyTime + "Febuary in the year of " + year;
		break;
		case 3: fancyTime = fancyTime + "March in the year of " + year;
		break;
		case 4: fancyTime = fancyTime + "April in the year of " + year;
		break;
		case 5: fancyTime = fancyTime + "May in the year of " + year;
		break;
		case 6: fancyTime = fancyTime + "June in the year of " + year;
		break;
		case 7: fancyTime = fancyTime + "July in the year of " + year;
		break;
		case 8: fancyTime = fancyTime + "August in the year of " + year;
		break;
		case 9: fancyTime = fancyTime + "September in the year of " + year;
		break;
		case 10: fancyTime = fancyTime + "October in the year of " + year;
		break;
		case 11: fancyTime = fancyTime + "November in the year of " + year;
		break;
		case 12: fancyTime = fancyTime + "December in the year of " + year;
		break;
		}
		day = oDay;
		year = oYear;
		month = oMonth;
		hour = oHour;
		return fancyTime;
	}
	//returns current year
	public int getYear()
	{
		return year;
	}
	//returns current Month as a number
	public int getMonthN()
	{
		return month;
	}
	//Returns current Month as a string
	public String getMonthS()
	{
		String fancyTime = "";
		
		switch (month)
		{
		case 1: fancyTime = fancyTime + "January";
		break;
		case 2: fancyTime = fancyTime + "Febuary";
		break;
		case 3: fancyTime = fancyTime + "March";
		break;
		case 4: fancyTime = fancyTime + "April";
		break;
		case 5: fancyTime = fancyTime + "May";
		break;
		case 6: fancyTime = fancyTime + "June";
		break;
		case 7: fancyTime = fancyTime + "July";
		break;
		case 8: fancyTime = fancyTime + "August";
		break;
		case 9: fancyTime = fancyTime + "September";
		break;
		case 10: fancyTime = fancyTime + "October";
		break;
		case 11: fancyTime = fancyTime + "November";
		break;
		case 12: fancyTime = fancyTime + "December";
		break;
		}
		
		return fancyTime;
	}
	//returns current day as a number
	public int getDayN()
	{
		return day;
	}
	//returns current day as a string
	public String getDayS()
	{
	String fancyTime = "";
	
	if(day == 1)
	{
		fancyTime = "1st";
	}
	else if(day == 2)
	{
		
		fancyTime = "2nd";
		
	}
	else if(day == 3)
	{
		
		fancyTime = "3rd";
		
	}
	
	else
	{
		
		fancyTime = "" + day +"th";
		
	}
	
		return fancyTime;
	}

	@Override
	public void run() {
	
	 updateTime();
	 
	}

	
	
	
	
}