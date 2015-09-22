package Main;

import GUI.GUI;

public class Main
{
    public static void main(String[] args) {
    	String language;
    	String country;

    	if (args.length != 2)
    	{
            language = "en";
            country = "US";
        }
    	else
    	{
    		language = args[0];
    		country = args[1];
        }

    	new Game.Game(language, country).play();
    }
}