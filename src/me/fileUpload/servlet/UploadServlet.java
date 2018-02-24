package me.fileUpload.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author AlbertRui
 * @create 2018-02-23 20:21
 */
public class UploadServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        //1.得到FileItem的集合items
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

// Set factory constraints
        factory.setSizeThreshold(1024*500);
        File tempDirectory = new File("D:\\tempDirectory");
        factory.setRepository(tempDirectory);

// Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

// Set overall request size constraint
        upload.setSizeMax(1024*1024*5);

// Parse the request
        try {
            List<FileItem> items = upload.parseRequest(request);
            //2.遍历items
            for (FileItem item:items) {
                //若是一个一般的表单域，则打印信息
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString();
                    System.out.println(name + ":" + value);
                } else {
                    //若是文件域，则把文件保存到d:\\files目录下
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    String contentType = item.getContentType();
                    long sizeInBytes = item.getSize();
                    System.out.println(fieldName);
                    System.out.println(fileName);
                    System.out.println(contentType);
                    System.out.println(sizeInBytes);

                    fileName = "d:\\files\\" + fileName;
                    System.out.println(fileName);
                    InputStream inStream = item.getInputStream();
                    OutputStream outStream = new FileOutputStream(fileName);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inStream.read()) != -1) {
                        outStream.write(buffer, 0 ,len);
                    }
                    outStream.close();
                    inStream.close();
                }
            }
        } catch (FileUploadException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }
}
