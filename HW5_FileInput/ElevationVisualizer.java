/**
 * GUI application to read and visualize elevation data.
 * 
 */
import java.io.PrintWriter;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.util.*;

public class ElevationVisualizer extends JFrame
{
    // Panel that displays the elevation data
    private class DisplayPanel extends JPanel
    {
        private int[][] data;       // Elevation data stored in a 2D array
        private int[] path;         // Path data - element i in this array corresponds to the row of
                                    //  column i that should be chosen to try to minimize elevation differences

        public void setData(int[][] data)
        {
            this.data = data;
        }

        public void setPath(int[] path)
        {
            this.path = path;
        }

        // Overrides the Swing paintComponent method -- defines how to draw this panel
        public void paintComponent(Graphics g)
        {
            if (dataLoaded)
            {
                int max = findMax(data);
                for (int r = 0; r < data.length; r++)   // Each pixel on the panel = 1 value in the data array
                {
                    for (int c = 0; c < data[r].length; c++)
                    {
                        int h = (int)((double)data[r][c]/max*255);  // lighter = higher, darker = lower
                        g.setColor(new Color(h, h, h));
                        g.drawRect(c, r, 1, 1);
                    }
                }
            }

            if (pathRequested)
            {
                for (int i = 0; i < path.length; i++)
                {
                    g.setColor(Color.GREEN);
                    g.drawRect(i, path[i], 1, 1);
                }
            }
        }
    }

    // Event handler for button presses
    private class ButtonHandler implements ActionListener
    {
        // actionPerformed gets called whenever an "action event" (e.g., button press) occurs
        public void actionPerformed(ActionEvent e)
        {
            Object src = e.getSource();
            if (src == bLoadFile)
                chooseFile();
            else if (src == bFindPath)
                findPath();
        }
    }

    private DisplayPanel    pDisplay = new DisplayPanel();
    private JButton         bLoadFile = new JButton("Load new data file"),
                            bFindPath = new JButton("Find a path");
    private JLabel          lStatus = new JLabel("Use the Load button to open a data file");
    private boolean         dataLoaded = false,     // Has the user loaded map data yet?
                            pathRequested = false;  // Has the user requested a path yet?

    // Constructor - handles GUI construction tasks
    public ElevationVisualizer()
    {
        ButtonHandler bh = new ButtonHandler(); // Register event handler for buttons
        bLoadFile.addActionListener(bh);
        bFindPath.addActionListener(bh);

        JPanel cp = new JPanel();       // Content pane
        cp.setLayout(new BorderLayout());

        JPanel bp = new JPanel();       // Panel to hold load/path buttons
        bp.setLayout(new GridLayout(1, 2));
        bp.add(bLoadFile);
        bp.add(bFindPath);

        cp.add(BorderLayout.NORTH, lStatus);
        cp.add(BorderLayout.CENTER, pDisplay);
        cp.add(BorderLayout.SOUTH, bp);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Elevation Data Visualizer");
        setContentPane(cp);
        setSize(600, 600);
        setVisible(true);
    }

    private void resetLoadState()
    {
        dataLoaded = pathRequested = false;
        pDisplay.setData(null);
        lStatus.setText("Use the Load button to open a data file.");
        repaint();
    }
    
    // Opens a file chooser dialog to read elevation data, and calls
    //  readElevationData to import that data into the application
    private void chooseFile()
    {
        resetLoadState();
        
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("."));
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File f = fc.getSelectedFile();
            try
            {
                pDisplay.setData(readElevationData(f));
                dataLoaded = true;
                repaint();      // Triggers the paintComponent method to be called
                lStatus.setText(f.getName() + " successfully opened!");
            }
            catch (FileNotFoundException e)
            {
                lStatus.setText("Can't find " + f.getName());
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                lStatus.setText(f.getName() + " seems to have inconsistent rows and columns");
            }
            catch (NumberFormatException e)
            {
                lStatus.setText(f.getName() + " seems to contain non-integer data");
            }
        }
    }
    
    private void findPath()
    {
        if (!dataLoaded)
            lStatus.setText("Can't find path yet, must load a data file first!");
        else
        {
            pDisplay.setPath(findPath(pDisplay.data));
            pathRequested = true;
            repaint();      // Triggers the paintComponent method to be called
            lStatus.setText("West-east path computed!");
        }
    }

    // **** COMPLETE THIS METHOD ****
    // Reads elevation data from the specified file into a 2-D array, and
    //  returns the array created
    // Throws:  FileNotFoundException if file isn't found
    //          ArrayIndexOutOfBoundsException if each row in the file doesn't have the same number of columns
    //          NumberFormatException if file contains non-integer data
    private int[][] readElevationData(File f) throws FileNotFoundException, ArrayIndexOutOfBoundsException, NumberFormatException
    {	
    	
    	Scanner s = null;
    	try {
    		s = new Scanner(f);
    	} catch (FileNotFoundException e) {
    		System.out.println("File not found!");
    		System.exit(1);
    	}
    	
    	int rows = 0, columns = 0;
    	while (s.hasNextLine()) {
    		rows++;
    		s.nextLine();
    	}
    	
    	s = new Scanner(f);
    	String line = s.nextLine();
    	line = line.substring(3);
		String[] elevationInput = line.split("\\s+");
		columns = elevationInput.length;
		
		s = new Scanner(f);
		int[][] elevationArray = new int[rows][columns];
		while (s.hasNextLine()) {

			for (int i = 0; i < rows; i++) {
				line = s.nextLine();
				line = line.substring(3);
				elevationInput = line.split("\\s+");
				int columnsMatch = elevationInput.length; 				
				try {
					for (int j = 0; j < columnsMatch; j++) {
						elevationArray[i][j] = Integer.parseInt(elevationInput[j]);
					}
				} catch (NumberFormatException n) {
					System.out.println("Error: Input cannot be read as an integer!");
					System.exit(1);
				} catch (ArrayIndexOutOfBoundsException a) {
					System.out.println("Error: Column Mismatch!");
					System.exit(1);
				}
			}	
		}
    	
        return elevationArray;    // Replace with the array that you return
    }

    // **** COMPLETE THIS METHOD ****
    // Finds and returns a west-east path through the area whose elevation is stored in data
    private int[] findPath(int[][] data) {
    	
    	int[] path = new int[480];
    	int startIndex = indexOfMinFromCol(data, 0);
    	path[0] = startIndex;

    	for (int j = 0; j < data.length - 1; j++) {

    		if (startIndex == 0) {
    			int eastIndex = startIndex;
    			int southEastIndex = startIndex + 1;
    			int minAltitude = eastIndex;

    			int minChange = Math.abs(data[startIndex][j] - data[eastIndex][j + 1]);
    			if (Math.abs(data[startIndex][j] - data[southEastIndex][j + 1]) < minChange) {
    				minAltitude = southEastIndex;
    				minChange = Math.abs(data[startIndex][j] - data[southEastIndex][j + 1]);
    			}	
    			path[j+1] =  minAltitude;
    			startIndex = minAltitude;
    		}

    		else if (startIndex == data.length - 1) {
    			int northEastIndex = startIndex - 1;
    			int eastIndex = startIndex;

    			int minAltitude = northEastIndex;
    			int minChange = Math.abs(data[startIndex][j] - data[northEastIndex][j + 1]);

    			if (Math.abs(data[startIndex][j] - data[eastIndex][j + 1]) < minChange) {
    				minAltitude = eastIndex;
    				minChange = Math.abs(data[startIndex][j] - data[eastIndex][j + 1]);
    			}
    			path[j+1] =  minAltitude;
    			startIndex = minAltitude;
    		}

    		else { //if (startIndex > 0 && startIndex < data.length - 1) 	
    			int northEastIndex = startIndex - 1;
    			int eastIndex = startIndex;
    			int southEastIndex = startIndex + 1;

    			int minAltitude = northEastIndex;
    			int minChange = Math.abs(data[startIndex][j] - data[northEastIndex][j + 1]);

    			if (Math.abs(data[startIndex][j] - data[eastIndex][j + 1]) < minChange) {
    				minAltitude = eastIndex;
    				minChange = Math.abs(data[startIndex][j] - data[eastIndex][j + 1]);
    			}
    			if (Math.abs(data[startIndex][j] - data[southEastIndex][j + 1]) < minChange) 
    				minAltitude = southEastIndex;

    			path[j+1] =  minAltitude;
    			startIndex = minAltitude;
    		}	
    	}
    	return path;    // Replace with the array that you return

    }
    
    // **** COMPLETE THIS METHOD ****
    // Finds and returns the max element from the 2D array a
    public static int findMax(int[][] a)
    {	
    	int max = a[0][0];
    	for (int i = 0; i < a.length; i++) {
    		for (int j = 0; j < a[i].length; j++) {
    			if (a[i][j] > max) {
    				max = a[i][j];
    			}
    		}
    	}
        return max;       // Replace with the max value that you find
    }

    // **** COMPLETE THIS METHOD ****
    // Finds and returns the index of the min element from column c of the 2D array a
    public static int indexOfMinFromCol(int[][] a, int c)
    {	
    	int min = a[0][c];
    	int minIndex = 0;
    	for (int i = 1; i < a.length; i++) {
    		if (a[i][c] < min) {
    			min = a[i][c];
    			minIndex = i;
    		}
    	}
        return minIndex;       // Replace with the index that you find
    }

    public static void main(String[] args)
    {
        new ElevationVisualizer();
    }
}
