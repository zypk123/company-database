package com.huntkey.rx.modeler.provider.service.impl;

import com.huntkey.rx.modeler.common.model.EdmNumber;
import com.huntkey.rx.modeler.provider.dao.EdmNumberMapper;
import com.huntkey.rx.modeler.provider.service.EdmNumberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author liucs
 * 2017-11-21 11:21:42
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class EdmNumberServiceImpl implements EdmNumberService {
    /**
     * 规则类型
     */
    private static  Integer EDMN_TTYPE_FIRST = Integer.valueOf(1);
    private static  Integer EDMN_TTYPE_SECOND = Integer.valueOf(2);
    private static  Integer EDMN_TTYPE_THIRD = Integer.valueOf(3);
    private static  Integer EDMN_TTYPE_FOURTH = Integer.valueOf(4);


    @Autowired
    private EdmNumberMapper edmNumberMapper;
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public String update(EdmNumber edmNumber) {
        StringBuilder endoceStr = new StringBuilder();
        Date date = new Date();
        //规则类型
        Integer edmnType = edmNumber.getEdmnType();
        //编号规则
        String edmnEncode = edmNumber.getEdmnEncode();
        //流水号
        Integer edmnSerialNumber = 0;
        //根据规则类型（edmnTYpe）和编号规则（edmnEncod）查询EdmNumber
        EdmNumber exitedEdmNumber= edmNumberMapper.selectedmNumberByTypeEncod(edmnType,edmnEncode);
        if(null != exitedEdmNumber){
            //流水号+1
            edmnSerialNumber = exitedEdmNumber.getEdmnSerialNumber() + 1;
            exitedEdmNumber.setEdmnSerialNumber(edmnSerialNumber);
            exitedEdmNumber.setAddtime(date);
            exitedEdmNumber.setModtime(date);
            exitedEdmNumber.setIsDel((byte) 0);
            edmNumberMapper.updateByPrimaryKeySelective(exitedEdmNumber);
            //获取当前时间
            Calendar nowDate = Calendar.getInstance();
            //当前年份(后两位)
            String nowYear = String.valueOf(nowDate.get(Calendar.YEAR)).substring(2);
            //当前月份
            String nowMon = String.valueOf(nowDate.get(Calendar.MONTH) + 1);
            //当前日
            String nowDay = String.valueOf(nowDate.get(Calendar.DAY_OF_MONTH));
            //流水号补位（4位或5位）
            String endoceSerialNumber = "";
            if(null == edmnEncode){
                edmnEncode = "";
            }
            if (Objects.equals(EDMN_TTYPE_SECOND, edmNumber.getEdmnType())){
                endoceSerialNumber = String.format("%05d", edmnSerialNumber);
            }else{
                endoceSerialNumber = String.format("%04d", edmnSerialNumber);
            }
            if (Objects.equals(EDMN_TTYPE_FIRST, edmNumber.getEdmnType())){
                endoceStr.append(edmnEncode).append("-").
                        append(nowYear).append(nowMon).
                        append(nowDay).append(endoceSerialNumber);
            }else if(Objects.equals(EDMN_TTYPE_SECOND, edmNumber.getEdmnType()) || Objects.equals(EDMN_TTYPE_THIRD, edmNumber.getEdmnType())){
                endoceStr.append(edmnEncode).append(endoceSerialNumber);
            }else{
                endoceStr.append(edmnEncode). append(nowYear).append(nowMon).
                        append(nowDay).append(endoceSerialNumber);
            }
        }
        return endoceStr.toString();
    }

}
