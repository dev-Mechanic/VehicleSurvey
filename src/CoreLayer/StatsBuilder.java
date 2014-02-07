/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CoreLayer;

import Entity.StatsSet;
import Entity.SurveyConstants;
import Entity.VehicleRecord;
import static Utility.DebugFuncs.M2C;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Himanshu
 */
public class StatsBuilder {
    

    
    public static StatsSet Builder(ArrayList<VehicleRecord> list)
    {
        Calendar timeStampHolder = Calendar.getInstance();
        double estSpeedHolder = 0;
        boolean flagA=false,flagB=false;
        StatsSet set = new StatsSet();
        
        set.CountDirA = 0;
        set.CountDirB = 0;
        set.AvgSpeedDirA = 0;
        set.AvgSpeedDirB = 0;
        set.AvgDistanceBtwCars = 0;
        set.AvgDistanceBtwCarsDirA = 0;
        set.AvgDistanceBtwCarsDirB = 0;
        set.CountInfringementsDirA = 0;
        set.CountInfringementsDirB = 0;
        
        if(list.size()>0)
        {
            
            if(list.get(0).direction() == SurveyConstants.GroupDirectionA)
            {
                set.MaxEstSpeedA = list.get(0).getEstSpeed();
                set.MinEstSpeedA = list.get(0).getEstSpeed();
                flagA = true;
            }
            else if(list.get(0).direction() == SurveyConstants.GroupDirectionB)
            {
                set.MaxEstSpeedB = list.get(0).getEstSpeed();
                set.MinEstSpeedB = list.get(0).getEstSpeed();
                flagB = true;
            }


            for(VehicleRecord vr : list)
            {
                if(vr.direction() == SurveyConstants.GroupDirectionA)
                {
                    set.CountDirA++;
                    set.AvgSpeedDirA += vr.getEstSpeed();
                    if(vr.getEstSpeed()> SurveyConstants.ExpectedSpeed)
                    {
                        set.CountInfringementsDirA++;
                    }
                    
                    
                    if(flagA)
                    {
                        if(vr.getEstSpeed() > set.MaxEstSpeedA)
                        {
                            set.MaxEstSpeedA = vr.getEstSpeed();
                        }
                        
                        if(vr.getEstSpeed() < set.MinEstSpeedA)
                        {
                            set.MinEstSpeedA = vr.getEstSpeed();
                        }
                    }
                    else
                    {
                        set.MaxEstSpeedA = list.get(0).getEstSpeed();
                        set.MinEstSpeedA = list.get(0).getEstSpeed();
                        flagA = true;
                    }
                    
                }
                else if(vr.direction() == SurveyConstants.GroupDirectionB)
                {
                    set.CountDirB++;
                    set.AvgSpeedDirB += vr.getEstSpeed();
                    
                    if(vr.getEstSpeed()> SurveyConstants.ExpectedSpeed)
                    {
                        set.CountInfringementsDirB++;
                    }
                    
                    
                    if(flagB)
                    {
                        if(vr.getEstSpeed() > set.MaxEstSpeedB)
                        {
                            set.MaxEstSpeedB = vr.getEstSpeed();
                        }
                        
                        if(vr.getEstSpeed() < set.MinEstSpeedB)
                        {
                            set.MinEstSpeedB = vr.getEstSpeed();
                        }
                    }
                    else
                    {
                        set.MaxEstSpeedB = list.get(0).getEstSpeed();
                        set.MinEstSpeedB = list.get(0).getEstSpeed();
                        flagB = true;
                    }
                    
                    
                }

                

                if(list.indexOf(vr)>0)
                {
                    //M2C("Dist : " + vr.SetDistanceFromLastCar(timeStampHolder,estSpeedHolder));
                     
                    if(vr.direction() == SurveyConstants.GroupDirectionA)
                    {
                        set.AvgDistanceBtwCarsDirA += vr.SetDistanceFromLastCar(timeStampHolder,estSpeedHolder);
                    }
                    else if(vr.direction() == SurveyConstants.GroupDirectionB)
                    {
                        set.AvgDistanceBtwCarsDirB += vr.SetDistanceFromLastCar(timeStampHolder,estSpeedHolder);
                    }
                    
                    timeStampHolder = vr.getTimeStamp();
                    estSpeedHolder = vr.getEstSpeed();
                   
                }
                else
                {
                    timeStampHolder = vr.getTimeStamp();
                    estSpeedHolder = vr.getEstSpeed();
                }

            }

            //M2C("Total In Direction A:" + set.CountDirA );
            //M2C("Total In Direction B:" + set.CountDirB );
            //M2C("Max Speed:" + set.MaxEstSpeed );
            //M2C("Min Speed:" + set.MinEstSpeed );
            //M2C("Avg Speed :" + ((set.AvgSpeedDirA+set.AvgSpeedDirB)/(set.CountDirA+set.CountDirB) ));
            set.CountCars = set.CountDirA + set.CountDirB;
            set.AvgSpeed = ((set.AvgSpeedDirA+set.AvgSpeedDirB)/(set.CountCars) );

            
            //M2C("Avg Speed (A):" + set.AvgSpeedDirA/set.CountDirA );
            set.AvgSpeedDirA = set.AvgSpeedDirA/set.CountDirA;
            
            //M2C("Avg Speed (B):" + set.AvgSpeedDirB/set.CountDirB );
            set.AvgSpeedDirB = set.AvgSpeedDirB/set.CountDirB;
            
            set.AvgDistanceBtwCars = (set.AvgDistanceBtwCarsDirA+set.AvgDistanceBtwCarsDirB)/Double.parseDouble(String.valueOf(set.CountCars));
            set.AvgDistanceBtwCarsDirA = set.AvgDistanceBtwCarsDirA/Double.parseDouble(String.valueOf(set.CountDirA));
            set.AvgDistanceBtwCarsDirB = set.AvgDistanceBtwCarsDirB/Double.parseDouble(String.valueOf(set.CountDirB));
            
            
            
            
            return set;
        }
        else
        {
            M2C("Empty List Detected! Nothing to build statistics on...");
            return null;
        }
    }
    
    public static ArrayList<StatsSet> GroupStats(ArrayList<VehicleRecord> list,Calendar startAt,int groupMinutes)
    {
        ArrayList<StatsSet> stats = new ArrayList<StatsSet>();
        ArrayList<VehicleRecord> workSet = new ArrayList<VehicleRecord>();
        int groupCount=1;
        StatsSet holder;
        Calendar grpFrom = Calendar.getInstance(),grpTo = Calendar.getInstance();
        grpFrom.set(startAt.get(Calendar.YEAR), startAt.get(Calendar.MONTH), startAt.get(Calendar.DAY_OF_MONTH), startAt.get(Calendar.HOUR_OF_DAY), startAt.get(Calendar.MINUTE), startAt.get(Calendar.SECOND));
        grpTo.set(startAt.get(Calendar.YEAR), startAt.get(Calendar.MONTH), startAt.get(Calendar.DAY_OF_MONTH), startAt.get(Calendar.HOUR_OF_DAY), startAt.get(Calendar.MINUTE), startAt.get(Calendar.SECOND));


        for(VehicleRecord vr : list)
        {
            if((vr.getTimeStamp().getTimeInMillis()- startAt.getTimeInMillis()) > groupMinutes*groupCount*SurveyConstants.MinuteToSec*SurveyConstants.SecondToMSEC)
            {
                holder = Builder(workSet);

                grpTo.add(Calendar.MINUTE, groupMinutes);
                //M2C(grpFrom.getTime().toString() + " To " + grpTo.getTime().toString() + "----- GC " + groupCount);
                holder.GroupTag = grpFrom.getTime().toString() + " To " + grpTo.getTime().toString();
                stats.add(holder);
                groupCount++;
                workSet.clear();
                grpFrom.add(Calendar.MINUTE, groupMinutes);

            }
            
            workSet.add(vr);
        }
        //M2C("Remaining Set : " + workSet.size());
        holder = Builder(workSet);
        grpTo.add(Calendar.MINUTE, groupMinutes);
        //M2C(grpFrom.getTime().toString() + " To " + grpTo.getTime().toString() + "-----" + startAt.getTime().toString() + " GC " + groupCount);
        holder.GroupTag = grpFrom.getTime().toString() + " To " + grpTo.getTime().toString();
        stats.add(holder);
        groupCount++;
        workSet.clear();
        grpFrom.add(Calendar.MINUTE, groupMinutes);
        
        return stats;
    }
    
}



