package edu.cmu.cmulib.API.ml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonUtil {


	public static Map<String, String> jsonToMap(JSONObject json) throws JSONException {
        Map<String, String> retMap = new HashMap<String, String>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }
	

    public static Map<String, String> toMap(JSONObject object) throws JSONException {
        Map<String, String> map = new HashMap<String, String>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            String value = object.get(key).toString();
            map.put(key, value);
        }
        return map;
    }

}
