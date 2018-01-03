package ru.skogmark.go.gen.core.template;

import ru.skogmark.go.gen.core.dao.ConjunctionDao;
import ru.skogmark.go.gen.core.domain.ConjunctionType;
import ru.skogmark.go.gen.core.domain.old.Conjunction;

import java.util.Optional;

class ConjunctionTemplatePart implements TemplatePart {
    private final ConjunctionType conjunctionType;
    private final ConjunctionDao conjunctionDao;

    public ConjunctionTemplatePart(ConjunctionType conjunctionType, ConjunctionDao conjunctionDao) {
        this.conjunctionType = conjunctionType;
        this.conjunctionDao = conjunctionDao;
    }

    @Override
    public Optional<String> getContent() {
        return Optional.ofNullable(conjunctionDao.getRandomConjunctionByType(conjunctionType))
                .map(Conjunction::getContent);
    }

    @Override
    public String getCode() {
        return conjunctionType.toString();
    }

    @Override
    public String toString() {
        return "ConjunctionTemplatePart{" +
                "conjunctionType=" + conjunctionType +
                '}';
    }
}
