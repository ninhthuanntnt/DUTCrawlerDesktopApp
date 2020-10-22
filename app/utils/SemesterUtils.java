package com.ntnt.dutcrawler.app.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SemesterUtils {

    public static String getSemesterTypeFromSemester(String semester){
        int i1 = semester.indexOf("/");
        int i2 = semester.indexOf("-");

        String prefix = semester.substring(i1 + 1, i2).substring(2);
        String term = semester.substring(0, i1);
        switch (term){
            case "1":
                return prefix + "10";
            case "2":
                return prefix + "20";
            case "Hè":
                return prefix + "21";
        }

        return null;
    }

    public static List<String> renderSemestersFromClassName(String className){
        List<String> semesters = new ArrayList<>();

        String val = className.substring(0,2);
        Calendar curDate = Calendar.getInstance();

        int curYear = curDate.get(Calendar.YEAR);
        int startYear = -1;

        int curYearTemp = curYear;
        while(true){
            if(curYearTemp%Math.pow(10,val.length()) == Integer.parseInt(val)){
                startYear = curYearTemp;
                break;
            }
            curYearTemp--;
        }

        if(startYear != -1){
            for(; startYear <= curYear ; startYear++){
                String semester = startYear + "-" + (startYear + 1);
                semesters.add("1/" + semester);
                semesters.add("2/" + semester);
                semesters.add("Hè/" + semester);
            }
        }

        int len = semesters.size();
        if(curDate.get(Calendar.MONTH) >= 8){
            semesters = semesters.subList(0, len - 2);
        }else if(curDate.get(Calendar.MONTH) >= 6){
            semesters = semesters.subList(0, len - 3);
        }else{
            semesters = semesters.subList(0, len - 4);
        }

        return semesters;
    }
}
