package com.dlz.apps.sys.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dlz.apps.sys.db.model.PushsCriteria.GeneratedCriteria;
import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;

public class PushsCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public PushsCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andIdIsNull() {
            addCriterion("ID is null");return this;
        }

        public GeneratedCriteria andIdIsNotNull() {
            addCriterion("ID is not null");return this;
        }

        public GeneratedCriteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");return this;
        }

        public GeneratedCriteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");return this;
        }

        public GeneratedCriteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");return this;
        }

        public GeneratedCriteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");return this;
        }

        public GeneratedCriteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");return this;
        }

        public GeneratedCriteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");return this;
        }

        public GeneratedCriteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");return this;
        }

        public GeneratedCriteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");return this;
        }

        public GeneratedCriteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");return this;
        }

        public GeneratedCriteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");return this;
        }

        public GeneratedCriteria andUserIdIsNull() {
            addCriterion("USER_ID is null");return this;
        }

        public GeneratedCriteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");return this;
        }

        public GeneratedCriteria andUserIdEqualTo(Long value) {
            addCriterion("USER_ID =", value, "userId");return this;
        }

        public GeneratedCriteria andUserIdNotEqualTo(Long value) {
            addCriterion("USER_ID <>", value, "userId");return this;
        }

        public GeneratedCriteria andUserIdGreaterThan(Long value) {
            addCriterion("USER_ID >", value, "userId");return this;
        }

        public GeneratedCriteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("USER_ID >=", value, "userId");return this;
        }

        public GeneratedCriteria andUserIdLessThan(Long value) {
            addCriterion("USER_ID <", value, "userId");return this;
        }

        public GeneratedCriteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("USER_ID <=", value, "userId");return this;
        }

        public GeneratedCriteria andUserIdIn(List<Long> values) {
            addCriterion("USER_ID in", values, "userId");return this;
        }

        public GeneratedCriteria andUserIdNotIn(List<Long> values) {
            addCriterion("USER_ID not in", values, "userId");return this;
        }

        public GeneratedCriteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("USER_ID between", value1, value2, "userId");return this;
        }

        public GeneratedCriteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");return this;
        }

        public GeneratedCriteria andClientIdIsNull() {
            addCriterion("CLIENT_ID is null");return this;
        }

        public GeneratedCriteria andClientIdIsNotNull() {
            addCriterion("CLIENT_ID is not null");return this;
        }

        public GeneratedCriteria andClientIdEqualTo(String value) {
            addCriterion("CLIENT_ID =", value, "clientId");return this;
        }

        public GeneratedCriteria andClientIdNotEqualTo(String value) {
            addCriterion("CLIENT_ID <>", value, "clientId");return this;
        }

        public GeneratedCriteria andClientIdGreaterThan(String value) {
            addCriterion("CLIENT_ID >", value, "clientId");return this;
        }

        public GeneratedCriteria andClientIdGreaterThanOrEqualTo(String value) {
            addCriterion("CLIENT_ID >=", value, "clientId");return this;
        }

        public GeneratedCriteria andClientIdLessThan(String value) {
            addCriterion("CLIENT_ID <", value, "clientId");return this;
        }

        public GeneratedCriteria andClientIdLessThanOrEqualTo(String value) {
            addCriterion("CLIENT_ID <=", value, "clientId");return this;
        }

        public GeneratedCriteria andClientIdLike(String value) {
            addCriterion("CLIENT_ID like", value, "clientId");return this;
        }

        public GeneratedCriteria andClientIdNotLike(String value) {
            addCriterion("CLIENT_ID not like", value, "clientId");return this;
        }

        public GeneratedCriteria andClientIdIn(List<String> values) {
            addCriterion("CLIENT_ID in", values, "clientId");return this;
        }

        public GeneratedCriteria andClientIdNotIn(List<String> values) {
            addCriterion("CLIENT_ID not in", values, "clientId");return this;
        }

        public GeneratedCriteria andClientIdBetween(String value1, String value2) {
            addCriterion("CLIENT_ID between", value1, value2, "clientId");return this;
        }

        public GeneratedCriteria andClientIdNotBetween(String value1, String value2) {
            addCriterion("CLIENT_ID not between", value1, value2, "clientId");return this;
        }

        public GeneratedCriteria andTitleIsNull() {
            addCriterion("TITLE is null");return this;
        }

        public GeneratedCriteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");return this;
        }

        public GeneratedCriteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");return this;
        }

        public GeneratedCriteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");return this;
        }

        public GeneratedCriteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");return this;
        }

        public GeneratedCriteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");return this;
        }

        public GeneratedCriteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");return this;
        }

        public GeneratedCriteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");return this;
        }

        public GeneratedCriteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");return this;
        }

        public GeneratedCriteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");return this;
        }

        public GeneratedCriteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");return this;
        }

        public GeneratedCriteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");return this;
        }

        public GeneratedCriteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");return this;
        }

        public GeneratedCriteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");return this;
        }

        public GeneratedCriteria andTxtIsNull() {
            addCriterion("TXT is null");return this;
        }

        public GeneratedCriteria andTxtIsNotNull() {
            addCriterion("TXT is not null");return this;
        }

        public GeneratedCriteria andTxtEqualTo(String value) {
            addCriterion("TXT =", value, "txt");return this;
        }

        public GeneratedCriteria andTxtNotEqualTo(String value) {
            addCriterion("TXT <>", value, "txt");return this;
        }

        public GeneratedCriteria andTxtGreaterThan(String value) {
            addCriterion("TXT >", value, "txt");return this;
        }

        public GeneratedCriteria andTxtGreaterThanOrEqualTo(String value) {
            addCriterion("TXT >=", value, "txt");return this;
        }

        public GeneratedCriteria andTxtLessThan(String value) {
            addCriterion("TXT <", value, "txt");return this;
        }

        public GeneratedCriteria andTxtLessThanOrEqualTo(String value) {
            addCriterion("TXT <=", value, "txt");return this;
        }

        public GeneratedCriteria andTxtLike(String value) {
            addCriterion("TXT like", value, "txt");return this;
        }

        public GeneratedCriteria andTxtNotLike(String value) {
            addCriterion("TXT not like", value, "txt");return this;
        }

        public GeneratedCriteria andTxtIn(List<String> values) {
            addCriterion("TXT in", values, "txt");return this;
        }

        public GeneratedCriteria andTxtNotIn(List<String> values) {
            addCriterion("TXT not in", values, "txt");return this;
        }

        public GeneratedCriteria andTxtBetween(String value1, String value2) {
            addCriterion("TXT between", value1, value2, "txt");return this;
        }

        public GeneratedCriteria andTxtNotBetween(String value1, String value2) {
            addCriterion("TXT not between", value1, value2, "txt");return this;
        }

        public GeneratedCriteria andTpIsNull() {
            addCriterion("TP is null");return this;
        }

        public GeneratedCriteria andTpIsNotNull() {
            addCriterion("TP is not null");return this;
        }

        public GeneratedCriteria andTpEqualTo(String value) {
            addCriterion("TP =", value, "tp");return this;
        }

        public GeneratedCriteria andTpNotEqualTo(String value) {
            addCriterion("TP <>", value, "tp");return this;
        }

        public GeneratedCriteria andTpGreaterThan(String value) {
            addCriterion("TP >", value, "tp");return this;
        }

        public GeneratedCriteria andTpGreaterThanOrEqualTo(String value) {
            addCriterion("TP >=", value, "tp");return this;
        }

        public GeneratedCriteria andTpLessThan(String value) {
            addCriterion("TP <", value, "tp");return this;
        }

        public GeneratedCriteria andTpLessThanOrEqualTo(String value) {
            addCriterion("TP <=", value, "tp");return this;
        }

        public GeneratedCriteria andTpLike(String value) {
            addCriterion("TP like", value, "tp");return this;
        }

        public GeneratedCriteria andTpNotLike(String value) {
            addCriterion("TP not like", value, "tp");return this;
        }

        public GeneratedCriteria andTpIn(List<String> values) {
            addCriterion("TP in", values, "tp");return this;
        }

        public GeneratedCriteria andTpNotIn(List<String> values) {
            addCriterion("TP not in", values, "tp");return this;
        }

        public GeneratedCriteria andTpBetween(String value1, String value2) {
            addCriterion("TP between", value1, value2, "tp");return this;
        }

        public GeneratedCriteria andTpNotBetween(String value1, String value2) {
            addCriterion("TP not between", value1, value2, "tp");return this;
        }

        public GeneratedCriteria andParaIsNull() {
            addCriterion("PARA is null");return this;
        }

        public GeneratedCriteria andParaIsNotNull() {
            addCriterion("PARA is not null");return this;
        }

        public GeneratedCriteria andParaEqualTo(String value) {
            addCriterion("PARA =", value, "para");return this;
        }

        public GeneratedCriteria andParaNotEqualTo(String value) {
            addCriterion("PARA <>", value, "para");return this;
        }

        public GeneratedCriteria andParaGreaterThan(String value) {
            addCriterion("PARA >", value, "para");return this;
        }

        public GeneratedCriteria andParaGreaterThanOrEqualTo(String value) {
            addCriterion("PARA >=", value, "para");return this;
        }

        public GeneratedCriteria andParaLessThan(String value) {
            addCriterion("PARA <", value, "para");return this;
        }

        public GeneratedCriteria andParaLessThanOrEqualTo(String value) {
            addCriterion("PARA <=", value, "para");return this;
        }

        public GeneratedCriteria andParaLike(String value) {
            addCriterion("PARA like", value, "para");return this;
        }

        public GeneratedCriteria andParaNotLike(String value) {
            addCriterion("PARA not like", value, "para");return this;
        }

        public GeneratedCriteria andParaIn(List<String> values) {
            addCriterion("PARA in", values, "para");return this;
        }

        public GeneratedCriteria andParaNotIn(List<String> values) {
            addCriterion("PARA not in", values, "para");return this;
        }

        public GeneratedCriteria andParaBetween(String value1, String value2) {
            addCriterion("PARA between", value1, value2, "para");return this;
        }

        public GeneratedCriteria andParaNotBetween(String value1, String value2) {
            addCriterion("PARA not between", value1, value2, "para");return this;
        }

        public GeneratedCriteria andStaIsNull() {
            addCriterion("STA is null");return this;
        }

        public GeneratedCriteria andStaIsNotNull() {
            addCriterion("STA is not null");return this;
        }

        public GeneratedCriteria andStaEqualTo(String value) {
            addCriterion("STA =", value, "sta");return this;
        }

        public GeneratedCriteria andStaNotEqualTo(String value) {
            addCriterion("STA <>", value, "sta");return this;
        }

        public GeneratedCriteria andStaGreaterThan(String value) {
            addCriterion("STA >", value, "sta");return this;
        }

        public GeneratedCriteria andStaGreaterThanOrEqualTo(String value) {
            addCriterion("STA >=", value, "sta");return this;
        }

        public GeneratedCriteria andStaLessThan(String value) {
            addCriterion("STA <", value, "sta");return this;
        }

        public GeneratedCriteria andStaLessThanOrEqualTo(String value) {
            addCriterion("STA <=", value, "sta");return this;
        }

        public GeneratedCriteria andStaLike(String value) {
            addCriterion("STA like", value, "sta");return this;
        }

        public GeneratedCriteria andStaNotLike(String value) {
            addCriterion("STA not like", value, "sta");return this;
        }

        public GeneratedCriteria andStaIn(List<String> values) {
            addCriterion("STA in", values, "sta");return this;
        }

        public GeneratedCriteria andStaNotIn(List<String> values) {
            addCriterion("STA not in", values, "sta");return this;
        }

        public GeneratedCriteria andStaBetween(String value1, String value2) {
            addCriterion("STA between", value1, value2, "sta");return this;
        }

        public GeneratedCriteria andStaNotBetween(String value1, String value2) {
            addCriterion("STA not between", value1, value2, "sta");return this;
        }

        public GeneratedCriteria andMsgIsNull() {
            addCriterion("MSG is null");return this;
        }

        public GeneratedCriteria andMsgIsNotNull() {
            addCriterion("MSG is not null");return this;
        }

        public GeneratedCriteria andMsgEqualTo(String value) {
            addCriterion("MSG =", value, "msg");return this;
        }

        public GeneratedCriteria andMsgNotEqualTo(String value) {
            addCriterion("MSG <>", value, "msg");return this;
        }

        public GeneratedCriteria andMsgGreaterThan(String value) {
            addCriterion("MSG >", value, "msg");return this;
        }

        public GeneratedCriteria andMsgGreaterThanOrEqualTo(String value) {
            addCriterion("MSG >=", value, "msg");return this;
        }

        public GeneratedCriteria andMsgLessThan(String value) {
            addCriterion("MSG <", value, "msg");return this;
        }

        public GeneratedCriteria andMsgLessThanOrEqualTo(String value) {
            addCriterion("MSG <=", value, "msg");return this;
        }

        public GeneratedCriteria andMsgLike(String value) {
            addCriterion("MSG like", value, "msg");return this;
        }

        public GeneratedCriteria andMsgNotLike(String value) {
            addCriterion("MSG not like", value, "msg");return this;
        }

        public GeneratedCriteria andMsgIn(List<String> values) {
            addCriterion("MSG in", values, "msg");return this;
        }

        public GeneratedCriteria andMsgNotIn(List<String> values) {
            addCriterion("MSG not in", values, "msg");return this;
        }

        public GeneratedCriteria andMsgBetween(String value1, String value2) {
            addCriterion("MSG between", value1, value2, "msg");return this;
        }

        public GeneratedCriteria andMsgNotBetween(String value1, String value2) {
            addCriterion("MSG not between", value1, value2, "msg");return this;
        }

        public GeneratedCriteria andSendResultIsNull() {
            addCriterion("SEND_RESULT is null");return this;
        }

        public GeneratedCriteria andSendResultIsNotNull() {
            addCriterion("SEND_RESULT is not null");return this;
        }

        public GeneratedCriteria andSendResultEqualTo(String value) {
            addCriterion("SEND_RESULT =", value, "sendResult");return this;
        }

        public GeneratedCriteria andSendResultNotEqualTo(String value) {
            addCriterion("SEND_RESULT <>", value, "sendResult");return this;
        }

        public GeneratedCriteria andSendResultGreaterThan(String value) {
            addCriterion("SEND_RESULT >", value, "sendResult");return this;
        }

        public GeneratedCriteria andSendResultGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_RESULT >=", value, "sendResult");return this;
        }

        public GeneratedCriteria andSendResultLessThan(String value) {
            addCriterion("SEND_RESULT <", value, "sendResult");return this;
        }

        public GeneratedCriteria andSendResultLessThanOrEqualTo(String value) {
            addCriterion("SEND_RESULT <=", value, "sendResult");return this;
        }

        public GeneratedCriteria andSendResultLike(String value) {
            addCriterion("SEND_RESULT like", value, "sendResult");return this;
        }

        public GeneratedCriteria andSendResultNotLike(String value) {
            addCriterion("SEND_RESULT not like", value, "sendResult");return this;
        }

        public GeneratedCriteria andSendResultIn(List<String> values) {
            addCriterion("SEND_RESULT in", values, "sendResult");return this;
        }

        public GeneratedCriteria andSendResultNotIn(List<String> values) {
            addCriterion("SEND_RESULT not in", values, "sendResult");return this;
        }

        public GeneratedCriteria andSendResultBetween(String value1, String value2) {
            addCriterion("SEND_RESULT between", value1, value2, "sendResult");return this;
        }

        public GeneratedCriteria andSendResultNotBetween(String value1, String value2) {
            addCriterion("SEND_RESULT not between", value1, value2, "sendResult");return this;
        }

        public GeneratedCriteria andLastSendTimeIsNull() {
            addCriterion("LAST_SEND_TIME is null");return this;
        }

        public GeneratedCriteria andLastSendTimeIsNotNull() {
            addCriterion("LAST_SEND_TIME is not null");return this;
        }

        public GeneratedCriteria andLastSendTimeEqualTo(Date value) {
            addCriterion("LAST_SEND_TIME =", value, "lastSendTime");return this;
        }

        public GeneratedCriteria andLastSendTimeNotEqualTo(Date value) {
            addCriterion("LAST_SEND_TIME <>", value, "lastSendTime");return this;
        }

        public GeneratedCriteria andLastSendTimeGreaterThan(Date value) {
            addCriterion("LAST_SEND_TIME >", value, "lastSendTime");return this;
        }

        public GeneratedCriteria andLastSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LAST_SEND_TIME >=", value, "lastSendTime");return this;
        }

        public GeneratedCriteria andLastSendTimeLessThan(Date value) {
            addCriterion("LAST_SEND_TIME <", value, "lastSendTime");return this;
        }

        public GeneratedCriteria andLastSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("LAST_SEND_TIME <=", value, "lastSendTime");return this;
        }

        public GeneratedCriteria andLastSendTimeIn(List<Date> values) {
            addCriterion("LAST_SEND_TIME in", values, "lastSendTime");return this;
        }

        public GeneratedCriteria andLastSendTimeNotIn(List<Date> values) {
            addCriterion("LAST_SEND_TIME not in", values, "lastSendTime");return this;
        }

        public GeneratedCriteria andLastSendTimeBetween(Date value1, Date value2) {
            addCriterion("LAST_SEND_TIME between", value1, value2, "lastSendTime");return this;
        }

        public GeneratedCriteria andLastSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("LAST_SEND_TIME not between", value1, value2, "lastSendTime");return this;
        }

        public GeneratedCriteria andReciveTimeIsNull() {
            addCriterion("RECIVE_TIME is null");return this;
        }

        public GeneratedCriteria andReciveTimeIsNotNull() {
            addCriterion("RECIVE_TIME is not null");return this;
        }

        public GeneratedCriteria andReciveTimeEqualTo(Date value) {
            addCriterion("RECIVE_TIME =", value, "reciveTime");return this;
        }

        public GeneratedCriteria andReciveTimeNotEqualTo(Date value) {
            addCriterion("RECIVE_TIME <>", value, "reciveTime");return this;
        }

        public GeneratedCriteria andReciveTimeGreaterThan(Date value) {
            addCriterion("RECIVE_TIME >", value, "reciveTime");return this;
        }

        public GeneratedCriteria andReciveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("RECIVE_TIME >=", value, "reciveTime");return this;
        }

        public GeneratedCriteria andReciveTimeLessThan(Date value) {
            addCriterion("RECIVE_TIME <", value, "reciveTime");return this;
        }

        public GeneratedCriteria andReciveTimeLessThanOrEqualTo(Date value) {
            addCriterion("RECIVE_TIME <=", value, "reciveTime");return this;
        }

        public GeneratedCriteria andReciveTimeIn(List<Date> values) {
            addCriterion("RECIVE_TIME in", values, "reciveTime");return this;
        }

        public GeneratedCriteria andReciveTimeNotIn(List<Date> values) {
            addCriterion("RECIVE_TIME not in", values, "reciveTime");return this;
        }

        public GeneratedCriteria andReciveTimeBetween(Date value1, Date value2) {
            addCriterion("RECIVE_TIME between", value1, value2, "reciveTime");return this;
        }

        public GeneratedCriteria andReciveTimeNotBetween(Date value1, Date value2) {
            addCriterion("RECIVE_TIME not between", value1, value2, "reciveTime");return this;
        }

        public GeneratedCriteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");return this;
        }

        public GeneratedCriteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");return this;
        }

        public GeneratedCriteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");return this;
        }
    }
}