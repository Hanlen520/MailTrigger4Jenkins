package com.cmic.PreMailCheck2TriggerJks.bean;

import com.cmic.PreMailCheck2TriggerJks.Tips;

@Tips(description = "测试类，表征一个测试设备", riskPoint = "当前仅支持Android设备")
public class Device {
	private String deviceName;
	private int deviceSDK;
	private String deviceUid;

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public int getDeviceSDK() {
		return deviceSDK;
	}

	public void setDeviceSDK(int deviceSDK) {
		this.deviceSDK = deviceSDK;
	}

	public String getDeviceUid() {
		return deviceUid;
	}

	public void setDeviceUid(String deviceUid) {
		this.deviceUid = deviceUid;
	}
}
