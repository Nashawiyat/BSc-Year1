import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//Class to act as the main and get the game starting
public class Game
{ 
    //Main method
    public static void main(String[] args) throws FileNotFoundException
    {
        WordList words = new WordList();
        Scanner input = new Scanner(System.in);
        File word_file = new File("WordList.txt");
        Guess guess;
        //Constant to determine how many guessed allowed per game
        final int MAX_ROUNDS = 6;
        //Vairable to check if player won
        boolean is_win = false;
        //vairable to check if player wants to play again
        char cont;
        int points;
        int current_round;
        String target_word;
        //Get the word list/library from a text file
        words.getWords(word_file);
        
        //Outter loop to check if player wants to play again
        do
        {
            target_word = words.getTargetWord();
            current_round = 0;
            points = 0;                                               

            System.out.println("Welcome to Wordle!");
            //Inner loop to cycle through different guess-rounds
            do
            {
                System.out.println("Enter guess: (all UPPERCASE)");
                System.out.println("_ _ _ _ _");
                //Initialise guess
                String guess_word = input.next();
                //Check if guess is valid
                if (words.validWord(guess_word) == false)
                {
                    System.out.println("Word is not valid! Try again!");
                }
                else
                {
                    //Setup guess and target for feedback
                    guess = new Guess(guess_word, target_word);
  
                    //Gett feedback about guess and the status of the letters
                    guess.getFeedback();
                    //Check if all letters are correct (player won)
                    if(guess.continueGuess() == guess_word.length())
                    {
                        is_win= true;
                        points = points + (MAX_ROUNDS - current_round);
                        //Winner message + points
                        System.out.println("You guessed the word! It was " + target_word + "." + '\n' + "Points: " + points);
                    }
                    //Keep track of which guess-round
                    current_round++;
                }      
            } while (current_round < MAX_ROUNDS && is_win == false);

            if(is_win == false)
            {
                //Loser message
                System.out.println("You lose! The word was " + target_word + ".");
            }
            System.out.println("Play another round? (Y/N)");
            cont = input.next( ).charAt(0);
            is_win = false;
        } while (cont == 'Y' || cont == 'y');    
        //End message
        //Leaderboard integration can be added here
        System.out.println("Thank for playing!");
        input.close();
    }
}