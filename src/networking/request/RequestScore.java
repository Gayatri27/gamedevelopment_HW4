/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.request;

import java.io.IOException;

import core.GameServer;
import networking.response.ResponseScore;
import playtime.PlayManager;
import playtime.PlayTimePlayer;
import utility.DataReader;

/**
 *
 * @author anu
 */
public class RequestScore extends GameRequest{
    
     // Data
    private float Score;
    private int p_id;
    private int opponent_id;
 
    // Responses
    private ResponseScore responseScore;

    public RequestScore() {
       //responses.add(responseScore = new ResponseScore());
    }

    @Override
    public void parse() throws IOException {
        Score = DataReader.readFloat(dataInput);
     
    }

    @Override
    public void doBusiness() throws Exception {
        PlayTimePlayer player;
        p_id = client.getPlayer().getID();
        player =  PlayManager.manager.getPlayByPlayerID(p_id).getPlayer(p_id);
        player.setScore(Score);
        responseScore = new ResponseScore();
        responseScore.setScore((int)Score);

        opponent_id = PlayManager.manager.getPlayByPlayerID(p_id)
                .getOpponent(player).getID();
        GameServer.getInstance().getThreadByPlayerID(opponent_id).send(responseScore);
        
    }
}
