package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Character.Player;
import Commands.ACommand;
import Commands.Parser;
import Game.Room;

/**
 * This Class is for print a Controls Panel into the GUI
 * @author Stevosh
 *
 */
public class ControlsPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private Player _player;
	private Parser _parser;
	private JButton buttonLook = new JButton("Look");
	private JButton buttonTake = new JButton("Take");
	private JButton buttonDrop = new JButton("Drop");
	private JButton buttonGive = new JButton("Give");
	private JButton buttonNorth = new JButton("North");
	private JButton buttonInventory = new JButton("Inventory");
	private JButton buttonWest = new JButton("West");
	private JButton buttonSouth = new JButton("South");
	private JButton buttonEast = new JButton("East");
	private Container window;
	private DefaultListModel model = new DefaultListModel();
	private JList list = new JList(model);
	private Graphics2D g2;
	private GamePanel _gamePanel;
	private JPanel inventory = new JPanel();

	/**
	 * Constructor
	 * @param cwindow
	 * @param player
	 * @param parser
	 * @param gamePanel
	 */
	public ControlsPanel(Container cwindow, Player player, Parser parser, GamePanel gamePanel)
	{
		super();
		
		this._gamePanel = gamePanel;
		this.window = cwindow;
		this._player = player;
		this._parser = parser;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setVisible(true);
		this.setMinimumSize(new Dimension(440,600));
		window.add(this);
		drawComponents();
	}
	
	/**
	 * @return the control panel object
	 */
	public ControlsPanel getControlsPanel()
	{
		return this;
	}
	
	/**
	 * Update method when action is made 
	 */
	public void updateGamePanel()
	{
		this._gamePanel.drawComponents(this._gamePanel.getGraphics());
		addInInventory();
		checkButtonsEnabled();
	}
	
	/**
	 * Method to draw the components of the Controls panel
	 */
	public void drawComponents()
	{
		drawInventory();
		drawControlsTable();
		addInInventory();
		checkButtonsEnabled();
	}
	
	/**
	 * Method to check if the buttons can be enabled or not
	 */
	public void checkButtonsEnabled()
	{
		Room checkNorth = this._player.getCurrentRoom().getExit("north");
		Room checkSouth = this._player.getCurrentRoom().getExit("south");
		Room checkEast = this._player.getCurrentRoom().getExit("east");
		Room checkWest = this._player.getCurrentRoom().getExit("west");
		
		// Check Exits to print or not buttons
		if (checkNorth == null)
			this.buttonNorth.setEnabled(false);
		else
			this.buttonNorth.setEnabled(true);
		if (checkSouth == null)
			this.buttonSouth.setEnabled(false);
		else
			this.buttonSouth.setEnabled(true);
		if (checkEast == null)
			this.buttonEast.setEnabled(false);
		else
			this.buttonEast.setEnabled(true);
		if (checkWest == null)
			this.buttonWest.setEnabled(false);
		else
			this.buttonWest.setEnabled(true);
		// Check if the player can take and print the button
		if (this._player.getCurrentRoom().getAllItems().size() <= 0)
			this.buttonTake.setEnabled(false);
		else
			this.buttonTake.setEnabled(true);
		// Check if the player can drop and print the button
		if (this._player.getInventory().size() <= 0)
			this.buttonDrop.setEnabled(false);
		else
			this.buttonDrop.setEnabled(true);
		// Check if the player can give and print the button
		if ((this._player.getInventory().size() <= 0) || (this._player.getCurrentRoom().getListCharacters().size() <= 0))
			this.buttonGive.setEnabled(false);
		else
			this.buttonGive.setEnabled(true);
		// Check if the player have an inventory
		if (this._player.getInventory().isEmpty())
			this.buttonInventory.setEnabled(false);
		else
			this.buttonInventory.setEnabled(true);
	}
	
	/**
	 * Method to add Items to the inventory GUI
	 */
	public void addInInventory()
	{
		model.clear();
		for (String mapKey : this._player.getCurrentRoom().getAllItems().keySet())
		{
			model.addElement(mapKey);
		}
	}
	
	/**
	 * Method to draw the inventory list with the commands buttons
	 */
	public void drawInventory()
	{
		inventory.setLayout(new BoxLayout(inventory, BoxLayout.Y_AXIS));
		
		JScrollPane scrollList = new JScrollPane(list);
		DefaultListCellRenderer renderer = null;
		renderer = (DefaultListCellRenderer)list.getCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
		Border empty = BorderFactory.createEmptyBorder();
		TitledBorder title = BorderFactory.createTitledBorder(empty, "Items in Room");
		title.setTitleJustification(TitledBorder.CENTER);
		scrollList.setBorder(title);
		inventory.add(scrollList);
		
		// an empty space to design
		inventory.add(Box.createRigidArea(new Dimension(10, 10)));
		// new panel to contain the tool bar
		JPanel toolBar = new JPanel();
		toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));
		toolBar.setVisible(true);
		// Set buttons from the tool bar with action listeners
		//		add the look button
		buttonLook.addActionListener(this);
		toolBar.add(buttonLook);
		// 		add the look button
		buttonTake.addActionListener(this);
		toolBar.add(buttonTake);
		//		 add the look button
		buttonDrop.addActionListener(this);
		toolBar.add(buttonDrop);
		// 		add the look button
		buttonGive.addActionListener(this);
		toolBar.add(buttonGive);
		// 		add the toolBar in the inventory  and the inventory in the control panel
		inventory.add(toolBar);
		this.add(inventory);
		// an empty space to design
		inventory.add(Box.createRigidArea(new Dimension(10, 10)));
		// add the control panel in the window
		window.add(this);
	}
	
	/**
	 * Method to draw the Controllers table
	 */
	public void drawControlsTable()
	{
		// add a panel to contain the controls move actions
		JPanel controlerTable = new JPanel();
		controlerTable.setPreferredSize(new Dimension(120, 200));
		controlerTable.setMaximumSize(getPreferredSize());
		controlerTable.setLayout(new BorderLayout());
		
		// Set buttons from the controls move
		// 		set the North Button
		this.buttonNorth.setPreferredSize(new Dimension(20, 60));
		this.buttonNorth.setMaximumSize(getPreferredSize());
		this.buttonNorth.addActionListener(this);
		controlerTable.add(this.buttonNorth, BorderLayout.PAGE_START);
		// 		set the Inventory Button
		this.buttonInventory.setPreferredSize(new Dimension(10, 10));
		this.buttonInventory.setMaximumSize(getPreferredSize());
		this.buttonInventory.addActionListener(this);
        controlerTable.add(this.buttonInventory, BorderLayout.CENTER);
        // 		set the West Button
        this.buttonWest.setPreferredSize(new Dimension(80, 10));
        this.buttonWest.setMaximumSize(getPreferredSize());
        this.buttonWest.addActionListener(this);
        controlerTable.add(this.buttonWest, BorderLayout.LINE_START);
        // 		set the South Button 
        this.buttonSouth.setPreferredSize(new Dimension(10, 60));
        this.buttonSouth.setMaximumSize(getPreferredSize());
        this.buttonSouth.addActionListener(this);
        controlerTable.add(this.buttonSouth, BorderLayout.PAGE_END);
        // 		set the East Button 
        this.buttonEast.setPreferredSize(new Dimension(80, 10));
        this.buttonEast.setMaximumSize(getPreferredSize());
        this.buttonEast.addActionListener(this);
        controlerTable.add(this.buttonEast, BorderLayout.LINE_END);
        // 		add the controler table to the controler component panel
        this.add(controlerTable);
		// an empty space to design
        this.add(Box.createRigidArea(new Dimension(10, 10)));
        // add the control panel in the window
        window.add(this);
	}
	
	/**
	 * Method overide to catch events to do actions
	 */
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		updateGamePanel();
		// Button Look
		if(source == this.buttonLook)
		{
			ACommand command = this._parser.getCommandGUI("look");
			command.execute(this._player);
		}
		// Button Take
		else if (source == this.buttonTake)
		{
			if (this._player.getCurrentRoom().getAllItems().size() > 0)
			{
				//what item to take
				int i = 0;
				String[] tab = new String[this._player.getCurrentRoom().getAllItems().size()];
				for (String mapKey : this._player.getCurrentRoom().getAllItems().keySet())
				{
					tab[i] = mapKey;
					++i;
				}
				String item = (String)JOptionPane.showInputDialog(
	                    this.window,
	                    "Choose an item to Take",
	                    "Take Command",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    tab,
	                    tab[0]);
				
				ACommand command = this._parser.getCommandGUI("take" + " " + item);
				command.execute(this._player);
			}
		}
		// Button Drop
		else if(source == this.buttonDrop)
		{
			if (this._player.getInventory().size() > 0)
			{
				// what item to drop
				int i = 0;
				String[] tab = new String[this._player.getInventory().size()];
				for (String mapKey : this._player.getInventory().keySet())
				{
					tab[i] = mapKey;
					++i;
				}
				String item = (String)JOptionPane.showInputDialog(
	                    this.window,
	                    "Choose an item to Drop",
	                    "Drop Command",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    tab,
	                    tab[0]);
				ACommand command = this._parser.getCommandGUI("drop" + " " + item);
				command.execute(this._player);
			}
		}
		// Button Give
		else if(source == this.buttonGive)
		{
			if (this._player.getInventory().size() > 0)
			{
				// What item to give
				int i = 0;
				String[] tab = new String[this._player.getInventory().size()];
				for (String mapKey : this._player.getInventory().keySet())
				{
					tab[i] = mapKey;
					++i;
				}
				String item = (String)JOptionPane.showInputDialog(
	                    this.window,
	                    "Choose an item to Drop",
	                    "Drop Command",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    tab,
	                    tab[0]);
				if (this._player.getCurrentRoom().getListCharacters().size() > 0)
				{
					// What Player to give
					int j = 0;
					String[] tab2 = new String[this._player.getCurrentRoom().getListCharacters().size()];
					for (String mapKey2 : this._player.getCurrentRoom().getListCharacters().keySet())
					{
						tab2[j] = mapKey2;
						++j;
					}
					String who = (String)JOptionPane.showInputDialog(
		                    this.window,
		                    "Choose an item to Drop",
		                    "Drop Command",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    tab2,
		                    tab2[0]);
					// create Command
					ACommand command = this._parser.getCommandGUI("give" + " " + item + " " + who);
					command.execute(this._player);
				}
			}
		}
		// Button North
		else if(source == this.buttonNorth)
		{
			ACommand command = this._parser.getCommandGUI("go north");
			command.execute(this._player);
		}
		// Button Inventory
		else if(source == this.buttonInventory)
		{
			if (this._player.getInventory().size() > 0)
			{
				// What item to give
				int i = 0;
				String[] tab = new String[this._player.getInventory().size()];
				for (String mapKey : this._player.getInventory().keySet())
				{
					tab[i] = mapKey;
					++i;
				}
				String info = "Weight : " + this._player.getTotalWeight() + " / " + this._player.getMaxWeight();
				String item = (String)JOptionPane.showInputDialog(
	                    this.window,
	                    info,
	                    "INVENTORY",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    tab,
	                    tab[0]);
			}
		}
		// Button West
		else if(source == this.buttonWest) 
		{
			ACommand command = this._parser.getCommandGUI("go west");
			command.execute(this._player);
		}
		// Button South
		else if(source == this.buttonSouth)
		{
			ACommand command = this._parser.getCommandGUI("go south");
			command.execute(this._player);
		}
		// Button East
		else if(source == this.buttonEast)
		{
			ACommand command = this._parser.getCommandGUI("go east");
			command.execute(this._player);
		}
		updateGamePanel();
	}
}
