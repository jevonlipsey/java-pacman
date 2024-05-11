//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

/**
 * Represents the maze that appears on screen. Creates the maze data using a 2D
 * array of Cell objects, and renders the maze on screen.
 *
 */
public final class Map extends JPanel
{
	
    final static int CELL = 24;

	private static final int GAME_PANEL_WIDTH = 550;
	private static final int GAME_PANEL_HEIGHT = 630;
	
    private String map = "testLevel.txt/";
   
    private int tileWidth;
    private int tileHeight;
    
    
    private Cell[][] cells;

    public Map()
    {
        createMap(map);
        setPreferredSize(new Dimension(GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT));
        repaint();
    }

    /**
     * Reads from the map file and create the two dimensional array
     */
    private void createMap(String mapFile) 
    {

        // Scanner object to read from map file
        Scanner fileReader;
        ArrayList<String> lineList = new ArrayList<String>();

        // Attempt to load the maze map file
        try 
        {
            fileReader = new Scanner(new File(mapFile));

            while (true) 
            {
                String line = null;

                try 
                {
                    line = fileReader.nextLine();
                } 
                catch (Exception eof) 
                {

                	// eof.printStackTrace();
                	//TODO: has errors on stacktrace, unsure why
                }

                if (line == null)
                {
                    break;
                }

                lineList.add(line);
            }

            tileHeight = lineList.size();
            tileWidth  = lineList.get(0).length();

            // Create the cells
            cells = new Cell[tileHeight][tileWidth];

            for (int row = 0; row < tileHeight; row++)
            {
                String line = lineList.get(row);

                for (int column = 0; column < tileWidth; column++)
                {
                    char type = line.charAt(column);

                    cells[row][column] = new Cell(column, row, type);
                }
            }
        } 
        catch (FileNotFoundException e)
        {
            System.out.println("Map file not found");
        }
    }

    /**
     * Generic paint method Iterates through each cell/tile in the 2D array,
     * drawing each in the appropriate location on screen
     *
     * @param g Graphics object
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, tileWidth * CELL, tileHeight * CELL);

        // Outer loop loops through each row in the array
        for (int row = 0; row < tileHeight; row++)
        {
            // Inner loop loops through each column in the array
            for (int column = 0; column < tileWidth; column++) 
            {
                cells[row][column].drawBackground(g);
            }
        }

 
    }

    public Cell[][] getCells()
    {
        return cells;
    }

    public boolean isWall(int column, int row) 
    {
        if (row < 0 || row >= tileHeight || column < 0 || column >= tileWidth)
        {
            return false; 
        }
        return cells[row][column].isWall();
    }

}