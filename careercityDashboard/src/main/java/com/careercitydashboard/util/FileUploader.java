package com.careercitydashboard.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FileUploader {

    public FileUploader() {

    };

    public void connectServer() throws Exception {

        long start = System.currentTimeMillis();

        Socket sock = new Socket("//35.197.38.49/", 80);
        System.out.println("Connecting...");
        InputStream is = sock.getInputStream();

        new FileUploader().receiveFile(is);
        OutputStream os = sock.getOutputStream();

        long end = System.currentTimeMillis();
        System.out.println(end - start);

        sock.close();

    }

    public void send(OutputStream os) throws Exception {

        File myFile = new File("/Users/x207663/Downloads/JUUL (1).jpg");
        byte[] mybytearray = new byte[(int) myFile.length() + 1];
        FileInputStream fis = new FileInputStream(myFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        bis.read(mybytearray, 0, mybytearray.length);
        System.out.println("Sending...");
        os.write(mybytearray, 0, mybytearray.length);
        os.flush();

    }

    private void receiveFile(InputStream is) throws Exception {

        Integer filesize = 6022386;
        Integer bytesRead;
        Integer current = 0;
        byte[] mybytearray = new byte[filesize];

        FileOutputStream fos = new FileOutputStream("def");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bytesRead = is.read(mybytearray, 0, mybytearray.length);
        current = bytesRead;

        do {

            bytesRead = is.read(mybytearray, current, (mybytearray.length - current));

            if (bytesRead >= 0) {

                current += bytesRead;

            }

        } while (bytesRead > -1);

        bos.write(mybytearray, 0, current);
        bos.flush();
        bos.close();

    }

}
