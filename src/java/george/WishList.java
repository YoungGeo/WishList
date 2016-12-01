
package george;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "WishList", urlPatterns =
{
    "/WishList" 
})
public class WishList extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // print out
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String json = null;
        // get cookie array
        Cookie cookie; // cookie to store wish items
        cookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null)
        {
            // find cookie
            for(int i = 0; i < cookies.length; ++i)
            {
                if(cookies[i].getName().equals("wishList"))
                {
                    // found it!
                    cookie = cookies[i];
                    break;
                }
            }
        }
        
        // get request params
        String wish = request.getParameter("wish");
        String clear = request.getParameter("clear");
        
        // if clear was set, expire cookie
        if(clear != null)
        {
            // expire the cookie immediately
            if(cookie != null)
            {
                cookie.setMaxAge(0);
                response.addCookie(cookie); // tell client the cookie is expired
                
            }
            
            // optional
            out.print("[]");
        }
        
        else
        {
            // tmp var to store cookie value
            String wishList = null;
            
            // if new wish item was set, append it to the current list
            if(wish != null && !wish.isEmpty())
            {
                // first time to add wish item to cookie, create one
                if(cookie == null)
                {
                    cookie = new Cookie("wishList", wish);                    
                    wishList = wish;
                }
                else
                {
                    // append new item to existing wish list
                    // first, get the previous cookie value
                    wishList = cookie.getValue();
                    // second, add new wish with delmiter(~)
                    wishList += "-" + wish;
                    cookie.setValue(wishList); // must update cookie value
                }
                
                // update cookie object
                cookie.setMaxAge(48 * 60 * 60); // 48 hours
                response.addCookie(cookie);
            }
            // no wish item, get the current wish list only
            else
            {
                if(cookie != null)
                {
                    // get the previous wish list
                    wishList = cookie.getValue();
                }
            }
        if(wishList == null)
            json = "[]"; // empty array
        else {
            //split and construct json array
            
            String x = "";
            String[] items = wishList.split("-");
            
            for(String item : items) {
               x += "\"" + item + "\" , ";
            }
            //making the code into the "adf", "asdf" format
            
            x = x.replaceAll(", $", "");
            json = "[" + x + "]";
            //adds the outer brackets for json.parse
            
            out.print(json);//prints json for the main.js to recieve
        }
        
        
        }
        
    }

}
