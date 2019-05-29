package cn.weipan.fb.model;

import java.util.List;

public class GetAccount {


    /**
     * Result : 0
     * Error : 成功
     * data : [[{"url":"TwoDayReceiptClass.aspx","title":"收款账单","imgsrc":"image/image/account_avater_receipt@2x.png","urlnext":"OrderSearch.aspx","Type":"1"},{"url":"ReturnCurrentMonthOrderList.aspx","title":"退款账单","imgsrc":"image/image/account_avater_refound@2x.png"},{"url":"CardConsumeOrderList.aspx","title":"核销账单","imgsrc":"image/image/account_avater_verification@2x.png","urlnext":"SearchCardTime.aspx","Type":"1"}],[{"url":"MMAccount.aspx","title":"会员账单","imgsrc":"image/image/account_avater_vip@2x.png","urlnext":"OrderSearch.aspx","Style":"1","Type":"1"},{"url":"MPAccount.aspx","title":"积分账单","imgsrc":"image/image/account_avater_integral@2x.png","urlnext":"OrderSearch.aspx","Style":"2","Type":"1"}],[{"url":"MonthOrders.aspx","title":"收款趋势","imgsrc":"image/image/account_avater_chamberlain@2x.png","urlnext":"TypeMonthOrders.aspx","Type":"2"}]]
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
         * url : TwoDayReceiptClass.aspx
         * title : 收款账单
         * imgsrc : image/image/account_avater_receipt@2x.png
         * urlnext : OrderSearch.aspx
         * Type : 1
         */

        private String url;
        private String title;
        private String imgsrc;
        private String urlnext;
        private String Type;

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

        public String getUrlnext() {
            return urlnext;
        }

        public void setUrlnext(String urlnext) {
            this.urlnext = urlnext;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }
    }
}
