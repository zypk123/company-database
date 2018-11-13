package com.huntkey.rx.purchase.common.model.puquoteorder;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.edm.constant.PuqoPuqoPriceSetaProperty;
import com.huntkey.rx.edm.entity.PuquoteorderEntity;
import org.junit.Test;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.*;

public class PuquoteOrderDTOTest {

    @Test
    public void test2() {
        System.out.println(new DecimalFormat(",###").format(123456789.123456) );
        System.out.println(new DecimalFormat(",###.#######").format(123456789.12) );
        System.out.println(new DecimalFormat(",###.#######").format(123456789.12002) );
        System.out.println(new DecimalFormat(",###.#######").format(123456789.1200000000000) );
        System.out.println(new DecimalFormat(",###.#######").format(123456789.123456789) );
    }


    @Test
    public void test() {
        PuquoteOrderDTO dto = new PuquoteOrderDTO();
        dto.setPuqoSnameSupp("供应商");
        List<PuqoPriceSetDTO> list = new ArrayList<>();
        PuqoPriceSetDTO setDTO = new PuqoPriceSetDTO();
        setDTO.setPuqoDateEnd(new Date());
        list.add(setDTO);
        dto.setPuqoPriceSet(list);
        System.out.println(JSON.toJSONString(dto, true));

//        @RestController
        /**
         * {
         "id": null,
         "ordeRodeObj": null,
         "ordeAdduser": null,
         "ordeDuty": null,
         "ordeDept": null,
         "puqoCodeSupp": null,
         "puqoSnameSupp": "供应商",
         "puqoCurr": null,
         "puqoTax": null,
         "puqoRemark": null,
         "puqoPriceSet": [
         {
         "puqoGoods": null,
         "puqoPark": null,
         "puqoUm": null,
         "puqoUpdownRate": null,
         "puqoPrice": null,
         "puqoRebate": null,
         "puqoDateBeg": null,
         "puqoDateEnd": 1516417571012,
         "puqoIsladder": null,
         "puqoOqtyMin": null,
         "puqoOqtyMax": null,
         "puqoPriceOld": null,
         "puqoRebateOld": null,
         "puqoDateBegOld": null,
         "puqoDateEndOld": null,
         "puqoIsLadderOld": null,
         "puqoOqtyMinOld": null,
         "puqoOqtyMaxOld": null
         }
         ]
         }
         */
        PuquoteorderEntity entity = JSON.parseObject(JSON.toJSONString(dto),PuquoteorderEntity.class);
        System.out.println(entity.getPuqo_sname_supp());
        System.out.println(JSON.toJSONString(entity, true));

        PuquoteOrderDTO dto2 = JSON.parseObject(JSON.toJSONString(entity),PuquoteOrderDTO.class);
        System.out.println(JSON.toJSONString(dto2, true));

        JSONObject o1 = new JSONObject();
        o1.putAll(JSONObject.parseObject(JSON.toJSONString(dto2)));
        System.out.println(o1);
    }


    @Test
    public void test22() throws  Exception{
       Class clazz = PuqoPuqoPriceSetaProperty.class;
       Field[] fields =  clazz.getDeclaredFields();
       for(Field f:fields){
           System.out.println(f.get(f.getName()));
       }

    }

    @Test
    public void test32() throws  Exception{
        List<Integer> list1 = new ArrayList<>();
        for(int i=0;i<15;i++){
            list1.add(i);
        }
        Map<Integer,List<Integer>> listMap = new HashMap<>();
        List<Integer> tempList = new ArrayList<>();
        for(int i=0;i<list1.size();i++){
            if(tempList.contains(i)){
                continue;
            }
            System.out.println("i="+i);
            List<Integer> indexs = new ArrayList<>();
            for(int j=i;j<list1.size();j++){
                System.out.println("        j="+j);
                if(i==0){
                    if(j<=3){
                        indexs.add(j);
                        tempList.add(j);
                    }
                }else if(i==4){
                    if(j==i||j==7||j==8){
                        indexs.add(j);
                        tempList.add(j);
                    }
                }else if(i==5){
                    if(j==i||j==6||j==9){
                        indexs.add(j);
                        tempList.add(j);
                    }
                }
            }
            listMap.put(i,indexs);
            System.out.println("===================");

        }
        System.out.println(JSONObject.toJSONString(listMap,true));


    }
    @Test
    public void test33() throws  Exception{
//        List<Integer> list1 = new ArrayList<>();
//        for(int i=0;i<10;i++){
//            list1.add(i);
//        }
//        Collections.sort(list1,(o1,o2)->{
//            return String.valueOf(o2).compareTo(String.valueOf(o1));
//        });
//        for(int i=0;i<10;i++){
//            System.out.println(list1.get(i));
//        }
//        JSONArray array = resultData.getJSONArray("selectableParks");
        List<SimplePuqoPriceSetDTO> list = JSONObject.parseArray("[\n" +
                "            {\n" +
                "                \"puqoPark\": \"438495a6d02211e7bbd9005056b7c0c3\",\n" +
                "                \"parkName\": \"深圳\",\n" +
                "                \"puqoPrice\": \"\",\n" +
                "                \"puqoPriceOld\": \"\",\n" +
                "                \"puqoRebate\": \"\",\n" +
                "                \"puqoRebateOld\": \"\",\n" +
                "                \"puqoDateBeg\": \"\",\n" +
                "                \"puqoDateBegOld\": \"\",\n" +
                "                \"puqoDateEnd\": \"\",\n" +
                "                \"puqoDateEndOld\": \"\",\n" +
                "                \"puqoIsLadder\": \"\",\n" +
                "                \"ladderPriceList\": []\n" +
                "            },\n" +
                "            {\n" +
                "                \"puqoPark\": \"43849d97d02211e7bbd9005056b7c0c3\",\n" +
                "                \"parkName\": \"河源\",\n" +
                "                \"puqoPrice\": \"\",\n" +
                "                \"puqoPriceOld\": \"1.0001000\",\n" +
                "                \"puqoRebate\": \"\",\n" +
                "                \"puqoRebateOld\": \"0.0001000\",\n" +
                "                \"puqoDateBeg\": \"\",\n" +
                "                \"puqoDateBegOld\": \"2018-01-22 11:03:23\",\n" +
                "                \"puqoDateEnd\": \"\",\n" +
                "                \"puqoDateEndOld\": \"2018-01-24 11:02:39\",\n" +
                "                \"puqoIsLadder\": \"0\",\n" +
                "                \"ladderPriceList\": []\n" +
                "            }\n" +
                "        ]", SimplePuqoPriceSetDTO.class);
        System.out.println(list.size());
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
        System.out.println(array);
    }



}