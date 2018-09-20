package com.dlz.app.uim.bean;

public class GroupItem  implements java.io.Serializable  {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户组数据ID
     */
    private int itemid;

    /**
     * 用户组ID
     */
    private int grpId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public int getGrpId() {
		return grpId;
	}

	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}

    
}