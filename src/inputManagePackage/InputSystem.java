package inputManagePackage;

import java.util.Scanner;

import dataManagePackage.CVTemplate;

public abstract class InputSystem {
    abstract protected void skipLines(Scanner inputStream);
    protected int generalInfoEndTagLength;

    protected CVTemplate createTemplateWithGeneralInfo(Scanner inputStream){
        final String name = inputStream.nextLine();
        String applicantName = name.substring(6, name.length() - generalInfoEndTagLength);
        final String address = inputStream.nextLine();
        String applicantAddress = address.substring(9, address.length() - generalInfoEndTagLength);
        final String homeTel = inputStream.nextLine();
        String applicantHomeTelephone = homeTel.substring(18, homeTel.length() - generalInfoEndTagLength);
        final String workTel = inputStream.nextLine();
        String applicantWorkTelephone = workTel.substring(18, workTel.length() - generalInfoEndTagLength);
        final String email = inputStream.nextLine();
        String applicantEmail = email.substring(7, email.length() - generalInfoEndTagLength);
        CVTemplate cvtemplate = new CVTemplate(applicantName, applicantAddress, applicantHomeTelephone, applicantWorkTelephone, applicantEmail);
        return cvtemplate;
    }
}
