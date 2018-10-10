package application.util;

import java.io.File;

public class FileUtil {
	public static void main(String[] args) {
		File file = new File("D:\\workspace\\MySanGuo\\src\\application\\img\\dixing");
		//得到所有地形图片  145张
		File[] files = file.listFiles();
		for (File f : files) {
			String name = f.toString().replace(" (", "");
			name = name.replace(")", "");
			boolean b = f.renameTo(new File(name));
			System.err.println(b);
		}
	}
}
