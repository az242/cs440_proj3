

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Set;

import javax.swing.Timer;

public class Display extends Applet implements MouseListener, ActionListener, MouseMotionListener {
	int[] screen;
	int[] mouse;
	boolean showMap, showStats, setScale;
	Map map;
	double zoom;
	int WIDTH, HEIGHT;
	Timer myTimer;

	public Display(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		setSize(width, height);
		reset();
		addMouseListener(this);
		addMouseMotionListener(this);
		showMap = false;
		showStats = false;
		setScale = false;
		Timer myTimer;
		myTimer = new Timer(30, this);
		myTimer.start();
	}

	public Display(int width, int height, Map map) {
		addMouseListener(this);
		addMouseMotionListener(this);
		WIDTH = width;
		HEIGHT = height;
		reset();
		this.map = map;
		setScale = false;
		showMap = true;
		myTimer = new Timer(30, this);
		myTimer.start();
	}

	public void updateSize(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		setSize(width, height);
	}

	public Map getCurrentMap() {
		return map;
	}

	public void setShowStats(boolean show) {
		showStats = show;
	}

	public double getZoom() {
		return zoom;
	}
	public Map getMap() {
		return map;
	}
	public void setZoom(double newZoom) {
		if(map==null)
			return;
		if (newZoom > zoom) {
			screen[0] = (int) (screen[0] + (map.getCellMap().length * (newZoom - zoom)) / 2.0);
			screen[1] = (int) (screen[1] + (map.getCellMap().length * (newZoom - zoom)) / 2.0);
		} else {
			screen[0] = (int) (screen[0] - (map.getCellMap().length * (zoom - newZoom)) / 2.0);
			screen[1] = (int) (screen[1] - (map.getCellMap().length * (zoom - newZoom)) / 2.0);
		}
		zoom = newZoom;
		if (map == null)
			return;
	}
	
	
	public void reset() {
		zoom = 5;
		mouse = new int[] { 250, 250 };
		screen = new int[] { 0, 0 };
	}

	public void setNewMap(Map map) {
		reset();
		this.map = map;
		showMap = true;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		if (showMap)
			drawMap(g);
		/*
		 * uneeded code if (showTabs) drawTabs(g);
		 */
		if (showStats) {
			drawStats(g);
		}
		/*
		 * if(showVisited) drawVisited(g); if(showFringe) drawFringe(g);
		 */
		g.setColor(Color.black);
		// g.drawString(mouse[0] +", " + mouse[1], mouse[0], mouse[1]);
	}

	private Image dbImage;
	private Graphics dbg;

	@Override
	public void update(Graphics g) {
		// code for double buffering. gets a image of the canvas and displays
		// it, then it is cleared and paint is called
		if (dbImage == null) {
			dbImage = createImage(WIDTH, HEIGHT);
			dbg = dbImage.getGraphics();
		}
		// clear screen in background
		dbg.setColor(getBackground());
		dbg.fillRect(0, 0, WIDTH, HEIGHT);

		// draw elements in background
		dbg.setColor(getForeground());
		paint(dbg);

		// draw image on the screen
		try {
			g.drawImage(dbImage, 0, 0, this);
		} catch (Exception ex) {
			System.out.println("error painting");
		}

	}

	public void drawStats(Graphics g) {
		int infoLine = 1;
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Cell: " + (int) (((mouse[0] + screen[0]) / zoom)) + ", " + (int) ((mouse[1] + screen[1]) / zoom),
				mouse[0], mouse[1]);

		if (map == null)
			return;

		if (map.getCell((int) ((mouse[0] + screen[0]) / zoom), (int) ((mouse[1] + screen[1]) / zoom)) != null) {
			String temp = "";
			switch (map.getCell((int) ((mouse[0] + screen[0]) / zoom), (int) ((mouse[1] + screen[1]) / zoom))
					.getType()) {
			case 'N':
				temp = "Normal";
				break;
			case 'B':
				temp = "Blocked";
				break;
			case 'H':
				temp = "Highway";
				break;
			case 'T':
				temp = "Hard Traverse";
				break;
			}
			g.drawString("type: " + temp, mouse[0], mouse[1] - (infoLine * 20));
			infoLine++;
		}
	}
	
	public void drawMap(Graphics g) {
		for (int x = 0; x < map.getCellMap().length; x++) {
			for (int y = 0; y < map.getCellMap()[x].length; y++) {
				Color temp = new Color(50, 50, 50);
				double factor = 1/(Math.abs(Math.log(map.getCellMap()[x][y].getProbability())/Math.log(10)));
				switch (map.getCellMap()[x][y].getType()) {
				case 'B':
					temp = Color.BLACK;
					break;
				default:
					temp = new Color(255,255-(int)(255*factor),255-(int)(255*factor));
					break;
				}
				g.setColor(temp);
				g.fillRect((int) Math.ceil(x * zoom - screen[0]), (int) Math.ceil(y * zoom - screen[1]),
						(int) Math.ceil(zoom), (int) Math.ceil(zoom));
				g.setColor(Color.BLACK);
				g.drawRect((int) Math.ceil(x * zoom - screen[0]), (int) Math.ceil(y * zoom - screen[1]),
						(int) Math.ceil(zoom), (int) Math.ceil(zoom));
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		screen[0] = screen[0] + (mouse[0] - arg0.getX());
		screen[1] = screen[1] + (mouse[1] - arg0.getY());
		mouse[0] = arg0.getX();
		mouse[1] = arg0.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mouse[0] = e.getX();
		mouse[1] = e.getY();

	}
}
