package gui;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class LevelManager extends PanelManager{

	public LevelManager() {
		super("Level Management");
		
		this.add(new LevelManagerButton(new ImageIcon("assets/button_icon/new-icon.png")));
		this.add(new LevelManagerButton(new ImageIcon("assets/button_icon/open-icon.png")));
		this.add(new LevelManagerButton(new ImageIcon("assets/button_icon/save-icon.png")));
		this.add(new LevelManagerButton(new ImageIcon("assets/button_icon/play-icon.png")));
	}
}
