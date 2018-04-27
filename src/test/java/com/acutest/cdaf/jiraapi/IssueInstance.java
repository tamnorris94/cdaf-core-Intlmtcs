package com.acutest.cdaf.jiraapi;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.acutest.cdaf.common.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Represents the jira issue held in repository
 * <p>Retrieves and sets fields an other entities in the Jira issue.</p>
 */
public class IssueInstance {
    private static Logger logger = LogManager.getLogger();
    String issueKey;
    private JsonNode issueNode;
    private JsonNode fieldsNode;
    private boolean isLoaded = false;
    /**
     * Expects the issue to already exist.
     * Returns error if issue does not exist.
     *
     * @param issueId
     */
    public IssueInstance(String issueId) {
        issueKey = issueId;
    }

    /**
     * Returns id of comment created.
     *
     * @param commentText Text to be inserted in the comment
     */
    public String addComment(String commentText) {
        String resource = appendResource("comment");
        String json = "{\"body\": "
                + "\"" + commentText + "\""
                + "}";

        logger.debug("Adding comment to {}", issueKey);
        JiraApi.request("POST", resource, json);
        String commentId = "Return string";
        return commentId;
    }

    public String getSummary() {
        return fieldsNode.path("summary").textValue();
    }

    public String getDescription() {
        return fieldsNode.path("description").textValue();
    }

    /*
    * Return json string representing comment objects: good enough to search for coded words
    *
    * TODO refactor to return list of comment strings
     */
    public String getComments() {
        String comments = fieldsNode.path("comment").path("comments").toString();

        return comments;
    }

    public String getExecutionStatus() {
        String customfieldId = testConfiguration.getProperty("jira.customfieldId.executionStatus");
        String customfieldKey = "customfield_" + customfieldId;
        return fieldsNode.path(customfieldKey).path("value").textValue();
    }


    public String updateExecutionStatus(String status) {
        String customfieldId = testConfiguration.getProperty("jira.customfieldId.executionStatus");
        String customfieldKey = "customfield_" + customfieldId;
        String json = "{\"fields\": "
                    +"{"
                        + "\"" + customfieldKey + "\":"
                            + "{"
                                + "\"value\":"
                                + "\"" + status +"\""
                            + "}"
                    + "}"
                + "}";

        String resource = appendResource("");

        logger.debug("Updating test execution status to {}", status);
        String result = JiraApi.request("PUT", resource, json);

        return result;
    }

    public String loadIssue() {
        try {
            String resource = appendResource("");
            String result = JiraApi.request("GET", resource);

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            issueNode = mapper.readTree(result);

            fieldsNode = issueNode.path("fields");

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ToDo";
    }

    /**
     * Set the issue instance by giving the Jackson Json node
     * @param node
     */
    public void setWithNode (JsonNode node){
        issueNode = node;
        fieldsNode = issueNode.get("fields");

    }
    private String appendResource(String issueResource) {
        return "/rest/api/2/issue/" + issueKey + "/" + issueResource;
    }

    /**
     * Create a feature file at the target location using description in jira issue
     * Returns the filepath of created file.
     * @param location
     */
    public String  createFeaturefile(String location) {

        String ffText =
                "Feature: Filler text from automation.\n"
               + "@" + issueKey +" @autorun\n"
               + getDescription();

        String filepath = location + "/" + issueKey + ".feature";

        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filepath), StandardCharsets.UTF_8));
            writer.write(ffText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filepath;
    }
}
