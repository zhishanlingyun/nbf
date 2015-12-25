package com.nbf.common;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PatternUtil {
    public static String patternDecode(String msg) {
        return msg.replaceAll("@c13@", "\n");
    }
    public static String getData(String regex, String msg) {
        Matcher m0 = Pattern.compile(regex).matcher(msg);
        if (m0.find()) {
            return m0.group(1);
        }
        return null;
    }
    public static String patternEncode(String msg) {
        StringBuilder buff = new StringBuilder(msg.length() + 100);
        char c[] = msg.toCharArray();
        for (int i = 0; i < c.length; i++) {
            int cnum = c[i];
            if (cnum == 10 || cnum == 13) {
                if (cnum == 10)
                    buff.append("@c13@");
            } else {
                buff.append(c[i]);
            }
        }
        return buff.toString();
    }
    public static String getMeta(String name, String html) {
        String regex = "<meta(.+?)name=\"" + name + "\"(.+?)content=\"(.+?)\"";
        return PatternUtil.getGroupN(html, regex, 3);
    }
    public static String replace(String source, String regex, String replacement) {
        StringBuffer buff = new StringBuffer(source.length() + 100);
        char c[] = source.toCharArray();
        for (int i = 0; i < c.length; i++) {
            int cnum = c[i];
            if (cnum == 10 || cnum == 13) {
                if (cnum == 10)
                    buff.append("@_n@");
            } else {
                buff.append(c[i]);
            }
        }
        source = buff.toString();
        String str = source.replaceAll(regex, replacement);
        return str.replaceAll("@_n@", "\n");
    }
    public final static String  HREF      = "<[Aa](.+?)(href|HREF)=['\"](.+?)['\"](.+?){0,1}>(.+?)</[aA]>";
    public final static String  HOST      = "(http|https|HTTP|HTTPS)://(.+?):{0}\\d{0}/";
    public final static String  BASE      = "(http|https|HTTP|HTTPS)://\\S{1}/";
    public final static String  BASE2     = "(http|https|HTTP|HTTPS)://\\S{1}/";
    public final static String  NUMID_URL = "(http|https|HTTP|HTTPS)://(.+?)/\\S{1}/(.+?)\\d{1}";
    public final static Pattern P_HREF    = Pattern.compile(HREF);                                            ;
    public final static String  HREF2     = "<(A|a)(.+?)(href|HREF)]=['\"](.+?)['\"](.+?){0,1}>(.+?)</[aA]>";
    public static Matcher matcher(Pattern pattern, String input) {
        return pattern.matcher(patternEncode(input));
    }
   
    public static String getPatternRegex(String url) {
        url = url.replaceAll("\\.", "\\\\.");
        url = url.replaceAll("\\?", "\\\\?");
        url = url.replaceAll("\\d{1}", "\\\\d{1}");
        return url;
    }
    public static String getUrlPatternRegex(String url) {
        String url0 = getGroup(url, NUMID_URL, 1) + "://" + getGroup(url, NUMID_URL, 2) + "@##@" + getGroup(url, NUMID_URL, 3) + "#@@#";
        System.err.println(url0);
        url0 = url0.replaceAll("\\.", "\\\\.");
        url0 = url0.replaceAll("\\?", "\\\\?");
        url0 = url0.replaceAll("#@@#", "\\\\d{1}");
        url0 = url0.replaceAll("@##@", "\\(\\.\\+\\?\\)");
        System.err.println(url0);
        return url0;
    }
    public static String getUrlPatternRegex2(String url) {
        String regex = BASE;
        String host = getGroup(url, regex);
     //   org.noe.framework.util.log.Log.debug("hostddd=" + getGroup(url, NUMID_URL, 1) + "(.+?)" + getGroup(url, NUMID_URL, 2) + "\\d{1}");
       // org.noe.framework.util.log.Log.debug("host1=" + getGroup(url, HOST) + "(.+?)");
        url = url.replaceAll("\\.", "\\\\.");
        url = url.replaceAll("\\?", "\\\\?");
        url = url.replaceAll("\\d{1}", "\\\\d{1}");
        return url;
    }
//    public static List<Link> getHref(String input) {
//        Matcher m0 = matcher(P_HREF, input);
//        List<Link> list = new ArrayList<Link>();
//        while (m0.find()) {
//            // String s0 = m0.group();
//            // String s1 = m0.group(1);
//            // String s2 = m0.group(2);
//            Link link = new Link();
//            link.setUrl(m0.group(2));
//            link.setTitle(m0.group(4));
//            // org.noe.framework.util.log.Log.debug(s0 + "||" + s1 + "||" + s2+"#"+m0.group(3));
//            list.add(link);
//        }
//        return list;
//    }
    public static List<String> getFindList(String regex, String input) {
        Matcher m0 = matcher(Pattern.compile(regex), input);
        List<String> list = new ArrayList<String>();
        while (m0.find()) {
            list.add(m0.group());
        }
        return list;
    }
    public static String getGroup(String input, String regex, int index) {
        Matcher m0 = Pattern.compile(regex).matcher(input);
        if (m0.find()) {
            return m0.group(index);
        }
        return "";
    }
    public static String getGroup(String input, String regex) {
        Matcher m0 = Pattern.compile(regex).matcher(input);
        if (m0.find()) {
            return m0.group();
        }
        return "";
    }
    public static String delScript(String source) {
        // System.err.println(source);
        StringBuffer buff = new StringBuffer(source.length() + 100);
        char c[] = source.toCharArray();
        for (int i = 0; i < c.length; i++) {
            int cnum = c[i];
            if (cnum == 10 || cnum == 13) {
                if (cnum == 10)
                    buff.append("@_n@");
            } else {
                buff.append(c[i]);
            }
        }
        String str = buff.toString().replaceAll("<(script|SCRIPT)(.+?)</(script|SCRIPT)>", "");
        str = str.replaceAll("<!--(.+?)-->", "");
        str = str.replaceAll("<(STYLE|style)(.+?)</(STYLE|style)>", "");
        return str.replaceAll("@_n@", "\n");
    }
    public static String delHtmlCode(String source) {
        // System.err.println(source);
        if (source == null)
            return "";
        StringBuffer buff = new StringBuffer(source.length() + 100);
        char c[] = source.toCharArray();
        for (int i = 0; i < c.length; i++) {
            int cnum = c[i];
            if (cnum == 10 || cnum == 13) {
                if (cnum == 10)
                    buff.append("@_n@");
            } else {
                buff.append(c[i]);
            }
        }
        String str = buff.toString().replaceAll("<(.+?)>", "");
        str = str.replaceAll("<!--(.+?)-->", "");
        str = str.replaceAll("<(STYLE|style)(.+?)</(STYLE|style)>", "");
        str = str.replaceAll("&nbsp;", "");
        return str.replaceAll("@_n@", "\n");
    }
    
    
    public static String getHtmlTag(String html, String tag) {
        StringBuffer buff = new StringBuffer(html.length() + 100);
        char c[] = html.toCharArray();
        for (int i = 0; i < c.length; i++) {
            int cnum = c[i];
            if (cnum == 10 || cnum == 13) {
                if (cnum == 10)
                    buff.append("@_n@");
            } else {
                buff.append(c[i]);
            }
        }
   
        Matcher m0 = Pattern.compile("<"+tag.toLowerCase()+">(.+?)</"+tag.toLowerCase()+">").matcher(buff.toString());
        if (m0.find()) {
            String s = m0.group(1);
            return s.replaceAll("@_n@", "\n");
        }
        
        
        
          m0 = Pattern.compile("<"+tag.toUpperCase()+">(.+?)</"+tag.toUpperCase()+">").matcher(buff.toString());
        if (m0.find()) {
            String s = m0.group(1);
            return s.replaceAll("@_n@", "\n");
        }
        return "";
    }
    
    
    public static String getGroupN(String input, String regex, int groupIndex) {
        StringBuffer buff = new StringBuffer(input.length() + 100);
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            int cnum = c[i];
            if (cnum == 10 || cnum == 13) {
                if (cnum == 10)
                    buff.append("@_n@");
            } else {
                buff.append(c[i]);
            }
        }
        Matcher m0 = Pattern.compile(regex).matcher(buff.toString());
        if (m0.find()) {
            String s = m0.group(groupIndex);
            return s.replaceAll("@_n@", "\n");
        }
        return "";
    }
    public static String getGroupN(String input, String regex, int groupIndex, int index) {
        StringBuffer buff = new StringBuffer(input.length() + 100);
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            int cnum = c[i];
            if (cnum == 10 || cnum == 13) {
                if (cnum == 10)
                    buff.append("@_n@");
            } else {
                buff.append(c[i]);
            }
        }
        Matcher m0 = Pattern.compile(regex).matcher(buff.toString());
        int n = 0;
        while (m0.find()) {
            n++;
            if (index == n) {
                String s = m0.group(groupIndex);
                return s.replaceAll("@_n@", "\n");
            }
        }
        return "";
    }
    public static String getMatchByIndex(String input, String regex, int index) {
        StringBuffer buff = new StringBuffer(input.length() + 100);
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            int cnum = c[i];
            if (cnum == 10 || cnum == 13) {
                if (cnum == 10)
                    buff.append("@_n@");
            } else {
                buff.append(c[i]);
            }
        }
        Matcher m0 = Pattern.compile(regex).matcher(buff.toString());
        int n = 0;
        while (m0.find()) {
            n++;
            if (index == n) {
                String s = m0.group(0);
                return s.replaceAll("@_n@", "\n");
            }
        }
        return "";
    }
    public static String getGroupN(String input, String regex) {
        return getGroupN(input, regex, 0);
    }
    public static void main(String args[]){
    	//org.noe.framework.util.log.Log.debug(NOE.regex.getHtmlTag( "<html><head>head asdf asd df s</head>\n<body>asdfa\n head \nsfasasdf head </body></html>","head"));
    	//System.out.println(RegExUtil.getHtmlTag( "<html><head>head asdf asd df s</head>\n<body>asdfa\n head \nsfasasdf head </body></html>","body"));
    }
}
