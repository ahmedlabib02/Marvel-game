package Tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Board;

public class TileManager {
Board b;
Tile[] tile;
int mapTileManager[][];
public TileManager(Board b) {
	this.b=b;
	tile = new Tile[10];
	mapTileManager = new int[b.maxScreenCol][b.maxScreenRow];
	getTileImage();
	loadMap();
	
}
public void getTileImage() {
	try {
		tile[0]= new Tile();
		tile[0].image=ImageIO.read(getClass().getResourceAsStream("/tiles/floor.png"));
		tile[1]= new Tile();
		tile[1].image= ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
		tile[2]= new Tile();
		tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
	}
	catch(IOException e) {
		e.printStackTrace();
	}
	
}
public void loadMap() {
	try {
		InputStream is =getClass().getResourceAsStream("/tiles/map.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		int col =0;
		int row =0;
		while(col<b.maxScreenCol&& row<b.maxScreenRow) {
			String line = br.readLine();
			while(col<b.maxScreenCol) {
				String numbers[] = line.split(" ");
				int num = Integer.parseInt(numbers[col]);
				mapTileManager[col][row]=num;
				col++;
			}
			if(col==b.maxScreenCol) {
				col=0;	
				row++;
			}
			
			
		}
		br.close();
	}catch(Exception e) {
		
	}
}
public void draw(Graphics2D g2) {
	int col=0;
	int row = 0;
	int x =0;
	int y=0;
	while(col<b.maxScreenCol&& row<b.maxScreenRow) {
		int tileNum =	mapTileManager[col][row];
		g2.drawImage(tile[tileNum].image,x,y,b.tileSize,b.tileSize,null);
		col++;
		x+= b.tileSize;
		if(col== b.maxScreenCol) {
			col=0;
			x=0;
			row++;
			y+=b.tileSize;
		}
	}
}
}
