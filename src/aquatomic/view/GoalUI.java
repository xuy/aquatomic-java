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
 * This is the View, implemented the Obsrvable interface.
 * 
 */

package aquatomic.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class GoalUI extends JLabel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
		
	public void update(Observable o, Object arg) {
		
	}
}
