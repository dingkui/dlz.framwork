package com.dlz.framework.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dlz.framework.db.page.APageDeal;
import com.dlz.framework.db.page.Page;
import com.dlz.framework.db.page.PagePara;
import com.dlz.framework.db.page.PageUtils;
import com.dlz.framework.db.service.IBaseService;
import com.dlz.framework.db.util.DbUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> implements IBaseService<T> {
    @Autowired
    protected M dao;

    @Override
    public Page<T> findPage(PagePara<T> page) {
        PageUtils.startPage(page);
        return PageUtils.buildPage(dao.selectList(DbUtil.mkWrapper(null,page.getEq(),page.getOrders())));
    }

    @Override
    public <O> Page<O> findPage(PagePara<T> page, APageDeal<T,O> pageDeal) {
        PageUtils.startPage(page);
        QueryWrapper<T> queryWrapper = DbUtil.mkWrapper(null, page.getEq(), page.getOrders());
        pageDeal.setWrapper(queryWrapper);

        List<O> rows = new LinkedList<>();
        Page<T> dbPage = PageUtils.buildPage(dao.selectList(queryWrapper));
        Page<O> tPage = new Page<>();
        tPage.setPageNum(dbPage.getPageNum());
        tPage.setHasNextPage(dbPage.isHasNextPage());
        tPage.setPages(dbPage.getPages());
        tPage.setPageSize(dbPage.getPageSize());
        tPage.setTotal(dbPage.getTotal());
        tPage.setRows(rows);

        List<T> transRows =  dbPage.getRows();
        if(CollectionUtils.isNotEmpty(transRows)) {
            for (T obj : transRows) {
                rows.add(pageDeal.getOutBean(obj));
            }
        }

        return tPage;
    }
}
