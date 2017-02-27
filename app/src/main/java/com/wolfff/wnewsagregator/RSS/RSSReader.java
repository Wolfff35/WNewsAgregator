package com.wolfff.wnewsagregator.RSS;

import com.wolfff.wnewsagregator.Obj.WNews;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by wolff on 21.10.2016.
 */

public class RSSReader {
    public static ArrayList<WNews> getRssNews(String feedUrl,long idChannel) {

        ArrayList<WNews> rssNews = new ArrayList<WNews>();

        //WNews rssItemT = new RssItem("MSUG news", "Best IT news.",
        //        new Date(), "http://msug.vn.ua/");

        //rssItems.add(rssItemT);

        try {
            //open an URL connection make GET to the server and
            //take xml RSS data
            URL url = new URL(feedUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();

                //DocumentBuilderFactory, DocumentBuilder are used for
                //xml parsing
                DocumentBuilderFactory dbf = DocumentBuilderFactory
                        .newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();

                //using db (Document Builder) parse xml data and assign
                //it to Element
                Document document = db.parse(is);
                Element element = document.getDocumentElement();

                //take rss nodes to NodeList
                NodeList nodeList = element.getElementsByTagName("item");

                if (nodeList.getLength() > 0) {
                    for (int i = 0; i < nodeList.getLength(); i++) {

                        //take each entry (corresponds to <item></item> tags in
                        //xml data

                        Element entry = (Element) nodeList.item(i);

                        Element _titleE = (Element) entry.getElementsByTagName(
                                "title").item(0);
                        Element _descriptionE = (Element) entry
                                .getElementsByTagName("description").item(0);
                        Element _pubDateE = (Element) entry
                                .getElementsByTagName("pubDate").item(0);
                        Element _linkE = (Element) entry.getElementsByTagName(
                                "link").item(0);
                        Element _guidE = (Element) entry.getElementsByTagName(
                                "guid").item(0);

                        String _title = _titleE.getFirstChild().getNodeValue();
                        String _description = _descriptionE.getFirstChild().getNodeValue();
                        String _pubDate = _pubDateE.getFirstChild().getNodeValue();
                        Date _date = new Date(_pubDateE.getFirstChild().getNodeValue());
                        String _link = _linkE.getFirstChild().getNodeValue();
                        String _guid = _guidE.getFirstChild().getNodeValue();

                        //create RssItemObject and add it to the ArrayList
//                        public WNews(long id, String guid, String pubDate, String title, String description, String link, String enclosure, int isRead, long idChannel, long date){

                            WNews rssItem = new WNews(0,_guid,_pubDate,_title, _description, _link,null,0,idChannel,_date.getTime());

                        rssNews.add(rssItem);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rssNews;
    }

}
