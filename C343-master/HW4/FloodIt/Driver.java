package FloodIt;

import javax.swing.*;
import javax.swing.Timer;
import java.util.*;

public class Driver {
    public int size = 14;
    public int numOfCoords;
    public int[][] colorOfCoord;
    public int currentStep;
    public int maxStep;

    public FloodFunction floodFunction;

    private boolean enableGUIP;
    private GUIFrame guiFrame;

    private static Random rand = new Random();

    public static void main(final String... args) {
        SwingUtilities.invokeLater(() -> new Driver(true));
    }

    public Driver(final boolean _enableGUIP) {
        try {
            //for the weird mac
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

            this.enableGUIP = _enableGUIP;
            this.initData();
            this.floodFunction = new FloodFunction(this);
            if(this.enableGUIP) {
                this.guiFrame =new GUIFrame(this);
            }
        }catch (Exception e) {
            e.printStackTrace(System.out);
            System.exit(0);
        }
    }

    public void flood(final int colorIndex) {
        this.floodFunction.flood(colorIndex);
        System.out.println(this.floodFunction.floodList.size());
        for(final Coord coord:this.floodFunction.floodList) {
            this.colorOfCoord[coord.y][coord.x] = colorIndex;
            if(this.enableGUIP) {
                this.guiFrame.setColor(coord, colorIndex);
            }
        }
        this.currentStep += 1;
        this.guiFrame.updateStep();
        if(this.currentStep == this.maxStep) {
            this.lose();
        }else if(this.floodFunction.floodList.size() >= this.size * this.size) {
            System.out.println("here");
            this.win();
        }
    }

    public int getColor(final Coord coord) {
        return this.colorOfCoord[coord.y][coord.x];
    }

    public void resize(final int _size) {
        this.size = _size;
        this.restart();
    }

    public void win() {
        JOptionPane.showMessageDialog(guiFrame, "You took " + Integer.toString(this.currentStep) + " steps.");
        this.guiFrame.dispose();
        System.exit(0);
    }
    public void lose() {
        JOptionPane.showMessageDialog(guiFrame, "You lose.");
        this.guiFrame.dispose();
        System.exit(0);
    }

    public void autoPlay() {
        javax.swing.Timer timer = new Timer(Constants.TIMER_WAIT, e -> this.flood(Driver.randInt()));
        timer.start();
    }

    public void restart() {
        if(this.enableGUIP)  {
            this.guiFrame.dispose();
        }
        this.initData();
        this.floodFunction = new FloodFunction(this);
        if(this.enableGUIP) {
            this.guiFrame =new GUIFrame(this);
        }
    }

    private void initData() {
        this.currentStep = 0;
        this.maxStep = Driver.genStepLimit(this.size);
        this.numOfCoords = this.size * this.size;
        this.genCoords();
    }

    private void genCoords() {
        this.colorOfCoord = new int[this.size][this.size];
        for(int x=0; x<this.size; x+=1) {
            for (int y = 0; y < this.size; y += 1) {
                this.colorOfCoord[x][y] = Driver.randInt();
            }
        }
    }

    private static int genStepLimit(final int size) {
        return size * 25 / 1;
    }

    private static int randInt() {
        return Driver.rand.nextInt(6);
    }
}
