import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.GridLayout;

import java.awt.event.*;
import java.util.Random;

public class Launcher {
    public static void main(String[] args) {
        new Window(2000,1500,"Population Graph");
    }
}

class Window extends JFrame {

    public Window(int width, int height, String title) {

        setSize(width, height);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setGridLayout();
    }

    void setGridLayout() {
        setLayout(new GridLayout(1,1,0,0));
        add(new Screen(this));
        setVisible(true);
    }
}

class Screen extends JPanel implements ActionListener{

    int bottom = 100;
    int restarttimer = 0, maxTimer = 0;

    int populationTimer = 0, populationMaxTimer = 1;

    GraphPoint[] points = new GraphPoint[10000];
    Background background;

    Window window;
    Timer timer = new Timer(10,this);

    int graphPopulation = 700, xCor, yCor;

    public Screen(Window window) {
        setOpaque(false);
        this.window = window;
        background = new Background(this);
        setFocusable(true);
        timer.start();

        yCor = graphPopulation-bottom;
        xCor = 0;

        points[0] = new GraphPoint(xCor, yCor, this);
    }

    public void paint(Graphics graphics) {
        Graphics backgroundGraphics = graphics;
        backgroundGraphics.setColor(Color.black);
        background.render(backgroundGraphics);

        Graphics pointGraphics = graphics;
        pointGraphics.setColor(Color.white);

        for (int i = 0; i < points.length; i++) {
            if (points[i] != null) {
                points[i].render(pointGraphics);
            }
        }

        Graphics text = graphics;
        text.setColor(Color.red);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        restarttimer++;

        System.out.println(graphPopulation);

        if (restarttimer > 1000) {
            restarttimer = 0;
            xCor = 0;
            for (int i = 0; i < points.length; i++) {
                points[i] = null;
            }

            if (graphPopulation > 1000) {
                graphPopulation = 700;
            }

            else if (graphPopulation < 200) {
                graphPopulation = 700;
            }
        }
        repaint();
        
        populationTimer++;

        if (populationTimer >= populationMaxTimer) {
            populationTimer = 0;
            for (int i = 0; i < points.length; i++) {
                if (points[i] == null) {
                    Random randomOperation = new Random();
                    int randomOp = randomOperation.nextInt(3);

                    Random randomDrop = new Random();
                    int newRandomDrop = randomDrop.nextInt(20);

                    Random randomAmount = new Random();
                    int random_Drop = randomAmount.nextInt(newRandomDrop+1);

                    System.out.println(randomOp-1);

                    int drop = random_Drop*(randomOp-1);

                    graphPopulation+=drop;

                    yCor = graphPopulation-bottom;
                    xCor+=2;
                    
                    points[i] = new GraphPoint(xCor, yCor, this);
                    break;
                }
            }
        }
    }
}

class Background extends Rectangle {

    Screen screen;
    Window window;

    public Background(Screen screen) {
        this.screen = screen;
        window = screen.window;

        int width = window.getWidth();
        int height = window.getHeight();

        setBounds(0,0,width,height);
    }

    public void render(Graphics graphics) {
        graphics.fillRect(x, y, window.getWidth(), window.getHeight());
    }
}