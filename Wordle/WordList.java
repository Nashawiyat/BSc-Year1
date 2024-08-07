import java.util.Random;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
//Class to handle vocabulary/word-list to be used in game
public class WordList
{
    Random rand = new Random();
    HashSet<String> list = new HashSet<String>();

    //Method to store words from word-list file into a HashSet
    public void getWords(File file) throws FileNotFoundException
    {
        FileReader reader = new FileReader(file);
        Scanner input = new Scanner(reader);
        
        while (input.hasNext())
        {
            list.add(input.next().toUpperCase());
        }
        input.close();
    }

    //Word-list getter method
    public HashSet<String> getWordList()
    {
        return list;
    }
    //Target-word getter method
    //Chooses a random target word each game

    public String getTargetWord()
    {
        int num = rand.nextInt(getWordList().size()-1);
        String[] word_array = getWordList().toArray(new String[getWordList().size()]);
        return word_array[num];
    }

    //Check if guessed word is a valid English word
    //Works assuming word-list file contains most of the 5-letter English words
    //Also validates guess length ensure it is 5 letters long (or whatever length you have set)
    public boolean validWord(String word)
    {
        boolean decision = false;

        //try-catch in case word-list file is not found
        try
        {
            //HashSet is used so comparison of words is O(n) worst-case time complexity 
            if (getWordList().contains(word) && word.length() == getTargetWord().length())
        {
            decision = true;
        }
        else decision = false;
        } catch (Exception e) {
            System.out.println("Sorry, no word-list was provided.");
        }
        return decision;
    }
}
