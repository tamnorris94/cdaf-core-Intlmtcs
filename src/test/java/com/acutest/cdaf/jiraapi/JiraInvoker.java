package com.acutest.cdaf.jiraapi;

import com.acutest.cdaf.common.TestConfiguration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class to co-ordinate extraction of scenarios and running the tests
 */
public class JiraInvoker {
    private static Logger logger = LogManager.getLogger(JiraInvoker.class);

    public JiraInvoker() {
    }

    public static void runScenarios() {
        String stepdefLocation = TestConfiguration.getProperty("testrun.cucumberOptionGlue");

        String command =
                "mvn test -Pcucumber"
                        + " -Dcucumber.options= \""
                        + " --tags @autorun"
                        + " --glue " + stepdefLocation
                        + "\"";

        try

        {
            Process process = Runtime.getRuntime().exec(command);
            final BufferedReader is = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = is.readLine()) != null) {
                logger.warn(line);
            }
            final BufferedReader is2 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = is2.readLine()) != null) {
                logger.warn(line);
            }
        } catch (IOException e) {
            logger.error("Error writing feature file.", e);
        }
    }
}
