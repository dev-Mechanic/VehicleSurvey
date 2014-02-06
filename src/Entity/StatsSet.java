/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import static Utility.DebugFuncs.M2C;

/**
 *
 * @author Himanshu
 */
public class StatsSet
{
    public double MaxEstSpeed;
    public double MinEstSpeed;
    public int CountDirA;
    public int CountDirB;
    public double AvgSpeedDirA;
    public double AvgSpeedDirB;
    public double AvgDistanceBtwCarsDirA;
    public double AvgDistanceBtwCarsDirB;
    public double AvgDistanceBtwCars;
    public int CountCars;
    public double AvgSpeed;
    
    public String GroupTag;
    
    
    
    public void print()
    {
        M2C("Group : " + GroupTag + "\t\tAvgSpeed = " + Math.round(AvgSpeed) + "\tMaxSpeed = " + Math.round(MaxEstSpeed) + "\tMinSpeed = " + Math.round(MinEstSpeed) + "\tAvg Speed in Dir A = " + Math.round(AvgSpeedDirA) + "\tAvg Speed in Dir B = " + Math.round(AvgSpeedDirB) + "\tCount in Dir A = " + CountDirA + "\tCount in Dir B = " + CountDirB + "\tAvg Dist Between Cars in Dir A = " + AvgDistanceBtwCarsDirA + "\tAvg Dist Between Cars in Dir B = " + AvgDistanceBtwCarsDirB + "\tAvg Dist Between Cars = " + AvgDistanceBtwCars + "\tTotal Count of Cars = " + CountCars );
    }
}