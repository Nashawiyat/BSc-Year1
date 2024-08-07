package questionTwo;

public class Show {
	
	private Film filmName;
	private Venue venueID;
	private int rowIdx;
	private int seatNo;
	
	public Show(String venueID, Film filmName)
	{
		this.venueID = new Venue(venueID);
		this.filmName = filmName;
	}
	//method to set the seat
	public void setSeat(String seat)
	{
		this.rowIdx = getVenue().rowLetter2Idx(seat.charAt(0));
		this.seatNo = Character.getNumericValue(seat.charAt(1));
	}
	//method to get the seat row
	public int getRow()
	{
		return rowIdx;
	}
	//method to get the seat column
	public int getSeatNo()
	{
		return seatNo;
	}
	
	//method to pass values to venue class to book seat
	public void buyTicket(char row, int seatNo)
	{
		getVenue().bookASeat(getVenue().rowLetter2Idx(row), seatNo);
	}
	//method to check if seat chosen is occupied
	public Boolean seatCheckAvailability(char row, int seatNo)
	{
		if(getVenue().checkOccupied(getVenue().rowLetter2Idx(row), seatNo)) return false;
		else return true;
	}
	//method to get venue
	public Venue getVenue()
	{
		return venueID;
	}
	//method to get the film
	public Film getFilm()
	{
		return filmName;
	}
	//method to show available shows
	public void printHall()
	{
		if(getVenue().checkIfVenueIsFull())
		{
			System.out.printf("%-20s %-20s %-20s %n", "SOLD OUT", "SOLD OUT", "SOLD OUT");
		}
		else System.out.printf("%-20s %-20s %-20s %n", getVenue().getID(), getFilm().getTitle(), getFilm().getSession());
	}
	//method to show if a show is available or not
	public void printAvailability()
	{
		if(getVenue().checkIfVenueIsFull())
		{
			System.out.println("Sorry, the venue is full. Please try another show.");
		}
		else
		{
			System.out.println("Show has the following available seats:" + '\n');
		}
	}
	//method to get the info about the venue
	public void locationInfo()
	{
		if(getVenue().checkIfVenueIsFull() == false)
		{
			System.out.println(getVenue().seatVenueDisplay());
		}
	}
	//method to print the ticket details
	public String toString()
	{
		return getVenue().toString() + '\n' + getVenue().getTickets()[getRow()][getSeatNo()-1].toString() + '\n' + "Movie name: " + getFilm().getTitle() + '\n' + "Movie session: " + getFilm().getSession() + '\n';
	}
}