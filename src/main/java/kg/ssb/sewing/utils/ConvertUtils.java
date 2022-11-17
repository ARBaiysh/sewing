package kg.ssb.sewing.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.base.CaseFormat;

import java.util.HashMap;
import java.util.Map;

public class ConvertUtils {
    public static <T> T toCamelCase(Map<String, Object> map, Class<T> clazz) {
        Map<String, Object> var = new HashMap<>();
        map.forEach((k, v) -> {
            String s = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, k);
            var.put(s, v);
        });
        return JSON.parseObject(JSON.toJSONString(var), clazz);
    }
}
