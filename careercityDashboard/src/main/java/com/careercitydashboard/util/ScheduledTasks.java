package com.careercitydashboard.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.careercitydashboard.Model.OpenJobReq;
import com.careercitydashboard.Service.AccountService;
import com.careercitydashboard.Service.PositionService;
import com.careercitydashboard.Service.UploadJobReqService;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    // Job Requisitions paths
    private static final String jobreqLocalPath = "../uranus-cc/src/main/resources/workday/jobreq/";
    private static final String jobReqDeploymentPath = "//opt/tomcat/webapps/uranus-cc/WEB-INF/classes/workday/jobreq//";

    // Roster paths
    private static final String rosterlocalPath = "../uranus-cc/src/main/resources/workday/roster/";
    private static final String rosterDeploymentPath = "//opt/tomcat/webapps/uranus-cc/WEB-INF/classes/workday/roster//";

    @Autowired
    private UploadJobReqService uploadJobReqService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PositionService positionService;

    // @Scheduled(fixedRate = 120000) 
    // Upload file every day @11pm
    @Scheduled(cron = "0 0 22 * * ?", zone = "Asia/Manila")
    public void updateDailyJobReq() throws FileNotFoundException, IOException, ParseException {

        try {

            // Get the latest file in the given directory
            File file = this.getLastModified(jobReqDeploymentPath);

            if (file != null) {

                JobReqUploadToDB csvFile = new JobReqUploadToDB();
                csvFile.upload(new FileReader(file));

                // Truncate open_job_req table before uploading
                uploadJobReqService.truncate();

                // Upload CSV raw data to database
                for (OpenJobReq openJobReq : csvFile.getOpenJobReqList()) {

                    uploadJobReqService.save(openJobReq);

                }

                System.out.println("Upload counts: " + csvFile.getOpenJobReqList().size());

                // Sync job req mapping for account/positions
                this.accountService.syncJobReqAccounts();
                this.positionService.syncJobReqPosition();

                System.out.println("Sync Accounts/Positions completed");

            }
            else {

                System.out.println("No file/s found.");

            }

        }
        catch (Exception e) {

            e.printStackTrace();

        }

    }

    // Remove CSV files every week
    @Scheduled(cron = "0 0 0 * * SUN", zone = "Asia/Manila")
    public void clearJobReqFolderWeekly() {

        File directory = new File(jobReqDeploymentPath);
        for (File file : directory.listFiles())
            if (!file.isDirectory())
                file.delete();

    }

    public static File getLastModified(String directoryFilePath) {

        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null) {

            for (File file : files) {

                if (file.lastModified() > lastModifiedTime) {

                    chosenFile = file;
                    lastModifiedTime = file.lastModified();

                }

            }

        }

        return chosenFile;

    }

}
