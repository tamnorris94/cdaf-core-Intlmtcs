package com.acutest.cdaf.common.DTO;

/**
 * Result folder for this scenario or example.
 */
public class ResultFolderDto {
    private static String currentResultFolder;

    public static String getCurrentResultFolder(){
        return currentResultFolder;
    }

    public static void setCurrentResultFolder(String currentResultFolder){
        ResultFolderDto.currentResultFolder = currentResultFolder;
    }
}
