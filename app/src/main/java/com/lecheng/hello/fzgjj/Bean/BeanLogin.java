package com.lecheng.hello.fzgjj.Bean;

/**
 * Created by 乐城 on 2017/4/28.
 */

public class BeanLogin {

    /**
     * data : {"id":"35832","username":"350102196612250058","verioncode":"1.0"}
     * status : 1
     */

    private DataBean data;
    private String status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * id : 35832
         * username : 350102196612250058
         * verioncode : 1.0
         */

        private String id;
        private String username;
        private String verioncode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getVerioncode() {
            return verioncode;
        }

        public void setVerioncode(String verioncode) {
            this.verioncode = verioncode;
        }
    }
}
