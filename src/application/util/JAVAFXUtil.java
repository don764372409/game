package application.util;

import javafx.scene.Node;

public enum JAVAFXUtil {
	INSTANCE;
	/**
	 * ���ñ�����ɫ
	 */
	public void setBackGroundColor(Node node,String color) {
		node.setStyle("-fx-background-color:"+color+";");
	}
	/**
	 * ���ñ���ͼƬ
	 */
	public void setBackGroundImg(Node node,String path) {
		node.setStyle("-fx-background-image:url("+path+");");
	}
	/**
	 * ���ñ���ͼƬ�ĳߴ�
	 */
	public void setBackGroundImgAndSize(Node node,String path,String repeatX,String repeatY) {
		node.setStyle("-fx-background-image:url("+path+");"+"-fx-background-size:"+repeatX+" "+repeatY+";");
	}
}
