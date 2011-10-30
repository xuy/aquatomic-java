/**
 * This is a java version of the game "Aquatomic" migrated from MacOS
 * Copyright (C) 2011  
 * Author: Eric Xu 
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
 * This is the main class of the application
 * version 0.0.1
 * Make the game work
 * 
 * version 0.1 (stable and mailstone)
 * Change to the MVC structure
 * Make the GameMap be observable and add GameMapUI as an obsrverable.
 * 
 * @version 0.2beta (under developing)
 * Use a partial rendering methodology to make animation
 * 
 */

package aquatomic;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.Locale;

import aquatomic.controller.AquaKeyboardListener;
import aquatomic.controller.AquaMouseListener;
import aquatomic.model.GameMap;
import aquatomic.model.ResourceManager;
import aquatomic.view.GameMapUI;

public class Aquatomic extends JFrame {

	public void init() {
		this.setTitle("Aquatomic -- " + gm.getName());
		BufferedImage background = rm.getEmptyMap();
		map.setIcon(new ImageIcon(map.getImageMap(background)));
		map.setText("");
		goal = rm.getGoal();

		goalLab.setIcon(new ImageIcon(goal.getScaledInstance(
				goal.getWidth() * 3 / 5, goal.getHeight() * 3 / 5,
				Image.SCALE_SMOOTH)));
		goalLab.setVerticalTextPosition(JLabel.TOP);
		goalLab.setHorizontalAlignment(JLabel.CENTER);
		goalLab.setText("<HTML><H2>GOAL</H2></HTML>");

		step = 0;
		steps.setText(rm.getLocalizedMsg(Locale.CHINESE) + 0);
		setTitle("Aquatomic -- " + gm.getName());

	}

	public Aquatomic() {
		this.addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowOpened(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowActivated(WindowEvent e) {
			}

			public void windowDeactivated(WindowEvent e) {
			}
		});

		gm = rm.getGameMap("1");
		map = new GameMapUI(gm);

		gameMouseCtrl = new AquaMouseListener();
		gameMouseCtrl.setAquamatic(this);
		gameKeyCtrl = new AquaKeyboardListener();
		gameKeyCtrl.setAquamatic(this);

		map.addMouseListener(gameMouseCtrl);
		map.addKeyListener(gameKeyCtrl);
		map.setBounds(20, 20, 450, 450);
		map.setOpaque(true);
		map.setBackground(new Color(243, 243, 243));
		map.setForeground(new Color(180, 180, 180));
		add(map);
		map.setFocusable(true);

		goalLab = new JLabel();
		goalLab.setOpaque(true);
		goalLab.setBackground(new Color(243, 243, 243));
		goalLab.setBounds(480, 20, 200, 200);
		// goalLab.setHorizontalAlignment(JLabel.TOP);
		add(goalLab);

		String[] level = new String[82];
		for (int i = 0; i < 82; i++)
			level[i] = "" + (i + 1);
		levellist = new JComboBox(level);
		levellist.addActionListener(new LevellistAdapter());
		levellist.setFocusable(false);
		levellist.setBounds(485, 235, 195, 30);
		add(levellist);

		steps = new JLabel();
		steps.setBounds(485, 275, 100, 20);
		this.add(steps);

	}

	/**
	 * 
	 *
	 */
	public void increaseStep() {
		step++;
		steps.setText(rm.getLocalizedMsg(Locale.US) + step);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Aquatomic jf = new Aquatomic();
		jf.init();
		jf.setLayout(null);
		jf.setSize(720, 540);
		jf.setResizable(false);
		jf.setVisible(true);
	}

	public class LevellistAdapter implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JComboBox)
				level = (String) ((JComboBox) e.getSource()).getSelectedItem();
			gm = rm.getGameMap(level);
			init();
		}
	}

	private static final long serialVersionUID = 1L;
	private ResourceManager rm = ResourceManager.getInstance();

	private JComboBox levellist;
	private AquaMouseListener gameMouseCtrl;
	private AquaKeyboardListener gameKeyCtrl;
	private JLabel goalLab;
	private BufferedImage goal;
	private int step = 0;
	private JLabel steps;

	private String level = "1";
	private GameMap gm;
	private GameMapUI map;
}