package cn.weipan.fb.model;

public class TodayCount {

    /**
     * Result : 0
     * Error : 成功
     * weiixn : {"TotalDayCount":"0","TotalDayMoney":"0.00","TotalYestCount":"1","TotalYestMoney":"0.01","TotalMonthCount":"1","TotalMonthMoney":"0.01"}
     * zhifubao : {"TotalDayCount":"0","TotalDayMoney":"0.00","TotalYestCount":"0","TotalYestMoney":"0.00","TotalMonthCount":"0","TotalMonthMoney":"0.00"}
     * baidu : {"TotalDayCount":"0","TotalDayMoney":"0.00","TotalYestCount":"0","TotalYestMoney":"0.00","TotalMonthCount":"0","TotalMonthMoney":"0.00"}
     * qq : {"TotalDayCount":"0","TotalDayMoney":"0.00","TotalYestCount":"0","TotalYestMoney":"0.00","TotalMonthCount":"0","TotalMonthMoney":"0.00"}
     * jindon : {"TotalDayCount":"0","TotalDayMoney":"0.00","TotalYestCount":"0","TotalYestMoney":"0.00","TotalMonthCount":"0","TotalMonthMoney":"0.00"}
     * account : {"TotalDayCount":"0","TotalDayMoney":"0.00","TotalYestCount":"0","TotalYestMoney":"0.00","TotalMonthCount":"0","TotalMonthMoney":"0.00"}
     * allTotal : {"TotalDayCount":"0","TotalDayMoney":"0.00","TotalYestCount":"1","TotalYestMoney":"0.01","TotalMonthCount":"1","TotalMonthMoney":"0.01","LastmonthCount":"3","LastmonthMoney":"0.03"}
     */

    private String Result;
    private String Error;
    private WeiixnBean weiixn;
    private ZhifubaoBean zhifubao;
    private BaiduBean baidu;
    private QqBean qq;
    private JindonBean jindon;
    private AccountBean account;
    private AllTotalBean allTotal;

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

    public WeiixnBean getWeiixn() {
        return weiixn;
    }

    public void setWeiixn(WeiixnBean weiixn) {
        this.weiixn = weiixn;
    }

    public ZhifubaoBean getZhifubao() {
        return zhifubao;
    }

    public void setZhifubao(ZhifubaoBean zhifubao) {
        this.zhifubao = zhifubao;
    }

    public BaiduBean getBaidu() {
        return baidu;
    }

    public void setBaidu(BaiduBean baidu) {
        this.baidu = baidu;
    }

    public QqBean getQq() {
        return qq;
    }

    public void setQq(QqBean qq) {
        this.qq = qq;
    }

    public JindonBean getJindon() {
        return jindon;
    }

    public void setJindon(JindonBean jindon) {
        this.jindon = jindon;
    }

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }

    public AllTotalBean getAllTotal() {
        return allTotal;
    }

    public void setAllTotal(AllTotalBean allTotal) {
        this.allTotal = allTotal;
    }

    public static class WeiixnBean {
        /**
         * TotalDayCount : 0
         * TotalDayMoney : 0.00
         * TotalYestCount : 1
         * TotalYestMoney : 0.01
         * TotalMonthCount : 1
         * TotalMonthMoney : 0.01
         */

        private String TotalDayCount;
        private String TotalDayMoney;
        private String TotalYestCount;
        private String TotalYestMoney;
        private String TotalMonthCount;
        private String TotalMonthMoney;

        public String getTotalDayCount() {
            return TotalDayCount;
        }

        public void setTotalDayCount(String TotalDayCount) {
            this.TotalDayCount = TotalDayCount;
        }

        public String getTotalDayMoney() {
            return TotalDayMoney;
        }

        public void setTotalDayMoney(String TotalDayMoney) {
            this.TotalDayMoney = TotalDayMoney;
        }

        public String getTotalYestCount() {
            return TotalYestCount;
        }

        public void setTotalYestCount(String TotalYestCount) {
            this.TotalYestCount = TotalYestCount;
        }

        public String getTotalYestMoney() {
            return TotalYestMoney;
        }

        public void setTotalYestMoney(String TotalYestMoney) {
            this.TotalYestMoney = TotalYestMoney;
        }

        public String getTotalMonthCount() {
            return TotalMonthCount;
        }

        public void setTotalMonthCount(String TotalMonthCount) {
            this.TotalMonthCount = TotalMonthCount;
        }

        public String getTotalMonthMoney() {
            return TotalMonthMoney;
        }

        public void setTotalMonthMoney(String TotalMonthMoney) {
            this.TotalMonthMoney = TotalMonthMoney;
        }
    }

    public static class ZhifubaoBean {
        /**
         * TotalDayCount : 0
         * TotalDayMoney : 0.00
         * TotalYestCount : 0
         * TotalYestMoney : 0.00
         * TotalMonthCount : 0
         * TotalMonthMoney : 0.00
         */

        private String TotalDayCount;
        private String TotalDayMoney;
        private String TotalYestCount;
        private String TotalYestMoney;
        private String TotalMonthCount;
        private String TotalMonthMoney;

        public String getTotalDayCount() {
            return TotalDayCount;
        }

        public void setTotalDayCount(String TotalDayCount) {
            this.TotalDayCount = TotalDayCount;
        }

        public String getTotalDayMoney() {
            return TotalDayMoney;
        }

        public void setTotalDayMoney(String TotalDayMoney) {
            this.TotalDayMoney = TotalDayMoney;
        }

        public String getTotalYestCount() {
            return TotalYestCount;
        }

        public void setTotalYestCount(String TotalYestCount) {
            this.TotalYestCount = TotalYestCount;
        }

        public String getTotalYestMoney() {
            return TotalYestMoney;
        }

        public void setTotalYestMoney(String TotalYestMoney) {
            this.TotalYestMoney = TotalYestMoney;
        }

        public String getTotalMonthCount() {
            return TotalMonthCount;
        }

        public void setTotalMonthCount(String TotalMonthCount) {
            this.TotalMonthCount = TotalMonthCount;
        }

        public String getTotalMonthMoney() {
            return TotalMonthMoney;
        }

        public void setTotalMonthMoney(String TotalMonthMoney) {
            this.TotalMonthMoney = TotalMonthMoney;
        }
    }

    public static class BaiduBean {
        /**
         * TotalDayCount : 0
         * TotalDayMoney : 0.00
         * TotalYestCount : 0
         * TotalYestMoney : 0.00
         * TotalMonthCount : 0
         * TotalMonthMoney : 0.00
         */

        private String TotalDayCount;
        private String TotalDayMoney;
        private String TotalYestCount;
        private String TotalYestMoney;
        private String TotalMonthCount;
        private String TotalMonthMoney;

        public String getTotalDayCount() {
            return TotalDayCount;
        }

        public void setTotalDayCount(String TotalDayCount) {
            this.TotalDayCount = TotalDayCount;
        }

        public String getTotalDayMoney() {
            return TotalDayMoney;
        }

        public void setTotalDayMoney(String TotalDayMoney) {
            this.TotalDayMoney = TotalDayMoney;
        }

        public String getTotalYestCount() {
            return TotalYestCount;
        }

        public void setTotalYestCount(String TotalYestCount) {
            this.TotalYestCount = TotalYestCount;
        }

        public String getTotalYestMoney() {
            return TotalYestMoney;
        }

        public void setTotalYestMoney(String TotalYestMoney) {
            this.TotalYestMoney = TotalYestMoney;
        }

        public String getTotalMonthCount() {
            return TotalMonthCount;
        }

        public void setTotalMonthCount(String TotalMonthCount) {
            this.TotalMonthCount = TotalMonthCount;
        }

        public String getTotalMonthMoney() {
            return TotalMonthMoney;
        }

        public void setTotalMonthMoney(String TotalMonthMoney) {
            this.TotalMonthMoney = TotalMonthMoney;
        }
    }

    public static class QqBean {
        /**
         * TotalDayCount : 0
         * TotalDayMoney : 0.00
         * TotalYestCount : 0
         * TotalYestMoney : 0.00
         * TotalMonthCount : 0
         * TotalMonthMoney : 0.00
         */

        private String TotalDayCount;
        private String TotalDayMoney;
        private String TotalYestCount;
        private String TotalYestMoney;
        private String TotalMonthCount;
        private String TotalMonthMoney;

        public String getTotalDayCount() {
            return TotalDayCount;
        }

        public void setTotalDayCount(String TotalDayCount) {
            this.TotalDayCount = TotalDayCount;
        }

        public String getTotalDayMoney() {
            return TotalDayMoney;
        }

        public void setTotalDayMoney(String TotalDayMoney) {
            this.TotalDayMoney = TotalDayMoney;
        }

        public String getTotalYestCount() {
            return TotalYestCount;
        }

        public void setTotalYestCount(String TotalYestCount) {
            this.TotalYestCount = TotalYestCount;
        }

        public String getTotalYestMoney() {
            return TotalYestMoney;
        }

        public void setTotalYestMoney(String TotalYestMoney) {
            this.TotalYestMoney = TotalYestMoney;
        }

        public String getTotalMonthCount() {
            return TotalMonthCount;
        }

        public void setTotalMonthCount(String TotalMonthCount) {
            this.TotalMonthCount = TotalMonthCount;
        }

        public String getTotalMonthMoney() {
            return TotalMonthMoney;
        }

        public void setTotalMonthMoney(String TotalMonthMoney) {
            this.TotalMonthMoney = TotalMonthMoney;
        }
    }

    public static class JindonBean {
        /**
         * TotalDayCount : 0
         * TotalDayMoney : 0.00
         * TotalYestCount : 0
         * TotalYestMoney : 0.00
         * TotalMonthCount : 0
         * TotalMonthMoney : 0.00
         */

        private String TotalDayCount;
        private String TotalDayMoney;
        private String TotalYestCount;
        private String TotalYestMoney;
        private String TotalMonthCount;
        private String TotalMonthMoney;

        public String getTotalDayCount() {
            return TotalDayCount;
        }

        public void setTotalDayCount(String TotalDayCount) {
            this.TotalDayCount = TotalDayCount;
        }

        public String getTotalDayMoney() {
            return TotalDayMoney;
        }

        public void setTotalDayMoney(String TotalDayMoney) {
            this.TotalDayMoney = TotalDayMoney;
        }

        public String getTotalYestCount() {
            return TotalYestCount;
        }

        public void setTotalYestCount(String TotalYestCount) {
            this.TotalYestCount = TotalYestCount;
        }

        public String getTotalYestMoney() {
            return TotalYestMoney;
        }

        public void setTotalYestMoney(String TotalYestMoney) {
            this.TotalYestMoney = TotalYestMoney;
        }

        public String getTotalMonthCount() {
            return TotalMonthCount;
        }

        public void setTotalMonthCount(String TotalMonthCount) {
            this.TotalMonthCount = TotalMonthCount;
        }

        public String getTotalMonthMoney() {
            return TotalMonthMoney;
        }

        public void setTotalMonthMoney(String TotalMonthMoney) {
            this.TotalMonthMoney = TotalMonthMoney;
        }
    }

    public static class AccountBean {
        /**
         * TotalDayCount : 0
         * TotalDayMoney : 0.00
         * TotalYestCount : 0
         * TotalYestMoney : 0.00
         * TotalMonthCount : 0
         * TotalMonthMoney : 0.00
         */

        private String TotalDayCount;
        private String TotalDayMoney;
        private String TotalYestCount;
        private String TotalYestMoney;
        private String TotalMonthCount;
        private String TotalMonthMoney;

        public String getTotalDayCount() {
            return TotalDayCount;
        }

        public void setTotalDayCount(String TotalDayCount) {
            this.TotalDayCount = TotalDayCount;
        }

        public String getTotalDayMoney() {
            return TotalDayMoney;
        }

        public void setTotalDayMoney(String TotalDayMoney) {
            this.TotalDayMoney = TotalDayMoney;
        }

        public String getTotalYestCount() {
            return TotalYestCount;
        }

        public void setTotalYestCount(String TotalYestCount) {
            this.TotalYestCount = TotalYestCount;
        }

        public String getTotalYestMoney() {
            return TotalYestMoney;
        }

        public void setTotalYestMoney(String TotalYestMoney) {
            this.TotalYestMoney = TotalYestMoney;
        }

        public String getTotalMonthCount() {
            return TotalMonthCount;
        }

        public void setTotalMonthCount(String TotalMonthCount) {
            this.TotalMonthCount = TotalMonthCount;
        }

        public String getTotalMonthMoney() {
            return TotalMonthMoney;
        }

        public void setTotalMonthMoney(String TotalMonthMoney) {
            this.TotalMonthMoney = TotalMonthMoney;
        }
    }

    public static class AllTotalBean {
        /**
         * TotalDayCount : 0
         * TotalDayMoney : 0.00
         * TotalYestCount : 1
         * TotalYestMoney : 0.01
         * TotalMonthCount : 1
         * TotalMonthMoney : 0.01
         * LastmonthCount : 3
         * LastmonthMoney : 0.03
         */

        private String TotalDayCount;
        private String TotalDayMoney;
        private String TotalYestCount;
        private String TotalYestMoney;
        private String TotalMonthCount;
        private String TotalMonthMoney;
        private String LastmonthCount;
        private String LastmonthMoney;

        public String getTotalDayCount() {
            return TotalDayCount;
        }

        public void setTotalDayCount(String TotalDayCount) {
            this.TotalDayCount = TotalDayCount;
        }

        public String getTotalDayMoney() {
            return TotalDayMoney;
        }

        public void setTotalDayMoney(String TotalDayMoney) {
            this.TotalDayMoney = TotalDayMoney;
        }

        public String getTotalYestCount() {
            return TotalYestCount;
        }

        public void setTotalYestCount(String TotalYestCount) {
            this.TotalYestCount = TotalYestCount;
        }

        public String getTotalYestMoney() {
            return TotalYestMoney;
        }

        public void setTotalYestMoney(String TotalYestMoney) {
            this.TotalYestMoney = TotalYestMoney;
        }

        public String getTotalMonthCount() {
            return TotalMonthCount;
        }

        public void setTotalMonthCount(String TotalMonthCount) {
            this.TotalMonthCount = TotalMonthCount;
        }

        public String getTotalMonthMoney() {
            return TotalMonthMoney;
        }

        public void setTotalMonthMoney(String TotalMonthMoney) {
            this.TotalMonthMoney = TotalMonthMoney;
        }

        public String getLastmonthCount() {
            return LastmonthCount;
        }

        public void setLastmonthCount(String LastmonthCount) {
            this.LastmonthCount = LastmonthCount;
        }

        public String getLastmonthMoney() {
            return LastmonthMoney;
        }

        public void setLastmonthMoney(String LastmonthMoney) {
            this.LastmonthMoney = LastmonthMoney;
        }
    }
}
