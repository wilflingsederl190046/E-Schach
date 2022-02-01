import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleFileManager {
    public static List<ElectronicArticle> readStockFile(String filename) throws IOException {
        return Files.lines(new File(filename).toPath())
                .skip(1)
                .map(s -> s.split(";"))
                .map(s -> new ElectronicArticle(
                        Integer.parseInt(s[0].substring(6)),
                        s[1],
                        Integer.parseInt(s[2]),
                        Float.parseFloat(s[3].replace(",", ".")),
                        LocalDate.of(Integer.parseInt(s[4].split("-")[0]), Integer.parseInt(s[4].split("-")[1]), Integer.parseInt(s[4].split("-")[2]))
                ))
                .collect(Collectors.toList());
    }
}
