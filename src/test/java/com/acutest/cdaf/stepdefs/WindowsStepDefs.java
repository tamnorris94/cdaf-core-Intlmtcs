package com.acutest.cdaf.stepdefs;

        import com.acutest.cdaf.windowstests.ExcelDoc;
        import cucumber.api.PendingException;
        import cucumber.api.java.en.Given;
        import cucumber.api.java.en.When;

public class WindowsStepDefs {

    @Given("^a macro-enabled workbook in folder: \"(.*)\"$")
    public void a_macro_enabled_workbook_in_folder(String location) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ExcelDoc excelDoc = new ExcelDoc();
    }

    @When("^the file is opened$")
    public void the_file_is_opened() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }



}
