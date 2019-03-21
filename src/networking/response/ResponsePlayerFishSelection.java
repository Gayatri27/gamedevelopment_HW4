package networking.response;

import metadata.NetworkCode;
import utility.GamePacket;

public class ResponsePlayerFishSelection extends GameResponse {
	
	private short status;

    public ResponsePlayerFishSelection() {
        responseCode = NetworkCode.SD_PLAYER_FISH_SELECTION;
    }

    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addShort16(status);
        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }
}

