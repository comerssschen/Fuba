package cn.weipan.fb.model;

public class GetQCodeUrl {

    /**
     * Result : 0
     * Error : 成功
     * Data : {"qcodeurl":"http://QCodeWXPay.payweipan.com/?siteid=419&sId=419&DeviceId=10013087&cashId=13074","qcodeurlshow":"http://manager.payweipan.com/AutoQrCode.ashx?url=http://QCodeWXPay.payweipan.com/?siteid=419|sId=419|DeviceId=10013087|cashId=13074","bgqcodeurl":"http://WebApi.payweipan.com/img/zhihuicode_bg.jpg"}
     */

    private int Result;
    private String Error;
    private DataBean Data;

    public int getResult() {
        return Result;
    }

    public void setResult(int Result) {
        this.Result = Result;
    }

    public String getError() {
        return Error;
    }

    public void setError(String Error) {
        this.Error = Error;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * qcodeurl : http://QCodeWXPay.payweipan.com/?siteid=419&sId=419&DeviceId=10013087&cashId=13074
         * qcodeurlshow : http://manager.payweipan.com/AutoQrCode.ashx?url=http://QCodeWXPay.payweipan.com/?siteid=419|sId=419|DeviceId=10013087|cashId=13074
         * bgqcodeurl : http://WebApi.payweipan.com/img/zhihuicode_bg.jpg
         */

        private String qcodeurl;
        private String qcodeurlshow;
        private String bgqcodeurl;

        public String getQcodeurl() {
            return qcodeurl;
        }

        public void setQcodeurl(String qcodeurl) {
            this.qcodeurl = qcodeurl;
        }

        public String getQcodeurlshow() {
            return qcodeurlshow;
        }

        public void setQcodeurlshow(String qcodeurlshow) {
            this.qcodeurlshow = qcodeurlshow;
        }

        public String getBgqcodeurl() {
            return bgqcodeurl;
        }

        public void setBgqcodeurl(String bgqcodeurl) {
            this.bgqcodeurl = bgqcodeurl;
        }
    }
}
