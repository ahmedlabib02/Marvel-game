package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Board;
import main.Controller;

public class Objects extends Entity {
	public Board gp;
	public Controller keyH;
	public BufferedImage car, tree,rubble;

	public Objects(Board b,Controller keyH,int xx,int yy,int maxHp) {
		gp=b;
		
		x = (b.screenWidth/5)*xx;
		y = (4-yy)*(b.screenHeight/5);
		this.keyH = null;
		this.maxHp = maxHp;
		this.currentHp= maxHp;
		
		direction ="alive";
		getHulkImage();
	}
	public void getHulkImage() {
		try {
			tree= ImageIO.read(getClass().getResourceAsStream("/Cover/Tree.png"));
			car= ImageIO.read(getClass().getResourceAsStream("/Cover/car.png"));
			rubble = ImageIO.read(getClass().getResourceAsStream("/Cover/rubble.png"));;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void draw(Graphics2D g2) {
	 BufferedImage image=null;
			if(!direction.equals("dead"))
				image = tree;
			else attacked = false;
			if(attacked) {
				removeHp++;
				double oneScale = (double) gp.tileSize*2/maxHp;
				double hpBarValue= oneScale*currentHp;
				g2.setColor(new Color(35,35,35));
				g2.fillRect(x -1, y-16, (gp.tileSize*2)+2, 12);
				g2.setColor(new Color(255,0,30));
				g2.fillRect(x,y-15,(int)hpBarValue,10);
				g2.setColor(Color.WHITE);
				g2.setFont(hp_font);
				g2.drawString(""+currentHp+"/"+maxHp+" Hp",x +gp.tileSize-30 , y-5);
				if(removeHp>300) {
					attacked=false;
					removeHp=0;
				}}
			if(makeSound) {
				gp.keyH.playSE(29);
				makeSound= false;}
		
		g2.drawImage(image, x, y, gp.tileSize*2, gp.tileSize*2,null);
		
	}

	@Override
	public void update() {
		
		
	}
	@Override
	public void ability1Sound(String a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leaderAbilitySound() {
		// TODO Auto-generated method stub
		
	}

}