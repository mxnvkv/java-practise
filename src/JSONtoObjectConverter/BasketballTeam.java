package JSONtoObjectConverter;

public class BasketballTeam {
  private String team;
  private String arena;

  BasketballTeam() {}

  BasketballTeam(String team, String arena) {
    this.team = team;
    this.arena = arena;
  }

  public String getTeam() { return this.team; }
  public String getArena() { return this.arena; }

  @Override
  public String toString() {
    return "[ " + team + ", " + arena + " ]";
  }
}
