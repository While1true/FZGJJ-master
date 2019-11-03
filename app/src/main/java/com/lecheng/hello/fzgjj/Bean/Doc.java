package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by vange on 2017/9/5.
 */

public class Doc {

    /**
     * name :
     * date : ["死亡证明(原件和复印件)","申请人身份证(原件和复印件)","申请人和死亡人关系证明(原件和复印件)"]
     */

    private String name;
    private List<String> date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }
}
