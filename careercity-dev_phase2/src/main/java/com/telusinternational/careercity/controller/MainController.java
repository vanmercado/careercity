package com.telusinternational.careercity.controller;

import java.security.Principal;
/*import java.util.Collections;
import java.util.Comparator;*/
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.telusinternational.careercity.dao.UsersRepository;
import com.telusinternational.careercity.enums.UsedUsername;
import com.telusinternational.careercity.model.Account;
import com.telusinternational.careercity.model.AccountClicks;
import com.telusinternational.careercity.model.Answer;
import com.telusinternational.careercity.model.AnswerSheet;
import com.telusinternational.careercity.model.Position;
import com.telusinternational.careercity.model.SearchedKey;
import com.telusinternational.careercity.model.Site;
import com.telusinternational.careercity.model.Users;
import com.telusinternational.careercity.service.AccountService;
import com.telusinternational.careercity.service.AnswerService;
import com.telusinternational.careercity.service.AnswerSheetRecordService;
import com.telusinternational.careercity.service.PositionService;
import com.telusinternational.careercity.service.QuestionnaireResultsService;
import com.telusinternational.careercity.service.SearchedKeyService;
import com.telusinternational.careercity.service.SiteService;
import com.telusinternational.careercity.service.TMLogsService;
import com.telusinternational.careercity.service.UsersServiceImpl;
import com.telusinternational.careercity.util.AnswersHelper;
import com.telusinternational.careercity.util.Response;
import com.telusinternational.careercity.util.UserUtilities;

@Controller
@SessionAttributes("accounts")

public class MainController extends Response {

    @Autowired
    public QuestionnaireResultsService questionnairesultsService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private SearchedKeyService searchedKeyService;

    @Autowired
    private AnswerSheetRecordService answerSheetRecordService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TMLogsService tmLogsService;

    @Autowired
    private UsersServiceImpl userService;

    private Map<String, String> accounts;

    /*
     * @GetMapping("/login") public String index() { return "login"; }
     * 
     * @GetMapping("/screen-saver") public String screenSaver() { return
     * "screen-saver"; }
     */

    @Autowired
    private HttpSession session;

    public MainController() {

        accounts = new HashMap<String, String>();
        accounts.put("cco", "CC");
        accounts.put("digitalsolutions", "DS");
        accounts.put("support", "ST");

    }

    @GetMapping("/login")
    public String Index() {

        return "login";

    }

    @GetMapping("/signout")
    public String signout() {

        return "logout";

    }

    @GetMapping("/")
    public String Landing(Authentication auth, Model model, RedirectAttributes redirect, Principal principal,
            OAuth2Authentication oauth2) {

        //UserDetailsImpl authenticatedUser = (UserDetailsImpl) auth.getPrincipal();
        //Users user = authenticatedUser.getUsers();

        //if(tmLogsService.countUserExistence(user) == 1 && this.session.getAttribute("initChangePasswordPassed") == null ) {
        //    return "redirect:/initChangePasswordForm";
        //}

        @SuppressWarnings("unchecked")
        LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) oauth2.getUserAuthentication().getDetails();
        String email = (String) properties.get("preferred_username");
        this.tmLogsService.saveLogRepository(email);

        return "index";

    }

    @GetMapping("/welcome")
    public String index(Model model) {

        Account account = accountService.getAccountById(1);

        List<Account> getallaccounts = accountService.getAllAccounts();
        model.addAttribute("listofaccounts", getallaccounts);

        List<Answer> answers = this.answerService.findByQuestionId(2);

        model.addAttribute("account", account);
        model.addAttribute("answers", answers);

        return "test";

    }

    @RequestMapping(value = "/accounts/{account}", method = RequestMethod.GET)
    public String accountsCCO(@PathVariable("account") String account, Model model) {

        //System.out.println("test call");
        if (account.equals("cco")) {

            List<Account> accountList = accountService.getCcoAccount(accounts.get(account));

            for (Account acct : accountList) {
                // System.out.println(acct.getTM_TASK());
            }

            model.addAttribute("accountList", accountList);

        }
        else {

            List<Position> positionList = positionService.getDsStAccount(accounts.get(account));
            model.addAttribute("accountList", positionList);

        }

        model.addAttribute("pillar", account);
        List<Site> allSiteList = this.siteService.getAllSite();
        model.addAttribute("sitelist", allSiteList);
        return "accounts";

    }

    @GetMapping("/{LOCATION}")
    public String findByLocation(@PathVariable String LOCATION, Model model) {

        List<Account> getbylocation = this.accountService.getByLocation(LOCATION);
        model.addAttribute("matchedResults", getbylocation);
        //System.out.println(getbylocation.size());

        return "result";

    }

    @GetMapping("/{LOCATION}/{ACCOUNT}/description")
    public String resultDescription(@PathVariable String LOCATION, @PathVariable Integer ACCOUNT, Model model) {

        Account account = this.accountService.getAccountById(ACCOUNT);
        model.addAttribute("account", account);

        return "result-description";

    }

    @GetMapping("/quiz")
    public String quiz(Principal principal, HttpSession session, Model model) {

        model.addAttribute("principal", principal);

        String username = principal.getName();

        Users userDetails = null;

        UsedUsername usedUsername = new UserUtilities().identifyUsedUsername(username);

        if (usedUsername.equals(UsedUsername.EMAIL)) {

            userDetails = usersRepository.findByEmail(username);

        }
        else {

            userDetails = usersRepository.findByUsername(username);

        }

        model.addAttribute("userDetails", userDetails);

        if (session.getAttribute("answerSheetsCco") == null) {

            AnswersHelper.createCcoAnswerSheets(this.accountService.getAllAccounts());
            session.setAttribute("answerSheetsCco", AnswersHelper.answerSheetsCco);

        }

        if (session.getAttribute("answerSheetsDsSt") == null) {

            AnswersHelper.createDsStAnswerSheets(positionService.getAllPositions());
            session.setAttribute("answerSheetsDsSt", AnswersHelper.answerSheetsDsSt);

        }

        model.addAttribute("answerSheet", new AnswerSheet());
        model.addAttribute("question1", this.answerService.findByQuestionId(1));
        model.addAttribute("question2", this.answerService.findByQuestionId(2));
        model.addAttribute("question3", this.answerService.findByQuestionId(3));
        model.addAttribute("question4", this.answerService.findByQuestionId(4));
        model.addAttribute("question5", this.answerService.findByQuestionId(5));
        model.addAttribute("question6", this.answerService.findByQuestionId(6));
        model.addAttribute("question7", this.answerService.findByQuestionId(7));
        model.addAttribute("question8", this.answerService.findByQuestionId(8));
        model.addAttribute("question9", this.answerService.findByQuestionId(9));
        model.addAttribute("question10", this.answerService.findByQuestionId(10));
        model.addAttribute("question11", this.answerService.findByQuestionId(11));
        model.addAttribute("question12", this.answerService.findByQuestionId(12));

        return "quiz";

    }

    @PostMapping("/result")
    public String submitAnswers(@RequestParam(required = false) String quizWorkdayEmail,
            @RequestParam(required = false) Integer quizWorkdayId, @RequestParam(required = false) String quizName,
            @ModelAttribute("answer") AnswerSheet userAnswers, Model model, HttpSession session, Authentication auth) {

        // cco unchecked; ds and/or st checked
        if (userAnswers.getQ2().isEmpty() && ((!userAnswers.getQ3().isEmpty() && !userAnswers.getQ4().isEmpty())
                || (!userAnswers.getQ3().isEmpty() || !userAnswers.getQ4().isEmpty()))) {

            /*
             * Set all empty answers to null for uniformity
             */
            userAnswers = AnswersHelper.setAllEmptyAnswersToNull(userAnswers);
            //System.out.println( "Quiz Answer: " + userAnswers.toString() );

            //UserDetailsImpl authenticatPrincipal = (UserDetailsImpl) auth.getPrincipal();
            Users user = userService.getAuthenticatedUserByEmail(quizWorkdayEmail);

            quizWorkdayId = Integer.parseInt(user.getUsername().toString());
            this.answerSheetRecordService.saveNewAnswerSheetRecord(userAnswers, quizWorkdayId, user);

            @SuppressWarnings("unchecked")
            HashMap<Integer, AnswerSheet> answerSheetsDsSt = (HashMap<Integer, AnswerSheet>) session.getAttribute("answerSheetsDsSt");

            List<Position> matchedDsStAccounts = questionnairesultsService.getResultsDsSt(userAnswers, answerSheetsDsSt, quizWorkdayId, user);

            List<Position> matchedDsStAccountList = matchedDsStAccounts.stream().filter(x -> !x.getCATEGORY().equalsIgnoreCase("NA")).collect(Collectors.toList());

            List<Position> matchedDsStAccountList_withJobReq_sorted = matchedDsStAccountList.stream().filter(x -> x.getJOB_REQ() != null && x.getJOB_REQ().length() != 0).collect(Collectors.toList());
            /*
             * Start Delete - Daryll Moya - 23-FEB-2021
             */
            //List<Position> matchedDsStAccountList_withoutJobReq = matchedDsStAccountList.stream().filter(x -> x.getJOB_REQ() == null || x.getJOB_REQ().length() == 0).collect(Collectors.toList());
            //matchedDsStAccountList_withJobReq_sorted.addAll(matchedDsStAccountList_withoutJobReq);
            /*
             * End Delete - Daryll Moya - 23-FEB-2021
             */
            model.addAttribute("matchedDsStResults", matchedDsStAccountList_withJobReq_sorted);
            /*
             * Start Change - Daryll Moya - 23-FEB-2021
             */
            //int totalPage = matchedDsStAccountList.size();
            int totalPage = matchedDsStAccountList_withJobReq_sorted.size();
            /*
             * End Change - Daryll Moya - 23-FEB-2021
             */
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("quizWorkdayId", quizWorkdayId);
            model.addAttribute("quizName", quizName);

        }
        // cco checked; ds and st unchecked
        else if (!userAnswers.getQ2().isEmpty() && (userAnswers.getQ3().isEmpty() && userAnswers.getQ4().isEmpty())) {

            /*
             * Set all empty answers to null for uniformity
             */
            userAnswers = AnswersHelper.setAllEmptyAnswersToNull(userAnswers);
            //System.out.println( "Quiz Answer: " + userAnswers.toString() );

            //UserDetailsImpl authenticatPrincipal = (UserDetailsImpl) auth.getPrincipal();
            Users user = userService.getAuthenticatedUserByEmail(quizWorkdayEmail);

            quizWorkdayId = Integer.parseInt(user.getUsername().toString());
            this.answerSheetRecordService.saveNewAnswerSheetRecord(userAnswers, quizWorkdayId, user);

            @SuppressWarnings("unchecked")
            HashMap<Integer, AnswerSheet> answerSheetsCco = (HashMap<Integer, AnswerSheet>) session.getAttribute("answerSheetsCco");

            List<Account> matchedCcoAccounts = questionnairesultsService.getResultsCco(userAnswers, answerSheetsCco, quizWorkdayId, user);

            List<Account> matchedCcoAccountList = matchedCcoAccounts.stream().filter(x -> !x.getCATEGORY().equalsIgnoreCase("NA")).collect(Collectors.toList());

            List<Account> matchedCcoAccountList_withJobReq_sorted = matchedCcoAccountList.stream().filter(x -> x.getJOB_REQ() != null && x.getJOB_REQ().length() != 0).collect(Collectors.toList());
            /*
             * Start Delete - Daryll Moya - 23-FEB-2021
             */
            //List<Account> matchedCcoAccountList_withoutJobReq = matchedCcoAccountList.stream().filter(x -> x.getJOB_REQ() == null || x.getJOB_REQ().length() == 0).collect(Collectors.toList());
            //matchedCcoAccountList_withJobReq_sorted.addAll(matchedCcoAccountList_withoutJobReq);
            /*
             * End Delete - Daryll Moya - 23-FEB-2021
             */
            model.addAttribute("matchedCcoResults", matchedCcoAccountList_withJobReq_sorted);
            /*
             * Start Change - Daryll Moya - 23-FEB-2021
             */
            //int totalPage = matchedCcoAccountList.size();
            int totalPage = matchedCcoAccountList_withJobReq_sorted.size();
            /*
             * End Change - Daryll Moya - 23-FEB-2021
             */
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("quizWorkdayId", quizWorkdayId);
            model.addAttribute("quizName", quizName);

            //List<Account> matchedCcoAccountList_withoutJobReq = matchedCcoAccountList.stream().filter(x -> x.getJOB_REQ().equalsIgnoreCase("")).collect(Collectors.toList());
            //matchedCcoAccountList_withJobReq_sorted.addAll(matchedCcoAccountList_withoutJobReq);

        }
        // cco checked; ds and/or st checked
        else {

            /*
             * set all empty answers to null for uniformity
             */
            userAnswers = AnswersHelper.setAllEmptyAnswersToNull(userAnswers);
            //System.out.println( "Quiz Answer: " + userAnswers.toString() );

            //UserDetailsImpl authenticatPrincipal = (UserDetailsImpl) auth.getPrincipal();
            Users user = userService.getAuthenticatedUserByEmail(quizWorkdayEmail);

            quizWorkdayId = Integer.parseInt(user.getUsername().toString());
            this.answerSheetRecordService.saveNewAnswerSheetRecord(userAnswers, quizWorkdayId, user);

            @SuppressWarnings("unchecked")
            HashMap<Integer, AnswerSheet> answerSheetsCco = (HashMap<Integer, AnswerSheet>) session.getAttribute("answerSheetsCco");
            @SuppressWarnings("unchecked")
            HashMap<Integer, AnswerSheet> answerSheetsDsSt = (HashMap<Integer, AnswerSheet>) session.getAttribute("answerSheetsDsSt");

            List<Account> matchedCcoAccounts = questionnairesultsService.getResultsCco(userAnswers, answerSheetsCco, quizWorkdayId, user);

            List<Account> matchedCcoAccountList = matchedCcoAccounts.stream().filter(x -> !x.getCATEGORY().equalsIgnoreCase("NA")).collect(Collectors.toList());

            List<Account> matchedCcoAccountList_withJobReq_sorted = matchedCcoAccountList.stream().filter(x -> x.getJOB_REQ() != null && x.getJOB_REQ().length() != 0).collect(Collectors.toList());
            /*
             * Start Delete - Daryll Moya - 23-FEB-2021
             */
            List<Account> matchedCcoAccountList_withoutJobReq = matchedCcoAccountList.stream().filter(x -> x.getJOB_REQ() == null || x.getJOB_REQ().length() == 0).collect(Collectors.toList());
            matchedCcoAccountList_withJobReq_sorted.addAll(matchedCcoAccountList_withoutJobReq);
            /*
             * End Delete - Daryll Moya - 23-FEB-2021
             */
            model.addAttribute("matchedCcoResults", matchedCcoAccountList_withJobReq_sorted);

            List<Position> matchedDsStAccounts = questionnairesultsService.getResultsDsSt(userAnswers, answerSheetsDsSt, quizWorkdayId, user);

            List<Position> matchedDsStAccountList = matchedDsStAccounts.stream().filter(x -> !x.getCATEGORY().equalsIgnoreCase("NA")).collect(Collectors.toList());

            List<Position> matchedDsStAccountList_withJobReq_sorted = matchedDsStAccountList.stream().filter(x -> x.getJOB_REQ() != null && x.getJOB_REQ().length() != 0).collect(Collectors.toList());
            /*
             * Start Delete - Daryll Moya - 23-FEB-2021
             */
            //List<Position> matchedDsStAccountList_withoutJobReq = matchedDsStAccountList.stream().filter(x -> x.getJOB_REQ() == null || x.getJOB_REQ().length() == 0).collect(Collectors.toList());
            //matchedDsStAccountList_withJobReq_sorted.addAll(matchedDsStAccountList_withoutJobReq);
            /*
             * End Delete - Daryll Moya - 23-FEB-2021
             */
            model.addAttribute("matchedDsStResults", matchedDsStAccountList_withJobReq_sorted);
            /*
             * Start Change - Daryll Moya - 23-FEB-2021
             */
            //int totalPage = matchedCcoAccountList.size() + matchedDsStAccountList.size();
            int totalPage = matchedCcoAccountList_withJobReq_sorted.size() + matchedDsStAccountList_withJobReq_sorted.size();
            /*
             * End Change - Daryll Moya - 23-FEB-2021
             */
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("quizWorkdayId", quizWorkdayId);
            model.addAttribute("quizName", quizName);

        }

        return "result";

    }// public String submitAnswers

    @GetMapping("/error")
    public String errorPage() {

        return "error";

    }

    @ResponseBody
    @PostMapping("/addClickCount")
    public String addClickCount(@RequestParam Integer accountId, @RequestParam String pillar,
            @RequestParam String email, Authentication auth) {

        Users user = userService.getAuthenticatedUserByEmail(email);

        AccountClicks click = new AccountClicks(accountId, pillar, user);
        this.accountService.saveClickCounts(click);
        return "Success";

    }

    @ResponseBody
    @RequestMapping(value = "/addSearchedKey", method = RequestMethod.POST)
    public String addSerchedKey(@RequestParam String searchedText, @RequestParam String email,
            HttpServletRequest request, Authentication auth) {

        Users user = userService.getAuthenticatedUserByEmail(email);

        SearchedKey searchedKey = new SearchedKey();
        searchedKey.setSEARCHED_TEXT(searchedText);
        searchedKey.setUser(user);

        this.searchedKeyService.saveSearchedKey(searchedKey);

        return "Success";

    }

    @RequestMapping(value = "/sitelist", method = RequestMethod.GET)
    public String getAllSiteList(Model model) {

        List<Site> allSiteList = this.siteService.getAllSite();
        model.addAttribute("sitelist", allSiteList);
        return "accounts";

    }

    @RequestMapping(value = "/initChangePasswordForm", method = RequestMethod.GET)
    public String initChangePasswordForm() {

        return "changePasswordForm";

    }

    @RequestMapping(value = "/initChangePassword", method = RequestMethod.POST)
    public String initialChangePassword(Authentication auth, RedirectAttributes redirectAttr,
            @RequestParam String newPassword, @RequestParam String confirmPassword) {

        //Users user = UserUtilities.getAuthenticatedUser(auth);

        redirectAttr.addAttribute("initChangePasswordRedirected");

        if (newPassword.equals(confirmPassword)) {

            //user.setPASSWORD(newPassword);
            this.session.setAttribute("initChangePasswordPassed", "passed");

        }
        else {

            redirectAttr.addAttribute("passwordMismatch");
            return "redirect:/initChangePasswordForm";

        }

        //this.userService.updateUsers(user);
        return "redirect:/";

    }

}
