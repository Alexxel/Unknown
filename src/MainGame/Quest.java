package MainGame;


public class Quest 
{
	String Name;
	String Description;
	
	Quest()
	{
		Name = "";
		Description = "";
	}
	
	Quest(String name, String description)
	{
		Name = name;
		Description = description;
	}
	
	public String getName()
	{
		return Name;
	}
	
	public String getDescription()
	{
		return Description;
	}
	
	
}