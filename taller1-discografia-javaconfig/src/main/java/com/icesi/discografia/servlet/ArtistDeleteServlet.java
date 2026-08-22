package com.icesi.discografia.servlet;

import com.icesi.discografia.service.ArtistService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ArtistDeleteServlet extends HttpServlet {

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
        req.getRequestDispatcher("/WEB-INF/views/artistDelete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idParam = req.getParameter("id");
        try {
            Long id      = Long.parseLong(idParam.trim());
            boolean done = artistService.deleteById(id);
            if (done) {
                req.setAttribute("success", "Artista con ID " + id + " eliminado exitosamente.");
            } else {
                req.setAttribute("error", "No se encontró artista con ID " + id + ".");
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "ID inválido: " + idParam);
        }
        req.getRequestDispatcher("/WEB-INF/views/artistDelete.jsp").forward(req, resp);
    }
}
