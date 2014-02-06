/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DoNotInclude;

import CoreLayer.StatsBuilder;
import CoreLayer.SurveyParser;
import Entity.StatsSet;
import Entity.VehicleRecord;
import static Utility.DebugFuncs.M2C;
import Utility.FileReaderUtility;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Himanshu
 */
public class DraftTester {
    
    public static void main(String [] args)
    {
       FileReaderUtility fr = new FileReaderUtility("C:\\Users\\Himanshu\\Documents\\Projects\\VehicleSurvey\\input stats.txt");
       ArrayList<String> contents = fr.ReadAll();
       int msAtT,msAtTp1;
       msAtT = Integer.parseInt(contents.get(0).substring(1));
       
       for(String line : contents)
       {
           msAtTp1 = Integer.parseInt(line.substring(1));
           if ( msAtTp1 < msAtT)
           {
               M2C("Day Change : " + line);
               
           }
           msAtT = msAtTp1;
       }
       
       M2C("Last MS : " + msAtT);
       M2C("File Length : " + contents.size());
       
       //M2C("Vehicle Count : " + SurveyParser.parseSurvey(contents).size());
       
       Calendar test = Calendar.getInstance();
       //test.set(test.get(Calendar.YEAR), test.get(Calendar.MONTH), test.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
       //test.add(Calendar.MILLISECOND, 1000000);
       test.set(test.get(Calendar.YEAR), test.get(Calendar.MONTH), test.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
//       M2C(":" + test.get(Calendar.HOUR_OF_DAY));
//       M2C(":" + test.get(Calendar.AM_PM));
//       for(VehicleRecord vr : SurveyParser.parseSurvey(contents))
//       {
//           vr.print();
//       }
       
       ArrayList<StatsSet> stats = StatsBuilder.GroupStats(SurveyParser.parseSurvey(contents),test,60);
       M2C("Size : " + stats.size());
       
       int MaxCount;
       String MaxGrp;
       MaxCount = stats.get(0).CountCars;
       MaxGrp = stats.get(0).GroupTag;
       
       for(StatsSet st : stats)
       {
           st.print();
           if(st.CountCars > MaxCount)
           {
               MaxCount = st.CountCars;
               MaxGrp = st.GroupTag;
           }
       }
       
       M2C(" Peak Time : " + MaxGrp + " with a count of " + MaxCount);
    }
    
}
