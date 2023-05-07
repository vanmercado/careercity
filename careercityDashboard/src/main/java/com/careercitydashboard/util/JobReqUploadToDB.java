package com.careercitydashboard.util;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.careercitydashboard.Model.OpenJobReq;
import com.opencsv.CSVReader;

public class JobReqUploadToDB {

    @Autowired
    private List<OpenJobReq> openJobReqList;

    public JobReqUploadToDB() {

        this.openJobReqList = new ArrayList<OpenJobReq>();

    }

    public void upload(FileReader file) throws IOException, ParseException {

        Integer row = 0;
        CSVReader reader = new CSVReader(file);

        try {

            String[] values;

            while ((values = reader.readNext()) != null) {

                row++;

                if (values != null) {

                    if (row > 1) {

                        if (values.length > 1) {

                            OpenJobReq openJobReq = new OpenJobReq();
                            openJobReq.setJobProfile(values[0]);
                            openJobReq.setJobPostingTitle(values[3]);
                            openJobReq.setJobReqId(values[4]);
                            openJobReq.setFunctionalArea(values[6]);
                            openJobReq.setLocation(parseLocation(values[7]));
                            openJobReq.setDateRequestEntered(parseDate(values[8]));
                            this.openJobReqList.add(openJobReq);

                        }

                    }

                }

            }

        }
        catch (Exception e) {

            e.printStackTrace();

        }
        finally {

            reader.close();

        }

    }

    private static Date parseDate(String stringDate) throws ParseException {

        Date dateFormat = new SimpleDateFormat("M/d/yyyy").parse(stringDate);
        return new Date(dateFormat.getTime());

    }

    private static String parseLocation(String stringSite) {

        String[] lines = stringSite.split("[\\r\\n]+");
        String site = lines[0];

        if (site.equalsIgnoreCase("McKinley")) {

            site = "McKinley Exchange";

        }

        if (lines.length > 1) {

            for (int i = 1; i < lines.length; i++) {

                if (lines[i].equalsIgnoreCase("McKinley")) {

                    lines[i] = "McKinley Exchange";

                }

                site = site.concat(", " + lines[i]);

            }

        }

        return site;

    }

    public List<OpenJobReq> getOpenJobReqList() {

        return openJobReqList;

    }

    public void setOpenJobReqList(List<OpenJobReq> openJobReqList) {

        this.openJobReqList = openJobReqList;

    }

}
