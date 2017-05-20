package com.vovatkach2427gmail.houseenergyoptimization.Algoritm;

import android.os.Parcel;
import android.os.Parcelable;

import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;

public class Suit implements Cloneable, Parcelable {
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


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.device, flags);
		dest.writeInt(this.timeOfWorknig);
	}

	protected Suit(Parcel in) {
		this.device = in.readParcelable(Device.class.getClassLoader());
		this.timeOfWorknig = in.readInt();
	}

	public static final Parcelable.Creator<Suit> CREATOR = new Parcelable.Creator<Suit>() {
		@Override
		public Suit createFromParcel(Parcel source) {
			return new Suit(source);
		}

		@Override
		public Suit[] newArray(int size) {
			return new Suit[size];
		}
	};
}
