package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.EdmMethod;
import com.huntkey.rx.modeler.common.model.EdmModeler;
import com.huntkey.rx.modeler.common.model.EdmModelerExample;
import com.huntkey.rx.modeler.common.model.vo.EdmClassVO;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodTypeVO;
import com.huntkey.rx.modeler.common.model.vo.EdmModelerVO;
import com.huntkey.rx.modeler.common.model.vo.EdmPropertyVO;
import com.huntkey.rx.modeler.provider.ModelerProviderApplication;
import org.apache.poi.hssf.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liangh on 2017/4/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ModelerProviderApplication.class)
public class EdmModelerServiceTest {
    @Autowired
    private EdmModelerService edmModelerService;

    @Autowired
    private EdmClassService edmClassService;

    @Autowired
    private EdmCodeService edmCodeService;

    @Autowired
    private EdmPropertyService edmPropertyService;

    @Autowired
    private EdmConvoluteService edmConvoluteService;

    @Autowired
    private EdmLinkService edmLinkService;

    @Autowired
    private EdmConnectService edmConnectService;

    @Autowired
    private EdmUnitService edmUnitService;

    @Autowired
    private EdmAttachmentService edmAttachmentService;

    @Autowired
    private EdmMethodService edmMethodService;


   /* @Autowired
    private EdmClassMethdService edmClassMethodService;*/


    @Test
    public void add(){
        EdmModeler edmModeler = new EdmModeler();
        edmModeler.setId(UuidCreater.uuid());
        //edmModeler.setEdmdName("raraar");
        edmModeler.setIsDel((byte)0);
        edmModeler.setEdmdStatus((byte)1);
        edmModeler.setEdmdCode("333333");
        //edmModeler.setEdmiIndustryCode("IC001,IC002");
        edmModelerService.insert(edmModeler);
    }

    @Test
    public void update(){
        EdmModeler edmModeler = new EdmModeler();
        edmModeler.setId("8ab10933350045c2a11fed2ff88a9bcc");
        edmModeler.setEdmdParentId("");
        edmModelerService.updateByPrimaryKey(edmModeler);
    }

    @Test
    public void delete(){
        String aa = "1";
        edmModelerService.updateIsDelByPrimaryKey(aa);
    }

    @Test
    public void newTestExportExcel(){
        String id = "5b46a394d2cc4b02ba8d7f5880080f80";
        //String fileUrl = "F:/test.xls";
        String fileUrl = "D:/modelerData";
        fileUrl = edmModelerService.judgeFilePath(fileUrl);
        HSSFWorkbook wb = new HSSFWorkbook();
        wb = edmModelerService.exportExcel(id);
        //ExcelUtil.writeExcel(wb,fileUrl);
    }

    private void exportToExcel(EdmClassVO edmClassVO,String fileUrl){
        List<EdmClassVO> le = new ArrayList<EdmClassVO>();
        le.add(edmClassVO);
        FileOutputStream fos = null;
        try{
            HSSFWorkbook wb = null;
            wb = getWorkbook(le);
            fos = new FileOutputStream(fileUrl);
            if(wb!=null){
                wb.write(fos);
            }
            fos.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            // 如果fos不是null，才需要close()
            if (fos != null) {
                // 为了保证close()一定会执行，就放到这里了
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private HSSFWorkbook getWorkbook(List<EdmClassVO> list){
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("类定义");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 1);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell(1);
        cell.setCellValue("序号");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("父类");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("类编码");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("类名称");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("类英文名");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("排序");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("描述");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("是否自定义");
        cell.setCellStyle(style);
        cell = row.createCell(9);
        cell.setCellValue("版本号");
        cell.setCellStyle(style);
        cell = row.createCell(10);
        cell.setCellValue("责任人");
        cell.setCellStyle(style);
        cell = row.createCell(11);
        cell.setCellValue("使用说明");
        cell.setCellStyle(style);

        for (int i = 0; i < list.size(); i++)
        {
            row = sheet.createRow((int) i + 2);
            EdmClassVO ecv = (EdmClassVO) list.get(i);
            // 第四步，创建单元格，并设置值
            row.setRowStyle(style);
            HSSFCell hc = row.createCell(1);
            hc.setCellValue(i+1);
            hc.setCellStyle(style);

            hc = row.createCell(2);
           // hc.setCellValue(ecv.getEdmcParentName());
            hc.setCellStyle(style);

            hc = row.createCell(3);
            hc.setCellValue(ecv.getEdmcCode());
            hc.setCellStyle(style);

            hc = row.createCell(4);
            hc.setCellValue(ecv.getEdmcName());
            hc.setCellStyle(style);

            hc = row.createCell(5);
            hc.setCellValue(ecv.getEdmcNameEn());
            hc.setCellStyle(style);

            hc = row.createCell(6);
            hc.setCellValue(ecv.getEdmcSeq());
            hc.setCellStyle(style);

            hc = row.createCell(7);
            hc.setCellValue(ecv.getEdmcDesc());
            hc.setCellStyle(style);

            hc = row.createCell(8);
            hc.setCellValue(ecv.getIsDefined()==1?"是":"否");
            hc.setCellStyle(style);

            hc = row.createCell(9);
            hc.setCellValue(ecv.getEdmcVer());
            hc.setCellStyle(style);

            hc = row.createCell(10);
            hc.setCellValue(ecv.getEdmcPrincipal());
            hc.setCellStyle(style);

            hc = row.createCell(11);
            hc.setCellValue(ecv.getEdmcDesc());
            hc.setCellStyle(style);

            /*cell = row.createCell(3);
            cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(stu
                    .getBirth()));*/
        }
        return wb;
    }

    @Test
    public void selectModelerVer(){
        List<EdmModeler> edmModelerList = new ArrayList<EdmModeler>();
        EdmModeler edmModeler1 = new EdmModeler();
        edmModeler1.setEdmdVer("V1.0");
        edmModelerList.add(edmModeler1);
        EdmModeler edmModeler2 = new EdmModeler();
        edmModeler2.setEdmdVer("V1.1");
        edmModelerList.add(edmModeler2);
        EdmModeler edmModeler3= new EdmModeler();
        edmModeler3.setEdmdVer("V2.1");
        edmModelerList.add(edmModeler3);
        //获取最大的版本号
        String maxVer = "V1.0";
        for(EdmModeler edmModeler:edmModelerList){
            if(!StringUtils.isEmpty(edmModeler.getEdmdVer())){
                double maxver = Double.valueOf(maxVer.substring(1,maxVer.length()));
                double tempver = Double.valueOf(edmModeler.getEdmdVer().substring(1,maxVer.length()));
                if(maxver<tempver){
                    maxVer = "V" + Double.toString(tempver);
                }
            }
        }
        //最大版本号最后一位加一
        if(maxVer.contains(".")){
            System.out.println("maxVer="+maxVer);
            String secondNum = maxVer.split("\\.")[1];
            int temp = 0;
            if(secondNum!=null&&!"".equals(secondNum)){
                temp = Integer.parseInt(secondNum)+1;
            }
            maxVer = maxVer.split("\\.")[0] + "." + Integer.toString(temp);
        }
        System.out.println("maxVer="+maxVer);
    }

    @Test
    public void testAdd(){
        EdmModeler edmModeler = new EdmModeler();
        edmModeler.setId("5");
        edmModeler.setEdmdVer("V0.1");
        edmModeler.setEdmdStatus((byte)1);
        edmModeler.setEdmdUpdateDesc("1111");
        edmModelerService.insert(edmModeler);
    }

    @Test
    public void testEdmModelerInsert(){
        EdmModeler edmModeler = new EdmModeler();
        edmModeler.setId("f691d19633a64639b3ab8a2e4617fc35");
        edmModeler.setEdmdStatus((byte)1);
        edmModeler.setEdmdCode("33333");
        edmModeler.setEdmdVer("V222.1");
        edmModeler.setEdmdUpdateDesc("asdasd");
        edmModelerService.insert(edmModeler);
    }

    @Test
    public void testDelete(){
        String id = "5";
        edmModelerService.updateIsDelByPrimaryKey(id);
    }

    @Test
    public void upload(){
        HttpServletRequest request;
        //EdmAttachment edmAttachment = edmAttachmentService.upload(request);
    }

    @Test
    public void testQueryClassMethodType(){
        List<EdmMethodTypeVO> list = edmMethodService.queryClassMethodTypeTree();
        for(EdmMethodTypeVO edmMethodTypeVO:list){
            System.out.println(edmMethodTypeVO.toString());
        }
    }

    @Test
    public void testFilePath(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileName = dateFormat.format(date);
        System.out.println("fileName="+fileName);
    }

    @Test
    public void testRoot(){
        File[] roots = File.listRoots();
        for (int i = 0; i < roots.length; i++) {
            System.out.println(roots[i].getAbsoluteFile());
        }
    }

    @Test
    public void test11(){
        String id = "68cb46cf9d174c6b88b7b2ee00c3b6a5";
        List<EdmPropertyVO> edmProperties = edmPropertyService.selectEdmPropertiesByCid(id);
        for(EdmPropertyVO edmPropertyVO:edmProperties){
            System.out.println(edmPropertyVO.toString());
        }
    }
    @Test
    public void testgetMethodsByClassId(){
        String id = "4";
        List<EdmMethod> le = null;
//        le = edmClassService.selectEdmMethodByClassId(id);
        if(le!=null){
            System.out.println("le.size()="+le.size());
        }
    }

    @Test
    public void testSelectById(){
        String id = "bab56672d7804534a862039ec07cbd52";
        EdmClassVO edmClassVO = edmClassService.selectById(id);
        System.out.println(edmClassVO.toString());
    }

    @Test
    public void testClassMethodUpdate(){
        String classId = "8888052b9164f21b96a98189be7b116";
        String[] methodIds = {"222"};
       // edmClassMethodService.updateClassMethods(classId,methodIds);
    }

    @Test
    public void testBeforeAdd(){
        EdmModelerVO edmModelerVO = edmModelerService.beforeAdd();
        System.out.println(edmModelerVO.toString());
    }

    @Test
    public void testCheckVerUnique(){
        EdmModeler edmModeler = new EdmModeler();
        edmModeler.setEdmdVer("V1.0");
        int verNum = 0;
        //verNum = edmModelerService.checkUnique("V1.0");
        System.out.println("verNum="+verNum);
    }

    @Test
    public void testQuery(){
        List<EdmModelerVO> VOList = null;
        EdmModelerExample example = new EdmModelerExample();
        VOList = edmModelerService.getShowList(example);
        if(!VOList.isEmpty()){
            for(EdmModelerVO edmModelerVO:VOList){
                System.out.println(edmModelerVO.toString());
            }
        }
    }

    @Test
    public void testCheckData(){
        int verNum = 0;
        EdmModeler edmModeler = new EdmModeler();
        edmModeler.setEdmdVer("V2.6");
        //verNum = edmModelerService.checkUnique("V2.6");
        System.out.println(verNum);
    }

    @Test
    public void testQueryClassMethodTypeTree(){
        List<EdmMethodTypeVO> le = null;
        le = edmMethodService.queryClassMethodTypeTree();
        if(!le.isEmpty()){
            for(EdmMethodTypeVO e:le)
            System.out.println(e.toString());
        }
    }

    @Test
    public void testReg(){
        String reg = "^(v)\\d{1,}\\.\\d{1,}$";
        String v1 = "v1.1";
        String v2 = "V1.1";
        String v3 = "v1.11";
        String v4 = "v111.1";
        String v5 = "hahaha";
        String v6 = "大的";
        String v7 = "v111.dd";
        String v8 = "v111.的";
        String v9 = "vdd.1";
        String v10 = "v的.1";
        for(int i=1;i<11;i++){
            String ver = "v"+i;
            System.out.println("v" + i + ":" + reg.matches(ver));
        }

    }

    @Test
    public void testInsertModeler(){
        EdmModeler edmModeler = new EdmModeler();
        edmModeler.setId("a9e63a1389884ac28eb3c234157dcceb");//0feda5d06432492882fff527fdd4c297  //a9e63a1389884ac28eb3c234157dcceb
        edmModeler.setEdmdVer("V100.0");
        edmModeler.setEdmdStatus((byte)7);
        edmModelerService.insert(edmModeler);
    }

    @Test
    public void testChangePublishedStatus(){
        EdmModeler edmModeler = new EdmModeler();
        edmModeler.setId("408936c3cb3c4866893f1dc0c3439aa8");
        edmModeler.setEdmdStatus((byte)6);
        edmModelerService.updateByPrimaryKey(edmModeler);
    }

    @Test
    public void testSubString(){
        String head = "";
        head = "v1.0".substring(0,1);
        System.out.println(head);
        head = "V1.1".substring(0,1);
        System.out.println(head);
    }

    @Test
    public void testCheckUnique() {
        String errorStr = "";
        String edmcCode = "C00099";
        String edmcName = "2323";
        String edmcShortName = "123";
        String edmcEdmdId = "44d7b2e49be845bd924cb40f1d4eb339";
        //errorStr = edmClassService.checkUnique(edmcCode, edmcName, edmcShortName, edmcEdmdId);
        System.out.println("errorStr="+errorStr);
    }

    @Test
    public void testGetSimpleClass() {
        String id = "01002a664abe4ca0bcbd384cbae3e110";
        EdmClassVO edmClassVO = edmClassService.selectSimpleClassById(id);
        System.out.println(edmClassVO.toString());
    }

    @Test
    public void testQueryss() {
        List<EdmModelerVO> VOList = null;
        EdmModelerExample example = new EdmModelerExample();
        EdmModelerExample.Criteria criteria = example.createCriteria().andIsDelNotEqualTo((byte)1);
        String edmdVer = "V1";
        if(edmdVer!=null){
            edmdVer = "%"+edmdVer+"%";
            criteria.andEdmdVerLike(edmdVer);
        }
        VOList = edmModelerService.getShowList(example);
        for(EdmModelerVO edmModelerVO:VOList) {
            System.out.println(edmModelerVO.toString());
        }
    }

    @Test
    public void testInsertModelerAllData() {
        String oldModelerId = "9aeb08337eab423389e2e991537c285b";
        String newModelerId = UuidCreater.uuid();
        System.out.println("newModelerId="+newModelerId);
        edmModelerService.insertAllData(newModelerId,oldModelerId);
    }
}
