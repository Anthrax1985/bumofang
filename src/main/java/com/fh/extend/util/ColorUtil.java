package com.fh.extend.util;

import java.awt.Color;

public class ColorUtil {
	
	public static Color getColorFromHex(String hexColor){
		int rgb = Integer.parseInt(hexColor, 16);
		int r = (rgb >> 16) & 0xFF;
		int g = (rgb >> 8) & 0xFF;
		int b = rgb & 0xFF;
		
		return new Color(r, g, b);
	}
	
	public static Color getColorFromRGB(String rgbColor){
		String[] colorArray = rgbColor.split(",");
		String red = colorArray[0];
		String green = colorArray[1];
		String blue = colorArray[2];
		red = red.substring(red.indexOf("(") + 1).trim();
		green = green.trim();
		blue = blue.substring(0, blue.indexOf(")"));
		int r = Integer.parseInt(red);
		int g = Integer.parseInt(green);
		int b = Integer.parseInt(blue);
		
		return new Color(r, g, b);
	}
	
	public static int getColorDistane(Color srcColor, Color targetColor){
		int r = srcColor.getRed() - targetColor.getRed();
		int g = srcColor.getGreen() - targetColor.getGreen();
		int b = srcColor.getBlue() - targetColor.getBlue();
		return r * r + g  *g + b * b;
	}

}
