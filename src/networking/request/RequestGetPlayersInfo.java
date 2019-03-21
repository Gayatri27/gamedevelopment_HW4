package networking.request;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import dataAccessLayer.PlayInfoDAO;
import metadata.Constants;
import networking.response.ResponseGetPlayersInfo;
import playtime.PlayTimePlayer;
import utility.DataReader;

public class RequestGetPlayersInfo extends GameRequest {

	private int play_id;
	
	private ResponseGetPlayersInfo responseGetPlayersInfo;

    public RequestGetPlayersInfo() {
        responses.add(responseGetPlayersInfo = new ResponseGetPlayersInfo());
    }
	
	@Override
	public void parse() throws IOException {
		play_id = DataReader.readInt(dataInput);
	}

	@Override
	public void doBusiness() throws Exception {
		
		try {
			Map<Integer, PlayTimePlayer> allPlayersInfo = PlayInfoDAO.getAllPlayersInfo(play_id);
			
			int count = 0;
			Iterator<Entry<Integer, PlayTimePlayer>> it = allPlayersInfo.entrySet().iterator();
		    while (it.hasNext()) {
		    		Map.Entry pair = (Map.Entry) it.next();
	    			PlayTimePlayer playTimePlayer = (PlayTimePlayer) pair.getValue();

		    		if(count == 0) {
		    			responseGetPlayersInfo.setPlayerId(playTimePlayer.getID());
		    			responseGetPlayersInfo.setPreyId(playTimePlayer.getRunnerSpeciesID());
		    			count++;
		    		} else if(count == 1) {
		    			responseGetPlayersInfo.setOpponentPlayerId(playTimePlayer.getID());
		    			responseGetPlayersInfo.setOpponentPreyId(playTimePlayer.getRunnerSpeciesID());
		    		}
		        
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		    
			responseGetPlayersInfo.setStatus((short) Constants.FAILURE_STATUS_CODE);
		} catch (Exception e) {
			e.printStackTrace();
			responseGetPlayersInfo.setStatus((short) Constants.FAILURE_STATUS_CODE);
		}
	}
}