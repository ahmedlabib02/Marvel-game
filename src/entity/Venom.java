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

public class Venom extends Entity{
Board gp ;
BufferedImage cast4,logo;
int timer;
int counter=1;
public Venom(Board b,Controller k,int xx,int yy,int maxHp) {
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
		up1= ImageIO.read(getClass().getResourceAsStream("/Venom/venom_up1.png"));
		up2= ImageIO.read(getClass().getResourceAsStream("/Venom/venom_up2.png"));
		down1= ImageIO.read(getClass().getResourceAsStream("/Venom/venom_down1.png"));
		down2= ImageIO.read(getClass().getResourceAsStream("/Venom/venom_down2.png"));
		left1=ImageIO.read(getClass().getResourceAsStream("/Venom/venom_left1.png"));
		left2= ImageIO.read(getClass().getResourceAsStream("/Venom/venom_left2.png"));
		right1= ImageIO.read(getClass().getResourceAsStream("/Venom/venom_right1.png"));
		right2= ImageIO.read(getClass().getResourceAsStream("/Venom/venom_right2.png"));
		dead= ImageIO.read(getClass().getResourceAsStream("/Venom/venom_dead.png"));
		stand= ImageIO.read(getClass().getResourceAsStream("/Venom/venom_stand.png"));
		attack1= ImageIO.read(getClass().getResourceAsStream("/Venom/venom_attack.png"));
		attack2= ImageIO.read(getClass().getResourceAsStream("/Venom/venom_attack2.png"));
		cast1 = ImageIO.read(getClass().getResourceAsStream("/Venom/hb1.png"));
		cast2 = ImageIO.read(getClass().getResourceAsStream("/Venom/hb2.png"));
		cast3 = ImageIO.read(getClass().getResourceAsStream("/Venom/we are venom.png"));
		cast4 = ImageIO.read(getClass().getResourceAsStream("/Venom/we are venom2.png"));
		logo =   ImageIO.read(getClass().getResourceAsStream("/Venom/venom logo.png"));
	}
	catch(IOException e) {
		e.printStackTrace();
	}
}
public void setProjectileImages(Projectile p) {
	try{p.up = ImageIO.read(getClass().getResourceAsStream("/Venom/p_up.png"));
		p.right = ImageIO.read(getClass().getResourceAsStream("/Venom/p_right.png"));
		p.left=ImageIO.read(getClass().getResourceAsStream("/Venom/p_left.png"));
		p.down =ImageIO.read(getClass().getResourceAsStream("/Venom/p_down.png"));
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
	else if(keyH.cast1 ==true) {
		direction= "headbite";
	}
	else if(keyH.cast2==true) {
		timer ++;
		direction= "we are venom";
		if(timer>100) {
			keyH.cast2=false;
			timer=0;}}
		else if(keyH.leader == true) {
			timer++;
			direction = "attack";
			if(timer>180) {
				keyH.leader=false;
				timer =0;
				gp.logo=null;
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
		keyH.cast1=false;
		
	}
	spriteCounter++;
	if(spriteCounter>20) {
		if(spriteNum==1)
			spriteNum=2;
		else if(spriteNum==2)
			spriteNum=1;
		spriteCounter =0;
			if(counter==1)
				counter=2;}
	
	
		
	
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
	case "headbite": if(spriteNum==1)
			image = cast1;
		else if(spriteNum==2)
			image=cast2; break;
	case "we are venom": if(counter==1)
		image = cast3;
	else if(counter==2)
		image=cast4; break;		
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
	g2.drawImage(image, x, y, gp.tileSize*2, gp.tileSize*2,null);
}
@Override
public void ability1Sound(String a) {
	switch(a) {
	case "Head Bite": 	gp.keyH.playSE(18);break;
	case "We are venom": 	gp.keyH.playSE(19);break;
	case "Symbiosis": 	gp.keyH.playSE(18);break;
	}
	
}

@Override
public void leaderAbilitySound() {
	gp.keyH.playSE(20);
	
}
public void animateAbility(int n) {
	if(n==0|| n==2) {
		stop =38;
		keyH.cast1 =true;	
	}
	if(n==1) {
		keyH.cast2=true;
	}
	
	}

public void animateLeader() {
	gp.logo =logo;
	keyH.leader=true;
	for(int i=0;i<targets.size();i++) {
		Projectile p = new Projectile(gp);
		p.height= gp.tileSize *3;
		p.width = gp.tileSize*3;
		p.speed = 4;
		setProjectileImages(p);
		if(targets.get(i) instanceof Cover) 
		p.set(x, y, true,((Cover)targets.get(i)).animation, this);
		if(targets.get(i) instanceof Champion) 
			p.set(x, y, true,((Champion)targets.get(i)).animation, this);
		gp.projectiles.add(p);}
}
}