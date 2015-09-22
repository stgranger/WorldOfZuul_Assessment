package Character;

import Item.Item;
import Game.Room;
import Game.AGame;

import java.util.HashMap;
import java.util.List;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * A player is a Character. It inherit from the Character Interface.
 * It is used to create a Player class. 
 * We have a name, a currentRoom and an inventory.
 * 
 * @author Steve Granger
 *
 */
public class Player implements Character
{
	private String _name;  // name of the player
	private Room _currentRoom; // current room where the player is
	private int _totalWeight; // weight total of items can wear
	private static int _MAX_WEIGHT = 10;
	private HashMap<String, Item> _itemsInventory;
	
	/**
	 * Constructor
	 * @param name
	 * @param startRoom
	 */
	public Player(String name, Room startRoom)
	{
		this._name = name;
		this._currentRoom = startRoom;
		this._totalWeight = 0;
		this._itemsInventory = new HashMap<>();
	}
	   
	/**
	 * set the name
	 */
	@Override
	public void setName(String name)
	{
		this._name = name;
	}

	/**
	 * get the name
	 */
	@Override
	public String getName()
	{
		return this._name;
	}
	
	/**
	 * set the current room
	 */
	@Override
	public void setCurrentRoom(Room currentRoom)
	{
		this._currentRoom = currentRoom;
	}

	/**
	 * get the current room
	 */
	@Override
	public Room getCurrentRoom()
	{
		return this._currentRoom;
	}

	/**
	 * get details
	 */
	@Override
	public List<String> getDetails()
	{
		return getCurrentRoom().getDetails();
	}

	/**
	 * get a item
	 */
	@Override
	public Item getItemInventory(String item)
	{
		return _itemsInventory.get(item);
	}

	@Override
	public int getInventorySize()
	{
		return this._itemsInventory.size();
	}

	/**
	 * get inventory
	 */
	@Override
	public HashMap<String, Item> getInventory()
	{
		return this._itemsInventory;
	}
	
	/**
	 * check if is an item
	 * @param desc
	 * @return
	 */
	public boolean hasItem(String desc)
	{
		return this._itemsInventory.containsKey(desc);
	}

	/**
	 * check if is too heavy
	 * @param item
	 * @return
	 */
	public boolean tooHeavy(Item item)
	{
		return (item.getWeight() + this._totalWeight > getMaxWeight());
	}

	/**
	 * add an item
	 */
	@Override
	public void addItem(Item item)
	{
		this._itemsInventory.put(item.getItemName(), item);
	}
	
	/**
	 * Command take
	 * @param desc
	 */
    public void take(String desc)
    {
        if (!getCurrentRoom().containsItem(desc))
        {
            AGame._out.println(desc + " " + AGame._messages.getString("room"));
            return;
        }
        Item item = getCurrentRoom().getItem(desc);
        if (tooHeavy(item))
        {
            AGame._out.println(desc + " " + AGame._messages.getString("heavy"));
            return;
        }
        item = getCurrentRoom().removeItem(desc);
        this._itemsInventory.put(desc, item);
        this._totalWeight += item.getWeight();
    }
	
	/**
	 * Command drop
	 * @param desc
	 */
	@Override
	public void dropItem(String name)
	{
		if (!hasItem(name))
		{
			AGame._out.println(AGame._messages.getString("dontHave") + " " + name);
            return;
		}
        Item item = this._itemsInventory.remove(name);
        this._totalWeight -= item.getWeight();
        this._currentRoom.addItem(name, item);
	}
	
	/**
	 * Command give
	 * @param desc
	 */
	@Override
    public void giveItem(String desc, String character)
	{
        if (!this._currentRoom.hasCharacter(character))
        {
            AGame._out.println(character + " " + AGame._messages.getString("room"));
            return;
        }
        if (!this._itemsInventory.containsKey(desc))
        {
            AGame._out.println(AGame._messages.getString("room") + " " + desc);
            return;
        }
        if (this._currentRoom.getCharacter(character).getTotalWeight() + this._itemsInventory.get(desc).getWeight() > this._currentRoom.getCharacter(character).getMaxWeight())
        {
        	AGame._out.println(character + " " + AGame._messages.getString("heavy") + " " + desc);
            return;
        }
        Item item = this._itemsInventory.remove(desc);
        this._totalWeight -= item.getWeight();
        this._currentRoom.getCharacter(character).addItem(item);        
    }

	/**
	 * Command look
	 * @param desc
	 */
	public void look()
	{
		for (String str : getCurrentRoom().getDetails())
			AGame._out.println(str);
	}
	
	/**
	 * Command go room
	 * @param desc
	 */
    public void goRoom(String direction)
    {
        Room nextRoom = getCurrentRoom().getExit(direction);
        if (nextRoom == null)
        {
            AGame._out.println(AGame._messages.getString("door"));
        }
        else
        {
            setCurrentRoom(nextRoom);
            look();
        }
    }
	
    /**
     * set the total weight
     */
	public void setTotalWeight(int totalWeight)
	{
		this._totalWeight = totalWeight;
	}

	/**
	 * get the total weight
	 */
	public int getTotalWeight()
	{
		return this._totalWeight;
	}
	
	/**
	 * get the maw weight
	 */
	public int getMaxWeight()
	{
		return _MAX_WEIGHT;
	}
}
