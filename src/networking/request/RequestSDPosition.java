/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.request;

import java.io.IOException;

import core.GameServer;
import networking.response.ResponseSDPosition;
import playtime.PlayManager;
import playtime.PlayTimePlayer;
import utility.DataReader;

/**
 *
 * @author anu
 */
public class RequestSDPosition extends GameRequest{
    private float x, y, rotation;
    private int p_id;
    private ResponseSDPosition responseSDPosition;
    
    @Override
    public void parse() throws IOException {
        x = Float.parseFloat(DataReader.readString(dataInput));
        y = Float.parseFloat(DataReader.readString(dataInput));
        rotation = Float.parseFloat(DataReader.readString(dataInput));
    }

    @Override
    public void doBusiness() throws Exception {
		try {
	        PlayTimePlayer player; // the RacePlayer sending the request
	        System.out.println("X:  " +  x + "Y :  " + y + "Rotation " + rotation);
	   
	        responseSDPosition = new ResponseSDPosition();
	        responseSDPosition.setX(x);
	        responseSDPosition.setY(y);
	        responseSDPosition.setRotation(rotation);
	        
	
	   
	        //The playerID of the opponent of the player who sent the request
	        p_id = PlayManager.manager.getPlayByPlayerID(client.getPlayer().getID())
	                .getOpponent(client.getPlayer()).getID();
	              
	        
	        // get the player and define x and y at the time of request
	        player = PlayManager.manager.getPlayByPlayerID(p_id).getPlayers().get(p_id);
	        player.setX(x);
	        player.setY(y);
	        player.setRotation(rotation);
	        //PlayDAO.updatePlay(player); this is slowing things substanitially
			if(GameServer.getInstance().getActivePlayer(p_id) != null){
				GameServer.getInstance().getThreadByPlayerID(p_id).send(responseSDPosition);
		    }
	    } catch(Exception ex) {
	    	
	    }  
    }
}
