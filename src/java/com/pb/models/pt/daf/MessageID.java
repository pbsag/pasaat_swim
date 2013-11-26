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
package com.pb.models.pt.daf;

/**
 * List of MessageIDs used in PTDaf
 * 
 * @author hansens
 * 
 * 
 */
public final class MessageID {

    public static final String SKIMS_READ = "Skims are Read";
    public static final String WORKER_CHECKING_IN = "Ready to Work";
    public static final String CALCULATE_AUTO_OWNERSHIP = "Calculate Auto Ownership";
    public static final String AUTO_OWNERSHIP_CALCULATED = "Auto Ownership Calculated";
    public static final String SEND_MORE_HHS = "Send More Households";

    // Messages sent from IntializerTask
    public static final String NODE_INITIALIZED = "Node Initialized";

    // Messages sent from PTDafMaster
    public static final String CREATE_MC_LOGSUMS = "CreateMCLogsums";


    public static final String CREATE_DC_LOGSUMS = "CreateDCLogsums";

    // public static final String CREATE_LABOR_FLOWS = "CreateLaborFlows";
    public static final String CALCULATE_WORKPLACE_LOCATIONS = "CalculateWorkplaceLocations";

    public static final String PROCESS_HOUSEHOLDS = "ProcessHouseholds";
    
    public static final String PROCESS_VISITOR = "ProcessVisitors";

    public static final String LOAD_DC_LOGSUMS = "LoadDCLogsums";

    public static final String UPDATE_WORKPLACES = "UpdateWorkPlaces";

    public static final String DC_LOGSUMS_WRITTEN = "DCLogsumsWritten";

    public static final String UPDATE_DC_LOGSUMS = "UpdateDCLogsums";

    // Messages sent from HouseholdWorker

    public static final String SKIMS = "Skims";

    public static final String MC_LOGSUMS_CREATED = "MCLogsumsCreated";

    public static final String MC_LOGSUMS_COLLAPSED = "MCLogsumsCollapsed";

    public static final String DC_LOGSUMS_CREATED = "DCLogsumsCreated";

    public static final String DC_EXPUTILITIES_CREATED = "DCExpUtilitiesCreated";

    // public static final String LABOR_FLOWS_CREATED = "LaborFlowsCreated";
    public static final String WORKPLACE_LOCATIONS_CALCULATED = "WorkplaceLocationsCalculated";

    public static final String HOUSEHOLDS_PROCESSED = "HouseholdsProcessed";
    
    public static final String VISITOR_HHS_PROCESSED = "VisitorsHHProcessed";

    public static final String UPDATE_TAZDATA = "UpdateTazData";

    public static final String TAZDATA_UPDATED = "TazDataUpdated";

    public static final String NODE_SETUP_DONE = "NodeSetupDone";

    public static final String NUM_OF_HHS = "NumOfHHs";

    public static final String NUM_OF_PERSONS = "NumOfPersons";

    public static final String NUM_OF_WORK_QUEUES = "NumOfWorkQueues";

    public static final String ALL_HOUSEHOLDS_PROCESSED = "AllHHsDone";

    public static final String MODEL_TIMINGS = "ModelTimings";

    public static final String ALL_FILES_WRITTEN = "AllFilesWritten";
    
    public static final String LDT_HOUSEHOLDS = "LDT Households";
    
    public static final String LDTTOURS_PROCESSED = "LDT Tours Processed"; 
}
