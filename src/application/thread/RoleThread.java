package application.thread;

import java.util.HashSet;
import java.util.List;

import application.GameData;
import application.Role;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * 角色线程对象
 */
public class RoleThread extends Thread{
	@Override
	public void run() {
		//获取第二层画布
		GraphicsContext gc = GameData.gc2;
		while(true) {
			try {
				if (gc!=null) {
					//获取游戏所有的角色
					HashSet<Role> roles = GameData.roles;
					for (Role role : roles) {
						//画角色到窗口中
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
