// ActivityDurationFile.java
//      jf 11/2/2000


package com.pb.models.pt.survey;
import java.util.*;
import java.io.*;
import com.pb.common.util.OutTextFile;

/**  This class creates an estimation
     file for the duration of activities
*/
public class ActivityDurationFile{


	public ActivityDurationFile(List households) 
		throws IOException{

		//open output estimation files : home
		OutTextFile aFile = new OutTextFile();
		PrintWriter actFile = aFile.open("activities.dat");
		
		//begin iterating		
		System.out.println("Writing activity durations\n");
		ListIterator h = households.listIterator();
		while(h.hasNext()){
			Household thisHousehold = (Household)h.next();
			ListIterator p = thisHousehold.persons.listIterator();
			while(p.hasNext()){
				Person thisPerson = (Person)p.next();

				
					ListIterator t;
					Pattern thisPattern = new Pattern("");
  				    t = thisPerson.dayTours.listIterator();
					thisPattern = new Pattern(thisPerson.getWordWithStops(1));
					int tourNumber=0;
					ArrayList tourActivities = new ArrayList();
					//for each tour, build an arraylist containing all the activities of that tour
					while(t.hasNext()){
						Tour thisTour= (Tour)t.next();
						++tourNumber;
											
						//first activity of the day
						Activity thisActivity = (Activity)thisTour.getLeaveOrigin();
						thisActivity.parentTourNumber=tourNumber;
						//only add originActivity if it is the first tour of the day
						if(tourNumber==1){
							thisActivity.parentTourOrigin=1;
							tourActivities.add(thisActivity);
						}	
								
						//first intermediate stop
           				if(thisTour.hasIntermediateStop1){
							thisActivity = (Activity)thisTour.getIntermediateStop1();
							thisActivity.parentTourIStop1=1;
							thisActivity.parentTourNumber=tourNumber;
							tourActivities.add(thisActivity);
						}
						//tour destination
						thisActivity = (Activity)thisTour.getDestination();
						thisActivity.parentTourPrimary=1;
						thisActivity.parentTourNumber=tourNumber;
						tourActivities.add(thisActivity);
						
						//second intermediate stop
           				if(thisTour.hasIntermediateStop2){
							thisActivity = (Activity)thisTour.getIntermediateStop2();
							thisActivity.parentTourIStop2=1;
							thisActivity.parentTourNumber=tourNumber;
							tourActivities.add(thisActivity);
						}
						//end of tour
						thisActivity = (Activity)thisTour.getArriveOrigin();
						thisActivity.parentTourNumber=tourNumber;
						thisActivity.parentTourDestination=1;
						tourActivities.add(thisActivity);
					}
					//write the activity data
					ListIterator a = tourActivities.listIterator();
					while(a.hasNext()){
						Activity thisActivity = (Activity)a.next();
						Tour thisTour=new Tour();
						thisTour=(Tour)thisPerson.dayTours.get(thisActivity.parentTourNumber-1);
						char letter=thisActivity.letter;
						
						//need to fix this method because I changed the number of arguments - rp
						//printDurationRecord(actFile,thisHousehold,thisPerson,thisPattern,thisTour,thisActivity,letter);
						
					} //end activities
			}//end persons
		} //end households					
					
		actFile.close();
	} //end method

	void printDurationRecord(PrintWriter thisFile,Household thisHousehold,Person thisPerson, 
		Pattern thisPattern,Tour thisTour,Activity thisActivity, int i,char letter){
		thisActivity.calculateDuration();
		thisHousehold.print(thisFile);
		thisPerson.print(thisFile);
		thisFile.print(" "+(i+1)+" ");    	//day number
		thisPattern.print(thisFile);
		thisTour.print(thisFile);				
		thisActivity.print(thisFile);
		thisFile.print(" ");
		thisActivity.activityTrip.print(thisFile);
		thisFile.print(" "+thisActivity.alreadyThere+" ");
		thisFile.print(" "+thisActivity.parentTourNumber+" ");  		//which tour does activity belong to?
		thisFile.print(" "+thisActivity.parentTourPrimary+" ");		//is it the primary activity of the tour (0,1)?
		thisFile.print(" "+thisActivity.parentTourIStop1+" ");		//is it intermediate stop 1 (0,1)?
		thisFile.print(" "+thisActivity.parentTourIStop2+" ");		//is it intermediate stop 2 (0,1)?
		thisFile.print(" "+thisActivity.parentTourOrigin+" ");		//is it the home/work origin (0,1)?
		thisFile.print(" "+thisActivity.parentTourDestination+" ");	//is it the home/work destination (0,1)?

		if(letter=='h')
			thisFile.print(" "+1);
		else if(letter=='w')
			thisFile.print(" "+2);
		else if(letter=='c')
			thisFile.print(" "+3);
		else if(letter=='s')
			thisFile.print(" "+4);
		else if(letter=='r')
			thisFile.print(" "+5);
		else if(letter=='o')
			thisFile.print(" "+6);
		else if(letter=='b')
			thisFile.print(" "+7);

		thisFile.print("\n");
	}

} //end class