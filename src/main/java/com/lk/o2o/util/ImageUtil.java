package com.lk.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

	/**
	 * 将CommonsMultipartFile转换成File类
	 * @param cFile
	 * @return
	 */
	public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile){
		File newFile = new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFile;
	}

	/**
	 * 处理缩略图，并返回新生成图片的相对值路径
	 * @param thumbnail
	 * @param targetAddr
	 * @return
	 */
	public static String generateThumbnail(File thumbnail, String targetAddr) {
		String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String realFileName = FileUtil.getRandomFileName();
		String extension = getFileExtension(thumbnail);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail).size(200, 200)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/baidu.png")),0.25f).toFile(dest);
		} catch (IOException e) {
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		return relativeAddr;
	}

	/**
	 * 创建目标路径所涉及到的目录
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = FileUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取输入文件流的扩展名
	 * @param cFile
	 * @return
	 */
	private static String getFileExtension(File cFile) {
		String originalFileName = cFile.getName();
		return originalFileName.substring(originalFileName.lastIndexOf("."));
	}
}
