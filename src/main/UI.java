package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
Board gp;
Font arial_40;
Font arial_50;
Graphics2D g2;
public boolean messageOn;
public String message="";
int messageCounter =0;
public int slotCol = 0;
public int slotRow = 0;

public UI(Board gp){
	this.gp=gp;
	arial_40 = new Font("Arial",Font.PLAIN,30);
	 arial_50= new Font("Arial",Font.BOLD,60);
}
	public void showMessage(String Text) {
		message= Text;
		messageOn= true;
		
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		if(gp.stopGame==true) {
			g2.setFont(arial_50);
			g2.setColor(Color.white);
			message = gp.winner+" wins.";
			int textlength = (int)g2.getFontMetrics().getStringBounds(message, g2).getWidth();
			g2.drawString(message,gp.screenWidth/2-textlength/2,(gp.screenHeight/2)-30);
			g2.setColor(Color.yellow);
			message = "Congratulations!";
			textlength = (int)g2.getFontMetrics().getStringBounds(message, g2).getWidth();
			g2.drawString(message,gp.screenWidth/2-textlength/2,gp.screenHeight/2+30);
		}
			
		if(gp.choosingDirection) {
			g2.setFont(arial_40);
			g2.setColor(Color.black);
			int textlength = (int)g2.getFontMetrics().getStringBounds(message, g2).getWidth();
			g2.drawString(message,gp.screenWidth/2-textlength/2,gp.screenHeight/2);
		}else if(gp.choosingTarget){
			drawCursor();
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			int textlength = (int)g2.getFontMetrics().getStringBounds(message, g2).getWidth();
			g2.drawString(message,gp.screenWidth/2-textlength/2,gp.screenHeight/2);
		}
		else if(gp.getInfo==true) {
			message = "get information of?";
			drawCursor();
			g2.setFont(arial_40);
			g2.setColor(new Color(115,0,150));
			int textlength = (int)g2.getFontMetrics().getStringBounds(message, g2).getWidth();
			g2.drawString(message,gp.screenWidth/2-textlength/2,gp.screenHeight/2);
		}
		else {
		g2.setFont(arial_40);
		g2.setColor(Color.red);
		if(messageOn==true) {
			int textlength = (int)g2.getFontMetrics().getStringBounds(message, g2).getWidth();
			g2.drawString(message,gp.screenWidth/2-textlength/2,gp.screenHeight/2);
			messageCounter++;
			if(messageCounter>60){
				messageCounter=0;
				messageOn=false;}}
			
		}
	}
	public void drawCursor() {
		int cursorX =  (gp.screenWidth/5)*slotCol;
		int cursorY =   (gp.screenHeight/5)*slotRow;
		int cursorWidth = (gp.tileSize *2)+20;
		int cursorHeight = (gp.tileSize*2)+20;
		if(gp.choosingTarget)
		g2.setColor(Color.white);
		else g2.setColor(Color.darkGray);
		g2.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,10,10);
	
		
		
	}
}
