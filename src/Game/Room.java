package Game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Game.AGame;
import Item.Item;
import Character.Player;
import ZuulCommands.BadExitException;

public class Room
{
	// Description of the room (name)
    final public String _description;
    // Exits from the room
    final private Map<String, Room> _exits;
    // An Map of items in the room
    private HashMap<String, Item> _itemsPresent;
    // An Map of Characters
    final private HashMap<String, Player> _characters;

    public Room(String description) 
    {
        this._description = description;
        this._itemsPresent = new HashMap<>();
        this._characters = new HashMap<>();
        this._exits = new HashMap<>();
    }

    public void setExits(Room north, Room east, Room south, Room west) 
    {
        try
        {
    	    setExit(AGame._messages.getString("north"), north);
    	    setExit(AGame._messages.getString("east"), east);
    	    setExit(AGame._messages.getString("south"), south);
    	    setExit(AGame._messages.getString("west"), west);
    	}
        catch (BadExitException e)
        {
    	    e.printStackTrace();
    	    System.exit(1);
    	}
    }
    
    public void setExit(String direction, Room room) throws BadExitException
    {
    	if (room == null)
    	    return;
        if (direction == null)
            throw new BadExitException(direction, room);
        this._exits.put(direction, room);
    }

    public Room getExit(String direction)
    {
    	return this._exits.get(direction);
    }

    public String getDescription()
    {
        return this._description;
    }

    public void addItem(String name, int weight)
    {
    	Item item = new Item(name, weight);
   		this._itemsPresent.put(name, item);
    }
    
    public void addItem(String name, Item item)
    {
    	if (item != null)
    	{
    		this._itemsPresent.put(name, item);
    	}
    }

    public int getNumberItems()
    {
    	return this._itemsPresent.size();
    }

    public Item getItem(String item)
    {
    	return this._itemsPresent.get(item);
    }

    public HashMap<String, Item> getAllItems()
    {
    	return this._itemsPresent;
    }

    public Item removeItem(String description)
    {
    	if (this._itemsPresent.containsKey(description))
    		return this._itemsPresent.remove(description);
    	else
    	{
    		AGame._out.println(description + " " + AGame._messages.getString("room"));
    		return null;
    	}
    }
    
    public boolean containsItem(String description)
    {
    	return this._itemsPresent.containsKey(description);
    }
    
    public void addCharacter(Player character)
    {
    	this._characters.put(character.getName(), character);
    }

    public Player getCharacter(String name)
    {
    	return this._characters.get(name);
    }
    
    public void removeCharacter(String name)
    {
    	if (name != null)
    		this._characters.remove(name);
    }
    
    public boolean hasCharacter(String character)
    {
    	return this._characters.containsKey(character);
    }
    
    public HashMap<String, Player> getListCharacters()
    {
    	return this._characters;
    }
    
    public List<String> getDetails()
    {   	
    	List<String> details = new LinkedList<>();
    	
    	details.add(AGame._messages.getString("in") + " " + getDescription());
        String tmp = AGame._messages.getString("exits") + ": ";
        tmp = this._exits.keySet().stream()
                .map((dir) -> dir + ' ')
                .reduce(tmp, String::concat);
        details.add(tmp);
        
        tmp = AGame._messages.getString("items") + ": ";
        tmp = this._itemsPresent.keySet().stream()
                .map((desc) -> desc + '(' + this._itemsPresent.get(desc).getWeight() + ')')
                .reduce(tmp, String::concat);
        details.add(tmp);
        
        tmp = AGame._messages.getString("characters") + ": ";
        tmp = this._characters.keySet().stream()
        		.map((desc) -> desc + ' ')
        		.reduce(tmp, String::concat);    
        details.add(tmp);
        
        return details;
    }
}
