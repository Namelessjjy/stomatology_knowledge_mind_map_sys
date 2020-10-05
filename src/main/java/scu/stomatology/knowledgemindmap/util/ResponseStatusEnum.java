package scu.stomatology.knowledgemindmap.util;

public enum ResponseStatusEnum {

    OK(10000, "成功"),

    FAILED(10001, "失败"),

    LOGINFAIL(10001, "登录失败，用户名或密码错误，请重试！")
    ;

    private int code;
    private String msg;

    private ResponseStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
