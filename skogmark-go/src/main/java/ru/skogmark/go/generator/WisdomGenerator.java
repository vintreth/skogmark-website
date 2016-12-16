package ru.skogmark.go.generator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.skogmark.go.domain.Wisdom;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author svip
 *         2016-11-26
 */
@Component
@Scope("session")
public class WisdomGenerator {
    private static final String LOCAL_REPOSITORY = "local-wisdom.txt";
    private static final String LOCAL_REPOSITORY_ENCODING = "utf-8";

    private List<String> localRepository;

    public Wisdom generateOne() {
        if (null == localRepository) {
            loadLocalRepository();
        }
        int rand1 = new Random().nextInt(localRepository.size());
        int rand2 = new Random().nextInt(localRepository.size());
        Wisdom wisdom = new Wisdom();
        wisdom.setContent(localRepository.get(rand1) + " " + localRepository.get(rand2));

        return wisdom;
    }

    public Wisdom[] generateMany(int count) {
        Wisdom[] wisdoms = new Wisdom[count];
        for (int i = 0; i < count; i++) {
            wisdoms[i] = generateOne();
        }
        return wisdoms;
    }

    private void loadLocalRepository() {
        URL localRepositoryUrl = Thread.currentThread().getContextClassLoader().getResource(LOCAL_REPOSITORY);
        if (null == localRepositoryUrl) {
            throw new FailedGenerationException("Unable to find local repository file " + LOCAL_REPOSITORY);
        }

        try (InputStream inputStream = new FileInputStream(localRepositoryUrl.getFile())) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, LOCAL_REPOSITORY_ENCODING));
            localRepository = new ArrayList<>();
            String line;
            while (null != (line = reader.readLine())) {
                localRepository.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new FailedGenerationException("Unable to resolve local repository file " + LOCAL_REPOSITORY, e);
        } catch (IOException e) {
            throw new FailedGenerationException("Failure to read file" + LOCAL_REPOSITORY, e);
        }
    }
}
