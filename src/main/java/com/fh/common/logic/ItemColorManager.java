package com.fh.common.logic;

import java.awt.Color;

import com.fh.extend.util.ColorUtil;

public class ItemColorManager {
	
	public static String[] rgbColorArray = new String[]{
			"(230,0,18)", "(235,97,0)","(243,152,0)", 
			"(252,200,0)", "(255,251,0)", "(207,0,219)",
			"(143,195,31)", "(34,172,56)", "(0,153,68)",
			"(0,155,107)", "(0,158,150)", "(0,160,193)",
			"(0,160,233)", "(0,134,209)", "(0,104,183)",
			"(0,71,157)", "(29,32,136)", "(96,25,134)",
			"(146,7,131)", "(190,0,129)", "(228,0,127)",
			"(229,0,106)", "(229,0,79)", "(230,0,51)",
			"(255,255,255)", "(224,224,224)", "(197,197,197)",
			"(168,168,168)", "(141,141,141)", "(112,112,112)",
			"(85,85,85)", "(57,57,57)", "(28,28,28)",
			"(0,0,0)" };
	
	public static Color[] colorArray = null;
	
	static{
		init();
	}
	
	private static void init(){
		colorArray = new Color[rgbColorArray.length];
		for(int i=0;i<rgbColorArray.length;i++){
			colorArray[i] = ColorUtil.getColorFromRGB(rgbColorArray[i]);
		}
	}
	
	public static int getSimilarColorIndex(String hexColor){
		Color srcColor = ColorUtil.getColorFromHex(hexColor);
		
		int[] colorDistance = new int[colorArray.length];
		for(int i=0;i<colorArray.length;i++){
			colorDistance[i] = ColorUtil.getColorDistane(srcColor, colorArray[i]);
		}
		
		int minDistance = 255*255 * 3;
		int index = -1;
		for(int i=0;i<colorDistance.length;i++){
			if(minDistance > colorDistance[i]){
				index = i;
				minDistance = colorDistance[i];
			}
		}
		return index;
	}
	
	public static void main(String[] args) {
		int index = getSimilarColorIndex("eb6100");
		System.out.println("," + (index+1) + ",");
	}

}
