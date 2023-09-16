package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;




import main.Board;
import main.Controller;
import model.world.Champion;
import model.world.Cover;
import model.world.Damageable;

public class Thor extends Entity{
Board gp ;
BufferedImage light1,light2,storm1,storm2;
int timer;
int stormx=0;
int stormy=300;
int leaderNum=1;
public Thor(Board b,Controller k,int xx,int yy,int maxHp) {
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
	direction ="stand";

}
public void getHulkImage() {
	try {
		up1= ImageIO.read(getClass().getResourceAsStream("/thor/thor_up.png"));
		up2= ImageIO.read(getClass().getResourceAsStream("/thor/thor_up2.png"));
		down1= ImageIO.read(getClass().getResourceAsStream("/thor/thor_down.png"));
		down2= ImageIO.read(getClass().getResourceAsStream("/thor/thor_down2.png"));
		left1=ImageIO.read(getClass().getResourceAsStream("/thor/thor_left1.png"));
		left2= ImageIO.read(getClass().getResourceAsStream("/thor/thor_left2.png"));
		right1= ImageIO.read(getClass().getResourceAsStream("/thor/thor_right1.png"));
		right2= ImageIO.read(getClass().getResourceAsStream("/thor/thor_right2.png"));
		dead= ImageIO.read(getClass().getResourceAsStream("/thor/thor_dead.png"));
		stand= ImageIO.read(getClass().getResourceAsStream("/thor/thor_stand.png"));
		attack1= ImageIO.read(getClass().getResourceAsStream("/thor/thor_attack.png"));
		attack2= ImageIO.read(getClass().getResourceAsStream("/thor/thor_attack2.png"));
		cast1= ImageIO.read(getClass().getResourceAsStream("/thor/cast.png"));
		cast2= ImageIO.read(getClass().getResourceAsStream("/thor/cast2.png"));
		light1= ImageIO.read(getClass().getResourceAsStream("/thor/light1.png"));
		light2= ImageIO.read(getClass().getResourceAsStream("/thor/light2.png"));
		storm1 =ImageIO.read(getClass().getResourceAsStream("/thor/storm1.png"));
		storm2=	ImageIO.read(getClass().getResourceAsStream("/thor/storm2.png"));	
		
	}
	catch(IOException e) {
		e.printStackTrace();
	}
}
public void setProjectileImages(Projectile p) {
	try{p.up = ImageIO.read(getClass().getResourceAsStream("/thor/p_up.png"));
		p.right = ImageIO.read(getClass().getResourceAsStream("/thor/p_right.png"));
		p.left=ImageIO.read(getClass().getResourceAsStream("/thor/p_left.png"));
		p.down =ImageIO.read(getClass().getResourceAsStream("/thor/p_down.png"));
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
			direction = "stand";
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
	else if(keyH.leader==true|| keyH.cast1==true) {
		direction = "leader";
		if(keyH.cast1!=true)
		stormx+=5;
		timer++;
		if(timer>180){
			keyH.cast1=false;
			keyH.leader=false;
			timer=0;
			leaderNum=1;
		}
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
			spriteNum=2	;		
		else if(spriteNum==2)
			spriteNum=1;
		spriteCounter =0;}
	if(timer>40) {
		if(leaderNum==1)
			leaderNum=2;
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
	case "leader":  if(leaderNum==1)
		image = cast1;
	else if(leaderNum==2)
		image=cast2; break;
	case "stand": image = stand;break;
	case "dead":  if(deadCounter>60)image =null;
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
	}}
	if(direction.equals("leader") ) {
	for(int i=0;i<targets.size();i++) {
		if(spriteNum == 1) {
			if(targets.get(i) instanceof Cover)
			g2.drawImage(light1,((Cover) targets.get(i)).animation.x, ((Cover) targets.get(i)).animation.y-gp.tileSize*2,gp.tileSize*2,gp.tileSize*4 , null);
	if(targets.get(i) instanceof Champion)
		g2.drawImage(light1,((Champion) targets.get(i)).animation.x, ((Champion) targets.get(i)).animation.y-gp.tileSize*2,gp.tileSize*2,gp.tileSize*4 , null);}
	if(spriteNum == 2) {
		if(targets.get(i) instanceof Cover)
		g2.drawImage(light2,((Cover) targets.get(i)).animation.x, ((Cover) targets.get(i)).animation.y-gp.tileSize*2,gp.tileSize*2,gp.tileSize*4 , null);
	if(targets.get(i) instanceof Champion)
	g2.drawImage(light2,((Champion) targets.get(i)).animation.x, ((Champion) targets.get(i)).animation.y-gp.tileSize*2,gp.tileSize*2,gp.tileSize*4 , null);}
	
	}
	if(keyH.cast1!=true) {
	if(spriteNum==1)
		g2.drawImage(storm1,stormx, stormy ,gp.tileSize*6,gp.tileSize*8, null);
	if(spriteNum==2)
		g2.drawImage(storm2,stormx, stormy ,gp.tileSize*6,gp.tileSize*8, null);}}
	
	
	g2.drawImage(image, x, y, gp.tileSize*2, gp.tileSize*2,null);
}
public void ability1Sound(String a) {
	switch(a) {
	case "God of Thunder": gp.keyH.playSE(17);break;
	case "Mjollnir Throw": gp.keyH.playSE(16);break;
	case "Bring Me Thanos":	gp.keyH.playSE(15);break;
	}
	
}
public void leaderAbilitySound() {
	gp.keyH.playSE(17);
	gp.keyH.playSE(26);
}
public void animateAbility(int n) {
	if(n==0) 
		keyH.cast1 = true;
	
	if(n==1) {
		this.stop=35;
		keyH.attackpressed=true;
		for(int i=0;i<targets.size();i++) {
			Projectile p = new Projectile(gp);
			setProjectileImages(p);
			if(targets.get(i) instanceof Cover) 
			p.set(x, y, true,((Cover)targets.get(i)).animation, this);
			if(targets.get(i) instanceof Champion) 
				p.set(x, y, true,((Champion)targets.get(i)).animation, this);
			gp.projectiles.add(p);}}
		if(n==2) {
			this.stop=35;
			keyH.attackpressed=true;
		}
		
	
		
	
}
	public void animateLeader() {
		keyH.leader=true;
		
		
	}

}
