package application.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;

import application.MapBlock;

public class FileUtil {
	public static void main(String[] args) {
		File file = new File("D:\\workspace\\MySanGuo\\src\\application\\img\\dixing");
		//�õ����е���ͼƬ  145��
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			boolean b = files[i].renameTo(new File("D:\\workspace\\MySanGuo\\src\\application\\img\\dixing\\"+(i+1)+".png"));
			System.err.println(b);
			
		}
	}
	/**
	 * ��һ���ļ�·��  ���������ļ�
	 * @param path
	 * @return
	 */
	public static File[] getAllFiles(String path) {
		File file = new File(path);
		//�õ����е���ͼƬ  145��
		return file.listFiles();
	}
	/**
	 * д��ͼ
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
