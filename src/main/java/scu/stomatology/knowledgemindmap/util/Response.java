package scu.stomatology.knowledgemindmap.util;


import java.io.Serializable;

public class Response<T> implements Serializable {

    private static final long serialVersionUID = -1955667707282023534L;

    private int code;

    private String msg;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    protected Response() {

    }

    public static Response valueOf(int code, String msg) {
        Response response = new Response();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> valueOf(T data) {
        Response response = new Response();
        response.setCode(10000);
        response.setMsg("OK");
        response.setData(data);
        return response;
    }

    public static <T> Response<T> valueOf(int code, String msg, T data) {
        Response response = new Response();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> valueOf(ResponseStatusEnum status, T data) {
        Response response = new Response();
        response.setCode(status.getCode());
        response.setMsg(status.getMsg());
        response.setData(data);
        return response;
    }

    public static <T> Response<T> valueOf(ResponseStatusEnum status) {
        Response response = new Response();
        response.setCode(status.getCode());
        response.setMsg(status.getMsg());
        return response;
    }
}
