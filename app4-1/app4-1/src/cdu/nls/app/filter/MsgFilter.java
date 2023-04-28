package cdu.nls.app.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "msgFilter", urlPatterns = "/addMsg",
        initParams = @WebInitParam(name = "filePath",
                value = "WEB-INF/stopWords.txt"))
public class MsgFilter implements Filter {
    List<String> stopwords = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String path = filterConfig.getInitParameter("filePath");
        try {

            String realPath = filterConfig.getServletContext().getRealPath(path);
            File file = new File(realPath);
            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    if (line.length() > 0) {
                        stopwords.add(line.trim());
                    }
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
                fileReader.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        filterChain.doFilter(new MyMsgReqWrapper(req, stopwords), servletResponse);
    }

}
