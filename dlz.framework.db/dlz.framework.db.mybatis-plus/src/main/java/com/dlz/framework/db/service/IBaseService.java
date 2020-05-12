package com.dlz.framework.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dlz.framework.db.page.APageDeal;
import com.dlz.framework.db.page.Page;
import com.dlz.framework.db.page.PagePara;

public interface IBaseService<T> extends IService<T>  {
    Page<T> findPage(PagePara<T> page);

    <O> Page<O> findPage(PagePara<T> page, APageDeal<T, O> pageDeal);
}
