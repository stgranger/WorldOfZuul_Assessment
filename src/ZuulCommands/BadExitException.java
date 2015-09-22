package ZuulCommands;

import Game.Room;

/**
 * 
 * this class is for control the bad exit exception
 *
 */
public class BadExitException extends Exception {

    private static final long serialVersionUID = -6960984107626797656L;

    public BadExitException(String direction, Room r)
    {
        super(direction == null && r == null
                ? "Direction and room are null"
                : direction == null
                        ? "Direction is null"
                        : r == null
                                ? "Room is null"
                                : "Something bad about an exit");
    }
}
