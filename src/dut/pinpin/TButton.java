package dut.pinpin;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class TButton extends JButton {
	
	public TButton(String text) {
		super(text);
		this.setBackground(Color.GRAY);
		this.setForeground(Color.WHITE);
		this.setFocusPainted(false);
		this.setFont(new Font("Tahoma", Font.BOLD, 12));
	}
	
}
