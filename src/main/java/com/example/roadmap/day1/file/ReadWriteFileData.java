//package com.example.roadmap.day1.file;
//
//import java.io.*;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.Iterator;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class ReadWriteFileData {
//
//        public static void main(String[] args)
//        {
//            // Try block to check for exceptions
//            // Reading file from local directory
//            try(FileInputStream file = new FileInputStream(
//                    new File("C:\\Users\\DELL\\Downloads\\roadmap\\Files\\StudentDetail.xlsx"))) {
//                // Create Workbook instance holding reference to
//                // .xlsx file
//                XSSFWorkbook workbook = new XSSFWorkbook(file);
//                // Blank workbook
//                XSSFWorkbook writeWorkbook = new XSSFWorkbook();
//                XSSFSheet studentDetail = writeWorkbook.createSheet("StudentDetail");
//
//                // Get first/desired sheet from the workbook
//                XSSFSheet sheet = workbook.getSheetAt(0);
//
//                // Iterate through each rows one by one
//                Iterator<Row> rowIterator = sheet.iterator();
//
//                // Till there is an element condition holds true
//                int rownum = 0;
//                while (rowIterator.hasNext()) {
//
//                    Row row = rowIterator.next();
//                    Row newRow = studentDetail.createRow(rownum++);
//
//                    // For each row, iterate through all the
//                    // columns
//                    Iterator<Cell> cellIterator
//                            = row.cellIterator();
//                    int cellnum = 0;
//                    while (cellIterator.hasNext()) {
//                        Cell cell = cellIterator.next();
//                        Cell cell1 = newRow.createCell(cellnum++);
//                        switch(cell.getCellType()){
//                            case NUMERIC:
//                                cell1.setCellValue(cell.getNumericCellValue());
//                                break;
//                            case STRING:
//                                cell1.setCellValue(cell.getStringCellValue());
//                                break;
//                        }
//                    }
//                }
//                // Try block to check for exceptions
//                try(FileOutputStream out = new FileOutputStream(
//                        new File("C:\\Users\\DELL\\Downloads\\roadmap\\Files\\StudentDetailOut"+new Date().getTime()+".xlsx"))) {
//                    // Writing the workbook
//                    writeWorkbook.write(out);
//                    System.out.println("Data written into new file");
//                }
//
//                // Catch block to handle exceptions
//                catch (FileNotFoundException  fnf) {
//                    System.out.println(Arrays.toString(fnf.getStackTrace()));
//                }
//                catch (IOException  ioException) {
//                    System.out.println(Arrays.toString(ioException.getStackTrace()));
//                }
//            }
//            // Catch block to handle exceptions
//            catch (FileNotFoundException  fnf) {
//                System.out.println(Arrays.toString(fnf.getStackTrace()));
//            }
//            catch (IOException ioException) {
//                System.out.println(Arrays.toString(ioException.getStackTrace()));
//            }
//        }
//    }
