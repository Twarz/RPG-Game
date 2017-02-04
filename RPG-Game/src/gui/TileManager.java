package gui;

import java.awt.GridLayout;

import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class TileManager extends PanelManager{

	public TileManager() {
		super("Tiles Management");
		this.setLayout(new GridLayout(1,1));
		JTabbedPane tabs = new JTabbedPane();
		tabs.add("Items", null);
		tabs.add("Particles", null);
		tabs.add("Monsters", null);
		this.add(tabs);
	}
	
	
}
