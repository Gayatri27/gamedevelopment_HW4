package networking.request;

import java.io.IOException;

import dataAccessLayer.DAO;
import dataAccessLayer.PlayDAO;
import dataAccessLayer.PlayInfoDAO;
import metadata.Constants;
import networking.response.ResponsePlayerFishSelection;
import playtime.PlayTimePlayer;
import utility.DataReader;

public class RequestPlayerFishSelection extends GameRequest {

	private int player_id;
	private int species_id;

	private ResponsePlayerFishSelection responsePlayerFishSelection;

    public RequestPlayerFishSelection() {
        responses.add(responsePlayerFishSelection = new ResponsePlayerFishSelection());
    }
	
	@Override
	public void parse() throws IOException {
		player_id = DataReader.readInt(dataInput);
		species_id = DataReader.readInt(dataInput);
	}

	@Override
	public void doBusiness() throws Exception {
		
		try {
			// DB
	        // check connection
	        if (DAO.getDataSource().getConnection() != null) {
	            System.out.println("Successfully connected to database.\n");
	        }
	        PlayTimePlayer playTimePlayer = PlayInfoDAO.getPlayerInfo(player_id);
	        playTimePlayer.setRunnerSpeciesID(species_id);
	        
	        PlayDAO.updatePlay(playTimePlayer);
	        
			responsePlayerFishSelection.setStatus((short) Constants.SUCCESS_STATUS_CODE); // db update successful
		} catch (Exception e) {
			e.printStackTrace();
			responsePlayerFishSelection.setStatus((short) Constants.FAILURE_STATUS_CODE); // db update not successful
		}
	}
}
