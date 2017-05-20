package com.vovatkach2427gmail.houseenergyoptimization.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Device implements Parcelable {

	private int id;
	private String name;
	private String extraInfo;
	private int powerConsumption;
	private int priority;
	private int tMin = 0;
	private int tMax = 0;

	public Device(final int id, final String name, final String extraInfo,final int powerConsumption, final int priority, final int tMin,
			final int tMax) {

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

	public int getPowerConsumption() {
		return powerConsumption;
	}

	public void setPowerConsumption(int powerConsumption) {
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

	public String getExtraInfo() {
		return extraInfo;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.name);
		dest.writeString(this.extraInfo);
		dest.writeInt(this.powerConsumption);
		dest.writeInt(this.tMin);
		dest.writeInt(this.priority);
		dest.writeInt(this.tMax);
	}

	protected Device(Parcel in) {
		this.id = in.readInt();
		this.name = in.readString();
		this.extraInfo = in.readString();
		this.powerConsumption = in.readInt();
		this.tMin = in.readInt();
		this.priority = in.readInt();
		this.tMax = in.readInt();
	}

	public static final Parcelable.Creator<Device> CREATOR = new Parcelable.Creator<Device>() {
		@Override
		public Device createFromParcel(Parcel source) {
			return new Device(source);
		}

		@Override
		public Device[] newArray(int size) {
			return new Device[size];
		}
	};
}
