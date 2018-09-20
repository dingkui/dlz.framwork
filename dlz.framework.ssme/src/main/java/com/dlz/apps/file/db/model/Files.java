package com.dlz.apps.file.db.model;

import com.dlz.framework.db.modal.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Files extends BaseModel {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public String tableName = "S_FILES";

    @JsonIgnore
    public String tableColums = "ID,D_TYPE,DATA_ID,F_NAME,F_PATH,F_SURFIX,F_ORD,F_DEL";

    /**
     * S_FILES.ID
     * 编号
     */
    private Long id;

    /**
     * S_FILES.D_TYPE
     * 业务类型
     */
    private String dType;

    /**
     * S_FILES.DATA_ID
     * 数据ID
     */
    private Long dataId;

    /**
     * S_FILES.F_NAME
     * 名称
     */
    private String fName;

    /**
     * S_FILES.F_PATH
     * 存储地址
     */
    private String fPath;
    
    /**
     * 访问地址
     */
    private String httpfPath;

    /**  
		 * 获取访问地址
		 * @return httpfPath 访问地址  
		 */
		public String getHttpfPath() {
			return httpfPath;
		}

		/** 
		 * 设置访问地址 
		 * @param httpfPath 访问地址 
		 */
		public void setHttpfPath(String httpfPath) {
			this.httpfPath = httpfPath;
		}

		/**
     * S_FILES.F_SURFIX
     * 文件后缀
     */
    private String fSurfix;

    /**
     * S_FILES.F_ORD
     * 序号
     */
    private Long fOrd;

    /**
     * S_FILES.F_DEL
     * 是否删除
     */
    private Long fDel;
    
    /**
     * S_FILES.F_SIZE
     * 文件大小
     */
    private Long fSize;
    
    /**
     * ptn_download.ZL_TYPE
     * 文件分类
     */
    private String zlType;
    /**
     * ptn_download.ZL_Id
     * 文件分类id
     */
    private String zlId;

	public String getZlId() {
		return zlId;
	}

	public void setZlId(String zlId) {
		this.zlId = zlId;
	}

	public String getZlType() {
		return zlType;
	}

	public void setZlType(String zlType) {
		this.zlType = zlType;
	}

	public Long getfSize() {
		return fSize;
	}

	public void setfSize(Long fSize) {
		this.fSize = fSize;
	}

	/**
     * S_FILES.ID
     * 编号
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the value for S_FILES.ID
     * 编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * S_FILES.D_TYPE
     * 业务类型
     */
    public String getdType() {
        return dType;
    }

    /**
     * @param dType the value for S_FILES.D_TYPE
     * 业务类型
     */
    public void setdType(String dType) {
        this.dType = dType == null ? null : dType.trim();
    }

    /**
     * S_FILES.DATA_ID
     * 数据ID
     */
    public Long getDataId() {
        return dataId;
    }

    /**
     * @param dataId the value for S_FILES.DATA_ID
     * 数据ID
     */
    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    /**
     * S_FILES.F_NAME
     * 名称
     */
    public String getfName() {
        return fName;
    }

    /**
     * @param fName the value for S_FILES.F_NAME
     * 名称
     */
    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    /**
     * S_FILES.F_PATH
     * 存储地址
     */
    public String getfPath() {
        return fPath;
    }

    /**
     * @param fPath the value for S_FILES.F_PATH
     * 存储地址
     */
    public void setfPath(String fPath) {
        this.fPath = fPath == null ? null : fPath.trim();
    }

    /**
     * S_FILES.F_SURFIX
     * 文件后缀
     */
    public String getfSurfix() {
        return fSurfix;
    }

    /**
     * @param fSurfix the value for S_FILES.F_SURFIX
     * 文件后缀
     */
    public void setfSurfix(String fSurfix) {
        this.fSurfix = fSurfix == null ? null : fSurfix.trim();
    }

    /**
     * S_FILES.F_ORD
     * 序号
     */
    public Long getfOrd() {
        return fOrd;
    }

    /**
     * @param fOrd the value for S_FILES.F_ORD
     * 序号
     */
    public void setfOrd(Long fOrd) {
        this.fOrd = fOrd;
    }

    /**
     * S_FILES.F_DEL
     * 是否删除
     */
    public Long getfDel() {
        return fDel;
    }

    /**
     * @param fDel the value for S_FILES.F_DEL
     * 是否删除
     */
    public void setfDel(Long fDel) {
        this.fDel = fDel;
    }
}