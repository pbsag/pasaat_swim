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

import java.io.PrintWriter;

/**  
 * Attributes of a TAZ - only parking cost right now
 * 
 * @author Joel Freedman
 * @version 1.0 12/01/2003
 * 
 */

public class ZoneAttributes{

     public double parkingCost;
    public double terminalTime;


     public void print(PrintWriter file){
        file.println("Zone Attributes:" );
        file.println("\tparkingCost = " + parkingCost);
        file.println();
        file.println();
        
        file.flush();
     }

}
