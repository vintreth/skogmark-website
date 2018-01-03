package ru.skogmark.go.gen.core.template;

import ru.skogmark.go.gen.core.dao.SentenceDao;
import ru.skogmark.go.gen.core.domain.Sentence;
import ru.skogmark.go.gen.core.domain.SentenceRole;

import java.util.Optional;

class SentenceTemplatePart implements TemplatePart {
    private final SentenceRole sentenceRole;
    private final SentenceDao sentenceDao;

    public SentenceTemplatePart(SentenceRole sentenceRole, SentenceDao sentenceDao) {
        this.sentenceRole = sentenceRole;
        this.sentenceDao = sentenceDao;
    }

    @Override
    public Optional<String> getContent() {
        return Optional.ofNullable(sentenceDao.getRandomSentenceByRole(sentenceRole)).map(Sentence::getContent);
    }

    @Override
    public String getCode() {
        return sentenceRole.toString();
    }

    @Override
    public String toString() {
        return "SentenceTemplatePart{" +
                "sentenceRole=" + sentenceRole +
                '}';
    }
}
