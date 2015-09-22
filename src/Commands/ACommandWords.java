package Commands;

import Game.AGame;

/**
 * This class create a Abstract command words to valid the commands
 * @author Stevosh
 *
 */
public abstract class ACommandWords 
{
    public ACommandWords() { }

    public abstract String[] getValidCommands();
    
    // Check if the given String is a valid command word else return false
    public boolean isCommand(String aString)
    {
        for(String cmd : getValidCommands())
        {
            if (cmd.equals(AGame._messages.getString(aString)))
                return true;
        }
        return false;
    }
}
