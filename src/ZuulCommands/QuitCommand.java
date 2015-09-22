package ZuulCommands;

import Game.AGame;
import Commands.ACommand;
import Character.Player;

/**
 * This command class is to the quit Command
 * @author Stevosh
 *
 */
public class QuitCommand extends ACommand
{
    public QuitCommand(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }
    
    public QuitCommand() {}
    
    /**
     * this method execute the command
     */
    @Override
    public boolean execute(Player player)
    {       
        if (hasSecondWord())
        {
            AGame._out.println(AGame._messages.getString("quitWhat"));
            return false;
        }
        return true;
    }
}
