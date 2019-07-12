# cdaf-Xray implementation

# to run locally:

Pull branch CDAF-137_Xray.

Add environment variable to your computer called XRAY_PRIVATEKEY with value 64c3412cf763890b809d0bebc95b7c8747bbdb7c0bc2a1c52d725f2f84ed670d

run mvn install from command line

run mvn clean verify -Dexec.mainClass=com.acutest.cdaf.xray.XrayExport -Dexec.args="CDAFSBXC-26"

check in maven logs for the line: [ERROR] Sent successfully{"id":....

# to run from Jenkins:

Set-up Jenkins and install Environment Injector Plugin & restart Jenkins server

point Jenkins at CDAF-core branch CDAF-137_Xray

In 'Build' section click 'Add build step' then 'Inject Environment Variables' in 'Properties Content' box write XRAY_PRIVATEKEY=64c3412cf763890b809d0bebc95b7c8747bbdb7c0bc2a1c52d725f2f84ed670d

In 'Build' section click 'Add build step' then 'Invoke Top-level Maven Targets' in 'Goals' box write 
clean verify -Dexec.mainClass=com.acutest.cdaf.xray.XrayExport -Dexec.args="CDAFSBXC-26"

check console output for the line: [ERROR] Sent successfully{"id":....
