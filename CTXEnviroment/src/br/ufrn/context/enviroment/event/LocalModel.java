package br.ufrn.context.enviroment.event;

public class LocalModel {

	private String location;
	private double temperature;
	private String pollutionLevel;
	private int isFire;
	public String getLocation() {
		return location;
	}
	public void setLocation(String local) {
		this.location = local;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public String getPollutionLevel() {
		return pollutionLevel;
	}
	public void setPollutionLevel(String pollutionLevel) {
		this.pollutionLevel = pollutionLevel;
	}
	public int isFire() {
		return isFire;
	}
	public void setFire(int isFire) {
		this.isFire = isFire;
	}
	
	
}
