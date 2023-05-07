package com.telusinternational.careercity.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telusinternational.careercity.model.Account;
import com.telusinternational.careercity.model.Answer;
import com.telusinternational.careercity.model.AnswerSheet;
import com.telusinternational.careercity.model.FTAccountAnswers;
import com.telusinternational.careercity.model.FTPositionAnswers;
import com.telusinternational.careercity.model.Position;

/*
 * 
 * Class for building Account answer sheets to match with the quiz form
 * 
 */

public class AnswersHelper {

    public static Map<Integer, AnswerSheet> answerSheetsCco = new HashMap<>();

    public static Map<Integer, AnswerSheet> answerSheetsDsSt = new HashMap<>();

    /*
     * This function creates a list of AnswerSheets of all accounts present in the
     * database
     */
    public static void createCcoAnswerSheets(List<Account> accounts) {

        /*
         * First, loop through all accounts
         */
        for (Account account : accounts) {

            AnswerSheet answerSheet = new AnswerSheet();

            //System.out.println( "--------------------------------" + account.getACCOUNT_NAME().toUpperCase() + "--------------------------------");
            //System.out.println(account.getQuestionAnswers().toString());

            /*
             * For every accounts, loop through it's answers
             */
            for (FTAccountAnswers accountAnswer : account.getQuestionAnswers()) {

                //System.out.println("\t----------" + accountAnswer.getQuestion().getQUESTION() + "----------");

                Answer answer = accountAnswer.getAnswer();

                /*
                 * System.out.println("\t\tANSWER(" + answer.getANSWER_ID() + "): " +
                 * answer.getANSWER());
                 */
                List<Answer> answerList = new ArrayList<>();

                /*
                 * Filter every answer by QUEsTION_ID and add them to an AnswerSheet
                 */
                switch (answer.getQUESTION_ID()) {

                case 1: // Preferred site location

                    if (answerSheet.getQ1() != null) {
                        answerList = answerSheet.getQ1();
                    }
                    answerList.add(answer);
                    answerSheet.setQ1(answerList);
                    break;

                case 2: // Preferred Type of Work (CCO)

                    if (answerSheet.getQ2() != null) {
                        answerList = answerSheet.getQ2();
                    }
                    answerList.add(answer);
                    answerSheet.setQ2(answerList);
                    break;

                case 3: // Preferred Type of Work (Digital Services)

                    if (answerSheet.getQ3() != null) {
                        answerList = answerSheet.getQ3();
                    }
                    answerList.add(answer);
                    answerSheet.setQ3(answerList);
                    break;

                case 4: // Preferred Type of Work (Support)

                    if (answerSheet.getQ4() != null) {
                        answerList = answerSheet.getQ4();
                    }
                    answerList.add(answer);
                    answerSheet.setQ4(answerList);
                    break;

                case 5: // Preferred Work Schedule (max 2)

                    if (answerSheet.getQ5() != null) {
                        answerList = answerSheet.getQ5();
                    }
                    answerList.add(answer);
                    answerSheet.setQ5(answerList);
                    break;

                case 6: // Educational Background

                    if (answerSheet.getQ6() != null) {
                        answerList = answerSheet.getQ6();
                    }
                    answerList.add(answer);
                    answerSheet.setQ6(answerList);
                    break;

                case 7: // Field of Study

                    if (answerSheet.getQ7() != null) {
                        answerList = answerSheet.getQ7();
                    }
                    answerList.add(answer);
                    answerSheet.setQ7(answerList);
                    break;

                case 8: // Years of BPO Experience

                    if (answerSheet.getQ8() != null) {
                        answerList = answerSheet.getQ8();
                    }
                    answerList.add(answer);
                    answerSheet.setQ8(answerList);
                    break;

                case 9: // Type of BPO Experience

                    if (answerSheet.getQ9() != null) {
                        answerList = answerSheet.getQ9();
                    }
                    answerList.add(answer);
                    answerSheet.setQ9(answerList);
                    break;

                case 10: // Additional Skills (General)

                    if (answerSheet.getQ10() != null) {
                        answerList = answerSheet.getQ10();
                    }
                    answerList.add(answer);
                    answerSheet.setQ10(answerList);
                    break;

                case 11: // Additional Skills (Basic Troubleshooting)

                    if (answerSheet.getQ11() != null) {
                        answerList = answerSheet.getQ11();
                    }
                    answerList.add(answer);
                    answerSheet.setQ11(answerList);
                    break;

                case 12: // Additional Skills (Information Technology)

                    if (answerSheet.getQ12() != null) {
                        answerList = answerSheet.getQ12();
                    }
                    answerList.add(answer);
                    answerSheet.setQ12(answerList);
                    break;

                }

                // Add the account AnswerSheet to the master list
                answerSheetsCco.put(accountAnswer.getAccount().getACCOUNT_ID(), answerSheet);

            }

        }

    }

    public static void createDsStAnswerSheets(List<Position> allPosition) {

        for (Position position : allPosition) {

            AnswerSheet answerSheet = new AnswerSheet();

            for (FTPositionAnswers positionAnswer : position.getQuestionAnswers()) {

                Answer answer = positionAnswer.getAnswer();
                List<Answer> answerList = new ArrayList<>();

                switch (answer.getQUESTION_ID()) {

                case 3: // Preferred Type of Work (Digital Services)

                    if (answerSheet.getQ3() != null) {
                        answerList = answerSheet.getQ3();
                    }
                    answerList.add(answer);
                    answerSheet.setQ3(answerList);
                    break;

                case 4: // Preferred Type of Work (Support)

                    if (answerSheet.getQ4() != null) {
                        answerList = answerSheet.getQ4();
                    }
                    answerList.add(answer);
                    answerSheet.setQ4(answerList);
                    break;

                }

                answerSheetsDsSt.put(positionAnswer.getPosition().getPOSITION_ID(), answerSheet);

            }

        }

    }

    /*
     * Function to match a (single) account's AnswerSheet to user submitted
     * AnswerSheet via quiz form
     */
    public static boolean isCcoMatch(AnswerSheet accountAnswers, AnswerSheet userAnswers) {

        boolean isMatch = true;

        if (userAnswers.equals(accountAnswers)) {
            return isMatch;
        }

        // Question Number 1
        if ((((userAnswers.getQ1() == null) && (accountAnswers.getQ1() == null))
                || ((userAnswers.getQ1() != null) && (accountAnswers.getQ1() != null)))
                        && userAnswers.getQ1().containsAll(accountAnswers.getQ1())) {
            //System.out.println("Q1: Yes");
        }
        else {
            isMatch = false;
            //System.out.println("Q1: X");
        }

        // Question Number 2
        if ((((userAnswers.getQ2() == null) && (accountAnswers.getQ2() == null))
               || ((userAnswers.getQ2() != null) && (accountAnswers.getQ2() != null)))
                   && userAnswers.getQ2().containsAll(accountAnswers.getQ2())) {
            //System.out.println("Q2: Yes");
        }
        else {
            isMatch = false;
            //System.out.println("Q2: X");
        }

        // Question Number 5
        // Old condition
        //if (((userAnswers.getQ5() == null) && (accountAnswers.getQ5() == null))
        //    || ((userAnswers.getQ5() != null) && (accountAnswers.getQ5() != null)) 
        //        && userAnswers.getQ5().containsAll(accountAnswers.getQ5())) {
        //     System.out.println("Q5: Yes");
        // }
        // else {
        //     isMatch = false; System.out.println("Q5: X");
        // }
        // if ((userAnswers.getQ5() == null && accountAnswers.getQ5() != null) || accountAnswers.getQ5() == null || !accountAnswers.getQ5().contains(userAnswers.getQ5().get(0))) {
        //     System.out.println("Q5: X");
        //     isMatch = false;
        // }
        // else {
        //     System.out.println("Q5: Yes");
        // }

        // Question Number 6
        // if ((userAnswers.getQ6() == null && accountAnswers.getQ6() != null) || accountAnswers.getQ6() == null
        //         || !accountAnswers.getQ6().contains(userAnswers.getQ6().get(0))) {
        //     System.out.println("Q6: X");
        //     isMatch = false;
        // }
        // else {
        //     System.out.println("Q6: Yes");
        // }

        // Question Number 7
        // if ((userAnswers.getQ7() == null && accountAnswers.getQ7() != null) || ((accountAnswers.getQ7() != null)
        //         && (!accountAnswers.getQ7().contains(userAnswers.getQ7().get(0))))) {
        //     System.out.println("Q7: X");
        //     isMatch = false;
        // }
        // else {
        //     System.out.println("Q7: Yes");
        // }

        // Question Number 8
        if ((userAnswers.getQ8() == null && accountAnswers.getQ8() != null) || ((accountAnswers.getQ8() != null)
                && (!accountAnswers.getQ8().contains(userAnswers.getQ8().get(0))))) {
            //System.out.println("Q8: X");
            isMatch = false;
        }
        else {
            //System.out.println("Q8: Yes");
        }
            
        // Question Number 9
        // if (((userAnswers.getQ9() == null) && (accountAnswers.getQ9() == null))
        //         || ((userAnswers.getQ9() != null) && (accountAnswers.getQ9() != null))
        //                 && !Collections.disjoint(userAnswers.getQ9(), accountAnswers.getQ9())) {
        //     //System.out.println("Q9: Yes");
        // }
        // else {
        //     //System.out.println("Q9: X");
        //     isMatch = false;
        // }

        // Question Number 10
        if ((accountAnswers.getQ10() == null) || ((userAnswers.getQ10() != null) && (accountAnswers.getQ10() != null))
                && userAnswers.getQ10().containsAll(accountAnswers.getQ10())) {
            //System.out.println("Q10: Yes");
        }
        else {
            isMatch = false;
            //System.out.println("Q10: X");
        }

        // Question Number 11
        if ((accountAnswers.getQ11() == null) || ((userAnswers.getQ11() != null) && (accountAnswers.getQ11() != null))
                && userAnswers.getQ11().containsAll(accountAnswers.getQ11())) {
            //System.out.println("Q11: Yes");
        }
        else {
            isMatch = false;
            //System.out.println("Q11: X");
        }
        
        // Question Number 12
        if ((accountAnswers.getQ12() == null) || ((userAnswers.getQ12() != null) && (accountAnswers.getQ12() != null))
                && userAnswers.getQ12().containsAll(accountAnswers.getQ12())) {
            //System.out.println("Q12: Yes");
        }
        else {
            isMatch = false;
            //System.out.println("Q12: X");
        }

        // q10 if (((userAnswers.getQ10() == null) && (accountAnswers.getQ10() ==
        // null)) || ((userAnswers.getQ10() != null) && (accountAnswers.getQ10() !=
        // null)) && userAnswers.getQ10().containsAll(accountAnswers.getQ10())) {
        // 
        // System.out.println("Q10: Yes");
        // 
        // } else { isMatch = false; System.out.println("Q10: X"); } // q11 if
        // ((accountAnswers.getQ11() == null) || ((userAnswers.getQ11() != null) &&
        // (accountAnswers.getQ11() != null)) &&
        // userAnswers.getQ11().containsAll(accountAnswers.getQ11())) {
        // 
        // System.out.println("Q11: Yes");
        // 
        // } else { isMatch = false; System.out.println("Q11: X"); } // q12 if
        // ((accountAnswers.getQ12() == null) || ((userAnswers.getQ12() != null) &&
        // (accountAnswers.getQ12() != null)) &&
        // userAnswers.getQ12().containsAll(accountAnswers.getQ12())) {
        // 
        // System.out.println("Q12: Yes");
        // 
        // }
        // else { 
        //    isMatch = false; System.out.println("Q12: X"); 
        // }

        return isMatch;

    }

    public static boolean isDsStMatch(AnswerSheet accountAnswers, AnswerSheet userAnswers) {

        if (((userAnswers.getQ3() != null) && (accountAnswers.getQ3() != null))
                && userAnswers.getQ3().containsAll(accountAnswers.getQ3())) {
            return true;
        }
        else if (((userAnswers.getQ4() != null) && (accountAnswers.getQ4() != null))
                && userAnswers.getQ4().containsAll(accountAnswers.getQ4())) {
            return true;
        }

        return false;

    }

    public static AnswerSheet setAllEmptyAnswersToNull(AnswerSheet answerSheet) {

        if ((answerSheet.getQ1() != null) && answerSheet.getQ1().isEmpty()) {
            answerSheet.setQ1(null);
        }

        if ((answerSheet.getQ2() != null) && answerSheet.getQ2().isEmpty()) {
            answerSheet.setQ2(null);
        }

        if ((answerSheet.getQ3() != null) && answerSheet.getQ3().isEmpty()) {
            answerSheet.setQ3(null);
        }

        if ((answerSheet.getQ4() != null) && answerSheet.getQ4().isEmpty()) {
            answerSheet.setQ4(null);
        }

        if ((answerSheet.getQ5() != null) && answerSheet.getQ5().isEmpty()) {
            answerSheet.setQ5(null);
        }

        if ((answerSheet.getQ6() != null) && answerSheet.getQ6().isEmpty()) {
            answerSheet.setQ6(null);
        }

        if ((answerSheet.getQ7() != null) && answerSheet.getQ7().isEmpty()) {
            answerSheet.setQ7(null);
        }

        if ((answerSheet.getQ8() != null) && answerSheet.getQ8().isEmpty()) {
            answerSheet.setQ8(null);
        }

        if ((answerSheet.getQ9() != null) && answerSheet.getQ9().isEmpty()) {
            answerSheet.setQ9(null);
        }

        if ((answerSheet.getQ10() != null) && answerSheet.getQ10().isEmpty()) {
            answerSheet.setQ10(null);
        }

        if ((answerSheet.getQ11() != null) && answerSheet.getQ11().isEmpty()) {
            answerSheet.setQ11(null);
        }

        if ((answerSheet.getQ12() != null) && answerSheet.getQ12().isEmpty()) {
            answerSheet.setQ12(null);
        }

        return answerSheet;

    }

}
