package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import Character.Player;
import Commands.Parser;
import Game.Room;

/**
 * This class is a Jpanel class to contain the view of the game
 * into the GUI
 * @author Stevosh
 *
 */
public class GamePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	static final int width = 450;
	static final int height = 600;
	Player _player;
	Parser _parser;
	private Container window;
	private ImageLoader loader = new ImageLoader();
	
	/**
	 * Constructor with set up parameters
	 * @param cwindow
	 * @param player
	 * @param parser
	 */
	public GamePanel(Container cwindow, Player player, Parser parser)
	{
		super();
		
		this._player = player;
		this._parser = parser;
		this.window = cwindow;
		this.setPreferredSize(new Dimension(width,height));
		this.setMaximumSize(getPreferredSize());
		this.setBackground(Color.GRAY);
		this.setVisible(true);
		window.add(this);
	}
	
	/**
	 * Method to Draw Components inside the Jpanel window
	 * the update with some actions call this method
	 * @param graphic
	 */
	public void drawComponents(Graphics graphic)
	{
		graphic.clearRect(0, 0, getWidth(), getHeight());
		loader.loadImage("SolInside.jpg");
		graphic.drawImage(loader.getImage(), 0,0, 416,572,null);
		//DrawWalls
		drawWalls(graphic);
		//DrawDoors and Player
		drawDoors((Graphics2D)graphic);
		drawPlayer((Graphics2D)graphic);
	}
	
	/**
	 * overide method to paint the components
	 */
	public void paintComponent(Graphics graphic)
	{
		graphic.clearRect(0, 0, getWidth(), getHeight());
		loader.loadImage("SolInside.jpg");
		graphic.drawImage(loader.getImage(), 0,0, 416,572,null);
		drawComponents(graphic);
	}
	
	/**
	 * this method draw the player and other characters 
	 * in the room
	 * @param g2
	 */
	public void drawPlayer(Graphics2D g2)
	{
		loader.loadImage("player.png");
		g2.drawImage(loader.getImage(), 416 / 2 - 22, 572 / 2 - 27, 45,55,null);
		if (this._player.getCurrentRoom().getListCharacters().size() > 0)
		{
			loader.loadImage("gandalf.png");
			g2.drawImage(loader.getImage(), 416 / 2 - 22, 572 / 2 - 140, 75,85,null);
		}
		if (this._player.getCurrentRoom().getAllItems().size() > 0)
		{
			loader.loadImage("treasure.gif");
			g2.drawImage(loader.getImage(), 416 / 2 - 15, 572 / 2 + 35, 32,32,null);
		}
	}
	
	/**
	 * this method draw the walls around the map
	 * @param graphic
	 */
	public void drawWalls(Graphics graphic)
	{
		// drawWalls Left and right
		loader.loadImage("WallSide.jpg");
		graphic.drawImage(loader.getImage(), 0,0, 23,572,null);
		loader.loadImage("WallSide.jpg");
		graphic.drawImage(loader.getImage(), 416 - 23,0, 23,572,null);
		// drawWalls top and bot
		loader.loadImage("WallTop.jpg");
		graphic.drawImage(loader.getImage(), 0,0, 572,23,null);
		loader.loadImage("WallTop.jpg");
		graphic.drawImage(loader.getImage(), 0,572 - 23, 416,23,null);
	}
	
	/**
	 * this method draw the doors when there are a door
	 * @param g2
	 */
	public void drawDoors(Graphics2D g2)
	{
		Room checkNorth = this._player.getCurrentRoom().getExit("north");
		Room checkSouth = this._player.getCurrentRoom().getExit("south");
		Room checkEast = this._player.getCurrentRoom().getExit("east");
		Room checkWest = this._player.getCurrentRoom().getExit("west");
		
		if (checkNorth != null)
		{
			loader.loadImage("porteNorth.jpg");
			g2.drawImage(loader.getImage(), (416 / 2) - 23, 0, 45, 60, null);
		}
		if (checkSouth != null)
		{

			loader.loadImage("porteSouth.jpg");
			g2.drawImage(loader.getImage(),(416 / 2) - 23, (572 - 60), 45, 60, null);
		}
		if (checkEast != null)
		{
			loader.loadImage("porteEast.jpg");
			g2.drawImage(loader.getImage(),(416 - 60) ,(572 / 2) - 23, 60, 45, null);
		}
		if (checkWest != null)
		{
			loader.loadImage("porteWest.jpg");
			g2.drawImage(loader.getImage(), 0, (572 / 2) - 23, 60, 45, null);
		}
		
	}
}
