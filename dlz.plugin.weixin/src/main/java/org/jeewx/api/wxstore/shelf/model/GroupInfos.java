package org.jeewx.api.wxstore.shelf.model;

import java.util.List;

public class GroupInfos {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	// 分组ID
	private List<GroupsInfo> groups;

	public List<GroupsInfo> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupsInfo> groups) {
		this.groups = groups;
	}
}
