import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class HangmanGUI extends JFrame implements ActionListener  {
    //Array list for target words
    public static ArrayList<String> targetWords = new ArrayList<>();
    //Array list for the wrong answers
    ArrayList<String> wrongAns = new ArrayList<>();
    //Array list for the right answers
    ArrayList<String> rightAns = new ArrayList<>();
    //Array list for the random word's letters
    static ArrayList<String> randomWordChars = new ArrayList<>();
    //String to store the random word
    public static String randomWord;
    //Declare text field for input
    JTextField Guess;
    // Declare labels
    JLabel[] labels = new JLabel[randomWord.length()];
    //counters
    int correctAns = 0;
    int counter = randomWordChars.size();
    // Declare label for the output
    JLabel output;
    // String array for acceptable words
    String [] acceptableWords = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    // Array labels
    JLabel[] acceptWords = new JLabel[acceptableWords.length];


    HangmanGUI() {
        this.setTitle("Hangman");
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        //Add in a JTextField for Guess
        this.add(new JLabel("Guess"));
        Guess = new JTextField(5);
        Guess.setActionCommand("Guess");
        Guess.addActionListener(this);
        this.add(Guess);

        //Add button
        JButton Check = new JButton("Submit Guess");
        this.add(Check);
        Check.addActionListener(this);
        output = new JLabel("Guesses Remaining: " + randomWord.length()+ "                                                  ");
        this.add(output);

        //Add letters with the length of the random word to GUI
        for (int i = 0; i < randomWord.length(); i++) {
            labels[i] = new JLabel("Letter: " + i);

        }
        for (int i = 0; i < labels.length; i++) {
            this.add(labels[i]);
        }
        //Add Label to GUI
        this.add(new JLabel("          CORRECT AND WRONG ANSWERS:         "));

        //Add the alphabet to GUI
        for (int i = 0; i < acceptableWords.length; i++) {
            acceptWords[i] = new JLabel("   " + acceptableWords[i]);
        }
        for (int i = 0; i < acceptWords.length; i++) {
            this.add(acceptWords[i]);
        }

        this.setVisible(true);
    }

    //Get all the words from the wordlist text
    public static void allwords() throws FileNotFoundException {
        Scanner in = new Scanner(new File("wordlist.txt"));
        while(in.hasNext()){
            targetWords.add(in.next());
        }

    }

    // Get the random word
    private static String getWord() {
        Random r = new Random();
        String word = targetWords.get(r.nextInt(targetWords.size()));
        System.out.println("The target word is: " + word);
        return word;
    }

    //Push each letter of the word into an array list
    public static void pushWord(String randomWord) {
        for (int i = 0; i < randomWord.length(); i++) {
            randomWordChars.add(String.valueOf(randomWord.charAt(i)));
        }
        System.out.println("The Characters are " + randomWordChars);
    }
    //Start GUI for Hangman
    public static void main(String[] args) throws FileNotFoundException {
        allwords();
        randomWord = getWord();
        pushWord(randomWord);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HangmanGUI();
            }

        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // String to get the word input
        String guess = String.valueOf(Guess.getText());

            if (e.getActionCommand().equals("Submit Guess")) {
                //Go through each letter in the arraylist and if any equal the input reveal the letter, add it into the rightAns array and increase the counter for correctAns
                for (int i = 0; i < randomWordChars.size(); i++) {

                    if(Objects.equals(randomWordChars.get(i), guess)) {
                        labels[i].setText("Letter: " + guess);
                        rightAns.add(guess);
                        correctAns++;

                //If answer is correct make the alphabet letter green
                        for (int j = 0; j < rightAns.size(); j++) {
                            for (int k = j+1; k < acceptableWords.length; k++) {
                                if(Objects.equals(rightAns.get(j), acceptableWords[k])) {
                                    acceptWords[k].setOpaque(true);
                                    acceptWords[k].setBackground(Color.green);
                                }
                            }
                        }

                    }
                }
                //Print the right inputs
                System.out.println( "Right Inputs: " + rightAns);

                //Print the correct guesses
                System.out.println("Correct Guesses: " + correctAns);
                //Display the Remaining guesses
                if(randomWordChars.contains(guess)) {
                    output.setText("Guesses Remaining: " + counter + "                                                  ");
                }
                //If the input is wrong or not the right letter make the letter red in the alphabet, decrease the number of guesses and add the wrong answer/Input into the wrongAns
                else {
                    wrongAns.add(guess);
                    counter--;
                    for (int i = 0; i < wrongAns.size(); i++) {
                        for (int j = 0; j < acceptableWords.length; j++) {
                            if(Objects.equals(wrongAns.get(i), acceptableWords[j])) {
                                acceptWords[j].setOpaque(true);
                                acceptWords[j].setBackground(Color.red);
                            }
                        }
                    }
                    //Print Remaining guesses
                    output.setText("Guesses Remaining: " + counter + "                                                  ");
                    //Print the wrongAns array list and all the acceptable words
                    //Print Wrong inputs
                    System.out.println("Wrong Inputs: " + wrongAns);
                    System.out.println("Wrong answers or Input a Correct input -1 Guess, see acceptable inputs down below:");
                    System.out.println(Arrays.toString(acceptableWords));

                }
                //If all the guessed used close the GUI and Print that the Game Lost
                 if(counter == 0) {
                    System.out.println("Game Lost");
                    output.setText("Game Lost");
                    System.exit(0);

                }
                 //If all the words are guessed Print You Win and Display a message in the GUI
                 if (rightAns.size() == randomWordChars.size()) {
                    System.out.println("YOU WIN");
                    output.setText("YOU GUESSED THE WORD WELL DONE !");
                    JLabel win = new JLabel("(Reopen GUI to play again)");
                    this.add(win);
                }
        }
    }

}

