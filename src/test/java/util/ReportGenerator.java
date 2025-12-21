package util;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {

    public static void main(String[] args) {
        System.out.println("Report generated successfully.");
        String path = new File("").getAbsolutePath()+"/target/";
        File reportFolder = new File(path + "JBGROUP");

        Configuration configuration = new Configuration(reportFolder, "test");

        List<String> jsonList = new ArrayList<>();
        jsonList.add(path + "report.json");

        ReportBuilder reportBuilder = new ReportBuilder(jsonList, configuration);
        reportBuilder.generateReports();
    }
}
