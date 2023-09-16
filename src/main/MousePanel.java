package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.color.ColorSpace;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MousePanel extends JPanel{
	TextArea a;
	public MousePanel() {
		setOpaque(false);
		setBackground(new Color(Color.TRANSLUCENT));
		setPreferredSize(new Dimension(430,1080));
		setLayout(null);
		setVisible(false);
		a=new TextArea();
	    a.setBackground(new Color(0,0,0,0));
		a.setBounds(0, 0,500,700);
		a.setFont(new Font("Helvecta", Font.BOLD, 15));
		a.setForeground(Color.WHITE);
		add(a);
	}
	public TextArea getA() {
		return a;
	}
}
