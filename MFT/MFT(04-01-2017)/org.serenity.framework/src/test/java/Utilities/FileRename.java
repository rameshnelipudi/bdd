package Utilities;

import Pages.BasePage;
import Pages.DataFeedRequestFormPage;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;

import java.io.File;
import java.io.IOException;

/**
 * Created by E002465 on 04-01-2017.
 */
public class FileRename extends BasePage{
    @Step
    public String fileRename(String newFileName,String fileFormate) throws IOException {
        String rootDir = new File(".").getCanonicalPath();
        String TestFilesPath = rootDir + "/src/test/resources/TestFiles/".replace("/", File.separator);
        File dir = new File(TestFilesPath);
        System.out.println("TestFilesPath>>>>"+TestFilesPath);
        System.out.println("newFileName+fileFormate>>>>"+newFileName+fileFormate);
        File newName = new File(TestFilesPath+newFileName+fileFormate);
        String[] filesOfDir=dir.list();
        if(filesOfDir.length!=0&&filesOfDir!=null) {
            for (String fileArray : dir.list()) {
                System.out.println("File in directory>>>>>>" + fileArray);
                if (fileArray.endsWith(fileFormate)) {
                    dir = new File(dir + "\\" + fileArray);
                    System.out.println("dir>>>" + dir);
                    System.out.println("newName>>>" + newName);
                    dir.renameTo(newName);
                }
            }

        }else{
            return null;
        }
        return newName.toString();
    }
}
