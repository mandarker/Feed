package com.mandarker.feed.classes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by mandarker on 4/22/2017.
 */

public class RestaurantImageParser {
    public String getOriginal(String url){
        int i;
        for( i = url.length()-1; i >= 0; i--){
            if (url.charAt(i) == '/'){
                break;
            }
        }
        return url.substring(0, i+1) + "o.jpg";
    }

/*
    public static ArrayList<String> getPictures(String html){
=======

    public static ArrayList<String> getPictures(String html) throws IOException {
>>>>>>> ca00014a6cba306f24537d9b9aa11646a882f03f
        Document document = Jsoup.parse(html);
        document = Jsoup.connect(html).userAgent("Firefox").get();
        System.out.println(html);
        System.out.println(document.location());
        Elements images = document.select("div.photo-box > img");
        ArrayList<String> urls = new ArrayList<String>();
<<<<<<< HEAD
        System.out.println("cat");
=======
>>>>>>> ca00014a6cba306f24537d9b9aa11646a882f03f
        String src;

        for (Element image: images){
            src = image.attr("src");
            if (src != null && !src.equals(""))
                urls.add(getOriginal(image.attr("src")));
        }
        return urls;
    }
    */
}
