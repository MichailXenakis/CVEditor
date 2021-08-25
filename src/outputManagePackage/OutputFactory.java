package outputManagePackage;

import dataManagePackage.Database;

public class OutputFactory {
    public void chooseOutput(int output) {
        if (output==0){
            OutputSystemTxt.saveApplicantInfoToTxt("outputfiles", Database.getCVtemplateArrayListSize()-1);
            OutputSystemLatex.saveApplicantInfoToLatex("outputfiles", Database.getCVtemplateArrayListSize()-1);
        }else if (output==1){
            OutputSystemTxt.saveApplicantInfoToTxt("outputfiles", Database.getCVtemplateArrayListSize()-1);
        }else if (output==2){
            OutputSystemLatex.saveApplicantInfoToLatex("outputfiles", Database.getCVtemplateArrayListSize()-1);
        }
    }
}