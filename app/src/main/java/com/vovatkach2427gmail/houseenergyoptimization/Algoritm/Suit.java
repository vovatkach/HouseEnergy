package com.vovatkach2427gmail.houseenergyoptimization.Algoritm;

import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;

public class Suit implements Cloneable {
	private Device device;
	private int timeOfWorknig;

	public Suit(final Device device, final int timeOfWorknig) {
		this.device = device;
		this.timeOfWorknig = timeOfWorknig;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public int getTimeOfWorknig() {
		return timeOfWorknig;
	}

	public void setTimeOfWorknig(int timeOfWorknig) {
		this.timeOfWorknig = timeOfWorknig;
	}

	@Override
	public String toString() {
		return "Suit [Назва:" + device.getName() + ", Пріорітет=" + device.getPriority() + ", Eнергія="
				+ device.getPowerConsumption() + ", Час роботи=" + timeOfWorknig + "]";
	}

	public Suit clone() {
		Suit suit = new Suit(this.getDevice(), this.timeOfWorknig);
		return suit;

	}

	public double getPriority() {
		return getTimeOfWorknig() * getDevice().getPriority();
	}

	public double getCost() {
		return getTimeOfWorknig() * getDevice().getPowerConsumption();
	}
}
