package com.fh.extend.logic.map;

public enum AppKeyMapEnum {
	ID("ID"),
	NUMBER("NUMBER"),
	NAME("NAME"),
	RENDER_FILE("RENDER_FILE"),
	ART_PICTURE("ART_PICTURE", "imageUrl"),
	STYLE_TAGS("STYLE_TAGS"),
	COLLOCATION_TAGS("COLLOCATION_TAGS"),
	DESIGN_TAGS("DESIGN_TAGS"),
	PRICE("PRICE")
	;

	private String source;
	private String target;
	
	private AppKeyMapEnum(String source, String target){
		this.source = source;
		this.target = target;
	}
	
	private AppKeyMapEnum(String source){
		this(source, source.toLowerCase());
	}
	
	public String getSource(){
		return source;
	}
	
	public String getTarget(){
		return target;
	}
}
