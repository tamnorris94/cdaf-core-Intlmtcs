package com.acutest.cdaf.jiraapi;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JiraUtils {
    private static Logger logger = LogManager.getLogger(JiraUtils.class);

    public static String timestampTextShort() {
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        return format.format(curDate);
    }

    public static boolean isValidIssueKey(String candidate){
        Pattern pattern = Pattern.compile("([A-Za-z]{1,10}-\\d+$)");
        Matcher m = pattern.matcher(candidate);
        return m.find();
    }

    /**
     * Delete feature files in the given location.
     * @param dirPath: path to location
     * <p>kWarning: this will delete all feature files in this location</p>
     */
     public static void deleteFeatureFiles(String dirPath) {
        try {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.feature");
            Files.list(Paths.get(dirPath))
                    .filter (path -> matcher.matches(path))
                    .map(Path:: toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
    }
}
