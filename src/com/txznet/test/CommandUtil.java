package com.txznet.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by uixli on 2018/5/21.
 */

public class CommandUtil {

    private static String TAG ="CommandUtil";

    public static void REGSISTER_CMD() throws IOException {
//        InputStream inputStream = App.getInstance().getAssets().open("cmd.bnf");
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        String line = "";
//        while (!(line = bufferedReader.readLine()).equals("#end")){
//            if (line.startsWith("#")){
//                continue;
//            }
//
//        }
    }

    public static void main(String arg[]){
        String line = "open_air_quality_display=(PM2.5|空气质量)(信息显示)^(显示)(PM2.5|空气质量)(信息)";
        CommandUtil commandUtil = new CommandUtil();
        commandUtil.pareCMD(line);

    }

    public Map pareCMD(String line){
        Map map = new HashMap();
        String key = line.split("=")[0];
        String value = line.split("=")[1];
        Set<String> set = new HashSet<>();
        if (value.contains("^")){
           String values[] = value.split("\\^");
           for (String v :values){
               BNFParse bnfParse = new BNFParse();
               Set<String> s = (Set<String>) bnfParse.parseCmd(v);
               set.addAll(s);
           }
        }else {
            BNFParse bnfParse = new BNFParse();
            set = (Set<String>) bnfParse.parseCmd(value);
        }

        System.out.println("1111111");
        map.put(key,set);
        return map;
    }




}
