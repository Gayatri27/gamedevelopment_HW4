/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.response;

import metadata.NetworkCode;
import utility.GamePacket;

/**
 *
 * @author Karl
 */
public class ResponseSDDisconnect extends GameResponse {

    private short status;
    
    public void RepsonseSDDisconnect() {
        responseCode = NetworkCode.SD_DISCONNECT;
        status=0;
    }
    
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
                
        packet.addShort16(status);        
        
        return packet.getBytes();
    }
}
