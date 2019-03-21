/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.request;

import java.io.IOException;

import core.GameServer;
import networking.response.ResponseSDKeyboard;
import playtime.PlayManager;
import playtime.PlayTimePlayer;
import utility.DataReader;

/**
 *
 * @author anu
 */
public class RequestSDKeyboard extends GameRequest {
    
    private int keyCode,keyCombination;
    private int p_id;
    private  ResponseSDKeyboard responsekeyboard;
    
       
    public void parse() throws IOException {
        keyCode = DataReader.readInt(dataInput);
        keyCombination = DataReader.readInt(dataInput);
    }
    
    public void doBusiness() throws Exception {
        PlayTimePlayer player;
      //  System.out.println("key type:  " +  keytype + "key :  " + key);
        
        responsekeyboard = new ResponseSDKeyboard();
        responsekeyboard.setKeyCode(keyCode);
        responsekeyboard.setKeyCombination(keyCombination);
        
        p_id = PlayManager.manager.getPlayByPlayerID(client.getPlayer().getID())
                .getOpponent(client.getPlayer()).getID();
        
        player = PlayManager.manager.getPlayByPlayerID(p_id).getPlayers().get(p_id);
        
        GameServer.getInstance().getThreadByPlayerID(p_id).send(responsekeyboard);

    }
    
}
