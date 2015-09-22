package ZuulCommands;

import Game.AGame;
import Commands.ACommand;
import Character.Player;

/**
 * This command class is to the take Command
 * @author Stevosh
 *
 */
public class TakeCommand extends ACommand
{
    public TakeCommand(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }
    
    public TakeCommand() {}
    
    /**
     * this method execute the command
     */
    @Override
    public boolean execute(Player player)
    {
        if (!hasSecondWord())
        {
            // if there is no second word, we don't know what to take...
            AGame._out.println(AGame._messages.getString("takeWhat"));
            return false;
        }
        String desc = getSecondWord();
        player.take(desc);
        return false;
    }
}
