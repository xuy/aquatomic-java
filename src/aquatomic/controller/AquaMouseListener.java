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
 * @version 0.0.1
 * This is the Controller.
 */

package aquatomic.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import aquatomic.Aquatomic;
import aquatomic.model.GameMap;
import aquatomic.model.ResourceManager;


public class AquaMouseListener implements MouseListener {
	private GameMap gameMap = ResourceManager.getInstance().getGameMap();
	private Aquatomic aquatomic;
	
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		
		if (gameMap.isSuccess()) return;

		Point p = arg0.getPoint();
		int y = p.x/30;
		int x = p.y/30;
		int state = gameMap.process(x,y);
		if (state==1) aquatomic.increaseStep(); 
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setAquamatic (Aquatomic a)
	{
		this.aquatomic = a;
	}

}

