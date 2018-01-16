@cdaf-30-feature
  Feature: Run test macros in excel workbook
As a windows platform and application build engineer
I want modules which enable me to run windows platform and application deployment tests from the Java CDAF framework
So that I can use existing CDAF framework to improve delivery of Windows 10 engineering services at Nomura bank.

@cdaf-30-1
Scenario: Run local macro
  Given a macro-enabled workbook in folder: "src/test/resources/cdaf-31/cdaf-30_test01.xslm
  When the file is opened
  #Then the automatic macro should run