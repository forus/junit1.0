
package test.ui;

import java.awt.*;

class TestStatus extends java.awt.Canvas {
    public boolean fError= false;
    public int fTotal= 0;
    public int fProgress= 0;
    public int fProgressX= 0;

public TestStatus() {
    super();
    setSize(20, 30);
}

private Color getStatusColor () {
    if (fError)
        return Color.red;
    return Color.green;
}

public void paint ( java.awt.Graphics g) {
    paintBackground(g);
    paintStatus(g);
    return;
}

public void paintBackground( java.awt.Graphics g) {
    g.setColor(SystemColor.control);
    g.fillRect(0, 0, getBounds().width, getBounds().height);
    g.setColor(Color.darkGray);
    g.drawLine(0, 0, getBounds().width-1, 0);
    g.drawLine(0, 0, 0, getBounds().height-1);
    g.setColor(Color.white);
    g.drawLine(getBounds().width-1, 0, getBounds().width-1, getBounds().height-1);
    g.drawLine(0, getBounds().height-1, getBounds().width-1, getBounds().height-1);
}

public void paintStatus ( java.awt.Graphics g) {
    g.setColor(getStatusColor());
    Rectangle r= new Rectangle(0, 0, fProgressX, getBounds().height);
    g.fillRect(1, 1, r.width-1, r.height-2);
}

private void paintStep ( int startX, int endX) {
    Graphics g= getGraphics();
    g.setColor(getStatusColor());
    int s= startX;
    g.fillRect(s, 1, endX-s, getBounds().height-2);
}

public void reset () {
    fProgressX= 1;
    fProgress= 0;
    fError= false;
    paint(getGraphics());
}

public int scale (int value) {
    if (fTotal > 0)
        return Math.max(1, value*(getBounds().width-1)/fTotal);
    return value; // hack
}

public void setBounds (int x, int y, int w, int h) {
    super.setBounds(x, y, w, h);
    fProgressX= scale(fProgress);
}

public void start ( int total) {
    fTotal= total;
    reset();
}

public void step ( boolean successful) {
    fProgress++;
    int x= fProgressX;

    fProgressX= scale(fProgress);

    if (!fError && !successful) {
            fError= true;
            x= 1;
    }
    paintStep(x, fProgressX);
}
}