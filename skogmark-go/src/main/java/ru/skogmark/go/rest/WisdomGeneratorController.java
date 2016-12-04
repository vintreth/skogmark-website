package ru.skogmark.go.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.skogmark.go.domain.Wisdom;
import ru.skogmark.go.service.WisdomService;

/**
 * @author svip
 *         2016-11-26
 */
@Controller
@Scope("session")
@RequestMapping("/rest/gen")
public class WisdomGeneratorController {
    private final static Integer MAX_WISDOM_COUNT = 1000;

    private WisdomService wisdomService;

    @Autowired
    public void setWisdomService(WisdomService wisdomService) {
        this.wisdomService = wisdomService;
    }

    @RequestMapping(value = "/wisdom", method = RequestMethod.GET)
    public @ResponseBody Wisdom randomMessage() {
        return wisdomService.getRandomWisdom();
    }

    @RequestMapping(value = "/wisdoms", method = RequestMethod.GET)
    public @ResponseBody Wisdom[] randomMessages(@RequestParam(value = "count", required = false) Integer count) {
        if (null == count || count > MAX_WISDOM_COUNT) {
            count = MAX_WISDOM_COUNT;
        }
        return wisdomService.getRandomWisdoms(count);
    }
}
