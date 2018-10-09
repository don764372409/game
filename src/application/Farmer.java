package application;

import java.util.ArrayList;
import java.util.List;

public class Farmer extends Role{
	//��ɫ�ĵ�ǰ����
	private String action = GameData.left;//Ĭ��������
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public List<String> getActionImgs() {
		List<String> actionImgs = new ArrayList<>();
		//�����������
		if (action.equals(GameData.left)) {
			actionImgs.add("/application/img/farmer/left/5.png");
			actionImgs.add("/application/img/farmer/left/6.png");
			actionImgs.add("/application/img/farmer/left/7.png");
		}
		return actionImgs;
	}
}
