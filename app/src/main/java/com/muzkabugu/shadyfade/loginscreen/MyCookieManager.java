package com.muzkabugu.shadyfade.loginscreen;

/**
 * Created by shadyfade on 8/15/15.
 */
import java.io.IOException;
import java.net.CookieManager;
import java.net.URI;
import java.util.List;
import java.util.Map;

class MyCookieManager extends CookieManager {

    @Override
    public void put(URI uri, Map<String, List<String>> stringListMap) throws IOException {
        super.put(uri, stringListMap);
        if (stringListMap != null && stringListMap.get("Set-Cookie") != null)
            for (String string : stringListMap.get("Set-Cookie")) {
                if (string.contains("userid")) {
                    //save your cookie here
                }
            }
    }
}