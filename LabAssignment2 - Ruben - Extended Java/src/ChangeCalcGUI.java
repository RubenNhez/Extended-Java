import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.TreeMap;

public class ChangeCalcGUI extends JFrame implements ActionListener {
    JTextField price;
    JTextField paid;

    JLabel output;

    ChangeCalcGUI() {
        this.setTitle("Change Calculator");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        //Add in a JTextField for price
        this.add(new JLabel("Price"));
        price = new JTextField(5);
        price.setActionCommand("Price");
        price.addActionListener(this);
        this.add(price);

        //Add in a JTextField for paid
        this.add(new JLabel("Paid"));
        paid = new JTextField(5);
        paid.setActionCommand("Paid");
        paid.addActionListener(this);
        this.add(paid);

        //Add Button
        JButton calculate = new JButton("Calculate");
        this.add(calculate);
        calculate.addActionListener(this);
        output = new JLabel("How much change should you get ?");
        this.add(output);

        this.setVisible(true);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChangeCalcGUI();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        double Price = 0.0;
        double  Paid= 0;

        try {
            Price = Double.parseDouble(price.getText());
        }catch (NumberFormatException numExcep) {
            JOptionPane.showMessageDialog(null,"Enter a valid number");
            price.setText("");
        }
        try{
            Paid = Double.parseDouble(paid.getText());
        }catch (NumberFormatException numExcep) {
            JOptionPane.showMessageDialog(null,"Enter a valid number");
            paid.setText("");
        }
        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equals("Calculate")){
            calcChange(Price,Paid);
            output.setText("How much change should you get " + (Paid- Price));
        }


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
        System.out.println("Change is " + notesandcoinsMap);
        return notesandcoinsMap;
    }

}

