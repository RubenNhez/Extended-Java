import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TipCalculator extends JFrame implements ActionListener {

    JTextField price;
    JTextField tip;
    JTextField people;

    JLabel output;

    TipCalculator() {
        this.setTitle("Meal Splitter");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        //Add in a JTextField for price
        this.add(new JLabel("Price"));
        price = new JTextField(5);
        price.setActionCommand("Price");
        price.addActionListener(this);
        this.add(price);

        //Add in a JTextField for tip
        this.add(new JLabel("Tip"));
        tip = new JTextField(5);
        tip.setActionCommand("Tip");
        tip.addActionListener(this);
        this.add(tip);

        //Add in a JTextField for people
        this.add(new JLabel("People"));
        people = new JTextField(5);
        people.setActionCommand("People");
        tip.addActionListener(this);
        this.add(people);

        //Add Button
        JButton calculate = new JButton("Calculate");
        this.add(calculate);
        calculate.addActionListener(this);
        output = new JLabel("Each person should pay ?");
        this.add(output);

        this.setVisible(true);


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TipCalculator();
            }
        });
    }


    public void actionPerformed(ActionEvent e) {
        double p = 0.0;
        int ppl = 0;
        double t = 0.0;
        try {
            p = Double.parseDouble(price.getText());
        }catch (NumberFormatException numExcep) {
            JOptionPane.showMessageDialog(null,"Enter a valid number");
            price.setText("");
        }

        try {
            t = (Double.parseDouble(price.getText()) / 100) * Double.parseDouble(tip.getText());
        } catch (NumberFormatException numExcept) {
            JOptionPane.showMessageDialog(null,"Enter a valid number");
            tip.setText("");
        }

        try{
            ppl = Integer.parseInt(people.getText());
        }catch (NumberFormatException numException) {
            JOptionPane.showMessageDialog(null, "Enter a valid number");
            people.setText("");
        }

        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equals("Calculate")){
            output.setText("Each person should pay " + String.format("%.2f",(p + t) / ppl));
        }
    }
}


