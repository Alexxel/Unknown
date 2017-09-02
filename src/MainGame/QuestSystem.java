package MainGame;

import java.util.ArrayList;
import java.util.List;

public class QuestSystem
{
	List<Quest> Quests;
	
	QuestSystem()
	{
		Quests = new ArrayList<Quest>();
	}
	
	public void addQuest(Quest quest)
	{
		Quests.add(quest);
	}
	
	public void removeQuest(int i)
	{
		Quests.remove(i);
	}
	
	public Quest getQuest(int i)
	{
		return Quests.get(i);
	}
	public List<Quest> getAll()
	{
		return Quests;
	}
}