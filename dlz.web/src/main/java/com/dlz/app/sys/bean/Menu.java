package com.dlz.app.sys.bean;

import java.util.List;

/**
 * 菜单信息表
 * 
 * @author dk 2017-06-15
 *
 */
public class Menu implements java.io.Serializable {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	/**
	 * 
	 */
	private static final long serialVersionUID = 5070462418563344534L;
	protected int id;
	protected int pid;
	protected String title;
	protected String url;
	protected String s_url;
	protected String target;
	protected int sorder;
	protected int menutype;
	protected String icon;
	protected String icon_hover;
	protected int is_display;
	private List<Menu> children;

	public int getId() {
		return id;
	}

	public void setId(int value) {
		this.id = value;
	}

	/**
	 * 获取父节点
	 */
	public int getPid() {
		return pid;
	}

	/**
	 * 设置父节点
	 */
	public void setPid(int value) {
		this.pid = value;
	}

	/**
	 * 获取标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 */
	public void setTitle(String value) {
		this.title = value;
	}

	/**
	 * 获取路径
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置路径
	 */
	public void setUrl(String value) {
		this.url = value;
	}

	public String getS_url() {
		return s_url;
	}

	public void setS_url(String s_url) {
		this.s_url = s_url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String value) {
		this.target = value;
	}

	/**
	 * 获取排序
	 */
	public int getSorder() {
		return sorder;
	}

	/**
	 * 设置排序
	 */
	public void setSorder(int value) {
		this.sorder = value;
	}

	/**
	 * 获取类型
	 */
	public int getMenutype() {
		return menutype;
	}

	/**
	 * 设置类型
	 */
	public void setMenutype(int value) {
		this.menutype = value;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String value) {
		this.icon = value;
	}

	public String getIcon_hover() {
		return icon_hover;
	}

	public void setIcon_hover(String value) {
		this.icon_hover = value;
	}

	/**
	 * 获取是否显示
	 */
	public int getIs_display() {
		return is_display;
	}

	/**
	 * 设置是否显示
	 */
	public void setIs_display(int value) {
		this.is_display = value;
	}

	public void setChildren(List<Menu> children) {
		this.children=children;
	}
	public List<Menu> getChildren() {
		return children;
	}
}