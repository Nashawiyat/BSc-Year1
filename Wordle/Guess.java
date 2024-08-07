//Class to initiliase guess and put all letters of guess and target into
//char arrays for easier feedback
public class Guess
{
    //char arrays to store all letters of guess and target separately
    private char[] tL = new char[5];
    private char[] gL = new char[5];
    private Feedback feedback = new Feedback();
    
    //Consturctor to initialise the character arrays
    public Guess(String guess, String target)
    {
        for(int i = 0; i < gL.length; i++)
        {
            this.gL[i] = guess.charAt(i);
        }

        for(int i = 0; i < tL.length; i++)
        {
            this.tL[i] = target.charAt(i);
        }
    }

    //Getter method for the guess character array
    public char[] getGuessList()
    {
        return gL;
    }

    //Getter method for the target character array
    public char[] getTargetList()
    {
        return tL;
    }

    //Method to get full guess feedback from Feedback class
    public void getFeedback()
    {
        feedback.guessFeedback(getGuessList(), getTargetList());
    }

    //Method to receive how many "correct" letters found in guess
    //Determines whether or not game is won
    public int continueGuess()
    {
        return feedback.getCount();
    }
}
