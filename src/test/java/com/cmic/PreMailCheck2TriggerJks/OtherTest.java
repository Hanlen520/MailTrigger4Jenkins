package com.cmic.PreMailCheck2TriggerJks;

import java.io.File;

import org.junit.Test;

import com.cmic.PreMailCheck2TriggerJks.bean.TestInfo;
import com.cmic.PreMailCheck2TriggerJks.util.LogUtil;
import com.cmic.PreMailCheck2TriggerJks.util.YamlUtil;

public class OtherTest {

	@Test
	public void hello0() {
		File yamlFile = new File("res/other/mailtemplet.yaml");
		if (yamlFile.exists()) {
			TestInfo info = YamlUtil.yaml2Bean(yamlFile, TestInfo.class);
			LogUtil.w("测试设备名为{}",info.getTestDeviceList()[0].getDeviceName());
		}
	}
}
