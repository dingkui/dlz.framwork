package com.dlz.framework.db.page;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "分页响应对象")
public class Page<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "当前页码", position = 1)
	private int pageNum;
	@ApiModelProperty(value = "每页条数", position = 2)
	private int pageSize;
	@ApiModelProperty(value = "数据总条数", position = 3)
	private long total;
	 //总页数
	@ApiModelProperty(value = "总页数", position = 4)
    private int pages;
	@ApiModelProperty(value = "数据集合", position = 5)
	private List<T> rows;

    //是否有下一页
	@ApiModelProperty(value = "是否有下一页", position = 6)
    private boolean hasNextPage = false;

    public Page() {
    }

	public Page(PageInfo<T> pageInfo) {
		super();
	    setRows(pageInfo.getList());
	    setPageNum(pageInfo.getPageNum());
	    setPageSize(pageInfo.getPageSize());
	    setTotal(pageInfo.getTotal());
	    setPages(pageInfo.getPages());
	    setHasNextPage(pageInfo.isHasNextPage());
	}


	public static <T> Page<T> getInstance(PageInfo<T> pageInfo){
		return new Page<T>(pageInfo);
	}
}