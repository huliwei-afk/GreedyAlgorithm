package com.example.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyTest {
    public static void main(String[] args) {
        //创建广播电台，放入HashMap
        HashMap<String,HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //将各个电台放入broadcasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1",hashSet1);
        broadcasts.put("K2",hashSet2);
        broadcasts.put("K3",hashSet3);
        broadcasts.put("K4",hashSet4);
        broadcasts.put("K5",hashSet5);

        //allArea 放入地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.addAll(hashSet1);
        allAreas.addAll(hashSet2);
        allAreas.addAll(hashSet3);
        allAreas.addAll(hashSet4);
        allAreas.addAll(hashSet5);

        //创建ArrayList存放选择的电台集合
        ArrayList<String> selects = new ArrayList<String>();

        //定义一个临时的集合。在遍历过程中，存放电台覆盖地区和未覆盖地区的交集
        HashSet<String> tempSet = new HashSet<>();

        //定义一个maxKey，保存在依次遍历过程中能覆盖最多未覆盖地区对应电台的key
        //如果maxKey不为空，则加入到selects里
        String maxKey = null;
        while (allAreas.size() != 0){ //如果不为0，则表示还没有覆盖到所有地区
            //没进行一次while需要置空
            maxKey = null;

            //遍历broadcasts，取出对应的key
            for(String key : broadcasts.keySet()) {
                //每进行一次for，就要清空
                tempSet.clear();

                //当前key能覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet和allAreas集合的交集,交集会赋给tempSet
                tempSet.retainAll(allAreas);
                //如果当前集合包含的未覆盖地区的数量，比如maxKey指向的集合未覆盖地区还要多
                //就需要重置maxKey。贪心算法就在这
                if(tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            //不等于空，就加入selects集合
            if(maxKey != null){
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区从allAreas中清除
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("选择的结果是" + selects);


    }
}