package com.icesi.discografia.servlet;

import com.icesi.discografia.model.Artist;
import com.icesi.discografia.service.ArtistService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ArtistCreateServlet extends HttpServlet {

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
        req.getRequestDispatcher("/WEB-INF/views/artistCreate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name        = req.getParameter("name");
        String nationality = req.getParameter("nationality");

        if (name == null || name.trim().isEmpty()
                || nationality == null || nationality.trim().isEmpty()) {
            req.setAttribute("error", "Nombre y nacionalidad son requeridos");
            req.getRequestDispatcher("/WEB-INF/views/artistCreate.jsp").forward(req, resp);
            return;
        }

        artistService.create(new Artist(name.trim(), nationality.trim()));
        resp.sendRedirect(req.getContextPath() + "/artists?success=1");
    }
}
