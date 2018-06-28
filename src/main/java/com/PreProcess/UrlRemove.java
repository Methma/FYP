package com.PreProcess;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Methma Samaranayake on 6/10/2017.
 */
public class UrlRemove {
    public static void main(String[] args) {
        UrlRemove urlRemove = new UrlRemove();
        String toRemove = "THiS IS WHAT WiLL KiLL APPLE http://t.co/72Jw4z5c RiP @APPLE,negative. Hilarious @youtube video - guy does a duet with @apple 's Siri. Pretty much sums up the love affair! http://t.co/8ExbnQjY,positive";
        String urlremoved = urlRemove.removeUrl(toRemove.toLowerCase());
        System.out.println(urlremoved);
    }
    public String removeUrl(String tweet){
        String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(tweet);
        int i = 0;
        while (m.find()) {
            tweet = tweet.replaceAll(m.group(i),"").trim();
            i++;
        }
        return tweet;
    }
}
