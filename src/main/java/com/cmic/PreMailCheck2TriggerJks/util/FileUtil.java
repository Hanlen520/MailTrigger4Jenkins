package com.cmic.PreMailCheck2TriggerJks.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import com.cmic.PreMailCheck2TriggerJks.Tips;

public class FileUtil {

	@Tips(description = "路径格式化")
	public static String pathFormat(String pathRaw) {
		String OSName = System.getProperties().getProperty("os.name"); // 操作系统名称
		if (OSName.contains("Linux")) {// Linux类的操作系统
			return pathRaw.replaceAll("\\", File.separator);
		} else {// 使用Win
			return pathRaw.replaceAll("/", File.separator);
		}
	}

	@Tips(description = "文件复制")
	public static void copyFileUsingFileChannels(File source, File dest) throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} finally {
			inputChannel.close();
			outputChannel.close();
		}
	}
}
