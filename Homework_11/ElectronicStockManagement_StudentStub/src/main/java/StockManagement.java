import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StockManagement {
    private List<ElectronicArticle> articles;
    private String stockFileName;

    public StockManagement(String stockFileName) {
        this.stockFileName = stockFileName;
    }

    /* Dieser Konstruktor ist nur für die Tests relevant! */
    public StockManagement(List<ElectronicArticle> articles) {
        this.articles = articles;
    }

    public void loadStockFiles() throws IOException {
        articles = ArticleFileManager.readStockFile(stockFileName);
    }

    /* Diese Methode wird zum Testen benötigt */
    public List<ElectronicArticle> getArticles() {
        return this.articles;
    }

    public void printArticles(List<ElectronicArticle> articlesToPrint) {
        for(ElectronicArticle e : articlesToPrint) {
            System.out.print(e.toString() + "\n");
        }
    }

    public void printAllArticles() {
        for(ElectronicArticle e : articles) {
            System.out.print(e.toString() + "\n");
        }
    }

    public List<ElectronicArticle> selectSoldArticles() {
        return articles.stream().filter(electronicArticle ->  electronicArticle.getStock() == 0).collect(Collectors.toList());
    }

    public List<ElectronicArticle> selectArticlesWherePriceIsLessThan(float price) {
        return articles.stream().filter(electronicArticle ->  electronicArticle.getPrice() <= price).collect(Collectors.toList());
    }

    public List<ElectronicArticle> sortArticlesDependingOnDate() {
        return articles.stream().sorted(Comparator.comparing(electronicArticle -> electronicArticle.getOrderDate())).collect(Collectors.toList());
    }

    public List<ElectronicArticle> selectArticlesAfterDate(int date) {
        return articles.stream().filter(electronicArticle -> electronicArticle.getOrderDate().isEqual(LocalDate.of(Integer.parseInt(Integer.toString(date).substring(0,4)), Integer.parseInt(Integer.toString(date).substring(4,6)), Integer.parseInt(Integer.toString(date).substring(6)))) || electronicArticle.getOrderDate().isAfter(LocalDate.of(Integer.parseInt(Integer.toString(date).substring(0,4)), Integer.parseInt(Integer.toString(date).substring(4,6)), Integer.parseInt(Integer.toString(date).substring(6))))).collect(Collectors.toList());
    }

    public double calculateAverageArticlePrice() {
        return articles.stream().map(electronicArticle -> electronicArticle.getPrice()).reduce(0.0f, (a1, a2) -> a1 + a2) / articles.size();
    }

    public double calculateStockValueSerial() {
        return (articles.stream().mapToDouble(electronicArticle -> (double) electronicArticle.getPrice() * (double) electronicArticle.getStock()).reduce((a1, a2) -> a1 + a2)).getAsDouble();
    }

    public double calculateStockValueParallel() {
        return new ArticleSumTask(articles).compute();
    }

    public List<ElectronicArticle> selectTopNArticlesDependingOnPrice(int n) {
        return articles.stream().sorted(Comparator.comparingDouble(ElectronicArticle::getPrice).reversed()).limit(n).collect(Collectors.toList());
    }

    public String selectLongestArticleName() {
        return (articles.stream().max(Comparator.comparingInt(value -> value.getArticleName().length()))).get().getArticleName();
    }

    public ElectronicArticle findArticleByArticleNumber(int articleNumber) {
        for(ElectronicArticle e : articles) {
            if(e.getArticleNumber() == articleNumber) {
                return e;
            }
        }
        return null;
    }

    public void printDaysBetweenArticles(ElectronicArticle articleA, ElectronicArticle articleB) {
        if(articleA != null && articleB != null) {
            LocalDate l1 = articleA.getOrderDate();
            LocalDate l2 = articleB.getOrderDate();
            LocalDate smaller;
            LocalDate larger;

            if(l1.isBefore(l2)) {
                smaller = l1;
                larger = l2;
            } else {
                smaller = l2;
                larger = l1;
            }

            List<LocalDate> list = Stream.iterate(smaller, localDate -> localDate.plusDays(1)).limit(ChronoUnit.DAYS.between(smaller, larger)).collect(Collectors.toList());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for(int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i).format(formatter) + "\n");
            }
        } else {
            throw new IllegalArgumentException("one or more articles are null!");
        }
    }

    public int calculateMaxStoreCountParallel(int taskCount) throws InterruptedException {
        if(taskCount < 1 || taskCount > 99) {
            throw new IllegalArgumentException("The taskCount can only be from 1 - 99");
        }
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(taskCount);
        List<Callable<Integer>> taskList = new ArrayList<>();

        int chunkSize = (int) Math.ceil( (double) articles.size() / (double) taskCount);
        int numberOfLists = (int) Math.ceil( (double) articles.size() / (double) chunkSize);

        List<List<ElectronicArticle>> splittedLists = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger();
        for(ElectronicArticle e : articles) {
            if(counter.getAndIncrement() % chunkSize == 0) {
                splittedLists.add(new ArrayList<>());
            }
            splittedLists.get(splittedLists.size()-1).add(e);
        }

        for(int i = 0; i < numberOfLists; i++) {
            Callable<Integer> task = new ArticleStoreMaxFinderTask(splittedLists.get(i));
            taskList.add(task);
        }

        List<Future<Integer>> resultList = null;

        try {
            resultList = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();

        List<Integer> allMaxStoreEachThread = new ArrayList<>();
        for(int i = 0; i < resultList.size(); i++) {
            Future<Integer> future = resultList.get(i);
            try {
                allMaxStoreEachThread.add(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Collections.sort(allMaxStoreEachThread);

        return allMaxStoreEachThread.get(allMaxStoreEachThread.size()-1);
    }
}
