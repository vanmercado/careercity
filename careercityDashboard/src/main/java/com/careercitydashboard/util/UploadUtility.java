package com.careercitydashboard.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtility {

    private static String UPLOADER_FOLDER = "//opt/tomcat/webapps/ImageRepo//";

    private static final String WEB_APP_URL = "https://tipcareercity.com/ImageRepo/";

    public String singleFileUpload(MultipartFile file) {

        String url = "";

        //if (file.isEmpty()) {
        //    redirectAttributes.addFlashAttribute("warning", "Please Select a file to upload");
        //    return "Please Select a file to upload";
        //}

        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADER_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            url = WEB_APP_URL + file.getOriginalFilename();
            return url;

        }

        catch (IOException e) {

            e.printStackTrace();
            return "";

        }

    }

}
