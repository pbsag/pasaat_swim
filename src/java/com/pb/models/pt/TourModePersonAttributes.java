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



/** 
 * Person Attributes for Tour Mode Choice
 * 
 * @author Joel Freedman
 * @version 1.0 12/01/2003
 * 
 */
public class TourModePersonAttributes {

    public int size;
    public int autos;
    public int workers;
    public int age;
    public int income;
    public int originZone;
    public int destinationZone;
    public float primaryDuration;
    public ActivityPurpose tourPurpose;

    //dummy variables
    public int totalStops;
    public int stop0;
    public int stop1;
    public int stop2;
    public int size1;
    public int size2;
    public int size3p;
    public int inclow;
    public int incmed;
    public int inchi;
    public int auwk0;
    public int auwk1;
    public int auwk2;
    public int noon;
  
   
    public void setAttributes(PTHousehold thisHousehold, PTPerson thisPerson, Tour thisTour){

                                                                         
        stop0=0;
        stop1=0;
        stop2=0;
        size1=0;
        size2=0;
        size3p=0;
        inclow=0;
        incmed=0;
        inchi=0;
        auwk0=0;
        auwk1=0;
        auwk2=0;
        noon=0;
                                                                        
        size=thisHousehold.size;                                      
        autos=thisHousehold.autos;                                    
        workers=thisHousehold.workers;                                
        age=thisPerson.age;                                           
        income=thisHousehold.income;                                  
        originZone=thisTour.begin.location.zoneNumber;                
        destinationZone=thisTour.primaryDestination.location.zoneNumber;
        primaryDuration=thisTour.primaryDestination.duration;
        tourPurpose=thisTour.getPurpose();
     
        if(thisTour.iStopsCheck(1,thisTour.tourString)==1)
           totalStops=1;
        else if(thisTour.iStopsCheck(2,thisTour.tourString)==1)
               totalStops=2;
        else totalStops = 0;
               
        if(totalStops==0)
             stop0=1;
        else if(totalStops==1)
             stop1=1;
        else
             stop2=1;

        if(size==1)
             size1=1;
        else if(size==2)
             size2=1;
        else
             size3p=1;
        
        setIncomeSegment(thisHousehold.income);
        setAutoSegment(thisHousehold.autos,thisHousehold.workers);
 
        if(thisTour.begin.endTime>1130 && thisTour.begin.endTime<1300)
             noon=1;
    }

   /** Set the income segment based on household income.
    * 
    * @param income income
    */
   private void setIncomeSegment(int income){
       if(income<20000)
           inclow=1;
      else if(income>=20000 && income<=60000)
           incmed=1;
      else
           inchi=1;
       
   }

   /**
    * Set the auto segment based on autos to workers
    * @param autos number of autos
    * @param workers number of workers
    */
 private void setAutoSegment(int autos, int workers){
    if(autos==0)
         auwk0=1;
    else if(autos<workers)
         auwk1=1;
    else
         auwk2=1;

 }
}
     
     
