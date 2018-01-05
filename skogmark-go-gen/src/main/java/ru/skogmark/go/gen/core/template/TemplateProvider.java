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
        templates = ImmutableList.of(//templateBuilder.single().build(),
                templateBuilder.none().empty().main().build(),
                templateBuilder.none().empty().main().empty().adverbial().build(),
                templateBuilder.none().empty().main().compound().secondary().build(),
                templateBuilder.main().empty().main().build(),
                templateBuilder.main().complex().main().build(),
                templateBuilder.main().complex().main().comma().none().build(),
                templateBuilder.main().complex().main().empty().adverbial().build(),
                templateBuilder.main().compound().secondary().build(),
                templateBuilder.main().compound().secondary().comma().none().build(),
                templateBuilder.main().compound().secondary().empty().adverbial().build(),
                templateBuilder.adverbial().empty().main().build(),
                templateBuilder.adverbial().empty().main().comma().none().build(),
                templateBuilder.adverbial().empty().main().compound().secondary().build());
        // todo списки
        // todo слева что-то справа что-то
        // todo буду там в качестве
        // todo метал в России
        // todo за 200 тысяч
        // todo я у сцены стою и думаю
        // todo 200 долларов
        // todo выходят на сцену
        // todo глубокая мысль
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
