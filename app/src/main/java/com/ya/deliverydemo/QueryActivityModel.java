package com.ya.deliverydemo;

import android.util.Log;

import com.show.api.ShowApiRequest;
import com.ya.deliverydemo.entity.ExpressContent;

/**
 * Created by YaoFengjuan on 2016/6/22.
 */
public class QueryActivityModel implements ILoadModel<ExpressContent> {

    private String simpleName;
    private String mailNo;

    public QueryActivityModel() {
    }

    public void setParameter(String simpleName,String mailNo) {
        this.simpleName = simpleName;
        this.mailNo = mailNo;
    }

    @Override
    public ExpressContent loadInfo() {
        Log.d(this.getClass().getName(), "expTextName：" + simpleName + "  mailNo：" + mailNo);
        String result = new ShowApiRequest(Constant.BASE_URL + "19", Constant.appid, Constant.secret)
                .addTextPara("com", simpleName)
                .addTextPara("nu", mailNo)
                .post();
        Log.d(this.getClass().getName(), "result------>" + result);
        ExpressContent content = ExpressContent.objectFromData(result);
        return content;
    }
}
