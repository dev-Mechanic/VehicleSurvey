/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CoreLayer;

import Entity.VehicleRecord;
import static Utility.DebugFuncs.M2C;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Himanshu
 */
public class SurveyParser {
    
    public static ArrayList<VehicleRecord> parseSurvey(ArrayList<String> survey)
    {
        if(survey.size()>0)
        {
            ArrayList<String> parseSet = new ArrayList<String>();
            ArrayList<VehicleRecord> vehicleSet = new ArrayList<VehicleRecord>();
            int msAtT,msAtTp1;
            int dayCount=0;
            
            if(survey.size()>1)
            {
                msAtT = Integer.parseInt(survey.get(0).substring(1));
                
                for(int i=0;i<survey.size();i++)
                {
                    parseSet.clear();
                    msAtTp1 = Integer.parseInt(survey.get(i).substring(1));

                    //M2C(survey.get(i) + " : " + survey.get(i+1) + " :\t" + msAtT + " : " + msAtTp1);
                    
                    if ( msAtTp1 < msAtT)
                    {
                        M2C("InParse : Day Change : " + survey.get(i) + " :" + msAtT + " : " + msAtTp1);
                        dayCount++;
                        
                    }
                    
                    if(survey.get(i).startsWith(survey.get(i+1).substring(0, 1)))
                    {
                        //Case 1 with front and rear in direction A
                        //Need two in a set
                        parseSet.add(survey.get(i));
                        parseSet.add(survey.get(i+1));
                        //M2C("DirASet");
                        //0,1 considered
                        //do not consider 1,2; consider 2 onwards
                       
                        msAtT = Integer.parseInt(survey.get(i+1).substring(1));
                        i++;
                    }
                    else
                    {
                        //Case 2 with front going through A/B in direction B
                        // Need four in a set
                        parseSet.add(survey.get(i));
                        parseSet.add(survey.get(i+1));
                        parseSet.add(survey.get(i+2));
                        parseSet.add(survey.get(i+3));
                        //M2C("DirBSet" + parseSet.get(3));
                        //0,1,2,3
                        msAtT = Integer.parseInt(survey.get(i+3).substring(1));
                        i += 3;
                    }
                    
                    vehicleSet.add(new VehicleRecord(parseSet,dayCount));
                    
                    
                }
            }
        return vehicleSet;
        }
        
        return null;
    }
    
    
    
    
    
}
