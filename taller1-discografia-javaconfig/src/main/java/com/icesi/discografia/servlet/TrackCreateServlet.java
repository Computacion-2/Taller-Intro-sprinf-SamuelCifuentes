package com.icesi.discografia.servlet;

import com.icesi.discografia.model.Track;
import com.icesi.discografia.service.ArtistService;
import com.icesi.discografia.service.TrackService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrackCreateServlet extends HttpServlet {

    private TrackService trackService;
    private ArtistService artistService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext ctx = WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());
        this.trackService  = ctx.getBean(TrackService.class);
        this.artistService = ctx.getBean(ArtistService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("artists", artistService.findAll());
        req.getRequestDispatcher("/WEB-INF/views/trackCreate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String   title       = req.getParameter("title");
        String   genre       = req.getParameter("genre");
        String   durationStr = req.getParameter("duration");
        String   albumTitle  = req.getParameter("albumTitle");
        String[] idStrs      = req.getParameterValues("artistIds");

        if (title == null || title.trim().isEmpty()
                || idStrs == null || idStrs.length == 0) {
            req.setAttribute("error", "Título y al menos un artista son requeridos");
            req.setAttribute("artists", artistService.findAll());
            req.getRequestDispatcher("/WEB-INF/views/trackCreate.jsp").forward(req, resp);
            return;
        }

        try {
            int duration = Integer.parseInt(durationStr.trim());
            List<Long> artistIds = new ArrayList<>();
            for (String s : idStrs) {
                artistIds.add(Long.parseLong(s.trim()));
            }
            Track track = new Track(title.trim(),
                    genre != null ? genre.trim() : "",
                    duration,
                    albumTitle != null ? albumTitle.trim() : "");
            trackService.create(track, artistIds);
            resp.sendRedirect(req.getContextPath() + "/tracks?success=1");
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Duración o ID de artista inválido");
            req.setAttribute("artists", artistService.findAll());
            req.getRequestDispatcher("/WEB-INF/views/trackCreate.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("artists", artistService.findAll());
            req.getRequestDispatcher("/WEB-INF/views/trackCreate.jsp").forward(req, resp);
        }
    }
}
