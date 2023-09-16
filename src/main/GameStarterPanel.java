package main;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GameStarterPanel extends JLayeredPane {

	 private JLabel p1;
	private  JTextField p1name;
	 private JLabel p2;
	 private JTextField p2name;
	private  JButton enterNamep1;
	private  JButton enterNamep2;
	private  ImageIcon error = new ImageIcon("message.png");

	public GameStarterPanel() {
		// ImageIcon error= new ImageIcon("errormessage.jpg");
		this.setPreferredSize(new Dimension(1500,1080));
		//this.setBounds(0, 0, 1650, 1080);
		JLabel image = new JLabel();
		image.setBounds(0, 0, 1550, 1080);
		ImageIcon x= new ImageIcon("background.jpg");
		Image newimg = x.getImage().getScaledInstance(1550, 1080, java.awt.Image.SCALE_SMOOTH);
		x=new ImageIcon(newimg);
		image.setIcon(x);
		this.add(image, JLayeredPane.DEFAULT_LAYER);

		p1 = new JLabel("Player one name:");
		p1.setBounds(350, 350, 200, 50);
		p1.setBackground(Color.red);
		p1.setForeground(Color.WHITE);
		p1.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(p1, JLayeredPane.DRAG_LAYER);
		p1name = new JTextField();
		p1name.setBounds(600, 350, 200, 50);
		this.add(p1name, JLayeredPane.DRAG_LAYER);

		enterNamep1 = new JButton("Enter Name of Player 1");
		enterNamep1.setBounds(850, 350, 300, 50);

	
		enterNamep1.setActionCommand("E1");
		enterNamep1.setFont(new Font("Arial", Font.BOLD, 18));
		enterNamep1.setBackground(new Color(140,0,150));
		enterNamep1.setForeground(Color.WHITE);
		this.add(enterNamep1, JLayeredPane.DRAG_LAYER);

		p2 = new JLabel("Player two name:");

		p2.setBounds(350, 650, 200, 50);
		p2.setBackground(Color.red);
		p2.setForeground(Color.WHITE);
		p2.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(p2, JLayeredPane.DRAG_LAYER);
		p2name = new JTextField();
		p2name.setBounds(600, 650, 200, 50);

		this.add(p2name, JLayeredPane.DRAG_LAYER);

		enterNamep2 = new JButton("Enter Name of Player 2");
		
		enterNamep2.setBounds(850, 650, 300, 50);
		enterNamep2.setActionCommand("E2");
		enterNamep2.setFont(new Font("Arial", Font.BOLD, 18));
		enterNamep2.setBackground(new Color(140,0,150));
		enterNamep2.setForeground(Color.WHITE);
		this.add(enterNamep2, JLayeredPane.DRAG_LAYER);

		this.validate();
		this.repaint();
	}

	public JLabel getP1() {
		return p1;
	}

	public JTextField getP1name() {
		return p1name;
	}

	public JLabel getP2() {
		return p2;
	}

	public JTextField getP2name() {
		return p2name;
	}

	public JButton getEnterNamep1() {
		return enterNamep1;
	}

	public JButton getEnterNamep2() {
		return enterNamep2;
	}

	public ImageIcon getError() {
		return error;
	}




}