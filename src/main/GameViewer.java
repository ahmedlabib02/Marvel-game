package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameViewer extends JFrame {
	 JPanel desc;
	 JPanel choose;
	 JLayeredPane jp;
	 Board board;
	 Container content;
	 MousePanel mp;
	 JLabel fl1;
	 JLabel fl2 ;
	 JLabel fl3 ;
	 JLabel fl4 ;
	 JLabel fl5 ;
	 JLabel fl6; 

	public GameViewer() throws IOException {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);
		this.setBounds(0, 0, 1650,1080);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		content = this.getContentPane();
		content.setLayout(null);
		
		ImageIcon icon = new ImageIcon("marvel 2.jpeg");
		this.setIconImage(icon.getImage());
		
		this.setTitle("Marvel");
		//.put(.activeTitleBackground(), Color.red);
		
		jp=new JLayeredPane();
		jp.setBounds(0, 0, 1650, 1080);
		jp.setVisible(true);
		content.add(jp);
		
		BufferedImage myPicture = ImageIO.read(new File("marvel4.jpg"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setBounds(0, 0, 1650, 1080);
		jp.add(picLabel,Integer.valueOf(0));
		
		BufferedImage f1 = ImageIO.read(new File("frame.png"));
		Image newimg = f1.getScaledInstance(171, 220, java.awt.Image.SCALE_SMOOTH);
		fl1 = new JLabel(new ImageIcon(newimg));
		fl1.setBounds(1000,190,171,220);
		fl1.setVisible(false);
		jp.add(fl1,Integer.valueOf(1));
		
		BufferedImage f2 = ImageIO.read(new File("frame.png"));
		Image newimg2 = f2.getScaledInstance(171, 220, java.awt.Image.SCALE_SMOOTH);
		fl2 = new JLabel(new ImageIcon(newimg2));
		fl2.setBounds(1175,190,171,220);
		fl2.setVisible(false);
		jp.add(fl2,Integer.valueOf(1));
		
		BufferedImage f3 = ImageIO.read(new File("frame.png"));
		Image newimg3 = f3.getScaledInstance(171, 220, java.awt.Image.SCALE_SMOOTH);
		fl3 = new JLabel(new ImageIcon(newimg3));
		fl3.setBounds(1350,190,171,220);
		fl3.setVisible(false);
		jp.add(fl3,Integer.valueOf(1));
		
		BufferedImage f4 = ImageIO.read(new File("frame.png"));
		Image newimg4 = f4.getScaledInstance(171, 220, java.awt.Image.SCALE_SMOOTH);
		fl4 = new JLabel(new ImageIcon(newimg4));
		fl4.setBounds(1000,490,171,220);
		fl4.setVisible(false);
		jp.add(fl4,Integer.valueOf(1));
		
		BufferedImage f5 = ImageIO.read(new File("frame.png"));
		Image newimg5 = f5.getScaledInstance(171, 220, java.awt.Image.SCALE_SMOOTH);
		fl5 = new JLabel(new ImageIcon(newimg5));
		fl5.setBounds(1175,490,171,220);
		fl5.setVisible(false);
		jp.add(fl5,Integer.valueOf(1));
		
		BufferedImage f6 = ImageIO.read(new File("frame.png"));
		Image newimg6 = f6.getScaledInstance(171, 220, java.awt.Image.SCALE_SMOOTH);
		fl6 = new JLabel(new ImageIcon(newimg6));
		fl6.setBounds(1350,490,171,220);
		fl6.setVisible(false);
		jp.add(fl6,Integer.valueOf(1));
		
		choose = new JPanel();
		choose.setBounds(100,50,900,750);
		choose.setLayout(new GridLayout(3,5));
		choose.setOpaque(false);
		jp.add(choose,Integer.valueOf(2));
		
		
		
		desc = new JPanel();
		desc.setBounds(1010,0,535,1080);
		desc.setLayout(null);
		desc.setOpaque(false);
		desc.setVisible(false);
		jp.add(desc,Integer.valueOf(2));
		
		mp=new MousePanel();
		mp.setBounds(1050,100,400,600);
		jp.add(mp,Integer.valueOf(3));

        
		
		
		
		this.setVisible(true);
		this.revalidate();
		this.repaint();
	}
	


	public MousePanel getMp() {
		return mp;
	}



	public void setMp(MousePanel mp) {
		this.mp = mp;
	}



	public Container getContent() {
		return content;
	}

	public void setContent(Container content) {
		this.content = content;
	}

public JPanel getDesc() {
		return desc;
	}

	public void setDesc(JPanel desc) {
		this.desc = desc;
	}

public JPanel getChoose() {
		return choose;
	}

	public void setChoose(JPanel choose) {
		this.choose = choose;
	}






public static void main(String[] args) {
	//new GameViewer();
}



}