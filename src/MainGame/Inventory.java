package MainGame;

import java.util.List;

public class Inventory
{
	List<Item> inventory;
	
	Inventory()
	{
		
	}
	
	public Item getItem(int number)
	{
		return inventory.get(number);
	}
	
	public void addItem(Item item, int postion)
	{
		inventory.add(postion, item);
	}
	public void removeItem(int postion)
	{
		inventory.remove(postion);
	}
}