package com.careercitydashboard.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.careercitydashboard.Model.Account;
import com.careercitydashboard.Model.AccountHistory;
import com.careercitydashboard.Model.ClonedAccount;
import com.careercitydashboard.Model.ClonedPosition;
import com.careercitydashboard.Model.JobProfileChange;
import com.careercitydashboard.Model.OpenJobReq;
import com.careercitydashboard.Model.Position;
import com.careercitydashboard.Model.PositionHistory;
import com.careercitydashboard.Model.Report;
import com.careercitydashboard.Model.TMJobDetails;
import com.careercitydashboard.Service.AccountHistoryService;
import com.careercitydashboard.Service.AccountService;
import com.careercitydashboard.Service.AnalyticsService;
import com.careercitydashboard.Service.JobProfileChangeService;
import com.careercitydashboard.Service.PositionHistoryService;
import com.careercitydashboard.Service.PositionService;
import com.careercitydashboard.Service.TMJobDetailsService;
import com.careercitydashboard.Service.TMPersonalDetailsService;
import com.careercitydashboard.Service.UploadJobReqService;
import com.careercitydashboard.Service.UsersService;
import com.careercitydashboard.util.BulkRosterUploadToDB;
import com.careercitydashboard.util.JobReqUploadToDB;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private AccountHistoryService accountHistoryService;

    @Autowired
    private PositionHistoryService positionHistoryService;

    @Autowired
    private DataController dataController;

    @Autowired
    private TMPersonalDetailsService tmPersonalDetailsService;

    @Autowired
    private TMJobDetailsService tmJobDetailsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private AnalyticsService analyticsService;

    @Autowired
    private UploadJobReqService uploadJobReqService;

    @Autowired
    private JobProfileChangeService jobProfileChangeService;

    private static final String UPLOADER_FOLDER = "//opt/tomcat/webapps/ImageRepo//";
    private static final String WEB_APP_URL = "https://tipcareercity.com/";

    @RequestMapping("/reporting")
    public String admin(Model model) {

        List<Report> initialFunctionalAreaList = analyticsService.getAllFunctionalAreaByDate("2019-06-27",
                "2020-06-27");
        model.addAttribute("initialFunctionalAreaList", initialFunctionalAreaList);

        return "pages/admin";

    }

    @RequestMapping(value = "/uploadcsv", method = RequestMethod.GET)
    public String uploadCsvPage() {

        return "upload_csv";

    }

    @RequestMapping(value = "/uploadcsv", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadCsv(MultipartFile file) throws FileNotFoundException, IOException, ParseException {

        if (file.isEmpty()) {

            System.out.println("No file up to upload.");
            return "redirect:/admin/uploadcsv";

        }

        try {

            Path path = Paths.get(UPLOADER_FOLDER + file.getOriginalFilename());
            String extension = "";

            // Check if the file extension is CSV.
            String stringPath = path.toString();

            if (stringPath.contains(".")) {

                extension = stringPath.substring(stringPath.lastIndexOf(".") + 1);

                if (extension.equalsIgnoreCase("csv")) {

                    // Convert file's char set encoding to UTF-8 and save to temporary repository
                    ByteBuffer bb = ByteBuffer.wrap(file.getBytes());
                    CharBuffer cb = Charset.forName("windows-1252").decode(bb);
                    bb = Charset.forName("UTF-8").encode(cb);
                    Files.write(path, bb.array());

                    BulkRosterUploadToDB roster = new BulkRosterUploadToDB();
                    roster.saveBulkUpload(new FileReader(path.toFile()));

                    // Loop through list of users
                    for (int i = 0; i < roster.getTmPersonalDetailsList().size(); i++) {

                        try {

                            // Check is user exists
                            TMJobDetails currentTmJobDetails = tmJobDetailsService
                                    .findByWdId(roster.getTmJobDetailsList().get(i).getEmployeeId());

                            // User does not exist yet, create new record
                            if (Objects.isNull(currentTmJobDetails)) {

                                // User already exists
                                tmPersonalDetailsService.addTm(roster.getTmPersonalDetailsList().get(i));
                                tmJobDetailsService.addTmJobDetails(roster.getTmJobDetailsList().get(i));
                                usersService.saveUsers(roster.getUsersList().get(i));

                            }

                            else {

                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                                // Check if there's change of job profile
                                String wdId = currentTmJobDetails.getEmployeeId();
                                String firstName = roster.getTmPersonalDetailsList().get(i).getFirstName();
                                String lastName = roster.getTmPersonalDetailsList().get(i).getLastName();
                                String currentProfile = currentTmJobDetails.getJobTitle();
                                String incomingProfile = roster.getTmJobDetailsList().get(i).getJobTitle();
                                String currentDate = format.format(new Date());

                                if (!currentProfile.equalsIgnoreCase(incomingProfile)) {

                                    System.out.println("different profile");
                                    JobProfileChange jobChangeCco_quiz = jobProfileChangeService
                                            .findMatchedJobProfileQuiz_cco(wdId, firstName, lastName, currentProfile,
                                                    incomingProfile, currentDate);
                                    JobProfileChange jobChangeDsSt_quiz = jobProfileChangeService
                                            .findMatchedJobProfileQuiz_dsSt(wdId, firstName, lastName, currentProfile,
                                                    incomingProfile, currentDate);
                                    JobProfileChange jobChangeCco_search = jobProfileChangeService
                                            .findMatchedJobProfileSearch_cco(wdId, firstName, lastName, currentProfile,
                                                    incomingProfile, currentDate);
                                    JobProfileChange jobChangeDsSt_search = jobProfileChangeService
                                            .findMatchedJobProfileSearch_dsSt(wdId, firstName, lastName, currentProfile,
                                                    incomingProfile, currentDate);

                                    if (!Objects.isNull(jobChangeCco_quiz)) {

                                        if (!jobChangeCco_quiz.getPrevJobProfile()
                                                .equalsIgnoreCase(jobChangeCco_quiz.getNewJobProfile())) {

                                            jobProfileChangeService.save(jobChangeCco_quiz);

                                        }

                                    }

                                    if (!Objects.isNull(jobChangeDsSt_quiz)) {

                                        if (!jobChangeDsSt_quiz.getPrevJobProfile()
                                                .equalsIgnoreCase(jobChangeDsSt_quiz.getNewJobProfile())) {

                                            jobProfileChangeService.save(jobChangeDsSt_quiz);

                                        }

                                    }

                                    if (!Objects.isNull(jobChangeCco_search)) {

                                        if (!jobChangeCco_search.getPrevJobProfile()
                                                .equalsIgnoreCase(jobChangeCco_search.getNewJobProfile())) {

                                            jobProfileChangeService.save(jobChangeCco_search);

                                        }

                                    }

                                    if (!Objects.isNull(jobChangeDsSt_search)) {

                                        if (!jobChangeDsSt_search.getPrevJobProfile()
                                                .equalsIgnoreCase(jobChangeDsSt_search.getNewJobProfile())) {

                                            jobProfileChangeService.save(jobChangeDsSt_search);

                                        }

                                    }

                                    try {

                                        // TMPersonalDetails tmPersonalDetails =
                                        // tmPersonalDetailsService.findByTmPersonalDetailsId(currentTmJobDetails.getTmPersonalDetails().getTmPersonalDetailsId());
                                        // tmPersonalDetails.setLastName(roster.getTmPersonalDetailsList().get(i).getLastName());
                                        // tmPersonalDetails.setName(roster.getTmPersonalDetailsList().get(i).getFirstName());
                                        // tmPersonalDetails.setGender(roster.getTmPersonalDetailsList().get(i).getGender());
                                        // tmPersonalDetails.setAgeGroup(roster.getTmPersonalDetailsList().get(i).getAgeGroup());
                                        // tmPersonalDetailsService.addTm(tmPersonalDetails);

                                        TMJobDetails tmJobDetails = tmJobDetailsService.findByWdId(wdId);
                                        tmJobDetails.setJobTitle(incomingProfile);
                                        tmJobDetails.setJobLevel(roster.getTmJobDetailsList().get(i).getJobLevel());
                                        tmJobDetails.setImmediateSupervisorId(
                                                roster.getTmJobDetailsList().get(i).getImmediateSupervisorId());
                                        tmJobDetails.setImmediateSupervisorName(
                                                roster.getTmJobDetailsList().get(i).getImmediateSupervisorName());
                                        tmJobDetails.setManagerImmediateSupervisorId(
                                                roster.getTmJobDetailsList().get(i).getManagerImmediateSupervisorId());
                                        tmJobDetails.setManagerImmediateSupervisorName(roster.getTmJobDetailsList()
                                                .get(i).getManagerImmediateSupervisorName());
                                        tmJobDetails.setSite(roster.getTmJobDetailsList().get(i).getSite());
                                        tmJobDetails.setFunctionalAreaName(
                                                roster.getTmJobDetailsList().get(i).getFunctionalAreaName());
                                        tmJobDetailsService.addTmJobDetails(tmJobDetails);

                                        // Users user = usersService.findByEmpId(wdId);
                                        // user.setFirstname(roster.getUsersList().get(i).getFirstname());
                                        // user.setLastname(roster.getUsersList().get(i).getLastname());
                                        // user.setUsername(roster.getUsersList().get(i).getUsername());
                                        // user.setEmail(roster.getUsersList().get(i).getEmail());
                                        // user.setUSER_STATUS(roster.getUsersList().get(i).getUSER_STATUS());
                                        // usersService.addUsers(user);

                                    }

                                    catch (Exception e) {
                                        System.err.println(e);
                                    }

                                }

                                else {
                                    System.out.println("same profile");
                                }

                            }

                        }

                        catch (Exception e) {
                            System.err.println(e);
                        }

                    }

                    // Remove the CSV file from temporary folder when the upload is finished.
                    File csvFileToDelete = path.toFile();
                    csvFileToDelete.delete();

                }

                else {

                    System.out.println("Invalid file. Please make sure it's a CSV file.");
                    return "redirect:/admin/uploadcsv";

                }

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return "redirect:/admin/uploadcsv";

    }

    @RequestMapping(value = "/uploadopenjobreq", method = RequestMethod.GET)
    public String uploadOpenJobReqPage() {

        return "upload_openJobReq";

    }

    @RequestMapping(value = "/uploadopenjobreq", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadOpenJobReq(MultipartFile file, RedirectAttributes redirectAttributes)
            throws FileNotFoundException, IOException, ParseException {

        if (file.isEmpty()) {

            redirectAttributes.addFlashAttribute("error", "Please Select a file to upload");
            return "redirect:/admin/uploadopenjobreq";

        }

        try {

            Path path = Paths.get(UPLOADER_FOLDER + file.getOriginalFilename());
            String extension = "";
            // Check if the file extension is CSV
            String stringPath = path.toString();

            if (stringPath.contains(".")) {

                extension = stringPath.substring(stringPath.lastIndexOf(".") + 1);

                if (extension.equalsIgnoreCase("csv")) {

                    // Convert file's char set encoding to UTF-8 and save to temporary repository.
                    ByteBuffer bb = ByteBuffer.wrap(file.getBytes());
                    CharBuffer cb = Charset.forName("windows-1252").decode(bb);
                    bb = Charset.forName("UTF-8").encode(cb);
                    Files.write(path, bb.array());

                    JobReqUploadToDB csvFile = new JobReqUploadToDB();
                    csvFile.upload(new FileReader(path.toFile()));

                    // Truncate open_job_req table before uploading
                    uploadJobReqService.truncate();

                    // Upload CSV raw data to database
                    for (OpenJobReq openJobReq : csvFile.getOpenJobReqList()) {
                        uploadJobReqService.save(openJobReq);
                    }

                    System.out.println("Upload counts: " + csvFile.getOpenJobReqList().size());

                    // Remove the CSV file from temporary folder when the upload is finished.
                    File csvFileToDelete = path.toFile();
                    csvFileToDelete.delete();

                    // Sync job requisition mapping for account/positions.
                    this.accountService.syncJobReqAccounts();
                    this.positionService.syncJobReqPosition();

                    System.out.println("Sync Accounts/Positions completed");

                }

                else {
                    System.out.println("Invalid file. Please make sure it's a CSV file.");
                    return "redirect:/admin/uploadopenjobreq";
                }

            }

        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/uploadopenjobreq";

    }

    @RequestMapping(value = "/job-req-mapping", method = RequestMethod.GET)
    public String mapJobReq() {

        return "redirect:/admin/uploadopenjobreq";

    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public String admin_accounts(Model model) {

        logger.debug("accounttable");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        logger.info("last logged by" + auth.getName());
        model.addAttribute("lastsavedby", username);

        List<Account> listofallaccounts = this.accountService.getallAccounts();
        model.addAttribute("allaccounts", listofallaccounts);

        List<AccountHistory> allAccountHistory = this.accountHistoryService.getAllAccountHistory();
        model.addAttribute("allAccountHistory", allAccountHistory);

        return "pages/accounts";

    }

    @RequestMapping(value = "/accounts/create", method = RequestMethod.GET)
    public String createAccount(Model model) throws IOException {

        logger.debug("accounttable");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        logger.info("last logged by" + auth.getName());
        model.addAttribute("lastsavedby", username);
        model.addAttribute("create_form", "create_form");
        // System.out.println(this.accountService.getallAccounts());
        List<Account> listofallaccounts = this.accountService.getallAccounts();

        List<Map<String, String>> imageList = dataController.getAllImages();
        model.addAttribute("imageList", imageList);

        model.addAttribute("allaccounts", listofallaccounts);

        return "pages/accounts_create";

    }

    @PostMapping("/accounts/clone")
    public String cloneAccount(@ModelAttribute ClonedAccount clonedAccount, RedirectAttributes redirectAttr) {

        redirectAttr.addFlashAttribute("clonedImagePath", clonedAccount.getCloneAcct_imagePath());
        redirectAttr.addFlashAttribute("clonedAccountName", clonedAccount.getCloneAcct_name());
        redirectAttr.addFlashAttribute("clonedLoc", clonedAccount.getCloneAcct_loc());
        redirectAttr.addFlashAttribute("clonedLob", clonedAccount.getCloneAcct_lob());
        redirectAttr.addFlashAttribute("clonedOperHrs", clonedAccount.getCloneAcct_operHrs());
        redirectAttr.addFlashAttribute("clonedWorkType", clonedAccount.getCloneAcct_workType());
        redirectAttr.addFlashAttribute("clonedEduc", clonedAccount.getCloneAcct_educ());
        redirectAttr.addFlashAttribute("clonedPrem", clonedAccount.getCloneAcct_skillsPrem());
        redirectAttr.addFlashAttribute("clonedExp", clonedAccount.getCloneAcct_bpoExp());
        redirectAttr.addFlashAttribute("clonedBusVertical", clonedAccount.getCloneAcct_busVertical());
        redirectAttr.addFlashAttribute("clonedJobReq", clonedAccount.getCloneAcct_jobReq());
        redirectAttr.addFlashAttribute("clonedDesc", clonedAccount.getCloneAcct_desc());
        redirectAttr.addFlashAttribute("clonedSkills", clonedAccount.getCloneAcct_skills());
        redirectAttr.addFlashAttribute("clonedCategory", clonedAccount.getCloneAcct_category());
        redirectAttr.addFlashAttribute("clonedTmTask", clonedAccount.getCloneAcct_task());
        redirectAttr.addFlashAttribute("clonedTrainings", clonedAccount.getCloneAcct_trainings());
        redirectAttr.addFlashAttribute("clonedWorkdayJobProfile", clonedAccount.getCloneAcct_workdayJobProfile());

        return "redirect:/admin/accounts/create";

    }

    @PostMapping("/selectedimagepath")
    public String getSelectedImagePath(@RequestParam(value = "iconPath", required = false) String imagePath,
            RedirectAttributes redirectsAttr) {

        redirectsAttr.addAttribute("accountImagePath", imagePath);
        return "redirect:/admin/accounts/create";

    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    public String displayAccount(Model model, @PathVariable Integer id) throws IOException {

        logger.debug("displayAccount");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        logger.info("last logged by" + auth.getName());
        model.addAttribute("lastsavedby", username);

        List<AccountHistory> accountHistoryList = this.accountHistoryService.getAccountHistoryByAccountId(id);
        model.addAttribute("accountHistoryList", accountHistoryList);

        List<AccountHistory> allAccountHistory = this.accountHistoryService.getAllAccountHistory();
        model.addAttribute("allAccountHistory", allAccountHistory);

        Account account = this.accountService.getaccountbyId(id);
        model.addAttribute("account", account);

        List<Map<String, String>> imageList = dataController.getAllImages();
        model.addAttribute("imageList", imageList);

        return "pages/accounts_edit";

    }

    @RequestMapping(value = "/accounts/save", method = RequestMethod.POST)
    public String saveAccount(Account account, RedirectAttributes redirectsAttr) {

        logger.debug("saveAccount");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        redirectsAttr.addFlashAttribute("message", "Success!");

        if (account.getACCOUNT_ID() == null) {
            redirectsAttr.addFlashAttribute("createMessage", " is now created.");
        }

        else {
            redirectsAttr.addFlashAttribute("updateMessage", " has been updated.");
        }

        // account.setSKILLS(account.getSKILLS().replace(".,", ".~"));
        // account.setTRAININGS(account.getTRAININGS().replace(".,", ".~"));
        account.setSKILLS(account.getSKILLS().replace(",", "~"));
        account.setTRAININGS(account.getTRAININGS().replace(",", "~"));
        account.setWORKDAY_JOB_PROFILE(account.getWORKDAY_JOB_PROFILE().replace(",", "~"));

        // Parse the data from check box input
        // on = CC (enabled)
        // null = NA (disabled)
        if (account.getCATEGORY() != null) {

            account.setCATEGORY("CC");

        }

        else {

            account.setCATEGORY("NA");

        }

        account.setCreated_by(username);

        Account savedAccount = this.accountService.saveUpdateAccount(account);

        // Sync mapping for CCO roles.
        // this.accountService.syncJobReqAccounts();

        return "redirect:/admin/accounts/" + savedAccount.getACCOUNT_ID();

    }

    @RequestMapping(value = "/positions", method = RequestMethod.GET)
    public String admin_psositions(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        logger.info("last logged by" + auth.getName());
        model.addAttribute("modify_by", username);

        List<Position> listofallpositions = this.positionService.getallPositions();
        model.addAttribute("allpositions", listofallpositions);

        List<PositionHistory> allPositionHistory = this.positionHistoryService.getAllPositionHistory();
        model.addAttribute("allPositionHistory", allPositionHistory);

        return "pages/positions";

    }

    @RequestMapping(value = "/positions/{id}", method = RequestMethod.GET)
    public String displayPosition(Model model, @PathVariable Integer id) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        logger.info("last logged by" + auth.getName());
        model.addAttribute("modify_by", username);

        List<Position> listofallpositions = this.positionService.getallPositions();
        model.addAttribute("allpositions", listofallpositions);

        List<PositionHistory> positionHistoryList = this.positionHistoryService.getPositionHistoryByPositionId(id);
        model.addAttribute("positionHistoryList", positionHistoryList);

        List<PositionHistory> allPositionHistory = this.positionHistoryService.getAllPositionHistory();
        model.addAttribute("allPositionHistory", allPositionHistory);

        Position position = this.positionService.getPositionById(id);
        model.addAttribute("position", position);

        List<Map<String, String>> imageList = dataController.getAllImages();
        model.addAttribute("imageList", imageList);

        return "pages/positions_edit";

    }

    @RequestMapping(value = "/positions/create", method = RequestMethod.GET)
    public String createPosition(Model model) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        logger.info("last logged by" + auth.getName());
        model.addAttribute("modify_by", username);
        model.addAttribute("create_form", "create_form");

        List<Position> listofallpositions = this.positionService.getallPositions();
        model.addAttribute("allpositions", listofallpositions);

        List<Map<String, String>> imageList = dataController.getAllImages();
        model.addAttribute("imageList", imageList);

        return "pages/positions_create";

    }

    @PostMapping("/positions/clone")
    public String clonePosition(@ModelAttribute ClonedPosition clonedposition, RedirectAttributes redirectAttr) {

        redirectAttr.addFlashAttribute("clonedImagePath", clonedposition.getClonePos_imagePath());
        redirectAttr.addFlashAttribute("clonedJobDesc", clonedposition.getClonePos_name());
        redirectAttr.addFlashAttribute("clonedDept", clonedposition.getClonePos_dept());
        redirectAttr.addFlashAttribute("clonedSuppType", clonedposition.getClonePos_suppType());
        redirectAttr.addFlashAttribute("clonedCert", clonedposition.getClonePos_cert());
        redirectAttr.addFlashAttribute("clonedDesc", clonedposition.getClonePos_desc());
        redirectAttr.addFlashAttribute("clonedTrainings", clonedposition.getClonePos_trainings());
        redirectAttr.addFlashAttribute("clonedWorkdayJobProfile", clonedposition.getClonePos_workdayJobProfile());
        redirectAttr.addFlashAttribute("clonedEduc", clonedposition.getClonePos_educ());
        redirectAttr.addFlashAttribute("clonedExp", clonedposition.getClonePos_exp());
        redirectAttr.addFlashAttribute("clonedCategory", clonedposition.getClonePos_category());
        redirectAttr.addFlashAttribute("clonedJobReq", clonedposition.getClonePos_jobReq());
        redirectAttr.addFlashAttribute("clonedSkills", clonedposition.getClonePos_skills());
        redirectAttr.addFlashAttribute("clonedOtherSkills", clonedposition.getClonePos_otherSkills());
        return "redirect:/admin/positions/create";

    }

    @RequestMapping(value = "/positions/save", method = RequestMethod.POST)
    public String savePosition(Position position, RedirectAttributes redirectsAttr) {

        logger.debug("savedPosition");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        redirectsAttr.addFlashAttribute("message", "Success!");

        if (position.getPOSITION_ID() == null) {
            redirectsAttr.addFlashAttribute("createMessage", " is now created.");
        }

        else {
            redirectsAttr.addFlashAttribute("updateMessage", " has been updated.");
        }

        // position.setSKILLS_REQUIRED(position.getSKILLS_REQUIRED().replace(".,",
        // ".~"));
        // position.setTRAININGS(position.getTRAININGS().replace(",", "~"));
        // position.setOTHER_SKILLS(position.getOTHER_SKILLS().replace(".,", ".~"));
        position.setSKILLS_REQUIRED(position.getSKILLS_REQUIRED().replace(",", "~"));
        position.setTRAININGS(position.getTRAININGS().replace(",", "~"));
        position.setWORKDAY_JOB_PROFILE(position.getWORKDAY_JOB_PROFILE().replace(",", "~"));
        position.setOTHER_SKILLS(position.getOTHER_SKILLS().replace(",", "~"));

        // Parse the data from check box input
        // on = CC (enabled)
        // null = NA (disabled)
        // if(position.getCATEGORY() != null) {
        // position.setCATEGORY("NA");
        // }
        // else {
        // position.setCATEGORY(null);
        // }

        position.setCREATED_BY(username);

        Position savedPosition = this.positionService.savePosition(position);

        // Sync Job Requisitions mapping for Support/TIDS roles
        // this.positionService.syncJobReqPosition();

        return "redirect:/admin/positions/" + savedPosition.getPOSITION_ID();

    }

    @RequestMapping(value = "/uploadimage", method = RequestMethod.POST)
    public String uploadImage(@RequestParam(value = "file") MultipartFile file, RedirectAttributes redirectAttr) {

        if (file.isEmpty()) {
            redirectAttr.addFlashAttribute("error", "Please select a file to add!");
            return "redirect:/manageimages";
        }

        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADER_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttr.addFlashAttribute("success", "Image has been added successfully.");
            return "redirect:/manageimages";

        }

        catch (IOException e) {

            e.printStackTrace();
            return "";

        }

    }

    @RequestMapping(value = "/deleteimage", method = RequestMethod.POST)
    public String deleteImage(@RequestParam(value = "filename") String filename, RedirectAttributes redirectAttr) {

        try {

            File file = new File(UPLOADER_FOLDER + filename);
            file.delete();
            redirectAttr.addFlashAttribute("deleteOk", "Image is deleted successfully!");

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return "redirect:/manageimages";

    }

    @RequestMapping(value = "/syncjobreq", method = RequestMethod.GET)
    public String syncJobReq(HttpServletRequest request) {

        // String uriOrigin = request.getRequestURI();
        this.accountService.syncJobReqAccounts();
        this.positionService.syncJobReqPosition();
        return "redirect:/admin/positions";

    }

}
