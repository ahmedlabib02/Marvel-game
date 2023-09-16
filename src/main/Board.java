package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.UI;
import javax.swing.Timer;

import Tile.TileManager;
import entity.CaptainAmerica;
import entity.Deadpool;
import entity.DrStrange;
import entity.Electro;
import entity.Entity;
import entity.Ghostrider;
import entity.Hela;
import entity.Hulk;
import entity.Iceman;
import entity.Ironman;
import entity.Loki;
import entity.Objects;
import entity.Projectile;
import entity.QuickSilver;
import entity.Spiderman;
import entity.Thor;
import entity.Venom;
import entity.YellowJacket;
import model.world.Champion;
import model.world.Cover;
import model.world.Damageable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel implements  Runnable   {
	public final int originalTileSize = 16;
	 public final int scale = 3;
	 public final int tileSize = originalTileSize *scale; //
	 public final int maxScreenCol = 16;
	 public final int maxScreenRow = 20;
	 public final int screenHeight = tileSize * maxScreenRow -40 ;//920
	 public final int screenWidth = tileSize * maxScreenCol;//768

	 public BufferedImage logo;
	 Thread gameThread; 
	 Image BackGround = new ImageIcon("blackscreen.jfif").getImage();
	 Image BackGround2 = new ImageIcon("white.png").getImage();
	boolean choosingDirection, choosingTarget,stopGame , leader,getInfo ;
	int x=100 ;
	int y=200 ;
	int speed=4;
	int FPS = 60;
	UI ui = new UI(this);
	public Controller keyH;;
	public ArrayList<Entity> visuals ;	
	TileManager tiles;
	String winner;
	
	public ArrayList<Projectile> projectiles= new ArrayList<Projectile>();


	public Board(Controller keyH,ArrayList<Damageable> damageables) {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		//BackGround = new ImageIcon("arena.png").getImage();
		this.keyH = keyH;
		visuals = new ArrayList<Entity>();
		for(int i=0;i<damageables.size();i++) {
			Damageable x = damageables.get(i);
			
			if(x instanceof Champion) {
			switch(((Champion)x).getName()){
			case "Hulk": Hulk h= new Hulk(this,null, x.getLocation().y,x.getLocation().x,((Champion) x).getMaxHP());
			visuals.add(h);
			((Champion) x).animation = h;
			break;
			case "Spiderman": Spiderman s= new Spiderman(this,null, x.getLocation().y,x.getLocation().x,((Champion) x).getMaxHP());
			visuals.add(s);
			((Champion) x).animation = s;
			break;
			case "Ghost Rider": Ghostrider g= new Ghostrider(this,null, x.getLocation().y,x.getLocation().x,((Champion) x).getMaxHP());
			visuals.add(g);
			((Champion) x).animation = g;
			break;
			case "Ironman": Ironman ir= new Ironman(this,null, x.getLocation().y,x.getLocation().x,((Champion) x).getMaxHP());
			visuals.add(ir);
			((Champion) x).animation = ir;
			break;
			case "Deadpool":  Deadpool d= new Deadpool(this,null, x.getLocation().y,x.getLocation().x,((Champion) x).getMaxHP());
			visuals.add(d);
			((Champion) x).animation = d;
			break;
			case "Venom": Venom v= new Venom(this,null, x.getLocation().y,x.getLocation().x,((Champion) x).getMaxHP());
			visuals.add(v);
			((Champion) x).animation = v;
			break;
			case "Captain America": CaptainAmerica c= new CaptainAmerica(this,null, x.getLocation().y,x.getLocation().x,((Champion) x).getMaxHP());
			visuals.add(c);
			((Champion) x).animation = c;
			break;
			case "Hela": Hela hela= new Hela(this,null, x.getLocation().y,x.getLocation().x,((Champion) x).getMaxHP());
			visuals.add(hela);
			((Champion) x).animation = hela;
			break;
			case "Yellow Jacket": YellowJacket yj= new YellowJacket(this,null, x.getLocation().y,x.getLocation().x,((Champion) x).getMaxHP());
			visuals.add(yj);
			((Champion) x).animation = yj;break;
			case "Quicksilver": QuickSilver qs= new QuickSilver(this,null, x.getLocation().y,x.getLocation().x,((Champion) x).getMaxHP());
			visuals.add(qs);
			((Champion) x).animation = qs;break;
			case "Thor": Thor thor= new Thor(this,null, x.getLocation().y,x.getLocation().x,((Champion) x).getMaxHP());
			visuals.add(thor);
			((Champion) x).animation = thor;break;
			case "Loki": Loki lo= new Loki(this,null, x.getLocation().y,x.getLocation().x,((Champion) x).getMaxHP());
			visuals.add(lo);
			((Champion) x).animation = lo;break;
			case "Electro": Electro elc= new Electro(this,null, x.getLocation().y,x.getLocation().x,((Champion) x).getMaxHP());
			visuals.add(elc);
			((Champion) x).animation = elc;break;
			case "Iceman": Iceman ic= new Iceman(this,null, x.getLocation().y,x.getLocation().x,((Champion) x).getMaxHP());
			visuals.add(ic);
			((Champion) x).animation =ic;break;
			case "Dr Strange": DrStrange ds= new DrStrange(this,null, x.getLocation().y,x.getLocation().x,((Champion) x).getMaxHP());
			visuals.add(ds);
			((Champion) x).animation =ds;break;
			}}
			else if(x instanceof Cover) {
				 Objects cover= new Objects(this, this.keyH,x.getLocation().y,x.getLocation().x,((Cover)x).getCurrentHP());
					visuals.add(cover);
					((Cover) x).animation = cover;}

		}
		 tiles = new TileManager(this);
		
		
		
	}

	public  void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		
		}
	
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta =0;
		long  lastTime= System.nanoTime();
		long currentTime;
		
		while(gameThread!=null) {
			currentTime = System.nanoTime();
			delta+= (currentTime-lastTime)/drawInterval;
			lastTime = currentTime;
			
			if(delta>=1) {
				update();
			repaint();
			delta--;}
		}
		
		}
	public void update() {
		if(keyH.gameEnded) {
			winner = keyH.winner.getName();
			stopGame =true;
			gameThread = null;}
			for(int i=0; i<visuals.size();i++)
				visuals.get(i).update();
			
			for(int j=0;j<projectiles.size();j++) {
				if(projectiles.get(j)!=null) {
					if(projectiles.get(j).alive==true) {
						projectiles.get(j).update();}
					if(projectiles.get(j).alive==false) 
						projectiles.remove(j);	
					
				}
			}
			
		
	
	}
	
public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	if(logo!=null) {
		g2.drawImage(logo,0,0,768,960,null );
	}
	else if(choosingTarget!=true&& getInfo!=true) 
		tiles.draw(g2);
	else  if(choosingTarget==true)
		g2.drawImage(BackGround,0,0,null);
	else g2.drawImage(BackGround2,0,0,null);
	for(int i=0;i<visuals.size();i++)
		visuals.get(i).draw(g2);
	for(int i =0;i<projectiles.size();i++) {
		projectiles.get(i).draw(g2);
	}
	ui.draw(g2);
	g2.dispose();
	
	
	
	
	
}


}
