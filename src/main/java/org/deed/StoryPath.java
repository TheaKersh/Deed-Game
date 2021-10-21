import java.util.ArrayList;
import java.util.Scanner;
//Class to contain the story
class StoryPath{
  //Start of the story. There will only be one
  Chamber start;
  //All possible ends for the story. There should be multiple to maximize replayability
  Chamber[] ends;
  //End which the chamber is currently on.
  Chamber currentEnd;
  //Constructs a storyPath object when given a start and ends
  public StoryPath(Chamber start, Chamber[] ends){
    this.start = start;
    this.ends = ends;
  }
  //Copy constructor
  public StoryPath(StoryPath toCopy){
    this.start = toCopy.start;
    this.ends = toCopy.ends;
  }
  //Default constructor
  public StoryPath(){
    this.start = null;
    this.ends = null;
  }
  //Returns the start of the StoryPath
  public Chamber getStart(){
    return this.start;
  }
  //Sets the start of the StoryPath
  public void setStart(Chamber start){
    this.start = start;
  }
  //Returns all possible ends of a StoryPath
  public Chamber[] getEnds(){
    return this.ends;
  }
  //Sets all possible ends of a storyPath
  public void setEnd(Chamber[] ends){
    this.ends = ends;
  }
  //Shuffles the chambers of the StoryPath
  public void shuffle(){

  } 
}