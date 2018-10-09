package application.thread;

import application.GameData;
import application.Role;

public class MoveRoleThread extends Thread{
	private Role role;//当前要行走的角色
	private double x;//要走到的位置
	private double y;//要走到的位置
	
	//定义一个length，移动素的
	int length = 5;
	//定义一个变量表示角色是否到达终点
	public void run() {
		boolean end = false;
		while(!end) {
			end = moveRole(role, x, y);
			//判断是否到达目的
			if (end) {
				//将角色行走状态改为等待
				role.setState("wait");
				GameData.selectedRoles.remove(role);
				//结束线程
			}
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 移动角色
	 * @param role
	 */
	private boolean moveRole(Role role,double x,double y) {
		//获取角色当前的坐标
		int sceneX = role.getSceneX();
		int sceneY = role.getSceneY();
		//计算角色下一步要达到的坐标
		int toX = sceneX;
		int toY = sceneY;
		
		//左走
		if (y==sceneY&&x<sceneX) {
			toX -=length;
			//判断是否到达终点
			if (toX<=x) {
				return true;
			}
			//右走
		}else if(y==sceneY&&x>sceneX){
			toX +=length;
			//判断是否到达终点
			if (toX>=x) {
				return true;
			}
			//上走
		}else if(x==sceneX&&y<sceneY) {
			toY-=length; 
			//判断是否到达终点
			if (toY<=y) {
				return true;
			}
			//下走
		}else if(x==sceneX&&y>sceneY) {
			toY+=length;
			//判断是否到达终点
			if (toX>=x) {
				return true;
			}
			//左上
		}else if(x<sceneX&&y<sceneY) {
			toX-=length;
			toY-=length;
			
			//判断是否到达终点
			if (toX<=x&&toY<=y) {
				return true;
			}
			
			//左下
		}else if(x<sceneX&&y>sceneY) {
			toX-=length;
			toY+=length;
			//判断是否到达终点
			if (toX<=x&&toY>=y) {
				return true;
			}
			//右上
		}else if(x>sceneX&&y<sceneY) {
			toX+=length;
			toY-=length;
			//判断是否到达终点
			if (toX>=x&&toY<=y) {
				return true;
			}
			//右下
		}else if(x>sceneX&&y>sceneY) {
			toX+=length;
			toY+=length;
			//判断是否到达终点
			if (toX>=x&&toY>=y) {
				return true;
			}
		}
		//角色一步走完之后  将当前坐标设置给角色对象 给下一次行走做参考
		role.setSceneX(toX);
		role.setSceneY(toY);
		return false;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
}
