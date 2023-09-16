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

	public class DrStrange extends Entity{
	Board gp ;
	BufferedImage logo;
	public DrStrange(Board b,Controller k,int xx,int yy,int maxHp) {
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
	public void setProjectileImages(Projectile p) {
		try{if(direction.equals("attack")) {
			p.up = ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_bc2.png"));
			p.right = ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_bc2.png"));
			p.left=ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_bc2.png"));
			p.down =ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_bc2.png"));
		}
		else {	p.up = ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_bc.png"));
			p.right = ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_bc.png"));
			p.left=ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_bc.png"));
			p.down =ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_bc.png"));}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void getHulkImage() {
		try {
			up1= ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_up.png"));
			up2= ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_up2.png"));
			down1= ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_down.png"));
			down2= ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_down2.png"));
			left1=ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_up.png"));
			left2= ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_up.png"));
			right1=ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_up2.png"));
			right2= ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_up2.png"));
			dead= ImageIO.read(getClass().getResourceAsStream("/Hela/hela_dead.jfif"));
			stand= ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_up.png"));
			attack1= ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_attack.png"));
			attack2= ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_cast1.png"));
			cast1 = ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/ds_cast2.png"));
			logo =ImageIO.read(getClass().getResourceAsStream("/Dr_Strange/logo.png"));
			
			
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
				if(stop==30) {
					
					for(int i=0;i<targets.size();i++) {
						Projectile p = new Projectile(gp);
						setProjectileImages(p);
						if(targets.get(i) instanceof Cover) 
						p.set(x, y, true,((Cover)targets.get(i)).animation, this);
						if(targets.get(i) instanceof Champion) 
							p.set(x, y, true,((Champion)targets.get(i)).animation, this);
						gp.projectiles.add(p);}
				}
				
			}
			else if(keyH.cast1==true) {
				direction = "eye of agamotto";
			if(stop==30)
				for(int i=0;i<targets.size();i++) {
					Projectile p = new Projectile(gp);
					setProjectileImages(p);
					if(targets.get(i) instanceof Cover) 
					p.set(x, y, true,((Cover)targets.get(i)).animation, this);
					if(targets.get(i) instanceof Champion) 
						p.set(x, y, true,((Champion)targets.get(i)).animation, this);
					gp.projectiles.add(p);}

			}
			else if(keyH.leader==true) {
			    if(stop==30)
				for(int i=0;i<targets.size();i++) {
					Projectile p = new Projectile(gp);
					setProjectileImages(p);
					if(targets.get(i) instanceof Cover) 
					p.set(x, y, true,((Cover)targets.get(i)).animation, this);
					if(targets.get(i) instanceof Champion) 
						p.set(x, y, true,((Champion)targets.get(i)).animation, this);
					gp.projectiles.add(p);}
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
				keyH.leader=false;
				gp.logo=null;
				spriteNum=1;
				
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
			case "eye of agamotto":	if(spriteNum==1)
				image = cast1;
			else if(spriteNum==2)
				image=attack2; break;
			case "stand": image = stand;	break;
			case "dead": if(deadCounter>60)image =null;
			else 	image = dead;}
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
		public void animateAbility(int n) {
			if(n==1||n==2) {
				stop=40;
				keyH.attackpressed = true;
			}
			if(n==0) {
				stop = 40;
				keyH.cast1=true;
			}
		}
		public void animateLeader() {
			stop = 60;
		    gp.logo = logo;
			keyH.leader = true;
		}
	}

