package security;

import java.io.Serializable;

public class HttpClientResult<T> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4084418251167336044L;

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private T content;
    
    public HttpClientResult() {
    }

    public HttpClientResult(int code) {
        this.code = code;
    }

    public HttpClientResult(T content) {
        this.content = content;
    }

    public HttpClientResult(int code, T content) {
        this.code = code;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HttpClientResult [code=" + code + ", content=" + content + "]";
    }

}
