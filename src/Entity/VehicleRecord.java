/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import static Utility.DebugFuncs.M2C;
import java.util.ArrayList;
import java.util.Calendar;


/**
 *
 * @author Himanshu
 */
public class VehicleRecord {
    
    ArrayList<String> sensorTimeStamp;
    
    Calendar timeOfDay;
    double estSpeed;
    int direction;
    double distanceFromLastCar;
    
    public VehicleRecord(ArrayList<String> sensTS,int dayRef)
    {
        sensorTimeStamp = sensTS;
        timeOfDay  = Calendar.getInstance();
        timeOfDay.set(timeOfDay.get(Calendar.YEAR), timeOfDay.get(Calendar.MONTH), timeOfDay.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        timeOfDay.add(Calendar.MILLISECOND, Integer.parseInt(sensTS.get(0).substring(1)));
        timeOfDay.add(Calendar.DAY_OF_MONTH, dayRef);
        
        if(sensTS.size() == SurveyConstants.GroupDirectionA)
        {
          int timeSpan = Integer.parseInt(sensTS.get(1).substring(1)) - Integer.parseInt(sensTS.get(0).substring(1));    
          estSpeed = SurveyConstants.DistanceBWAxles*SurveyConstants.SecondToHourConversion/timeSpan;  // m/msec same as KM/sec --> KM*3600/hr
          direction = SurveyConstants.GroupDirectionA;
          
        }
        else if(sensTS.size() == SurveyConstants.GroupDirectionB)
        {
          int timeSpan = Integer.parseInt(sensTS.get(2).substring(1)) - Integer.parseInt(sensTS.get(0).substring(1));    
          int timeSpanRear = Integer.parseInt(sensTS.get(3).substring(1)) - Integer.parseInt(sensTS.get(1).substring(1));
          
          estSpeed = SurveyConstants.DistanceBWAxles*SurveyConstants.SecondToHourConversion/timeSpan;  // m/msec same as KM/sec --> KM*3600/hr
          estSpeed += SurveyConstants.DistanceBWAxles*SurveyConstants.SecondToHourConversion/timeSpanRear;
          estSpeed = estSpeed/2; // Average of two estimates [ front and rear axles]
          direction = SurveyConstants.GroupDirectionB;
        }
    }
    
    public void print()
    {
        M2C(timeOfDay.get(Calendar.DAY_OF_MONTH) + "-" + (timeOfDay.get(Calendar.MONTH)+1) + "-" + timeOfDay.get(Calendar.YEAR) + "\t" + timeOfDay.get(Calendar.HOUR_OF_DAY) + ":" + timeOfDay.get(Calendar.MINUTE) + ":" + timeOfDay.get(Calendar.SECOND) + "\t" + estSpeed + "\t" + direction);
    }
    
    public Calendar getTimeStamp()
    {
        return timeOfDay;
    }
    
    public double getEstSpeed()
    {
        return estSpeed;
    }
    
    public int direction()
    {
        return direction;
    }

    public double SetDistanceFromLastCar(Calendar timeStamp,double estSpeed) {
        distanceFromLastCar = ((timeOfDay.getTimeInMillis() - timeStamp.getTimeInMillis())*estSpeed)/((SurveyConstants.SecondToMSEC*SurveyConstants.SecondToHourConversion));
        //M2C("Time : " + timeOfDay.getTime().toString() + " - " + timeStamp.getTime().toString() + ":" +  this.getEstSpeed() + " : " + (timeOfDay.getTimeInMillis() - timeStamp.getTimeInMillis()));
        return distanceFromLastCar;
    }
    
    
}
