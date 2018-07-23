package com.toxic.common.util;

import com.toxic.common.config.ExportConfig;
import com.toxic.common.model.DataInfo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author py
 * @date 2018/7/23 下午2:21.
 */
public class ExportUtil {

    /**
     * Method: poi-excel导出
     * Description: 支持百万级导出以及分页
     * @Author panying
     * @Data 2018/7/23 下午2:59
     * @param request
    * @param response
    * @param workbook
    * @param dataInfoList
    * @param headerArr 列名
    * @param excelName 导出excel的文件名
     * @return void
     */
    public static void export(HttpServletRequest request, HttpServletResponse response, SXSSFWorkbook workbook,
                              List<DataInfo> dataInfoList, String[] headerArr, String excelName,int size){
        response.setContentType("APPLICATION/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-disposition", "attachment;filename="+excelName+".xls");
        int page_size = size;// 定义每页数据数量
        int list_count =dataInfoList.size();//数据总条数

        //计算sheet页数量
        int export_times = list_count % page_size > 0 ? list_count / page_size + 1 : list_count / page_size;

        //循环获取每页数据
        for (int m = 0; m < export_times; m++) {
            //新建sheet
            Sheet sheet = null;
            sheet = workbook.createSheet(System.currentTimeMillis()
                    + "toxic-"+m);
            // 创建属于上面Sheet的Row，参数0可以是0～65535之间的任何一个，
            Row header = sheet.createRow(0); // 第0行
            // 产生标题列，每个sheet页产生一个标题
            Cell cell;
            //产生标题名称
            for (int j = 0; j < headerArr.length; j++) {
                cell = header.createCell((short) j);
                cell.setCellValue(headerArr[j]);
            }

            // 迭代数据
            if (dataInfoList != null && dataInfoList.size() > 0 + (m * page_size )) {
                int rowNum = 1;
                for (int i = 0 + (m * page_size ); i < page_size + (m * page_size ); i++) {

                    if(   i <  dataInfoList.size() ){
                        DataInfo dataInfo = dataInfoList.get(i);
                        sheet.setDefaultColumnWidth((short) 17);
                        Row row = sheet.createRow(rowNum++);

                        for( int j = 0; j < headerArr.length; j++){
                            String code = ("data" + j).toLowerCase();
                            Object value = ClassUtil.getProperties(dataInfo, code);
                            if( null != value ) {
                                row.createCell(j).setCellValue(value.toString());
                            } else {
                                row.createCell(j).setCellValue("");
                            }
                        }
                    }
                }//end for
            }//end if
        }
    }
}
