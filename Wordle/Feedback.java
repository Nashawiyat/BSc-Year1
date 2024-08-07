import java.util.HashMap;
//Class to handle guess feedbac
public class Feedback
{
    //Variable to count how many "correct" letters in guess
    private int count = 0;
    //Array to store  status of each letter
    private String[] letter_status;
    //ANSI colors for visual display of letter status
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m"; 
    public static final String ANSI_GREEN = "\u001B[32m"; 

    //setter method for count
    public void setCount()
    {
        this.count = this.count +1 ;
    }

    //getter method for count
    public int getCount()
    {
        return count;
    }

    //Method to set status of each letter in a guess
    public String[] letterStatus(char[] guess, char[] target) {
        //Array to store the results
        letter_status = new String[guess.length];
        
        //Create a HashMap to count the occurrences of each character in the target
        HashMap<Character, Integer> targetCountMap = new HashMap<>();
        for (char c : target)
        {
            targetCountMap.put(c, targetCountMap.getOrDefault(c, 0) + 1);
        }
        
        //Compare each character in the guess to the target, and store it's status in the array
        //Two separate for loops are used to ensure that "correct" status has priority over "present" status
        //HashMap used for O(n) worst-case time complexity when comparing (when for loop is considered)
        for (int i = 0; i < guess.length; i++)
        {  
            if (guess[i] == target[i] && targetCountMap.get(guess[i]) > 0)
            {
                //"correct" = letter is present in target word AND is in the correct position
                letter_status[i] = "correct";
                targetCountMap.replace(guess[i], targetCountMap.get(guess[i]) - 1);
            } 
        }

        for (int i = 0; i < guess.length; i++)
        {
            if (letter_status[i] != null)
            {
                continue;
            }
            if (targetCountMap.containsKey(guess[i]) && targetCountMap.get(guess[i]) > 0)
            {
                //"present" = letter is present in target word but is NOT in correct position
                letter_status[i] = "present";
                targetCountMap.replace(guess[i], targetCountMap.get(guess[i]) - 1);
            }
            else
            {
                //"absent" = letter is not found  in target word
                letter_status[i] = "absent";
            }
        }
        return letter_status;
    }

    //Method to display full guess feedback
    public void guessFeedback(char[] guess, char[] target)
    {
        String[] status = letterStatus(guess, target);
        for (int i = 0; i < guess.length; i++)
        {
            if (status[i].equals("present"))
            {
                System.out.print(ANSI_YELLOW + guess[i] + ANSI_RESET);
            }

            else if (status[i].equals("correct"))
            {
                System.out.print(ANSI_GREEN + guess[i] + ANSI_RESET);
                //Adds 1 to the count if correct letter is found
                setCount();
            }

            else
            {
                System.out.print(guess[i]);
            }  
        }
        System.out.println("");
    }
}
