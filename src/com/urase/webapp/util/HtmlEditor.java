package com.urase.webapp.util;

import com.urase.webapp.model.AbstractSection;
import com.urase.webapp.model.ListSimpleSection;
import com.urase.webapp.model.Resume;
import com.urase.webapp.model.SectionType;

import java.util.Map;

public class HtmlEditor {
    public static String toHtml(Resume resume) {
        StringBuilder html = new StringBuilder();
        for (Map.Entry<SectionType, AbstractSection> map : resume.getSections().entrySet()) {
            SectionType section = map.getKey();
            AbstractSection abstractSection = map.getValue();
            switch (section) {
                case OBJECTIVE:
                    html.append("<h2><a>")
                            .append(section.getTitle())
                            .append("</a></h2>")
                            .append("\n")
                            .append("<input type=\"text\" name=\"")
                            .append(map.getKey())
                            .append("\"")
                            .append(" size=75 value=\"")
                            .append(abstractSection)
                            .append("\">")
                            .append("<br/>")
                            .append("\n");
                    break;
                case PERSONAL:
                    html.append("<h2><a>")
                            .append(section.getTitle())
                            .append("</a></h2>")
                            .append("\n")
                            .append("<textarea name=\"")
                            .append(map.getKey())
                            .append("\"")
                            .append(" cols=75 rows=5>")
                            .append(abstractSection)
                            .append("</textarea>")
                            .append("<br/>")
                            .append("\n");
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    ListSimpleSection listSimpleSection = (ListSimpleSection) abstractSection;
                    StringBuilder description = new StringBuilder();
                    listSimpleSection.getItems().stream().map(text -> description.append(text).append("\n")).count();
                    html.append("<h2><a>")
                            .append(section.getTitle())
                            .append("\n")
                            .append("</a></h2>")
                            .append("<textarea name=\"")
                            .append(map.getKey())
                            .append("\"")
                            .append(" cols=75 rows=5>")
                            .append(description)
                            .append("</textarea>")
                            .append("<br/>")
                            .append("\n");
                    break;
            }
        }
        return html.toString();
    }
}
