package cn.ohyeah.mobile.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtils {

	public static byte[] FileToByteArray(File file) {
		FileInputStream stream = null;
		ByteArrayOutputStream out = null;
		try{
			stream = new FileInputStream(file);
			out = new ByteArrayOutputStream(1024);
			byte[] b = new byte[1024];
			int n;
			while ((n = stream.read(b)) != -1) {
				out.write(b, 0, n);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				out.close();
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		byte[] bytes = out.toByteArray();
		return bytes;
	}
}
