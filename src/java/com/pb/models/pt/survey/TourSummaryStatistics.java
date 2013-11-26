package com.pb.models.pt.survey;

//TourSummaryStatistics.java
//
// this class provides summary statistics of tours
// and writes them to a report file: part of the 
// survey package
//

import java.util.*;
import java.io.*;

import com.pb.common.util.OutTextFile;
import com.pb.common.util.ObjectUtil;

public class TourSummaryStatistics{

    //summary variables

    int totalHouseholds=0;
    int totalPersons=0;
    int totalTrips=0;
    int totalStops=0;
    int totalTours=0;
     int stopsDropped=0;
    int day1TotalTrips=0;
    int day1TotalStops=0;
    int day1TotalTours=0;
     int day1StopsDropped=0;
    int day2TotalTrips=0;
    int day2TotalStops=0;
    int day2TotalTours=0;
     int day2StopsDropped;
    int[] toursByPurpose = new int[100];
    int[] day1ToursByPurpose = new int[100];
    int[] day2ToursByPurpose = new int[100];
    String[] toursByPurposeLabels = new String[100];
    int[] employmentStatus = new int[100];
    String[] employmentStatusLabels = new String[100];
    int[] schoolStatus = new int[100];
    String[] schoolStatusLabels = new String[100];

    int totalWorkers;
    int totalStudents;

    //constructor takes a list of households and a filename to write to
    //tours should be coded on household list
    public TourSummaryStatistics(List households, String reportFileName) throws IOException{
        //open report file
        OutTextFile reportFile = new OutTextFile();
        PrintWriter rptFile = reportFile.open(reportFileName);

        ListIterator h = households.listIterator();

        while(h.hasNext()){
            Household thisHousehold = (Household)h.next();
            thisHousehold.calculateStructure();

            //summarize hh stuff here
            ++totalHouseholds;


            //end summarizing hh stuff

            ListIterator p = thisHousehold.persons.listIterator();
            //person summary variables

            while(p.hasNext()){
                Person thisPerson = (Person)p.next();

                //summarize person stuff here
                ++totalPersons;
                ++employmentStatus[thisPerson.getEmploymentStatus()];
                ++schoolStatus[thisPerson.getStudentStatus()];

                //end summarizing person stuff

                ListIterator t1 = thisPerson.getDayTours().listIterator();
                while(t1.hasNext()){
                    Tour thisTour = (Tour)t1.next();

                    //summarize day1 tours here
                    ++totalTours;
                    ++day1TotalTours;
                    totalTrips += 2;
                    day1TotalTrips += 2;
                    stopsDropped += thisTour.getOriginalStops();
                    day1StopsDropped += thisTour.getOriginalStops();
                    if(thisTour.getHasIntermediateStop1()){
                        ++totalStops;
                        ++totalTrips;
                        --stopsDropped;
                        ++day1TotalStops;
                        ++day1TotalTrips;
                        --day1StopsDropped;

                    }
                    if(thisTour.getHasIntermediateStop2()){
                        ++totalStops;
                        ++totalTrips;
                        --stopsDropped;
                        ++day1TotalStops;
                        ++day1TotalTrips;
                        --day1StopsDropped;
                    }
                    Activity thisDestination = (Activity) thisTour.destination;
                    ++toursByPurpose[thisDestination.getGeneralActivity()];
                    ++day1ToursByPurpose[thisDestination.getGeneralActivity()];

                    //end summarizing day1 tours
                }
            } //end persons
        }	//end hhs


        //now print summary statistics
        rptFile.println("Total Households:    "+ totalHouseholds);
        rptFile.println("Total Persons:       "+ totalPersons);
        rptFile.println("");
        rptFile.println("Persons by Employment Status");
        employmentStatusLabels[1]="Emplyd Full-Time   ";
        employmentStatusLabels[2]="Emplyd Part-Time   ";
        employmentStatusLabels[3]="Self Full-Time     ";
        employmentStatusLabels[4]="Self Part-Time     ";
        employmentStatusLabels[5]="Unemplyd,looking   ";
        employmentStatusLabels[6]="Retired            ";
        employmentStatusLabels[7]="Home-maker         ";
        employmentStatusLabels[8]="Not Employed       ";
        employmentStatusLabels[99]="Don't Know-Refused ";
        ObjectUtil.printArray(employmentStatusLabels,employmentStatus,1,rptFile);
        rptFile.println("");
        rptFile.println("Persons by School Status");
        schoolStatusLabels[1]="Student             ";
        schoolStatusLabels[2]="Not Student         ";
        schoolStatusLabels[99]="Don't Know-Refused ";
        ObjectUtil.printArray(schoolStatusLabels, schoolStatus,1,rptFile);
        rptFile.println("");
        rptFile.println("Total Trips:         "+ totalTrips);
        rptFile.println("Total Tours:         "+ totalTours);
        rptFile.println("Total Stops:         "+ totalStops);
        rptFile.println("Total Stops Dropped: "+ stopsDropped);
        rptFile.println("");
        rptFile.println("Tours by Purpose");
        toursByPurposeLabels[1]="Work            ";
        toursByPurposeLabels[2]="School          ";
        toursByPurposeLabels[3]="Major Shop      ";
        toursByPurposeLabels[4]="Other Shop      ";
        toursByPurposeLabels[5]="Soc/Rec         ";
        toursByPurposeLabels[6]="Other           ";
        toursByPurposeLabels[7]="Pickup-Dropoff  ";
        toursByPurposeLabels[8]="Work-Based      ";
        toursByPurposeLabels[9]="Work-Second Job ";

        ObjectUtil.printArray(toursByPurposeLabels, toursByPurpose,1,rptFile);
        rptFile.println("");
        rptFile.println("Summaries by Day:  Day1");
        rptFile.println("Total Trips:         "+ day1TotalTrips);
        rptFile.println("Total Tours:         "+ day1TotalTours);
        rptFile.println("Total Stops:         "+ day1TotalStops);
        rptFile.println("Total Stops Dropped: "+ day1StopsDropped);
        rptFile.println("");
        rptFile.println("Day1 Tours by Purpose");
        ObjectUtil.printArray(toursByPurposeLabels, day1ToursByPurpose,1,rptFile);
        rptFile.println("");
        rptFile.println("Summaries by Day:  Day2");
        rptFile.println("Total Trips:         "+ day2TotalTrips);
        rptFile.println("Total Tours:         "+ day2TotalTours);
        rptFile.println("Total Stops:         "+ day2TotalStops);
        rptFile.println("Total Stops Dropped: "+ day2StopsDropped);
        rptFile.println("");
        rptFile.println("Day2 Tours by Purpose");
        ObjectUtil.printArray(toursByPurposeLabels, day2ToursByPurpose,1,rptFile);
        rptFile.close();
    }	//end constructor

}

