package GUI;

import java.awt.*;

import javax.swing.*;

import Character.Player;
import Commands.Parser;

/**
 * this class is to create a GUI which content the entire GUI
 * the Contoler and the View are inside and
 * @author Stevosh
 *
 */
public class GUI
{
	static final int width = 700;
	static final int height = 600;

	/**
	 * in this Constructor we declare the GUI in thread safe
	 * @param player
	 * @param parser
	 */
	public GUI(Player player, Parser parser)
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run()
			{
				WindowFrame window = new WindowFrame(width, height);

				GamePanel gamePanel = new GamePanel(window.getContentPane(), player, parser);
				window.add(Box.createRigidArea(new Dimension(10, 10)));
			    ControlsPanel controlsPanel = new ControlsPanel(window.getContentPane(), player, parser, gamePanel);
			    window.add(Box.createRigidArea(new Dimension(10, 10)));
			}
		});
	}
}
