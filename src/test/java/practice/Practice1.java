package practice;

import java.util.*;

public class Practice1 {

    public static void main(String[] args) {

        String str = "cCcodefish";

        //Count each letter with map
        //c=2, o=1, e=2
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {

           if (!map.containsKey(str.charAt(i))){

               //c already exists
               map.put(str.charAt(i),1);
           }else {

               map.put(str.charAt(i), map.get(str.charAt(i))+1);
           }

        }
        //System.out.println(map);
       // System.out.println(map.get('c'));
        Integer numberForC = map.get('c');
        System.out.println(numberForC);

        //Count each element from an array of String and put inside Map interface
        String [] names = {"Ular", "Kuba", "Petko", "Meryem", "Viktor", "Viktor", "John", "John",};

        Map<String, Integer> map1 = new HashMap<>();

        for (int i = 0; i < names.length; i++) {

            if (!map1.containsKey(names[i])){

                map1.put(names[i], 1);
            }else {

                map1.put(names[i], map1.get(names[i])+1);
            }

        }
        System.out.println(map1);

        List<String> allNames = new ArrayList<>();

        for (int i = 0; i < map1.size(); i++) {

            for (Map.Entry<String, Integer> m:map1.entrySet()){ //enhance for loop

                allNames.add(m.getKey());
            }
            System.out.println(allNames);


        }

    }

}
