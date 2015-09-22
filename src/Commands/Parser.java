package Commands;

import java.util.MissingResourceException;
import java.util.Scanner;

import Game.AGame;
import ZuulCommands.*;

public class Parser 
{
    private final ACommandWords _commands;  // holds all valid command words
    private final Scanner _reader;         // source of command input
    private final String _MYPACKAGE; // name of the package with the game-specific classes

    public Parser(String pkg)
    {
        this._commands = AGame._commands;
        this._reader = new Scanner(AGame._in.in);
        this._MYPACKAGE = pkg + '.';
    }

    /**
     * @return The next command from the user.
     */
    public ACommand getCommand()
    {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;
        String word3 = null;

        AGame._out.print("> ");     // print prompt

        inputLine = this._reader.nextLine();

        try ( // Find up to two words on the line.
            // Note this construct will auto close the input 
            Scanner tokenizer = new Scanner(inputLine)) {
                if(tokenizer.hasNext()) {
                    word1 = tokenizer.next();      // get first word
                    if(tokenizer.hasNext()) {
                        word2 = tokenizer.next();      // get second word
                    }
                    if(tokenizer.hasNext()) {
                        word3 = tokenizer.next();      // get second word
                        // note: we just ignore the rest of the input line.
                    }
                }
        }

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        try
        {
            word1 = AGame._messages.getString(word1); // translate it
        }
        catch (MissingResourceException | NullPointerException e)
        {
            return new UnknownCommand(word1, word2, word3);
        }
        if(this._commands.isCommand(word1))
        {
        	String cmdString = this._MYPACKAGE + word1.substring(0, 1).toUpperCase() + word1.substring(1) + "Command";
            try
            {
                ACommand cmd = (ACommand) Class.forName(cmdString).newInstance();
                cmd.addWords(word1, word2, word3);
                //Could use the Constructor class but this is easier
                return cmd;
            }
            catch (ClassNotFoundException  
                   | InstantiationException 
                   | IllegalAccessException 
                   | SecurityException  
                   | IllegalArgumentException 
                   e ) 
            { 
                return new UnknownCommand(word1, word2, word3);
            } 
        }
        else
        {
            return new UnknownCommand(word1, word2, word3); 
        }
    }
    
    public ACommand getCommandGUI(String str)
    {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;
        String word3 = null;

        inputLine = str;

        try ( // Find up to two words on the line.
            // Note this construct will auto close the input 
            Scanner tokenizer = new Scanner(inputLine)) {
                if(tokenizer.hasNext()) {
                    word1 = tokenizer.next();      // get first word
                    if(tokenizer.hasNext()) {
                        word2 = tokenizer.next();      // get second word
                    }
                    if(tokenizer.hasNext()) {
                        word3 = tokenizer.next();      // get second word
                        // note: we just ignore the rest of the input line.
                    }
                }
        }

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        try
        {
            word1 = AGame._messages.getString(word1); // translate it
        }
        catch (MissingResourceException | NullPointerException e)
        {
            return new UnknownCommand(word1, word2, word3);
        }
        if(this._commands.isCommand(word1))
        {
        	String cmdString = this._MYPACKAGE + word1.substring(0, 1).toUpperCase() + word1.substring(1) + "Command";
            try
            {
                ACommand cmd = (ACommand) Class.forName(cmdString).newInstance();
                cmd.addWords(word1, word2, word3);
                //Could use the Constructor class but this is easier
                return cmd;
            }
            catch (ClassNotFoundException  
                   | InstantiationException 
                   | IllegalAccessException 
                   | SecurityException  
                   | IllegalArgumentException 
                   e ) 
            { 
                return new UnknownCommand(word1, word2, word3);
            } 
        }
        else
        {
            return new UnknownCommand(word1, word2, word3); 
        }
    }
}
