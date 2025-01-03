package dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ksoff
 */
public class RequestDTO<T> implements Serializable {

    private boolean status;
    private String action;
    private String url;
    private List<T> dataList;
    private T data;

    public RequestDTO() {
    }

    public RequestDTO(boolean status, String action, String url, List<T> dataList, T data) {
        this.status = status;
        this.action = action;
        this.url = url;
        this.dataList = dataList;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
    
}
