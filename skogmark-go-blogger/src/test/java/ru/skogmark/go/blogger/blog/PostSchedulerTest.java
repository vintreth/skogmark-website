package ru.skogmark.go.blogger.blog;

/**
 * @author svip
 *         2016-12-17
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
//@ContextConfiguration(locations = {"classpath:beans.xml"})
public class PostSchedulerTest {
//    @Autowired
//    private BloggerSettings configuration;
//
//    @Test
//    public void testTheTimeToPost() throws Exception {
//        configure();
//        WisdomService wisdomService = getWisdomService();
//        PostScheduler postScheduler = new PostScheduler(getBlog(), configuration, wisdomService);
//        postScheduler.schedule();
//        verify(wisdomService, times(1)).getWisdom();
//    }
//
//    @Test
//    public void testNoRepeatPost() throws Exception {
//        configure();
//        WisdomService wisdomService = getWisdomService();
//        PostScheduler postScheduler = new PostScheduler(getBlog(), configuration, wisdomService);
//        postScheduler.schedule();
//        postScheduler.schedule();
//        verify(wisdomService, times(1)).getWisdom();
//    }
//
//    private void configure() {
//        int actualHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//        configuration.getPostSchedulerParams().getTimeTable().setTimes(
//            new Integer[] {actualHour, actualHour + 1, actualHour + 10});
//    }
//
//    private WisdomService getWisdomService() throws Exception {
//        WisdomService wisdomService = mock(WisdomService.class);
//        when(wisdomService.getWisdom()).thenReturn(getWisdom());
//
//        return wisdomService;
//    }
//
//    private Wisdom getWisdom() {
//        Wisdom wisdom = new Wisdom();
//        wisdom.setContent("Test wisdom");
//
//        return wisdom;
//    }
//
//    private Blog getBlog() {
//        return mock(Blog.class);
//    }
}