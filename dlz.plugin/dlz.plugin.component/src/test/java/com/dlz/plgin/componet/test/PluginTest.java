package com.dlz.plgin.componet.test;

import com.dlz.comm.util.JacksonUtil;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.plgin.componet.UserPluginPile;
import com.dlz.plgin.componet.group.PluginGroup;
import com.dlz.plugin.component.ComponetInfos;
import org.junit.Before;
import org.junit.Test;

public class PluginTest {

    UserPluginPile userplugin;
    PluginGroup pluginGroup;

    @Before
    public void setUp() throws Exception {
        SpringHolder.init();
        userplugin = SpringHolder.getBean(UserPluginPile.class);
        pluginGroup = SpringHolder.getBean(PluginGroup.class);
    }

    @Test
    public void init() {
        userplugin.beforeSave(33);
        pluginGroup.stop();
        userplugin.beforeSave(44);
        System.out.println(JacksonUtil.getJson(ComponetInfos.getPluginInfos()));
    }
}
