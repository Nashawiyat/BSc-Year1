package F28PB_CW;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class ListWordMapTest {

	// Add your own tests
	private ListWordMap wordMap;
	
	@Before
	public void setUp()
	{
		wordMap = new ListWordMap();
	}
	
	@Test
	public void testAddPositionEmpty()
	{
		assertEquals(0, wordMap.numberOfEntries());
	}
	
	@Test
	public void testAddPositionFirstEntry()
	{
		IPosition pos = new WordPosition("F28PAWeek1A", 1, "SDA");
		wordMap.addPosition("SDA", pos);
		assertEquals(1, wordMap.numberOfEntries());
	}
	
	@Test
	public void testAddPositionOldEntry()
	{
		IPosition pos1 = new WordPosition("F28PAWeek1A", 1, "SDA");
		wordMap.addPosition("SDA", pos1);
		assertEquals(1, wordMap.numberOfEntries());
		
		IPosition pos2 = new WordPosition("F28PAWeek1A", 2, "SDA");
		wordMap.addPosition("SDA", pos2);
		assertEquals(1, wordMap.numberOfEntries());
	}
	
	@Test
	public void testAddPositionDifferentEntry()
	{
		IPosition pos1 = new WordPosition("F28PAWeek1A", 1, "SDA");
		wordMap.addPosition("SDA", pos1);
		assertEquals(1, wordMap.numberOfEntries());
		
		IPosition pos2 = new WordPosition("F28PAWeek1A", 2, "F27SA");
		wordMap.addPosition("F27SA", pos2);
		assertEquals(2, wordMap.numberOfEntries());
	}
	
	@Test(expected = WordException.class)
	public void testRemoveWordEmpty() throws WordException
	{
		wordMap.removeWord("SDA");
	}
	
	@Test
	public void testRemoveWord() throws WordException
	{
		IPosition pos1 = new WordPosition("F28PAWeek1A", 1, "SDA");
		wordMap.addPosition("SDA", pos1);
		assertEquals(1, wordMap.numberOfEntries());
		
		wordMap.removeWord("SDA");
		assertEquals(0, wordMap.numberOfEntries());
	}
	
	@Test
	public void testRemoveWordMany() throws WordException
	{
		IPosition pos1 = new WordPosition("F28PAWeek1A", 1, "SDA");
		wordMap.addPosition("SDA", pos1);
		assertEquals(1, wordMap.numberOfEntries());
		
		IPosition pos2 = new WordPosition("F28PAWeek1A", 2, "F27SA");
		wordMap.addPosition("F27SA", pos2);
		assertEquals(2, wordMap.numberOfEntries());
		
		wordMap.removeWord("SDA");
		assertEquals(1, wordMap.numberOfEntries());
		
		wordMap.removeWord("F27SA");
		assertEquals(0, wordMap.numberOfEntries());
	}	
	
	@Test(expected = WordException.class)
	public void testRemoveWordNotFound() throws WordException
	{
		IPosition pos1 = new WordPosition("F28PAWeek1A", 1, "SDA");
		wordMap.addPosition("SDA", pos1);
		assertEquals(1, wordMap.numberOfEntries());
		
		wordMap.removeWord("F27SA");
	}
	
	@Test(expected = WordException.class)
	public void testRemovePositionEmpty() throws WordException
	{
		IPosition pos1 = new WordPosition("F28PAWeek1A", 1, "SDA");
		wordMap.removePosition("SDA", pos1);
	}
	
	@Test(expected = WordException.class)
	public void testRemovePosition() throws WordException
	{
		IPosition pos1 = new WordPosition("F28PAWeek1A", 1, "SDA");
		IPosition pos2 = new WordPosition("F28PAWeek1A", 2, "SDA");
		wordMap.addPosition("SDA", pos1);
		wordMap.addPosition("SDA", pos2);
		assertEquals(1, wordMap.numberOfEntries());
		wordMap.removePosition("SDA", pos1);
		wordMap.removePosition("SDA", pos1);
	}
	
	@Test
    public void testWordsWhenEmpty()
	{
        assertFalse(wordMap.words().hasNext());
    }
	
	@Test
    public void testWordsWithMultipleWords()
	{
		IPosition pos1 = new WordPosition("F28PAWeek1A", 1, "SDA");
	    IPosition pos2 = new WordPosition("F28PAWeek1A", 2, "F27SA");

	    wordMap.addPosition("SDA", pos1);
	    wordMap.addPosition("F27SA", pos2);

	    String[] words = {"SDA", "F27SA"};
	    Iterator<String> wordsIterator = wordMap.words();

	    int count = 0;
	    while (wordsIterator.hasNext()) {
	        assertEquals(words[count], wordsIterator.next());
	        count++;
	    }
	    assertFalse(wordsIterator.hasNext());
    }
	
	@Test(expected = WordException.class)
	public void testPositionsNotFound() throws WordException
	{
		wordMap.positions("SDA");
	}
	
	@Test
	public void testPositions() throws WordException
	{
		IPosition pos1 = new WordPosition("F28PAWeek1A", 1, "SDA");
	    IPosition pos2 = new WordPosition("F28PAWeek1A", 2, "F27SA");
		IPosition pos3 = new WordPosition("F28PAWeek1A", 3, "SDA");

	    wordMap.addPosition("SDA", pos1);
	    wordMap.addPosition("F27SA", pos2);
	    wordMap.addPosition("SDA", pos3);
	    
	    assertTrue(wordMap.positions("SDA").hasNext());
	    assertTrue(wordMap.positions("F27SA").hasNext());
	    
	    Iterator<IPosition> positionsIterator;
	    
	    positionsIterator = wordMap.positions("SDA");
        assertEquals(pos1, positionsIterator.next());
        assertEquals(pos3, positionsIterator.next());
        assertFalse(positionsIterator.hasNext());
	}
	
	@Test
	public void testNumberOfEntriesEmpty()
	{
		assertEquals(wordMap.numberOfEntries(), 0);
	}
	
	@Test
	public void testNumberOfEntries()
	{
		IPosition pos1 = new WordPosition("F28PAWeek1A", 1, "SDA");
	    IPosition pos2 = new WordPosition("F28PAWeek1A", 2, "F27SA");
		IPosition pos3 = new WordPosition("F28PAWeek1A", 3, "SDA");

	    wordMap.addPosition("SDA", pos1);
	    wordMap.addPosition("F27SA", pos2);
	    wordMap.addPosition("SDA", pos3);
	    
		assertEquals(wordMap.numberOfEntries(), 2);
	}
	
	@Test
	public void testNumberOfPositionsEmpty()
	{
		assertEquals(wordMap.numberOfPositions(), 0);
	}
	
	@Test
	public void testNumberOfPositions()
	{
		IPosition pos1 = new WordPosition("F28PAWeek1A", 1, "SDA");
	    IPosition pos2 = new WordPosition("F28PAWeek1A", 2, "F27SA");
		IPosition pos3 = new WordPosition("F28PAWeek1A", 3, "SDA");

	    wordMap.addPosition("SDA", pos1);
	    wordMap.addPosition("F27SA", pos2);
	    wordMap.addPosition("SDA", pos3);
	    
		assertEquals(wordMap.numberOfPositions(), 3);
	}
	
	@Test
	public void signatureTest() {
		try {
			IWordMap map = new ListWordMap();
			String word1 = "test1";
			String word2 = "test2";
			IPosition pos1 = new WordPosition("test.txt", 4, word1);
			IPosition pos2 = new WordPosition("test.txt", 5, word2);
			map.addPosition(word1, pos1);
			map.addPosition(word2, pos2);
			map.words();
			map.positions(word1);
			map.numberOfEntries();
			map.removePosition(word1, pos1);
			map.removeWord(word2);
		} catch (Exception e) {
			fail("Signature of solution does not conform");
		}
	}
}
