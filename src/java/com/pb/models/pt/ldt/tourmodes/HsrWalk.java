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
package com.pb.models.pt.ldt.tourmodes;

import com.pb.common.model.ModelException;
import com.pb.models.pt.Mode;
import com.pb.models.pt.ldt.LDModeChoiceHouseholdAttributes;
import com.pb.models.pt.ldt.LDTour;
import com.pb.models.pt.ldt.LDTourModeType;
import com.pb.models.pt.ldt.LDTourPatternType;
import com.pb.models.pt.ldt.LDTravelTimeAndCost;
import org.apache.log4j.Logger;

import static com.pb.models.pt.ldt.LDInternalModeChoiceParameters.*;

import java.util.ResourceBundle;

/**
 * Long-distance bus mode.
 *
 * @author Erhardt
 * @version 1.0 Apr 5, 2006
 *
 */
public class HsrWalk extends Mode {

    protected static Logger logger = Logger.getLogger(HsrWalk.class);

    private final static long serialVersionUID = 287;

    private static int m;

    public HsrWalk(ResourceBundle rb) {

        isAvailable = true;
        hasUtility = false;
        utility = 0.0D;
        alternativeName = new String("HighSpeedRailWalk");
//        type = LDTourModeType.HSR_WALK;
        HsrWalk.m = type.ordinal();
    }


    /**
     * Calculates utility of choosing this mode
     *
     * @param c -
     *            TourModeParameters
     * @param hh -
     *            LDModeChoiceHouseholdAttributes
     * @param tour -
     *            LD tour of interest
     * @param tc -
     *            TravelTimeAndCost
     * @return boolean flag indicating if the mode is available.
     */
    public boolean calculateUtility(float[] c, LDModeChoiceHouseholdAttributes hh,
            LDTour tour, LDTravelTimeAndCost tc) {

        // calculate tour attributes
        int completeTour = 0;
        if (tour.patternType.equals(LDTourPatternType.COMPLETE_TOUR)) {
            completeTour = 1;
        }

        // set availability
        isAvailable = true;
        if (tc.icRailInVehicleTime[HsrWalk.m]==0) isAvailable = false;
        if (completeTour==1) {
            if (tc.totalTime[HsrWalk.m] > (tour.schedule.duration)) {
                isAvailable = false;
            }
        }

        // scale cost such that all travelers must pay fare
        float cost = tc.cost[HsrWalk.m] * tour.partySize;
        
        // calculate utility
        if (isAvailable) {
        	
            utility = 0;
            utility += c[INVEHICLETIME] * tc.inVehicleTime [HsrWalk.m];
            utility += c[HSRINVEHICLETIME] * tc.inVehicleTime [HsrWalk.m];
            utility += c[WALKTIME] * tc.walkTime      [HsrWalk.m];
            utility += c[WAITTIME] * tc.waitTime [HsrWalk.m];
            utility += c[TERMINALTIME  ] * tc.terminalTime[HsrWalk.m]; 
            utility += c[COSTINC020] * cost * hh.inclow;
            utility += c[COSTINC2060] * cost * hh.incmed;
            utility += c[COSTINC60P] * cost * hh.inchi;
//            utility += c[CONSTHSR_WALK];

            // account for frequency in Oregon
            if (c.length>FREQUENCYCOEFFICIENT) {
            	double frequencyTerm = Math.pow(c[FREQUENCYNUMERATOR] / tc.frequency[HsrWalk.m], c[FREQUENCYPOWER]);
            	utility += c[FREQUENCYCOEFFICIENT] * frequencyTerm; 
            }
            
            hasUtility = true;
        }

        return isAvailable;
    }

    /**
     * Get drive transit utility
     */
    public double getUtility() {
        if (!hasUtility) {
            String msg = "Error: Utility not calculated for " + alternativeName;
            HsrWalk.logger.fatal(msg);
            throw new ModelException(msg);
        }
        return utility;
    }

}
