//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICalculator extends JFrame implements ActionListener {

    // Declare components
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JPanel panel;

    // Operator buttons
    private JButton addButton, subButton, mulButton, divButton;
    // Other buttons
    private JButton decButton, equButton, delButton, clrButton;

    // Variables for logic
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public GUICalculator() {
        // Frame setup
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 400);
        this.setLayout(null);
        this.setResizable(false);

        // Display field
        display = new JTextField();
        display.setBounds(30, 25, 240, 50);
        display.setEditable(false);
        this.add(display);

        // Buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");

        functionButtons = new JButton[] {
                addButton, subButton, mulButton, divButton, decButton, equButton, delButton, clrButton
        };

        for (JButton button : functionButtons) {
            button.addActionListener(this);
            button.setFocusable(false);
        }

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFocusable(false);
        }

        // Panel for button layout
        panel = new JPanel();
        panel.setBounds(30, 100, 240, 250);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Add buttons to the panel
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        // Add panel and other buttons to the frame
        this.add(panel);

        delButton.setBounds(30, 360, 100, 40);
        clrButton.setBounds(170, 360, 100, 40);
        this.add(delButton);
        this.add(clrButton);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Number buttons
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                display.setText(display.getText().concat(String.valueOf(i)));
            }
        }

        // Decimal button
        if (e.getSource() == decButton) {
            display.setText(display.getText().concat("."));
        }

        // Operator buttons
        if (e.getSource() == addButton || e.getSource() == subButton || e.getSource() == mulButton || e.getSource() == divButton) {
            try {
                num1 = Double.parseDouble(display.getText());
                operator = command.charAt(0);
                display.setText("");
            } catch (NumberFormatException ex) {
                // Handle cases where the display is not a valid number
                display.setText("Error");
            }
        }

        // Equals button
        if (e.getSource() == equButton) {
            try {
                num2 = Double.parseDouble(display.getText());

                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            display.setText("Error: Div by 0");
                            return;
                        }
                        break;
                }
                display.setText(String.valueOf(result));
                num1 = result;
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        }

        // Clear button
        if (e.getSource() == clrButton) {
            display.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
        }

        // Delete button
        if (e.getSource() == delButton) {
            String temp = display.getText();
            if (temp.length() > 0) {
                display.setText(temp.substring(0, temp.length() - 1));
            }
        }
    }

    public static void main(String[] args) {
        new GUICalculator();
    }
}