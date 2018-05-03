package com.acutest.cdaf.jiraapi;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.acutest.cdaf.common.TestConfiguration;

public class JiraApi {
    static String authority;
    static String authString;
    private static Logger logger = LogManager.getLogger(JiraApi.class);
    static {
        authority = "https://" + TestConfiguration.getProperty("jira.host");
        String jiraUser = getUsername();
        char[] jiraPassword = getPassword();
        String credentials = jiraUser + ":" + new String(jiraPassword);
        authString = "Basic " + Base64.encodeBase64String(credentials.getBytes());
    }

    /**
     * Two parameters, used for GET
     */
    public static String request(String requestType, String resource) {
        return request(requestType, resource, "");
    }

    /**
     * Send http request to Jira REST API, returning body or empty sting
     *
     * @param requestType: POST, GET, or PUT
     * @param resource:    resource string
     * @param content:     content to post as JSON string, or empty string
     * @return response body, usually json string.
     * @throws Exception
     */
    public static String request(String requestType, String resource, String content) {


        //String encodedResource = null;
        //try {
        //    encodedResource = URLEncoder.encode(resource, StandardCharsets.UTF_8.toString());
        //} catch (UnsupportedEncodingException e) {
        //    e.printStackTrace();
        //}
        String uri = authority + resource;
        String requestResponse = "";
        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //logger.trace("url.openConnection response code: {}", conn.getResponseCode());
            //logger.trace("url.openConnection response error message: {}", conn.getResponseCode());
            conn.setRequestMethod(requestType);
            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestProperty("Origin", authority);
            conn.setRequestProperty("Authorization", authString);

            logger.trace(requestType + "ing to {}", uri);

            if (requestType.equals("POST") || requestType.equals("PUT")) {
                logger.trace("Request contents: {}", content);
                conn.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(content);
                wr.flush();
                wr.close();
            }
            int responseCode = conn.getResponseCode();
            String responseMessage = conn.getResponseMessage();
            logger.trace("Request response code: {}", responseCode);
            logger.trace("Request response message: {}", responseMessage);

            // Nothing is returned for response code 204
            if ( responseCode != 204) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String output;
                StringBuffer response = new StringBuffer();
                logger.trace("About to read connection in buffer");
                while ((output = in.readLine()) != null) {
                    response.append(output);
                }
                in.close();

                requestResponse = response.toString();

                logger.trace("Response body initial chars: {}", response.toString().substring(0, 100));
                //logger.trace("Response body initial chars: {}",response.toString());
            }
        } catch (MalformedURLException e) {

        } catch (IOException e) {
            logger.warn(e.getMessage(), e);

        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }


        return requestResponse;
    }

    /**
     * Run a jQuery request on Jira to fetch issues
     *
     * @param jquery
     * @return Map of issueInstance s.
     */
    public static  Map<String, IssueInstance> runJQuery(String jquery) {
        HashMap<String, IssueInstance> issues = new HashMap<String, IssueInstance>() ;
        JsonNode jQueryNode;
        JsonNode issueNode;

        String resource = "/rest/api/2/search?jql="
                + jquery;
        String result = request("GET", resource);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            jQueryNode = mapper.readTree(result);

            Integer count = jQueryNode.get("total").intValue();
            for (Integer i = 0; i < count; i++){
                    issueNode = jQueryNode.get("issues").get(i);
                    String key = issueNode.get("key").textValue();
                    IssueInstance issue = new IssueInstance(key);
                    issue.setWithNode(issueNode);
                    issues.put(key, issue);
            }
            //issuesNode.forEach(node -> {
            //    String key = node.get("key").textValue();
            //    IssueInstance issue = new IssueInstance(key);
            //    issue.setWithNode(node);
            //    issues.put(key, issue);
            //});

        } catch (JsonGenerationException e) {
            e.printStackTrace();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return issues;


    }
    
    private static String getUsername() {
        String result = TestConfiguration.getProperty("jira.defaultUser");
        if (result == null || result.length() == 0) {
            result = promptUser("Please enter your Jira username");
        }
        return result;
    }
    
    private static char[] getPassword() {
        char[] result = System.getenv("JIRA_PASSWORD").toCharArray();
        if (result == null || result.length == 0) {
            result = promptUser("Please enter your password").toCharArray();
        }
        return result;
    }

    private static String promptUser(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        String result = sc.nextLine();
        sc.close();
        return result;
    }
}
