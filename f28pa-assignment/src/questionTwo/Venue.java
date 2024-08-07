package questionTwo;

public class Venue {
	//instance variables
	private String id;
	private int noRows;
	private int noCols;
	private Ticket[][] tickets;
	private int numOfTickets;
	private int numOfSeatsOccupied;
	
	//Constructor to initialise the ID
	public Venue(String id)
	{
		this.id = id;
		this.tickets= new Ticket[getNoRows()][getNoCols()];
		//initialising blank tickets
		for(int i = 0; i < tickets.length; i++)
		{
			for(int j = 0; j < tickets[i].length; j++)
			{
				tickets[i][j] = new Ticket(i+1, j+1, false);
			}
		}
	}
	//getter method for ID
	public String getID()
	{
		return id;
	}
	//getting the tickets
	public Ticket[][] getTickets()
	{
		return tickets;
	}
	//method to get number of tickets
	public int getNumOfTickets()
	{
		return numOfTickets;
	}
	//method to get the number of rows from the ID
	public int getNoRows()
	{
		switch(getID().charAt(1))
		{
		case 'E':
		case 'N':
			noRows = 7;
			break;
		case 'W':
			noRows = 5;
			break;
		case 'S':
			noRows = 9;
			break;
		}
		return noRows;
	}
	//method to get the number of rows from the ID
	public int getNoCols()
	{
		switch(getID().charAt(1))
		{
		case 'E':
		case 'W':
			noCols = 7;
			break;
		case 'N':
			noCols = 5;
			break;
		case 'S':
			noCols = 9;
			break;
		}
		return noCols;
	}
	//getting total number of seats
	public int getNumOfSeats()
	{
		return getNoRows() * getNoCols();
	}
	//booking a seat (making it occupied)
	public void bookASeat(int rowIdx, int seatNo)
	{
		getTickets()[rowIdx][seatNo-1].seatIsOccupied(true);
		numOfTickets++;
		numOfSeatsOccupied++;
	}
	//method to check if seat is occupied
	public Boolean checkOccupied(int rowIdx, int seatNo)
	{
		if(getTickets()[rowIdx][seatNo-1].getIsOccupied())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//displaying a grid of all the seats in the venue
	public String seatVenueDisplay()
	{
		String output = "";
		
		for(int i = 0; i < getTickets().length; i++)
		{
			for(int j = 0; j < getTickets()[i].length; j++)
			{
				//to put XX on occupied seats (additional feature)
				if(getTickets()[i][j].getIsOccupied())
				{
					output += "XX" + " ";
				}
				else output += Character.toString(getTickets()[i][j].getRowLetter()) + (j+1) + " ";
			}
			output += '\n';
		}
		return output;
	}
	//to check if the venue has no available seats
	public Boolean checkIfVenueIsFull()
	{
		if(getNumOfSeats() == numOfSeatsOccupied) return true;
		else return false;
	}
	
	// Note: this static method is given
	// Converts row letter (char) to index number (int)
	public static int rowLetter2Idx(char letter) {
		return (int) (letter) - 65;
	}

	// Note: this static method is given
	// Converts index number (int) to row letter (char)
	public static char rowIndex2Letter(int idx) {
		return (char) (idx + 'A');
	}
	
	public String toString()
	{
		return "Venue ID is: " + getID();
	}
}