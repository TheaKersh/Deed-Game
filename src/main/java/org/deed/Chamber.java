//Class to contain specific "areas" within a StoryPath
//Relationship between these two is analogous to Relationship
//Between Node and LinkedList class
class Chamber{
    //Prompt for each chamber 
    private String toSay;
    //Where the chamber can lead depending on response to
    //prompt
    private Chamber[] nexts;
    //All chambers which can lead into this one
    private Chamber[] prevs;
    //Name of chamber, for testing and identification
    private String name;
    //Shows if this chamber has been modified in the past
    //Potentially useless
    private boolean modified;
    //Input which leads to this chamber;
    private String inputTo;
    //Constructor for default chamber, with previouses 
    //and nexts. Previouses and nexts will be flexible
    //To maximize replayability
    public Chamber(String toSay, Chamber[] nextDecisions,Chamber[] prevDecisions, String name){
      this.toSay = toSay;
      this.nexts = nextDecisions;
      this.prevs = prevDecisions;
      this.name = name;
    }
    //Copy Constructor
    public Chamber(Chamber toCopy){
      this.toSay = toCopy.toSay;
      this.nexts = toCopy.nexts;
      this.prevs = toCopy.prevs;
      this.name = toCopy.name;
    }
    //Constructor for a Chamber with only a name and prompt
    public Chamber(String name, String prompt){
      this.name = name;
      this.toSay = prompt;
    }
    //Default Constructor
    public Chamber(){
      this.toSay = null;
      this.nexts = null;
      this.prevs = null;
      this.name = null;
    }
    //Returns the names of the possible next chambers
    public String getNexts(){
      String toReturn = "";
      for (int i = 0; i < nexts.length; i++){
        toReturn+=nexts[i];
      }
      return toReturn;
    }
    public void setNexts(Chamber[] c_list){
      this.nexts = c_list;
    }
    //Modifies the possible next chambers
    public boolean modifyNext(Chamber toModify){
      if (nexts.length == 0 || nexts == null){
        nexts = new Chamber[1];
        nexts[1] = toModify;
        return true;
      }
      for (int i = 0; i < nexts.length; i++){
        if(nexts[i].modified == false){
          nexts[i] = toModify;
          return true;
        }
      }
      return false;
    }
    //ToString for testing
    public String toString(){
      String toString = "";
     
      if (this.nexts != null){
        toString += "It leads into ";
        int i = 0;
        for (i = 0; i < nexts.length; ++i){
          Chamber c = nexts[i];
          toString += c.name + ", ";
        }
      }
      if (this.prevs != null){
        toString += " And is lead into by ";
        int a = 0;
        for (a = 0; a < prevs.length; ++a){
          Chamber c = prevs[a];
          toString += c.name;
        }
      }
      if (this.name != null){
        toString += "Name: " + this.name;
      }
      if (this.toSay != null){
        toString += "\nPrompt: " + this.toSay;
      }  
      return toString;
    }
  }
  //I figured it might be a good idea to have a subclass
  //For boss areas specifically
  class BossChamber extends Chamber{
    
  }