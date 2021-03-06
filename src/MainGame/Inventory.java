package MainGame;



public class Inventory
{
	Item[][] inventory;
	Item itemOne;
	Item itemTwo;
	
	Inventory()
	{
		inventory = new Item[6][10];
		itemOne = new Item();
		itemTwo = new Item();
	}
	
	public Item getItem(int x,int y)
	{
		return inventory[x][y];
	}
	
	public void addItem(Item item, int x,int y)
	{
		inventory[x][y] = item;
	}
	public void removeItem(int x,int y)
	{
		inventory[x][y] = new Item();
	}
	public void setItem(Item item, int x, int y)
	{
		inventory[x][y] = item;
	}
	public void itemSwap(int x1, int y1, int x2, int y2)
	{
	itemOne = inventory[x1][y1];
	itemTwo = inventory[x2][y2];
	
	inventory[x1][y1] = itemTwo;
	inventory[x2][y2] = itemOne;
	}
}