package Game;

import java.util.ResourceBundle;
import java.util.List;
import java.util.Locale;

import Commands.ACommand;
import Commands.ACommandWords;
import Commands.Parser;
import Character.Player;
import GUI.GUI;
import ZuulInputOutput.*;

/**
 * This class create an abstract Game to decouple the code and load an other game
 * if we want to change the game
 * @author Stevosh
 *
 */
public abstract class AGame
{
	public static final Out _out = new Out();
	public static final In _in = new In();
	public static ResourceBundle _messages;
	public static ACommandWords _commands;
	final private Parser _parser;
	protected Player _player;
	protected List<Room> _rooms;
	
	/**
	 * Constructor
	 * @param language
	 * @param country
	 * @param commands
	 */
	public AGame(String language, String country, ACommandWords commands)
	{
		Locale currentLocation = new Locale(language, country);
		AGame._messages = ResourceBundle.getBundle("ZuulCommands.MessagesBundle", currentLocation);
		AGame._commands = commands;
		this._parser = new Parser("ZuulCommands");
		createRooms();
	}
	
	/**
	 * the start of the game
	 */
	public void play()
	{
		boolean finished = false;
		GUI gui = new GUI(this._player, this._parser);
		
		printWelcome();
		while (!finished)
		{
			ACommand command = this._parser.getCommand();
			finished = processCommand(command);
		}
		AGame._out.println(AGame._messages.getString("goodbye"));
	}
	
	/**
	 * print the welcome on the game
	 */
	private void printWelcome()
	{
		getWelcomeStrings().stream().forEach((str) -> { AGame._out.println(str); });
	}
	
	/**
	 * send to the player to execute the command
	 * @param command
	 * @return
	 */
	private boolean processCommand(ACommand command)
	{
		return command.execute(this._player);
	}
	
	/**
	 * set a player in the game
	 * @param player
	 */
	protected void setPlayer(Player player)
	{
		this._player = player;
	}
	
	/**
	 * get the player in the game
	 * @return
	 */
	protected Player getPlayer()
	{
		return this._player;
	}
	
	/**
	 * set the rooms in the game
	 * @param rooms
	 */
	protected void setRooms(List<Room> rooms)
	{
		this._rooms = rooms;
	}
	
	protected abstract List<String> getWelcomeStrings();
	protected abstract void createRooms();
}
