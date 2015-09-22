package Commands;

import Character.Player;

/**
 * This class create an Abstract Command
 * @author Stevosh
 *
 */
public abstract class ACommand
{
    private String _commandWord;
    private String _secondWord;
    private String _thirdWord;

    public ACommand(String firstWord, String secondWord, String thirdWord)
    {
        this._commandWord = firstWord;
        this._secondWord = secondWord;
        this._thirdWord = thirdWord;
    }
    
    public ACommand () {}
    
    /**
     * add words in the class
     * @param firstWord
     * @param secondWord
     * @param thirdWord
     */
    public void addWords(String firstWord, String secondWord, String thirdWord) {
        this._commandWord = firstWord;
        this._secondWord = secondWord;
        this._thirdWord = thirdWord;

    }

    /**
     * get the command
     * @return
     */
    public String getCommandWord()
    {
    	return this._commandWord;
    }

    /**
     * get the secon word
     * @return
     */
    public String getSecondWord()
    {
    	return this._secondWord;
    }

    /**
     * get the third word
     * @return
     */
    public String getThirdWord()
    {
    	return this._thirdWord;
    }
    
    /**
     * check if the command is know
     * @return
     */
    public boolean isUnknown()
    {
    	if (this._commandWord == null)
    		return true;
    	else
    		return false;
    }

    /**
     * check is has a second word
     * @return
     */
    public boolean hasSecondWord()
    {
    	if (this._secondWord != null)
    		return true;
    	else
    		return false;
    }
    
    /**
     * check is has a second word
     * @return
     */
    public boolean hasThirdWord()
    {
    	if (this._thirdWord != null)
    		return true;
    	else
    		return false;
    }
    
    public abstract boolean execute(Player player);
}

