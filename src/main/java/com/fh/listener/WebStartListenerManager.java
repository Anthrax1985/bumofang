package com.fh.listener;

import java.util.ArrayList;
import java.util.List;

public class WebStartListenerManager {
	
	private static final WebStartListenerManager instance = new WebStartListenerManager();
	
	private List<WebStartListener> listenerList;
	
	public static WebStartListenerManager getInstance(){
		return instance;
	}
	
	private WebStartListenerManager(){
		listenerList = new ArrayList<WebStartListener>();
	}
	
	public void register(WebStartListener listener){
		listenerList.add(listener);
	}
	
	public List<WebStartListener> getAll(){
		return listenerList;
	}

}
