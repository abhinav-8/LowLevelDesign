package org.example.lld.HashMap;

public class Main {
    static void main(String[] args) {
        CustomHashMap<String, String> map = new CustomHashMap<String, String>();
        map.showMap();
        map.put("0", "abhi");
        map.showMap();
        map.put("11", "lol");
        map.showMap();
        map.put("1", "yes is a two");
        map.showMap();
        map.put("14", "aa gaya range me");
        map.showMap();
        map.put("15", "siiiu");
        map.showMap();
        map.put("16", "siiiu");
        map.showMap();
        map.put("17", "bobzyy king");
        map.showMap();
        map.put("17", "Updated-Bobzyy king");
        map.showMap();
        map.remove("11");
        map.remove("14");
        map.remove("15");
        map.remove("16");
        map.showMap();

    }
}
