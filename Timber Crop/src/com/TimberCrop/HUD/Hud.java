// Contains a reference to the Player.
// Draws all relevant information at the
// bottom of the screen.

package com.TimberCrop.HUD;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.TimberCrop.Entity.Crop;
import com.TimberCrop.Entity.Player;
import com.TimberCrop.Main.GamePanel;
import com.TimberCrop.Manager.Content;

public class Hud {
	
	private int yoffset;
	
	private BufferedImage bar;
	private BufferedImage crop;
	private BufferedImage boat;
	private BufferedImage axe;
	
	private Player player;
	
	private int numCrops;
	
	private Font font;
	private Color textColor; 
	
	public Hud(Player p, ArrayList<Crop> d) {
		
		player = p;
		numCrops = d.size();
		yoffset = GamePanel.HEIGHT;
		
		bar = Content.BAR[0][0];
		crop = Content.CROP[0][0];
		boat = Content.ITEMS[0][0];
		axe = Content.ITEMS[0][1];
		
		font = new Font("Arial", Font.PLAIN, 10);
		textColor = new Color(8, 150, 32);
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw hud
		g.drawImage(bar, 0, yoffset, null);
		
		// draw crop bar
		g.setColor(textColor);
		g.fillRect(8, yoffset + 6, (int)(28.0 * player.numCrops() / numCrops), 4);
		
		// draw crop amount
		g.setColor(textColor);
		g.setFont(font);
		String s = player.numCrops() + "/" + numCrops;
		Content.drawString(g, s, 40, yoffset + 3);
		if(player.numCrops() >= 10) g.drawImage(crop, 80, yoffset, null);
		else g.drawImage(crop, 72, yoffset, null);
		
		// draw items
		if(player.hasBoat()) g.drawImage(boat, 100, yoffset, null);
		if(player.hasAxe()) g.drawImage(axe, 112, yoffset, null);
		
		// draw time
		int minutes = (int) (player.getTicks() / 1800);
		int seconds = (int) ((player.getTicks() / 30) % 60);
		if(minutes < 10) {
			if(seconds < 10) Content.drawString(g, "0" + minutes + ":0" + seconds, 85, 3);
			else Content.drawString(g, "0" + minutes + ":" + seconds, 85, 3);
		}
		else {
			if(seconds < 10) Content.drawString(g, minutes + ":0" + seconds, 85, 3);
			else Content.drawString(g, minutes + ":" + seconds, 85, 3);
		}
		
		
		
	}
	
}
