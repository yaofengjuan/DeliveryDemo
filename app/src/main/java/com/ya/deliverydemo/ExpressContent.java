package com.ya.deliverydemo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YaoFengjuan on 2016/6/20.
 */
public class ExpressContent {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"data":[{"context":"顺丰速运 已收取快件","time":"2015-05-11 21:03:33"},{"context":"快件到达 【青岛流亭集散中心】","time":"2015-05-11 21:59:56"},{"context":"快件正转运至 <a style='color:red;text-decoration:none;' href='javascript:void(0)' link-type='store-point' store-type='1' virtual-address-code='532BN'><font color='red'>【青岛北安服务点】<\/font><\/a>","time":"2015-05-11 23:23:42"},{"context":"快件到达 <a style='color:red;text-decoration:none;' href='javascript:void(0)' link-type='store-point' store-type='1' virtual-address-code='532BN'><font color='red'>【青岛北安服务点】<\/font><\/a>","time":"2015-05-12 05:53:53"},{"context":"正在派送途中,请您准备签收<br/>","time":"2015-05-12 07:28:39"},{"context":"已签收,感谢使用顺丰,期待再次为您服务","time":"2015-05-12 09:25:38"},{"context":"在官网\"运单资料&签收图\",可查看签收人信息","time":"2015-05-12 09:25:38"}],"expSpellName":"shunfeng","flag":true,"mailNo":"305980661496","ret_code":0,"status":4}
     */

    public int showapi_res_code;
    public String showapi_res_error;
    /**
     * data : [{"context":"顺丰速运 已收取快件","time":"2015-05-11 21:03:33"},{"context":"快件到达 【青岛流亭集散中心】","time":"2015-05-11 21:59:56"},{"context":"快件正转运至 <a style='color:red;text-decoration:none;' href='javascript:void(0)' link-type='store-point' store-type='1' virtual-address-code='532BN'><font color='red'>【青岛北安服务点】<\/font><\/a>","time":"2015-05-11 23:23:42"},{"context":"快件到达 <a style='color:red;text-decoration:none;' href='javascript:void(0)' link-type='store-point' store-type='1' virtual-address-code='532BN'><font color='red'>【青岛北安服务点】<\/font><\/a>","time":"2015-05-12 05:53:53"},{"context":"正在派送途中,请您准备签收<br/>","time":"2015-05-12 07:28:39"},{"context":"已签收,感谢使用顺丰,期待再次为您服务","time":"2015-05-12 09:25:38"},{"context":"在官网\"运单资料&签收图\",可查看签收人信息","time":"2015-05-12 09:25:38"}]
     * expSpellName : shunfeng
     * flag : true
     * mailNo : 305980661496
     * ret_code : 0
     * status : 4
     */

    public ShowapiResBodyEntity showapi_res_body;

    public static ExpressContent objectFromData(String str) {

        return new Gson().fromJson(str, ExpressContent.class);
    }

    public static ExpressContent objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ExpressContent.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ExpressContent> arrayExpressContentFromData(String str) {

        Type listType = new TypeToken<ArrayList<ExpressContent>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ExpressContent> arrayExpressContentFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ExpressContent>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public static class ShowapiResBodyEntity {
        public String expSpellName;
        public boolean flag;
        public String mailNo;
        public int ret_code;
        public int status;
        /**
         * context : 顺丰速运 已收取快件
         * time : 2015-05-11 21:03:33
         */

        public List<DataEntity> data;
        public String msg;

        public static ShowapiResBodyEntity objectFromData(String str) {

            return new Gson().fromJson(str, ShowapiResBodyEntity.class);
        }

        public static ShowapiResBodyEntity objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), ShowapiResBodyEntity.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<ShowapiResBodyEntity> arrayShowapiResBodyEntityFromData(String str) {

            Type listType = new TypeToken<ArrayList<ShowapiResBodyEntity>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<ShowapiResBodyEntity> arrayShowapiResBodyEntityFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<ShowapiResBodyEntity>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public static class DataEntity {
            public String context;
            public String time;

            public static DataEntity objectFromData(String str) {

                return new Gson().fromJson(str, DataEntity.class);
            }

            public static DataEntity objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), DataEntity.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<DataEntity> arrayDataEntityFromData(String str) {

                Type listType = new TypeToken<ArrayList<DataEntity>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<DataEntity> arrayDataEntityFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<DataEntity>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }
        }
    }
}
