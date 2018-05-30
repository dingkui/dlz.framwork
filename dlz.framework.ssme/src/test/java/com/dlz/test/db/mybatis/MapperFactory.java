package com.dlz.test.db.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/** 
 * Mapper Creator 
 * @author boyce 
 * @version 2014-3-28 
 */  
public enum MapperFactory {  
      
    HD,  
    HO;  
     
	private SqlSessionFactory sqlSessionFactory;  
    @SuppressWarnings("unchecked")
	public <T> T createMapper(Class<T> clazz) {  
    	 SqlSession sqlSession = sqlSessionFactory.openSession();  
    	 return sqlSession.getMapper(clazz);
//         Object mapper = sqlSession.getMapper(clazz);  
        // return (T)MapperProxy.bind(mapper, sqlSession);  
    }    
      
    private static InputStream inputStream = null;  
    static {  
        try {  
            inputStream = Resources.getResourceAsStream("mybatis/mybatis.xml");  
//            HO.sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream, HO.name());  
            HD.sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream, HD.name());  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            IOUtils.closeQuietly(inputStream);  
        }  
    }  
      
   
      
    /** 
     * Mapper Proxy  
     * executing mapper method and close sqlsession 
     * @author boyce 
     * @version 2014-4-9 
     */  
    private static class MapperProxy implements InvocationHandler {  
        private Object mapper;  
        private SqlSession sqlSession;  
          
        private MapperProxy(Object mapper, SqlSession sqlSession) {  
            this.mapper = mapper;  
            this.sqlSession = sqlSession;  
        }  
          
        private static Object bind(Object mapper, SqlSession sqlSession) {  
            return Proxy.newProxyInstance(mapper.getClass().getClassLoader(), mapper.getClass().getInterfaces(), new MapperProxy(mapper, sqlSession));  
        }  
          
        /** 
         * execute mapper method and finally close sqlSession 
         */  
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {  
            Object object = null;  
            try {  
                object = method.invoke(mapper, args);  
            } catch(Exception e) {  
                e.printStackTrace();  
            } finally {  
                sqlSession.close();  
            }  
            return object;  
        }  
    }  
}  