package ru.skogmark.go.service;

import org.springframework.stereotype.Service;
import ru.skogmark.go.domain.Wisdom;

/**
 * @author svip
 *         2016-11-26
 */
@Service
public interface WisdomService {
    Wisdom getRandomWisdom();

    Wisdom[] getRandomWisdoms(int count);
}
