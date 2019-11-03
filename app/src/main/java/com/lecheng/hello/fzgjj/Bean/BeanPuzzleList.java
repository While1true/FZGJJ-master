package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/5/25.
 */

public class BeanPuzzleList {

    /**
     * data : {"data":[{"createdate":"2017-06-26 17:10:00.0","hfnr":"购房时间在一年内，可按购买自住住房的条件和材料办理提取手续。购房提取所需材料请浏览省直公积金网站办事指南中住房公积金支取的相关内容。","name":"陈先生","title":"购房支取","twnr":"本人原在福州市公积金中心于2015年因在泉州购房支取过公积金，2015年7月转入福建省直公积金中心；现要改善住房计划今年7月在泉州购房产，请问可以再支取公积金吗？","type":"1","updatedate":"2017-10-10 14:23:33.0"},{"createdate":"2017-06-29 09:47:00.0","hfnr":"公积金查询系统登录的初始密码是本人身份证号码的后六位数字（有字母的需大写），如果登录不了，请凭本人身份证到省直公积金中心登记，申请恢复初始密码。","name":"黄启航","title":"密码咨询","twnr":"公司给的公积金存折，上面显示个人账户和中心编号还有名字，但是印密这里显示无密户，我这个密码怎么办？查询信息都要求登入输入密码。","type":"4","updatedate":"2017-08-21 08:38:39.0"}],"sum":"1129"}
     * status : 1
     */

    private String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createdate : 2017-06-26 17:10:00.0
         * hfnr : 购房时间在一年内，可按购买自住住房的条件和材料办理提取手续。购房提取所需材料请浏览省直公积金网站办事指南中住房公积金支取的相关内容。
         * name : 陈先生
         * title : 购房支取
         * twnr : 本人原在福州市公积金中心于2015年因在泉州购房支取过公积金，2015年7月转入福建省直公积金中心；现要改善住房计划今年7月在泉州购房产，请问可以再支取公积金吗？
         * type : 1
         * updatedate : 2017-10-10 14:23:33.0
         */

        private String createdate;
        private String hfnr;
        private String name;
        private String title;
        private String twnr;
        private String type;
        private String updatedate;

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public String getHfnr() {
            return hfnr;
        }

        public void setHfnr(String hfnr) {
            this.hfnr = hfnr;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTwnr() {
            return twnr;
        }

        public void setTwnr(String twnr) {
            this.twnr = twnr;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdatedate() {
            return updatedate;
        }

        public void setUpdatedate(String updatedate) {
            this.updatedate = updatedate;
        }
    }
}
