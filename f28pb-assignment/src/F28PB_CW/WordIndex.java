package F28PB_CW;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/** Main class for the Word Index program */
public class WordIndex {

	static final File textFilesFolder = new File("TextFiles");
	static final FileFilter commandFileFilter = (File file) -> file.getParent() == null;
	static final FilenameFilter txtFilenameFilter = (File dir, String filename) -> filename.endsWith(".txt");

	public static void main(String[] argv) throws Exception {
		if (argv.length != 1) {
			System.err.println("Usage: WordIndex commands.txt");
			System.exit(1);
		}
		try {
			File commandFile = new File(argv[0]);
			if (commandFile.getParent() != null) {
				System.err.println("Use a command file in current directory");
				System.exit(1);
			}

			// creating a command reader from a file
			WordTxtReader commandReader = new WordTxtReader(commandFile);

			// initialise map
			IWordMap wordPossMap = new ListWordMap();
			// ...

			// reading the content of the command file
			while (commandReader.hasNextWord()) {
				// getting the next command
				String command = commandReader.nextWord().getWord();
				File[] num_files = textFilesFolder.listFiles(txtFilenameFilter);

				switch (command) {
				case "addall":
					assert (textFilesFolder.isDirectory());
					File[] listOfFiles = textFilesFolder.listFiles(txtFilenameFilter);
					Arrays.sort(listOfFiles);
					for (File textFile : listOfFiles) {
						WordTxtReader wordReader = new WordTxtReader(textFile);

						while (wordReader.hasNextWord()) {
							WordPosition wordPos = wordReader.nextWord();
							// adding word to the map
							// ...
							//adds all the positions
							wordPossMap.addPosition(wordPos.getWord(), wordPos);
						}
					}
					System.out.println("=================================");
					System.out.println("Add all command:");
					System.out.println("---------------------------------");
					System.out.println(wordPossMap.numberOfPositions() + " entries (total words) added from " + listOfFiles.length + " files");
					break;
				case "add":
					File textFile = new File(textFilesFolder, commandReader.nextWord().getWord() + ".txt");
					WordTxtReader wordReader = new WordTxtReader(textFile);
					//to keep track of the entries
					int entries = 0;
					while (wordReader.hasNextWord()) {
						WordPosition word = wordReader.nextWord();
						// adding word to the map
						// ...
						//adds the entries to the map
						wordPossMap.addPosition(word.getWord(), word);
						entries++;
					}
					System.out.println("=================================");
					System.out.println("Add commmand: ");
					System.out.println("---------------------------------");
					System.out.println(entries + " entries (total words) added from the file " + "'" +  textFile + "'");
					break;
				case "search":
					int numberOfFiles = Integer.parseInt(commandReader.nextWord().getWord());
					String wordToSearch = commandReader.nextWord().getWord();
					//list to keep track of the different files the word is found in
					LinkedList<String> different_files = new LinkedList<>();
					// search for word entry in map
					// ...
					try
					{
						//to try and catch an exception in case the 'n' in the search command brings an index exception
						try
						{
						Iterator<IPosition> poss = wordPossMap.positions(wordToSearch);
						//to keep track of the number of times it was found
						int i = 0;
						while (poss.hasNext()) {
							IPosition pos = poss.next();
							//boolean to check if the file already exists
							Boolean file_existance = false;
							if(different_files.contains(pos.getFileName()))
									{
										file_existance = true; 
									}
							//if it doesn't exist, add it to the list
							if(file_existance == false)
							{
								different_files.add(pos.getFileName());
							}
							i++;
						}
						//printing the total times the word was found
						System.out.println("=================================");
						System.out.println("Search command:");
						System.out.println("---------------------------------");
						System.out.println("found \"" + wordToSearch + "\" in (" + i + ") different lines in " + different_files.size() + " files \n");
						//array to store the frequency of the word in each file				
						int[] file_frequency = new int[different_files.size()];
						String[] file_frequency_names = new String[different_files.size()];
						//loop to get the frequency number
						for(int j = 0; j < different_files.size(); j++)
						{
							Iterator<IPosition> poss2 = wordPossMap.positions(wordToSearch);
							//loop to check how many times that word was found in each file
							while(poss2.hasNext())
							{
								if(poss2.next().getFileName().equalsIgnoreCase(different_files.get(j)))
										{
											file_frequency[j]++;
											file_frequency_names[j] = different_files.get(j);
										}
							}
						}
						//loop to sort the array in descending order of frequency
						for (int k = 0; k < file_frequency.length; k++)
						{
				            for (int j = k+1; j < file_frequency.length; j++)
				            {
				                if (file_frequency[k] < file_frequency[j])
				                {
				                    int temp = file_frequency[k];
				                    file_frequency[k] = file_frequency[j];
				                    file_frequency[j] = temp;
				                    
				                    String temp_name = file_frequency_names[k];
				                    file_frequency_names[k] = file_frequency_names[j];
				                    file_frequency_names[j] = temp_name;
				                }
				            }
						}
						//array to store all the lines the word is found in
						String[] lines_list = new String[numberOfFiles];
						//loop to store all the lines in the array
						for(int j = 0; j < numberOfFiles; j++)
						{
							Iterator<IPosition> poss2 = wordPossMap.positions(wordToSearch);
							String lines = "";
							while(poss2.hasNext())
							{
								IPosition p = poss2.next();
								if(p.getFileName().equalsIgnoreCase(file_frequency_names[j]))
										{
											lines += p.getLineNumber() + ", ";
										}
							}
							lines_list[j] = lines;
						}
						
						//loop to print out the word frequency
						for(int j = 0; j < numberOfFiles; j++)
						{
							System.out.println("found \"" + wordToSearch + "\" (" + file_frequency[j] + ") times in  " + file_frequency_names[j]);
							System.out.println("Line ( " + lines_list[j] + ")" + '\n');
						}
						}
						//to catch an exception in case the 'n' in the search command brings an index exception
						catch (ArrayIndexOutOfBoundsException e)
						{
							System.err.print("Word is not found in that many files \n");
						}
					}
					catch (WordException e)
					{
						System.err.println("not found");
					}
					// ...
					
					break;
				case "remove":
					File textFileToRemove = new File(textFilesFolder, commandReader.nextWord().getWord() + ".txt");
					// remove word-positions
					// ...
					//reading the file
					WordTxtReader wordRemoverReader = new WordTxtReader(textFileToRemove);
					//counter
					int entries_removed = 0;
					while(wordRemoverReader.hasNextWord())
					{
						WordPosition pos = wordRemoverReader.nextWord();
						//removing the positions for each word
						wordPossMap.removePosition(pos.getWord(), pos);
						//counting how many times it removes
						entries_removed++;
					}
					System.out.println("=================================");
					System.out.println("Remove command: ");
					System.out.println("---------------------------------");
					System.out.println(entries_removed + " entries (total words) removed from the file " +  "'" +  textFileToRemove + "'");
					break;
				case "overview":
					// print overview
					// ...
					System.out.println("=================================");
					System.out.println("Overview command:");
					System.out.println("---------------------------------");
					System.out.println("Number of files : " + num_files.length);
					System.out.println("Number of unique words : " + wordPossMap.numberOfEntries());
					System.out.println("Number of different words (positions) : " + wordPossMap.numberOfPositions());
					break;
				default:
					break;
				}
			}
		} catch (IOException e) { // catch exceptions caused by file input/output errors
			System.err.println("Check your file name");
			System.exit(1);
		}
	}
}
