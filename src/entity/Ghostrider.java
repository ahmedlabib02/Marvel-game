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

public class Ghostrider extends Entity{
Board gp ;
BufferedImage cast4,logo1,logo2,storm1, storm2;
int stormx;
int stormy= 768/2-100;
public Ghostrider(Board b,Controller k,int xx,int yy,int maxHp) {
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
		up1= ImageIO.read(getClass().getResourceAsStream("/Ghostrider/ghostrider_up.png"));
		up2= ImageIO.read(getClass().getResourceAsStream("/Ghostrider/ghostrider_up2.png"));
		down1= ImageIO.read(getClass().getResourceAsStream("/Ghostrider/ghostrider_down.png"));
		down2= ImageIO.read(getClass().getResourceAsStream("/Ghostrider/ghostrider_down2.png"));
		left1=ImageIO.read(getClass().getResourceAsStream("/Ghostrider/ghostrider_left.png"));
		left2= ImageIO.read(getClass().getResourceAsStream("/Ghostrider/ghostrider_left2.png"));
		right1= ImageIO.read(getClass().getResourceAsStream("/Ghostrider/ghostrider_right.png"));
		right2= ImageIO.read(getClass().getResourceAsStream("/Ghostrider/ghostrider_right2.png"));
		dead= ImageIO.read(getClass().getResourceAsStream("/Ghostrider/ghostrider_dead.png"));
		stand= ImageIO.read(getClass().getResourceAsStream("/Ghostrider/ghostrider_stand.png"));
		attack1= ImageIO.read(getClass().getResourceAsStream("/Ghostrider/ghostrider_attack.png"));
		attack2= ImageIO.read(getClass().getResourceAsStream("/Ghostrider/ghostrider_attack2.png"));
		cast1=   ImageIO.read(getClass().getResourceAsStream("/Ghostrider/death_stare.png"));
		cast2 =ImageIO.read(getClass().getResourceAsStream("/Ghostrider/death_stare2.png"));
		cast3 =ImageIO.read(getClass().getResourceAsStream("/Ghostrider/chain whip.png"));
		cast4 =ImageIO.read(getClass().getResourceAsStream("/Ghostrider/chain whip2.png"));
		logo1  =ImageIO.read(getClass().getResourceAsStream("/Ghostrider/logo1.png"));
		logo2= ImageIO.read(getClass().getResourceAsStream("/Ghostrider/logo2.png"));
		storm1 = ImageIO.read(getClass().getResourceAsStream("/Ghostrider/storm1.png"));
		storm2 = ImageIO.read(getClass().getResourceAsStream("/Ghostrider/storm2.png"));

	}
	catch(IOException e) {
		e.printStackTrace();
	}
}
public void setProjectileImages(Projectile p) {
	try{p.up = ImageIO.read(getClass().getResourceAsStream("/Ghostrider/p_up.png"));
		p.right = ImageIO.read(getClass().getResourceAsStream("/Ghostrider/p_right.png"));
		p.left=ImageIO.read(getClass().getResourceAsStream("/Ghostrider/p_left.png"));
		p.down =ImageIO.read(getClass().getResourceAsStream("/Ghostrider/p_down.png"));
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
	else if (keyH.cast3) {
		direction ="chain whip";
	}
	else if(keyH.cast1==true) {
		direction ="death stare";
	}
	else if (keyH.leader==true ) {
		direction = "leader";
		if(stop<60)
			gp.logo=logo2;
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
		keyH.cast3 = false;
		keyH.leader = false; 
		gp.logo=null;
		
	}
	if(keyH.leader==true) 
		stormx+=5;

	
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
	case "chain whip": 	if(spriteNum==1)
		image = cast3;
	else if(spriteNum==2)
		image=cast4; break;
	case "death stare":	if(spriteNum==1)
		image = attack1;
	else if(spriteNum==2)
		image=attack2; break;
	case "leader":	case "stand": image = stand;break;
	case "dead":  if(deadCounter>60)image =null;
	else image = dead;}
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
	if(direction.equals("death stare") ) {
		//System.out.print(targets.size());
		for(int i=0;i<targets.size();i++) {
			if(spriteNum == 1) {
				if(targets.get(i) instanceof Cover)
				g2.drawImage(cast1,((Cover) targets.get(i)).animation.x, ((Cover) targets.get(i)).animation.y-gp.tileSize*2,gp.tileSize*2,gp.tileSize*4 , null);
		if(targets.get(i) instanceof Champion)
			g2.drawImage(cast1,((Champion) targets.get(i)).animation.x, ((Champion) targets.get(i)).animation.y-gp.tileSize*2,gp.tileSize*2,gp.tileSize*4 , null);}
		if(spriteNum == 2) {
			if(targets.get(i) instanceof Cover)
			g2.drawImage(cast2,((Cover) targets.get(i)).animation.x, ((Cover) targets.get(i)).animation.y-gp.tileSize*2,gp.tileSize*2,gp.tileSize*4 , null);
		if(targets.get(i) instanceof Champion)
		g2.drawImage(cast2,((Champion) targets.get(i)).animation.x, ((Champion) targets.get(i)).animation.y-gp.tileSize*2,gp.tileSize*2,gp.tileSize*4 , null);}
		
		}}
	if(direction.equals("leader")) {
		if(spriteNum==1)
			g2.drawImage(storm1,stormx, stormy ,gp.tileSize*6,gp.tileSize*8, null);
		if(spriteNum==2)
			g2.drawImage(storm2,stormx, stormy ,gp.tileSize*6,gp.tileSize*8, null);
	}
		
	
	
	g2.drawImage(image, x, y, gp.tileSize*2, gp.tileSize*2,null);
}
@Override
public void ability1Sound(String a) {
	switch(a) {
	case "Death stare": gp.keyH.playSE(6);break;
	case "Fire Breath":gp.keyH.playSE(6);break;
	case "Chain Whip":	gp.keyH.playSE(6);break;
	}
	
}


public void leaderAbilitySound() {
	gp.keyH.playSE(7);
	gp.keyH.playSE(26);
}
public void animateAbility(int n) {
	if(n==0) {
		stop=40;
		keyH.cast1=true;
	}
	if(n==1) {
		stop=40;
		keyH.attackpressed=true;
		for(int i=0;i<targets.size();i++) {
			Projectile p = new Projectile(gp);
			setProjectileImages(p);
			if(targets.get(i) instanceof Cover) 
			p.set(x, y, true,((Cover)targets.get(i)).animation, this);
			if(targets.get(i) instanceof Champion) 
				p.set(x, y, true,((Champion)targets.get(i)).animation, this);
			gp.projectiles.add(p);}
		
	}
	if(n==2) {
		stop = 40;
		keyH.cast3=true;
	}
}
public void animateLeader() {
	stop = 120;
	gp.logo = logo1;
    keyH.leader=true;
}}