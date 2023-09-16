package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;




import main.Board;
import main.Controller;
import model.world.Champion;
import model.world.Cover;

public class CaptainAmerica extends Entity{
Board gp ;
BufferedImage logo;
int castCounter=1;
int timer =0;
public CaptainAmerica(Board b,Controller k,int xx,int yy,int maxHp) {
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
	direction ="up";
}
public void getHulkImage() {
	try {
		up1= ImageIO.read(getClass().getResourceAsStream("/Ca/ca_up.png"));
		up2= ImageIO.read(getClass().getResourceAsStream("/Ca/ca_up2.png"));
		down1= ImageIO.read(getClass().getResourceAsStream("/Ca/ca_down.png"));
		down2= ImageIO.read(getClass().getResourceAsStream("/Ca/ca_down2.png"));
		left1=ImageIO.read(getClass().getResourceAsStream("/Ca/ca_left.png"));
		left2= ImageIO.read(getClass().getResourceAsStream("/Ca/ca_left2.png"));
		right1= ImageIO.read(getClass().getResourceAsStream("/Ca/ca_right.png"));
		right2= ImageIO.read(getClass().getResourceAsStream("/Ca/ca_right2.png"));
		dead= ImageIO.read(getClass().getResourceAsStream("/Ca/ca_dead.png"));
		stand= ImageIO.read(getClass().getResourceAsStream("/Ca/ca_stand.png"));
		attack1= ImageIO.read(getClass().getResourceAsStream("/Ca/ca_attack.png"));
		attack2= ImageIO.read(getClass().getResourceAsStream("/Ca/ca_attack2.png"));
		cast1 = ImageIO.read(getClass().getResourceAsStream("/Ca/shiled_throw.png"));
		cast2= ImageIO.read(getClass().getResourceAsStream("/Ca/shiled_up.png"));
		cast3 = ImageIO.read(getClass().getResourceAsStream("/Ca/shiled_up2.png"));
		this.logo = ImageIO.read(getClass().getResourceAsStream("/Ca/logo.png"));
		
	}
	catch(IOException e) {
		e.printStackTrace();
	}
}
public void setProjectileImages(Projectile p) {
	try{p.up = ImageIO.read(getClass().getResourceAsStream("/Ca/p_up.png"));
		p.right = ImageIO.read(getClass().getResourceAsStream("/Ca/p_right.png"));
		p.left=ImageIO.read(getClass().getResourceAsStream("/Ca/p_right.png"));
		p.down =ImageIO.read(getClass().getResourceAsStream("/Ca/p_up.png"));
	}
	catch(IOException e) {
		e.printStackTrace();
	}
}
public void update() {
	if(keyH==null) {
		if(direction.equals("dead")) {
			deadCounter++;
			attacked=false;
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
	else if(keyH.cast1==true) {
		direction ="shield throw";
		timer++;
	}
	else if(keyH.cast2==true) {
		direction = "shield up";
	}
	else direction ="stand";
	stop--;
	if(stop<=0) {
		keyH.upPressed= false;
		keyH.leftPressed= false;
		keyH.rightPressed= false;
		keyH.downPressed= false;
		keyH.attackpressed=false;
		keyH.cast1=false;
		keyH.cast2 = false;
		castCounter= 1;
		gp.logo=null;
		
		
	}
	spriteCounter++;
	if(spriteCounter>20) {
		if(spriteNum==1)
			spriteNum=2;
		else if(spriteNum==2)
			spriteNum=1;
		spriteCounter =0;
		
		}
	if(timer>30) {
		if(castCounter==1)
			castCounter=2;
		timer=0;
	}
	

	
		
	
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
	case "shield throw": if(castCounter==1)
		image = cast1;
	else if(castCounter==2)
		image=stand; break;
	case "shield up": if(castCounter ==1)
		image = cast2;
	if(castCounter ==2)
		image = cast3;break;
	case "stand": image = stand;break;
	case "dead": if(deadCounter>60)image =null;
	else image = dead;
	
	}
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
	}
	
	}
	g2.drawImage(image, x, y, gp.tileSize*2, gp.tileSize*2,null);
}
@Override
public void ability1Sound(String a) {
	switch(a) {
	case "Shield throw": gp.keyH.playSE(2);break;
	case "I can do this all day":gp.keyH.playSE(1);break;
	case "Shield Up":	gp.keyH.playSE(25);break;
	}
	
}

@Override
public void leaderAbilitySound() {
	gp.keyH.playSE(3);
	gp.keyH.playSE(23);
	
}

public void animateAbility(int n) {
	if(n==0) {
		stop = 38;
		keyH.cast1=true;
		for(int i=0;i<targets.size();i++) {
			Projectile p = new Projectile(gp);
			setProjectileImages(p);
			if(targets.get(i) instanceof Cover) 
			p.set(x, y, true,((Cover)targets.get(i)).animation, this);
			if(targets.get(i) instanceof Champion) 
				p.set(x, y, true,((Champion)targets.get(i)).animation, this);
			gp.projectiles.add(p);}
	}
	if(n==2|| n==1) {
		stop = 60;
		keyH.cast2 = true;}
}
public void animateLeader() {
	stop = 120;
	keyH.cast2 = true;
	gp.logo = logo;
	
}
}
