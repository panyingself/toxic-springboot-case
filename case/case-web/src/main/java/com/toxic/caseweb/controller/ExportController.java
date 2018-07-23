package com.toxic.caseweb.controller;

import com.toxic.common.config.ExportConfig;
import com.toxic.common.model.DataInfo;
import com.toxic.common.util.ExportUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author py
 * @date 2018/7/23 下午4:01.
 */
@Controller
public class ExportController {
    @Resource
    private ExportConfig exportConfig;
    @RequestMapping(value = "/export",method = {RequestMethod.GET,RequestMethod.POST})
    public void exportExcelHive(SXSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //获得国际化语言
        response.setContentType("APPLICATION/vnd.ms-excel;charset=UTF-8");
        //获取结果集
        List<DataInfo> dataInfoList = new ArrayList<DataInfo>();
        DataInfo dataInfo = new DataInfo();
        dataInfo.setData0("test1");
        dataInfo.setData1("test2");
        dataInfoList.add(dataInfo);
        System.out.println();
        //生成标题
        String[] headerArr = new String[]{"name1","test2"};
        //导出数据
        ExportUtil.export(request,response,workbook,(List<DataInfo>)dataInfoList,headerArr,"test",Integer.parseInt(exportConfig.getPageSize()));
        OutputStream os = response.getOutputStream();
        try {
            workbook.write(os);
            os.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
