import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ArticleSumTask extends RecursiveTask<Double> {
    private static final int SEQUENTIAL_THRESHOLD = 5;
    private List<ElectronicArticle> articles;

    public ArticleSumTask(List<ElectronicArticle> articles) {
        this.articles = articles;
    }

    @Override
    protected Double compute() {
        double value;
        if(articles.size() <= SEQUENTIAL_THRESHOLD) {
            value = (articles.stream().mapToDouble(electronicArticle -> (double) electronicArticle.getPrice() * (double) electronicArticle.getStock()).reduce((a1, a2) -> a1 + a2)).getAsDouble();
        } else {
            int mid = (articles.size() + 1) / 2;

            List<ElectronicArticle> firstHalf = new ArrayList<>(articles.subList(0, mid));
            List<ElectronicArticle> secondHalf = new ArrayList<>(articles.subList(mid, articles.size()));

            ArticleSumTask upperHalf = new ArticleSumTask(firstHalf);
            ArticleSumTask lowerHalf = new ArticleSumTask(secondHalf);
            invokeAll(upperHalf, lowerHalf);
            value = upperHalf.compute() + lowerHalf.compute();
        }
        return value;
    }
}
