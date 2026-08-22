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
import java.util.Optional;

public class ArtistSearchServlet extends HttpServlet {

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
        req.getRequestDispatcher("/WEB-INF/views/artistSearch.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");

        if (name == null || name.trim().isEmpty()) {
            req.setAttribute("error", "Por favor ingrese un nombre");
            req.getRequestDispatcher("/WEB-INF/views/artistSearch.jsp").forward(req, resp);
            return;
        }

        Optional<Artist> opt = artistService.findByNameWithTracks(name.trim());
        if (opt.isPresent()) {
            req.setAttribute("artist", opt.get());
            req.getRequestDispatcher("/WEB-INF/views/artistSearchResult.jsp").forward(req, resp);
        } else {
            req.setAttribute("searchName", name.trim());
            req.setAttribute("notFound", Boolean.TRUE);
            req.getRequestDispatcher("/WEB-INF/views/artistSearch.jsp").forward(req, resp);
        }
    }
}
