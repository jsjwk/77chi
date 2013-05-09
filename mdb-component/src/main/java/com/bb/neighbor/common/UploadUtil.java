package com.bb.neighbor.common;

import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传工具类
 * 
 * @author song.zhang@boco.jp
 * 
 */
public class UploadUtil {
	/**
	 * 保存文件
	 * 
	 * @param filedata
	 * @param path
	 * @param fileName
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public static void saveFile(MultipartFile filedata, String path, String fileName) throws IOException {
		InputStream stream = filedata.getInputStream();
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		FileOutputStream fs = new FileOutputStream(path + "/" + fileName);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}

	/**
	 * jmagick 缩放图
	 * @param path
	 * @param fileName
	 * @param suffix
	 * @param toWidth
	 * @param toHeight
	 */
	@SuppressWarnings("unused")
	public static void processHead(String path, String fileName, String suffix, int toWidth, int toHeight) {
		try {
			System.setProperty("jmagick.systemclassloader", "no");// 这个没什么好说的，照办就是了
			ImageInfo info = new ImageInfo(path + fileName + suffix);
			MagickImage fromImage = new MagickImage(info);
			Dimension dim = fromImage.getDimension();
			double w = dim.getWidth();
			double h = dim.getHeight();
			//int toHeight = (int) (h * toWidth / w);// 按宽度比例缩放

			MagickImage toImage = fromImage.scaleImage(toWidth, toHeight);// 缩放操作
			toImage.setFileName(path + fileName + toWidth + ImageSize.CONNECTOR + toHeight + suffix);// 设置输出的文件名
			toImage.writeImage(info); // 保存
		} catch (MagickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证上传的文件是否为空
	 * 
	 * @param filedata
	 * @return
	 */
	public static boolean validateFileEmpty(MultipartFile filedata) {
		boolean tag = false;
		if (null == filedata || filedata.getSize() <= 0) {
			tag = true;
		}
		return tag;
	}

	/**
	 * 验证上传的文件大小
	 * 
	 * @param filedata
	 * @param maxSize
	 * @return
	 */
	public static boolean validateFileSize(MultipartFile filedata, long maxSize) {
		boolean tag = false;
		if (filedata.getSize() > maxSize) {
			tag = true;
		}
		return tag;
	}
}
