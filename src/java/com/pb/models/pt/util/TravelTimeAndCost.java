/*
 * Copyright 2006 PB Consult Inc.
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
package com.pb.models.pt.util;

import org.apache.log4j.Logger;

import java.io.PrintWriter;


/** A class that represents LOS for a zone pair
 *
 * @author Joel Freedman
 */
public class TravelTimeAndCost {
    private Logger logger = Logger.getLogger(TravelTimeAndCost.class);

    public int itaz;
    public int jtaz;

	public float driveAloneTime;
	public float driveAloneDistance;
	public float driveAloneCost;

	public float sharedRide2Time;
	public float sharedRide2Distance;
	public float sharedRide2Cost;

	public float sharedRide3Time;
	public float sharedRide3Distance;
	public float sharedRide3Cost;

	public float walkTime;
	public float walkDistance;

	public float bikeTime;
	public float bikeDistance;

	public float walkTransitInVehicleTime;
	public float walkTransitFirstWaitTime;
	public float walkTransitShortFirstWaitTime;
	public float walkTransitLongFirstWaitTime;
	public float walkTransitTransferWaitTime;
	public float walkTransitTotalWaitTime;
	public float walkTransitNumberBoardings;
	public float walkTransitWalkTime;
	public float walkTransitFare;

	public float driveTransitInVehicleTime;
	public float driveTransitFirstWaitTime;
	public float driveTransitShortFirstWaitTime;
	public float driveTransitLongFirstWaitTime;
	public float driveTransitTotalWaitTime;
	public float driveTransitTransferWaitTime;
	public float driveTransitNumberBoardings;
	public float driveTransitWalkTime;
	public float driveTransitDriveTime;
	public float driveTransitDriveCost;
	public float driveTransitFare;

	public TravelTimeAndCost(){


	}


     public void printToScreen() {
         logger.info("Travel costs between " + itaz + " and " + jtaz);

         logger.info("driveAloneTime                  = "+driveAloneTime);
         logger.info("driveAloneDistance              = "+driveAloneDistance);
         logger.info("driveAloneCost                  = "+driveAloneCost);

         logger.info("sharedRide2Time                 = "+sharedRide2Time);
         logger.info("sharedRide2Distance             = "+sharedRide2Distance);
         logger.info("sharedRide2Cost                 = "+sharedRide2Cost);

         logger.info("sharedRide3Time                 = "+sharedRide3Time);
         logger.info("sharedRide3Distance             = "+sharedRide3Distance);
         logger.info("sharedRide3Cost                 = "+sharedRide3Cost);

         logger.info("walkTime                        = "+walkTime);
         logger.info("walkDistance                    = "+walkDistance);

         logger.info("bikeTime                        = "+bikeTime);
         logger.info("bikeDistance                    = "+bikeDistance);

         logger.info("walkTransitInVehicleTime        = "+walkTransitInVehicleTime);
         if (walkTransitInVehicleTime > 0) {
        	 logger.info("walkTransitFirstWaitTime        = "+walkTransitFirstWaitTime);
        	 logger.info("walkTransitShortFirstWaitTime   = "+walkTransitShortFirstWaitTime);
        	 logger.info("walkTransitLongFirstWaitTime    = "+walkTransitLongFirstWaitTime);
        	 logger.info("walkTransitTransferWaitTime     = "+walkTransitTransferWaitTime);
        	 logger.info("walkTransitTotalWaitTime        = "+walkTransitTotalWaitTime);
        	 logger.info("walkTransitNumberBoardings      = "+walkTransitNumberBoardings);
        	 logger.info("walkTransitWalkTime             = "+walkTransitWalkTime);
        	 logger.info("walkTransitFare                 = "+walkTransitFare);
         }

         logger.info("driveTransitInVehicleTime       = "+driveTransitInVehicleTime);
         if (driveTransitInVehicleTime > 0) {
             logger.info("driveTransitFirstWaitTime       = "+driveTransitFirstWaitTime);
             logger.info("driveTransitShortFirstWaitTime  = "+driveTransitShortFirstWaitTime);
             logger.info("driveTransitLongFirstWaitTime   = "+driveTransitLongFirstWaitTime);
             logger.info("driveTransitTotalWaitTime       = "+driveTransitTotalWaitTime);
             logger.info("driveTransitTransferWaitTime    = "+driveTransitTransferWaitTime);
             logger.info("driveTransitNumberBoardings     = "+driveTransitNumberBoardings);
             logger.info("driveTransitWalkTime            = "+driveTransitWalkTime);
             logger.info("driveTransitDriveTime           = "+driveTransitDriveTime);
             logger.info("driveTransitDriveCost           = "+driveTransitDriveCost);
             logger.info("driveTransitFare                = "+driveTransitFare);
         }
     };

    public void print(PrintWriter file){
        file.println("Travel Time and Cost Values: ");
    	file.println("\tdriveAloneTime = "+driveAloneTime);
		file.println("\tdriveAloneDistance = "+driveAloneDistance);
		file.println("\tdriveAloneCost = "+driveAloneCost);
		file.println("\tsharedRide2Time = "+sharedRide2Time);
		file.println("\tsharedRide2Distance = "+sharedRide2Distance);
		file.println("\tsharedRide2Cost = "+sharedRide2Cost);
		file.println("\tsharedRide3Time = "+sharedRide3Time);
		file.println("\tsharedRide3Distance = "+sharedRide3Distance);
		file.println("\tsharedRide3Cost = "+sharedRide3Cost);
		file.println("\twalkTime = "+walkTime);
		file.println("\twalkDistance = "+walkDistance);
		file.println("\tbikeTime = "+bikeTime);
		file.println("\tbikeDistance = "+bikeDistance);
		file.println("\twalkTransitInVehicleTime = "+walkTransitInVehicleTime);
		file.println("\twalkTransitFirstWaitTime = "+walkTransitFirstWaitTime);
		file.println("\talkTransitShortFirstWaitTime = "+walkTransitShortFirstWaitTime);
		file.println("\twalkTransitLongFirstWaitTime = "+walkTransitLongFirstWaitTime);
		file.println("\twalkTransitTransferWaitTime = "+walkTransitTransferWaitTime);
		file.println("\twalkTransitTotalWaitTime = "+walkTransitTotalWaitTime);
		file.println("\twalkTransitNumberBoardings = "+walkTransitNumberBoardings);
		file.println("\twalkTransitWalkTime = "+walkTransitWalkTime);
		file.println("\twalkTransitFare = "+walkTransitFare);
		file.println("\tdriveTransitInVehicleTime = "+driveTransitInVehicleTime);
		file.println("\tdriveTransitFirstWaitTime = "+driveTransitFirstWaitTime);
		file.println("\tdriveTransitShortFirstWaitTime = "+driveTransitShortFirstWaitTime);
		file.println("\tdriveTransitLongFirstWaitTime = "+driveTransitLongFirstWaitTime);
		file.println("\tdriveTransitTotalWaitTime = "+driveTransitTotalWaitTime);
		file.println("\tdriveTransitTransferWaitTime = "+driveTransitTransferWaitTime);
		file.println("\tdriveTransitNumberBoardings = "+driveTransitNumberBoardings);
		file.println("\tdriveTransitWalkTime = "+driveTransitWalkTime);
		file.println("\tdriveTransitDriveTime = "+driveTransitDriveTime);
		file.println("\tdriveTransitDriveCost = "+driveTransitDriveCost);
		file.println("\tdriveTransitFare= "+driveTransitFare);
        file.println();
        file.println();

        file.flush();
  	}
}
