package com.dlz.framework.ssme.db.model;

import com.dlz.framework.db.modal.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MenuDataOpt extends BaseModel {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public String tableName = "T_P_MENU_DATA_OPT";

    @JsonIgnore
    public String tableColums = "MDO_ID,MEMU_ID,ROLE_IDS,PREV_STATUS,CURRENT_STATUS,NEXT_STATUS,EXT1,EXT2,EXT3";

    /**
     * T_P_MENU_DATA_OPT.MDO_ID
     * ID
     */
    private Long mdoId;

    /**
     * T_P_MENU_DATA_OPT.MEMU_ID
     * 菜单ID
     */
    private Long memuId;

    /**
     * T_P_MENU_DATA_OPT.ROLE_IDS
     * 访问角色
     */
    private String roleIds;

    /**
     * T_P_MENU_DATA_OPT.PREV_STATUS
     * 上一步状态
     */
    private String prevStatus;

    /**
     * T_P_MENU_DATA_OPT.CURRENT_STATUS
     * 当前状态
     */
    private String currentStatus;

    /**
     * T_P_MENU_DATA_OPT.NEXT_STATUS
     * 下一步状态
     */
    private String nextStatus;

    /**
     * T_P_MENU_DATA_OPT.EXT1
     * 扩展字段1
     */
    private String ext1;

    /**
     * T_P_MENU_DATA_OPT.EXT2
     * 扩展字段2
     */
    private String ext2;

    /**
     * T_P_MENU_DATA_OPT.EXT3
     * 扩展字段3
     */
    private String ext3;

    /**
     * T_P_MENU_DATA_OPT.MDO_ID
     * ID
     */
    public Long getMdoId() {
        return mdoId;
    }

    /**
     * @param mdoId the value for T_P_MENU_DATA_OPT.MDO_ID
     * ID
     */
    public void setMdoId(Long mdoId) {
        this.mdoId = mdoId;
    }

    /**
     * T_P_MENU_DATA_OPT.MEMU_ID
     * 菜单ID
     */
    public Long getMemuId() {
        return memuId;
    }

    /**
     * @param memuId the value for T_P_MENU_DATA_OPT.MEMU_ID
     * 菜单ID
     */
    public void setMemuId(Long memuId) {
        this.memuId = memuId;
    }

    /**
     * T_P_MENU_DATA_OPT.ROLE_IDS
     * 访问角色
     */
    public String getRoleIds() {
        return roleIds;
    }

    /**
     * @param roleIds the value for T_P_MENU_DATA_OPT.ROLE_IDS
     * 访问角色
     */
    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds == null ? null : roleIds.trim();
    }

    /**
     * T_P_MENU_DATA_OPT.PREV_STATUS
     * 上一步状态
     */
    public String getPrevStatus() {
        return prevStatus;
    }

    /**
     * @param prevStatus the value for T_P_MENU_DATA_OPT.PREV_STATUS
     * 上一步状态
     */
    public void setPrevStatus(String prevStatus) {
        this.prevStatus = prevStatus == null ? null : prevStatus.trim();
    }

    /**
     * T_P_MENU_DATA_OPT.CURRENT_STATUS
     * 当前状态
     */
    public String getCurrentStatus() {
        return currentStatus;
    }

    /**
     * @param currentStatus the value for T_P_MENU_DATA_OPT.CURRENT_STATUS
     * 当前状态
     */
    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus == null ? null : currentStatus.trim();
    }

    /**
     * T_P_MENU_DATA_OPT.NEXT_STATUS
     * 下一步状态
     */
    public String getNextStatus() {
        return nextStatus;
    }

    /**
     * @param nextStatus the value for T_P_MENU_DATA_OPT.NEXT_STATUS
     * 下一步状态
     */
    public void setNextStatus(String nextStatus) {
        this.nextStatus = nextStatus == null ? null : nextStatus.trim();
    }

    /**
     * T_P_MENU_DATA_OPT.EXT1
     * 扩展字段1
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * @param ext1 the value for T_P_MENU_DATA_OPT.EXT1
     * 扩展字段1
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    /**
     * T_P_MENU_DATA_OPT.EXT2
     * 扩展字段2
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * @param ext2 the value for T_P_MENU_DATA_OPT.EXT2
     * 扩展字段2
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    /**
     * T_P_MENU_DATA_OPT.EXT3
     * 扩展字段3
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * @param ext3 the value for T_P_MENU_DATA_OPT.EXT3
     * 扩展字段3
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }
}