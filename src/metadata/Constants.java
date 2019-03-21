package metadata;

/**
 * The Constants class stores important variables as constants for later use.
 */
public class Constants {

    // Request (1xx) + Response (2xx)
    public final static short CMSG_AUTH = 101;
    public final static short SMSG_AUTH = 201;
    public final static short CMSG_HEARTBEAT = 102;
    public final static short SMSG_HEARTBEAT = 202;
    public final static short CMSG_PLAYERS = 103;
    public final static short SMSG_PLAYERS = 203;
    public static final short CMSG_TEST = 104;
    public static final short SMSG_TEST = 204;
    public static final short CMSG_GAMETIMER = 105;
    public static final short SMSG_GAMETIMER = 205;
    
    public static final int SUCCESS_STATUS_CODE = 0;
    public static final int FAILURE_STATUS_CODE = 1;
    
    public static final String CLIENT_VERSION = "1.00";
    public static final String CSV_SAVE_PATH = "src/log/";
    
    // for SeaDivided
    public static final int TIMEOUT_SECONDS = 90;
    public static final short MAX_NUMBER_OF_PLAYERS = 2;
    public static final int NUM_PREY = 40;
    public static final int X_MIN = -120;
    public static final int X_MAX = 120;
    public static final int Y_MIN = -75;
    public static final int Y_MAX = 75;
    public static final int MIN_PREY = 20;
    public static final int CREDIT_REWARD = 50;
}