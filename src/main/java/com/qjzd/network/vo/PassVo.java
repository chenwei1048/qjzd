package com.qjzd.network.vo;

/**
 * @Author:
 * @Description:
 * @Date Create on 11:39 2018/11/20
 * @MOdifyBy:
 * @parameter
 */
import javax.validation.constraints.NotNull;
public class PassVo {

    @NotNull
    private String oldpass;

    @NotNull
    private String pass;

    @NotNull
    private String repass;

    public String getOldpass() {
        return oldpass;
    }

    public void setOldpass(String oldpass) {
        this.oldpass = oldpass;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }

    @Override
    public String toString() {
        return "PassVo{" +
                "oldpass='" + oldpass + '\'' +
                ", pass='" + pass + '\'' +
                ", repass='" + repass + '\'' +
                '}';
    }
}
