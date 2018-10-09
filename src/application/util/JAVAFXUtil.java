package application.util;

import javafx.scene.Node;

public enum JAVAFXUtil {
	INSTANCE;
	/**
	 * …Ë÷√±≥æ∞—’…´
	 */
	public void setBackGroundColor(Node node,String color) {
		node.setStyle("-fx-background-color:"+color+";");
	}
	/**
	 * …Ë÷√±≥æ∞Õº∆¨
	 */
	public void setBackGroundImg(Node node,String path) {
		node.setStyle("-fx-background-image:url("+path+");");
	}
	/**
	 * …Ë÷√±≥æ∞Õº∆¨µƒ≥ﬂ¥Á
	 */
	public void setBackGroundImgAndSize(Node node,String path,String repeatX,String repeatY) {
		node.setStyle("-fx-background-image:url("+path+");"+"-fx-background-size:"+repeatX+" "+repeatY+";");
	}
}
