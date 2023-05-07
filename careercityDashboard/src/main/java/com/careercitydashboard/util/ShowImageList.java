package com.careercitydashboard.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowImageList {

    public List<String> getAllImage() throws IOException {

        List<String> imagelist = new ArrayList<>();

        String url = "//opt/tomcat/webapps/ImageRepo//";
        String WEB_APP_URL = "https://tipcareercity.com/ImageRepo/";

        File folder = new File(url);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            imagelist.add(WEB_APP_URL + file.getName());
        }

        return imagelist;

    }

}
