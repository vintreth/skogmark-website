package ru.skogmark.go.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.skogmark.go.domain.Wisdom;
import ru.skogmark.go.generator.WisdomGenerator;

/**
 * @author svip
 *         2016-11-26
 */
@Controller
@Scope("session")
@RequestMapping("/rest/gen")
public class WisdomGeneratorController {
    private static final Integer MAX_WISDOM_COUNT = 1000;

    private static final Logger logger = Logger.getLogger(WisdomGeneratorController.class);

    private WisdomGenerator wisdomGenerator;

    @Autowired
    public WisdomGeneratorController(WisdomGenerator wisdomGenerator) {
        this.wisdomGenerator = wisdomGenerator;
    }

    @RequestMapping(value = "/wisdom", method = RequestMethod.GET)
    public @ResponseBody Wisdom randomMessage() {
        logger.debug("Calling random message");
        return wisdomGenerator.generateOne();
    }

    @RequestMapping(value = "/wisdom-advanced", method = RequestMethod.GET)
    public @ResponseBody Wisdom randomMessageAdvanced() {
        logger.debug("Calling random message advanced");
        return wisdomGenerator.generateOneAdvanced();
    }

    @RequestMapping(value = "/wisdoms", method = RequestMethod.GET)
    public @ResponseBody Wisdom[] randomMessages(@RequestParam(value = "count", required = false) Integer count) {
        logger.debug("Calling random messages, count " + count);
        if (null == count || count > MAX_WISDOM_COUNT) {
            wisdomGenerator.generateMany(MAX_WISDOM_COUNT, wisdomGenerator::generateOne);
        }
        return wisdomGenerator.generateMany(count, wisdomGenerator::generateOne);
    }
}
