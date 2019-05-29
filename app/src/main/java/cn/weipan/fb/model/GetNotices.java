package cn.weipan.fb.model;

import java.util.List;

public class GetNotices {


    /**
     * Result : 0
     * Error : 成功
     * TatalPage : 0
     * data : []
     */

    private String Result;
    private String Error;
    private int TatalPage;
    private List<?> data;

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public String getError() {
        return Error;
    }

    public void setError(String Error) {
        this.Error = Error;
    }

    public int getTatalPage() {
        return TatalPage;
    }

    public void setTatalPage(int TatalPage) {
        this.TatalPage = TatalPage;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
