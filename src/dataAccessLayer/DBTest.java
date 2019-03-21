package dataAccessLayer;

import java.util.HashMap;
import java.util.Map;

import model.Prey;
import model.SDSpecies;
import playtime.PlayTimePlayer;
import utility.Log;

public class DBTest {

	public static void main(String[] args) {
        Map<Integer, SDSpecies> playSpecies;
        PlayTimePlayer r1, r2, rx;
        int i;
        Prey prey;
        Map<Integer, Prey> preyMap = new HashMap<Integer,Prey>();
        try {
            GameDB gameDB =  new GameDB();
            
            // check connection
            if (DAO.getDataSource().getConnection() != null) {
                System.out.println("Successfully connected to database.\n");
            }
            playSpecies = SDSpeciesDAO.getSDSpecies();
            Log.println("max health of id 90 (mackerel):"+playSpecies.get(90).getHealthMax());
            // define two race players
            r1 = new PlayTimePlayer(1, 1);
            r2 = new PlayTimePlayer(2, 1);
            rx = new PlayTimePlayer(3);
            for(i=2; i<20; ) {
                prey = new Prey(i,0.0F,0.0F);
                preyMap.put(i, prey);
                i += 1;
            }
            prey = new Prey(42,0.0F,0.0F);

            // test race creation, joining, finishing, leaving logic
            //player 1 makes race, player 2 joins
            PlayDAO.createPlay(r1.getRaceID());
            PlayDAO.createPlayer(r1.getID(), r1.getRaceID(),2);
            PlayDAO.createPlayer(r2.getID(), r2.getRaceID(),1);
            
            PlayDAO.leavePlay(r1.getID(),r1.getRaceID());
            PlayDAO.leavePlay(r2.getID(),r2.getRaceID());
            PlayDAO.endPlay(r1.getRaceID(),r1.getID(), 80);
            
        } catch (Exception e) {
            System.err.println("Error in database connection.");
        }
    }
}
