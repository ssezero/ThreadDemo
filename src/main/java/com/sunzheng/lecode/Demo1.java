package com.sunzheng.lecode;

import java.util.HashMap;

/**
 * @ClassName Demo1
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/5 9:24
 * @Version 1.0
 **/
public class Demo1 {
    public static void main(String[] args) {
            String s="sunzheng";
        System.out.println(s.charAt(2));
        int n = s.indexOf('n');
        System.out.println(n);
    }
    public static int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        boolean chongfu=false;
        for (int i=0;i<chars.length;i++){
            chongfu=false;
           for(int j=0;j<chars.length;j++){
               if(chars[j]==chars[i] && i!=j){
                   chongfu=true;
                   break;
               }
           }
           if(!chongfu){
               return i;
           }
        }
        return -1;
    }


    public int firstUniqChar2(String s) {
        for (int i = 0; i < s.length(); i++)
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i)))
                return i;
        return -1;
    }
}
