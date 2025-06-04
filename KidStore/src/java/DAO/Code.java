/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author admin
 */
import java.util.Random;

public class Code {

  public String Code() {
    int lines = 10;
    int stringLength = 10; // Adjust this for desired length per line
    
    Random random = new Random();
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < lines; i++) {
      builder.setLength(0); // Clear the builder for each line
      for (int j = 0; j < stringLength; j++) {
        int asciiValue = random.nextInt(62); // Generate random ASCII value within range
        char randomChar = (char) (asciiValue < 26 ? asciiValue + 65  // Uppercase letter (A-Z)
                                                 : asciiValue + 48); // Digit (0-9)
        builder.append(randomChar);
      }
    }
    return builder.toString();
  }
}
