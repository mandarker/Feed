package com.mandarker.feed.classes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandarker on 4/22/2017.
 */

public class RestaurantImageParser {
    private static String getOriginal(String url){
        int i;
        for( i = url.length()-1; i >= 0; i--){
            if (url.charAt(i) == '/'){
                break;
            }
        }
        return url.substring(0, i+1) + "o.jpg";
    }

    public static ArrayList<String> getPictures(String html){
        Document document = Jsoup.parse(html);
        Elements images = document.select("div.photo-box > img");
        ArrayList<String> urls = new ArrayList<String>();
        System.out.println("dog");
        String src;
        System.out.println(images.size());
        for (Element image: images){
            src = image.attr("src");
            System.out.println(src);
            if (src != null && !src.equals(""))
                urls.add(getOriginal(src));
        }
        return urls;
    }
}
