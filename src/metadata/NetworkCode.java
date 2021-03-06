package metadata;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import utility.Log;

public class NetworkCode {

	// Request + Response
    public final static short CLIENT = 100;
    public final static short HEARTBEAT = 101;
    public final static short ACTIVITY = 102;
    public final static short LOGIN = 103;
    public final static short LOGOUT = 104;
    public final static short REGISTER = 105;
    public final static short ERROR_LOG = 106;
    public final static short MESSAGE = 107;

    public final static short PLAYERS = 108;
    public final static short SPECIES_LIST = 109;
    public final static short WORLD = 110;
    public final static short ZONE_LIST = 111;
    public final static short ZONE = 112;
    public final static short ZONE_UPDATE = 113;
    public final static short ECOSYSTEM = 114;
    public final static short PREDICTION = 115;
    
    public final static short SHOP = 116;
    public final static short SHOP_ACTION = 117;
    public final static short TILE_SHOP_ACTION = 1172;
    
    public final static short TILE_PURCHASE = 301;
    public final static short TILE_PRICE = 302;
    public final static short CHAT = 333;

    public final static short STATUS = 1171;
    public final static short PARAMS = 118;
    public final static short CHANGE_PARAMETERS = 119;
    public final static short GET_FUNCTIONAL_PARAMETERS = 120;
    public final static short CHANGE_FUNCTIONAL_PARAMETERS = 121;
    public final static short STATISTICS = 122;
    public final static short HIGH_SCORE = 123;
    public final static short CHART = 124;
    public final static short SPECIES_ACTION = 125;
    public final static short BADGE_LIST = 126;

    public final static short UPDATE_RESOURCES = 134;
    public final static short SPECIES_KILL = 135;
    public final static short UPDATE_TIME = 136;
    public final static short SPECIES_CREATE = 137;
    public final static short OBJECTIVE_ACTION = 138;
    public final static short UPDATE_ENV_SCORE = 139;
    public final static short UPDATE_LEVEL = 140;
    public final static short BADGE_UPDATE = 141;
    public final static short UPDATE_SEASON = 142;
    public final static short UPDATE_CURRENT_EVENT = 143;
    public final static short BATTLE_END = 144;
        
    public final static short PLAYER_SELECT = 145;
    
    public final static short SPECIES_INFO = 190;

    public final static short TOPLIST = 153;    
    public final static short WAITFORGAME = 154;
    public final static short NOWAITFORGAME = 155;
    public final static short WAITLIST = 156;
    public final static short WAITSTATUS = 157;
    public final static short STARTGAME = 158;
    
    public final static short PAIR = 159;
    public final static short QUIT_ROOM = 160;
    public final static short GET_ROOMS = 161;
    
    public final static short BACK_TO_LOBBY = 192;
    public final static short PLAY_GAME = 193;
    public final static short END_GAME = 194;

    // Sea Divided
    public final static short SD_GAME_LOGIN = 400;
    public final static short SD_PLAY_INIT = 401;
    public final static short SD_START_GAME = 402;
    public final static short SD_END_GAME = 403;
    public final static short SD_KEYBOARD = 404;
    public final static short SD_PLAYER_POSITION = 405;
    public final static short SD_PREY = 406;
    public final static short SD_EAT_PREY = 407;
    public final static short SD_SCORE = 408;
    public final static short SD_DISCONNECT = 409;
    public final static short SD_RECONNECT = 410;
    public final static short SD_HEARTBEAT = 411;
    public final static short SD_NPCPOSITION = 412;
    public final static short SD_RESPAWN = 413;
    public final static short SD_GAME_LOGOUT = 414;
    public final static short SD_PLAYER_FISH_SELECTION = 415;
    public final static short SD_GET_PLAYERS_INFO = 416;

    /**
     * Check for duplicate values, if any.
     */
    public static void check() {
        NetworkCode nCodes = new NetworkCode();
        Map<Short, String> nCodeMap = new HashMap<Short, String>();

        for (Field field : NetworkCode.class.getDeclaredFields()) {
            try {
                Short value = (Short) field.get(nCodes);

                if (nCodeMap.containsKey(value)) {
                    Log.println_e(field.getName() + " is conflicting with " + nCodeMap.get(value));
                } else {
                    nCodeMap.put(value, field.getName());
                }
            } catch (IllegalArgumentException ex) {
                Log.println_e(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Log.println_e(ex.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        String str = "";

        str += "-----" + "\n";
        str += getClass().getName() + "\n";
        str += "\n";

        for (Field field : getClass().getDeclaredFields()) {
            try {
                str += field.getName() + " - " + field.get(this) + "\n";
            } catch (IllegalArgumentException ex) {
                Log.println_e(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Log.println_e(ex.getMessage());
            }
        }

        str += "-----";

        return str;
    }
}
