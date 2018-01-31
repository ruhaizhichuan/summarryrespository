package org.rhzc.util;

import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtils {

    private static Pattern patternForProtocal = Pattern.compile("[\\w]+://");

    //移除协议
    public static String removeProtocol(String url) {
        return patternForProtocal.matcher(url).replaceAll("");
    }

    //获取域名
    public static String getDomain(String url) {
    	
        String domain = removeProtocol(url);
        
        domain = domain.substring(0, domain.indexOf("/"));
        
        return removePort(domain);
    }

    //移除端口号
    public static String removePort(String domain) {
        int portIndex = domain.indexOf(":");
        if (portIndex != -1) {
            return domain.substring(0, portIndex);
        }else {
            return domain;
        }
    }

    private static final Pattern patternForCharset = Pattern.compile("charset\\s*=\\s*['\"]*([^\\s;'\"]*)", Pattern.CASE_INSENSITIVE);

    public static String getCharset(String contentType) {
        Matcher matcher = patternForCharset.matcher(contentType);
        if (matcher.find()) {
            String charset = matcher.group(1);
            if (Charset.isSupported(charset)) {
                return charset;
            }
        }
        return null;
    }
}
