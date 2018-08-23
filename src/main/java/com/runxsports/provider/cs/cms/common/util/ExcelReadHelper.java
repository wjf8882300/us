package com.runxsports.provider.cs.cms.common.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;

/**
 * 解析Excel，支持2003、2007
 *
 * @Author:chenssy
 * @date:2014年8月3日
 */
public class ExcelReadHelper {

    /**
     * 解析Excel 支持2003、2007<br>
     * 利用反射技术完成propertis到obj对象的映射，并将相对应的值利用相对应setter方法设置到obj对象中最后add到list集合中<br>
     * properties、obj需要符合如下规则：<br>
     * 1、obj对象必须存在默认构造函数，且属性需存在setter方法<br>
     * 2、properties中的值必须是在obj中存在的属性，且obj中必须存在这些属性的setter方法。<br>
     * 3、properties中值得顺序要与Excel中列相相应，否则值会设置错：<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;excel:编号    姓名         年龄       性别<br>
     * properties:id  name  age  sex<br>
     *
     * @param properties 与Excel相对应的属性
     * @param obj        反射对象的Class
     * @return
     * @throws Exception
     * @autor:chenssy
     * @date:2014年8月9日
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> excelRead(InputStream inputStream, String[] properties, Class<T> obj) throws Exception {
        Workbook book = WorkbookFactory.create(inputStream);      //解析2007
        return getExcelContent(book, properties, obj);
    }

    /**
     * 根据params、object解析Excel，并且构建list集合
     *
     * @param book       WorkBook对象，他代表了待将解析的Excel文件
     * @param properties 需要参考Object的属性
     * @return
     * @throws Exception
     * @autor:chenssy
     * @date:2014年8月9日
     */
    @SuppressWarnings("rawtypes")
    private static <T> List<T> getExcelContent(Workbook book, String[] properties,
                                                Class<T> obj) throws Exception {
        List<T> resultList = new ArrayList<T>();        //初始化结果解
        Map<String, Method> methodMap = getObjectSetterMethod(obj);
        Map<String, Field> fieldMap = getObjectField(obj);
        for (int numSheet = 0; numSheet < book.getNumberOfSheets(); numSheet++) {
            Sheet sheet = book.getSheetAt(numSheet);
            if (sheet == null) {   //谨防中间空一行
                continue;
            }

            for (int numRow = 1; numRow <= sheet.getLastRowNum(); numRow++) {   //一个row就相当于一个Object
                Row row = sheet.getRow(numRow);
                if (row == null) {
                    continue;
                }
                resultList.add(getObject(row, properties, methodMap, fieldMap, obj));
            }
        }
        return resultList;
    }

    /**
     * 获取row的数据，利用反射机制构建Object对象
     *
     * @param row        row对象
     * @param properties Object参考的属性
     * @param methodMap  object对象的setter方法映射
     * @param fieldMap   object对象的属性映射
     * @return
     * @throws Exception
     * @autor:chenssy
     * @date:2014年8月9日
     */
    @SuppressWarnings("rawtypes")
    private static <T> T getObject(Row row, String[] properties,
                                    Map<String, Method> methodMap, Map<String, Field> fieldMap, Class<T> obj) throws Exception {
        T object = obj.newInstance();
        for (int numCell = 0; numCell < row.getLastCellNum(); numCell++) {
            Cell cell = row.getCell(numCell);
            if (cell == null) {
                continue;
            }
            String cellValue = getValue(cell);
            String property = properties[numCell].toLowerCase();
            Field field = fieldMap.get(property);    //该property在object对象中对应的属性
            Method method = methodMap.get(property);  //该property在object对象中对应的setter方法
            setObjectPropertyValue(object, field, method, cellValue);
        }
        return object;
    }

    /**
     * 根据指定属性的的setter方法给object对象设置值
     *
     * @param obj    object对象
     * @param field  object对象的属性
     * @param method object对象属性的相对应的方法
     * @param value  需要设置的值
     * @throws Exception
     * @autor:chenssy
     * @date:2014年8月10日
     */
    private static void setObjectPropertyValue(Object obj, Field field,
                                               Method method, String value) throws Exception {
        Object[] oo = new Object[1];

        String type = field.getType().getName();
        if ("java.lang.String".equals(type) || "String".equals(type)) {
            oo[0] = value;
        } else if ("java.lang.Integer".equals(type) || "java.lang.int".equals(type) || "Integer".equals(type) || "int".equals(type)) {
            if (value.length() > 0) {
            	oo[0] = Integer.valueOf(value);
            }
        } else if ("java.lang.Float".equals(type) || "java.lang.float".equals(type) || "Float".equals(type) || "float".equals(type)) {
            if (value.length() > 0) {
            	oo[0] = Float.valueOf(value);
            }
        } else if ("java.lang.Double".equals(type) || "java.lang.double".equals(type) || "Double".equals(type) || "double".equals(type)) {
            if (value.length() > 0) {
            	oo[0] = Double.valueOf(value);
            }   
        } else if ("java.math.BigDecimal".equals(type) || "BigDecimal".equals(type)) {
            if (value.length() > 0) {
            	oo[0] = new BigDecimal(value);
            }
        } else if ("java.util.Date".equals(type) || "Date".equals(type)) {
            if (value.length() > 0) {
                if (value.length() == 19 || value.length() == 14) {
                    oo[0] = DateUtils.parseDate(value, "yyyyMMddHHmmss");
                } else {
                    oo[0] = DateUtils.parseDate(value, "yyyyMMdd");
                }
            }
        } else if ("java.sql.Timestamp".equals(type)) {
            if (value.length() > 0) {
                oo[0] = DateUtils.parseDate(value, "yyyyMMddHHmmss");
            }
        } else if ("java.lang.Boolean".equals(type) || "Boolean".equals(type)) {
            if (value.length() > 0) {
                oo[0] = Boolean.valueOf(value);
            }
        } else if ("java.lang.Long".equals(type) || "java.lang.long".equals(type) || "Long".equals(type) || "long".equals(type)) {
            if (value.length() > 0) {
                oo[0] = Long.valueOf(value);
            }
        }else if ("java.util.List".equals(type) || "List".equals(type)) {
            oo[0] = value;
        }
        try {
            method.invoke(obj, oo);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @SuppressWarnings("static-access")
    private static String getValue(Cell cell) {
        if (cell.getCellTypeEnum().equals(CellType.BOOLEAN)) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            return NumberToTextConverter.toText(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

    /**
     * 获取object对象所有属性的Setter方法，并构建map对象，结构为Map<'field','method'>
     *
     * @param object object对象
     * @return
     * @autor:chenssy
     * @date:2014年8月9日
     */
    @SuppressWarnings("rawtypes")
    private static Map<String, Method> getObjectSetterMethod(Class object) {
        Field[] fields = object.getDeclaredFields();       //获取object对象的所有属性
        Method[] methods = object.getDeclaredMethods();    //获取object对象的所有方法
        Map<String, Method> methodMap = new HashMap<String, Method>();
        for (Field field : fields) {
            String attri = field.getName();
            for (Method method : methods) {
                String meth = method.getName();
                //匹配set方法
                if (meth != null && "set".equals(meth.substring(0, 3)) &&
                        Modifier.isPublic(method.getModifiers()) &&
                        ("set" + Character.toUpperCase(attri.charAt(0)) + attri.substring(1)).equals(meth)) {
                    methodMap.put(attri.toLowerCase(), method);       //将匹配的setter方法加入map对象中
                    break;
                }
            }
        }

        return methodMap;
    }

    /**
     * 获取object对象的所有属性，并构建map对象，对象结果为Map<'field','field'>
     *
     * @param object object对象
     * @return
     * @autor:chenssy
     * @date:2014年8月10日
     */
    @SuppressWarnings("rawtypes")
    private static Map<String, Field> getObjectField(Class object) {
        Field[] fields = object.getDeclaredFields();       //获取object对象的所有属性
        Map<String, Field> fieldMap = new HashMap<String, Field>();
        for (Field field : fields) {
            String attri = field.getName();
            fieldMap.put(attri.toLowerCase(), field);
        }
        return fieldMap;
    }
}