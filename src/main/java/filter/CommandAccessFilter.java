package filter;

import constants.PageConstants;
import entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


import static java.util.Arrays.asList;

public class CommandAccessFilter implements Filter {
    private static Map<Role, List<String>> accessMap = new HashMap<Role,List<String>>();
    private static List<String> common = new ArrayList<>();
    private static List<String> noControl = new ArrayList<>();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        accessMap.put(Role.CLIENT,asList(filterConfig.getInitParameter("client")));
        accessMap.put(Role.ADMIN,asList(filterConfig.getInitParameter("admin")));
        accessMap.put(Role.MANAGER,asList(filterConfig.getInitParameter("manager")));
        noControl = asList(filterConfig.getInitParameter("noControl"));
        common = asList(filterConfig.getInitParameter("common"));

        for(String s:noControl){
            System.out.println(s);
        }
        List<String> test = accessMap.get(Role.ADMIN);
        System.out.println("Admin commands=====");
        for(String s:test){
            System.out.println(s);
        }
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            if(accessAllowed(servletRequest)){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                servletRequest.setAttribute("errorMsg","Unauthorised access");
                servletRequest.getRequestDispatcher(PageConstants.LOGIN_PAGE).forward(servletRequest,servletResponse);
            }
    }

    @Override
    public void destroy() {}

    private boolean accessAllowed(ServletRequest request){
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String commandName = request.getParameter("command");
        if(commandName == null|| commandName.isEmpty()){
            return true;
        }
        if(noControl.contains(commandName)){
            return true;
        }

        HttpSession session = httpRequest.getSession(false);
        if(session == null){
            return false;
        }
        String role = session.getAttribute("role").toString().trim();
        Role userRole = Role.valueOf(role);
        System.out.println(userRole);
        if(userRole == null){
            return false;
        }
        return accessMap.get(userRole).contains(commandName)||common.contains(commandName);

    }
    private List<String> asList(String str) {
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) list.add(st.nextToken());
        return list;
    }
}
