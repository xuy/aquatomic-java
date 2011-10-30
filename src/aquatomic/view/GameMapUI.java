/** 
 * This is a java version of the game "Aquamatics" migrated from MacOS
 * Copyright (C) 2006  You XU 
 * Mathematics Department, Nanjing University
 * 03/30/2006
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *	
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 * This is the View(MVC) part of the application
 * @author You XU
 * @version 0.1
 */

package aquatomic.view;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import aquatomic.model.GameMap;

public class GameMapUI extends JLabel implements Observer {

	/**
	 * This is the constructor of a View and takes a Model as it argument
	 * 
	 * @param map
	 */

	public GameMapUI(GameMap map) {
		this.map = map;
		// create the link to the Model
		map.addObserver(this);

	}

	/**
	 * This method is called when the Model changes its data. Hence it should
	 * repaint the screen of this View.
	 */
	public void update(Observable o, Object arg) {
		BufferedImage background = map.getRm().getEmptyMap();
		this.setIcon(new ImageIcon(this.getImageMap(background)));
		if (map.isSuccess()) // GameSuccess Like Google Style
		{
			this.setHorizontalTextPosition(JLabel.CENTER);
			this.setText("<HTML><B><H1><I><FONT COLOR='BLUE'>S</FONT>"
					+ "<FONT COLOR='RED'>U</FONT><FONT COLOR='YELLOW'>CC</FONT>"
					+ "<FONT COLOR='BLUE'>E</FONT><FONT COLOR='GREEN'>S</FONT>"
					+ "<FONT COLOR='RED'>S</FONT></I></H1></B></HTML>");
		}
	}

	/**
	 * 
	 * @param bi
	 * @return
	 */
	public BufferedImage getImageMap(BufferedImage bi) {
		Graphics2D g = bi.createGraphics();
		Canvas c = new Canvas();
		int[][] intMap = map.getMap();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				g.drawImage(map.getRm().getAtom(intMap[i][j]), j * 30, i * 30,
						c);
			}
		}
		return bi;
	}

	public BufferedImage getMolecular() {
		return molecular;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GameMap map;
	private BufferedImage molecular;
}
