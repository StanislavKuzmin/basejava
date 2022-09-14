package com.urase.webapp;

import com.urase.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestData {
    public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";
    public static final String FULL_NAME_1 = "Obi-Wan Kenobi";
    public static final String FULL_NAME_2 = "Dart Weider";
    public static final String FULL_NAME_3 = "Anakin Skywalker";
    public static final int EXPECTED_SIZE = 3;
    public static final int EMPTY_SIZE = 0;
    public static final int AFTER_DELETE_SIZE = 2;
    public static final ResumeTestData resumeTestData = new ResumeTestData();
    public static final List<Resume> RESUME_STORAGE = new ArrayList<>(Arrays.asList(resumeTestData.createResume(UUID_3, FULL_NAME_3),
            resumeTestData.createResume(UUID_2, FULL_NAME_2), resumeTestData.createResume(UUID_1, FULL_NAME_1)));
    public static final List<Resume> ONE_DELETE_RESUME_STORAGE = new ArrayList<>(Arrays.asList(resumeTestData.createResume(UUID_3, FULL_NAME_3),
            resumeTestData.createResume(UUID_2, FULL_NAME_2)));
    public static final List<Resume> EMPTY_STORAGE = new ArrayList<>();
    public static final Resume RESUME = resumeTestData.createResume(UUID_1, FULL_NAME_1);
}
