package com.stt.java.base.collection;

import java.util.HashMap;

/**
 * Created by Administrator on 2017-02-21.
 *
 * @author shitongtong
 */
public class TestMain {

    public static void main(String[] args) {

//        网传java7的下面两条新特性是假的假的！！
//        List<Integer> piDigits = [ 1,2,3,4,5,8 ];
//        Map map = {name:"xxx",age:18};

        HashMap<String, Object> hashMap = new HashMap<>();
        Object put = hashMap.put("ss", "ss");
        System.out.println(put);
        Object o = hashMap.putIfAbsent("ss", "ss");
        Object o1 = hashMap.putIfAbsent("ss", "sss");
        Object o2 = hashMap.putIfAbsent("s", "ss");
        System.out.println(o);
        System.out.println(o1);
        System.out.println(o2);

//        BufferedReader
    }

}
