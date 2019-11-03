package com.lecheng.hello.fzgjj.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 乐城 on 2017/4/28.
 */

public class BeanZcfgList implements Serializable{
    /**
     * data : [{"content":"<p>各住房公积金缴存单位：<\/p><p>　　根据《广东省供给侧结构性改革降成本行动计划（2016-2018）》以及《深圳市供给侧结构性改革降成本优环境行动计划（2016-2018年）》等规定，经市住房公积金管理委员会批准，现就阶段性调整住房公积金缴存基数上限有关事项通知如下：<\/p><p>　　一、自本通知发布之日起至2017年6月30日，单位及个人的住房公积金缴存基数原则上不超过本市统计部门公布的2015年度全市在岗职工月平均工资的3倍（即20259元）;经济效益好的单位，缴存基数最高不得超过33765元。<\/p><p>　　2017年7月1日起，前款规定的本市住房公积金缴存基数上限，将根据本市统计部门公布的上年度全市在岗职工月平均工资等另行通知。<\/p><p><br/><\/p><p><img src=\"ueditor/jsp/upload/image/20170427/1493273108746075343.jpg\" title=\"1493273108746075343.jpg\" alt=\"登录界面.jpg\" width=\"579\" height=\"352\" style=\"width: 579px; height: 352px;\"/><\/p><p>　　二、本通知施行前本市有关住房公积金规定与本通知不一致的，以本通知为准。<\/p><p>　　特此通知。<\/p><p>　　深圳市住房公积金管理中心<\/p><p>　　2017年1月20日<\/p>","fbt":"1","id":"45","title":"公积金新闻标题测试13adfa","twolevel":"ZCFG","updatedate":"2017-04-27"},{"content":"<p>各住房公积金缴存单位：<\/p>\n            <p>　　根据《广东省供给侧结构性改革降成本行动计划（2016-2018）》以及《深圳市供给侧结构性改革降成本优环境行动计划（2016-2018年）》等规定，经市住房公积金管理委员会批准，现就阶段性调整住房公积金缴存基数上限有关事项通知如下：<\/p>\n            <p>　　一、自本通知发布之日起至2017年6月30日，单位及个人的住房公积金缴存基数原则上不超过本市统计部门公布的2015年度全市在岗职工月平均工资的3倍（即20259元）;经济效益好的单位，缴存基数最高不得超过33765元。<\/p>\n            <p>　　2017年7月1日起，前款规定的本市住房公积金缴存基数上限，将根据本市统计部门公布的上年度全市在岗职工月平均工资等另行通知。<\/p>\n            <p>　　二、本通知施行前本市有关住房公积金规定与本通知不一致的，以本通知为准。<\/p>\n            <p>　　特此通知。<\/p>\n            <p>　　深圳市住房公积金管理中心<\/p>\n            <p>　　2017年1月20日<\/p>","fbt":"1","id":"19","title":"公积金新闻标题测试13","twolevel":"ZCFG","updatedate":"2017-04-25"},{"fbt":"1","id":"18","title":"公积金新闻标题测试12","twolevel":"ZCFG","updatedate":"2017-04-25"},{"fbt":"1","id":"44","title":"公积金新闻标题测试12","twolevel":"ZCFG","updatedate":"2017-04-25"},{"fbt":"1","id":"43","title":"公积金新闻标题测试11","twolevel":"ZCFG","updatedate":"2017-04-25"},{"fbt":"1","id":"17","title":"公积金新闻标题测试11","twolevel":"ZCFG","updatedate":"2017-04-25"},{"fbt":"1","id":"16","title":"公积金新闻标题测试10","twolevel":"ZCFG","updatedate":"2017-04-25"},{"fbt":"1","id":"42","title":"公积金新闻标题测试10","twolevel":"ZCFG","updatedate":"2017-04-25"},{"fbt":"1","id":"15","title":"公积金新闻标题测试9","twolevel":"ZCFG","updatedate":"2017-04-25"},{"fbt":"1","id":"41","title":"公积金新闻标题测试9","twolevel":"ZCFG","updatedate":"2017-04-25"}]
     * status : 1
     */

    private String status;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * content : <p>各住房公积金缴存单位：</p><p>　　根据《广东省供给侧结构性改革降成本行动计划（2016-2018）》以及《深圳市供给侧结构性改革降成本优环境行动计划（2016-2018年）》等规定，经市住房公积金管理委员会批准，现就阶段性调整住房公积金缴存基数上限有关事项通知如下：</p><p>　　一、自本通知发布之日起至2017年6月30日，单位及个人的住房公积金缴存基数原则上不超过本市统计部门公布的2015年度全市在岗职工月平均工资的3倍（即20259元）;经济效益好的单位，缴存基数最高不得超过33765元。</p><p>　　2017年7月1日起，前款规定的本市住房公积金缴存基数上限，将根据本市统计部门公布的上年度全市在岗职工月平均工资等另行通知。</p><p><br/></p><p><img src="ueditor/jsp/upload/image/20170427/1493273108746075343.jpg" title="1493273108746075343.jpg" alt="登录界面.jpg" width="579" height="352" style="width: 579px; height: 352px;"/></p><p>　　二、本通知施行前本市有关住房公积金规定与本通知不一致的，以本通知为准。</p><p>　　特此通知。</p><p>　　深圳市住房公积金管理中心</p><p>　　2017年1月20日</p>
         * fbt : 1
         * id : 45
         * title : 公积金新闻标题测试13adfa
         * twolevel : ZCFG
         * updatedate : 2017-04-27
         */

        private String content;
        private String fbt;
        private String id;
        private String title;
        private String twolevel;
        private String updatedate;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFbt() {
            return fbt;
        }

        public void setFbt(String fbt) {
            this.fbt = fbt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTwolevel() {
            return twolevel;
        }

        public void setTwolevel(String twolevel) {
            this.twolevel = twolevel;
        }

        public String getUpdatedate() {
            return updatedate;
        }

        public void setUpdatedate(String updatedate) {
            this.updatedate = updatedate;
        }
    }
    /*
    {"data":[{"content":"<p>各住房公积金缴存单位：</p><p>　　根据《广东省供给侧结构性改革降成本行动计划（2016-2018）》以及《深圳市供给侧结构性改革降成本优环境行动计划（2016-2018年）》等规定，经市住房公积金管理委员会批准，现就阶段性调整住房公积金缴存基数上限有关事项通知如下：</p><p>　　一、自本通知发布之日起至2017年6月30日，单位及个人的住房公积金缴存基数原则上不超过本市统计部门公布的2015年度全市在岗职工月平均工资的3倍（即20259元）;经济效益好的单位，缴存基数最高不得超过33765元。</p><p>　　2017年7月1日起，前款规定的本市住房公积金缴存基数上限，将根据本市统计部门公布的上年度全市在岗职工月平均工资等另行通知。</p><p><br/></p><p><img src=\"ueditor/jsp/upload/image/20170427/1493273108746075343.jpg\" title=\"1493273108746075343.jpg\" alt=\"登录界面.jpg\" width=\"579\" height=\"352\" style=\"width: 579px; height: 352px;\"/></p><p>　　二、本通知施行前本市有关住房公积金规定与本通知不一致的，以本通知为准。</p><p>　　特此通知。</p><p>　　深圳市住房公积金管理中心</p><p>　　2017年1月20日</p>","fbt":"1","id":"45","title":"公积金新闻标题测试13adfa","twolevel":"ZCFG","updatedate":"2017-04-27"},{"content":"<p>各住房公积金缴存单位：</p>\n            <p>　　根据《广东省供给侧结构性改革降成本行动计划（2016-2018）》以及《深圳市供给侧结构性改革降成本优环境行动计划（2016-2018年）》等规定，经市住房公积金管理委员会批准，现就阶段性调整住房公积金缴存基数上限有关事项通知如下：</p>\n            <p>　　一、自本通知发布之日起至2017年6月30日，单位及个人的住房公积金缴存基数原则上不超过本市统计部门公布的2015年度全市在岗职工月平均工资的3倍（即20259元）;经济效益好的单位，缴存基数最高不得超过33765元。</p>\n            <p>　　2017年7月1日起，前款规定的本市住房公积金缴存基数上限，将根据本市统计部门公布的上年度全市在岗职工月平均工资等另行通知。</p>\n            <p>　　二、本通知施行前本市有关住房公积金规定与本通知不一致的，以本通知为准。</p>\n            <p>　　特此通知。</p>\n            <p>　　深圳市住房公积金管理中心</p>\n            <p>　　2017年1月20日</p>","fbt":"1","id":"19","title":"公积金新闻标题测试13","twolevel":"ZCFG","updatedate":"2017-04-25"},{"fbt":"1","id":"18","title":"公积金新闻标题测试12","twolevel":"ZCFG","updatedate":"2017-04-25"},{"fbt":"1","id":"44","title":"公积金新闻标题测试12","twolevel":"ZCFG","updatedate":"2017-04-25"},{"fbt":"1","id":"43","title":"公积金新闻标题测试11","twolevel":"ZCFG","updatedate":"2017-04-25"},{"fbt":"1","id":"17","title":"公积金新闻标题测试11","twolevel":"ZCFG","updatedate":"2017-04-25"},{"fbt":"1","id":"16","title":"公积金新闻标题测试10","twolevel":"ZCFG","updatedate":"2017-04-25"},{"fbt":"1","id":"42","title":"公积金新闻标题测试10","twolevel":"ZCFG","updatedate":"2017-04-25"},{"fbt":"1","id":"15","title":"公积金新闻标题测试9","twolevel":"ZCFG","updatedate":"2017-04-25"},{"fbt":"1","id":"41","title":"公积金新闻标题测试9","twolevel":"ZCFG","updatedate":"2017-04-25"}],"status":"1"}
    */

}
