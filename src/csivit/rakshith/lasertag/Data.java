package csivit.rakshith.lasertag;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author rakshith
 */
public class Data {
    public static List<Player> AllPlayers = new ArrayList<>();
    public static List<Player> UnassignedPlayers = new ArrayList<>();
    public static List<Team> AllTeams = new ArrayList<>();
    public static List<Team> UnassignedTeams = new ArrayList<>();
    public static Team[][] Friday = new Team[8][14];
    public static Team[][] Saturday = new Team[8][14];
    public static Team[][] Sunday = new Team[8][14];
    
    public static Player findPlayer(String registrationNumber) {
        for(Player player : AllPlayers) {
            if(player.getRegistrationNumber().equals(registrationNumber)) {
                return player;
            }
        }
        return null;
    }
    
    public static Team findTeam(int teamNumber) {
        for(Team team : AllTeams) {
            if(team.getTeamNumber() == teamNumber) {
                return team;
            }
        }
        return null;
    }
    
    public static void importFromExcel(File file) {
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            Sheet sheet = workbook.getSheet(0);
            // first row is name
            // second is registration number
            // third is email
            // fourth is phone number
            // fifth is gender
            int length = sheet.getRows();
            for(int i = 1; i < length; i++) {
                Player player = new Player();
                player.setName(sheet.getCell(0, i).getContents());
                player.setRegistrationNumber(sheet.getCell(1, i).getContents());
                player.setEmail(sheet.getCell(2, i).getContents());
                player.setPhoneNumber(sheet.getCell(3, i).getContents());
                player.setGender("f".equals(sheet.getCell(4, i).getContents().toLowerCase()) ? 'f' : 'm');
                AllPlayers.add(player);
            }
        } catch (IOException | BiffException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void save() {
        try {
            // Save teams
            File teamsFile = new File("teams.txt");
            if(!teamsFile.exists())
                teamsFile.createNewFile();
            
            // Save teams
            BufferedWriter teamsWriter = new BufferedWriter(new FileWriter(teamsFile));
            for(Team team : Data.AllTeams) {
                String line = team.getTeamNumber() + "";
                for(Player player : team.getPlayers()) {
                    if(player != null)
                        line += "::" + player.getRegistrationNumber();
                }
                teamsWriter.write(line);
                teamsWriter.newLine();
            }
            teamsWriter.close();
            
            // Save friday slots
            File fridayFile = new File("friday.txt");
            if(!fridayFile.exists())
                fridayFile.createNewFile();
            BufferedWriter fridayWriter = new BufferedWriter(new FileWriter(fridayFile));
            for(Team[] teams : Data.Friday) {
                String line = "";
                for(int i = 0; i < teams.length; i++) {
                    if(teams[i] != null) {
                        if(i == 0) {
                            line += teams[i].getTeamNumber();
                        } else {
                            line += "::" + teams[i].getTeamNumber();
                        }
                    }
                }
                fridayWriter.write(line);
                fridayWriter.newLine();
            }
            fridayWriter.close();
            
            // Save saturday slots
            File saturdayFile = new File("saturday.txt");
            if(!saturdayFile.exists())
                saturdayFile.createNewFile();
            BufferedWriter saturdayWriter = new BufferedWriter(new FileWriter(saturdayFile));
            for(Team[] teams : Data.Saturday) {
                String line = "";
                for(int i = 0; i < teams.length; i++) {
                    if(teams[i] != null) {
                        if(i == 0) {
                            line += teams[i].getTeamNumber();
                        } else {
                            line += "::" + teams[i].getTeamNumber();
                        }
                    }
                }
                saturdayWriter.write(line);
                saturdayWriter.newLine();
            }
            saturdayWriter.close();
            
            // Save sunday slots
            File sundayFile = new File("sunday.txt");
            if(!sundayFile.exists())
                sundayFile.createNewFile();
            BufferedWriter sundayWriter = new BufferedWriter(new FileWriter(sundayFile));
            for(Team[] teams : Data.Sunday) {
                String line = "";
                for(int i = 0; i < teams.length; i++) {
                    if(teams[i] != null) {
                        if(i == 0) {
                            line += teams[i].getTeamNumber();
                        } else {
                            line += "::" + teams[i].getTeamNumber();
                        }
                    }
                }
                sundayWriter.write(line);
                sundayWriter.newLine();
            }
            sundayWriter.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void load() {
        try {
            importFromExcel(new File("Players.xls"));
            
            UnassignedPlayers.addAll(AllPlayers);
            
            // assign players to teams
            File playersFile = new File("teams.txt");
            if(!playersFile.exists())
                playersFile.createNewFile();
            String line;
            BufferedReader playersReader = new BufferedReader(new FileReader(playersFile));
            while((line = playersReader.readLine()) != null) {
                String[] lines = line.split("::");
                Team team = new Team();
                team.setTeamNumber(Integer.parseInt(lines[0]));
                for(int i = 1; i < lines.length; i++) {
                    Player player = Data.findPlayer(lines[i]);
                    team.getPlayers()[i - 1] = player;
                    UnassignedPlayers.remove(player);
                }
                AllTeams.add(team);
            }
            UnassignedTeams.addAll(AllTeams);
            
            // assign teams to slots
            File fridayFile = new File("friday.txt");
            if(!fridayFile.exists())
                fridayFile.createNewFile();
            BufferedReader fridayReader = new BufferedReader(new FileReader(fridayFile));
            int slot = 0;
            while((line = fridayReader.readLine()) != null) {
                if(!"".equals(line)) {
                    String[] lines = line.split("::");
                    for(int i = 0; i < lines.length; i++) {
                        Team team = Data.findTeam(Integer.parseInt(lines[i]));
                        Friday[slot][i] = team;
                        UnassignedTeams.remove(team);
                    }
                }
                slot++;
            }
            
            File saturdayFile = new File("saturday.txt");
            if(!saturdayFile.exists())
                saturdayFile.createNewFile();
            BufferedReader saturdayReader = new BufferedReader(new FileReader(saturdayFile));
            slot = 0;
            while((line = saturdayReader.readLine()) != null) {
                if(!"".equals(line)) {
                    String[] lines = line.split("::");
                    for(int i = 0; i < lines.length; i++) {
                        Team team = Data.findTeam(Integer.parseInt(lines[i]));
                        Saturday[slot][i] = team;
                        UnassignedTeams.remove(team);
                    }
                }
                slot++;
            }
            
            File sundayFile = new File("saturday.txt");
            if(!sundayFile.exists())
                sundayFile.createNewFile();
            BufferedReader sundayReader = new BufferedReader(new FileReader(sundayFile));
            slot = 0;
            while((line = sundayReader.readLine()) != null) {
                if(!"".equals(line)) {
                    String[] lines = line.split("::");
                    for(int i = 0; i < lines.length; i++) {
                        Team team = Data.findTeam(Integer.parseInt(lines[i]));
                        Sunday[slot][i] = team;
                        UnassignedTeams.remove(team);
                    }
                }
                slot++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
