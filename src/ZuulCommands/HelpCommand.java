/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ZuulCommands;

import Commands.ACommand;
import Game.AGame;
import Character.Player;

/**
 * This command class is to the help Command
 * @author Stevosh
 *
 */
public class HelpCommand extends ACommand
{  
    public HelpCommand(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }
    
    public HelpCommand() {}
    
    /**
     * this method execute the command
     */
    @Override
    public boolean execute(Player player)
    {
    	for (String str : getInstructions()) 
    	    AGame._out.println(str);
        return false;
    }
    
    private String[] getInstructions()
    {
        String[] rv = new String[4];
        rv[0] = AGame._messages.getString("lost");
        rv[1] = "";
        rv[2] = AGame._messages.getString("commands");
        String tmp = "   ";
        for (String cmd : AGame._commands.getValidCommands())
            tmp += cmd + ' ';
        rv[3] = tmp;
        return rv;
    }
}
