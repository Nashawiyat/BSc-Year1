package questionTwo;

public class Ticket {
	//instance variables
	private char rowLetter;
	private int seatNo;
	private Boolean isOccupied;
	
	//Constructor to initialise variables
	public Ticket(int rowIdx, int colIdx, Boolean isOccupied)
	{
		//switch case algorithm to get the row letter from the index
		switch(rowIdx)
		{
		case 1:
			this.rowLetter= 'A';
			break;
		case 2:
			this.rowLetter= 'B';
			break;
		case 3:
			this.rowLetter= 'C';
			break;
		case 4:
			this.rowLetter= 'D';
			break;
		case 5:
			this.rowLetter= 'E';
			break;
		case 6:
			this.rowLetter= 'F';
			break;
		case 7:
			this.rowLetter= 'G';
			break;
		case 8:
			this.rowLetter= 'H';
			break;
		case 9:
			this.rowLetter= 'I';
			break;
		}
		this.seatNo = colIdx;
		this.isOccupied = isOccupied;
	}
	//getter method for row letter
	public char getRowLetter()
	{
		return rowLetter;
	}
	//getter method for seat number
	public int getSeatNo()
	{
		return seatNo;
	}
	//getter method for isOccupied
	public Boolean getIsOccupied()
	{
		return isOccupied;
	}
	//setter method for occupied seats
	public void seatIsOccupied(Boolean isOccupied)
	{
		this.isOccupied = isOccupied;
	}
	
	public String toString()
	{
		return "Seat no.: " +getRowLetter() + getSeatNo();
	}
}