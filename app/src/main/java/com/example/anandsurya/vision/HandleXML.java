package com.example.anandsurya.vision;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HandleXML  {

    public String urlLink;

    public String title, link, description, pubDate;
    public boolean parsingComplete = true;
    public XmlPullParserFactory xmlFactoryObject;


    public HandleXML(String url) {
        urlLink = url;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }


    public String getDescription() {
        return description;
    }


    public void fetchXML() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlLink);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream stream = conn.getInputStream();
                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = xmlFactoryObject.newPullParser();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(stream, null);
                    parseXMLAndStoreIt(parser);
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void parseXMLAndStoreIt(XmlPullParser parser) {
        int event;


        String text = null;
        try {
            event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (name.equals("title")) {
                            newsfeed.titleList.add(text);

                        }/* else if (name.equals("description")) {
                            description = text;
                        } else if (name.equals("link")) {
                            link = text;
                        }
                        else if(name.equals("pubDate")){
                            pubDate = text;
                        }*/

                }
                event = parser.next();
            }
            parsingComplete = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
