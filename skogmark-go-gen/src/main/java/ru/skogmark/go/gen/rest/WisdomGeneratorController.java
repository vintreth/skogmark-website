package ru.skogmark.go.gen.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skogmark.go.api.Wisdom;
import ru.skogmark.go.gen.core.OldDumbWisdomGenerator;
import ru.skogmark.go.gen.core.WisdomGenerator;

/**
 * @author svip
 *         2016-11-26
 */
@RestController
@Scope("session")
@RequestMapping("/rest/gen")
public class WisdomGeneratorController {
    private static final Integer MAX_WISDOM_COUNT = 1000;
    private static final Logger log = Logger.getLogger(WisdomGeneratorController.class);

    private final OldDumbWisdomGenerator oldDumbWisdomGenerator;
    private final WisdomGenerator flexibleWisdomGenerator;

    @Autowired
    public WisdomGeneratorController(OldDumbWisdomGenerator oldDumbWisdomGenerator,
                                     @Qualifier("flexibleWisdomGenerator") WisdomGenerator flexibleWisdomGenerator) {
        this.oldDumbWisdomGenerator = oldDumbWisdomGenerator;
        this.flexibleWisdomGenerator = flexibleWisdomGenerator;
    }

    @RequestMapping(value = "/wisdom", method = RequestMethod.GET)
    public Wisdom randomMessage() {
        log.debug("Calling random message");
        return oldDumbWisdomGenerator.generateOne();
    }

    @RequestMapping(value = "/wisdom-advanced", method = RequestMethod.GET)
    public Wisdom randomMessageAdvanced() {
        log.debug("Calling random message advanced");
        return flexibleWisdomGenerator.generateOne();
    }

    @RequestMapping(value = "/wisdoms", method = RequestMethod.GET)
    public Wisdom[] randomMessages(@RequestParam(value = "count", required = false) Integer count) {
        log.debug("Calling random messages, count " + count);
        if (null == count || count > MAX_WISDOM_COUNT) {
            oldDumbWisdomGenerator.generateMany(MAX_WISDOM_COUNT, oldDumbWisdomGenerator::generateOne);
        }
        return oldDumbWisdomGenerator.generateMany(count, oldDumbWisdomGenerator::generateOne);
    }
}
