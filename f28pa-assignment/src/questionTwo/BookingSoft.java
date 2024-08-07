package questionTwo;

import java.util.Scanner;

/**
 * F28PA | Software Development A | Coursework
 * 
 * The Coursework specification is provided in Canvas. Please read through it in
 * full before you start work.
 * 
 * @author AHMED NASHWAN ABDULKARE AL-ANSI
 */
public class BookingSoft {
	public static void main(String[] args) throws InterruptedException {
		// There are currently 6 shows offered in 6 different venues.
		Show[] shows = new Show[6];

		// Uncomment lines 17 to 22 once the constructors have been written.
		shows[0] = new Show("1N", new Film("SING", 1));
		shows[1] = new Show("2W", new Film("THE GRINCH", 2));
		shows[2] = new Show("3E", new Film("BOSS BABY", 3));
		shows[3] = new Show("3S", new Film("YES DAY", 3));
		shows[4] = new Show("1E", new Film("THE KARATE KID", 1));
		shows[5] = new Show("2N", new Film("THE SEA BEAST", 2));
		System.out.println("### Welcome to the Booking System ###\n");
		// DO NOT CHANGE THE ABOVE PART OF THE CODE.
		///////////////////////////////////////////////////////////////////////////////////
		// variables and object
		Scanner input = new Scanner(System.in);
		String venueID;
		String seatNo = "";
		String decision = "Y";
		String ticket_details = '\n' + "### Ticket details: ###" + '\n';
		int total_tickets = 0;
		// loop to allow the user to get multiple tickets
		do {
			// to print out all the shows
			System.out.println("Show details:");
			System.out.printf("%-20s %-20s %-20s %n", "Venue ID", "Film Title", "Film Session");
			for (int i = 0; i < shows.length; i++) {
				shows[i].printHall();
			}
			// to get the user to pick a session
			System.out.println("");
			System.out.println("Which show would you like? (Enter the venue ID)");
			venueID = input.next();
			// to check the venue
			for (int i = 0; i < shows.length; i++) {
				// gets the venue chosen
				if (venueID.equals(shows[i].getVenue().getID())) {
					// shows availability and prints seating if venue is available
					shows[i].printAvailability();
					// condition to let you book a ticket only if its empty
					if (!shows[i].getVenue().checkIfVenueIsFull()) {
						shows[i].locationInfo();
						// getting the seat wanted
						System.out.println("Enter the seat number:");
						// exception handling to get the right seat number
						try {
							do// loop for the ticket availability
							{
								seatNo = input.next();
								// condition to only book an empty seat
								if (!shows[i].seatCheckAvailability(seatNo.charAt(0),
										(int) Character.getNumericValue(seatNo.charAt(1)))) {
									System.out.println("Seat is booked, pick another seat.");
									continue;
								}
								// booking empty seat
								else {
									System.out.println("Seat is available." + '\n');
									// setting seat number to be able to use the toString() method
									shows[i].setSeat(seatNo);
									// booking the ticket
									shows[i].buyTicket(seatNo.charAt(0), Character.getNumericValue(seatNo.charAt(1)));
									// String to print all the ticket details later
									ticket_details += '\n' + shows[i].toString();
									break;
								}
							} while ((!shows[i].seatCheckAvailability(seatNo.charAt(0),
									Character.getNumericValue(seatNo.charAt(1)))));
						} catch (Exception e) {
							System.out.println(
									"Error! You inputted an incorrect seat number, try again! (maybe the row letter was not capital)");
						}
					}
				}
			}
			System.out.println("Would you like to buy another ticket? (Y/N)");
			decision = input.next();
		} while (decision.equalsIgnoreCase("Y"));
		// to print the tickets and their details
		for (int i = 0; i < shows.length; i++) {
			total_tickets += shows[i].getVenue().getNumOfTickets();
		}
		System.out.println('\n' + "Number of tickets: " + total_tickets + '\n' + ticket_details);
		System.out.println("");
		// to print the current venue seat availability
		System.out.println("### Current seat availability: ###" + '\n');
		System.out.printf("%-20s %-20s %-20s %n", "Venue ID", "Film Title", "Film Session");
		for (int i = 0; i < shows.length; i++) {
			shows[i].printHall();
		}
		System.out.println("");
		System.out.println("### Thank you, goodbye! ###");
	}
}
