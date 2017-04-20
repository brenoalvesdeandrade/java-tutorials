package org.nklkarthi.shell.simple;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultPromptProvider;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimplePromptProvider extends DefaultPromptProvider {

    @Override
    public String getPrompt() {
        return "nklkarthi-shell>";
    }

    @Override
    public String getProviderName() {
        return "nklkarthi Prompt";
    }
}
