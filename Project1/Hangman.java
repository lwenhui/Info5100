import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class Hangman {
	private static final int MAX_GUESS_TIMES = 8;
	private static final boolean WIN = true;
	private static final boolean FAIL = false;
	private int times = 0; //cout the times of wrong guess 
	private ArrayList<Character> correctList = new ArrayList<Character>();
	private ArrayList<Character> wrongList = new ArrayList<Character>();
	private int wordLength = 0;
	Scanner input = new Scanner(System.in);
	private static ArrayList<String> words;

	//Initialises the words list
	public Hangman(ArrayList<String> words) {
		this.words = words;
	}

	//Randomly chooses a word from the list
	public String chooseWord() {
		Random random = new Random();
		int num = random.nextInt(this.words.size());
		String word = this.words.get(num);
		wordLength = word.length();
		return word;
	}

	//handle the guess and add the letter to correctList or WrongList
	public void handleGuess(char[] guessingWord, int count, String word){
		
		//loop
		while(true) {
			System.out.print("Now enter a letter: ");
			//ignore upperCase or lowerCase
			String toLower = input.next().toLowerCase();
			char letter = toLower.charAt(0);

			boolean isGuessed = correctList.contains(letter);
			if (isGuessed) {
				//System.out.println("letter : " + letter + "is guessed, enter another letter");
				times++;
				wrongList.add(letter);
			}

			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) == letter && !isGuessed) {
					correctList.add(letter);
					guessingWord[i] = letter; 
					count--;
				} 
			}
			if (!correctList.contains(letter)) {
				times++; 
				wrongList.add(letter);
			}

			//clear console
			clearConsole();

			printHangman();
			displayWord(guessingWord);
			int remainTimes = MAX_GUESS_TIMES - times;
			System.out.println("         You have " + remainTimes + " times to guess.");


			if (count == 0 || times >= MAX_GUESS_TIMES) {
				break;
			}

		}
		
	}

	//return true if user wins the game
	public boolean gameWon() {
		if (correctList.size() == wordLength && times <= MAX_GUESS_TIMES) {
			System.out.println("Congratulations! You win the game!");
			return WIN;
		}
		return FAIL;
	}

	//exit the program after the game is over
	public void gameOver(){
		if (times >= MAX_GUESS_TIMES && !gameWon()) {
			System.out.println("Game Over. You failed!");
		}
	}

	//print hangman after every guess
	private static final int ROW = 10;
	private static final int COLUMN = 12;
	public void printHangman() {
		String[][] hang = new String[ROW][COLUMN];

		for(int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {
				hang[i][j] = " ";
			}
		}

		for (int i = 0; i < COLUMN ; i++) {
			hang[0][i] = "-";
		}
		for (int i = 0; i < ROW; i++) {
			hang[i][0] = "|";
		}
		hang[1][6] = "|";
		for (int i = 0; i < COLUMN ; i++) {
			hang[ROW - 1][i] = "-";
		}

		if (times >= 1) {
			hang[2][6] = "0";
		}
		if (times >= 2) {
			hang[3][6] = "|";
		}
		if (times >= 3) {
			hang[4][3] = "-";
			hang[4][4] = "-";
			hang[4][5] = "-";
		}
		if (times >= 4) {
			hang[4][7] = "-";
			hang[4][8] = "-";
			hang[4][9] = "-";
		}
		if (times >= 5) {
			hang[5][5] = "/";
			hang[6][4] = "/";
		}
		if (times >= 6) {
			hang[5][7] = "\\";
			hang[6][8] = "\\";
		}
		if (times >= 7) {
			hang[7][2] = "-";
			hang[7][3] = "-";
		}
		if (times >= 8) {
			hang[7][9] = "-";
			hang[7][10] = "-";
		}
		for(int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {
				System.out.print(hang[i][j]);
			}
			System.out.println();
		}

	}

	//starts the game
	public void playGame(String word) {
		int count = word.length();
		char[] guessingWord = new char[count];
		for (int i = 0; i < count; i ++) {
			guessingWord[i] = '-';
		}
		times = 0;

		//clear console
		clearConsole();

		System.out.println("Game starts!");
		System.out.print("Guess a letter in word : ");
		for (int i = 0; i < count; i++) {
			System.out.print(guessingWord[i]);
		}
		System.out.println();
		
		handleGuess(guessingWord, count, word);
		

	}
	//display the correctly guessed letters and hide the remaining with dashes
	public void displayWord(char[] guessingWord) {
		if (guessingWord == null || guessingWord.length == 0) {
			return;
		}
		System.out.print("Word is : ");
		String currentWord = new String(guessingWord);
		System.out.print(currentWord);
	}

	//clear console
	public void clearConsole() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void main(String args[]) {
		
		ArrayList<String> words = new ArrayList<String>();
		words.add("write");
		words.add("read");
		words.add("test");
		words.add("problem");
		words.add("people");
		words.add("program");
		words.add("ourselves");
		words.add("apple");
		words.add("welcome");
		words.add("orange");

		Hangman hangman = new Hangman(words);

		String word = hangman.chooseWord();
		hangman.playGame(word);

		hangman.gameWon();
		hangman.gameOver();

	}
}