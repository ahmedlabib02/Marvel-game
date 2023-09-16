package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class GamePanel extends JPanel implements MouseListener {
public JPanel turns;
public JLabel pname;
public JLabel turnsdesc;
public JLabel l;
public JLabel m;
public JLabel leaderability;
public JLayeredPane jp;
public JProgressBar mana;
public JProgressBar actionPoints;



public GamePanel() {
	this.setPreferredSize(new Dimension(500,1080));
	this.setLayout(null);
	this.setBackground(Color.black);
	jp=new JLayeredPane();
	turns = new JPanel();
	turns.setOpaque(false);
	turns.setLayout(new FlowLayout());
	pname=new JLabel();
	pname.setForeground(new Color(255,215,0));
	turnsdesc=new JLabel();
	l=new JLabel();
	l.setForeground(Color.white);
	m=new JLabel();
	m.setForeground(Color.white);
	leaderability=new JLabel();
	leaderability.setForeground(Color.white);
	mana = new JProgressBar();
	actionPoints = new JProgressBar();
	mana.setFont(new Font("Trebuchet MS", Font.ITALIC, 15));
	actionPoints.setFont(new Font("Trebuchet MS", Font.ITALIC, 15));
	mana.setForeground(new Color(102,0,150));
	actionPoints.setForeground(Color.DARK_GRAY);
	this.setFocusable(false);
	this.revalidate();
	this.repaint();
	//this.setVisible(true);
	
}






public static void main(String[] args) {
	GamePanel info = new GamePanel();
	JFrame t = new JFrame();
	t.add(info);
	t.pack();
	t.setVisible(true);
}




@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}




@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}




@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}




@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}




@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
}