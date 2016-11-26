package ru.skogmark.go.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.domain.Wisdom;
import ru.skogmark.go.generator.WisdomGenerator;
import ru.skogmark.go.service.WisdomService;

/**
 * @author svip
 *         2016-11-26
 */
@Component
public class WisdomServiceImpl implements WisdomService {
    private WisdomGenerator wisdomGenerator;

    @Autowired
    public void setWisdomGenerator(WisdomGenerator wisdomGenerator) {
        this.wisdomGenerator = wisdomGenerator;
    }

    @Override
    public Wisdom getRandomMessage() {
        return wisdomGenerator.generate();
    }
}
