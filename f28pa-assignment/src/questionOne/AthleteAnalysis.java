package questionOne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * F28PA | Software Development A | Coursework
 * 
 * The Coursework specification is provided in Canvas. Please read through it in
 * full before you start work.
 * 
 * @author AHMED NASHWAN ABDULKAREM AL-ANSI
 */
public class AthleteAnalysis {
	// rounds variable to get the value of N
	private int rounds;
	// arrays for each column
	private String[] name;
	private String[] gender;
	private String[] age;
	private String[] height;
	private String[] weight;
	private String[] sport;
	private String[] medal;
	// array for the different unique sports
	private String[] unique_sports;

	// default constructor
	public AthleteAnalysis() {

	}

	// Constructor to initiliase the rounds and all the arrays
	public AthleteAnalysis(int rounds) {
		this.rounds = rounds;
		this.name = new String[rounds];
		this.gender = new String[rounds];
		this.age = new String[rounds];
		this.height = new String[rounds];
		this.weight = new String[rounds];
		this.sport = new String[rounds];
		this.medal = new String[rounds];
	}

	// method to handle file input of data
	public void fileInput(File f) throws FileNotFoundException {
		Scanner data = new Scanner(f);
		// to go to the next line to avoid reading N
		data.nextLine();
		while (data.hasNextLine()) {
			for (int i = 0; i < getRounds(); i++) {
				// array to separate each data from each line
				String[] array = data.next().split(",");
				// putting in the values for the arrays
				setName(array[0], i);
				setGender(array[1], i);
				setAge(array[2], i);
				setHeight(array[3], i);
				setWeight(array[4], i);
				setSport(array[5], i);
				setMedal(array[6], i);
			}
		}
		data.close();
	}

	// method to handle manual input of data
	public void manualInput() {
		Scanner input = new Scanner(System.in);
		// array to get each line of data
		String[] array1 = new String[getRounds()];

		System.out.println("Please enter the data record by record: " + '\n'
				+ "Format: Name,gender,age,height cm (integer),weight kg(integer),sport,medal");
		for (int i = 0; i < array1.length; i++) {
			System.out.println("record " + (i + 1) + ": ");
			array1[i] = input.nextLine();
		}
		// to put each column in it's respective array
		for (int i = 0; i < array1.length; i++) {
			String[] array2 = array1[i].split(",");
			setName(array2[0], i);
			setGender(array2[1], i);
			setAge(array2[2], i);
			setHeight(array2[3], i);
			setWeight(array2[4], i);
			setSport(array2[5], i);
			setMedal(array2[6], i);
		}
	}

	// Method to get validity of inputs
	public Boolean isValid() {
		Boolean validity = true;
		for (int i = 0; i < getRounds(); i++) {
			if (!getGender()[i].equalsIgnoreCase("M") && !getGender()[i].equalsIgnoreCase("F")) {
				System.out.println("There is an invalid gender entry! Please go through your records again.");
				validity = false;
			}

			if (!getMedal()[i].equalsIgnoreCase("Gold") && !getMedal()[i].equalsIgnoreCase("Silver")
					&& !getMedal()[i].equalsIgnoreCase("Bronze")) {
				System.out.println("There is an invalid medal award! Please go through your records again.");
				validity = false;
			}

			if (Integer.parseInt(getAge()[i]) < 18) {
				System.out.println("One of the ahtletes is underage! Please go through your records again.");
				validity = false;
			}
		}
		return validity;
	}

	public int getRounds() {
		return rounds;
	}

	public void setRounds(int r) {
		this.rounds = r;
	}

	public String[] getName() {
		return name;
	}

	public void setName(String n, int i) {
		this.name[i] = n;
	}

	public String[] getGender() {
		return gender;
	}

	public void setGender(String g, int i) {
		this.gender[i] = g;
	}

	public String[] getAge() {
		return (age);
	}

	public void setAge(String a, int i) {
		this.age[i] = a;
	}

	public String[] getHeight() {
		return height;
	}

	public void setHeight(String h, int i) {
		this.height[i] = h;
	}

	public String[] getWeight() {
		return weight;
	}

	public void setWeight(String w, int i) {
		this.weight[i] = w;
	}

	public String[] getSport() {
		return sport;
	}

	public void setSport(String s, int i) {
		this.sport[i] = s;
	}

	public String[] getMedal() {
		return medal;
	}

	public void setMedal(String m, int i) {
		this.medal[i] = m;
	}

	// method to get number of men
	public int numMale() {
		int male = 0;
		for (int i = 0; i < getGender().length; i++) {
			if (getGender()[i].equalsIgnoreCase("m")) {
				male++;
			}
		}
		return male;
	}

	// method to get number of women
	public int numFemale() {
		int female = 0;
		for (int i = 0; i < getGender().length; i++) {
			if (getGender()[i].equalsIgnoreCase("f")) {
				female++;
			}
		}
		return female;
	}

	// method to separate the column arrays for men only
	public String[] getMenArray(String[] array) {
		String[] arrayMen = new String[numMale()];

		for (int i = 0, m = 0; i < getRounds(); i++) {
			if (getGender()[i].equalsIgnoreCase("M")) {
				arrayMen[m] = array[i];
				m++;
			}
		}
		return arrayMen;
	}

	// method to separate the column arrays for women only
	public String[] getWomenArray(String[] array) {
		String[] arrayWomen = new String[numFemale()];

		for (int i = 0, f = 0; i < getRounds(); i++) {
			if (getGender()[i].equalsIgnoreCase("F")) {
				arrayWomen[f] = array[i];
				f++;
			}
		}
		return arrayWomen;
	}

	// print out athlete details
	public void printRecords() {
		System.out.println("-------------------------------------------------------------------");
		System.out.printf("%-3s %-7s %-3s %-7s %-7s %-7s %-12s %-7s %3s  %n", "|", "Name", "Gender", "Age", "Height",
				"Weight", "Sport", "Medal", "|");
		System.out.println("-------------------------------------------------------------------");
		for (int i = 0; i < getRounds(); i++) {
			System.out.printf("%-3s %-7s %-6s %-7s %-7s %-7s %-12s %-7s %3s %n", "|", getName()[i], getGender()[i],
					getAge()[i], getHeight()[i], getWeight()[i], getSport()[i], getMedal()[i], "|");
		}
		System.out.println("-------------------------------------------------------------------");
		System.out.println("");
	}

	// to get greatest common divisor
	// Reference: https://www.geeksforgeeks.org/java-program-to-compute-gcd/
	// (Accessed at: 21 October 2023)
	public int greatestCommonDivisor(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return greatestCommonDivisor(b, a % b);
		}
	}

	// method to calculate the mean
	public double calcMean(String[] list) {
		int sum = 0;
		if (list.length != 0) {
			for (int i = 0; i < list.length; i++) {
				sum = sum + Integer.parseInt(list[i]);
			}
			return sum / list.length;
		} else
			return 0;
	}

	// method to calculate standard deviation
	public double calcStandDeviation(String[] list, double mean) {
		double numerator = 0;

		for (int i = 0; i < list.length; i++) {
			numerator = numerator + Math.pow((Integer.parseInt(list[i]) - mean), 2);
		}
		return Math.sqrt((numerator / list.length));
	}

	// calculate the gender ratio and print it out
	public void genderRatio() {
		// use greatest common divisor method to get the ratio in simplest form
		final int greatestCommonDivisor = greatestCommonDivisor(numMale(), numFemale());
		System.out.println("---------------------------------------------");
		System.out.println("Gender ratio (M:F) is " + numMale() / greatestCommonDivisor + ":"
				+ numFemale() / greatestCommonDivisor);
	}

	// method to get the men's and women's age analysis
	public void ageAnalysis() {
		// making arrays for each gender
		String[] men_age = getMenArray(getAge());
		String[] women_age = getWomenArray(getAge());
		// analysis
		System.out.println("---------------------------------------------");
		System.out.println("Age analysis:" + '\n');
		System.out.printf("%-7s %-5s %-5s %n", "Gender", "Mean (years)", "Standard Deviation");
		System.out.printf("%-7s %11d %18.3f %n", "Male", (int) calcMean(men_age),
				calcStandDeviation(men_age, calcMean(men_age)));
		System.out.printf("%-7s %11d %18.3f %n", "Female", (int) calcMean(women_age),
				calcStandDeviation(women_age, calcMean(women_age)));
		System.out.println("");
	}

	// method to get the men's and women's height analysis
	public void heightAnalysis() {
		// making arrays for each gender
		String[] men_height = getMenArray(getHeight());
		String[] women_height = getWomenArray(getHeight());
		// analysis
		System.out.println("---------------------------------------------");
		System.out.println("Height analysis:" + '\n');
		System.out.printf("%-7s %-5s %-5s %n", "Gender", "Mean (cm)", "Standard Deviation");
		System.out.printf("%-7s %8d %18.3f %n", "Male", (int) calcMean(men_height),
				calcStandDeviation(men_height, calcMean(men_height)));
		System.out.printf("%-7s %8d %18.3f %n", "Female", (int) calcMean(women_height),
				calcStandDeviation(women_height, calcMean(women_height)));
		System.out.println("");
	}

	// method to get the men's and women's weight analysis
	public void weightAnalysis() {
		// making arrays for each gender
		String[] men_weight = getMenArray(getWeight());
		String[] women_weight = getWomenArray(getWeight());
		// analysis
		System.out.println("---------------------------------------------");
		System.out.println("Weight analysis:" + '\n');
		System.out.printf("%-7s %-5s %-5s %n", "Gender", "Mean (kg)", "Standard Deviation");
		System.out.printf("%-7s %8d %18.3f %n", "Male", (int) calcMean(men_weight),
				calcStandDeviation(men_weight, calcMean(men_weight)));
		System.out.printf("%-7s %8d %18.3f %n", "Female", (int) calcMean(women_weight),
				calcStandDeviation(women_weight, calcMean(women_weight)));
		System.out.println("");
	}

	// method to get the oldest male and female athlete
	public void oldestAthlete() {
		int oldest_ageM = 0;
		int oldest_ageF = 0;
		String oldest_man = "";
		String oldest_woman = "";
		// getting oldest man
		for (int i = 0; i < numMale(); i++) {
			if (Integer.parseInt(getMenArray(getAge())[i]) > oldest_ageM) {
				oldest_ageM = Integer.parseInt(getMenArray(getAge())[i]);
				oldest_man = getMenArray(getName())[i];
			} else if (Integer.parseInt(getMenArray(getAge())[i]) == oldest_ageM) {
				oldest_man += ", " + getMenArray(getName())[i];
			}
		}
		// getting oldest woman
		for (int i = 0; i < numFemale(); i++) {
			if (Integer.parseInt(getWomenArray(getAge())[i]) > oldest_ageF) {
				oldest_ageF = Integer.parseInt(getWomenArray(getAge())[i]);
				oldest_woman = getWomenArray(getName())[i];
			} else if (Integer.parseInt(getWomenArray(getAge())[i]) == oldest_ageF) {
				oldest_woman += " and" + getWomenArray(getName())[i];
			}
		}
		// conditionals to print only if that gender has data on it
		if (getMenArray(getAge()).length != 0) {
			System.out.println("Oldest male athlete(s): " + oldest_man + " (" + oldest_ageM + " years old)" + '\n');
		}
		if (getWomenArray(getAge()).length != 0) {
			System.out.println("Oldest Female athlete(s): " + oldest_woman + " (" + oldest_ageF + " years old)" + '\n');
		}
	}

	// method to get the youngest male and female athlete
	public void youngestAthlete() {
		int youngest_ageM = 100;
		int youngest_ageF = 100;
		String youngest_man = "";
		String youngest_woman = "";
		// getting youngest man
		for (int i = 0; i < numMale(); i++) {
			if (Integer.parseInt(getMenArray(getAge())[i]) < youngest_ageM) {
				youngest_ageM = Integer.parseInt(getMenArray(getAge())[i]);
				youngest_man = getMenArray(getName())[i];
			} else if (Integer.parseInt(getMenArray(getAge())[i]) == youngest_ageM) {
				youngest_man += ", " + getMenArray(getName())[i];
			}
		}
		// getting youngest woman
		for (int i = 0; i < numFemale(); i++) {
			if (Integer.parseInt(getWomenArray(getAge())[i]) < youngest_ageF) {
				youngest_ageF = Integer.parseInt(getWomenArray(getAge())[i]);
				youngest_woman = getWomenArray(getName())[i];
			} else if (Integer.parseInt(getWomenArray(getAge())[i]) == youngest_ageF) {
				youngest_woman += " and" + getWomenArray(getName())[i];
			}
		}
		// conditionals to print only if that gender has data on it
		if (getMenArray(getAge()).length != 0) {
			System.out
					.println("Youngest male athlete(s): " + youngest_man + " (" + youngest_ageM + " years old)" + '\n');
		}
		if (getWomenArray(getAge()).length != 0) {
			System.out.println(
					"Youngest Female athlete(s): " + youngest_woman + " (" + youngest_ageF + " years old)" + '\n');
		}
	}

	// method to find how many unique sports there are
	public void uniqueSports() {
		int count = 0;
		String output = "";
		String visited = "";
		// loop to get a String of all the unique sports and find how many unique sports
		// there are
		for (int i = 0; i < getSport().length; i++) {
			if (visited.contains(getSport()[i]) == false) {
				for (int j = 0; j < getSport().length; j++) {
					if (getSport()[i] == getSport()[j]) {
						count++;
						output += getSport()[i] + " ";
					}
				}
			}
			visited += getSport()[i];
		}
		unique_sports = new String[count];
		// loop to assign each different sport into an element of the unique_sports
		// array
		for (int i = 0; i < unique_sports.length; i++) {
			unique_sports[i] = output.split(" ")[i];
		}

		System.out.println("---------------------------------------------");
		System.out.println("There are " + count + " unique sports from this data list. They are: " + '\n');
		for (int i = 0; i < unique_sports.length; i++) {
			System.out.println((i + 1) + ". " + unique_sports[i]);
		}
	}

	// method to find out how many medals each gender gets for each medal type
	public void medalAnalysis() {
		int gold = 0, silver = 0, bronze = 0;
		// medals for men
		for (int i = 0; i < getMenArray(getMedal()).length; i++) {
			if (getMenArray(getMedal())[i].equalsIgnoreCase("Gold")) {
				gold++;
			} else if (getMenArray(getMedal())[i].equalsIgnoreCase("Silver")) {
				silver++;
			} else {
				bronze++;
			}
		}

		System.out.println("---------------------------------------------");
		System.out.println("Medals analysis:" + '\n');
		System.out.printf("%-7s %-5s %-5s %-5s %n", "Gender", "Gold", "Silver", "Bronze");
		System.out.printf("%-7s %5d %5d %5d %n", "Males", gold, silver, bronze);

		gold = 0;
		silver = 0;
		bronze = 0;
		// medals for women
		for (int i = 0; i < getWomenArray(getMedal()).length; i++) {
			if (getWomenArray(getMedal())[i].equalsIgnoreCase("Gold")) {
				gold++;
			} else if (getWomenArray(getMedal())[i].equalsIgnoreCase("Silver")) {
				silver++;
			} else {
				bronze++;
			}
		}
		System.out.printf("%-7s %5d %5d %5d %n", "Females", gold, silver, bronze);
		System.out.println("");
	}

	// method to calculate how many medals each gender gets in each sport for each
	// medal type
	public void specificMedalAnalysis() {
		// 2-dimensional arrays to store the medals separately for each gender and each
		// sport
		int gold[][] = new int[2][unique_sports.length];
		int silver[][] = new int[2][unique_sports.length];
		int bronze[][] = new int[2][unique_sports.length];
		// medals for each sport for men
		for (int i = 0; i < unique_sports.length; i++) {
			for (int j = 0; j < getMenArray(getSport()).length; j++) {
				if (getMenArray(getSport())[j].equalsIgnoreCase(unique_sports[i])) {
					if (getMenArray(getMedal())[j].equalsIgnoreCase("Gold")) {
						gold[0][i]++;
					} else if (getMenArray(getMedal())[j].equalsIgnoreCase("Silver")) {
						silver[0][i]++;
					} else {
						bronze[0][i]++;
					}
				}
			}
		}

		System.out.println("Sport specific: " + '\n');

		// medals for each sport for women
		for (int i = 0; i < unique_sports.length; i++) {
			for (int j = 0; j < getWomenArray(getSport()).length; j++) {
				if (getWomenArray(getSport())[j].equalsIgnoreCase(unique_sports[i])) {
					if (getWomenArray(getMedal())[j].equalsIgnoreCase("Gold")) {
						gold[1][i]++;
					} else if (getWomenArray(getMedal())[j].equalsIgnoreCase("Silver")) {
						silver[1][i]++;
					} else {
						bronze[1][i]++;
					}
				}
			}
		}
		for (int i = 0; i < unique_sports.length; i++) {
			System.out.println(unique_sports[i] + ":");
			System.out.printf("%-7s %-5s %-5s %-5s %n", "Gender", "Gold", "Silver", "Bronze");
			System.out.printf("%-7s %5d %5d %5d %n", "Males", gold[0][i], silver[0][i], bronze[0][i]);
			System.out.printf("%-7s %5d %5d %5d %n", "Females", gold[1][i], silver[1][i], bronze[1][i]);
			System.out.println("");
		}
	}

	// main method
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		AthleteAnalysis o1 = new AthleteAnalysis();
		int rounds = 1;
		int input_decision = 1;

		System.out.println("Welcome to the Athlete Analysis program! How would you like to enter your data?" + '\n'
				+ "1. Manual input" + '\n' + "2. File input" + '\n' + "0. Exit");
		input_decision = in.nextInt();
		// loop if user wants multiple tries
		while (input_decision != 0) {
			// loop for validity
			do {
				// manual input
				if (input_decision == 1) {
					// to get N
					System.out.println("How many records are there?");
					rounds = in.nextInt();

					AthleteAnalysis o2 = new AthleteAnalysis(rounds);
					o1 = o2;
					o1.manualInput();
				}
				// file input
				else if (input_decision == 2) {
					System.out.println("What is the file name/path?");
					String file_path = in.next();

					File data = new File(file_path);
					Scanner input = new Scanner(data);
					// to get N
					rounds = Integer.parseInt(input.next().split(",")[0]);

					AthleteAnalysis o2 = new AthleteAnalysis(rounds);
					o1 = o2;
					o1.fileInput(data);
				}

				if (o1.isValid() == false) {
					System.out.println("Your data is invalid, input again please.");
				}
				if (rounds == 0) {
					System.out.println("There are 0 rounds. Try again.");
				}
			} while (o1.isValid() == false || rounds == 0);
			// calling methods
			o1.printRecords();
			o1.genderRatio();
			o1.ageAnalysis();
			o1.oldestAthlete();
			o1.youngestAthlete();
			o1.heightAnalysis();
			o1.weightAnalysis();
			o1.uniqueSports();
			o1.medalAnalysis();
			o1.specificMedalAnalysis();

			System.out.println("would you like to go again?" + '\n' + "1. Manual input" + '\n' + "2. File input" + '\n'
					+ "0. Exit");
			input_decision = in.nextInt();
		}
	}
}