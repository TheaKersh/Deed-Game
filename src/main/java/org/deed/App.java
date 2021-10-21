package org.deed;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
    System.out.println("Hello world!");
    Chamber c = new Chamber("Basic Start", "Which way would you like to go");
    Chamber c2 = new Chamber("Left Chamber", "Do you go straight or right");
    Chamber c3 = new Chamber("Right Chamber", "Do you go left or right");
    System.out.println(c.toString());
    StoryPath s = new StoryPath();
    Chamber[] c_list = {c2,c3};
    c.setNexts(c_list);
    System.out.println(c.toString())
  }
}
