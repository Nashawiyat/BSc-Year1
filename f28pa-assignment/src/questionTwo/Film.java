package questionTwo;

public class Film {

	protected String filmTitle;
	protected int sessionTime;
	
	public Film(String title, int sessionTime)
	{
		this.filmTitle = title;
		this.sessionTime = sessionTime;
	}
	
	public String getSession()
	{
		String session = "";
		switch(sessionTime)
		{
		case 1:
			session = "Afternoon (1pm)";
			break;
		case 2:
			session = "Evening (5 pm)";
			break;
		case 3:
			session = "Night (9 pm)";
			break;
		}
		return session;
	}
	
	public String getTitle()
	{
		return filmTitle;
	}
}