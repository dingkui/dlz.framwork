package com.dlz.test.framework.db.config;

import com.dlz.framework.db.config.DlzDbConfig;
import com.dlz.framework.db.dao.IDlzDao;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.ResultMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: dk
 * @date: 2020-10-15
 */
@Slf4j
@Configuration
public class DlzDbConfigs extends DlzDbConfig {

//    @Bean
//    @DependsOn({"dbInfo"})
//    public ICommService commService() {
//        return new CommServiceImpl();
//    }

    @Bean(name = "dlzDao")
    public IDlzDao dlzDao() {
        return new IDlzDao(){
            @Override
            public List<ResultMap> getList(BaseParaMap paraMap) {
                ResultMap resultMap = new ResultMap();
                resultMap.put("cnt",0);
                List<ResultMap> resultMaps = new ArrayList<>();
                resultMaps.add(resultMap);
                return resultMaps;
            }

            @Override
            public int getCnt(BaseParaMap paraMap) {
                return 0;
            }

            @Override
            public int updateSql(BaseParaMap paraMap) {
                return 0;
            }

            @Override
            public HashMap<String, Integer> getTableColumsInfo(String tableName) {
                return new HashMap<>();
            }
        };
    }
}
