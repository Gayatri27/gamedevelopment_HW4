/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.request;

import java.io.IOException;
import java.text.ParseException;

import networking.response.ResponseSDStartGame;
import playtime.PlayManager;
import utility.DataReader;
import utility.Log;

/**
 *
 * @author anu
 */

public class RequestSDStartGame extends GameRequest {

    private int p_id1;
    private int p_id2;
    
        // Responses
    private ResponseSDStartGame responseStart;

    public RequestSDStartGame() throws ParseException {
        responses.add(responseStart = new ResponseSDStartGame());
    }

    @Override
    public void parse() throws IOException {
        p_id1 = DataReader.readInt(dataInput); 
    }

    @Override
    public void doBusiness() throws Exception {
        Log.println("request start game from user: '" + client.getUserID() + "' received");
        
        Log.println("The play the user belongs to is '" +  PlayManager.manager.getPlayByPlayerID(client.getUserID()).getID() + "'");
        
        PlayManager.manager.getPlayByPlayerID(client.getUserID()).startPlay(client.getUserID());

    }
}
