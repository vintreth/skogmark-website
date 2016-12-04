package ru.skogmark.go.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.skogmark.go.domain.Wisdom;
import ru.skogmark.go.generator.WisdomGenerator;
import ru.skogmark.go.service.WisdomService;

/**
 * @author svip
 *         2016-11-26
 */
@Component
@Scope("session")
public class WisdomServiceImpl implements WisdomService {
    private WisdomGenerator wisdomGenerator;

    @Autowired
    public void setWisdomGenerator(WisdomGenerator wisdomGenerator) {
        this.wisdomGenerator = wisdomGenerator;
    }

    @Override
    public Wisdom getRandomWisdom() {
        return wisdomGenerator.generateOne();
    }

    @Override
    public Wisdom[] getRandomWisdoms(int count) {
        return wisdomGenerator.generateMany(count);
    }
}
