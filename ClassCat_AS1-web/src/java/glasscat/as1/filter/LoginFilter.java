/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.filter;

import glasscat.as1.controller.LoginController;
import glasscat.as1.util.Constants;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * this filter is used to check the login session of the user
 * @author Li Xuekai<zerlzerl@163.com>
 */
@WebFilter(
    urlPatterns = {"/*"}
)
public class LoginFilter implements Filter{
    private String redirectPrefix;
    private static final Logger log = Logger.getLogger(LoginFilter.class.getName());
    @Inject
    private LoginController loginController; 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Loginfilter init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (!Constants.IS_ENABLE_LOGIN_FILTER) {
            // send request to other filters
            chain.doFilter(req, resp);
        } else {
            // set charset
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            
            HttpServletRequest request = (HttpServletRequest)req;
            HttpServletResponse response = (HttpServletResponse)resp;
            
            // direct to different pages
            String requestURI = request.getRequestURI();
            System.out.println(requestURI);
            
            // if request a login page, register page or error page, no need login
            if (requestURI.contains(Constants.LOGIN_PAGE) || 
                    requestURI.contains(Constants.REGISTER_PAGE) ||
                    requestURI.contains(Constants.ERROR_PAGE)) {
                // user requests a login page
                if (loginController.isLoggedIn()) {
                    // if user logged in, redirect user to index page
                    response.sendRedirect(this.getRedirectPrefix(request) + request.getContextPath() + "/" + Constants.INDEX_PAGE);
                } else {
                    // do nothing
                }
            } else if (requestURI.equals(request.getContextPath() + "/") ||
                    requestURI.contains(Constants.INDEX_PAGE) ||
                    requestURI.contains(Constants.ADMIN_PAGE) ||
                    requestURI.contains(Constants.RESULT_PAGE) ||
                    requestURI.contains(Constants.DETAIL_PAGE) ||
                    requestURI.contains(Constants.CART_PAGE)) { // other page needs login
                if (loginController.isLoggedIn()) {
                    // user logged in, while an additional permision check should be performed
                    
                } else {
                    // user
                    response.sendRedirect(this.getRedirectPrefix(request) + request.getContextPath() + "/" + Constants.LOGIN_PAGE);
                }
            }
            
            // other requests such as resource links can by pass
            System.out.println("Loginfilter doFilter");
            chain.doFilter(req, resp);
        }
        
    }

    @Override
    public void destroy() {
        System.out.println("Loginfilter destroy");
    }
    
    /**
     * method used to get redirect prefix
     * @param request
     * @return 
     */
    private String getRedirectPrefix(HttpServletRequest request) {
        if (this.redirectPrefix == null) {
            String url = request.getRequestURL().toString();
            String uri = request.getRequestURI();
            int offset = url.indexOf(uri);
            this.redirectPrefix = url.substring(0, offset);
            if (useHttps(request)) {
                log.log(Level.WARNING, "Changing request scheme to https.");
                this.redirectPrefix = this.redirectPrefix.replace("http:", "https:");
            }
        }

        return this.redirectPrefix;
    }
    
    private static boolean useHttps(HttpServletRequest request) {
        String protocolProperty = Constants.PROTOCOL;
        String protoHeader = request.getHeader("X-Forwarded-Proto");
        return request.isSecure() || 
                protoHeader != null && protoHeader.toLowerCase().equals("https") || 
                protocolProperty != null && protocolProperty.toLowerCase().equals("https");
    }
}
