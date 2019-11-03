package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by vange on 2017/9/27.
 */

public class TQInfo {

    /**
     * attention : ①应自该项行为发生之日起一年内且只能办理一次提取，不同的房屋或同一套住房在12个月内多次交易的，申请提取需与上次提取时间间隔12个月;\n②职工及配偶在省直中心缴存公积金的凭相关材料需同时申请提取住房公积金,提取总额不得超过已实际发生的住房消费支出;\n③必要时根据要求提供房地产交易部门出具的个人房屋登记信息查询记录原件和银行借款合同原件;\n④职工购买两套或两套以上住房的，只能以其中一套住房申请提取住房公积金;\n⑤职工在省外购房的，还应提供本人或配偶在购房所在地的下列材料之一：户籍证明、住房公积金缴交证明、社保缴交证明、工资薪酬税单的原件和复印件;\n⑥不动产登记证注明产权比例的，共有产权人按产权比例份额计算提取额度。
     * code_id : 11_1
     * condition : ["结婚证原件和复印件（单身不必提供）","申请人身份证原件和复印件","购房首付款发票原件和复印件","房屋预告登记证（不动产登记证明）原件和复印件","购房合同原件和复印件"]
     * parent_name : 购买一手房
     */

    private String attention;
    private String code_id;
    private String parent_name;
    private List<String> condition;

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getCode_id() {
        return code_id;
    }

    public void setCode_id(String code_id) {
        this.code_id = code_id;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public List<String> getCondition() {
        return condition;
    }

    public void setCondition(List<String> condition) {
        this.condition = condition;
    }
}
