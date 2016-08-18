package com.cxstock.utils.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;

public class FileUtils{

	public static void deleteFile(String path)
	{
		File file = new File(path);
		if (file.exists())
		{
			file.delete();
		}
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static void deleteDirectory(String sPath)
	{
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator))
		{
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) { return; }
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++)
		{
			// 删除子文件
			if (files[i].isFile())
			{
				deleteFile(files[i].getAbsolutePath());
			} // 删除子目录
			else
			{
				deleteFiles(files[i].getAbsolutePath());
			}
		}
	}

	public static void deleteFiles(String sPath)
	{
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator))
		{
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) { return; }
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++)
		{
			// 删除子文件
			if (files[i].isFile())
			{
				deleteFile(files[i].getAbsolutePath());
			} // 删除子目录
			else
			{
				deleteFiles(files[i].getAbsolutePath());
			}
		}
		dirFile.delete();
	}

	/**
	 * 获取文件夹大小
	 * 
	 * @param file
	 *            File实例
	 * @return long
	 */
	public static long getFolderSize(java.io.File file)
	{

		long size = 0;
		try
		{
			java.io.File[] fileList = file.listFiles();
			for (int i = 0; i < fileList.length; i++)
			 {
				if (fileList[i].isDirectory())
				{
					size = size + getFolderSize(fileList[i]);
				}
				else
				{
					size = size + fileList[i].length();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return size;
	}

	/**
	 * 格式化单位
	 * 
	 * @param size
	 * @return
	 */
	public static String getFormatSize(double size)
	{
		double kiloByte = size / 1024;
		if (kiloByte < 1) { return size + "Byte(s)"; }

		double megaByte = kiloByte / 1024;
		if (megaByte < 1)
		{
			BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
			return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
		}

		double gigaByte = megaByte / 1024;
		if (gigaByte < 1)
		{
			BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
			return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
		}

		double teraBytes = gigaByte / 1024;
		if (teraBytes < 1)
		{
			BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
			return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
		}
		BigDecimal result4 = new BigDecimal(teraBytes);
		return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
	}

	/**
	 * 文件file进行加密
	 * 
	 * @param fileUrl
	 *            文件路径
	 * @param key
	 *            密码
	 * @throws Exception
	 */
	public static void encrypt(String fileUrl, String key) throws Exception
	{
		File file = new File(fileUrl);
		String path = file.getPath();
		if (!file.exists()) { return; }
		int index = path.lastIndexOf("\\");
		String destFile = path.substring(0, index) + "\\" + "abc";
		System.out.println(destFile);
		File dest = new File(destFile);
		InputStream in = new FileInputStream(fileUrl);
		OutputStream out = new FileOutputStream(destFile);
		byte[] buffer = new byte[1024];
		int r;
		byte[] buffer2 = new byte[1024];
		while ((r = in.read(buffer)) > 0)
		{
			for (int i = 0; i < r; i++)
			{
				byte b = buffer[i];
				buffer2[i] = b == 255 ? 0 : ++b;
			}
			out.write(buffer2, 0, r);
			out.flush();
		}
		in.close();
		out.close();
		file.delete();
		dest.renameTo(new File(fileUrl));
		appendMethodA(fileUrl, key);
		System.out.println("加密成功");
	}

	/**
	 * 
	 * @param fileName
	 * @param content
	 *            密钥
	 */
	public static void appendMethodA(String fileName, String content)
	{
		try
		{
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.writeBytes(content);
			randomFile.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}