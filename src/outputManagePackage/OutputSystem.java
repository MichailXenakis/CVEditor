package outputManagePackage;

import java.io.PrintWriter;

import dataManagePackage.CVTemplate;

public abstract class OutputSystem {
    public void printOutputStream(PrintWriter outputStream, CVTemplate cvtemplate) {
        outputStream.println("Name: "+cvtemplate.getApplicantName()+"\\"+"\\");
        outputStream.println("Address: "+cvtemplate.getApplicantAddress()+"\\"+"\\");
        outputStream.println("Telephone (Home): "+cvtemplate.getApplicantHomeTelephone()+"\\"+"\\");
        outputStream.println("Telephone (Work): "+cvtemplate.getApplicantWorkTelephone()+"\\"+"\\");
        outputStream.println("Email: "+cvtemplate.getApplicantEmail()+"\\"+"\\");
        outputStream.println();
    }
}
