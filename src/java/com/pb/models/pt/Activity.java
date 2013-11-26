/*
 * Copyright  2005 PB Consult Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.pb.models.pt;

import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Vector;

/** A class that represents an activity, part of a tour
 * 
 * @author Joel Freedman
 * @version 1.0 12/01/2003
 */

public class Activity implements Serializable{
    final transient Logger logger = Logger.getLogger(Activity.class);
    // Attributes  (they are ints because they are multiplied by params in the Duration Model!)
    public class Location implements Serializable {
        public int zoneNumber;
        int gridCell;

     public void print(){
          logger.info("\tzoneNumber: "+zoneNumber);
          logger.info("\tgridCell:  "+gridCell);
     }
    }

    public Location location = new Location();

    public int activityNumber;
    public ActivityPurpose activityPurpose;
    public ActivityType activityType;
    
    //public int startAM;
    //public int startMD;
    //public int startPM;
    //public int startEV;

    //military time
    public short duration;
    public short startTime;
    public short endTime;
    public short timeToActivity;
    //trip to activity
    public TripModeType tripMode;
    public float distanceToActivity;
    // Associations

    /**
     */
    public Activity() {
    }

    public void setNominalLocation() {
    }

    /**
    *
    * 
    */
    protected Vector myPersonTimeSlot;
 
    /**
     * Calculate and return the duration.  Also store it with this activity.
     * 
     * @return The duration in minutes.
     */
    public short calculateDuration(){
        
        int startHour = startTime / 100;
        int startMinute = startTime - startHour * 100;
        
        int endHour = endTime / 100;
        int endMinute = endTime - endHour * 100;

        duration = (short) ((endHour - startHour) * 60);
        duration += (short) (endMinute - startMinute);
        return duration;
    }

     //returns minutes left in day based on day ending 2:59 am, using ending time of activity
    public float minutesLeftInDay() {

        int endHour = endTime / 100;
        int endMinute = endTime - endHour * 100;

        int endHoursRemaining = 27 - endHour;

        int endMinutesRemaining = 60 - endMinute;

        if (endMinutesRemaining > 0)
            --endHoursRemaining;

        return new Integer((endHoursRemaining * 60) + endMinutesRemaining)
            .floatValue();
    }

    //calculates end time of activity
    void calculateEndTime() {

        short hours = (short)(duration / 60);
        short minutes = (short)(duration - (hours * 60));

        short startHours = (short)(startTime / 100);
        short startMinutes = (short)(startTime - (startHours * 100));

        if ((startMinutes + minutes) >= 60) {
            ++hours;
            minutes = (short)((startMinutes + minutes) - 60);
            startMinutes = 0;
        }
        endTime = (short)((startHours * 100) + (hours * 100) + startMinutes + minutes);
    }

    //calculates startTime of current activity based on time of last activity and time to get to this activity
    void calculateStartTime(Activity lastActivity) {
        //time to activity is broken down into hours and minutes
         int hours = timeToActivity / 60;
         int minutes = timeToActivity - (hours * 60);

         //prior to adding on the timeToActivity to the end time of the first activity, break
         //down the end time into hours and minutes
         int endHours = lastActivity.endTime/100;
         int endMinutes = lastActivity.endTime - (endHours*100);

         if(endMinutes + minutes >= 60){ //we need to increment the hours
             hours++;
             minutes = (endMinutes + minutes)-60;  //and fix the minutes
             endMinutes=0;
         }
         //return the correct time in military format.
         startTime=(short)((endHours*100) + (hours*100)+ endMinutes + minutes);
    }

    // Operations
    public void print() {

        logger.info("Activity Number " + activityNumber);


        if (activityType==ActivityType.PRIMARY_DESTINATION)
            logger.info(", Primary Destination");
        else if (activityType==ActivityType.INTERMEDIATE_STOP)
            logger.info(", Intermediate Stop");
        else if (activityType==ActivityType.BEGIN)
            logger.info(", Tour Begin");
        else if (activityType==ActivityType.END)
            logger.info(", Tour End");

        if (activityPurpose==ActivityPurpose.HOME)
            logger.info("Activity Purpose:  Home");
        if (activityPurpose==ActivityPurpose.WORK||activityPurpose==ActivityPurpose.WORK_BASED)
            logger.info("Activity Purpose:  Work");
        if (activityPurpose==ActivityPurpose.GRADESCHOOL)
            logger.info("Activity Purpose:  School");
        if (activityPurpose==ActivityPurpose.COLLEGE)
            logger.info("Activity Purpose:  College");
        if (activityPurpose==ActivityPurpose.SHOP)
            logger.info("Activity Purpose:  Shop");
        if (activityPurpose==ActivityPurpose.RECREATE)
            logger.info("Activity Purpose:  Recreate");
        if (activityPurpose==ActivityPurpose.OTHER)
            logger.info("Activity Purpose:  Other");
        if (activityPurpose==ActivityPurpose.WORK_BASED)
            logger.info("Activity Purpose:  WorkBased");

//        if (startAM == 1)
//            logger.info("Activity Start Period:  AM");
//        if (startMD == 1)
//            logger.info("Activity Start Period:  MD");
//        if (startPM == 1)
//            logger.info("Activity Start Period:  PM");
//        if (startEV == 1)
//            logger.info("Activity Start Period:  EV");

        logger.info("Activity Duration:  " + duration);
        logger.info("       Start Time:  " + startTime);
        logger.info("         End Time:  " + endTime);

        logger.info("Location:");
        location.print();

        if (tripMode != null)
            logger.info("Mode:  " + tripMode);

    }

    public void print(PrintWriter file) {
        file.println("Activity Attributes:");
        file.println("\tActivity Number " + activityNumber);
        file.print("Activity Type = ");
         if (activityType==ActivityType.PRIMARY_DESTINATION)
            file.println("\tPrimary Destination");
        else if (activityType==ActivityType.INTERMEDIATE_STOP)
            file.println("\tIntermediate Stop");
        else if (activityType==ActivityType.BEGIN)
            file.println("\tTour Begin");
        else if (activityType==ActivityType.END)
            file.println("\tTour End");
        if (activityPurpose==ActivityPurpose.HOME)
            file.println("\tActivity Purpose =  Home");
        else if (activityPurpose==ActivityPurpose.WORK)
            file.println("\tActivity Purpose =  Work");
        else if (activityPurpose==ActivityPurpose.GRADESCHOOL)
            file.println("\tActivity Purpose =  School");
        else if (activityPurpose==ActivityPurpose.COLLEGE)
            file.println("\tActivity Purpose =  College");
        else if (activityPurpose==ActivityPurpose.SHOP)
            file.println("\tActivity Purpose =  Shop");
        else if (activityPurpose==ActivityPurpose.RECREATE)
            file.println("\tActivity Purpose =  Recreate");
        else if (activityPurpose==ActivityPurpose.OTHER)
            file.println("\tActivity Purpose =  Other");
        else if (activityPurpose==ActivityPurpose.WORK_BASED)
            file.println("\tActivity Purpose =  WorkBased");
        file.println("\tActivity Duration =  " + duration);
        file.println("\tStart Time =  " + startTime);
        file.println("\tEnd Time =  " + endTime);
        file.println("Location = " + location.zoneNumber);
        file.println();
        file.println();

        file.flush();

    }

    //to write to a text file, csv format
    void printCSV(PrintWriter file) {

 //       int activityType = 0;

  //      if (activityPurpose==ActivityPurpose.HOME)
 //           activityType = 1;
 //       if (activityPurpose==ActivityPurpose.WORK||activityPurpose==ActivityPurpose.WORK_BASED)
 //           activityType = 2;
//        if (activityPurpose==ActivityPurpose.SCHOOL)
//            activityType = 3;
//        if (activityPurpose==ActivityPurpose.SHOP)
//            activityType = 4;
 //       if (activityPurpose==ActivityPurpose.RECREATE)
 //           activityType = 5;
 //       if (activityPurpose==ActivityPurpose.OTHER)
 //           activityType = 6;
 //       if (activityPurpose==ActivityPurpose.WORK_BASED)
 //           activityType = 7;

        //int startPeriod = 0;

//        if (startAM == 1)
//            startPeriod = 1;
//        if (startMD == 1)
//            startPeriod = 2;
//        if (startPM == 1)
//            startPeriod = 3;
//        if (startEV == 1)
//            startPeriod = 4;


        file.print(
            activityPurpose
                + ","
                + startTime
                + ","
                + endTime
                + ","
                + timeToActivity
                + ","
                + distanceToActivity
                + ","
                + tripMode
                + ","
                + location.zoneNumber
                + ",");

    }
    //Returns a zero or one depending on
    public int startTimePeriodCheck(int startTime, String timePeriod){
        int timePeriodCheck=0;
        if(startTime>=300 && startTime<1000 && timePeriod.equals("AM"))
           timePeriodCheck=1;
        else if(startTime>=1000 && startTime<1530 && timePeriod.equals("MD"))
           timePeriodCheck=1;
        else if(startTime>=1530 && startTime<1830 && timePeriod.equals("PM"))
           timePeriodCheck=1;
        else if(startTime>=1830 && timePeriod.equals("EV"))
           timePeriodCheck=1;
        return timePeriodCheck;
    }
    public String startTimePeriod(int startTime){
        String timePeriod;
        if(startTime>=300 && startTime<1000)timePeriod="AM";
        else if(startTime>=1000 && startTime<1530)timePeriod="MD";
        else if(startTime>=1530 && startTime<1830)timePeriod="PM";
        else if(startTime>=1830) timePeriod="EV";
        else {
            logger.warn("??? startTime < 300!!!");
            timePeriod="< 300";
        } 
        return timePeriod;
    }
    
    public int checkActivityType(short activityType, short testActivityType){
            int activityTypeReturn = 0;
            if (activityType==testActivityType) activityTypeReturn=1;
            return activityTypeReturn;
        }
        
    public int checkActivityPurpose(ActivityPurpose activityPurpose, ActivityPurpose testActivityPurpose){
            int activityPurposeReturn = 0;
            if (testActivityPurpose==ActivityPurpose.WORK && 
                (activityPurpose==ActivityPurpose.WORK     ||
                 activityPurpose==ActivityPurpose.WORK_BASED))
                     {activityPurposeReturn=1;
            } 
            else if (activityPurpose==testActivityPurpose)
                activityPurposeReturn=1;
                               
            return activityPurposeReturn;            
    }
} /* end class Activity */
