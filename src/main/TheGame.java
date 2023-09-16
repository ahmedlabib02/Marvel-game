package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import model.world.Damageable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import engine.*;

public class TheGame extends JFrame {
Board board;
GamePanel Ginfo;
public TheGame(Controller keyH, ArrayList<Damageable> d) throws IOException {
	
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	this.setTitle("Marvel");
	board = new Board(keyH,d);
	Ginfo = new GamePanel();
	Ginfo.setPreferredSize(new Dimension(board.screenWidth,board.screenHeight));
	
	Ginfo.jp.setBounds(0, 0, board.screenWidth, board.screenHeight);
	Ginfo.jp.setVisible(true);
	
	BufferedImage myPicture = ImageIO.read(new File("gp.png"));
	JLabel picLabel = new JLabel(new ImageIcon(myPicture));
	picLabel.setBounds(0, 0, board.screenWidth, board.screenHeight);
	Ginfo.jp.add(picLabel,Integer.valueOf(0));
	
	Ginfo.turns.setBounds(0, 0, board.screenWidth,100);
	Ginfo.jp.add(Ginfo.turns,Integer.valueOf(1));
	
	Ginfo.l.setVerticalTextPosition(JLabel.TOP); 
	Ginfo.l.setBounds(0, 140, 200, 700);
	Ginfo.jp.add(Ginfo.l,Integer.valueOf(1));
	
	Ginfo.leaderability.setBounds(450, 105, 250, 25);
	Ginfo.jp.add(Ginfo.leaderability,Integer.valueOf(1));
	
	Ginfo.l.setVerticalTextPosition(JLabel.TOP); 
	Ginfo.m.setBounds(450, 140, 300, 700);
	Ginfo.jp.add(Ginfo.m,Integer.valueOf(1));
	
	Ginfo.pname.setBounds(0, 105, board.screenWidth,25);
	Ginfo.pname.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
	Ginfo.jp.add(Ginfo.pname,Integer.valueOf(1));
	
	Ginfo.add(Ginfo.jp);
	this.add(board,BorderLayout.EAST);
	this.add(Ginfo,BorderLayout.WEST);

	this.setUndecorated(true);
	this.pack();
	this.setLocationRelativeTo(null);
	this.setVisible(true);
	board.startGameThread();
	//this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	repaint();
	revalidate();
	}


public static void main(String[] args) {
	//TheGame x = new TheGame(null);
	
	
}
}
