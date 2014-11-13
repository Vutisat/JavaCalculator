import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorDisplay extends JFrame implements ActionListener {
	private JTextField input;
	private int result, arg;
	private char op;

	public CalculatorDisplay() {
		super("Calculator");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		result = arg = op = 0;
		// Create a place for the calculator display.
		input = new JTextField(20);
		input.setEditable(false);
		input.setHorizontalAlignment(JTextField.RIGHT);
		input.setText("0");
		this.add(input, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 0));

		String text = "123+456-789*0/=C";
		for (int i = 0; i < 16; i++) {
			JButton b = new JButton(text.substring(i, i + 1));
			b.setForeground(new Color(0, 0, 0));
			b.setBackground(new Color(143, 236, 211));
			b.addActionListener(this);
			b.setActionCommand(b.getText());
			buttonPanel.add(b);
		}
		this.add(buttonPanel);
		this.pack();
		this.setVisible(true);
	}

	// Stuff that should happen when an event occurs.
	public void actionPerformed(ActionEvent ae) {
		char cmd = ae.getActionCommand().charAt(0);
		if (Character.isDigit(cmd) && input.getText().length() < 9) {
			// Build the number and display it
			arg = 10 * arg + (cmd - '0');
			input.setText("" + arg);
		}
		else if (cmd == 'C') {
			// Clear everything
			result = arg = op = 0;
			input.setText("" + arg);
		}
		else {
			// Perform the appropriate calculation
			// and show the result
			try {
				switch (op) {
				case 0:
					result = arg;
					break;
				case '+':
					result += arg;
					break;
				case '-':
					result -= arg;
					break;
				case '*':
					result *= arg;
					break;
				case '/':
					result /= arg;
				}
				input.setText("" + result);
				arg = 0;
				op = (cmd == '=' ? 0 : cmd);
			}
			catch (Exception e) {
				// What to do if the math goes wrong:
				input.setText("ERROR!");
				result = arg = op = 0;
			}
		}
	} // End actionPerformed()
}
