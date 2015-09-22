package ZuulCommands;

import Commands.ACommand;
import Game.AGame;
import Character.Player;

/**
 * This command class is to the go Command
 * @author Stevosh
 *
 */
public class GoCommand extends ACommand
{
	public GoCommand(String firstWord, String secondWord, String thirdWord)
	{
        super(firstWord, secondWord, thirdWord);
    }
    
    public GoCommand() {}
    
    /**
     * this method execute the command
     */
    @Override
    public boolean execute(Player player)
    {
        if (!hasSecondWord())
        {
            AGame._out.println(AGame._messages.getString("goWhere"));
            return false;
        }
        String direction = getSecondWord();
        player.goRoom(direction);
        return false;
    }
}
