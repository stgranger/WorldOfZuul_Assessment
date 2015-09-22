package ZuulCommands;

import Commands.ACommand;
import Game.AGame;
import Character.Player;

/**
 * This command class is to the look Command
 * @author Stevosh
 *
 */
public class LookCommand extends ACommand
{
    public LookCommand(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }
    
    public LookCommand() {}
    
    /**
     * this method execute the command
     */
    @Override
    public boolean execute(Player player)
    {
        if (hasSecondWord())
        {
            AGame._out.println(AGame._messages.getString("lookWhat"));
            return false;
        }
        player.look();
        return false;
    }
}
