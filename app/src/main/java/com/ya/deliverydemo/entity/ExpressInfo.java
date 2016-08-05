package com.ya.deliverydemo.entity;

import com.google.gson.Gson;

/**
 * Created by YaoFengjuan on 2016/6/24.
 */
public class ExpressInfo {
    private String expSpellName;//快递名称
    private String mailNo;//快递单号
    private String simpleName;//快递简称
    private String logoUrl;
    private String lastMsg;//最后状态
    private String remarkName;//备注名

    public ExpressInfo objectFromData(String str) {
        return new Gson().fromJson(str, ExpressInfo.class);
    }

    public ExpressInfo(String expSpellName, String mailNo, String simpleName, String logoUrl, String lastMsg, String remarkName) {
        this.expSpellName = expSpellName;
        this.mailNo = mailNo;
        this.simpleName = simpleName;
        this.logoUrl = logoUrl;
        this.lastMsg = lastMsg;
        this.remarkName = remarkName;
    }


    @Override
    public String toString() {
        return "ExpressInfo{" +
                "expSpellName='" + expSpellName + '\'' +
                ", mailNo='" + mailNo + '\'' +
                ", simpleName='" + simpleName + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", lastMsg='" + lastMsg + '\'' +
                ", remarkName='" + remarkName + '\'' +
                '}';
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public String getExpSpellName() {
        return expSpellName;
    }

    public void setExpSpellName(String expSpellName) {
        this.expSpellName = expSpellName;
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }
}
