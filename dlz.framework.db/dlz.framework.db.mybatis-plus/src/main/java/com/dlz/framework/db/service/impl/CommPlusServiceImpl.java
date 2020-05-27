package com.dlz.framework.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.dlz.comm.json.JSONMap;
import com.dlz.comm.util.StringUtils;
import com.dlz.framework.db.service.ICommPlusService;
import com.dlz.framework.holder.SpringHolder;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * db操作通用service
 * 数据库操作时需要制定对应的bean class以指定相关表
 * bean必须定义对应的Dao 比如:
 * @Mapper
 * public interface TestDao extends SuperMapper<Test> {}
 * @author dk
 * @date 2020-03-10
 */
@Configuration
@Service
@Slf4j
@Transactional(rollbackFor = {Exception.class, RuntimeException.class})
public class CommPlusServiceImpl implements ICommPlusService {
    private Map<Class<?>, Object[]> hashtable = new Hashtable<>();

    @Bean
    BeanPostProcessor beanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean.getClass() == MapperFactoryBean.class) {
                    Class mapperInterface = ((MapperFactoryBean) bean).getMapperInterface();
                    if (!BaseMapper.class.isAssignableFrom(mapperInterface)) {
                        return bean;
                    }
                    ParameterizedType pt = (ParameterizedType) mapperInterface.getGenericInterfaces()[0];
                    Class<?> aClass = (Class<?>) pt.getActualTypeArguments()[0];
                    hashtable.put(aClass, new Object[]{mapperInterface, null});
                }
                return bean;
            }
        };
    }

    private <T> BaseMapper<T> getMapper(Class<T> clazz) {
        Object[] obj = hashtable.get(clazz);
        if (obj == null) {
            throw new RuntimeException("未找到[" + clazz.getName() + "]对应的Dao");
        }
        BaseMapper<T> BaseMapper = (BaseMapper) obj[1];
        if (BaseMapper == null) {
            BaseMapper = (BaseMapper) SpringHolder.getBean(((Class) obj[0]));
            obj[1] = BaseMapper;
        }
        return BaseMapper;
    }

    /**
     * 从list中取第一条数据返回对应List中泛型的单个结果
     *
     * @param list ignore
     * @param <E>  ignore
     * @return ignore
     */
    private static <E> E getObject(List<E> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            int size = list.size();
            if (size > 1) {
                log.warn(String.format("Warn: execute Method There are  %s results.", size));
            }
            return list.get(0);
        }
        return null;
    }

    public <T> int save(T entity) {
        return ((BaseMapper<T>) getMapper(entity.getClass())).insert(entity);
    }

    public <T> int removeById(Serializable id, Class<T> clazz) {
        return ((BaseMapper<T>) getMapper(clazz)).deleteById(id);
    }

    public <T> int removeByMap(JSONMap columnMap, Class<T> clazz) {
        return ((BaseMapper<T>) getMapper(clazz)).deleteByMap(columnMap);
    }

    public <T> int remove(Wrapper<T> queryWrapper, Class<T> clazz) {
        return ((BaseMapper<T>) getMapper(clazz)).delete(queryWrapper);
    }

    public <T> int removeByIds(Collection<? extends Serializable> idList, Class<T> clazz) {
        return ((BaseMapper<T>) getMapper(clazz)).deleteBatchIds(idList);
    }

    public <T> int updateById(T entity) {
        return ((BaseMapper<T>) getMapper(entity.getClass())).updateById(entity);
    }

    public <T> int update(T entity, Wrapper<T> updateWrapper) {
        return ((BaseMapper<T>) getMapper(entity.getClass())).update(entity, updateWrapper);
    }

    public <T> T getById(Serializable id, Class<T> clazz) {
        return ((BaseMapper<T>) getMapper(clazz)).selectById(id);
    }

    public <T> List<T> listByIds(Collection<? extends Serializable> idList, Class<T> clazz) {
        return ((BaseMapper<T>) getMapper(clazz)).selectBatchIds(idList);
    }

    @Override
    public <T> List<T> listByMap(JSONMap columnMap, Class<T> clazz) {
        Wrapper<T> queryWrapper =new QueryWrapper<T>();
        Set<Map.Entry<String, Object>> entries = columnMap.entrySet();
        for (Map.Entry<String, Object> entrie : entries) {
            if(StringUtils.isNotEmpty(entrie.getValue())){
                ((QueryWrapper)queryWrapper).eq(entrie.getKey(),entrie.getValue());
            }
        }
        return getMapper(clazz).selectList(queryWrapper);
    }

    public <T> List<T> listByMap(Map<String, Object> columnMap, Class<T> clazz) {
        return ((BaseMapper<T>) getMapper(clazz)).selectByMap(columnMap);
    }

    public <T> T getOne(Wrapper<T> queryWrapper, Class<T> clazz, boolean throwEx) {
        if (throwEx) {
            return ((BaseMapper<T>) getMapper(clazz)).selectOne(queryWrapper);
        }
        return getObject(((BaseMapper<T>) getMapper(clazz)).selectList(queryWrapper));

    }

    public <T> int count(Wrapper<T> queryWrapper, Class<T> clazz) {
        return SqlHelper.retCount(((BaseMapper<T>) getMapper(clazz)).selectCount(queryWrapper));
    }

    public <T> List<T> list(Wrapper<T> queryWrapper, Class<T> clazz) {
        return ((BaseMapper<T>) getMapper(clazz)).selectList(queryWrapper);

    }

    public <T> List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper, Class<T> clazz) {
        return ((BaseMapper<T>) getMapper(clazz)).selectMaps(queryWrapper);
    }
}
