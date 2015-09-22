package ZuulCommands;

import Commands.ACommand;
import Character.Player;
import Game.AGame;

/**
 * This command class is to the Drop Command
 * @author Stevosh
 *
 */
public class DropCommand extends ACommand
{
    
    public DropCommand(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }
    
    public DropCommand() {}
   
    /**
     * this method execute the command
     */
    @Override
    public boolean execute(Player player)
    {
        if (!hasSecondWord())
        {
            // if there is no second word, we don't know what to drop...
            AGame._out.println(AGame._messages.getString("dropWhat"));
            return false;
        }
        String desc = getSecondWord();
        player.dropItem(desc);
        return false;
    }
}
