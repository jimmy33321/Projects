package FloodIt;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class GUIFrame extends JFrame{
    public final Driver driver;
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JMenu currentSteps = new JMenu();
    private Map<Coord, Tile> coordTileMap;

    public GUIFrame(final Driver _driver) {
        this.driver = _driver;
        this.mainPanel = new JPanel(new GridLayout(this.driver.size, this.driver.size));
        this.coordTileMap = new HashMap<>();
        this.updateStep();
        this.initMenuBar();
        this.initTiles();
        this.setContentPane(mainPanel);
        this.setSize(640, 640);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateStep() {
        this.currentSteps.setText(Integer.toString(this.driver.currentStep) +
        "/" + Integer.toString(this.driver.maxStep));
    }
    public void setColor(final Coord coord, final int colorIndex) {
        this.coordTileMap.get(coord).setColor(colorIndex);
    }
    private void initMenuBar() {
        this.menuBar = new JMenuBar();
        final JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        this.menuBar.add(gameMenu);

        final JMenuItem restart = new JMenuItem("restart");
        restart.addActionListener(e -> this.driver.restart());
        restart.addActionListener(e -> this.driver.restart());
        gameMenu.add(restart);
        final JMenuItem auto = new JMenuItem("auto-play");
        auto.addActionListener(e -> this.driver.autoPlay());
        gameMenu.add(auto);
        final JMenuItem exit = new JMenuItem("exit");
        restart.addActionListener(e -> this.dispose());
        gameMenu.add(exit);

        this.menuBar.add(currentSteps);

        this.setJMenuBar(this.menuBar);
    }

    private void initTiles() {
        final int[][] colorOfCoord = this.driver.colorOfCoord;
        for(int y=0; y<colorOfCoord.length; y+=1) {
            for(int x=0; x<colorOfCoord[y].length; x+=1) {
                final Tile tile = new Tile(colorOfCoord[y][x], this);
                this.mainPanel.add(tile);
                this.coordTileMap.put(new Coord(x, y), tile);
            }
        }
    }
}

class Tile extends JButton {
    public int colorIndex;
    public GUIFrame guiFrame;

    public Tile(final int _colorIndex,  final GUIFrame _guiFrame) {
        this.guiFrame = _guiFrame;
        this.setColor(_colorIndex);

        final TileActionListener listener = new TileActionListener(this);
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);

        //for weird mac
        this.setOpaque(true);
    }

    public void setColor(final int _colorIndex) {
        this.colorIndex = _colorIndex;
        this.setBackground(Tile.genColor(_colorIndex));
    }

    private static Color genColor(final int colorIndex) {
        switch (colorIndex) {
            case Constants.BLUE:
                return Color.BLUE;
            case Constants.RED:
                return Color.RED;
            case Constants.CYAN:
                return Color.CYAN;
            case Constants.PINK:
                return Color.PINK;
            case Constants.GREEN:
                return Color.GREEN;
            default:
                return Color.YELLOW;
        }
    }
}
class TileActionListener extends MouseInputAdapter {
    private final Tile tile;

    public TileActionListener(final Tile _tile) {
        this.tile = _tile;
    }

    public void mouseClicked(final MouseEvent e) {
        this.tile.guiFrame.driver.flood(this.tile.colorIndex);
    }
}
