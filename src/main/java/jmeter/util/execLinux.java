package jmeter.util;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class execLinux {
    //http://blog.csdn.net/xh16319/article/details/17302947
    public static Object exec(String cmd) {  
        try {  
            String[] cmdA = { "/bin/sh", "-c", cmd };  
            Process process = Runtime.getRuntime().exec(cmdA);  
            LineNumberReader br = new LineNumberReader(new InputStreamReader(  
                    process.getInputStream()));  
            String result = "";
            String line;  
         while ((line = br.readLine()) != null) {  
//                 System.out.println(line); 
                 if(parseReport.isSummary(line))//判断是不是Summary
                     result = line;
            }  
            return result;  
        } catch (Exception e) {  
            System.out.println("脚本错误！"); 
        }  
        return null;  
    }
}
