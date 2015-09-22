package GUI;

import java.awt.Color;
import java.awt.Container;

import javax.swing.*;

/**
 *  This class create a window extends to JFrame
 *  its the main window of the GUI
 * @author Stevosh
 *
 */
public class WindowFrame extends JFrame
{
	/**
	 * Constructor
	 * @param width
	 * @param height
	 */
	public WindowFrame(int width, int height)
	{
		super();
		
		setUp(width, height);
	}

	/**
	 * Set up the parameters for the main window
	 * @param width
	 * @param height
	 */
	private void setUp(int width, int height)
	{
		Container container = new Container();
		this.setTitle("Zuul Game");
		this.setSize(width, height);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		container = this.getContentPane();
		container.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * get the window
	 * @return
	 */
	public WindowFrame getWindow()
	{
		return this;
	}
}
