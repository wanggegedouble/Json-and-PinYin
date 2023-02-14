package com.wy;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.Set;

public class JSONDemo {

    public static void main(String[] args) {
        String url = "https://api.seniverse.com/v3/weather/now.json?key=SV44O7Y6pg0VrZH_p&location=chengdu&language=zh-Hans&unit=c";
        //这个属于发起请求并获取json数据（得引入hutool，即上面引入的第二个依赖）
        String json= HttpUtil.createGet(url).execute().body();
        //转化请求的 json 数据
        JSONObject jsonResult = JSONObject.parseObject(json);
        //获取 results 数组
        JSONArray results = jsonResult.getJSONArray("results");
        JSONObject jsonObject = results.getJSONObject(0);
        JSONObject location = jsonObject.getJSONObject("location");

        JSONDemo jsonDemo = new JSONDemo();
        JSONArray allKey = jsonDemo.getAllKey(location);
        System.out.println(allKey);
    }

    /**
     * 读取所有的key
     *
     * @param jsonObject
     */
    public JSONArray getAllKey(JSONObject jsonObject) {
        JSONArray arr= new JSONArray();
        Iterator<String> keys = jsonObject.keySet().iterator();// jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            arr.add(key);
        }
        return arr;
    }
}
