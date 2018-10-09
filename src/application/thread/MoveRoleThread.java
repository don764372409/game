package application.thread;

import application.GameData;
import application.Role;

public class MoveRoleThread extends Thread{
	private Role role;//��ǰҪ���ߵĽ�ɫ
	private double x;//Ҫ�ߵ���λ��
	private double y;//Ҫ�ߵ���λ��
	
	//����һ��length���ƶ��ص�
	int length = 5;
	//����һ��������ʾ��ɫ�Ƿ񵽴��յ�
	public void run() {
		boolean end = false;
		while(!end) {
			end = moveRole(role, x, y);
			//�ж��Ƿ񵽴�Ŀ��
			if (end) {
				//����ɫ����״̬��Ϊ�ȴ�
				role.setState("wait");
				GameData.selectedRoles.remove(role);
				//�����߳�
			}
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * �ƶ���ɫ
	 * @param role
	 */
	private boolean moveRole(Role role,double x,double y) {
		//��ȡ��ɫ��ǰ������
		int sceneX = role.getSceneX();
		int sceneY = role.getSceneY();
		//�����ɫ��һ��Ҫ�ﵽ������
		int toX = sceneX;
		int toY = sceneY;
		
		//����
		if (y==sceneY&&x<sceneX) {
			toX -=length;
			//�ж��Ƿ񵽴��յ�
			if (toX<=x) {
				return true;
			}
			//����
		}else if(y==sceneY&&x>sceneX){
			toX +=length;
			//�ж��Ƿ񵽴��յ�
			if (toX>=x) {
				return true;
			}
			//����
		}else if(x==sceneX&&y<sceneY) {
			toY-=length; 
			//�ж��Ƿ񵽴��յ�
			if (toY<=y) {
				return true;
			}
			//����
		}else if(x==sceneX&&y>sceneY) {
			toY+=length;
			//�ж��Ƿ񵽴��յ�
			if (toX>=x) {
				return true;
			}
			//����
		}else if(x<sceneX&&y<sceneY) {
			toX-=length;
			toY-=length;
			
			//�ж��Ƿ񵽴��յ�
			if (toX<=x&&toY<=y) {
				return true;
			}
			
			//����
		}else if(x<sceneX&&y>sceneY) {
			toX-=length;
			toY+=length;
			//�ж��Ƿ񵽴��յ�
			if (toX<=x&&toY>=y) {
				return true;
			}
			//����
		}else if(x>sceneX&&y<sceneY) {
			toX+=length;
			toY-=length;
			//�ж��Ƿ񵽴��յ�
			if (toX>=x&&toY<=y) {
				return true;
			}
			//����
		}else if(x>sceneX&&y>sceneY) {
			toX+=length;
			toY+=length;
			//�ж��Ƿ񵽴��յ�
			if (toX>=x&&toY>=y) {
				return true;
			}
		}
		//��ɫһ������֮��  ����ǰ�������ø���ɫ���� ����һ���������ο�
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
