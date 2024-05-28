import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

public class Hangman {
   public static void main(String[] args) {
      final int num = 7;                              // Maximum number of wrong guesses in hangman
      
      // Initialize the word bank and get its length
      String[] array = wordBank();
      int arrayLength = array.length;                 // Length of the word-containing array
      
      // Select a random word from the word bank
      Random random = new Random();
      int randomNum = random.nextInt(arrayLength);
      String word = array[randomNum];                 // Randomly select a word
      
      int wordLength = word.length();                 // Length of the selected word
      
      // Create an array to store the characters of the word
      char[] wordArray = new char[wordLength];
      for (int i = 0; i < wordLength; i++) {
         wordArray[i] = word.charAt(i);
      }
      
      // Create an array to store the blanks representing unguessed letters
      char[] blanks = new char[wordLength];
      for (int i = 0; i < wordLength; i++) {
         blanks[i] = '_';
      }
      
      System.out.println("Welcome to Hangman.\n");
      
      int counter = 0;                                   // Counts number of wrong guesses made by the user
      boolean done = false;                              // Turns true once the game is over
      
      ArrayList<Character> charUsed = new ArrayList<>(); // List to store already guessed letters
      
      // Game loop
      while (!done) {
         displayBlanks(blanks, wordLength);             // Display the current state of the blanks
         System.out.println();
         
         System.out.print("Guess a letter: ");
         
         Scanner scanner = new Scanner(System.in);
         String letter = scanner.nextLine();            // Get the letter guessed by the user
         char c = letter.charAt(0);                     // Convert the guessed letter to char
         
         // Check if the letter has already been guessed
         if (!charUsed.contains(c)) {
            // Find positions of the guessed letter in the word
            ArrayList<Integer> matches = findMatches(c, word, wordArray, wordLength);
         
            if (matches.size() > 0) {
               charUsed.add(c);                        // Add the guessed letter to the list of used letters
               
               System.out.println(image(counter));     // Display the current state of the hangman image
               
               blanks = replaceBlanks(c, matches, blanks);  // Replace blanks with the correctly guessed letter
               
               // Check if the user has guessed all the letters
               if (Arrays.equals(blanks, wordArray)) {
                  displayBlanks(blanks, wordLength);
                  System.out.println();
                  System.out.println("Congratulations, you guessed the word!");  // Congratulate the user
                  done = true;                          // End the game
               }
            } else {
               counter++;                               // Increment the wrong guess counter
               
               System.out.println(image(counter));     // Display the current state of the hangman image
               
               // Check if the user has remaining guesses
               if (counter < num) {
                  System.out.println("The word does not contain this letter. Try again.");
               } else {
                  displayBlanks(blanks, wordLength);
                  System.out.println();
                  System.out.println("You failed to guess the word."); // Inform the user of failure
                  done = true;                          // End the game
               }
            }
         } else {
            System.out.println("You already guessed this letter, try again."); // Inform the user of a repeated guess
            System.out.println(image(counter));        // Display the current state of the hangman image
         }
      }
      
      // Display the correct word at the end of the game
      System.out.print("The word is: " + word);
   }
   
   // Method to create a word bank
   public static String[] wordBank () {
      String[] array = {
      "elephant", "computer", "mountain", "hamburger",
      "notebook", "sunshine", "backpack", "tangible",
      "journey", "diamond", "fantasy", "mystery",
      "control", "cabinet", "service", "balance",
      "friend", "beauty", "public", "market",
      "silver", "travel", "change", "garden",
      "apple", "bread", "crane", "dance",
      "eagle", "flame", "grace", "gaunt",
      };
      return array;
   }
   
   // Method to display the current state of the blanks
   public static void displayBlanks (char[] blanks, int wordLength) {
      for (int i = 0; i < wordLength; i++) {
         System.out.print(blanks[i] + " ");
      }
   }
   
   // Method to find positions of the guessed letter in the word
   public static ArrayList<Integer> findMatches (char c, String word, char[] wordArray, int wordLength) {
      ArrayList<Integer> match = new ArrayList<>();
      for (int i = 0; i < wordLength; i++) {
         if (wordArray[i] == c) {
            match.add(i);
         }
      }
      return match;
   }
   
   // Method to replace blanks with the correctly guessed letter
   public static char[] replaceBlanks (char c, ArrayList<Integer> matches, char[] blanks) {
      for (int i : matches) {
         blanks[i] = c;
      }
      return blanks;
   }
   
   // Method to display the current state of the hangman image
   public static String image (int counter) {
      String image;
      
      switch (counter) {
         case 1:
            image = "    ______\n" +
                    "    |    |\n" +
                    "         |\n" +
                    "         |\n" +
                    "         |\n" +
                    "         |\n" +
                    "         |\n";
            return image;
         case 2:
            image = "    ______\n" +
                    "    |    |\n" +
                    "    O    |\n" +
                    "         |\n" +
                    "         |\n" +
                    "         |\n" +
                    "         |\n";
            return image;
         case 3:
            image = "    ______\n" +
                    "    |    |\n" +
                    "    O    |\n" +
                    "    |    |\n" +
                    "    |    |\n" +
                    "         |\n" +
                    "         |\n";
            return image;
         case 4:
            image = "    ______\n" +
                    "    |    |\n" +
                    "    O    |\n" +
                    "    |\\   |\n" +
                    "    |    |\n" +
                    "         |\n" +
                    "         |\n";
            return image;
         case 5:
            image = "    ______\n" +
                    "    |    |\n" +
                    "    O    |\n" +
                    "   /|\\   |\n" +
                    "    |    |\n" +
                    "         |\n" +
                    "         |\n";
            return image;
         case 6:
            image = "    ______\n" +
                    "    |    |\n" +
                    "    O    |\n" +
                    "   /|\\   |\n" +
                    "    |    |\n" +
                    "     \\   |\n" +
                    "         |\n";
            return image;
         case 7:
            image = "    ______\n" +
                    "    |    |\n" +
                    "    O    |\n" +
                    "   /|\\   |\n" +
                    "    |    |\n" +
                    "   / \\   |\n" +
                    "         |\n";
            return image;
      }
      image = "";
      return image;
   }
}