package com.dlz.framework.db;

import com.dlz.comm.exception.DbException;
import com.dlz.framework.cache.CacheHolder;
import com.dlz.framework.db.enums.DbTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.*;
import java.util.*;

/**
 * 数据库配置信息
 *
 * @author dingkui 2011-08-12 v1.0
 * history dingkui 2012-05-07 v1.1
 * 修改sql文件路径取得方式，以便执行init时可以刷新内存
 */
@Slf4j
public class DbInfo {
    private static ResourceBundle dbConfig;
    private final static String NAME_DB_CONFIG = "db";
    private final static String STR_SQL_JAR = "sqllist.sql.jar.";
    private final static String STR_SQL_FILE = "sqllist.sql.file.";
    private final static String STR_SQL_FOLDER = "sqllist.sql.folder.";
    private final static String STR_DBTYPE = "dbtype";
    private static final Map<String, String> m_dbset = new HashMap<String, String>();
    private static final Map<String, String> m_sqlList = new HashMap<String, String>();
    private static DbTypeEnum dbtype = DbTypeEnum.ORACLE;
    public static DbTypeEnum getDbtype() {
        return dbtype;
    }

    private static boolean initIng = false;

    public DbInfo() {
        try {
            init();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private static void init() throws IOException {
        if (initIng || dbConfig != null) {
            return;
        }
        initIng = true;
        dbConfig = ResourceBundle.getBundle(NAME_DB_CONFIG);
        loadRsources("framework/*");
        loadRsources("common/*");
        loadRsources("service/*/*");
        for (Enumeration<String> enums = dbConfig.getKeys(); enums.hasMoreElements(); ) {
            String name = enums.nextElement();
            String str = dbConfig.getString(name).trim();
            if (STR_DBTYPE.equals(name)) {
                dbtype = DbTypeEnum.valueOf(str.toUpperCase());
                continue;
            }
            if ("1".equals(str)) {
                if (name.startsWith(STR_SQL_FILE)) {
                    String path = name.substring(STR_SQL_FILE.length() - 1).replaceAll("\\.", "/") + ".sql";
                    readSqlPath(new File(Objects.requireNonNull(DbInfo.class.getClassLoader().getResource("sql/")).getPath() + path));
                } else if (name.startsWith(STR_SQL_JAR)) {
                    loadRsources(name.substring(STR_SQL_JAR.length()).replaceAll("\\.", "/"));
                } else if (name.startsWith(STR_SQL_FOLDER)) {
                    String path = name.substring(STR_SQL_FOLDER.length() - 1).replaceAll("\\.", "/");
                    readSqlPath(new File(DbInfo.class.getClassLoader().getResource("sql/").getPath() + path));
                }
            }
            m_dbset.put(name, str);
        }
        if (log.isDebugEnabled()){
            log.debug("dbsettinhs:" + m_dbset);
            log.debug("sqlList:" + m_sqlList);
        }
        initIng = false;
    }

    private static void readSqlPath(File file) {
        try {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File fi : files) {
                    readSqlPath(fi);
                }
            } else {
                if (file.getAbsolutePath().endsWith(".sql")) {
                    log.info(file.getPath());
                    readSqlXml(new FileInputStream(file));
                }
            }
        } catch (FileNotFoundException e) {
            log.error(file.getAbsolutePath() + " 文件找不到！", e);
        } catch (Exception e) {
            log.error(file.getAbsolutePath() + " 加载异常！", e);
        }
    }

    private static void readSqlXml(InputStream is) {
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);
            for (Element sql : (List<Element>) doc.getRootElement().elements()) {
                addSqlSetting(sql.attributeValue("sqlId"),sql.getData().toString());
            }
        } catch (DocumentException e) {
            log.error(" 文件读取异常！", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error(" 文件关闭异常！", e);
                }
            }
        }
    }

    public static void addSqlSetting(String sqlId,String sqlStr){
        sqlStr = sqlStr.replaceAll("--.*", "");
        sqlStr = sqlStr.replaceAll("[\\s]+", " ");
        m_sqlList.put(sqlId, sqlStr);
        if (log.isDebugEnabled()){
            log.debug(sqlId + ":" + sqlStr);
        }
    }

    public void main(String[] args) throws IOException {
        loadRsources("common/*");
    }

    public static void loadRsources(String path) throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] rs = resourcePatternResolver.getResources("classpath*:sql/" + path + ".sql");
        for (Resource r : rs) {
            if (log.isDebugEnabled()){
                log.debug(r.getURI().toString());
            }
            readSqlXml(r.getInputStream());
        }
    }

    public static String getSql(String key) {
        if (key == null) {
            throw new DbException("输入的sql为空！", 1002);
        }
        if(key.matches("[\\s]*(?i)select.*") ){
            return key;
        }
        if (!key.startsWith("key.")) {
            throw new DbException("sqlKey格式无效:" + key, 1002);
        }
        String sql = m_sqlList.get(key + dbtype.getEnd());
        if (sql == null) {
            sql = m_sqlList.get(key);
        }
        if (sql == null) {
            throw new DbException("sqlKey未配置：" + key, 1002);
        }
        return sql;
    }

    public static void appendInfoToSql(String key, String appendInfo) {
        if (key == null) {
            throw new DbException("输入的sql为空！", 1002);
        }
        String sql = key;
        if (key.startsWith("key.")) {
            sql = m_sqlList.get(key + dbtype.getEnd());
            if (sql == null) {
                sql = m_sqlList.get(key);
            }
        }
        if (sql == null) {
            throw new DbException("输入的sqlKey找不到相应的sql语句！key=" + key, 1002);
        }
        if (sql.indexOf(appendInfo) == -1) {
            m_sqlList.put(key + dbtype.getEnd(), sql + " " + appendInfo);
        }
    }

    public static String getDbset(String key) {
        if (key == null) {
            return null;
        }
        String value = key;
        value = m_dbset.get(key.trim());
        if (value != null) {
            return value.trim();
        } else {
            return null;
        }
    }

    public static void reload() {
        dbConfig = null;
        CacheHolder.clearAll();
        m_sqlList.clear();
        try {
            init();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
