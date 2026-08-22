package com.icesi.discografia.servlet;

import com.icesi.discografia.service.ArtistService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ArtistListServlet extends HttpServlet {

    private ArtistService artistService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext ctx = WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());
        this.artistService = ctx.getBean(ArtistService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("artists", artistService.findAll());
        req.getRequestDispatcher("/WEB-INF/views/artistList.jsp").forward(req, resp);
    }
}
