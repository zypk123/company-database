package com.huntkey.rx.purchase.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.SettlemenetProperty;
import com.huntkey.rx.edm.entity.SettlemenetEntity;
import com.huntkey.rx.purchase.common.constants.SettTypeConstants;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.model.settlemenet.SettleMenetDTO;
import com.huntkey.rx.purchase.provider.service.SettleMenetService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 结算方式Service接口实现类
 *
 * @author zhangyu
 * @create 2018-01-10 17:36
 **/
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SettleMenetServiceImpl implements SettleMenetService {
    Logger logger = LoggerFactory.getLogger(SettleMenetServiceImpl.class);

    @Autowired
    private OrmService ormService;

    @Override
    public SettlemenetEntity load(String id) {
        if (StringUtil.isNullOrEmpty(id)){
            return null;
        }
        SettlemenetEntity settlemenetEntity = null;
        try {
            settlemenetEntity = ormService.load(SettlemenetEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return settlemenetEntity;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public String insert(SettleMenetDTO settleMenetDTO) {
        logger.info("结算方式保存服务开始...");

        String settleMenetId = null;

        try {
            // DTO转Entity
            SettlemenetEntity settlemenetEntity = JSONObject.parseObject(JSON.toJSONString(settleMenetDTO), SettlemenetEntity.class);

            settleMenetId = ormService.insertSelective(settlemenetEntity).toString();

        } catch (Exception e) {

            logger.error("结算方式保存接口出现错误：", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return settleMenetId;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result save(SettleMenetDTO settleMenetDTO) {
        logger.info("结算方式保存服务开始...");
        Result result = new Result();
        try {
            // 对不同的结算类型，进行不同的校验
            String settType = settleMenetDTO.getSettType();
            // 预付 - 1
            if (settType.equals(SettTypeConstants.PREPAY)) {
                // 封装查询条件
                OrmParam ormParam = new OrmParam();
                ormParam.setWhereExp(OrmParam.and(ormParam.getWhereExp(),
                        ormParam.getEqualXML(SettlemenetProperty.SETT_TYPE, settleMenetDTO.getSettType()),
                        ormParam.getEqualXML(SettlemenetProperty.SETT_ADAYS, settleMenetDTO.getSettAdays()),
                        ormParam.getEqualXML(SettlemenetProperty.SETT_RATE, settleMenetDTO.getSettRate()),
                        ormParam.getEqualXML(SettlemenetProperty.SETT_IDAYS, settleMenetDTO.getSettIdays())
                ));
                return getResult(settleMenetDTO, ormParam);
                // 到货 - 2
            } else if (settType.equals(SettTypeConstants.ARRIVAL)) {
                // 封装查询条件
                OrmParam ormParam = new OrmParam();
                ormParam.setWhereExp(OrmParam.and(ormParam.getWhereExp(),
                        ormParam.getEqualXML(SettlemenetProperty.SETT_TYPE, settleMenetDTO.getSettType()),
                        ormParam.getEqualXML(SettlemenetProperty.SETT_IDAYS, settleMenetDTO.getSettIdays())
                ));
                return getResult(settleMenetDTO, ormParam);
                // 月结 - 3
            } else if (settType.equals(SettTypeConstants.MONTHLY)) {
                // 封装查询条件
                OrmParam ormParam = new OrmParam();
                ormParam.setWhereExp(OrmParam.and(ormParam.getWhereExp(),
                        ormParam.getEqualXML(SettlemenetProperty.SETT_TYPE, settleMenetDTO.getSettType()),
                        ormParam.getEqualXML(SettlemenetProperty.SETT_MDATE, settleMenetDTO.getSettMdate()),
                        ormParam.getEqualXML(SettlemenetProperty.SETT_MDAYS, settleMenetDTO.getSettMdays())
                ));
                return getResult(settleMenetDTO, ormParam);
                // 出货 - 4
            } else if (settType.equals(SettTypeConstants.SHIPMENT)) {
                // 封装查询条件
                OrmParam ormParam = new OrmParam();
                ormParam.setWhereExp(OrmParam.and(ormParam.getWhereExp(),
                        ormParam.getEqualXML(SettlemenetProperty.SETT_TYPE, settleMenetDTO.getSettType()),
                        ormParam.getEqualXML(SettlemenetProperty.SETT_ODAYS, settleMenetDTO.getSettOdays())
                ));
                return getResult(settleMenetDTO, ormParam);
                // 其他 - 5
            } else if (settType.equals(SettTypeConstants.OTHER)) {
                // 封装查询条件
                OrmParam ormParam = new OrmParam();
                ormParam.setWhereExp(OrmParam.and(ormParam.getWhereExp(),
                        ormParam.getEqualXML(SettlemenetProperty.SETT_TYPE, settleMenetDTO.getSettType()),
                        ormParam.getEqualXML(SettlemenetProperty.SETT_DESC, settleMenetDTO.getSettDesc())
                ));
                return getResult(settleMenetDTO, ormParam);
            }
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("结算方式-判断是新增还直接取值发生错误");

            logger.error("结算方式-判断是新增还直接取值发生错误:", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return result;
    }

    /**
     * 结算方式-判断是新增还直接取值
     *
     * @param settleMenetDTO
     * @param ormParam
     * @return
     */
    private Result getResult(SettleMenetDTO settleMenetDTO, OrmParam ormParam) {

        Result result = new Result();

        try {
            List<SettlemenetEntity> settlemenetList = ormService.selectBeanList(SettlemenetEntity.class, ormParam);

            if (null != settlemenetList && settlemenetList.size() > 0) {

                // 存在该值，直接返回该数据的id，以及结算方式字段
                // 由于不会存在重复值，故直接取第一条
                SettlemenetEntity settlemenetEntity = settlemenetList.get(0);

                // 封装返回值
                JSONObject object = new JSONObject();
                object.put("id", settlemenetEntity.getId());
                object.put("settWay", settlemenetEntity.getSett_way());

                result.setData(object);

                return result;

            } else {
                // 新增
                String settleMenetId = insert(settleMenetDTO);

                SettlemenetEntity settlemenetEntity = ormService.load(SettlemenetEntity.class, settleMenetId);

                String settWay = settlemenetEntity.getSett_way();

                // 封装返回值
                JSONObject object = new JSONObject();
                object.put("id", settleMenetId);
                object.put("settWay", settWay);

                result.setData(object);
                return result;
            }
        } catch (Exception e) {
            logger.error("结算方式-判断是新增还直接取值发生错误:", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
    }

}
