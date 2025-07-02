import java.util.*;

public class MainChange {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean biggerPaid = false;

        double price = 0.00, paid = 0.00;

        while (!biggerPaid) {
            price = getMoneyInput("Enter the price in pounds and pence", in);
            paid = getMoneyInput("Enter the amount paid in pounds and pence", in);
            if (price > paid) {
                System.out.println("You haven't paid enough!");
            } else {
                biggerPaid = true;
            }
        }

        System.out.println("price " + price);
        TreeMap<NotesAndCoins, Integer> changeComposition = calcChange(price, paid);

        for (NotesAndCoins n : changeComposition.keySet()) {
            if (changeComposition.get(n) != 0) {
                System.out.println(n.getName() + ": " + changeComposition.get(n));
            }
        }

    }

    //takes input from the user and ensures it is a double and returns a double with 2 decimal places
    //Question(String) is the prompt for user input and (in)scanner is collecting user input
    public static double getMoneyInput(String question, Scanner in) {
        boolean validInput = false;
        double amount = 0.00;
        //do this until the user enters a valid double
        while (!validInput) {
            System.out.println(question);
            try {
                amount = in.nextDouble();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input try again");
                in.next();
            }
        }
        //return the value entered fixed to 2dp
        return (double) ((int) (amount * 100)) / 100;
    }

    public static TreeMap<NotesAndCoins, Integer> calcChange(double price, double paid) {

        //COMPLETE THIS METHOD!!!
        //This method will return a TreeMap where the key is NotesAndCoins and the value is the number of each denomination to make up the change
        TreeMap<NotesAndCoins, Integer> notesandcoinsMap = new TreeMap<>(new sort());

        NotesAndCoins[] notesAndCoins = NotesAndCoins.values();
        //Convert Price and Paid into pences
        int priceinpences = (int) (price * 100);
        int paidinpences = (int) (paid * 100);
        //find the change owed to the user
        double change = paidinpences - priceinpences;
        for (NotesAndCoins n : notesAndCoins) {
            notesandcoinsMap.put(n, 0);
            //if change is bigger than enum value subtract and add 1 to the value
            if(change >= n.getValueInP()) {
                change -=n.getValueInP();
                notesandcoinsMap.put(n, 1);
            }
            //while the change is still lower than the enum values keep subtracting and add 1 to the value
            while (change >= n.getValueInP()) {
                change -= n.getValueInP();
                notesandcoinsMap.put(n,notesandcoinsMap.get(n) + 1);
            }
        }
        //Print and return the treemap
        System.out.println(notesandcoinsMap);
        return notesandcoinsMap;
    }

}

    class sort implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            NotesAndCoins n1 = (NotesAndCoins) o1;
            NotesAndCoins n2 = (NotesAndCoins) o2;

            if (n1.getValueInP() > n2.getValueInP()) {
                return -1;
            }
            if (n1.getValueInP() < n2.getValueInP()) {
                return 1;
            }
            return 0;
        }
    }



