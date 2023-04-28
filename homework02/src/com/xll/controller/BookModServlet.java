package com.xll.controller;

import com.xll.model.Book;
import com.xll.service.BookService;
import com.xll.service.impl.BookServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author 谢琳琳
 * @version 1.0
 */
@WebServlet("/admin/book/mod")
public class BookModServlet extends HttpServlet {


    BookService bookService = new BookServiceImpl();


    @Override


    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置图像文件保存路径
        String path = "/cover";
        //获取图像文件保存路径对应的真实物理地址
        String saveDir = req.getServletContext().getRealPath(path);
        File dir = new File(saveDir);
        if (!dir.exists()) {
            dir.mkdir();

        }

        //使用commons-fileupload组件分别处理表单域和文件
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //客户端传递的新添加的图书信息将封装在下面的book对象中
        Book book = null;
        try {
            List<FileItem> items = upload.parseRequest(req);
            if (!items.isEmpty()) {
                book = new Book();

            }
            Iterator<FileItem> iterator = items.iterator();
            while (iterator.hasNext()) {
                FileItem item = iterator.next();
                if (item.isFormField()) {
                    if (item.getFieldName().equals("id")) {
                        book.setId(Integer.parseInt(item.getString()));

                    }
                    if (item.getFieldName().equals("title")) {
                        book.setTitle(new String(item.getString().getBytes("iso-8859-1"), "utf-8"));

                    }
                    if (item.getFieldName().equals("author")) {
                        book.setAuthor(new String(item.getString().getBytes("iso-8859-1"), "utf-8"));

                    }
                    if (item.getFieldName().equals("press")) {
                        book.setPress(new String(item.getString().getBytes("iso-8859-1"), "utf-8"));

                    }
                    if (item.getFieldName().equals("price")) {
                        book.setPrice(new String(item.getString().getBytes("iso-8859-1"), "utf-8"));

                    }
                    if (item.getFieldName().equals("sale")) {
                        book.setSale(Integer.parseInt(item.getString()));

                    }
                    if (item.getFieldName().equals("stock")) {
                        book.setStock(Integer.parseInt(item.getString()));

                    }
                    if (item.getFieldName().equals("info")) {
                        book.setInfo(new String(item.getString().getBytes("iso-8859-1"), "utf-8"));

                    }
                    if (item.getFieldName().equals("publishDate")) {
                        book.setPublishDate(new String(item.getString().getBytes("iso-8859-1"), "utf-8"));

                    }
                    if (item.getFieldName().equals("marketDate")) {
                        book.setMarketDate(new String(item.getString().getBytes("iso-8859-1"), "utf-8"));

                    }
                    if (item.getFieldName().equals("coverUrl")) {
                        book.setCoverUrl(item.getString());

                    }


                } else {
                    if (item.getSize() > 0) {
                        String fileName = item.getName();
                        //获取待上传文件的扩展名
                        String ext = item.getName().substring(fileName.lastIndexOf("."));
                        //使用当前时间做为文件的新文件名
                        fileName = new Date().getTime() + ext;
                        File file = new File(saveDir + "//" + fileName);
                        //在服务器端保存图像，注意在out目录下的cover中查看，而不是源代码的cover目录中查看上传结果
                        item.write(file);
                        //保存图像文件的相对路径
                        book.setCoverUrl(req.getContextPath() + path + "/" + fileName);

                    }

                }

            }

        } catch (Exception e) {
            System.out.println("Servlet 修改图书出错：" + e.getMessage());

        }

        if (bookService.mod(book)) {
            //修改成功，重定向至列表界面
            resp.sendRedirect("list");

        } else {
            //修改失败，将请求转发至图书修改界面
            req.setAttribute("book", book);
            req.getRequestDispatcher("mod.jsp").forward(req, resp);

        }
    }
}
