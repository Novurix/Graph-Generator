import java.awt.Rectangle;
import java.awt.Graphics;

public class GraphPoint extends Rectangle {

    Screen screen;
    Window window;

    public GraphPoint(int xCor, int yCor, Screen screen) {
        this.screen = screen;
        setBounds(xCor,yCor,2,2);
    }

    public void render(Graphics graphics) {
        window = screen.window;
        int newheight = window.getHeight()*10;
        graphics.fillRect(x, y, width, newheight);
    }
}