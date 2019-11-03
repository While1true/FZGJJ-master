package com.lecheng.hello.fzgjj.Bean;

import com.google.gson.annotations.SerializedName;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by vange on 2017/9/7.
 */

public class MessageBean extends DataSupport {


    /**
     * title:
     * remark : 距离本月还款日期还有866天,请您注意及时缴存还款,谢谢。
     * name : 尊敬的公积金用户：45
     * yhlx : 本期应还利息：55元
     * date : 本月还款日期：887
     * yhbj : 本期应还本金:474元
     * qs : 本月贷款期数：第888期
     *  :
     * type : 2
     * yhje : 本期应还金额：478元
     */


    private int typeid;
    private String remark;
    private String name;
    private String yhlx;
    private String yhbj;
    private String qs;
    private String date;
    private String title;
    private String today;
    private int type;
   //1:扣款提示 2：还款提示 3：逾期催收 4：全局广播 5：其他
    private String yhje;

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYhlx() {
        return yhlx;
    }

    public void setYhlx(String yhlx) {
        this.yhlx = yhlx;
    }



    public String getYhbj() {
        return yhbj;
    }

    public void setYhbj(String yhbj) {
        this.yhbj = yhbj;
    }

    public String getQs() {
        return qs;
    }

    public void setQs(String qs) {
        this.qs = qs;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getYhje() {
        return yhje;
    }

    public void setYhje(String yhje) {
        this.yhje = yhje;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }
}
