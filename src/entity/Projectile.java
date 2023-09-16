package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Board;
import model.world.Damageable;

public class Projectile {
Board gp;
int x;
int y;
String direction="";
Entity user;
int speed;
int width ;;
int height;
public boolean alive;
Entity target;
BufferedImage up, down, left ,right;
public Projectile(Board gp) {
	this.gp=gp;
	speed= 10;
	alive=false;
	height =gp.tileSize;
	width = gp.tileSize;
	
     
}
	public void set(int x,int y,boolean a,Entity t,Entity user) {
		this.x = x;
		this.y=y;
		this.alive=a;
		this.user = user;
		target = t;
	
	
	}
	public void update() {
		if(Math.abs(y-target.y)<10&& Math.abs(x-target.x)<9)
			alive= false;
		else { if(Math.abs(y-target.y)>6) {
		if(y<target.y) {
			y+=speed;
			direction = "down";}
		else if(y>target.y) {
			y-=speed;
			direction = "up";}}
		if(Math.abs(x-target.x)>6) {
		if (x<target.x) {
			x+=speed;
			direction = "right";}
		else if (x>target.x) {
			x-=speed;
			direction= "left";}
		}}
		
			
		}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch(direction) {
		case "up":  image= up;	break;
		case "down":  image= down;	break;
		case "right":image= right;break;
		case "left": image= left;break;
	}  
	 g2.drawImage(image, x, y, width, height,null);
}
}	
