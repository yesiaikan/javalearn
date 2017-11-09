package sys.util;

import sys.vo.PageObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by zhangxiaolei05 on 2015/8/25.
 */
public class Pager extends TagSupport{
    private String path;
    private String form;
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
        PageObject pageObject = (PageObject)request.getAttribute("pageObject");
        try {
            out.println("<script type=\"text/javascript\" src=\"/js/jquery-1.11.3.min.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"/js/jquery.pager.js\"></script>");
            out.println("<script type=\"text/javascript\">");
            out.println("$(function(){");
            out.println("$(\"#pager\").pager({ pagenumber: "+pageObject.getCurrentPage()+", pagecount: "+pageObject.getTotalPage()+", buttonClickCallback: PageClick });");
            out.println("});");
            out.println("PageClick = function(pageclickednumber) {");
            if(null != form && form.length() != 0){
                out.println("forward(\"/admin/left/"+path+"/\"+pageclickednumber,'"+form+"');");
            }else{
                out.println("forward(\"/admin/left/"+path+"/\"+pageclickednumber);");
            }

            out.println("$(\"#pager\").pager({ pagenumber: pageclickednumber, pagecount: "+pageObject.getTotalPage()+", buttonClickCallback: PageClick });");
            out.println("}");
            out.println("</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }
}
