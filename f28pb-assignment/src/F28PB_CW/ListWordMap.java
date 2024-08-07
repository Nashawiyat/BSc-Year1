package F28PB_CW;

import java.util.Iterator;
import java.util.LinkedList;

public class ListWordMap implements IWordMap {
	//linked list to map the word and position
	private LinkedList<WordEntry> wordEntries;
	//consturctor to initialise the linked list
    public ListWordMap()
    {
        wordEntries = new LinkedList<>();
    }
 //class to set the mapping so that each element in the wordEntries linked list can have
 //a value to store the word and a linked list to store all of its positions
    private static class WordEntry
    {
        private String word;
        private LinkedList<IPosition> positions;

        public WordEntry(String word)
        {
            this.word = word;
            this.positions = new LinkedList<>();
        }
        //getter method for the word
        public String getWord()
        {
            return word;
        }
        //getter method for the positions
        public LinkedList<IPosition> getPositions()
        {
            return positions;
        }
        //method to add the position into the position linked list
        public void addPosition(IPosition pos)
        {
            positions.add(pos);
        }
        //method to remove the position from the position linked list
        public boolean removePosition(IPosition pos)
        {
            return positions.remove(pos);
        }
    }
    //method to find the word in the linked list
    private WordEntry findWordEntry(String word)
    {
        for (WordEntry entry : wordEntries)
        {
            if (entry.getWord().equals(word))
            {
                return entry;
            }
        }
        return null;
    }
    
    @Override
    public void addPosition(String word, IPosition pos)
    {
    	//checking if the word already is mapped
        WordEntry entry = findWordEntry(word);
        //if the word is not mapped, it creates a new entry
        if (entry == null)
        {
            entry = new WordEntry(word);
            wordEntries.add(entry);
        }
        //adds position for the entry, works if it is already mapped or not
        entry.addPosition(pos);
    }

    @Override
    public void removeWord(String word) throws WordException
    {
    	//checking if the word exists
        WordEntry entry = findWordEntry(word);
        //if the word doesn't exist it throws exception
        if (entry == null)
        {
            throw new WordException("Word not found.");
        }
        //removes the word if it does exist
        wordEntries.remove(entry);
    }

    @Override
    public void removePosition(String word, IPosition pos) throws WordException
    {
    	//checks if the word exists
        WordEntry entry = findWordEntry(word);
      //if the word doesn't exist it throws exception
        if (entry == null)
        {
            throw new WordException("Word not found.");
        }
        boolean removed = entry.removePosition(pos);
        //if the word position was not removed (because it did not have that position), it will throw exception
        if (!removed)
        {
            throw new WordException("Word is not associated with the given position.");
        }
    }

    @Override
    public Iterator<String> words()
    {
    	//making a linked list to iterate through it
    	LinkedList<String> words = new LinkedList<String>();
    	//adding the word entries into the linked list
        for (WordEntry entry : wordEntries)
        {
            words.add(entry.getWord());
        }
        //returning the iterator
        return words.iterator();
    }

    @Override
    public Iterator<IPosition> positions(String word) throws WordException
    {
    	//finding the word
        WordEntry entry = findWordEntry(word);
        //throws exception if word is not found
        if (entry == null)
        {
            throw new WordException("Word not found.");
        }
        //returns the positions linked list as an iterator if no exception was thrown
        return entry.getPositions().iterator();
    }

    @Override
    public int numberOfEntries()
    {
    	//returns the number of elements in the linked list
        return wordEntries.size();
    }
    
    @Override
    //method to get the number of positions
    public int numberOfPositions()
    {
    	int entries = 0;
    	for(WordEntry entry : wordEntries)
    	{
    		entries += entry.getPositions().size();
    	}
    	return entries;
    }
}