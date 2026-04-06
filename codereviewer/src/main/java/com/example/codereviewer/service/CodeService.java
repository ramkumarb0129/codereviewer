
package com.example.codereviewer.service;

import com.example.codereviewer.model.CodeSubmission;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CodeService {

    private List<CodeSubmission> submissions = new ArrayList<>();
    private AtomicInteger counter = new AtomicInteger(1);

    public List<CodeSubmission> getAll() {
        return submissions;
    }

    public CodeSubmission getById(int id) {
        return submissions.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }


    public boolean addSubmission(String name, String language, String title, String code) {

        // 🚫 Prevent empty fields (extra safety)
        if (name == null || name.trim().isEmpty() ||
                title == null || title.trim().isEmpty() ||
                code == null || code.trim().isEmpty()) {
            return false;
        }

        // 🚫 Duplicate check
        if (isDuplicate(name, title)) {
            return false;
        }

        CodeSubmission sub = new CodeSubmission(counter.getAndIncrement(),
                name.trim(), language, title.trim(), code.trim());

        analyzeCode(sub);
        submissions.add(sub);
        return true;
    }
    public boolean isDuplicate(String name, String title) {
        return submissions.stream().anyMatch(s ->
                s.getDeveloperName().trim().equalsIgnoreCase(name.trim()) &&
                        s.getTitle().trim().equalsIgnoreCase(title.trim())
        );
    }



    public void updateSubmission(int id, String name, String language, String title, String code) {
        CodeSubmission sub = getById(id);
        if (sub != null) {
            sub.setDeveloperName(name);
            sub.setLanguage(language);
            sub.setTitle(title);
            sub.setCode(code);
            analyzeCode(sub);
        }
    }

    public void deleteSubmission(int id) {
        submissions.removeIf(s -> s.getId() == id);
    }

    // Basic Rule-based Analysis
    private void analyzeCode(CodeSubmission sub) {
        String code = sub.getCode();

        if (code.length() < 20) {
            sub.setReviewStatus("Poor");
            sub.setComments("Code too short");
        } else if (!code.contains(";")) {
            sub.setReviewStatus("Warning");
            sub.setComments("Missing semicolons");
        } else {
            sub.setReviewStatus("Good");
            sub.setComments("Looks fine");
        }
    }

}