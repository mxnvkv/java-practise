package JSONtoObjectConverter;

import org.json.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONFileToObjectConverter {
  public static void main(String[] args) throws IOException {

    List<BasketballTeam> basketballTeams = getBasketballTeamsFromFile();

    for (BasketballTeam team : basketballTeams) {
      System.out.println(team);
    }
  }

  static List<BasketballTeam> getBasketballTeamsFromFile() throws IOException {
    List<BasketballTeam> basketballTeams = new ArrayList<>();

    try (
      BufferedReader br =
        new BufferedReader(new FileReader("basketball_teams.txt"))
    ) {
      String line;

      while ((line = br.readLine()) != null) {
        JSONObject team = new JSONObject(line);
        basketballTeams.add(
          new BasketballTeam(
            team.get("team").toString(),
            team.get("arena").toString()
          )
        );
      }
    }

    return basketballTeams;
  }
}
