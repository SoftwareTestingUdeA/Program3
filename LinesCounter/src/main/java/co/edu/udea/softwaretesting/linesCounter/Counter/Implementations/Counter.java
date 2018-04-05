package co.edu.udea.softwaretesting.linesCounter.Counter.Implementations;

import co.edu.udea.softwaretesting.linesCounter.Counter.Interfaces.CounterInterface;
import co.edu.udea.softwaretesting.linesCounter.Models.CountedClassData;
import co.edu.udea.softwaretesting.linesCounter.Models.CountedData;
import co.edu.udea.softwaretesting.linesCounter.Models.CountedFunctionData;
import co.edu.udea.softwaretesting.linesCounter.Models.ProjectCloned.FileData;
import co.edu.udea.softwaretesting.linesCounter.Models.ProjectCloned.Project;
import co.edu.udea.softwaretesting.linesCounter.Reader.Implementations.GitProject;
import co.edu.udea.softwaretesting.linesCounter.Reader.Implementations.ReadFiles;
import co.edu.udea.softwaretesting.linesCounter.Reader.Interfaces.GitProjectInterface;
import co.edu.udea.softwaretesting.linesCounter.Reader.Interfaces.ReadFilesInterface;
import co.edu.udea.softwaretesting.linesCounter.Resources.Configurations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Counter implements CounterInterface {

    private GitProjectInterface projectToClone;
    private ReadFilesInterface readFiles;
    private Project project;
    private CountedData countedData;

    public CountedData getLOCCounting() throws IOException {

        projectToClone = new GitProject();
        readFiles = new ReadFiles();
        countedData = new CountedData();

        projectToClone.cloneProject(Configurations.REPO_URL, Configurations.CLONED_DIRECTORY_PATH);
        project = readFiles.projectData(Configurations.CLONED_DIRECTORY_PATH);
        projectToClone.clearFolder(Configurations.CLONED_DIRECTORY_PATH);

        countedData.setName(Configurations.REPO_NAME);

        for (FileData file : project.getFiles()) {
            countedData.getLoc().addAll(classLOCCounting(file.getLines()));
        }

        return countedData;
    }

    private List<CountedClassData> classLOCCounting(List<String> lines) {

        List<CountedClassData> countedClassList = new ArrayList<>();
        int imports = 0;

        for (int i = 0; i < lines.size(); i++) {
            if(lines.get(i).contains("//")) {
                lines.set(i, lines.get(i).substring(0, lines.get(i).indexOf("//")));
            }
        }

        String line = String.join(" ", lines);
        while (line.indexOf("/*") > 0) {
            line =  line.replace(line.trim().substring(line.indexOf("/*"), line.indexOf("*/") + 2), " ");
        }
        line = line.trim().replaceAll(" +", " ");
        String[] classes = line.split(" class | interface ");

        imports = countClass(classes[0]).getLocCountingProperties();

        for (int i = 1; i < classes.length; i++) {
            String s = classes[i];
            CountedClassData countedClassData = countClass(s);
            countedClassList.add(countedClassData);
        }
        countedClassList.get(0).setLocCountingProperties(countedClassList.get(0).getLocCountingProperties() + imports);

        return countedClassList;
    }

    private CountedClassData countClass(String s) {
        CountedClassData count = new CountedClassData("", new ArrayList<>(), 0);
        CountedFunctionData countedFunctionData = new CountedFunctionData();
        if(s.contains("{")) {
            String[] str = s.split("\\)\\{|\\) \\{"); // ){ ó ) {
            count.setName(str[0].split(" ")[0]);
            int i;
            int counterFunction = 0;

            counterFunction = countFunction(str[0]);
            count.setLocCountingProperties(counterFunction + count.getLocCountingProperties());
            String[] functionNameArr = str[0].split("\\(");
            String[] functionName;
            if (str[0].charAt(str[0].length() - 1) == '(') {
                functionName = functionNameArr[functionNameArr.length - 1].split(" ");
            } else if (str[0].contains("(")) {
                functionName = functionNameArr[functionNameArr.length - 2].split(" ");
            } else {
                functionName = new String[] {""};
            }
            countedFunctionData.setName(functionName[functionName.length - 1]);

            for (i = 1; i < str.length; i++) {
                String allClass = str[i];
                counterFunction = 0;
                if (allClass.length() > 6) {
                    while (allClass.contains("(")
                            && ((allClass.substring(allClass.lastIndexOf("(") - 3, allClass.lastIndexOf("("))).contains("if")
                            || (allClass.substring(allClass.lastIndexOf("(") - 4, allClass.lastIndexOf("("))).contains("for")
                            || (allClass.substring(allClass.lastIndexOf("(") - 6, allClass.lastIndexOf("("))).contains("while")
                            || (allClass.substring(allClass.lastIndexOf("(") - 6, allClass.lastIndexOf("("))).contains("catch"))) {

                        counterFunction += countFunction(allClass);
                        countedFunctionData.setLocCountingFunction(counterFunction + countedFunctionData.getLocCountingFunction());
                        i++;
                        allClass = str[i];
                    }
                }
                countedFunctionData.setLocCountingFunction(countFunction(allClass));
                count.getLocCountingFunctions().add(countedFunctionData);

                countedFunctionData = new CountedFunctionData();
                functionNameArr = allClass.split("\\(");
                if (allClass.charAt(allClass.length() - 1) == '(') {
                    functionName = functionNameArr[functionNameArr.length - 1].split(" ");
                } else if (allClass.contains("(")){
                    functionName = functionNameArr[functionNameArr.length - 2].split(" ");
                }

                if (allClass.contains("(")
                        && (functionName[functionName.length - 1].trim().equalsIgnoreCase("if")
                        || functionName[functionName.length - 1].trim().equalsIgnoreCase("while")
                        || functionName[functionName.length - 1].trim().equalsIgnoreCase("for")
                        || functionName[functionName.length - 1].trim().equalsIgnoreCase("catch"))) {
                    continue;
                }

                countedFunctionData.setName(functionName[functionName.length - 1]);
            }
        } else {
            String[] str1 = s.split(";");
            String[] str2 = s.split("@");
            count.setLocCountingProperties(count.getLocCountingProperties() + str1.length + str2.length);
        }
        return count;
    }

    private Integer countFunction(String allSentence) {
        String[] sentences = allSentence.split(";");
        int count = 0;
        Pattern pattern;
        Matcher matcher;
        for (String s : sentences) {
            //Sumo 3 por cada for encontrado
            pattern = Pattern.compile("for");
            matcher = pattern.matcher(s);
            while (matcher.find()) count += 3;

            //Sumo 1 por cada while encontrado
            pattern = Pattern.compile("while");
            matcher = pattern.matcher(s);
            while (matcher.find()) count++;

            //Sumo 1 por cada if encontrado
            pattern = Pattern.compile("if");
            matcher = pattern.matcher(s);
            while (matcher.find()) count++;

            //Sumo 2 por cada while encontrado
            pattern = Pattern.compile("catch");
            matcher = pattern.matcher(s);
            while (matcher.find()) count += 2;

            //Sumo 1 por cada anotación encontrada
            pattern = Pattern.compile("@");
            matcher = pattern.matcher(s);
            while (matcher.find()) count++;

            //Sumo 1 por cada lambda encontrado
            pattern = Pattern.compile("\\(\\{");
            matcher = pattern.matcher(s);
            while (matcher.find()) count++;

            //Sumo 1 por cada closure encontrado
            pattern = Pattern.compile("->");
            matcher = pattern.matcher(s);
            while (matcher.find()) count++;

            //Sumo 1 por cada llamado a metodo encontrado
            pattern = Pattern.compile("\\.[a-zA-Z]+\\(");
            matcher = pattern.matcher(s);
            while (matcher.find()) count++;

            //Resto 1 por cada llamado a getter o setter encontrado
            pattern = Pattern.compile("\\.get|\\.set");
            matcher = pattern.matcher(s);
            while (matcher.find()) count--;

            count++;
        }
        return count;
    }

}


// import package; public class A { private static int a; public getA() { System.out.println(this.a); } } class B { }