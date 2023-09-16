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

public class Ironman extends Entity{
Board gp ;
int timer;
int leaderNum = 1;
BufferedImage logo,cast4;
public Ironman(Board b,Controller k,int xx,int yy,int maxHp) {
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
		up1= ImageIO.read(getClass().getResourceAsStream("/Ironman/ironman_up.png"));
		up2= ImageIO.read(getClass().getResourceAsStream("/Ironman/ironman_up2.png"));
		down1= ImageIO.read(getClass().getResourceAsStream("/Ironman/ironman_down.png"));
		down2= ImageIO.read(getClass().getResourceAsStream("/Ironman/ironman_down2.png"));
		left1=ImageIO.read(getClass().getResourceAsStream("/Ironman/ironman_left.png"));
		left2= ImageIO.read(getClass().getResourceAsStream("/Ironman/ironman_left2.png"));
		right1= ImageIO.read(getClass().getResourceAsStream("/Ironman/Iron_right.png"));
		right2= ImageIO.read(getClass().getResourceAsStream("/Ironman/ironman_right2.png"));
		dead= ImageIO.read(getClass().getResourceAsStream("/Ironman/ironman_dead.png"));
		stand= ImageIO.read(getClass().getResourceAsStream("/Ironman/ironman_stand.png"));
		attack1= ImageIO.read(getClass().getResourceAsStream("/Ironman/ironman_attack.png"));
		attack2= ImageIO.read(getClass().getResourceAsStream("/Ironman/ironman_attack2.png"));
		cast1 = ImageIO.read(getClass().getResourceAsStream("/Ironman/cast1.png"));
		cast2 = ImageIO.read(getClass().getResourceAsStream("/Ironman/cast2.png"));
		logo = ImageIO.read(getClass().getResourceAsStream("/Ironman/leader.png"));
		cast3 = ImageIO.read(getClass().getResourceAsStream("/Ironman/leader1.png"));
		cast4 = ImageIO.read(getClass().getResourceAsStream("/Ironman/leader2.png"));
		
	}
	catch(IOException e) {
		e.printStackTrace();
	}
}
public void setProjectileImages(Projectile p) {
	try{
	    p.down= ImageIO.read(getClass().getResourceAsStream("/Ironman/p_up.png"));
		p.up= ImageIO.read(getClass().getResourceAsStream("/Ironman/p_up.png"));
		p.left= ImageIO.read(getClass().getResourceAsStream("/Ironman/p_right.png"));
		p.right= ImageIO.read(getClass().getResourceAsStream("/Ironman/p_right.png"));
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
	else if (keyH.cast1==true ||keyH.cast1==true) {
		direction = "cast";
	}
	else if (keyH.leader==true) {
		timer++;
		direction= "leader";
		if(timer>60) {
			gp.logo =null;
			keyH.leader=false;
			timer =0;
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
		keyH.cast1 = false;
		keyH.cast2 = false;
		keyH.cast3 = false;
	
		
	}
	spriteCounter++;
	if(spriteCounter>20) {
		if(spriteNum==1)
			spriteNum=2;
		else if(spriteNum==2)
			spriteNum=1;
		spriteCounter =0;
		if(leaderNum==1)
			leaderNum=2;}
	
		
	
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
	case "cast": if(spriteNum==1)
		image = cast1;
	else if(spriteNum==2)
		image=cast2; break;
	case "leader": if(leaderNum==1)
		image = cast3;
	else if(leaderNum==2)
		image=cast4; break;	
	case "stand": image = stand;	break;
	case "dead": if(deadCounter>60)image =null;
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
	
	
	g2.drawImage(image, x, y, gp.tileSize*2, gp.tileSize*2,null);
}
@Override
public void ability1Sound(String a) {
	switch(a) {
	case "I am Ironman": gp.keyH.playSE(11);break;
	case "Unibeam":gp.keyH.playSE(12);break;
	case "3000":	gp.keyH.playSE(12);break;
	}
	
}

@Override
public void leaderAbilitySound() {
	gp.keyH.playSE(12);
	
}
public void animateAbility(int n) {
	if(n==0|| n==1) {
		this.stop=35;
		keyH.cast1=true;
		for(int i=0;i<targets.size();i++) {
			Projectile p = new Projectile(gp);
			setProjectileImages(p);
			if(targets.get(i) instanceof Cover) 
			p.set(x, y, true,((Cover)targets.get(i)).animation, this);
			if(targets.get(i) instanceof Champion) 
				p.set(x, y, true,((Champion)targets.get(i)).animation, this);
			gp.projectiles.add(p);}}
		if(n==2){
			this.stop=35;
			keyH.attackpressed=true;
		}
	
		
	
}
	public void animateLeader() {
		gp.logo = logo;
		keyH.leader=true; 

		
	}

}