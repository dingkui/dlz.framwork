package com.dlz.framework.db.holder;

import com.dlz.comm.util.ExceptionUtils;
import com.dlz.framework.db.config.DlzDbProperties;
import com.dlz.framework.db.dao.IDlzDao;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.ResultMap;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.annotation.PostConstruct;
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
public class DefaultSqlholder implements ISqlHolder {
    final DlzDbProperties properties;
    final IDlzDao dao;

    public DefaultSqlholder(IDlzDao dao,DlzDbProperties properties) {
        this.dao = dao;
        this.properties = properties;
    }

    private final static String STR_SQL_JAR = "sqllist.sql.jar.";
    private final static String STR_SQL_FILE = "sqllist.sql.file.";
    private final static String STR_SQL_FOLDER = "sqllist.sql.folder.";
    static final Map<String, String> m_sqlList = new HashMap<String, String>();
    private static boolean initIng = false;

    private void readSqlPath(File file) {
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
            log.error(ExceptionUtils.getStackTrace(file.getAbsolutePath() + " 文件找不到！",e));
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(file.getAbsolutePath() + " 加载异常！",e));
        }
    }

    private void readSqlXml(InputStream is) {
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);
            for (Element sql : (List<Element>) doc.getRootElement().elements()) {
                addSqlSetting(sql.attributeValue("sqlId"),sql.getData().toString());
            }
        } catch (DocumentException e) {
            log.error(ExceptionUtils.getStackTrace(" 文件读取异常！",e));
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error(ExceptionUtils.getStackTrace(" 文件关闭异常！",e));
                }
            }
        }
    }
    @Override
    public void addSqlSetting(String sqlId,String sqlStr){
        sqlStr = clearSql(sqlStr);
        m_sqlList.put(sqlId, sqlStr);
        if (log.isDebugEnabled()){
            log.debug(sqlId + ":" + sqlStr);
        }
    }

    public void loadRsources(String path) throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] rs = resourcePatternResolver.getResources("classpath*:sql/" + path + ".sql");
        for (Resource r : rs) {
            if (log.isDebugEnabled()){
                log.debug(r.getURI().toString());
            }
            readSqlXml(r.getInputStream());
        }
    }

    @Override
    public String getSql(String key) {
        return m_sqlList.get(key);
    }

    @Override
    @PostConstruct
    public void load() {
        if (initIng || !m_sqlList.isEmpty()) {
            return;
        }
        initIng = true;

        Map<String, String> conf=new HashMap<>();
        try {
            loadRsources("framework/*");
            loadRsources("common/*");
            loadRsources("service/*/*");
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
        }
        try {
            ResourceBundle dbConfig = ResourceBundle.getBundle("db");
            for (Enumeration<String> enums = dbConfig.getKeys(); enums.hasMoreElements(); ) {
                String name = enums.nextElement();
                conf.put(name,dbConfig.getString(name).trim());
            }
        }catch (Exception e){
            log.info("db.properties not exists");
        }
        properties.getSqllist().stream().forEach(item->{
            conf.put("sqllist.sql."+item,"1");
        });
        conf.forEach((name,str)->{
            if ("1".equals(str)) {
                if (name.startsWith(STR_SQL_FILE)) {
                    String path = name.substring(STR_SQL_FILE.length() - 1).replaceAll("\\.", "/") + ".sql";
                    readSqlPath(new File(Objects.requireNonNull(DefaultSqlholder.class.getClassLoader().getResource("sql/")).getPath() + path));
                } else if (name.startsWith(STR_SQL_JAR)) {
                    try {
                        loadRsources(name.substring(STR_SQL_JAR.length()).replaceAll("\\.", "/"));
                    } catch (IOException e) {
                        log.error(ExceptionUtils.getStackTrace(e));
                    }
                } else if (name.startsWith(STR_SQL_FOLDER)) {
                    String path = name.substring(STR_SQL_FOLDER.length() - 1).replaceAll("\\.", "/");
                    readSqlPath(new File(DefaultSqlholder.class.getClassLoader().getResource("sql/").getPath() + path));
                }
            }
        });
        if(properties.isUseDbSql()){
            String sql = clearSql(properties.getSql());
            BaseParaMap baseParaMap = new BaseParaMap("");
            baseParaMap.getSqlItem().setSqlJdbc(sql);
            try {
                List<ResultMap> mapList = dao.getList(baseParaMap);
                mapList.forEach(item->addSqlSetting("key."+item.getStr("key"),item.getStr("sql")));
            }catch (Exception e){
                log.warn("取得数据库配置无效：sql="+sql);
            }

        }
        initIng = false;
    }

    @Override
    public void reLoad() {
        m_sqlList.clear();
        load();
    }
}
