package cn.weipan.fb.model;

import java.util.List;

public class GetTool {

    /**
     * Result : 0
     * Error : 成功
     * data : [[{"url":"HourDetail.aspx","title":"实时消费分析","imgsrc":"image/image/money_function_realtime@2x.png"},{"url":"PayDetail.aspx","title":"消费时间分析","imgsrc":"image/image/money_function_time@2x.png"},{"url":"TimesDetail.aspx","title":"消费次数分析","imgsrc":"image/image/money_function_count@2x.png"},{"url":"MoneyDetail.aspx","title":"消费金额分析","imgsrc":"image/image/money_function_figure@2x.png"},{"url":"WeekDetail.aspx","title":"消费周期分析","imgsrc":"image/image/money_function_period@2x.png"},{"url":"RePayDetail.aspx","title":"消费复购率分析","imgsrc":"image/image/money_function_repitition@2x.png"}],[],[]]
     */

    private String Result;
    private String Error;
    private List<List<DataBean>> data;

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

    public List<List<DataBean>> getData() {
        return data;
    }

    public void setData(List<List<DataBean>> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * url : HourDetail.aspx
         * title : 实时消费分析
         * imgsrc : image/image/money_function_realtime@2x.png
         */

        private String url;
        private String title;
        private String imgsrc;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }
    }
}
