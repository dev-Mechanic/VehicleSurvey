/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Himanshu
 */
public class FileReaderUtility {
    
    // Class Variables
    private String FileName;
    private String FileAddress;
    private ArrayList<String> FileStore;
    
    
    //Accessors
    public String getFileName()
    {
        return FileName;
    }
    
    private String getFileAddress()
    {
        return FileAddress;
    }
    
    //Mutators
    private void setFileName(String fname)
    {
        this.FileName = fname;
    }
    
    private void setFileAddress(String fAddress)
    {
        this.FileAddress = fAddress;
    }
    
    // Class Functions
    
    // Default Constr
    public FileReaderUtility(String fAddress)
    {
        this.setFileAddress(fAddress);        
    }
    
    // Function to return all lines in File
    public ArrayList<String> ReadAll()
    {
        try
        {
            String readLine;
            File readFile = new File(this.FileAddress);
            BufferedReader brFile = new BufferedReader(new FileReader(readFile));
            
            this.setFileName(readFile.getName());
            this.FileStore = new ArrayList();
            while ((readLine = brFile.readLine()) != null) {
                this.FileStore.add(readLine);
            }
        }
        catch(Exception ex)
        {
            //Exception Handling
        }
        
        return null;
    }
}
