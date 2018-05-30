package com.dlz.framework.ssme.util.office;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.ssme.util.system.Exceptions;
import com.dlz.framework.util.StringUtils;
import com.dlz.framework.util.system.Reflections;

public class Excels {
	private static MyLogger logger = MyLogger.getLogger(Excels.class);
	public static byte[] exportExcel(String title, Map<String, String> fieldMap, Collection<?> data, String pattem) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			Workbook wb = new HSSFWorkbook();

			// 设置日期格式
			CellStyle dateCellStyle = null;
			if (StringUtils.isNotEmpty(pattem)) {
				CreationHelper createHelper = wb.getCreationHelper();
				dateCellStyle = wb.createCellStyle();
				dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat(pattem));
			}

			// 设置文档头部信息
			Sheet sheet = wb.createSheet(title);
			Row row = sheet.createRow(0);
			int cellIndex = 0;
			for (String headName : fieldMap.values()) {
				row.createCell(cellIndex++).setCellValue(headName);
			}

			// 设置文档内容
			int rowIndex = 1;
			for (Object obj : data) {
				cellIndex = 0;
				row = sheet.createRow(rowIndex++);
				for (String fieldName : fieldMap.keySet()) {
					Cell cell = row.createCell(cellIndex++);
					Object fieldValue = Reflections.getFieldValue(obj, fieldName);
					if (fieldValue instanceof Date) {
						if (dateCellStyle != null) {
							cell.setCellStyle(dateCellStyle);
						}
						cell.setCellValue((Date) fieldValue);
					} else {
						if (fieldValue != null) {
							cell.setCellValue(fieldValue.toString());
						}
					}
				}
			}
			wb.write(baos);
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
		return baos.toByteArray();
	}

	/**
	 * 判断excel版本
	 * 
	 * @param in
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Workbook openWorkbook(InputStream in) throws IOException {
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(in);
		} catch (Exception e) {
			try {
				workbook = new HSSFWorkbook(in);
			} catch (Exception ex) {
				logger.error(ex.getMessage(),ex);
			}
		}
		return workbook;
	}

	public static Object getCeilValue(Cell cell) {
		Object cellValue = "";
		if (null != cell) {
			// 以下是判断数据的类型
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC: // 数字
				int cellStyle = cell.getCellStyle().getDataFormat();
				String cellStyleStr = cell.getCellStyle().getDataFormatString();
				if ("0.00_);[Red]\\(0.00\\)".equals(cellStyleStr)) {
					NumberFormat f = new DecimalFormat("#.##");
					cellValue = (f.format((cell.getNumericCellValue())) + "").trim();
				} else if (HSSFDateUtil.isCellDateFormatted(cell)) {
					cellValue = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
				} else if (cellStyle == 58 || cellStyle == 179 || "m\"月\"d\"日\";@".equals(cellStyleStr)) {
					// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					double value = cell.getNumericCellValue();
					Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
					cellValue = sdf.format(date);
				} else if ("[$-804]aaaa;@".equals(cellStyleStr)) {
					SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
					double value = cell.getNumericCellValue();
					Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
					cellValue = sdf.format(date);
				} else {
					NumberFormat f = new DecimalFormat("#.##");
					cellValue = (f.format((cell.getNumericCellValue())) + "").trim();
				}
				break;
			case HSSFCell.CELL_TYPE_STRING: // 字符串
				cellValue = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
				cellValue = cell.getBooleanCellValue() + "";
				break;
			case HSSFCell.CELL_TYPE_FORMULA: // 公式
				try {
					cellValue = String.valueOf(cell.getNumericCellValue());
				} catch (IllegalStateException e) {
					try {
						cellValue = String.valueOf(cell.getRichStringCellValue());
					} catch (Exception e1) {
						cellValue = "";
					}
				}
				break;
			case HSSFCell.CELL_TYPE_BLANK: // 空值
				break;
			case HSSFCell.CELL_TYPE_ERROR: // 故障
				break;
			default:
				break;
			}
		}
		return cellValue;
	}
}
