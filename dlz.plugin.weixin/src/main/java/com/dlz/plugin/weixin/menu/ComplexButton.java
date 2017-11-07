package com.dlz.plugin.weixin.menu;

/**
 * 复合类型按钮
 * 
 * @author 陈孙亮（微信）
 *
 */
public class ComplexButton extends Button {

	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] subButton) {
		sub_button = subButton;
	}
	
	
}
