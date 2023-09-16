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

public class Deadpool extends Entity{
Board gp ;
BufferedImage cast4 , leader1,leader2,bomb1,bomb2;
boolean explode;
Entity tmp;
int actionNum=1;
int timer;

public Deadpool(Board b,Controller k,int xx,int yy,int maxHp) {
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
		up1= ImageIO.read(getClass().getResourceAsStream("/Deadpool/deadpool_up.png"));
		up2= ImageIO.read(getClass().getResourceAsStream("/Deadpool/deadpool_up2.png"));
		down1= ImageIO.read(getClass().getResourceAsStream("/Deadpool/deadpool_down.png"));
		down2= ImageIO.read(getClass().getResourceAsStream("/Deadpool/deadpool_down2.png"));
		left1=ImageIO.read(getClass().getResourceAsStream("/Deadpool/deadpool_left.png"));
		left2= ImageIO.read(getClass().getResourceAsStream("/Deadpool/deadpool_left2.png"));
		right1= ImageIO.read(getClass().getResourceAsStream("/Deadpool/deadpool_right.png"));
		right2= ImageIO.read(getClass().getResourceAsStream("/Deadpool/deadpool_right2.png"));
		stand= ImageIO.read(getClass().getResourceAsStream("/Deadpool/deadpool_stand.png"));
		dead= ImageIO.read(getClass().getResourceAsStream("/Deadpool/deadpool_dead.png"));
		attack1= ImageIO.read(getClass().getResourceAsStream("/Deadpool/deadpool_attack.png"));
		attack2= ImageIO.read(getClass().getResourceAsStream("/Deadpool/deadpool_attack2.png"));
		cast1 = ImageIO.read(getClass().getResourceAsStream("/Deadpool/selftarget.png"));
		cast2 = ImageIO.read(getClass().getResourceAsStream("/Deadpool/selftarget2.png"));
		cast3 = ImageIO.read(getClass().getResourceAsStream("/Deadpool/8 bullets.png"));
		cast4 = ImageIO.read(getClass().getResourceAsStream("/Deadpool/8 bullets2.png"));
		leader1 = ImageIO.read(getClass().getResourceAsStream("/Deadpool/leader1.png"));
		leader2 = ImageIO.read(getClass().getResourceAsStream("/Deadpool/leader2.png"));
		bomb1 = ImageIO.read(getClass().getResourceAsStream("/Ca/explosion1.png"));
		bomb2 =	ImageIO.read(getClass().getResourceAsStream("/Ca/explosion2.png"));
	}
	catch(IOException e) {
		e.printStackTrace();
	}
}
public void setProjectileImages(Projectile p) {
	try{p.up = ImageIO.read(getClass().getResourceAsStream("/Deadpool/p.png"));
		p.right = ImageIO.read(getClass().getResourceAsStream("/Deadpool/p.png"));
		p.left=ImageIO.read(getClass().getResourceAsStream("/Deadpool/p.png"));
		p.down =ImageIO.read(getClass().getResourceAsStream("/Deadpool/p.png"));
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
	else if(keyH.cast1==true) {
		direction = "selftarget";
	}
	else if (keyH.cast2 ==true) {
		direction ="8 bullets";
	}
	else if (keyH.leader ==true) {
		direction = "leader";
	}
	else direction ="stand";
	stop--;
	
	if(stop<=0) {
		if(keyH.leader==true)
			explode = true;
		keyH.upPressed= false;
		keyH.leftPressed= false;
		keyH.rightPressed= false;
		keyH.downPressed= false;
		keyH.attackpressed=false;
		keyH.cast1=false;
		keyH.cast2 = false;
		keyH.leader= false;
		actionNum = 1;
		
	}
	spriteCounter++;
	
	if(spriteCounter>20) {
		if(spriteNum==1)
			spriteNum=2;
		else if(spriteNum==2)
			spriteNum=1;
		spriteCounter =0;
		if(actionNum==1)
			actionNum=2;}
	
		
	
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
	case "8 bullets": 		if(actionNum==1)
		image = cast3;
	else if(actionNum==2)
		image=cast4; break;
	case "selftarget": 	if(actionNum==1)
		image = cast1;
	else if(actionNum==2)
		image=cast2; break;
	case "leader": if(actionNum==1)
		image = leader1;
	else if(actionNum==2)
		image=leader2; break;
	case "stand": image = stand;break;
	case "dead": if(deadCounter>60)
		image = null;
	else image = dead;}
	
	if(explode) {
		if(timer==0)
			gp.keyH.playSE(24);
		timer++;
		if(timer>180) {
			timer=0;
			explode=false;
		}
		else if(timer>40) {
			g2.drawImage(bomb2,tmp.x-gp.tileSize*8,tmp.y-gp.tileSize*15,gp.tileSize*20,gp.tileSize*20,null);
			
		}
		else {
			g2.drawImage(bomb1,tmp.x,tmp.y,gp.tileSize*4,gp.tileSize*4,null);
		}
		
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
	
	g2.drawImage(image, x, y, gp.tileSize*2, gp.tileSize*2,null);
}
@Override
public void ability1Sound(String a) {
	switch(a) {
	case "Try Harder": gp.keyH.playSE(5);break;
	case "8 bullets left":gp.keyH.playSE(4);break;
	case "Can't Catch Me":	gp.keyH.playSE(5);break;
	}
	
}

@Override
public void leaderAbilitySound() {
	gp.keyH.playSE(22);
	
}

public void animateAbility(int n) {
	if(n==0|| n==2) {
		stop = 80;
		keyH.cast1 =true;
		}
	if(n==1) {
		stop = 120;
		keyH.cast2= true;
	}
}
public void animateLeader() {
	stop = 120;
	Projectile p = new Projectile(gp);
	p.speed = 4;
	p.width= gp.tileSize;
	p.height = gp.tileSize ;		
	setProjectileImages(p);
	tmp = new Objects(gp,keyH,2,2,100);
	p.set(x, y, true,tmp, this);
	gp.projectiles.add(p);
	keyH.leader =true;
}
}