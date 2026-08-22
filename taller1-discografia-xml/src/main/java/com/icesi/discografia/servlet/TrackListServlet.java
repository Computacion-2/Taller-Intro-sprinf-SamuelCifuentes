package com.icesi.discografia.servlet;

import com.icesi.discografia.service.TrackService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TrackListServlet extends HttpServlet {

    private TrackService trackService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext ctx = WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());
        this.trackService = ctx.getBean(TrackService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("tracks", trackService.findAll());
        req.getRequestDispatcher("/WEB-INF/views/trackList.jsp").forward(req, resp);
    }
}
