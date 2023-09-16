package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;




import main.Board;
import main.Controller;

public class Electro extends Entity{
Board gp ;
public Electro(Board b,Controller k,int xx,int yy,int maxHp) {
	gp=b;
	keyH=k;
	x = (b.screenWidth/5)*xx;
	y = (4-yy)*(b.screenHeight/5);
	this.maxHp = maxHp;
	this.currentHp = maxHp;
	setDefaultValues();
	getHulkImage();
}
public void setDefaultValues() {
	speed=4;
	direction ="down";
}
public void getHulkImage() {
	try {
		up1= ImageIO.read(getClass().getResourceAsStream("/electro/electro_up.png"));
		up2= ImageIO.read(getClass().getResourceAsStream("/electro/electro_up2.png"));
		down1= ImageIO.read(getClass().getResourceAsStream("/electro/electro_down.png"));
		down2= ImageIO.read(getClass().getResourceAsStream("/electro/electro_down2.png"));
		left1=ImageIO.read(getClass().getResourceAsStream("/electro/electro_left1.png"));
		left2= ImageIO.read(getClass().getResourceAsStream("/electro/electro_left2.png"));
		right1= ImageIO.read(getClass().getResourceAsStream("/electro/electro_right1.png"));
		right2= ImageIO.read(getClass().getResourceAsStream("/electro/electro_right2.png"));
		stand= ImageIO.read(getClass().getResourceAsStream("/electro/electro_stand.png"));
		dead= ImageIO.read(getClass().getResourceAsStream("/electro/electro_dead.png"));
		attack1= ImageIO.read(getClass().getResourceAsStream("/electro/electro_attack.png"));
		attack2= ImageIO.read(getClass().getResourceAsStream("/electro/electro_attack2.png"));
		
	}
	catch(IOException e) {
		e.printStackTrace();
	}
}
public void update() {
	if(keyH==null) {
		if(direction.equals("dead")) {
			deadCounter++;
			direction="dead";}
		else
		direction="stand";
		return;}
	if(keyH.upPressed==true && stop>=0) {
		direction ="up";
		this.y-=4;}
	else if(keyH.downPressed==true&& stop>=0) {
	direction = "down";
	this.y+=4;}
	else if(keyH.rightPressed==true&& stop>=0) {
	direction = "right";
	this.x+=4;}
	else if(keyH.leftPressed==true&& stop>=0) {
direction = "left";
	this.x-=4;}
	else if(keyH.attackpressed==true&& stop>=0) {
		direction = "attack";
	}
	else direction ="stand";
	stop--;
	if(stop<=0) {
		keyH.upPressed= false;
		keyH.leftPressed= false;
		keyH.rightPressed= false;
		keyH.downPressed= false;
		keyH.attackpressed=false;
		
	}
	spriteCounter++;
	
	if(spriteCounter>20) {
		if(spriteNum==1)
			spriteNum=2;
		else if(spriteNum==2)
			spriteNum=1;
		spriteCounter =0;}
	
		
	
}
public void draw(Graphics2D g2) {
	BufferedImage image =null;
	switch(direction) {
	
	case "up": if(spriteNum==1) 
		image= up1;
	else if(spriteNum==2) 
		image= up2 ;
	break;
	case "down": if(spriteNum==1) 
		image= down1;
	else if(spriteNum==2) 
		image= down2 ;
	break;
	case "right":
		if(spriteNum==1) 
			image= right1;
	if(spriteNum==2)
		image= right2 ;
	break;
	case "left":if(spriteNum==1) 
		image= left1;
	else if(spriteNum==2)
		image= left2 ;
	break;
	case "attack": if(spriteNum==1)
		image = attack1;
	else if(spriteNum==2)
		image=attack2; break;
	case "stand": image = stand;break;
	case "dead": if(deadCounter>60)
		image = null;
	else image = dead;}
	if(attacked) {
		removeHp++;
	double oneScale = (double) gp.tileSize*2/maxHp;
	double hpBarValue= oneScale*currentHp;
	g2.setColor(new Color(35,35,35));
	g2.fillRect(x -1, y-16, (gp.tileSize*2)+2, 12);
	g2.setColor(new Color(255,0,30));
	g2.fillRect(x,y-15,(int)hpBarValue,10);
	if(removeHp>300) {
		attacked=false;
		removeHp=0;
	}}
	
	g2.drawImage(image, x, y, gp.tileSize*2, gp.tileSize*2,null);
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