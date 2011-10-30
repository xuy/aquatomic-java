/**
 * This is a java version of the game "Aquatomic" migrated from MacOS
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
 * 	This is the Model in the MVC application structure
 *  The MVC approach is implemented using Java's Observer to Observable contract.  
 *  Since this is the Model it inherits from the Obeservable class 
 *  @version 0.0.1
 *  
 */
package aquatomic.model;

import java.util.Observable;
/**
 * 
 * @author Xu You
 *
 */
public class GameMap extends Observable {
	/**
	 * 
	 */
	public static final int BLANK = '.';

	/**
	 * 
	 */
	public static final int WALL = '#';
	
	/**
	 * 
	 */
	public static final int RIGHT = 'R';
	
	/**
	 * 
	 */
	public static final int LEFT = 'L';
	
	/**
	 * 
	 */
	public static final int UP = 'U';
	
	/**
	 * 
	 */
	public static final int DOWN = 'D';
	
	/**
	 * 
	 * @param horizontal
	 * @param vertical
	 * @param state
	 */
	public void setMap(int horizontal, int vertical, int state) {
		map[horizontal][vertical] = state;
	}
	
	/**
	 * 
	 * @param direction
	 */
	public void move(int direction) {
		this.move(this.nowx, this.nowy, direction);
	}
	
	/**
	 * 
	 * @return
	 */
	public int[][] getMap() {
		return map;
	}
	
	/**
	 * 
	 * @param fromHorizontal
	 * @param fromVertical
	 * @param direction
	 */
	public void move(int fromHorizontal, int fromVertical, int direction) {
		// Find the destination;
		this.clearhints();
		int horizontal = fromHorizontal, vertical=fromVertical;
		switch (direction) {
		case 'U': // UP
			while (map[horizontal-1][vertical]==GameMap.BLANK) horizontal--;
			break;
		case 'D': //DOWN
			while (map[horizontal+1][vertical]==GameMap.BLANK) horizontal++;
			break;
		case 'L': //LEFT
			while (map[horizontal][vertical-1]==GameMap.BLANK) vertical--;
			break;
		case 'R': //RIGHT
			while (map[horizontal][vertical+1]==GameMap.BLANK) vertical++;
			break;
		default:
			System.out.print("Shoudn't Be Here!");
		}
		this.nowx = horizontal;
		this.nowy = vertical;
	
		// animation
		map[horizontal][vertical]=map[fromHorizontal][fromVertical];
		map[fromHorizontal][fromVertical]= GameMap.BLANK;
		if (testSuccess()) return;
		if (hasHints(horizontal,vertical)) {showhints(horizontal,vertical); hintstatus=true;}
		// An atom has moved in the grid and the View must now be informed
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	/**
	 * 
	 *
	 */
	public void setFail() {
		success = false;
	}
	
	/** 
	 * @return
	 * 0: Has Hints;
	 * 1: Move
	 * 2: No hint
	 */
	public int process(int horizontal, int vertical)
	{
		int returnvalue =0;
		switch (map[horizontal][vertical]) {
		case GameMap.LEFT:
			move(horizontal,vertical+1, 'L');
			returnvalue = 1;
			break;
		case GameMap.RIGHT:
			move(horizontal,vertical-1, 'R');
			returnvalue = 1;
			break;
		case GameMap.DOWN:
			move(horizontal-1, vertical, 'D');
			returnvalue = 1;
			break;
		case GameMap.UP:
			move(horizontal+1, vertical, 'U');
			returnvalue = 1;
			break;
		default: 
			if (hasHints(horizontal, vertical)) 
			{
				clearhints(); 
				showhints(horizontal,vertical); 
				hintstatus = true;
				returnvalue = 0;
			}
			else {
				clearhints(); 
				hintstatus = false; 
				returnvalue= 2;
			}
		}
		this.setChanged();
		this.notifyObservers();
		return returnvalue;
	}
		
		
	/**
	 * 
	 * @param direction
	 * @return
	 */
	public int process(int direction) {
		for (int i=0; i<4; i++) {
			if (map[nowx+directions[i][0]][nowy+directions[i][1]]==direction) 
				{move(direction); this.setChanged();
				this.notifyObservers();
				return 1;}
		}
		return 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMolecular(int[][] goal) {
		this.goal = goal;
	}
	
	public int[][] getMolecular()
	{
		return this.goal;
	}

	private void showhints(int x, int y) {
		this.nowx = x;
		this.nowy = y;
		if (map[x+1][y]==GameMap.BLANK) map[x+1][y]=GameMap.DOWN;  
		if (map[x-1][y]==GameMap.BLANK) map[x-1][y]=GameMap.UP; 
		if (map[x][y-1]==GameMap.BLANK) map[x][y-1]=GameMap.LEFT;
		if (map[x][y+1]==GameMap.BLANK) map[x][y+1]=GameMap.RIGHT; 	
	}

	private void clearhints() {
		if (!hintstatus) return;
		if (map[nowx+1][nowy]==GameMap.DOWN) map[nowx+1][nowy]=GameMap.BLANK;
		if (map[nowx-1][nowy]==GameMap.UP) map[nowx-1][nowy]=GameMap.BLANK;
		if (map[nowx][nowy-1]==GameMap.LEFT) map[nowx][nowy-1]=GameMap.BLANK;
		if (map[nowx][nowy+1]==GameMap.RIGHT) map[nowx][nowy+1]=GameMap.BLANK;
		hintstatus = false;		
	}
	
	private boolean testSuccess() {
		// Fine the goal pattern in map;
		int x = goal.length;
		int y = goal[0].length;
		int innerx, innery;
		int i,j, temp,flag;
		for (i = 0; i<15-x; i++)
			for (j = 0; j<15-y ; j++)
			{
				flag = 0;
				for (innerx=0; innerx<x; innerx++)
					for (innery =0; innery< y; innery ++)
					{
						temp=(char)goal[innerx][innery];
						if (temp!=map[i+innerx][j+innery] && Character.isLetterOrDigit(temp)) 
							{
								flag =1;	
								break;
							}
					}
				if (flag==0) {success = true; return true;}
			}
		success = false;
		return false;
	}
	
	private boolean hasHints(int x, int y) {
		int temp = map[x][y];
		if (temp==GameMap.WALL || temp==GameMap.BLANK || (temp >'A' && temp<'Z')) return false;
		if (map[x+1][y]==GameMap.BLANK||map[x-1][y]==GameMap.BLANK
				||map[x][y-1]==GameMap.BLANK||map[x][y+1]==GameMap.BLANK) return true;
		else return false;
	}

	
	// This holds the atoms including the initial state
	private int map[][] = new int[15][15];
	
	// This is the structure of the final molecule
	private int goal[][];
	
	private int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
	private ResourceManager rm;
	private boolean success;
	private String name; 
	private int nowx =0 ;
	private int nowy =0 ;
	private boolean hintstatus = false;

	public ResourceManager getRm() {
		return rm;
	}

	public void setResourceManager(ResourceManager rm) {
		this.rm = rm;
	}
}