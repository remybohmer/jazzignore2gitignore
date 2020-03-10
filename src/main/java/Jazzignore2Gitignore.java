import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

/**
 * Jazzignore2gitignore - Converts all '.jazzignore' files in a RTC working tree to '.gitignore' files,
 * The original .jazzignore files remain in the workarea, and in each directory a corresponding .gitignore
 * will be created.
 */
public class Jazzignore2Gitignore {

    private static final String[] JAZZIGNORE_FILES = new String[]{"jazzignore"};

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: Run me with the directory of the RTC workarea as argument");
            System.exit(1);
        } else {
            File jazzRoot = new File(args[0]);

            FileUtils.listFiles(jazzRoot, JAZZIGNORE_FILES , true).forEach((file) -> {
                try {
                    File parent = file.getParentFile();
                    if (parent == null) {
                        parent = file;
                    }
                    File gitignore = new File(parent.getAbsolutePath(), ".gitignore");

                    System.out.println("Converting '" + file + "'to '" + gitignore + "'");
                    List<String> ignoredPatterns = JazzignoreTranslator.toGitignore(file);

                    gitignore.createNewFile();
                    FileUtils.writeLines(gitignore, ignoredPatterns, false);
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            File gitignore = new File(jazzRoot, ".gitignore");
            FileUtils.writeStringToFile(gitignore, "/.jazz5/" + System.lineSeparator(), Charset.defaultCharset(), false);
            FileUtils.writeStringToFile(gitignore, "/.jazzShed/" + System.lineSeparator(), Charset.defaultCharset(), true);
        }
    }
}
