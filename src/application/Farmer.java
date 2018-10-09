package application;

import java.util.ArrayList;
import java.util.List;

public class Farmer extends Role{
	//角色的当前方向
	private String action = GameData.left;//默认往左走
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public List<String> getActionImgs() {
		List<String> actionImgs = new ArrayList<>();
		//如果是往左走
		if (action.equals(GameData.left)) {
			actionImgs.add("/application/img/farmer/left/5.png");
			actionImgs.add("/application/img/farmer/left/6.png");
			actionImgs.add("/application/img/farmer/left/7.png");
		}
		return actionImgs;
	}
}
