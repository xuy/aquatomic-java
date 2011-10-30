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
 * 
 */

package aquatomic.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import aquatomic.Aquatomic;
import aquatomic.model.GameMap;
import aquatomic.model.ResourceManager;

public class AquaKeyboardListener implements KeyListener {

	public void setAquamatic(Aquatomic aqua) {
		this.aquatomic = aqua;
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {
		if (gm.isSuccess())
			return;
		int key = e.getKeyCode();
		int state = 0;
		switch (key) {
		case KeyEvent.VK_UP:
			state = gm.process(GameMap.UP);
			break;
		case KeyEvent.VK_DOWN:
			state = gm.process(GameMap.DOWN);
			break;
		case KeyEvent.VK_RIGHT:
			state = gm.process(GameMap.RIGHT);
			break;
		case KeyEvent.VK_LEFT:
			state = gm.process(GameMap.LEFT);
			break;
		default:
			// do nothing
		}
		if (state == 1)
			aquatomic.increaseStep();
	}

	private GameMap gm = ResourceManager.getInstance().getGameMap();
	private Aquatomic aquatomic;
}
