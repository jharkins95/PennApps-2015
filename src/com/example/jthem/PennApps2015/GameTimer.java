package com.example.jthem.PennApps2015;

public class GameTimer {
	
	private long currentTime;
	private long marker;
	
	public GameTimer() {
		currentTime = 0;
		marker = 0;
	}
	
	public GameTimer(long time) {
		currentTime = time;
		marker = 0;
	}
	
	public long getTime() {
		return currentTime;
	}
	
	public long getMarker() {
		return marker;
	}
	
	public void setCurrentTime() {
		currentTime = System.currentTimeMillis();
	}
	
	public void setMarker(long marker) {
		this.marker = marker;
	}
	
	public long cmpMarkerCurrent() {
		return currentTime - marker;
	}
}
