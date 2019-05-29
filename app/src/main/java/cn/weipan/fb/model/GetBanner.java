package cn.weipan.fb.model;

public class GetBanner {


    /**
     * Result : 0
     * Error : 成功
     * data : {"Imgurl":"/UpLoadFile/0/2017-4/1704251348333639993.png","Linkurl":"http://t.cn/RaXGG7i"}
     */

    private String Result;
    private String Error;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Imgurl : /UpLoadFile/0/2017-4/1704251348333639993.png
         * Linkurl : http://t.cn/RaXGG7i
         */

        private String Imgurl;
        private String Linkurl;

        public String getImgurl() {
            return Imgurl;
        }

        public void setImgurl(String Imgurl) {
            this.Imgurl = Imgurl;
        }

        public String getLinkurl() {
            return Linkurl;
        }

        public void setLinkurl(String Linkurl) {
            this.Linkurl = Linkurl;
        }
    }
}
