package utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CmdAdb {
	static String file = "D:\\testTmp";

	public void creatBat(String command) throws IOException {
		FileWriter fw = null;
		try {
			fw = new FileWriter(file + "\\runbat.bat");
			fw.write(command);
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(0);
				}
			}
		}
	}

	private String execute(String batname) throws IOException, InterruptedException {
		Process process;
		String line = null;
		StringBuffer sb = new StringBuffer();
		try {
			process = Runtime.getRuntime().exec(batname);
			InputStream fis = process.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			if (process.waitFor() != 0) {
				System.out.println("fail");
				return "fail";
			}
			System.out.println(batname + " run successful!");
			return "success";
		} finally {
		}
	}

	public static void run(String commond) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("路径是" + file);
		CmdAdb df = new CmdAdb();
		df.creatBat(commond);

		df.execute(file + "\\runbat.bat");
	}

	public static void main(String[] args) {
		try {
			run("adb push D:\\workspace\\uiauto5\\bin\\uiauto5.jar /sdcard/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
