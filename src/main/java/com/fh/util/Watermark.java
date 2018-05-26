package com.fh.util;

public class Watermark {
	
		private	static String strFWATERM,strIWATERM;
		
		static{
			strFWATERM = Tools.readTxtFile(Const.FWATERM);	//读取文字水印配置
			strIWATERM = Tools.readTxtFile(Const.IWATERM);	//读取图片水印配置
		}
		
		/**
		 * 刷新
		*/
		public static void fushValue(){
			strFWATERM = Tools.readTxtFile(Const.FWATERM);	//读取文字水印配置
			strIWATERM = Tools.readTxtFile(Const.IWATERM);	//读取图片水印配置
		}
			
		
	  
	
	
}
