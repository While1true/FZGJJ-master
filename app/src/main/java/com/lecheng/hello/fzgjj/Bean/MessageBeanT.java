package com.lecheng.hello.fzgjj.Bean;

import org.litepal.crud.DataSupport;

/**
 * Created by vange on 2017/9/7.
 */

public class MessageBeanT extends DataSupport {
    public int type;//1:扣款提示 2：还款提示 3：逾期催收 4：全局广播 5：其他
   public String data;//消Json内容

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
