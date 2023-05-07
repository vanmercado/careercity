package com.careercitydashboard.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.careercitydashboard.Model.Account;
import com.careercitydashboard.Model.AccountAnswers;
import com.careercitydashboard.Model.Answer;
import com.careercitydashboard.Model.Position;
import com.careercitydashboard.Model.Questions;
import com.careercitydashboard.Model.Site;
import com.careercitydashboard.Model.Users;
import com.careercitydashboard.Service.AccountAnswersService;
import com.careercitydashboard.Service.AccountService;
import com.careercitydashboard.Service.AnswerService;
import com.careercitydashboard.Service.PositionService;
import com.careercitydashboard.Service.QuestionsService;
import com.careercitydashboard.Service.SiteService;
import com.careercitydashboard.Service.UsersService;

@RestController
public class DataController {

    private final Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private AccountAnswersService accountAnswerService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private UsersService usersService;

    @RequestMapping(path = "/accounts", method = RequestMethod.GET)
    public List<Account> getallAccounts() {

        return this.accountService.getallAccounts();

    }

    @RequestMapping(path = "/positions", method = RequestMethod.GET)
    public List<Position> getallPositions() {

        return this.positionService.getallPositions();

    }

    @RequestMapping(path = "/questions", method = RequestMethod.GET)
    public List<Questions> getallQuestions() {

        return this.questionsService.getallQuestions();

    }

    @RequestMapping(path = "/answers", method = RequestMethod.GET)
    public List<Answer> getallAnswers() {

        return this.answerService.getallAnswer();

    }

    @RequestMapping(path = "/accountanswers", method = RequestMethod.GET)
    public List<AccountAnswers> getallAccountAnswers() {

        return this.accountAnswerService.getallAccountAnswers();

    }

    @RequestMapping(path = "/accountmap/{id}", method = RequestMethod.GET)
    public Account getaccountmapbyId(@PathVariable(value = "id") Integer ACCOUNT_ID) {

        return this.accountService.getAccountMapping(ACCOUNT_ID);

    }

    @GetMapping("/sitelist")
    public List<Site> getAllSiteListJson() {

        return this.siteService.getAllSiteList();

    }

    @GetMapping("/userlist")
    public List<Users> getAllUsers() {

        return (List<Users>) this.usersService.getAllUsers();

    }

    @GetMapping("/whosloggedin")
    public String userLoggedIn() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        logger.info("last logged by" + auth.getName());
        return username;

    }

    @GetMapping("/imagelist")
    public List<Map<String, String>> getAllImages() throws IOException {

        List<String> imagePathsFromRepo = new ArrayList<>();
        List<String> fileNamesFromRepo = new ArrayList<>();

        String WEB_APP_URL = "https://tipcareercity.com/ImageRepo/";

        File folder = new File("//opt/tomcat/webapps/ImageRepo//");
        File[] listOfFiles = folder.listFiles();

        try {

            for (File file : listOfFiles) {
                imagePathsFromRepo.add(WEB_APP_URL + file.getName());
                fileNamesFromRepo.add(file.getName());
            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        List<Map<String, String>> imageList = new ArrayList<Map<String, String>>();

        for (int i = 0; i < imagePathsFromRepo.size(); i++) {

            Map<String, String> image = new HashMap<String, String>();
            image.put("filename", fileNamesFromRepo.get(i));
            image.put("imagePath", imagePathsFromRepo.get(i));
            imageList.add(image);

        }

        return imageList;

    }

}
