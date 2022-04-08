package ru.otus.taskChecker.service;

import org.springframework.stereotype.Service;
import ru.otus.taskChecker.model.SolutionCheck;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TestLogChecker implements LogChecker {
    @Override
    public SolutionCheck checkLog(String log) {
     SolutionCheck solutionCheck = new SolutionCheck();
        Pattern pattern = Pattern.compile("<<< FAILURE!.*<<< FAILURE!(.*)\\n{2}", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(log);
        while (matcher.find()) {
            solutionCheck.addError(matcher.group(1));
        }
        if (solutionCheck.getErrorLog().size()==0) {
            solutionCheck.setCorrect(true);}
        return solutionCheck;
    }
}
