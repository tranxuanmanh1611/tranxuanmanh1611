package controller.household_manager;

import Service.HouseholdServiceImpl;
import model.HouseHold;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "householdServlet", value = "/house")
public class HouseholdServlet extends HttpServlet {
    HouseholdServiceImpl manager = new HouseholdServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "edit":
                showEditForm(request,response);
                break;
            default:
                showList(request,response);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("edit",request.getParameter("id"));
        int pageOrder = 1;
        if (request.getParameter("pageOrder")!=null){
            pageOrder = Integer.parseInt(request.getParameter("pageOrder"));
        }
        request.setAttribute("pageOrder",pageOrder);
        request.setAttribute("numberOfPage",manager.getNumberOfPages(3));

        request.setAttribute("houseList",manager.getAll((pageOrder-1)*3));
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request,response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageOrder = 1;
       if (request.getParameter("pageOrder")!=null){
           pageOrder = Integer.parseInt(request.getParameter("pageOrder"));
       }
       request.setAttribute("pageOrder",pageOrder);
       request.setAttribute("numberOfPage",manager.getNumberOfPages(3));

        request.setAttribute("houseList",manager.getAll((pageOrder-1)*3));
       RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
       dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "edit":
                editHousehold(request,response);
                break;
        }
    }

    private void editHousehold(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String owner = request.getParameter("owner");
        String createDate = request.getParameter("createDate");
        String address = request.getParameter("address");
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        if (manager.ownerValidate(owner)){
            manager.editHouseHold(id,owner,createDate,address);
            request.setAttribute("alert","Successfully updated!");
            int pageOrder = 1;
            if (request.getParameter("pageOrder")!=null){
                pageOrder = Integer.parseInt(request.getParameter("pageOrder"));
            }
            request.setAttribute("pageOrder",pageOrder);
            request.setAttribute("houseList",manager.getAll((pageOrder-1)*3));
            request.setAttribute("numberOfPage",manager.getNumberOfPages(3));
            dispatcher.forward(request,response);
        }
        request.setAttribute("error","The owner's name is error, update fail");
        int pageOrder = 1;
        if (request.getParameter("pageOrder")!=null){
            pageOrder = Integer.parseInt(request.getParameter("pageOrder"));
        }
        request.setAttribute("pageOrder",pageOrder);
        request.setAttribute("houseList",manager.getAll((pageOrder-1)*3));
        request.setAttribute("numberOfPage",manager.getNumberOfPages(3));
        dispatcher.forward(request,response);
    }
}
