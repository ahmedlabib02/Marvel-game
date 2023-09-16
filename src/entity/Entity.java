package entity;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Controller;
import model.world.Damageable;

abstract public class Entity {
public int x,y;
public int speed;
public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2,attack1,attack2,dead,stand,cast1,cast2,cast3,leader;
public String direction;
public Controller keyH;
public int stop = 38;
public int deadCounter=0;
public  int remove =0;
public int removeHp ;
public int spriteCounter =0;
public int spriteNum =1;
public int maxHp;
public int currentHp;
public boolean attacked;
public ArrayList<Damageable> targets= new ArrayList<Damageable>();
Font hp_font = new Font("Arial",Font.PLAIN,12);
public boolean makeSound;

public abstract void update();
public abstract void draw(Graphics2D g2);
public abstract void ability1Sound(String a);
public abstract void leaderAbilitySound();
public void animateAbility(int n) {	
	stop= 40;
	keyH.attackpressed=true;
}
public void animateLeader() {
	stop= 40;
	keyH.attackpressed=true;
	}

}
