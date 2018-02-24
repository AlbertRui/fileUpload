package me.download.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/x-msdownload");

        String fileName = "文件下载.pptx";
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

        OutputStream out = response.getOutputStream();
        String pptFileName = "E:\\ITstudy\\atguigu\\Javaweb\\javaweb 课件\\12. 尚硅谷_JavaWEB_文件的上传和下载.pptx";

        InputStream in = new FileInputStream(pptFileName);

        byte[] buffer = new byte[1024];
        int len;

        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }

        in.close();
    }

}
