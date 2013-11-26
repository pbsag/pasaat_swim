/*
 * Copyright  2006 PB Consult Inc.
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
package com.pb.models.pt.ldt;

/**
 * Defines column IDs for parameters for long-distance 
 * tour internal mode choice models.  
 * 
 * @author Erhardt
 * @version 1.0 Apr 5, 2006
 *
 */
public class LDInternalModeChoiceParameters {

    public static final int INVEHICLETIME          = 0;
    public static final int WALKTIME               = 1;
    public static final int DRIVETIME              = 2;
    public static final int WAITTIME               = 3;
    public static final int TERMINALTIME           = 4;
    public static final int HSRINVEHICLETIME       = 5;
    public static final int COSTINC020             = 6;
    public static final int COSTINC2060            = 7;
    public static final int COSTINC60P             = 8;
    public static final int CONSTAIR               = 9;
    public static final int CONSTTRANSIT_WALK      = 10;
    public static final int CONSTTRANSIT_DRIVE     = 11;
    public static final int CONSTHSR_WALK          = 12;
    public static final int CONSTHSR_DRIVE         = 13;
    public static final int NESTGROUND             = 14;
    public static final int NESTTRANSIT            = 15;
    public static final int NESTHSR                = 16;
    
    // used in Oregon only
    public static final int FREQUENCYCOEFFICIENT   = 17;
    public static final int FREQUENCYNUMERATOR     = 18;
    public static final int FREQUENCYPOWER         = 19; 
}
