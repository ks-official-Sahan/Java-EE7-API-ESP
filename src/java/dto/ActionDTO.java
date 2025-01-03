package dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ksoff
 */
public class ActionDTO<T> implements Serializable {
    
    private String actionName;
    private String actionCode;
    private int action;
    private boolean isTypeA;
    private T data;
    private List<T> dataList;

    public ActionDTO() {
    }

    public ActionDTO(String actionName, String actionCode, int action, boolean isTypeA, T data, List<T> dataList) {
        this.actionName = actionName;
        this.actionCode = actionCode;
        this.action = action;
        this.isTypeA = isTypeA;
        this.data = data;
        this.dataList = dataList;
    }
    
    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public boolean isIsTypeA() {
        return isTypeA;
    }

    public void setIsTypeA(boolean isTypeA) {
        this.isTypeA = isTypeA;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }
    
}
