//package com.toxic.common.util;
//
//import org.apache.log4j.Logger;
//
//import javax.annotation.Resource;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Title:
// * Description:
// *
// * @author py
// * @date 2018/7/23 下午4:40.
// */
//public class HiveJdbcUtil {
//    private static final Logger log = Logger.getLogger(HiveJdbcUtil.class);
//
//    @Resource
//    private HiveJdbcConfig hiveJdbcConfig;
//
//
//
//    public static void main(String[] args) {
////        Connection conn = null;
////        Statement stmt = null;
////        try {
////            conn = getConn();
////            stmt = conn.createStatement();
//
//        // 第一步:存在就先删除
////            String tableName = dropTable(stmt);
//
//        // 第二步:不存在就创建
////            createTable(stmt, tableName);
//
//        // 第三步:查看创建的表
////            showTables(stmt, tableName);
//
//        // 执行describe table操作
////            describeTables(stmt, tableName);
//
//        // 执行load data into table操作
////            loadData(stmt, tableName);
//
//        // 执行 select * query 操作
////            selectData(stmt, "gwtrxs");
//
//        // 执行 regular hive query 统计操作
////            countData(stmt, tableName);
//
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
////            log.error(driverName + " not found!", e);
////            System.exit(1);
////        } catch (SQLException e) {
////            e.printStackTrace();
////            log.error("Connection error!", e);
////            System.exit(1);
////        } finally {
////            try {
////                if (stmt != null) {
////                    stmt.close();
////                    stmt = null;
////                }
////                if (conn != null) {
////                    conn.close();
////                    conn = null;
////                }
////
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        }
//    }
//
//    public HiveResultInfo queryData(String sql){
//        HiveResultInfo hiveResultInfo = new HiveResultInfo();
//
//        Connection conn = null;
//        Statement stmt = null;
//        try {
//            conn = getConn();
//            stmt = conn.createStatement();
//            log.info("Running:" + sql);
//            //查询数据集
//            ResultSet res = stmt.executeQuery(sql);
//            hiveResultInfo = setData(hiveResultInfo,res);
//            log.info("执行 select * query 运行结果:");
//            hiveResultInfo = setColumnName(hiveResultInfo,res);
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return hiveResultInfo;
//    }
////    private static void countData(Statement stmt, String tableName)
////            throws SQLException {
////        String sql = "select count(1) from " + tableName;
////        System.out.println("Running:" + sql);
////        res = stmt.executeQuery(sql);
////        System.out.println("执行“regular hive query”运行结果:");
////        while (res.next()) {
////            System.out.println("count ------>" + res.getString(1));
////        }
////    }
////
////    private static void selectData(Statement stmt, String tableName)
////            throws SQLException {
////        String sql = "select gworders_id from " + tableName+" limit 10";
////        System.out.println("Running:" + sql);
////        res = stmt.executeQuery(sql);
////        System.out.println("执行 select * query 运行结果:");
////        while (res.next()) {
////            System.out.println(res.getString(1));
////        }
////    }
////
////    private static void loadData(Statement stmt, String tableName)
////            throws SQLException {
////        String filepath = "/home/hadoop01/data";
////        String sql = "load data local inpath '" + filepath + "' into table "
////                + tableName;
////        System.out.println("Running:" + sql);
////        res = stmt.executeQuery(sql);
////    }
////
////    private static void describeTables(Statement stmt, String tableName)
////            throws SQLException {
////        String sql = "describe " + tableName;
////        System.out.println("Running:" + sql);
////        res = stmt.executeQuery(sql);
////        System.out.println("执行 describe table 运行结果:");
////        while (res.next()) {
////            System.out.println(res.getString(1) + "\t" + res.getString(2));
////        }
////    }
////
////    private static void showTables(Statement stmt, String tableName)
////            throws SQLException {
////        String sql = "show tables '" + tableName + "'";
////        System.out.println("Running:" + sql);
////        res = stmt.executeQuery(sql);
////        System.out.println("执行 show tables 运行结果:");
////        if (res.next()) {
////            System.out.println(res.getString(1));
////        }
////    }
////
////    private static void createTable(Statement stmt, String tableName)
////            throws SQLException {
////        String sql = "create table "
////                + tableName
////                + " (key int, value string)  row format delimited fields terminated by '\t'";
////        stmt.execute(sql);
////    }
////
////    private static String dropTable(Statement stmt) throws SQLException {
////        // 创建的表名
////        String tableName = "testHive";
////        String sql = "drop table " + tableName;
////        stmt.execute(sql);
////        return tableName;
////    }
//    /**
//     * Method:
//     * Description: 获取jdbc连接
//     * @Author panying
//     * @Data 2018/7/19 下午5:15
//     * @param
//     * @return java.sql.Connection
//     */
//    private  Connection getConn() throws ClassNotFoundException,
//            SQLException {
//        Class.forName(hiveJdbcConfig.getDriverName());
//        Connection conn = DriverManager.getConnection(hiveJdbcConfig.getUrl(), hiveJdbcConfig.getUser(), hiveJdbcConfig.getPassword());
//        return conn;
//    }
//    /**
//     * Method:
//     * Description: 设置数据集
//     * @Author panying
//     * @Data 2018/7/19 下午5:07
//     * @param hiveResultInfo
//     * @return com.reapal.report.repository.HiveResultInfo
//     */
//    private HiveResultInfo setData(HiveResultInfo hiveResultInfo,ResultSet res){
//        List<DataInfo> list = new ArrayList<DataInfo>();
//        try {
//            //动态获取列名
//            ResultSetMetaData rsmd = res.getMetaData();
//            //获取一共有多少列
//            int count=rsmd.getColumnCount();
//            while (res.next()) {
//                DataInfo dataInfo = new DataInfo();
//                //给每一列赋值
//                for (int i = 0;i < count;i++){
//                    String code = ("data" + i).toLowerCase();
//                    dataInfo = (DataInfo) ClassUtil.setProperties(dataInfo,code,res.getString(i+1));
//                }
//                list.add(dataInfo);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        hiveResultInfo.setList(list);
//        return hiveResultInfo;
//    }
//    /**
//     * Method:
//     * Description: 设置标题集
//     * @Author panying
//     * @Data 2018/7/19 下午5:08
//     * @param hiveResultInfo
//     * @return com.reapal.report.repository.HiveResultInfo
//     */
//    private HiveResultInfo setColumnName(HiveResultInfo hiveResultInfo,ResultSet res){
//        try {
//            //动态获取列名
//            ResultSetMetaData rsmd = res.getMetaData();
//            //获取一共有多少列
//            int count=rsmd.getColumnCount();
//            String[] name=new String[count];
//            for(int i=0;i<count;i++){
//                name[i]=rsmd.getColumnName(i+1);
//            }
//            hiveResultInfo.setColumnName(name);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return hiveResultInfo;
//    }
//}
