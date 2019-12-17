/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.filters;

import huudn.dtos.RoleDTO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ngochuu
 */
public class RoleFilter implements Filter {

    private static final boolean debug = true;

    private final List<String> user;
    private final List<String> admin;
    private final List<String> quest;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public RoleFilter() {
        quest = new ArrayList<>();
        quest.add("");
        quest.add("error.jsp");
        quest.add("index.jsp");
        quest.add("login.jsp");
        quest.add("productDetails.jsp");
        quest.add("register.jsp");
        quest.add("footer.jsp");
        quest.add("header.jsp");
        quest.add("productList.jsp");
        quest.add("search.jsp");
        quest.add("slideShow.jsp");

        quest.add("MainController");
        quest.add("SearchProductController");
        quest.add("LoginController");
        quest.add("RegisterController");
        quest.add("ShowProductDetailsController");
        quest.add("SearchProduct");
        quest.add("Login");
        quest.add("Register");
        quest.add("ShowProductDetails");

        user = new ArrayList<>();
        user.add("");
        user.add("error.jsp");
        user.add("index.jsp");
        user.add("cart.jsp");
        user.add("info.jsp");
        user.add("password.jsp");
        user.add("productDetails.jsp");
        user.add("userHistory.jsp");
        user.add("userHistoryDetails.jsp");

        user.add("MainController");
        user.add("SearchProductController");
        user.add("ShowProductDetailsController");
        user.add("AddToCartController");
        user.add("ChangeCityController");
        user.add("ChangePasswordController");
        user.add("DeleteFromCartController");
        user.add("LogoutController");
        user.add("PaymentController");
        user.add("ShowCartController");
        user.add("ShowInfoController");
        user.add("UpdateInfoController");
        user.add("UserShowHistoryController");
        user.add("UserShowHistoryDetailsController");
        
        user.add("SearchProduct");
        user.add("ShowProductDetails");
        user.add("AddToCart");
        user.add("ChangeCity");
        user.add("ChangePassword");
        user.add("DeleteFromCart");
        user.add("Logout");
        user.add("Payment");
        user.add("ShowCart");
        user.add("ShowInfo");
        user.add("UpdateInfo");
        user.add("UserShowHistory");
        user.add("UserShowHistoryDetails");

        admin = new ArrayList<>();
        admin.add("");
        admin.add("error.jsp");
        admin.add("index.jsp");
        admin.add("info.jsp");
        admin.add("manageHistoryUserDetails.jsp");
        admin.add("managePost.jsp");
        admin.add("manageProduct.jsp");
        admin.add("manageShowUser.jsp");
        admin.add("manageUpdateProduct.jsp");
        admin.add("manageUser.jsp");
        admin.add("password.jsp");
        admin.add("post.jsp");
        admin.add("productDetails.jsp");

        admin.add("AdminChangeCityController");
        admin.add("AdminInsertProductController");
        admin.add("AdminSearchProductController");
        admin.add("AdminSearchUserController");
        admin.add("AdminShowInfoUserController");
        admin.add("AdminShowOrderDetailsController");
        admin.add("AdminShowProductDetailsController");
        admin.add("AdminUpdateProductChangeCity");
        admin.add("AdminUpdateProductController");
        admin.add("AdminUpdateUserController");
        admin.add("ChangeCityController");
        admin.add("ChangePasswordController");
        admin.add("DeleteProductController");
        admin.add("LogoutController");
        admin.add("MainController");
        admin.add("SearchProductController");
        admin.add("ShowInfoController");
        admin.add("ShowProductDetailsController");
        admin.add("UpdateInfoController");
        
        admin.add("AdminChangeCity");
        admin.add("AdminInsertProduct");
        admin.add("AdminSearchProduct");
        admin.add("AdminSearchUser");
        admin.add("AdminShowInfoUser");
        admin.add("AdminShowOrderDetails");
        admin.add("AdminShowProduct");
        admin.add("AdminUpdateProduct");
        admin.add("AdminUpdateUser");
        admin.add("ChangeCity");
        admin.add("ChangePassword");
        admin.add("DeleteProduct");
        admin.add("Logout");
        admin.add("SearchProduct");
        admin.add("ShowInfo");
        admin.add("ShowProductDetails");
        admin.add("UpdateInfo");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("RoleFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("RoleFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        log("Uri: " + uri);
        if ((uri.contains(".js") || uri.contains(".css") || uri.contains(".jpg") || uri.contains(".png") || uri.contains(".gif") || uri.contains(".jpeg")) && !uri.contains(".jsp")) {
            chain.doFilter(request, response);
        } else {
            int index = uri.lastIndexOf("/");
            String resource = uri.substring(index + 1);
            HttpSession session = req.getSession(false);
            log("AuthenFilter: " + resource);
            String action = req.getParameter("action");
            if(action == null) {
                action = "";
            }
            if ((session == null) || session.getAttribute("ROLE") == null) {
                if (quest.contains(resource) && quest.contains(action)) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect("login.jsp");
                }
            } else {
                String role = ((RoleDTO) session.getAttribute("ROLE")).getRoleName();
                if (role.equals("admin")) {
                    if (admin.contains(resource) && admin.contains(action)) {
                        chain.doFilter(request, response);
                    } else {
                        req.getRequestDispatcher("SearchProductController").forward(request, response);
                    }
                } else if (role.equals("user")) {
                    if (user.contains(resource) && user.contains(action)) {
                        chain.doFilter(request, response);
                    } else {
                        req.getRequestDispatcher("SearchProductController").forward(request, response);
                    }
                } else {
                    req.getRequestDispatcher("SearchProductController").forward(request, response);
                }
            }

        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("RoleFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("RoleFilter()");
        }
        StringBuffer sb = new StringBuffer("RoleFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
