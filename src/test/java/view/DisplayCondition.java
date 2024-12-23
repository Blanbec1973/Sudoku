package view;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DisplayCondition implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        String display = System.getenv("DISPLAY");
        if (display == null || display.isEmpty()) {
            return ConditionEvaluationResult.disabled("No X11 DISPLAY variable set. Tests are disabled.");
        }
        return ConditionEvaluationResult.enabled("X11 DISPLAY variable is set. Tests are enabled.");
    }
}
