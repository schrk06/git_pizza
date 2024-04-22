package fr.creator_craft.RaPizza;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JComponent;

public class Program {
	
	public static void main(String[] args) {
		new Application();
	}

}

class MyTabbedPaneUI extends javax.swing.plaf.basic.BasicTabbedPaneUI {

//    @Override
//    protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) {
//        Color savedColor = g.getColor();
//        g.setColor(Color.PINK);
//        g.fillRect(rects[tabIndex].x, rects[tabIndex].y, 
//               rects[tabIndex].width, rects[tabIndex].height);
//        g.setColor(Color.BLUE);
//        g.drawRect(rects[tabIndex].x, rects[tabIndex].y, 
//               rects[tabIndex].width, rects[tabIndex].height);
//        g.setColor(savedColor);
//    }
    @Override
    protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
    	super.paintTabArea(g, tabPlacement, selectedIndex);
    }
    @Override
    	public void paint(Graphics g, JComponent c) {
    	int tabPlacement = tabPane.getTabPlacement();

        Insets insets = c.getInsets(); Dimension size = c.getSize();

        // Paint the background for the tab area
        if ( !tabPane.isOpaque() ) {
            Color background = c.getBackground();
                g.setColor(background);
            switch ( tabPlacement ) {
            case LEFT:
            	System.out.println("NOP 1");
                g.fillRect( insets.left, insets.top,
                            calculateTabAreaWidth( tabPlacement, runCount, maxTabWidth ),
                            size.height - insets.bottom - insets.top );
                break;
            case BOTTOM:
            	System.out.println("NOP 2");
                int totalTabHeight = calculateTabAreaHeight( tabPlacement, runCount, maxTabHeight );
                g.fillRect( insets.left, size.height - insets.bottom - totalTabHeight,
                            size.width - insets.left - insets.right,
                            totalTabHeight );
                break;
            case RIGHT:
            	System.out.println("NOP 3");
                int totalTabWidth = calculateTabAreaWidth( tabPlacement, runCount, maxTabWidth );
                g.fillRect( size.width - insets.right - totalTabWidth,
                            insets.top, totalTabWidth,
                            size.height - insets.top - insets.bottom );
                break;
            case TOP:
            default:
            	System.out.println("NOP");
                g.fillRect( insets.left, insets.top,
                            size.width - insets.right - insets.left,
                            calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight) );
//                paintHighlightBelowTab();
            }
        }

        super.paint( g, c );
    	}
 }
