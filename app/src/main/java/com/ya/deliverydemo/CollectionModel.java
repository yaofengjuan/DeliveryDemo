package com.ya.deliverydemo;

import android.util.Log;

import com.show.api.ShowApiRequest;
import com.ya.deliverydemo.ExpressContent.ShowapiResBodyEntity.DataEntity;

/**
 * Created by YaoFengjuan on 2016/6/24.
 */
public class CollectionModel implements ILoadModel<ExpressInfo> {


    private ExpressInfo old;

    public ExpressInfo update(ExpressInfo old) {
        this.old = old;
        return loadInfo();
    }

    @Override
    public ExpressInfo loadInfo() {
        //获取公司信息
        String result = new ShowApiRequest(Constant.BASE_URL + "19", Constant.appid, Constant.secret)
                .addTextPara("com", old.getSimpleName())
                .addTextPara("nu", old.getMailNo())
                .post();
        Log.d(this.getClass().getName(), "result------>" + result);
        ExpressContent content = ExpressContent.objectFromData(result);
        DataEntity lastMsg = content.showapi_res_body.data.get(0);
        if (lastMsg != null) {
            old.setLastMsg(lastMsg.context);
        }
        return old;
    }
}
