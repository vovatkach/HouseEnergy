package com.vovatkach2427gmail.houseenergyoptimization.Model;

public class Device {

	private int id;
	private String name;
	private String extraInfo;
	private double powerConsumption;
	private int priority;
	private int tMin = 0;
	private int tMax = 0;

	public Device(final String name, final double powerConsumption, final int priority, final int tMin,
			final int tMax, final String extraInfo,final int id) {

		this.name = name;
		this.powerConsumption = powerConsumption;
		this.priority = priority;
		this.tMin = tMin;
		this.tMax = tMax;
		this.extraInfo=extraInfo;
		this.id=id;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPowerConsumption() {
		return powerConsumption;
	}

	public void setPowerConsumption(double powerConsumption) {
		this.powerConsumption = powerConsumption;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int gettMin() {
		return tMin;
	}

	public void settMin(int tMin) {
		this.tMin = tMin;
	}

	public int gettMax() {
		return tMax;
	}

	public void settMax(int tMax) {
		this.tMax = tMax;
	}
}
