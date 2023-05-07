package com.careercitydashboard.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.careercitydashboard.Model.Role;
import com.careercitydashboard.Model.TMJobDetails;
import com.careercitydashboard.Model.TMPersonalDetails;
import com.careercitydashboard.Model.Users;
import com.careercitydashboard.enums.Gender;
import com.opencsv.CSVReader;

public class BulkRosterUploadToDB {

    private final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    private final String authenticatedUsername = auth.getName();

    private List<TMPersonalDetails> tmPersonalDetailsList;
    private List<TMJobDetails> tmJobDetailsList;
    private List<Users> usersList;

    /**
     * @return the tmPersonalDetailsList
     */
    public BulkRosterUploadToDB() {

        this.tmPersonalDetailsList = new ArrayList<TMPersonalDetails>();
        this.tmJobDetailsList = new ArrayList<TMJobDetails>();
        this.usersList = new ArrayList<Users>();

    }
    //public static void main(String[]args) throws FileNotFoundException, IOException, ParseException {
    //    System.out.println("Hello");
    //    saveBulkUpload(new FileReader("C:\\Users\\x209486\\Desktop\\sample.csv"));
    //}
    public void saveBulkUpload(FileReader file) throws IOException, ParseException {

        Integer row = 0;
        CSVReader reader = new CSVReader(file);

        try {

            String[] values;

            while ((values = reader.readNext()) != null) {

                row++;

                if (values != null) {

                    if (row > 1) {

                        if (values.length > 1) {

                            TMPersonalDetails tmPersonalDetails = new TMPersonalDetails();
                            TMJobDetails tmJobDetails = new TMJobDetails();
                            Users user = new Users();

                            // Map TMPersonalDetails
                            tmPersonalDetails.setLastName(values[2]);
                            tmPersonalDetails.setFirstName(values[3]);
                            tmPersonalDetails.setGender(findGender(values[8]));
                            tmPersonalDetails.setAgeGroup(findAgeGroup(values[9]));

                            // Map TMJobDetails
                            tmJobDetails.setTmPersonalDetails(tmPersonalDetails);
                            tmJobDetails.setEmployeeId(values[0].trim());
                            tmJobDetails.setPrimaryWorkEmail(values[10].trim());
                            tmJobDetails.setJobTitle(values[4]);
                            tmJobDetails.setJobLevel(parseJobLevel(values[14]));
                            tmJobDetails.setImmediateSupervisorId(parseManagerId(values[6]));
                            tmJobDetails.setImmediateSupervisorName(parseAndGetManagerName(values[6]));
                            tmJobDetails.setManagerImmediateSupervisorId(parseManagerId(values[13]));
                            tmJobDetails.setManagerImmediateSupervisorName(parseAndGetManagerName(values[13]));
                            tmJobDetails.setSite(values[7]);
                            tmJobDetails.setFunctionalAreaName(values[12]);
                            tmJobDetails.setRemarks("Created by " + authenticatedUsername);

                            // Map User
                            user.setFirstname(tmPersonalDetails.getFirstName());
                            user.setLastname(tmPersonalDetails.getLastName());
                            user.setUsername(tmJobDetails.getEmployeeId());
                            user.setEmail(tmJobDetails.getPrimaryWorkEmail());
                            user.setUSER_STATUS(values[11].toLowerCase());

                            //user.setDateCreated(dateCreated);
                            user.setCreated_by("Created by " + authenticatedUsername);

                            user.setUSER_GROUP("N/A"); // TODO : THIS LINE OF CODE IS TEMPORARY, WE NEED TO DETERMINE
                                                       // THE RIGHT PILLAR OF TM
                            //user.setLastModifiedBy(lastModifiedBy);
                            //user.setLastModifiedBy(lastModifiedBy);
                            user.setTmJobDetails(tmJobDetails);
                            // TODO - Find duplicate entry in database (Users)

                            // Map Users role
                            Set<Role> roleSet = new HashSet<Role>();
                            Role defaultRole = new Role();
                            defaultRole.setROLE_ID(2);
                            defaultRole.setUSER_ROLE("USER");
                            roleSet.add(defaultRole);
                            user.setRoles(roleSet);

                            // Save to List variables
                            this.tmPersonalDetailsList.add(tmPersonalDetails);
                            this.tmJobDetailsList.add(tmJobDetails);
                            this.usersList.add(user);

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

    /**
     * @throws UnsupportedEncodingException
     * 
     */

    private static String findAgeGroup(String ageGroup) {

        if (ageGroup.equalsIgnoreCase("Baby Bloomer")) {

            ageGroup = "Baby Boomers";

        }

        if (ageGroup.equalsIgnoreCase("Generation X")) {

            ageGroup = "Gen X";

        }

        if (ageGroup.equalsIgnoreCase("Generation Z")) {

            ageGroup = "Gen Z";

        }

        if (ageGroup.equalsIgnoreCase("Millenial")) {

            ageGroup = "Millenials";

        }

        return ageGroup;

    }

    private static String parseJobLevel(String stringParam) {

        if (stringParam.startsWith("ASSOC") == true) {

            stringParam = "ASSOC";

        }

        if (stringParam.startsWith("OFCR") == true) {

            stringParam = "OFCR";

        }

        return stringParam;

    }

    private static String parseAndGetManagerName(String stringParam) {

        // "("
        int index = stringParam.indexOf("(");

        String managerName = stringParam.substring(0, index - 1);

        return managerName;

    }

    // test method
    private static String parseAnySymbol(String stringParam) throws UnsupportedEncodingException {

        String str = "\u00F1"; // or ñ
        byte[] arr = str.getBytes();
        String enyeSymbol = new String(arr, "UTF-8");

        String strResult = stringParam.replaceAll("e", enyeSymbol);

        return strResult;

    }

    private static String setDecriptyedPassword(TMPersonalDetails tmPersonalDetails) {
        // OLD Password parsing
        // String empIdLast5Digits = employeeId.substring(2);
        // String birthdateParse = birthdate.replace("-", "");

        // New Password parsing
        String empLastNameInitialLetter = tmPersonalDetails.getLastName().substring(0, 1).toLowerCase();

        // Parse birthdate from Date to String
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = formatter.format(tmPersonalDetails.getBirthdate());

        String[] birthdaySplit = sDate.split("-");
        String yearYY = birthdaySplit[0].substring(birthdaySplit[0].length() - 2);
        String monthMM = birthdaySplit[1];
        String dayYY = birthdaySplit[2];
        String concatBirthday = monthMM + dayYY + yearYY;

        // First Letter (of Last name) lower-case + birthday (mmddyy) - exp: e122097
        String concatNewPassword = empLastNameInitialLetter + concatBirthday;
        System.out.println(concatNewPassword);
        return concatNewPassword;

    }

    private static Date parseDate(String stringDate) throws ParseException {

        Date dateFormat = new SimpleDateFormat("M/d/yyyy").parse(stringDate);
        return new Date(dateFormat.getTime());

    }

    private static String parseManagerId(String string) {

        //Pattern pattern = Pattern.compile("\\((.*?)\\)");

        //Matcher m = pattern.matcher(string);
        //while(m.find()) {
        //    return m.group(1);
        //}

        // Get Manager ID as there are some values that have (?????) and (On Leave)
        int StringLength = string.length();
        String managerId = string.substring(StringLength - 9, StringLength - 1);

        return managerId;

    }

    private static Gender findGender(String str) {

        if (str.equalsIgnoreCase("female")) {

            return Gender.F;

        }
        else if (str.equalsIgnoreCase("male")) {

            return Gender.M;

        }
        else {

            return Gender.NA;

        }

    }

    // Setters and Getters
    /**
     * @return the tmPersonalDetailsList
     */
    public List<TMPersonalDetails> getTmPersonalDetailsList() {

        return tmPersonalDetailsList;

    }

    /**
     * @param tmPersonalDetailsList the tmPersonalDetailsList to set
     */
    public void setTmPersonalDetailsList(List<TMPersonalDetails> tmPersonalDetailsList) {

        this.tmPersonalDetailsList = tmPersonalDetailsList;

    }

    /**
     * @return the tmJobDetailsList
     */
    public List<TMJobDetails> getTmJobDetailsList() {

        return tmJobDetailsList;

    }

    /**
     * @param tmJobDetailsList the tmJobDetailsList to set
     */
    public void setTmJobDetailsList(List<TMJobDetails> tmJobDetailsList) {

        this.tmJobDetailsList = tmJobDetailsList;

    }

    /**
     * @return the usersList
     */
    public List<Users> getUsersList() {

        return usersList;

    }

    /**
     * @param usersList the usersList to set
     */
    public void setUsersList(List<Users> usersList) {

        this.usersList = usersList;

    }

}
