package com.urase.webapp.util;

import com.urase.webapp.exception.StorageException;
import com.urase.webapp.model.*;

public class HtmlConverter {
    public static String toHtml(SectionType sectionType, AbstractSection abstractSection) {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                SimpleSection simpleSection = (SimpleSection) abstractSection;
                return  "<textarea style=\"overflow:auto;resize:none\" rows=\"3\" cols=\"60\">" +
                        simpleSection.getText() + "</textarea>";
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                ListSimpleSection listSimpleSection = (ListSimpleSection) abstractSection;
                StringBuilder description = new StringBuilder();
                listSimpleSection.getItems().stream().map(text -> description.append(text).append("&#13&#10;")).count();
                return  "<textarea style=\"overflow:auto;resize:none\" rows=\"3\" cols=\"60\">" + description + "</textarea>";
            case EDUCATION:
            case EXPERIENCE:
                OrganizationSection organizationSection = (OrganizationSection) abstractSection;
                StringBuilder sectionHtml = new StringBuilder();
                for(Organization org : organizationSection.getOrganizations()) {
                    sectionHtml.append(toLink(org.getLinkEmployer(), org.getNameEmployer()));
                    for(Period per : org.getPeriods()) {
                        sectionHtml.append("<textarea style=\"overflow:auto;resize:none\" rows=\"3\" cols=\"60\">")
                                .append(per.getStartDate())
                                .append(" - ")
                                .append(per.getEndDate())
                                .append(" : ")
                                .append(per.getTitle())
                                .append("&#13&#10;")
                                .append(per.getDescription())
                                .append("</textarea><br/>");
                    }
                }
                return sectionHtml.toString();
            default:
                throw new StorageException("Unknown type of section in database", null);
        }
    }

    private static String toLink(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a><br/>";
    }
}
