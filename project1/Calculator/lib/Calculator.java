import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
 * @version 1.0 2014-08-01
 * @author 12330443 Zhuozhaojin
 */

public class Calculator {

    public static void main (String[] args)  {
 
	EventQueue.invokeLater(new Runnable() {
		public void run () {
		    CalculatorFrame frame = new CalculatorFrame();
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setVisible(true);
		}
	    });
    }
}

/**
 * A frame with Calculator frame
 */

class CalculatorFrame extends JFrame {

    public CalculatorFrame() {

	setTitle("Easy Calculator");
	CalculatorPanel panel = new CalculatorPanel();
	add(panel);
	pack();
    }
}

/**
 * A panel with Calculator buttons and a result display
 */
class CalculatorPanel extends JPanel {

    public CalculatorPanel() {

	result = 0;
	lastCommand = "+";
	ActionListener command = new CommandAction();

	GridLayout layout = new GridLayout(2, 5);
	layout.setHgap(5);
	layout.setVgap(5);

	panel = new JPanel();
	panel.setLayout(layout);
	panel.setPreferredSize(new Dimension(400, 200));

	// add display
	display = new JTextField();
	Command = new JTextField("+");
	firstNumber = new JTextField();
	secondNumber = new JTextField();
	JTextField equal =new JTextField("=");
	
	equal.setEnabled(false);
	display.setEnabled(false);
	Command.setEnabled(false);
		
	panel.add(firstNumber);
	panel.add(Command);
	panel.add(secondNumber);
	panel.add(equal);
	panel.add(display);
	
	addButton("+", command);
	addButton("-", command);
	addButton("*", command);
	addButton("/", command);
	addButton("OK", command);
	add(panel, BorderLayout.CENTER);
    }

    /**
     * Adds a button to the center panel
     * @param lable the button panel
     * @param listener the button listener
     */
    private void addButton(String lable, ActionListener listener) {
	
	JButton button = new JButton(lable);
	button.addActionListener(listener);
	panel.add(button);
    }

    /**
     * This action executes the command that the button aciton string denotes 
     */
    private class CommandAction implements ActionListener {

    	public void actionPerformed(ActionEvent event) {

	    String command = event.getActionCommand();
	    if (command.equals("OK")) {
		double x = Double.parseDouble(firstNumber.getText());
		double y = Double.parseDouble(secondNumber.getText());
		calculate(x, y);
		
	    } else {
		
		Command.setText(command);
		lastCommand = command;
	    }
	    
	}
    }

    /**
     * Carries out the pending calculation
     * @param x and y the value to be accumulated with the prior result.
     */
    public void calculate(double x, double y) {
	
	if (lastCommand.equals("+")) result = x + y;
	else if (lastCommand.equals("-")) result = x - y;
	else if (lastCommand.equals("*")) result = x * y;
	else if (lastCommand.equals("/")) {
	    if (0 == x)
		result = 0;
	    else
		result = x / y;
	}
	
	DecimalFormat format = new DecimalFormat("##.0000");
	display.setText("" + format.format(result));

    }
    
    private JTextField display;
    private JPanel panel;
    private double result;
    private String lastCommand;
    private JTextField Command;
    private JTextField firstNumber;
    private JTextField secondNumber;

}
