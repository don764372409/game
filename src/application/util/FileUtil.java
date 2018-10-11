package application.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;

import application.MapBlock;

public class FileUtil {
	public static void main(String[] args) {
		File file = new File("D:\\workspace\\MySanGuo\\src\\application\\img\\dixing");
		//得到所有地形图片  145张
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			boolean b = files[i].renameTo(new File("D:\\workspace\\MySanGuo\\src\\application\\img\\dixing\\"+(i+1)+".png"));
			System.err.println(b);
			
		}
	}
	/**
	 * 给一个文件路径  返回所有文件
	 * @param path
	 * @return
	 */
	public static File[] getAllFiles(String path) {
		File file = new File(path);
		//得到所有地形图片  145张
		return file.listFiles();
	}
	/**
	 * 写地图
	 * @param mbs
	 */
	public static void writeMap(LinkedList< MapBlock> mbs) {
		File file = new File("D:\\workspace\\MySanGuo\\src\\application\\map.txt");
		try {
			FileOutputStream out = new FileOutputStream(file);
			for(MapBlock mb:mbs) {
				out.write(("{x="+mb.getX()+",y="+mb.getY()+",width="+mb.getWidth()+",height="+mb.getHeight()+",type="+mb.getType()+",path="+mb.getImgPath()+"},\n").getBytes());
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
