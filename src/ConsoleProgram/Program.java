/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ConsoleProgram;

import CoreLayer.StatsBuilder;
import CoreLayer.SurveyParser;
import Entity.StatsSet;
import Entity.VehicleRecord;
import static Utility.DebugFuncs.*;
import Utility.FileReaderUtility;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Himanshu
 */
public class Program {
    
    
    public String Run(String fileAddress,Calendar startFrom,int GroupByMinutes)
    {
           FileReaderUtility fr = new FileReaderUtility(fileAddress);
           ArrayList<String> contents = fr.ReadAll();
           ArrayList<StatsSet> stats;
           
           if (contents != null)
           {
               Calendar startAt = (startFrom == null ? Calendar.getInstance(): startFrom);
               startAt.set(startAt.get(Calendar.YEAR), startAt.get(Calendar.MONTH), startAt.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

               if(GroupByMinutes > 0)
               {
                    stats = StatsBuilder.GroupStats(SurveyParser.parseSurvey(contents,startAt),startAt,GroupByMinutes);
               }
               else
               {
                   stats = new ArrayList<StatsSet>();
                   stats.add(StatsBuilder.Builder(SurveyParser.parseSurvey(contents,startAt)));
               }
               //M2C("Size : " + stats.size());

               int MaxCount;
               int MaxInfA,MaxInfB,MaxInf;
               double MaxSpeedInA,MaxSpeedInB,MinSpeedInA,MinSpeedInB;
               String MaxGrp,MaxInfGrp,MaxInfAGrp,MaxInfBGrp,MaxSpeedInAGrp,MinSpeedInAGrp,MaxSpeedInBGrp,MinSpeedInBGrp;

               if(stats.size() > 0 )
               {
                   MaxCount = stats.get(0).CountCars;
                   MaxGrp = stats.get(0).GroupTag;
                   MaxInfA = stats.get(0).CountInfringementsDirA;
                   MaxInfB = stats.get(0).CountInfringementsDirA;

                   MaxSpeedInA = stats.get(0).MaxEstSpeedA;
                   MaxSpeedInB = stats.get(0).MaxEstSpeedB;

                   MinSpeedInA = stats.get(0).MinEstSpeedA;
                   MinSpeedInB = stats.get(0).MinEstSpeedB;

                   MaxInf = MaxInfA + MaxInfB;

                   MaxInfGrp = MaxGrp;
                   MaxInfAGrp = MaxGrp;
                   MaxInfBGrp = MaxGrp;
                   MaxSpeedInAGrp = MaxGrp;
                   MinSpeedInAGrp = MaxGrp;
                   MaxSpeedInBGrp = MaxGrp;
                   MinSpeedInBGrp = MaxGrp;


                   for(StatsSet st : stats)
                   {
                       st.print();
                       if(st.CountCars > MaxCount)
                       {
                           MaxCount = st.CountCars;
                           MaxGrp = st.GroupTag;
                       }

                       if(st.CountInfringementsDirA > MaxInfA)
                       {
                           MaxInfA = st.CountInfringementsDirA;
                           MaxInfAGrp = st.GroupTag;
                       }

                       if(st.CountInfringementsDirB > MaxInfB)
                       {
                           MaxInfB = st.CountInfringementsDirB;
                           MaxInfBGrp = st.GroupTag; 
                       }

                       if((st.CountInfringementsDirA+st.CountInfringementsDirB) > MaxInf)
                       {
                           MaxInf = st.CountInfringementsDirA+st.CountInfringementsDirB;
                           MaxInfGrp = st.GroupTag; 
                       }

                       if(st.MaxEstSpeedA > MaxSpeedInA)
                       {
                           MaxSpeedInA = st.MaxEstSpeedA;
                           MaxSpeedInAGrp = st.GroupTag;
                       }

                       if(st.MinEstSpeedA < MinSpeedInA)
                       {
                           MinSpeedInA = st.MinEstSpeedA;
                           MinSpeedInAGrp = st.GroupTag;
                       }

                       if(st.MaxEstSpeedB > MaxSpeedInB)
                       {
                           MaxSpeedInB = st.MaxEstSpeedB;
                           MaxSpeedInBGrp = st.GroupTag;
                       }

                       if(st.MinEstSpeedB < MinSpeedInB)
                       {
                           MinSpeedInB = st.MinEstSpeedB;
                           MinSpeedInBGrp = st.GroupTag;
                       }


                   }

                   MaxSpeedInA = Math.round(MaxSpeedInA);
                   MaxSpeedInB = Math.round(MaxSpeedInB);

                   MinSpeedInA = Math.round(MinSpeedInA);
                   MinSpeedInB = Math.round(MinSpeedInB);


                   if(GroupByMinutes > 0)
                   {
                        M2C(" Peak Time : " + MaxGrp + " with a count of " + MaxCount);
                        M2S(" Speed Stats  : Max OverAll " + (MaxSpeedInA > MaxSpeedInB ? MaxSpeedInA : MaxSpeedInB ));
                                M2S(" ( Slot : " + (MaxSpeedInA > MaxSpeedInB ? MaxSpeedInAGrp : MaxSpeedInBGrp ) +")"); 
                                M2S(", Min OverAll " + (MinSpeedInA < MinSpeedInB ? MinSpeedInA : MinSpeedInB ));
                                M2S(" ( Slot : " + (MaxSpeedInA > MaxSpeedInB ? MaxSpeedInAGrp : MaxSpeedInBGrp ) +")");
                                M2S(" Max In Dir A " + MaxSpeedInA); 
                                M2S(" ( Slot : " + MaxSpeedInAGrp +")");
                                M2S(", Min In Dir A " + MinSpeedInA);
                                M2S(" ( Slot : " + MinSpeedInAGrp +")");                            
                                M2S(" Max In Dir B " + MaxSpeedInB); 
                                M2S(" ( Slot : " + MaxSpeedInBGrp +")");
                                M2S(", Min In Dir B " + MinSpeedInB);
                                M2S(" ( Slot : " + MinSpeedInBGrp +")\n");

                                M2C(" Max Total Infringements in Dir A between " + MaxInfAGrp + " . Total of  " + MaxInfA + " violations. ");
                        M2C(" Max Total Infringements in Dir B between " + MaxInfBGrp + " . Total of  " + MaxInfB + " violations. ");
                        M2C(" Max Total Infringements between " + MaxInfGrp + " . Total of  " + MaxInf + " violations. ");
                   }
                   else
                   {
                        M2C(" Total Count of Cars " + MaxCount);
                        M2S(" Speed Stats  : Max OverAll " + (MaxSpeedInA > MaxSpeedInB ? MaxSpeedInA : MaxSpeedInB ));
                        M2S(", Min OverAll " + (MinSpeedInA < MinSpeedInB ? MinSpeedInA : MinSpeedInB ) + "\n");
                        M2C(" Max Total Infringements in Dir A : " + MaxInfA + " violations. ");
                        M2C(" Max Total Infringements in Dir B : " + MaxInfB + " violations. ");
                        M2C(" Max Total Infringements : " + MaxInf + " violations. ");
                   }
                   return "DONE";
               }
           }
           return null;
    }
    
    public static void main(String [] args)
    {
       Program prg = new Program();
       M2C("Args : " + args.length);
       
       if(args == null || args.length == 0 || args.length > 5)
       {
           M2C("Usage Options");
           M2C("The console program takes in either 1,2 or 5 aguments");
           M2C("A.  1 Argument - Demo Mode - Uses defaults to run the program");
           M2C("    1 : Use Default Survey to provide Overall Analysis");
           M2C("    2 : Use Default Survey to provide Daily Analysis");
           M2C("    3 : Use Default Survey to provide Quarterly Analysis");
           M2C("    4 : Use Default Survey to provide Hourly Analysis");
           M2C("    5 : Use Default Survey to provide 30 Min Slot Analysis");
           M2C("    6 : Use Default Survey to provide 20 Min Slot Analysis");
           M2C("    7 : Use Default Survey to provide 15 Min Slot Analysis");
           M2C("For ex. #java ConsoleProgram.Program 1");
           M2C("B.  2 Arguments");
           M2C("    Arg 1 : FileAddress or FileName if present in current dir");
           M2C("    Arg 2 : Group By Interval in Minutes");
           M2C("For ex. #java ConsoleProgram.Program file1.txt 25");
           M2C("C.  2 Arguments");
           M2C("    Arg 1 : FileAddress or FileName if present in current dir");
           M2C("    Arg 2 : Group By Interval in Minutes");
           M2C("    Arg 3 : Starting Base Date - Numeric");
           M2C("    Arg 4 : Starting Base Month- Numeric");
           M2C("    Arg 5 : Starting Base Year - Numeric");
           M2C("For ex. #java ConsoleProgram.Program file1.txt 60 7 2 2014");
       }
       if(args.length == 1)
       {
           Calendar buildDate = Calendar.getInstance();
           
           switch (Integer.parseInt(args[0]))
           {
               case 1 : prg.Run(".//input stats.txt", buildDate, 0); break;
               case 2 : prg.Run(".//input stats.txt", buildDate, 1440); break;
               case 3 : prg.Run(".//input stats.txt", buildDate, 360); break;
               case 4 : prg.Run(".//input stats.txt", buildDate, 60); break;
               case 5 : prg.Run(".//input stats.txt", buildDate, 30); break;
               case 6 : prg.Run(".//input stats.txt", buildDate, 20); break;   
               case 7 : prg.Run(".//input stats.txt", buildDate, 15); break;   
           }
       }
       else
       {
           if(args.length >= 2)
           {
               File fileTest = new File(args[0]);
               if(fileTest.isFile())
               {
                   try
                   {
                        if( Integer.parseInt(args[1]) >= 0)
                        {
                            Calendar buildDate = Calendar.getInstance();
                             
                            if(args.length == 5)
                            {
                             buildDate.set(Calendar.YEAR, Integer.parseInt(args[4]));
                             buildDate.set(Calendar.MONTH, Integer.parseInt(args[3])-1);
                             buildDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(args[2]));
                             M2C(" BASE DATE : " + buildDate.getTime().toString());
                             prg.Run(fileTest.getAbsolutePath(), buildDate, Integer.parseInt(args[1]));
                             
                            }
                            else if(args.length == 2)
                            {
                             prg.Run(fileTest.getAbsolutePath(), buildDate, Integer.parseInt(args[1]));   
                            }
                            else
                            {
                                M2C("Invalid Parameter List Deteced");
                            }
                            
                        }
                        else
                        {
                            M2C("Invalid Group Type");
                        }
                   }
                   catch(NumberFormatException nfe)
                   {
                       M2C(" Invalid Group Type ");
                       
                   }
               }
               else
               {
                   M2C("Invalid File Deteced");
               }
               
           }
           else
           {
               M2C("More Arguments Provided !");
           }
       }
    }
    
    
    
    
    
    
    
    
    
    
    //           int msAtT,msAtTp1;
//           msAtT = Integer.parseInt(contents.get(0).substring(1));
//
//           for(String line : contents)
//           {
//               msAtTp1 = Integer.parseInt(line.substring(1));
//               if ( msAtTp1 < msAtT)
//               {
//                   //M2C("Day Change : " + line);
//
//               }
//               msAtT = msAtTp1;
//           }

           //M2C("Last MS : " + msAtT);
           //M2C("File Length : " + contents.size());

           //M2C("Vehicle Count : " + SurveyParser.parseSurvey(contents).size());

           
           //test.set(test.get(Calendar.YEAR), test.get(Calendar.MONTH), test.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
           //test.add(Calendar.MILLISECOND, 1000000);
           
    //       M2C(":" + test.get(Calendar.HOUR_OF_DAY));
    //       M2C(":" + test.get(Calendar.AM_PM));
    //       for(VehicleRecord vr : SurveyParser.parseSurvey(contents))
    //       {
    //           vr.print();
    //       }
}
