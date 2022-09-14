package com.urase.webapp.web;

import com.urase.webapp.Config;
import com.urase.webapp.model.*;
import com.urase.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init() throws ServletException {
        super.init();
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
                r = storage.get(uuid);
                break;
            case "edit":
                r = storage.get(uuid);
                for (SectionType section : SectionType.values()) {
                    switch (section) {
                        case PERSONAL:
                        case OBJECTIVE:
                            if (r.getSection(section) == null) {
                                r.saveSection(section, SimpleSection.EMPTY);
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (r.getSection(section) == null) {
                                r.saveSection(section, ListSimpleSection.EMPTY);
                            }
                            break;
                        case EDUCATION:
                        case EXPERIENCE:
                            if (r.getSection(section) == null) {
                                r.saveSection(section, OrganizationSection.EMPTY);
                            }
                            break;
                    }
                }
                break;
            case "add":
                r = Resume.EMPTY_RESUME;
                break;
            default:
                throw new IllegalArgumentException("Action" + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        if (fullName == null  || fullName.trim().length() == 0) {
            response.sendRedirect("resume");
            return;
        }
        boolean isInStorage = (uuid == null || uuid.length() == 0);
        Resume r;
        if (isInStorage) {
            r = new Resume(fullName);
        } else {
            r = storage.get(uuid);
            r.setFullName(fullName);
        }
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.saveContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        r.saveSection(type, new SimpleSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSimpleSection listSimpleSection = new ListSimpleSection();
                        new ArrayList<>(Arrays.asList(value.split("\n")))
                                .forEach(listSimpleSection::addTextToList);
                        r.saveSection(type, listSimpleSection);
                        break;
                }
            } else {
                r.getSections().remove(type);
            }
        }
        if (isInStorage) {
            storage.save(r);
        } else {
            storage.update(r);
        }
        response.sendRedirect("resume");
    }
}
