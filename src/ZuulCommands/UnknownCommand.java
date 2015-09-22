package ZuulCommands;

import Commands.ACommand;
import Character.Player;
import Game.AGame;

/**
 * This command class is to crate an unknow Command
 * @author Stevosh
 *
 */
public class UnknownCommand extends ACommand
{
    public UnknownCommand(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }
    
    public UnknownCommand() {}
    
    /**
     * this method execute an unknow command
     */
    @Override
    public boolean execute(Player player)
    {
        AGame._out.println(AGame._messages.getString("unknown"));
        return false;
    }
}
