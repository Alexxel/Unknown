package MainGame;


public class Shop{
	
	int gold;
	Inventory inventory;
	Item tempItem;
	
	Shop()
	{
		gold = 100;
		inventory = new Inventory();
		tempItem = new Item();
		for(int y = 0; y < 10; y++)
		{
			for(int x = 0; x<6;x++)
			{
				inventory.addItem(new Item(),x, y);
			}
		}
	}
	
	public int getGold()
	{
		return gold;
	}
	
	public void setGold(int g)
	{
		gold = g;
	}
	public Inventory getInventory()
	{
		return inventory;
	}
	
	public boolean sellItem(Item item,int salePrice)
	{
		if(gold < salePrice)
		{
			System.out.println("Can not sell item due to lack of shop funds");
			
			return false;
		}
		else if (!inventory.addNextItem(item))
		{
			System.out.println("Cannot sell due to lack of space");
			
			return false;
		}
		gold = gold - salePrice;
		return true;
		
	}
	
	public Item buyItem(int x, int y)
	{
		tempItem = inventory.getItem(x, y);
		
		gold = gold + inventory.getItem(x,y).getGoldValue();
		
		inventory.addItem(new Item(),x,y);
		
		return tempItem;
	}
}

