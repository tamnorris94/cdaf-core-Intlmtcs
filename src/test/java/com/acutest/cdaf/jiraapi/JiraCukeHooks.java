package com.acutest.cdaf.jiraapi;

import com.acutest.cdaf.common.TestConfiguration;
import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.Scenario;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.apache.xpath.operations.String;

import java.util.*;
import java.lang.String;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class JiraCukeHooks {

    private static Logger logger = LogManager.getLogger(JiraCukeHooks.class);
    // Flag used to achieve @BeforeAll
    private static boolean dunit = false;
    private static Scenario storedScenario;
    private static String storedIssueTag ;    // Stored value of issue key in scenario tag
    public static List<String> results = new ArrayList<>();
    /**
     * BEFORE ALL
     */
    @Before
    public void beforeAll(Scenario scenario )throws Exception {
        logger.debug("Executing beforeAll, about to check 'dunit' value");
        if (!dunit) {


            logger.debug("Initialising before first scenario.");            // Set up afterAll to run at end.
            Runtime.getRuntime().addShutdownHook(new Thread(){
                public void run()  {
                    logger.debug("Executing shutdownHook.");
                    afterAll() ;
                }
            });
            // Add items here to run before all scenarios.
            TestConfiguration.loadAPropertiesFile( "src/test/resources/testConfiguration.yaml");

            dunit = true;
        }
    }
    /**
     * BEFORE SCENARIO
     */
    @Before
    public void beforeScenario(Scenario scenario )throws Exception {
        logger.debug("Executing beforeScenario");

        String issueTag = getTagIssueKey(scenario);
        // Determine if the previous execution was the last example of a tagged scenario.
        if ( storedIssueTag != null
            &&    !storedIssueTag.equals("")
            && !issueTag.equals(storedIssueTag)){
           //afterScenarioChange();
        }
        storedScenario = scenario;
        storedIssueTag = issueTag;
    }

    /**
     * AFTER SCENARIO
     */
    @After
    public void afterScenarioOrExample(Scenario s){
        logger.debug("Executing afterScenarioOrExample");
        logger.trace("Scenario name: {}",s.getName());
        logger.trace("Scenario id   : {}",s.getId());
        logger.trace("Aferscenario, scenario tags   : {}",s.getSourceTagNames());
        logger.trace("Scenario status   : {}",s.getStatus());
//        logger.trace("Scenario Uri: {}",getUri.());
//        logger.trace("Scenario Lines: {}",s.getLines());
        String status = s.getStatus();
        results.add(status);
    }
    /**
     * AFTER SCENARIO CHANGE
     */
    /**
     * Do all the jobs for completion of scenario examples set
     *
     */
    private void afterScenarioChange(){
        logger.debug ("Executing afterScenarioChange");

        //count the outcomes
        Map<String, Long> resultCounts =
                results.stream()
                .collect(groupingBy(Function.identity(), counting()));
        // status to report depends on any failures

        Integer passedCount;
        if (resultCounts.get("passed") == null ) {
            passedCount = 0;
        }else {
            passedCount = resultCounts.get("passed").intValue();
        }

        Integer exceptionsCount = results.size() - passedCount;

        String jiraUpdateSwitch = TestConfiguration.getProperty("jira.makeUpdates");
        if  ( ! jiraUpdateSwitch.contains("false")) {
            String jiraComment = "Test execution completed "
                    + " Example row outcomes: " + resultCounts.toString();
            IssueInstance issue = new IssueInstance(storedIssueTag);
            issue.addComment(jiraComment);

            if (passedCount > 0 && exceptionsCount == 0) {
                issue.updateExecutionStatus("Passed");
            } else {
                issue.updateExecutionStatus("Failed");
            }
        }
        results.clear();
        logger.trace("returning from afterScenarioChange.");
    }
    /**
     * AFTER FEATURE
     */
   /*
   * Post comment to jira issue with the outcome of test run
    */

    /**
     * AFTER ALL SCENARIOS AND FEATURES
     */
    public void afterAll(){
        logger.trace("Executing afterAll");
        // Add items here to run after tests
        if (storedIssueTag != null && !storedIssueTag.equals("")){
            afterScenarioChange();
        }
    }
    /*
    * Return list of issue keys in scenario tags as comma-delimited string
    * Used to test getTagIssueKeys method.
     */
    public static String getTagIssuekeysString(){
        Scenario scenario = storedScenario;
        List<String> issueKeys = getTagIssueKeys(scenario);
        String keyListString = StringUtils.join(issueKeys, ",");
        return keyListString;
    }
    /**
    * Return valid jira issue key from scenario tags.
    * Converts everything to uppercase.
    * <em> warning: may not be the first valid jira issuekey </em> as tags are
    * returned as a Collection.
    * 
    * @return  a possibly empty list of tags, or null if scenario is null. 
    *
     */
    public static List<String> getTagIssueKeys(Scenario scenario){
        logger.trace("Entered getTagIssueKeys");
        logger.debug("Checking scenario exists? : {}", (scenario != null));
        if (scenario == null) {
            return null;
        }
        String message = "";
        for(StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()){
            message = message + System.lineSeparator() + stackTraceElement.toString();
        }
        //logger.debug("Stacktrace (without exception)\n {}", message);
        Collection<String> tags = scenario.getSourceTagNames();
        // Jira official pattern:   Pattern pattern = Pattern.compile("((?<!([A-Z]{1,10})-?)[A-Z]+-\\d+)");
        // https://community.atlassian.com/t5/Bitbucket-questions/Regex-pattern-to-match-JIRA-issue-key/qaq-p/233319
        Pattern  pattern = Pattern.compile("@([A-Za-z]{1,10}-\\d+$)");

        List <String> issueKeys = new ArrayList<String>();
        for (String tag : tags){
            Matcher m = pattern.matcher(tag);
            if (m.find()) {
                String found = m.group(1);
                issueKeys.add(found.toUpperCase());
            }
        }

        if (issueKeys.size() > 1) {
           logger.warn("multiple issuekeys found in scenario tag list");
        }

        String keysList = StringUtils.join(issueKeys, ",");
        logger.trace("Jira issue keys found in scenario tags: {}", keysList);

        return issueKeys;
    }

    String getTagIssueKey(Scenario scenario) {
        List<String> tagIssueKeys = getTagIssueKeys(scenario);
        String issueTag;
        if (tagIssueKeys == null || tagIssueKeys.size() == 0) {
            issueTag = "";
        } else {
            issueTag = tagIssueKeys.get(0);
        }
        return issueTag;
    }
}
