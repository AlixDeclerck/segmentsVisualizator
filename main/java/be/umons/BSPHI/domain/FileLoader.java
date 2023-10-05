package be.umons.BSPHI.domain;

import java.io.FileReader;
import java.io.IOException;

import be.umons.BSPHI.domain.shape.Point;
import be.umons.BSPHI.domain.shape.Segment;
import be.umons.BSPHI.domain.shape.SegmentList;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.HashMap;

import static java.lang.Integer.valueOf;

/**
 * Read files and return a list of segments
 */
public class FileLoader {
	
	/**
	 * Read the indicated file and return a list of segments. The coordinates of the segments point are adapted to the specified bounds. 
	 */
	public static SegmentList readFile(String filePath, int xBound, int yBound, HashMap<String,Color> colors, String elementSeparator) {
		SegmentList sl = new SegmentList();
		int width, height;
		double x1, y1, x2, y2;
		String color, line;
		Point p1, p2;
		String[] splittedLine;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			line = reader.readLine();
			splittedLine = line.split(elementSeparator);
			
			width = valueOf(splittedLine[1]);
			height = valueOf(splittedLine[2]);
			line = reader.readLine();

			while(line != null){
				splittedLine = line.split(elementSeparator);
				x1 = Double.valueOf(splittedLine[0]);
				y1 = Double.valueOf(splittedLine[1]);
				x2 = Double.valueOf(splittedLine[2]);
				y2 = Double.valueOf(splittedLine[3]);
				color = splittedLine[4];				
				
				x1 = boundAdapter(xBound, width, x1);
				y1 = boundAdapter(yBound, height, y1);

				x2 = boundAdapter(xBound, width, x2);
				y2 = boundAdapter(yBound, height, y2);
				p1 = new Point(x1, y1);
				p2 = new Point(x2, y2);
				sl.add(new Segment(p1, p2, getColorFromString(color, colors)));

				line = reader.readLine();
			}
			reader.close();   
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}

		return sl;
	}

	private static Color getColorFromString(String str, HashMap<String,Color> colors) {
		return colors.getOrDefault(str,Color.BLACK);
	}

	private static double boundAdapter(double mapBound, double fileBound, double val) {
		return ((val + fileBound) / 2) * (mapBound / fileBound);
	}

}
