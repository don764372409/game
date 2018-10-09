package application.thread;

import java.util.HashSet;
import java.util.List;

import application.GameData;
import application.Role;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * ��ɫ�̶߳���
 */
public class RoleThread extends Thread{
	@Override
	public void run() {
		//��ȡ�ڶ��㻭��
		GraphicsContext gc = GameData.gc2;
		while(true) {
			try {
				if (gc!=null) {
					//��ȡ��Ϸ���еĽ�ɫ
					HashSet<Role> roles = GameData.roles;
					for (Role role : roles) {
						//����ɫ��������
						List<String> imgs = role.getActionImgs();
						for (String img : imgs) {
							gc.clearRect(role.getSceneX()-5, role.getSceneY()-5,role.getWidth()+10,role.getHeight()+5);
							Image image = new Image(getClass().getResourceAsStream(img),role.getWidth(),role.getHeight(),false,false);
							gc.drawImage(image, role.getSceneX(), role.getSceneY());
							Thread.sleep(150);
						}
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
