package csivit.rakshith.lasertag;

/**
 *
 * @author rakshith
 */
public class Team {
    private final Player[] players = new Player[5];
    private int teamNumber;
    
    public Player[] getPlayers() {
        return players;
    }

    public int getTeamNumber() {
        return teamNumber;
    }
    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }
    
    public int getCount() {
        int count = 0;
        for (Player player : players) {
            if (player != null) {
                count++;
            }
        }
        return count;
    }
    
    public boolean hasGirls() {
        for(Player player : players) {
            if(player.getGender() == 'f')
                return true;
        }
        return false;
    }
}
