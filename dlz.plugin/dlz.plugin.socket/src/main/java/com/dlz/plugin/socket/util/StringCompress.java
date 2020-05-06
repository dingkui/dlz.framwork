package com.dlz.plugin.socket.util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;  
  
public class StringCompress {  
	private static String cataName="UTF-8";
    public static final byte[] compress(String paramString) {  
        if (paramString == null)  
            return null;  
        ByteArrayOutputStream byteArrayOutputStream = null;  
        ZipOutputStream zipOutputStream = null;  
        byte[] arrayOfByte;  
        try {  
            byteArrayOutputStream = new ByteArrayOutputStream();  
            zipOutputStream = new ZipOutputStream(byteArrayOutputStream);  
            zipOutputStream.putNextEntry(new ZipEntry("0"));  
            zipOutputStream.write(paramString.getBytes(cataName));
            zipOutputStream.closeEntry();  
            arrayOfByte = byteArrayOutputStream.toByteArray();  
        } catch (IOException localIOException5) {  
            arrayOfByte = null;  
        } finally {  
            if (zipOutputStream != null)  
                try {  
                    zipOutputStream.close();  
                } catch (IOException localIOException6) {  
            }  
            if (byteArrayOutputStream != null)  
                try {  
                    byteArrayOutputStream.close();  
                } catch (IOException localIOException7) {  
            }  
        }  
        return arrayOfByte;  
    }  
  
    public static final String decompress(byte[] paramArrayOfByte) {  
        if (paramArrayOfByte == null)  
            return null;  
        ByteArrayOutputStream byteArrayOutputStream = null;  
        ByteArrayInputStream byteArrayInputStream = null;  
        ZipInputStream zipInputStream = null;  
        String str;  
        try {  
            byteArrayOutputStream = new ByteArrayOutputStream();  
            byteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);  
            zipInputStream = new ZipInputStream(byteArrayInputStream);  
            zipInputStream.getNextEntry();  
            byte[] arrayOfByte = new byte[1024];  
            int i = -1;  
            while ((i = zipInputStream.read(arrayOfByte)) != -1)  
                byteArrayOutputStream.write(arrayOfByte, 0, i);  
            str = byteArrayOutputStream.toString(cataName);  
        } catch (IOException localIOException7) {  
            str = null;  
        } finally {  
            if (zipInputStream != null)  
                try {  
                    zipInputStream.close();  
                } catch (IOException localIOException8) {  
                }  
            if (byteArrayInputStream != null)  
                try {  
                    byteArrayInputStream.close();  
                } catch (IOException localIOException9) {  
                }  
            if (byteArrayOutputStream != null)  
                try {  
                    byteArrayOutputStream.close();  
                } catch (IOException localIOException10) {  
            }  
        }  
        return str;  
    }
    
    public static final String decompress(String paramArrayOfByte) throws UnsupportedEncodingException {  
			return decompress(paramArrayOfByte.getBytes(cataName));
    }
    
    public static final String compressStr(String paramString) throws UnsupportedEncodingException {  
        return new String(compress(paramString),cataName);  
    }  
    
    public static void main(String[] args) throws UnsupportedEncodingException {
    	String aa="{\"result\":1,\"message\":null,\"data\":{\"pageSize\":10,\"totalCount\":5,\"currentPageNo\":1,\"result\":[{\"image\":null,\"cat_name\":\"\",\"page_title\":\"系统升级调整通知\",\"sort\":1,\"site_code\":100000,\"title\":\"\",\"content\":\"<p style=\\\"text-align:center\\\"><span style=\\\"font-family: 宋体, SimSun;\\\"><strong><span style=\\\"font-size: 21px;\\\">系统升级调整通知</span></strong></span></p><p style=\\\"text-indent:28px\\\"><span style=\\\"font-family: 宋体, SimSun; font-size: 16px;\\\">因考虑到各区域运营中心在各辖区中市场管理的规范化及统一性，公司全力支持与配合各区域自主制定经营管理规则及价格体系政策。为了避免线上线下出现两套价格政策体系，平台于11月26日开始暂停健康家商城线上合伙人销售体系，商城已完成系统升级调整。升级调整后：</span></p><p><span style=\\\"font-size: 16px; font-family: 宋体, SimSun;\\\">1、<span style=\\\"font-size: 16px;\\\">现在不论是商城的合伙人还是普通会员，购买商品的价格统一为会员价，健康家商城不再有优惠返利；</span></span></p><p><span style=\\\"font-family: 宋体, SimSun; font-size: 16px;\\\">2、<span style=\\\"font-family: 宋体, SimSun;\\\">为了更有效的管理和服务好市场，健康家线上合伙人将统一交由各区域运营中心直属管理与服务。政策调整之日起，健康家合伙人可以直接联系小助手，帮忙对接到各区域运营中心负责人，新的合伙政策将由各区域自行商议制定并授权经营；</span></span></p><p><span style=\\\"font-family: 宋体, SimSun; font-size: 16px;\\\">3、暂无区域运营中心的健康家合伙人，华东除苏州、山东外其他四区（上海、安徽、浙江、福建） 直接由格林森华东市场总经理负责对接与管理，此外其他区域直接由格林森市场业务总经理负责对接与管理；</span></p><p><span style=\\\"font-family: 宋体, SimSun; font-size: 16px;\\\">期间，公司客服部门将对原合伙人进行密切关注，如有任何疑问请联系健康家小助手。</span></p><p><br/></p>\",\"hit\":0,\"lastmodified\":1511506800,\"cat_id\":26,\"id\":1380,\"add_time\":1511506800,\"sys_lock\":0},{\"image\":null,\"cat_name\":\"\",\"page_title\":\"关于取消合伙人自行开发合伙人的相关通知\",\"sort\":1,\"site_code\":100000,\"title\":\"\",\"content\":\"<p style=\\\"margin-top:42px;margin-right:0;margin-bottom:21px;margin-left:0;text-indent:0;text-autospace:ideograph-numeric;text-align:center\\\"><strong><span style=\\\"font-family: 宋体;font-size: 21px\\\"><span style=\\\"font-family:宋体\\\">关于取消合伙人自行开发合伙人的相关通知</span></span></strong></p><p style=\\\"text-indent: 32px;line-height: 150%\\\"><span style=\\\";font-family:宋体;line-height:150%;font-size:16px\\\">为了切实落实区域运营中心的规范管理，依据全国市场管理及运营的相关制度管理规定，特发布如下通知：</span></p><p style=\\\"text-indent: 32px;line-height: 150%\\\"><span style=\\\"font-family:宋体;font-size:16px\\\">1、</span><span style=\\\";font-family:宋体;line-height:150%;font-size:16px\\\"><span style=\\\"font-family:宋体\\\">公司将于</span>2017年10月20日00:00正式取消藻钙云和健康家商城各类合伙人自行开发合伙人的相关权利；</span></p><p style=\\\"text-indent: 32px;line-height: 150%\\\"><span style=\\\"font-family:宋体;font-size:16px\\\">2、</span><span style=\\\";font-family:宋体;line-height:150%;font-size:16px\\\">各地区合伙人的开发将由所在区域运营中心根据市场需要报公司审核批准名额后，进行有序开发；</span></p><p style=\\\"text-indent: 32px;line-height: 150%\\\"><span style=\\\"font-family:宋体;font-size:16px\\\">3、</span><span style=\\\";font-family:宋体;line-height:150%;font-size:16px\\\">各地区合伙人的开发必须报所在区域运营中心审核，同时接受运营中心的管理，享受运营中心提供的服务和支持，共享部分市场利益。</span></p><p style=\\\"text-indent: 32px;line-height: 150%\\\"><span style=\\\"font-family:宋体;font-size:16px\\\">4、</span><span style=\\\";font-family:宋体;line-height:150%;font-size:16px\\\"><span style=\\\"font-family:宋体\\\">原通知《关于合伙人套餐价格调整的提前通知》将于</span>2017年10月20日00:00正式执行。</span></p><p style=\\\"line-height: 150%\\\"><span style=\\\";font-family:宋体;line-height:150%;font-size:16px\\\">&nbsp;</span></p><p style=\\\"line-height: 150%\\\"><strong><span style=\\\"font-family: 宋体;line-height: 150%;font-size: 16px\\\">附：《关于合伙人套餐价格调整的提前通知》</span></strong></p><p style=\\\"text-indent:32px;line-height:150%\\\"><span style=\\\";font-family:宋体;line-height:150%;font-size:16px\\\">一、</span><span style=\\\";font-family:宋体;line-height:150%;font-size:16px\\\"><span style=\\\"font-family:宋体\\\">原城市合伙人套餐价格</span>13800元/个、体验店</span><span style=\\\";font-family:宋体;line-height:150%;font-size:16px\\\">合伙人</span><span style=\\\";font-family:宋体;line-height:150%;font-size:16px\\\"><span style=\\\"font-family:宋体\\\">套餐价格</span>58800元/个保留至2017年10月19日。</span></p><p style=\\\"text-indent:32px;line-height:150%\\\"><span style=\\\";font-family:宋体;font-size:16px\\\"><span style=\\\"font-family:宋体\\\">二、至</span>2017年10月20日始，城市合伙人套餐价格调整为15880元/个，体验店合伙人套餐价格调整为61880元/ 个。</span></p><p style=\\\"line-height: 150%\\\"><span style=\\\";font-family:宋体;line-height:150%;font-size:16px\\\">&nbsp;</span></p><p style=\\\"text-indent: 32px;line-height: 150%\\\"><span style=\\\";font-family:宋体;line-height:150%;font-size:16px\\\">&nbsp;</span></p><p style=\\\"text-align:right;line-height:150%\\\"><span style=\\\"font-family: 宋体;line-height: 150%;font-size: 16px\\\">特此通知，望悉知。</span></p><p style=\\\"text-align:right;line-height:150%\\\"><span style=\\\"font-family: 宋体;line-height: 150%;font-size: 16px\\\">&nbsp;</span></p><p style=\\\"text-align:right;line-height:150%\\\"><span style=\\\"font-family: 宋体;line-height: 150%;font-size: 16px\\\">格林森健康家运营管理中心</span></p><p style=\\\"text-align:right;line-height:150%\\\"><span style=\\\"font-family: 宋体;line-height: 150%;font-size: 16px\\\"><span style=\\\"font-family:宋体\\\">二</span>O<span style=\\\"font-family:宋体\\\">一七年十月十八日</span></span></p><p><br/></p>\",\"hit\":0,\"lastmodified\":1508382519,\"cat_id\":26,\"id\":1361,\"add_time\":1508382519,\"sys_lock\":0},{\"image\":null,\"cat_name\":\"\",\"page_title\":\"关于商城会员账户管理的通知\",\"sort\":1,\"site_code\":100000,\"title\":\"\",\"content\":\"<p style=\\\"white-space: normal;\\\"><span style=\\\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;; font-size: 18px;\\\">为优化商城会员体系，经公司研究决定，公布并实施如下管理政策：</span></p><p style=\\\"white-space: normal;\\\"><span style=\\\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;; font-size: 18px;\\\">一、<strong>近30日内</strong>，会员账户<strong>自购消费单笔订单未满200元</strong>将被系统判定为不活跃用户，一经判定系统将自动冻结该账户；</span></p><p style=\\\"white-space: normal;\\\"><span style=\\\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;; font-size: 18px;\\\">二、首次合伙人账户判定将于<strong>2017年9月15日00:00</strong>启动，即刻生效；</span></p><p style=\\\"white-space: normal;\\\"><span style=\\\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;; font-size: 18px;\\\">三、账户激活方法如下：<strong>自购消费单笔订单满200元及以上</strong>，即可自动激活账户；</span></p><p style=\\\"white-space: normal;\\\"><span style=\\\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;; font-size: 18px;\\\">如有不明，请联系<strong>400-008-2019</strong>；微信：健康家小助手</span></p><p style=\\\"white-space: normal;\\\"><br/></p><p><br/></p>\",\"hit\":0,\"lastmodified\":1505460176,\"cat_id\":26,\"id\":1340,\"add_time\":1505460096,\"sys_lock\":0},{\"image\":\"http://shop.jkj51.com/shop/statics/attachment/spec/2017/6/30/17//46372333.png\",\"cat_name\":\"\",\"page_title\":\"优惠好消息！！！\",\"sort\":1,\"site_code\":100000,\"title\":\"优惠券\",\"content\":\"<p>【重大利好通知】</p><p>经公司研究决定，7月1号开始，</p><p>1、格林森健康家商城优惠券不需要豆豆即可领取</p><p>2、领取不限量，任性领</p><p>优惠券规则</p><p>1. &nbsp;单个订单仅能使用一张优惠券，不可叠加使用</p><p>2. &nbsp;每张优惠券只能使用一次；</p><p>备注：如您有多张优惠券的话, 每次订购也只能使用一张；</p><p>3. 在使用优惠卷下单后，取消订单，优惠劵将作废使用</p><p>4.以上优惠券使用范围不包括合伙人套餐、豆乐园蜂蜜系列产品</p><p>5.以上优惠券有效期为17年7月1号起，请在券面显示有效期内使用,超过券面有效期，优惠劵将作废。</p><p>6.以上所有优惠券在限定时间内均不限量领取</p><p><br/></p><p><br/></p><p><br/></p>\",\"hit\":0,\"lastmodified\":1503755573,\"cat_id\":26,\"id\":1260,\"add_time\":1498816146,\"sys_lock\":0},{\"image\":null,\"cat_name\":\"\",\"page_title\":\"科普丨藻钙是什么？\",\"sort\":1,\"site_code\":100000,\"title\":\"\",\"content\":\"<p style=\\\"margin-top:5px;margin-right:0;margin-bottom:5px;margin-left:0;text-indent:0;text-align:center;line-height:26px\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 16px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">藻钙三字经</span></span></p><p style=\\\"margin-top:5px;margin-right:0;margin-bottom:5px;margin-left:0;text-indent:0;text-align:center;line-height:26px\\\"><span style=\\\"color: rgb(38, 38, 38);letter-spacing: 0;font-size: 16px\\\">&nbsp;</span></p><p style=\\\"margin-top:5px;margin-right:0;margin-bottom:5px;margin-left:0;text-indent:0;text-align:center;line-height:26px\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 14px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">物之初，性本善。人用之，悟其果。</span></span></p><p style=\\\"margin-top:5px;margin-right:0;margin-bottom:5px;margin-left:0;text-indent:0;text-align:center;line-height:26px\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 14px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">念思之，感客至。吾再来，新鲜多。</span></span></p><p style=\\\"margin-top:5px;margin-right:0;margin-bottom:5px;margin-left:0;text-indent:0;text-align:center;line-height:26px\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 14px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">灰颗粒，小身体。泥土香，自然气。</span></span></p><p style=\\\"margin-top:5px;margin-right:0;margin-bottom:5px;margin-left:0;text-indent:0;text-align:center;line-height:26px\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 14px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">原生态，绿色意。众小粒，团结性。</span></span></p><p style=\\\"margin-top:5px;margin-right:0;margin-bottom:5px;margin-left:0;text-indent:0;text-align:center;line-height:26px\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 14px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">置柜里，杠杠滴。新装修，莫着急。</span></span></p><p style=\\\"margin-top:5px;margin-right:0;margin-bottom:5px;margin-left:0;text-indent:0;text-align:center;line-height:26px\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 14px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">除甲醛，藻钙吸。产品多，乱了意。</span></span></p><p style=\\\"margin-top:5px;margin-right:0;margin-bottom:5px;margin-left:0;text-indent:0;text-align:center;line-height:26px\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 14px;background: rgb(255, 255, 255)\\\">...</span></p><p style=\\\"margin-top:5px;margin-right:0;margin-bottom:7px;margin-left:0;text-indent:0;padding:0 0 0 0 ;text-align:center;line-height:26px\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 21px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">藻钙</span>&nbsp;</span><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 12px;background: rgb(255, 255, 255)\\\">/ Zao Gai</span></p><p style=\\\"margin-top:5px;margin-right:0;margin-bottom:7px;margin-left:0;text-indent:0;padding:0 0 0 0 ;text-align:center;line-height:26px\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 14px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">一种无机型生态材料</span></span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; padding: 0px; line-height: 26px; text-align: center;\\\"><strong><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 14px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">基础信息</span> &nbsp; &nbsp;Basic Info</span></strong></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">简介：藻钙是一种生态型无机材料，属于新型绿色生态环保材料。由藻类矿物晶体和钙质矿物晶体等十三种无机晶体合成改性培养而成。具备寻常材料无法达到的有效分解甲醛、甲苯等有害物质的效果。</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">&nbsp;</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">组成：藻类矿物晶体、含钙晶体、凹凸矿物、光触媒及改性组分。</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">&nbsp;</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">工艺：通过对含有藻类晶体、钙质晶体、凹凸晶体的矿物及副产物通过物理活化，以水化凝胶、晶体转晶、催化酶负载、成型参数等核心控制技术，依撑合理分子结构及孔径大小、表面极性以及光触媒催化氧化还原协同技术，形成粉末状、涂覆状、板材状、颗粒状及异型状的系列产品。</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; line-height: 21px; text-align: center;\\\"><span style=\\\"color: rgb(38, 38, 38);letter-spacing: 0;font-size: 16px\\\">&nbsp;</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; padding: 0px; line-height: 26px; text-align: center;\\\"><strong><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 14px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">三大特性</span> &nbsp; &nbsp;The three major characteristics</span></strong></p><p style=\\\"margin: 5px 0px; text-indent: 0px; line-height: 21px; text-align: center;\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 12px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">当呼吸功能不断进行时，空气中有害气体也参与到这个过程中，其独有的微孔管状能够有效将甲醛、甲苯、游离重金属离子等有害物捕捉，不再存在残留有害物质释放到空气中，循环往复，室内环境中有害物不复存在。</span></span><span style=\\\"color: rgb(38, 38, 38);letter-spacing: 0;font-size: 16px\\\">&nbsp;</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"letter-spacing: 0;font-size: 16px\\\">1/</span><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">分解净化性：藻钙晶体内部促进媒与表面光触媒形成内外连续相，持续催化降解形成</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">·OH</span><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">（氢氧自由基），</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">·O2</span><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">（过氧自由基）</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">&nbsp;</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"letter-spacing: 0;font-size: 16px\\\">2/</span><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">负离子诱发性：负离子的诱发是基于组分中存在的中价组分，产生微电场，当空气中的水分子进入时，水分子被电离成带正电的氢离子和带负电的氢氧根离子，带正电的氢离子相互结合成氢气，释放到空气中。带负电的氢氧根离子与水结合生成负离子水，以气态形式释放到空气中。</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">&nbsp;</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"letter-spacing: 0;font-size: 16px\\\">3/</span><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">抗菌防霉祛味性：净化祛味是基于催化吸附分解原理，特定的微孔结构决定了其吸附性，同时藻钙材料表面连生型羟基对空间挥发的极性分子具有很强的亲合吸附捕捉，在藻钙光促酶下形成的</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">·OH</span><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">，</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">·O2</span><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">过氧自有基持续破坏</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">C—C</span><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">键、</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">C—H</span><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">键、</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">C—N</span><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">键、</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">C—O</span><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">键、</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">O—H</span><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">键</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">&nbsp;</span><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">、</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">N—H</span><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">而具备净化、祛味持</span><span style=\\\"font-family: &#39;Helvetica Neue&#39;; color: rgb(38, 38, 38); letter-spacing: 0px; font-size: 16px; background: rgb(255, 255, 255);\\\">久性，具有吸附捕捉快、无饱和、无脱附、祛味显着的特点。</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; line-height: 26px; text-align: center;\\\"><span style=\\\"letter-spacing: 0;font-size: 16px\\\">&nbsp;</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; padding: 0px; line-height: 26px; text-align: center;\\\"><strong><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 14px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">性能特点</span> &nbsp; &nbsp;Performance characteristics</span></strong></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">藻钙复合晶体将最具人体亲和力的钙质晶体与优越活性炭</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">5000</span><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">～</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">6000</span><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">倍环保功能性的藻类晶体、以及多种功能性促进酶的合成与培养，使其兼具优异的物理性能及显著的功能性。</span><span style=\\\"letter-spacing: 0;font-size: 16px\\\">&nbsp;</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">综合性能：藻类矿物晶体、含钙晶体、凹凸矿物、光触媒及改性组分。</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">呼吸自调湿性：通过对含有藻类晶体、钙质晶体、凹凸晶体的矿物及副产物通过物理活化，以水化凝胶、晶体转晶、催化酶负载、成型参数等核心控制技术，依撑合理分子结构及孔径大小、表面极性以及光触媒催化氧化还原协同技术，形成粉末状、涂覆状、板材状、颗粒状及异型状的系列产品。</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">负离子自发性：负氧离子</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"font-family: 宋体;letter-spacing: 0;font-size: 16px\\\">环境友好性：藻钙复合晶体中的结晶水稳定存在而具有防火性，同时钙质晶体与藻类晶体皆为无机晶体中最具亲和力、最环保的晶体类，具有零放射、零辐射的安全性，此外，表面的均匀分散的微孔而赋予吸音降噪与保温隔热性能。</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"letter-spacing: 0;font-size: 16px\\\">&nbsp;</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; padding: 0px; line-height: 26px; text-align: center;\\\"><strong><span style=\\\"font-family: 宋体;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 14px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:宋体\\\">常见问题</span></span><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 14px;background: rgb(255, 255, 255)\\\">&nbsp;&nbsp; &nbsp;question</span></strong></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"color: rgb(0, 112, 192);\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;; letter-spacing: 0px; font-size: 16px; background: rgb(255, 255, 255);\\\">Q藻钙是什么材料做的？</span><span style=\\\"letter-spacing: 0px; font-size: 16px;\\\">&nbsp;</span></span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 12px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">藻钙产品由多种藻类矿物，钙质矿物，催化微粉，氧化微粉等组合而成，不同需求，不同功能，产品的主要成分配比各不一样。</span></span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; line-height: 26px; text-align: center;\\\"><span style=\\\"color: rgb(38, 38, 38);letter-spacing: 0;font-size: 16px\\\">&nbsp;</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;; letter-spacing: 0px; font-size: 16px; color: rgb(0, 112, 192); background: rgb(255, 255, 255);\\\">Q藻钙产品可以使用多长时间？</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 12px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">藻钙产品未开封有效期为</span>3年，</span><span style=\\\"font-family: 宋体;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 12px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:宋体\\\">最佳净化期为</span></span><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 12px;background: rgb(255, 255, 255)\\\">6</span><span style=\\\"font-family: 宋体;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 12px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:宋体\\\">个月</span></span><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 12px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">，其使用时间时长远大于市面上其他净化类产品。</span></span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; line-height: 25px; text-align: center;\\\"><span style=\\\"color: rgb(38, 38, 38);letter-spacing: 0;font-size: 18px\\\">&nbsp;</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;; letter-spacing: 0px; font-size: 16px; color: rgb(0, 112, 192); background: rgb(255, 255, 255);\\\">Q藻钙与活性炭、硅藻纯、纳米矿晶有区别吗？</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; line-height: 25px; text-align: center;\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 12px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">竹炭、活性炭之类只能吸附甲醛，并且吸附饱和之后还会释放甲醛造成的二次污染，效果十分有限。而我们格林森特的藻钙宝宝，通过光催化原理持续分解甲醛，净化空气，释放负氧，是能真正分解甲醛的材料哦！</span></span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; line-height: 26px; text-align: center;\\\"><span style=\\\"color: rgb(38, 38, 38);letter-spacing: 0;font-size: 16px\\\">&nbsp;</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; text-align: center;\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;; letter-spacing: 0px; font-size: 16px; color: rgb(0, 112, 192); background: rgb(255, 255, 255);\\\">Q藻钙对雾霾有效果吗？</span></p><p style=\\\"margin: 5px 0px; text-indent: 0px; line-height: 25px; text-align: center;\\\"><span style=\\\"font-family: &#39;Helvetica Neue&#39;;color: rgb(38, 38, 38);letter-spacing: 0;font-size: 12px;background: rgb(255, 255, 255)\\\"><span style=\\\"font-family:Helvetica Neue\\\">我们的藻钙产品主要是针对甲醛，甲苯、氨、</span>TVOC等，但是对ph值小于2.5的有害物质也同样是有效果的，并且对除菌除异味也有明显效果，但是根据使用数量和使用环境，使用效果会不同哦。</span></p><p><span style=\\\";font-family:宋体;font-size:14px\\\">&nbsp;</span></p><p><br/></p>\",\"hit\":0,\"lastmodified\":1498197769,\"cat_id\":26,\"id\":1221,\"add_time\":1498197666,\"sys_lock\":0}],\"totalPageCount\":1}}";
		
    	Long t1=new Date().getTime();
    	byte[] a=compress(aa);
    	Long t2=new Date().getTime();
    	System.out.println("compress Time:"+(t2-t1));
    	String de=decompress(a);
    	Long t3=new Date().getTime();
    	System.out.println("decompress Time:"+(t3-t2));
    	System.out.println(de);
    	System.out.println(a.length);
		System.out.println(aa.getBytes(cataName).length);
	}
}