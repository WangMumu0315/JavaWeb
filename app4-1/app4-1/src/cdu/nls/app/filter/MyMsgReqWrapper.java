package cdu.nls.app.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.List;

public class MyMsgReqWrapper extends HttpServletRequestWrapper {
    List<String> stopwords = null;

    public MyMsgReqWrapper(HttpServletRequest request, List<String> stopwords) {
        super(request);
        this.stopwords = stopwords;
    }

    @Override
    public String getParameter(String name) {

        String text = getRequest().getParameter(name);

        if (name.equals("subject") ||name.equals("content") || name.equals("reply")) {
            if (text != null) {
                System.out.println("原始text: " + text);
                text = text.replaceAll(" ", "&nbsp;");
                text = text.replaceAll("\n", "<br>");
                for (String s : stopwords) {
                    text = text.replaceAll(s, "*");
                }
                System.out.println("过滤后：" + text);
            }
        }

        return text;
    }
}
