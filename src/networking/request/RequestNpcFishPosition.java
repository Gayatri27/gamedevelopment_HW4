
package networking.request;

import java.io.IOException;
import java.util.HashMap;

import core.GameServer;
import model.Prey;
import networking.response.ResponseNpcFishPosition;
import playtime.PlayManager;
import utility.DataReader;

/**
 * an update sent by the host client for movement of NPC fish.
 * @author Karl
 */
public class RequestNpcFishPosition extends GameRequest{
    private HashMap<Integer, Prey> fishMap = new HashMap<Integer, Prey>(); //prey_id -> prey object
    private int numFish;  //the number of fish in the request.
    private ResponseNpcFishPosition response;
    private int p_id;
    
    @Override
    public void parse() throws IOException {
        numFish = Integer.parseInt(DataReader.readString(dataInput));
        //System.out.println("Num fish is " + numFish);
        Prey fish;
        while(numFish > 0){
            fish = new Prey();
            fish.setPrey_id(Integer.parseInt(DataReader.readString(dataInput)));
            fish.setSpecies_id(Integer.parseInt(DataReader.readString(dataInput)));
            fish.setX(Float.parseFloat(DataReader.readString(dataInput)));
            fish.setY(Float.parseFloat(DataReader.readString(dataInput)));
            fish.setRotation(Float.parseFloat(DataReader.readString(dataInput)));
            fishMap.put(fish.getPrey_id(),fish);
            //System.out.println("Parsed. ");
            numFish--;
        }
    }

    @Override
    public void doBusiness() throws Exception {
		try{
	        response = new ResponseNpcFishPosition();
	        response.setNpcFishMap(fishMap);
	        
	        //The playerID of the opponent of the player who sent the request
	        p_id = PlayManager.manager.getPlayByPlayerID(client.getPlayer().getID())
	                .getOpponent(client.getPlayer()).getID();
	        
	        if(GameServer.getInstance().getActivePlayer(p_id) != null) {
	        	GameServer.getInstance().getThreadByPlayerID(p_id).send(response);
	        }
		} catch(Exception ex) {
			
		}
    }
}