public class player{
  private StoryPath[] savedGames;
  private String name;
  private int gamesPlayed;
  public player(){
    this.savedGames = null;
    this.name = null;
    this.gamesPlayed = null;
  }
  public player(StoryPath game; String name; int gamesPlayed){
    this.savedGames = {game};
    this.name = name;
    this.gamesPlayed = gamesPlayed;
  }
  public player(String name){
    this.savedGames = null;
    this.name = name;
    this.gamesPlayed = null;
  }
}