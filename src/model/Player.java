package model;

// Other Imports
import core.GameClient;

/**
 * The Player class holds important information about the player including, most
 * importantly, the account. Such information includes the username, password,
 * email, and the player ID.
 */
public class Player {

    private int player_id;
    private String username;
    private String password;
    private GameClient client; // References GameClient instance
    protected String opponentClientSessionID;
    private int selected_prey_id;

    public Player(int player_id) {
        this.player_id = player_id;
    }

    public Player(int player_id, String username, String password) {
        this.player_id = player_id;
        this.username = username;
        this.password = password;
    }

    public int getID() {
        return player_id;
    }

    public int setID(int player_id) {
        return this.player_id = player_id;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        return this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String setUsername(String username) {
        return this.username = username;
    }

    public GameClient getClient() {
        return client;
    }

    public GameClient setClient(GameClient client) {
        return this.client = client;
    }
    
    public int getSelectedFishId() {
        return selected_prey_id;
    }

    public int setSelectedFishId(int prey_id) {
        return this.selected_prey_id = prey_id;
    }
    
    /**
     * @return the opponentClientSessionID
     */
    public String getOpponentClientSessionID() {
        return opponentClientSessionID;
    }

    /**
     * @param opponentClientSessionID the opponentClientSessionID to set
     */
    public void setOpponentClientSessionID(String opponentClientSessionID) {
        this.opponentClientSessionID = opponentClientSessionID;
    }

    @Override
    public String toString() {
        return "Player{" +
                "player_id=" + player_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
