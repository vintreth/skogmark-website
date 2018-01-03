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
        templates = ImmutableList.of(
                // todo временые шаблоны, пока не перехерачили фразы с нулями
//                templateBuilder.none().comma().none().build(),
//                templateBuilder.none().complex().none().build(),
//                templateBuilder.none().complex().none().comma().adverbial().build(),
//                templateBuilder.none().compound().secondary().build(),

                templateBuilder.single().build(),
//                templateBuilder.none().empty().main().build(),
//                templateBuilder.none().empty().main().empty().adverbial().build(),
//                templateBuilder.none().empty().main().compound().secondary().build(),
                templateBuilder.main().comma().main().build(),
                templateBuilder.main().complex().main().build(),
                templateBuilder.main().complex().main().empty().adverbial().build(),
                templateBuilder.main().compound().secondary().build(),
                templateBuilder.main().compound().secondary().empty().adverbial().build(),
                templateBuilder.adverbial().empty().main().build(),
                templateBuilder.adverbial().empty().main().compound().secondary().build());
                // todo списки
                // todo слева что-то справа что-то
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
