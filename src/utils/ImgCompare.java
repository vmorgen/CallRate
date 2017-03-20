package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 图片对比的方法
 * 
 * @author panyanan
 *
 */

public class ImgCompare {

	/**
	 * 图片对比，100%相同
	 * 
	 * @param path1
	 * @param path2
	 * @return
	 * @throws FileNotFoundException
	 */
	public static boolean sameAs(String path1, String path2) throws FileNotFoundException {
		boolean res = false;
		FileInputStream fis1 = new FileInputStream(path1);
		Bitmap bitmap1 = BitmapFactory.decodeStream(fis1);

		FileInputStream fis2 = new FileInputStream(path2);
		Bitmap bitmap2 = BitmapFactory.decodeStream(fis2);

		res = sameAs(bitmap1, bitmap2);
		return res;
	}

	/**
	 * 图片对比，自定义最低相同比例
	 * 
	 * @param path1
	 * @param path2
	 * @param percent
	 * @return
	 * @throws FileNotFoundException
	 */
	public static boolean sameAs(String path1, String path2, double percent) throws FileNotFoundException {
		FileInputStream fis1 = new FileInputStream(path1);
		Bitmap bitmap1 = BitmapFactory.decodeStream(fis1);

		FileInputStream fis2 = new FileInputStream(path2);
		Bitmap bitmap2 = BitmapFactory.decodeStream(fis2);

		return sameAs(bitmap1, bitmap2, percent);

	}

	/**
	 * 图片对比，自定义最低相同比例
	 * 
	 * @param bitmap1
	 * @param bitmap2
	 * @param percent
	 * @return
	 */
	public static boolean sameAs(Bitmap bitmap1, Bitmap bitmap2, double percent) {
		if (bitmap1.getHeight() != bitmap2.getHeight())
			return false;

		if (bitmap1.getWidth() != bitmap2.getWidth())
			return false;

		if (bitmap1.getConfig() != bitmap2.getConfig())
			return false;

		int width = bitmap1.getWidth();
		int height = bitmap2.getHeight();

		int numDiffPixels = 0;
		/*
		 * 实现方法，单个像素值比较
		 */
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (bitmap1.getPixel(x, y) != bitmap2.getPixel(x, y)) {
					numDiffPixels++;
				}
			}
		}
		double numberPixels = height * width;
		double diffPercent = numDiffPixels / numberPixels; //不同的百分比
		return percent <= 1.0D - diffPercent; //( 1.0D - diffPercent ) 为相同的百分比，，percent为传入的要求，，相同比例要高于我的要求才返回True。
	}

	/**
	 * 图片对比，100%相同
	 * 
	 * @param bmp1
	 * @param bmp2
	 * @return
	 * @throws FileNotFoundException
	 */
	public static boolean sameAs(Bitmap bmp1, Bitmap bmp2) throws FileNotFoundException {
		boolean res = false;

		res = bmp1.sameAs(bmp2);

		return res;
	}

	/**
	 * 图片剪切方法
	 * 
	 * @param path
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Bitmap getSubImage(String path, int x, int y, int width, int height) throws FileNotFoundException {

		FileInputStream fis = new FileInputStream(path);
		Bitmap bitmap = BitmapFactory.decodeStream(fis);

		Bitmap res = Bitmap.createBitmap(bitmap, x, y, width, height);

		return res;

	}
}
