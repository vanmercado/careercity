package com.careercitydashboard.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.careercitydashboard.Model.Account;
import com.careercitydashboard.Model.AccountAnswers;
import com.careercitydashboard.Model.AccountHistory;
import com.careercitydashboard.Model.Answer;
import com.careercitydashboard.Model.Position;
import com.careercitydashboard.Model.PositionAnswers;
import com.careercitydashboard.Model.PositionHistory;
import com.careercitydashboard.Model.Questions;
import com.careercitydashboard.Model.Role;
import com.careercitydashboard.Model.Site;
import com.careercitydashboard.Model.UserHistory;
import com.careercitydashboard.Model.Users;
import com.careercitydashboard.Service.AccountAnswersService;
import com.careercitydashboard.Service.AccountHistoryService;
import com.careercitydashboard.Service.AccountService;
import com.careercitydashboard.Service.AnswerService;
import com.careercitydashboard.Service.PositionAnswerService;
import com.careercitydashboard.Service.PositionHistoryService;
import com.careercitydashboard.Service.PositionService;
import com.careercitydashboard.Service.QuestionsService;
import com.careercitydashboard.Service.SiteService;
import com.careercitydashboard.Service.UserHistoryService;
import com.careercitydashboard.Service.UsersService;
import com.careercitydashboard.util.UploadUtility;

@Controller
public class CommonController {

    private final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private QuestionsService questionService;

    @Autowired
    private AccountAnswersService accountAnswersService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private PositionAnswerService positionAnswerService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private UsersService userService;

    @Autowired
    private AccountHistoryService accountHistoryService;

    @Autowired
    private PositionHistoryService positionHistoryService;

    @Autowired
    private UserHistoryService userHistoryService;

    @Autowired
    private DataController dataController;

    @RequestMapping("/")
    public String admin(Model model) {

        return "redirect:/admin/reporting";

    }

    @RequestMapping("/login")
    public String login() {

        return "login";

    }

    @RequestMapping(value = "/listaccount", method = RequestMethod.GET)

    public String listofallAccounts(Model model) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        model.addAttribute("lastsavedby", username);

        List<Account> listofallaccounts = this.accountService.getallAccounts();
        model.addAttribute("allaccounts", listofallaccounts);

        List<AccountHistory> accountActivity = this.accountHistoryService.getAllAccountHistory();
        model.addAttribute("accountActivity", accountActivity);

        return "accounttable";

    }

    @RequestMapping(value = "/addaccount", method = RequestMethod.POST)
    public String addAccount(Account account, @RequestParam("file") MultipartFile file, AccountHistory accountHistory) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UploadUtility upload = new UploadUtility();

        if (!file.isEmpty()) {

            upload.singleFileUpload(file);

        }

        this.accountService.saveAccount(account);

        AccountHistory accountHistSession = this.accountHistoryService.saveAccountSession(accountHistory);
        accountHistSession.setAccount_hist_msg("Added by" + " " + auth.getName());

        accountHistSession.setAccount(account);
        this.accountHistoryService.saveAccountSession(accountHistory);

        return "redirect:/listaccount";

    }

    @RequestMapping(value = "/updateaccount", method = RequestMethod.POST)
    public String updateAccount(Account account, @RequestParam("file") MultipartFile file,
            AccountHistory accountHistory) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UploadUtility upload = new UploadUtility();

        if (!file.isEmpty()) {

            upload.singleFileUpload(file);

        }

        this.accountService.updateAccount(account);

        AccountHistory accountHistSession = this.accountHistoryService.saveAccountSession(accountHistory);
        accountHistSession.setAccount_hist_msg("Modified by" + " " + auth.getName());

        accountHistSession.setAccount(account);
        this.accountHistoryService.saveAccountSession(accountHistory);

        return "redirect:/listaccount";

    }

    @RequestMapping(value = "/accountmaps/{id}", method = RequestMethod.GET)
    public String getaccountmapbyId(@PathVariable("id") Integer ACCOUNT_ID, Model model) {

        Account getmapdetails = this.accountService.getAccountMapping(ACCOUNT_ID);
        List<Questions> questionList = questionService.getallQuestions();
        model.addAttribute("questionList", questionList);
        model.addAttribute("mapdetails", getmapdetails);

        return "accountmap";

    }

    @GetMapping("/getaccountanswerlist")
    public @ResponseBody List<Answer> getAccountAnswerList() {

        return answerService.getallAnswer();

    }

    @RequestMapping(value = "/addaccountanswers", method = RequestMethod.POST)
    public String addAccountMapping(AccountAnswers accountAnswers) {

        this.accountAnswersService.addAccountAnswers(accountAnswers);
        return "redirect:/accountmaps/" + accountAnswers.getACCOUNT_ID();

    }

    @RequestMapping(value = "/deleteaccountmap", method = RequestMethod.POST)
    public String deleteAccountMapping(@RequestParam Integer QUESTION_ANSWER_ID, @RequestParam Integer ACCOUNT_ID) {

        this.accountAnswersService.deleteMapping(QUESTION_ANSWER_ID);
        return "redirect:/accountmaps/" + ACCOUNT_ID;

    }

    @RequestMapping(value = "/listposition", method = RequestMethod.GET)
    public String listofallPositions(Model model) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();
        model.addAttribute("modify_by", username);

        List<Position> listofallpositions = this.positionService.getallPositions();
        model.addAttribute("allpositions", listofallpositions);

        return "positiontable";

    }

    @RequestMapping(value = "/updateposition", method = RequestMethod.POST)
    public String updatePosition(Position position, @RequestParam("file") MultipartFile file) {

        // For uploading the image to repository
        UploadUtility upload = new UploadUtility();

        if (!file.isEmpty()) {

            upload.singleFileUpload(file);

        }

        this.positionService.updatePosition(position);
        return "redirect:/listposition";

    }

    @RequestMapping(value = "/addposition", method = RequestMethod.POST)
    public String addPosition(Position position, @RequestParam("file") MultipartFile file,
            PositionHistory positionHistory) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UploadUtility upload = new UploadUtility();

        if (!file.isEmpty()) {

            upload.singleFileUpload(file);
        }

        PositionHistory positionHistSession = this.positionHistoryService.savePositionSession(positionHistory);
        positionHistSession.setPosition_hist_msg("Added by" + " " + auth.getName());

        this.positionService.savePosition(position);
        positionHistSession.setPosition(position);
        this.positionHistoryService.savePositionSession(positionHistory);

        return "redirect:/listposition";

    }

    @RequestMapping(value = "/positionmaps/{id}", method = RequestMethod.GET)
    public String getPositionMapById(@PathVariable("id") Integer POSITION_ID, Model model) {

        Position getPositionMapDetails = this.positionService.getPositionMapping(POSITION_ID);
        model.addAttribute("positionmaps", getPositionMapDetails);

        return "positionmaps";

    }

    @RequestMapping(value = "/addpositionanswers", method = RequestMethod.POST)
    public String addPositionMapping(PositionAnswers positionAnswers) {

        this.positionAnswerService.addPositionAnswers(positionAnswers);
        return "redirect:/positionmaps/" + positionAnswers.getPOSITION_ID();

    }

    @RequestMapping(value = "/deletepositionmap", method = RequestMethod.POST)
    public String deletePositionMapping(@RequestParam Integer QUESTION_ANSWER_ID, @RequestParam Integer POSITION_ID) {

        this.positionAnswerService.deletePositionMapping(QUESTION_ANSWER_ID);
        return "redirect:/positionmaps/" + POSITION_ID;

    }

    @RequestMapping(value = "/sites", method = RequestMethod.GET)
    public String listOfAllSites(Model model) {

        List<Site> getListOfAllSites = this.siteService.getAllSiteList();
        model.addAttribute("sitelist", getListOfAllSites);
        return "pages/sites";

    }

    @RequestMapping(value = "/addnewsite", method = RequestMethod.POST)
    public String addNewSite(Site site) {

        this.siteService.addSite(site);

        List<Answer> answerList = answerService.getallAnswer();
        List<String> siteList = new ArrayList<String>();

        for (Answer answer : answerList) {

            if (answer.getQUESTION_ID() == 1) {

                siteList.add(answer.getANSWER());

            }

        }

        Answer answer = new Answer();
        answer.setITEM_NUMBER(siteList.size() + 1);
        answer.setQUESTION_ID(1);
        answer.setANSWER(site.getSITE_NAME());
        answerService.addAnswer(answer);

        return "redirect:/sites";

    }

    @RequestMapping(value = "/deletesite", method = RequestMethod.POST)
    public String deleteSite(@RequestParam(value = "siteId") Integer siteId,
            @RequestParam(value = "siteName") String siteName) {

        this.siteService.deleteSite(siteId);
        Integer answerId = answerService.getAnswerIdByAnswer(siteName);
        answerService.deleteAnswerById(answerId);

        return "redirect:/sites";

    }

    @RequestMapping(value = "/allusers", method = RequestMethod.GET)
    public String allUserList(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();
        model.addAttribute("changedby", username);

        List<Users> users = this.userService.getAllUsers();
        model.addAttribute("users", users);

        Users newUser = new Users();
        model.addAttribute("newUser", newUser);

        List<UserHistory> userHistoryList = userHistoryService.getAllUserHistory();
        model.addAttribute("userHistoryList", userHistoryList);

        return "userspage";

    }

    @RequestMapping(value = "/users/superuser-role", method = RequestMethod.GET)
    public String allUserList_superuserRole(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();
        model.addAttribute("changedby", username);

        List<Users> adminRoleUsersList = userService.findAllUsersWithAdminRole();
        model.addAttribute("adminRoleUsersList", adminRoleUsersList);

        Users newUser = new Users();
        model.addAttribute("newUser", newUser);

        List<UserHistory> userHistoryList = userHistoryService.getAllUserHistory();
        model.addAttribute("userHistoryList", userHistoryList);

        return "userspage_superUserRole";

    }

    @RequestMapping(value = "/users/user-role", method = RequestMethod.GET)
    public String allUserList_userRole(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();
        model.addAttribute("changedby", username);

        List<Users> userRoleUsersList = userService.findAllUsersWithUserRole();
        model.addAttribute("userRoleUsersList", userRoleUsersList);

        Users newUser = new Users();
        model.addAttribute("newUser", newUser);

        List<UserHistory> userHistoryList = userHistoryService.getAllUserHistory();
        model.addAttribute("userHistoryList", userHistoryList);

        return "userspage_userRole";

    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String Users(@Valid @ModelAttribute("newUser") Users user, BindingResult result, RedirectAttributes attr,
            Model model) {

        if (result.hasErrors()) {

            List<Users> users = this.userService.getAllUsers();
            model.addAttribute("users", users);
            String addUserError = "error";
            model.addAttribute("addUserError", addUserError);
            Users newUser = user;
            model.addAttribute("newUser", newUser);
            return "userspage";

        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String auditor = auth.getName();
        Date date = new Date();

        user.setDateCreated(date);
        user.setCreated_by(auditor);
        user.setLastModifiedDate(date);
        user.setLastModifiedBy(auditor);

        this.userService.addUsers(user);

        return "redirect:/users/superuser-role";

    }

    @RequestMapping(value = "/edituser/{id}", method = RequestMethod.GET)
    public String editUserPage(@PathVariable int id, Model model) {

        Users user = userService.getUsersById(id);
        Set<Role> userRoles = user.getRoles();
        model.addAttribute("user", user);
        String userRole = null;

        for (Role role : userRoles) {

            userRole = role.getUSER_ROLE();

        }

        model.addAttribute("userRole", userRole);

        List<UserHistory> userhistories = userHistoryService.getUserHistoryByUserId(id);
        model.addAttribute("userHistories", userhistories);
        return "edituserdetails";

    }

    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public String editUser(@Valid @ModelAttribute("user") Users user, BindingResult result, RedirectAttributes attr,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute("updateError", "Please fill in the required field/s.");
            model.addAttribute("alertClass", "alert-danger");
            return "edituserdetails";

        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Date date = new Date();
        user.setLastModifiedDate(date);
        user.setLastModifiedBy(username);
        String userId = "" + user.getUSER_ID();

        String submittedStatus = user.getUSER_STATUS();

        if (submittedStatus == null) {

            user.setUSER_STATUS("inactive");

        }

        BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
        Users dbUser = userService.getUsersById(user.getUSER_ID());
        String rawPass = user.getPASSWORD();
        String dbPass = dbUser.getPASSWORD();

        if (rawPass.equals(dbPass)) {

            this.userService.updateUsersWithRawPass(user);

        }

        else {

            this.userService.updateUsers(user);
            attr.addFlashAttribute("resetSuccess", "Password has been reset.");
            attr.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/edituser/" + userId;

        }

        attr.addFlashAttribute("updateSuccess", "Changes successfully saved!");
        attr.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/edituser/" + userId;

    }

    @GetMapping("/manageimages")
    public String viewAllImagePath(Model model) throws IOException {

        List<Map<String, String>> imageList = dataController.getAllImages();
        model.addAttribute("imageList", imageList);

        return "imageList";

    }

    @GetMapping("/testfindacctbyid")
    @ResponseBody
    public Account acctsById() {

        return this.accountService.getaccountbyId(12);

    }

}
