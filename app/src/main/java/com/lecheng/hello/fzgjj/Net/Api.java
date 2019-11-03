package com.lecheng.hello.fzgjj.Net;


/**
 * Created by vange on 2017/9/1.
 */

public class Api {
    //超时时间
    public static int TIME_OUT = 12000;
//    public static final String URL = "http://1713581ab8.imwork.net:30565/gjjAndroid/services/GjjController?wsdl";
    public static final String URL = "http://120.35.30.201:8083/gjjAndroid/services/GjjController?wsdl";
    public static final String URLMESSAGE = "http://120.35.30.201:8083/message/services/SendMessageWebService?wsdl";
//    public static final String URLMESSAGE="http://10.0.110.117:8090/message/Services/SendMessageWebService?wsdl";
//    public static final String URL="http://10.0.110.127:8080/gjjAndroid/services/GjjController?wsdl";

    public static final String NAMESPACE_MESSAGE = "http://impl.ws.message.grid.com";
    public static final String NAMESPACE_ = "http://xml.apache.org/xml-soap";

    public static final String SIMPLE_SHEET_URL = "http://120.35.30.201/gjj/";

    //首页信息
    public static final String HOME_NEWS = "infoList";
    //在线机构
    public static final String HOME_ZXJG = "ZXJJ";
    //资料变更
    public static final String MODIFY_ACCOUNT = "zlbg";
    //资料查询
    public static final String QUIRY_ACCOUNT = "zlcx";
    //发送验证码
    public static final String SEND_CODE = "sendCode";
    //计算最高额度贷款
    public static final String CALCULATE_DKED = "searchLoanAmount";
    //计算最高还款测算
    public static final String CALCULATE_DKHK = "searchRepaymentCalculation";

    //疑难解答
    public static final String HANDLE_PROMBLEM = "addynjd";
    //在线调查
    public static final String ZXDC = "zxdcList";
    //在线调查分支
    public static final String ZXDC_SUB = "zxdcSubList";
    //提交在线调查
    public static final String ZXDC_SUBMIT = "zxdcCommitList";

    //小米推送
    public static final String BIND_CHANNEL_ID = "bindChannelId";

    //提取类型
    public static final String TQ_TYPE = "extract";

    //获取提取提示信息
    public static final String GET_TQ_INFO = "extractSub";
    //预约提取
    public static final String YYCOMMIT = "yyCommit";

    //上传ip
    public static final String UPLOADIP = "saveVisit";

    //民意征集
    public static final String MYZJLIST = "myzjList";

    //提交民意征集
    public static final String MYZJCOMMIT = "myzjCommitList";

    public static final String YYCANCEL = "yyCancel";
    public static final String YYLIST = "yyList";
    public static final String SENDMESSAGE = "sendMessageToPhone";

    public static final String TEMP_MESSAGE = "%s先生/女士，您%s业务预约的时间为%s，请准时前往取号，过时则预约失效。无法前来办理，请提前1天退号，以方便他人预约。";
    public static final String TEMP_MESSAGE_CANCEl = "%s先生/女士，您%s业务预约的时间为:%s，已取消成功，欢迎再次使用！";
    public static final String TEMP_MESSAGE_CODE = "%s先生/女士，开通短信的验证码为:%s，请不要告诉别人，欢迎再次使用！";

    //贷款进度查询
    public static final String DKJDCX = "dkjdcx";

    public static final String YYQUERY = "yyQuery";
    public static final String accessControl = "accessControl";
    public static final String ZHMI = "wjmm";
    public static final String UPDATEPWD = "updatePwdForyzm";
    public static final String CHECK_VERSION = "checkVersion";

    public static final String WTBLQK="wtblqk";




}
