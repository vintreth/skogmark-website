package ru.skogmark.go.gen.core.template;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TemplateProvider {
    private final List<Template> templates;

    @Autowired
    public TemplateProvider(TemplateBuilder templateBuilder) {
        templates = ImmutableList.of(templateBuilder.none().comma().none().build(),
                templateBuilder.none().complex().none().build(),
                templateBuilder.none().complex().none().comma().adverbial().build(),
                templateBuilder.none().compound().secondary().build(),
                templateBuilder.main().comma().main().build(),
                templateBuilder.main().complex().main().build(),
                templateBuilder.main().complex().main().comma().adverbial().build(),
                templateBuilder.main().compound().secondary().build(),
                templateBuilder.main().compound().secondary().comma().adverbial().build());
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public Optional<Template> getTemplate(int index) {
        if (index >= templates.size()) {
            return Optional.empty();
        }
        return Optional.of(templates.get(index));
    }
}
