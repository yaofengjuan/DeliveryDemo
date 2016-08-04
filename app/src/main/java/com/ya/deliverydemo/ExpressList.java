package com.ya.deliverydemo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YaoFengjuan on 2016/6/14.
 */
public class ExpressList {


    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"expressList":[{"expName":"申通快递","imgUrl":"http://app2.showapi.com/img/expImg/shentong.jpg","itf1":"kiees","itf2":"akd","itf3":"ickd","itf4":"","note":"","phone":"400-889-5543","simpleName":"shentong","url":"http://www.sto.cn"},{"expName":"圆通速递","imgUrl":"http://app2.showapi.com/img/expImg/yuantong.jpg","itf1":"off","itf2":"akd","itf3":"kiees","itf4":"","note":"","phone":"021-69777888/999","simpleName":"yuantong","url":"http://www.yto.net.cn"},{"expName":"韵达快运","imgUrl":"http://app2.showapi.com/img/expImg/yunda.jpg","itf1":"kiees","itf2":"akd","itf3":"ickd","itf4":"","note":"","phone":"400-821-6789","simpleName":"yunda","url":"http://www.yundaex.com"},{"expName":"中通快递","imgUrl":"http://app2.showapi.com/img/expImg/zto.jpg","itf1":"off","itf2":"akd","itf3":"ickd","itf4":"","note":"","phone":"021-39777777","simpleName":"zhongtong","url":"http://www.zto.cn"},{"expName":"顺丰速运","imgUrl":"http://app2.showapi.com/img/expImg/shunfeng.jpg","itf1":"off","itf2":"akd","itf3":"ickd","itf4":"","note":"","phone":"95338","simpleName":"shunfeng","url":"http://www.sf-express.com"},{"expName":"百世快递","imgUrl":"http://app2.showapi.com/img/expImg/ht.jpg","itf1":"kiees","itf2":"akd","itf3":"ickd","itf4":"","note":"","phone":"021-62963636","simpleName":"huitong","url":"http://www.htky365.com"},{"expName":"天天快递","imgUrl":"http://app2.showapi.com/img/expImg/tt.jpg","itf1":"off","itf2":"akd","itf3":"kiees","itf4":"ickd","note":"","phone":"400-820-8198","simpleName":"tiantian","url":"http://www.ttkdex.com"},{"expName":"宅急送","imgUrl":"http://app2.showapi.com/img/expImg/zjs_logo.gif","itf1":"off","itf2":"ickd","itf3":"akd","itf4":"kiees","note":"","phone":"4006-789-000","simpleName":"zhaijisong","url":"http://www.zjs.com.cn"},{"expName":"全峰快递","imgUrl":"http://app2.showapi.com/img/expImg/quanfeng.jpg","itf1":"off","itf2":"akd","itf3":"ickd","itf4":"","note":"","phone":"400-100-0001","simpleName":"quanfeng","url":"http://www.qfkd.com.cn"},{"expName":"德邦物流","imgUrl":"http://app2.showapi.com/img/expImg/dbwl_logo.gif","itf1":"off","itf2":"ickd","itf3":"akd","itf4":"kiees","note":"","phone":"400-830-5555","simpleName":"debang","url":"http://www.deppon.com"},{"expName":"AAE快递","imgUrl":"http://app2.showapi.com/img/expImg/aae.jpg","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"400-610-0400","simpleName":"aae","url":"http://cn.aaeweb.com"},{"expName":"安信达快递","imgUrl":"http://app2.showapi.com/img/expImg/axd.jpg","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"查询电话：021-54224681 54224682 54224685","simpleName":"anxinda","url":"http://www.anxinda.com"},{"expName":"安捷快递","imgUrl":"http://app2.showapi.com/img/expImg/anjie.jpg","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"400565656","simpleName":"anjie","url":"http://www.anjelex.com"},{"expName":"Aramex","imgUrl":"http://app2.showapi.com/img/expImg/aramex.jpg","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"+86 (0571) 85092777","simpleName":"aramex","url":"http://www.aramex.com"},{"expName":"爱彼西快递","imgUrl":"http://app2.showapi.com/img/expImg/abc.jpg","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"400-711-8899/400-818-0571","simpleName":"abc","url":"http://www.abccod.com"},{"expName":"安迅物流","imgUrl":"http://app2.showapi.com/img/expImg/anxl_logo.gif","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"010-5928 8730","simpleName":"anxun","url":"http://www.anxl.com.cn"},{"expName":"安得物流","imgUrl":"http://app2.showapi.com/img/expImg/ande.jpg","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"400-000-0056","simpleName":"ande","url":"http://www.annto.com"},{"expName":"AOL快递","imgUrl":"http://app2.showapi.com/img/expImg/aol.jpg","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"020-36413533","simpleName":"aol","url":"http://www.aol-au.com"},{"expName":"百福东方快递","imgUrl":"http://app2.showapi.com/img/expImg/ees.jpg","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"010-57169000","simpleName":"ees","url":"http://www.ees.com.cn"},{"expName":"包裹/挂号信/小包","imgUrl":"http://app2.showapi.com/img/expImg/pingyou.jpg","itf1":"off","itf2":"ickd","itf3":"akd","itf4":"kiees","note":"","phone":"11185","simpleName":"pingyou","url":"http://www.chinapost.com.cn"}],"flag":true,"ret_code":0}
     */

    public int showapi_res_code;
    public String showapi_res_error;
    /**
     * expressList : [{"expName":"申通快递","imgUrl":"http://app2.showapi.com/img/expImg/shentong.jpg","itf1":"kiees","itf2":"akd","itf3":"ickd","itf4":"","note":"","phone":"400-889-5543","simpleName":"shentong","url":"http://www.sto.cn"},{"expName":"圆通速递","imgUrl":"http://app2.showapi.com/img/expImg/yuantong.jpg","itf1":"off","itf2":"akd","itf3":"kiees","itf4":"","note":"","phone":"021-69777888/999","simpleName":"yuantong","url":"http://www.yto.net.cn"},{"expName":"韵达快运","imgUrl":"http://app2.showapi.com/img/expImg/yunda.jpg","itf1":"kiees","itf2":"akd","itf3":"ickd","itf4":"","note":"","phone":"400-821-6789","simpleName":"yunda","url":"http://www.yundaex.com"},{"expName":"中通快递","imgUrl":"http://app2.showapi.com/img/expImg/zto.jpg","itf1":"off","itf2":"akd","itf3":"ickd","itf4":"","note":"","phone":"021-39777777","simpleName":"zhongtong","url":"http://www.zto.cn"},{"expName":"顺丰速运","imgUrl":"http://app2.showapi.com/img/expImg/shunfeng.jpg","itf1":"off","itf2":"akd","itf3":"ickd","itf4":"","note":"","phone":"95338","simpleName":"shunfeng","url":"http://www.sf-express.com"},{"expName":"百世快递","imgUrl":"http://app2.showapi.com/img/expImg/ht.jpg","itf1":"kiees","itf2":"akd","itf3":"ickd","itf4":"","note":"","phone":"021-62963636","simpleName":"huitong","url":"http://www.htky365.com"},{"expName":"天天快递","imgUrl":"http://app2.showapi.com/img/expImg/tt.jpg","itf1":"off","itf2":"akd","itf3":"kiees","itf4":"ickd","note":"","phone":"400-820-8198","simpleName":"tiantian","url":"http://www.ttkdex.com"},{"expName":"宅急送","imgUrl":"http://app2.showapi.com/img/expImg/zjs_logo.gif","itf1":"off","itf2":"ickd","itf3":"akd","itf4":"kiees","note":"","phone":"4006-789-000","simpleName":"zhaijisong","url":"http://www.zjs.com.cn"},{"expName":"全峰快递","imgUrl":"http://app2.showapi.com/img/expImg/quanfeng.jpg","itf1":"off","itf2":"akd","itf3":"ickd","itf4":"","note":"","phone":"400-100-0001","simpleName":"quanfeng","url":"http://www.qfkd.com.cn"},{"expName":"德邦物流","imgUrl":"http://app2.showapi.com/img/expImg/dbwl_logo.gif","itf1":"off","itf2":"ickd","itf3":"akd","itf4":"kiees","note":"","phone":"400-830-5555","simpleName":"debang","url":"http://www.deppon.com"},{"expName":"AAE快递","imgUrl":"http://app2.showapi.com/img/expImg/aae.jpg","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"400-610-0400","simpleName":"aae","url":"http://cn.aaeweb.com"},{"expName":"安信达快递","imgUrl":"http://app2.showapi.com/img/expImg/axd.jpg","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"查询电话：021-54224681 54224682 54224685","simpleName":"anxinda","url":"http://www.anxinda.com"},{"expName":"安捷快递","imgUrl":"http://app2.showapi.com/img/expImg/anjie.jpg","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"400565656","simpleName":"anjie","url":"http://www.anjelex.com"},{"expName":"Aramex","imgUrl":"http://app2.showapi.com/img/expImg/aramex.jpg","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"+86 (0571) 85092777","simpleName":"aramex","url":"http://www.aramex.com"},{"expName":"爱彼西快递","imgUrl":"http://app2.showapi.com/img/expImg/abc.jpg","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"400-711-8899/400-818-0571","simpleName":"abc","url":"http://www.abccod.com"},{"expName":"安迅物流","imgUrl":"http://app2.showapi.com/img/expImg/anxl_logo.gif","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"010-5928 8730","simpleName":"anxun","url":"http://www.anxl.com.cn"},{"expName":"安得物流","imgUrl":"http://app2.showapi.com/img/expImg/ande.jpg","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"400-000-0056","simpleName":"ande","url":"http://www.annto.com"},{"expName":"AOL快递","imgUrl":"http://app2.showapi.com/img/expImg/aol.jpg","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"020-36413533","simpleName":"aol","url":"http://www.aol-au.com"},{"expName":"百福东方快递","imgUrl":"http://app2.showapi.com/img/expImg/ees.jpg","itf1":"","itf2":"","itf3":"","itf4":"","note":"","phone":"010-57169000","simpleName":"ees","url":"http://www.ees.com.cn"},{"expName":"包裹/挂号信/小包","imgUrl":"http://app2.showapi.com/img/expImg/pingyou.jpg","itf1":"off","itf2":"ickd","itf3":"akd","itf4":"kiees","note":"","phone":"11185","simpleName":"pingyou","url":"http://www.chinapost.com.cn"}]
     * flag : true
     * ret_code : 0
     */

    public ShowapiResBodyEntity showapi_res_body;

    public static ExpressList objectFromData(String str) {

        return new Gson().fromJson(str, ExpressList.class);
    }

    public static ExpressList objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ExpressList.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ExpressList> arrayExpressListFromData(String str) {

        Type listType = new TypeToken<ArrayList<ExpressList>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ExpressList> arrayExpressListFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ExpressList>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public static class ShowapiResBodyEntity {
        public boolean flag;
        public int ret_code;
        /**
         * expName : 申通快递
         * imgUrl : http://app2.showapi.com/img/expImg/shentong.jpg
         * itf1 : kiees
         * itf2 : akd
         * itf3 : ickd
         * itf4 :
         * note :
         * phone : 400-889-5543
         * simpleName : shentong
         * url : http://www.sto.cn
         */

        public List<ExpressListEntity> expressList;
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

        public static class ExpressListEntity {
            public String expName;
            public String imgUrl;
            public String itf1;
            public String itf2;
            public String itf3;
            public String itf4;
            public String note;
            public String phone;
            public String simpleName;
            public String url;

            public static ExpressListEntity objectFromData(String str) {

                return new Gson().fromJson(str, ExpressListEntity.class);
            }

            public static ExpressListEntity objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), ExpressListEntity.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<ExpressListEntity> arrayExpressListEntityFromData(String str) {

                Type listType = new TypeToken<ArrayList<ExpressListEntity>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<ExpressListEntity> arrayExpressListEntityFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<ExpressListEntity>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            @Override
            public String toString() {
                return "ExpressListEntity{" +
                        "expName='" + expName + '\'' +
                        ", imgUrl='" + imgUrl + '\'' +
                        ", itf1='" + itf1 + '\'' +
                        ", itf2='" + itf2 + '\'' +
                        ", itf3='" + itf3 + '\'' +
                        ", itf4='" + itf4 + '\'' +
                        ", note='" + note + '\'' +
                        ", phone='" + phone + '\'' +
                        ", simpleName='" + simpleName + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }
        }
    }
}
