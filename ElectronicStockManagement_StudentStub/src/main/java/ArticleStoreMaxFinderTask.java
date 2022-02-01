import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

public class ArticleStoreMaxFinderTask implements Callable<Integer> {
    private final List<ElectronicArticle> articles;

    public ArticleStoreMaxFinderTask(List<ElectronicArticle> articles) {
        this.articles = articles;
    }

    @Override
    public Integer call() throws Exception{
        List<Integer> allStoreCounter = new LinkedList<>();
        for(ElectronicArticle e : articles) {
            allStoreCounter.add(e.getStock());
        }
        Collections.sort(allStoreCounter);
        return allStoreCounter.get(allStoreCounter.size()-1);
    }
}
