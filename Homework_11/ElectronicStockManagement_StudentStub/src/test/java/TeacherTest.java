import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeacherTest {
    @Test
    public void electronicArticle_toString_returnsSomething() {
        ElectronicArticle electronicArticle = createArticles().get(0);

        assertTrue(electronicArticle.toString() != null);
        assertNotEquals(electronicArticle.toString().length(), 0);
    }

    @Test
    public void electronicArticle_toString_returnsCorrectString() {
        ElectronicArticle electronicArticle = createArticles().get(0);

        assertEquals("5 - Audio-Steckverbinder (46) Preis: 26.6 Bestelldatum: 11.04.2018",
                     electronicArticle.toString());
    }

    @Test
    public void electronicArticle_equals_correct() {
        ElectronicArticle electronicArticleA = createArticles().get(0);
        ElectronicArticle electronicArticleB = createArticles().get(0);

        assertTrue(electronicArticleA.equals(electronicArticleB));

        electronicArticleA = createArticles().get(0);
        electronicArticleB = createArticles().get(1);
        assertFalse(electronicArticleA.equals(electronicArticleB));

        electronicArticleA = createArticles().get(0);
        electronicArticleB = createArticles().get(1);
        electronicArticleB.setArticleName(electronicArticleA.getArticleName());
        assertTrue(electronicArticleA.equals(electronicArticleB));
    }

    @Test
    public void readStockFile_returnsSomething() throws IOException {
        List<ElectronicArticle> result = ArticleFileManager.readStockFile(ElectronicStockMain.STOCK_FILENAME);

        assertTrue(result.size() > 0);
    }

    @Test
    public void readStockFile_lengthTest() throws IOException {
        List<ElectronicArticle> result = ArticleFileManager.readStockFile(ElectronicStockMain.STOCK_FILENAME);

        assertEquals(createArticles().size(), result.size());
    }

    @Test
    public void readStockFile_listsEqual() throws IOException {
        List<ElectronicArticle> result = ArticleFileManager.readStockFile(ElectronicStockMain.STOCK_FILENAME);

        ListTester<ElectronicArticle> tester = new ListTester<>();
        assertTrue(tester.listsEqual(result, createArticles()));
    }

    @Test
    public void loadStockFiles_listsEqual() throws IOException {
        StockManagement stockManagement = new StockManagement(ElectronicStockMain.STOCK_FILENAME);

        stockManagement.loadStockFiles();

        ListTester<ElectronicArticle> tester = new ListTester<>();
        assertTrue(tester.listsEqual(stockManagement.getArticles(), createArticles()));
    }

    @Test
    public void printArticles_printsSomething() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        StockManagement stockManagement = new StockManagement("");
        stockManagement.printArticles(createArticles());

        System.setOut(originalOut);

        assertTrue(outContent.toString().length() > 0);
    }

    @Test
    public void printArticles_correctLineCount() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        StockManagement stockManagement = new StockManagement("");
        stockManagement.printArticles(createArticles());

        System.setOut(originalOut);

        String[] splitted = outContent.toString().split("\n");

        assertEquals(103, splitted.length);
    }

    @Test
    public void printArticles_correctOutput() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        StockManagement stockManagement = new StockManagement("");
        stockManagement.printArticles(createArticles());

        System.setOut(originalOut);

        assertEquals(outputString, outContent.toString());
    }

    @Test
    public void printAllArticles_returnsSomething() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        StockManagement stockManagement = new StockManagement(createArticles());
        stockManagement.printAllArticles();

        System.setOut(originalOut);

        assertNotEquals(0, outContent.toString().length());
    }

    @Test
    public void printAllArticles_correctOutput() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        StockManagement stockManagement = new StockManagement(createArticles());
        stockManagement.printAllArticles();

        System.setOut(originalOut);

        assertEquals(outputString, outContent.toString());
    }

    @Test
    public void selectSoldArticles_returnsSometing() {
        StockManagement stockManagement = new StockManagement(createArticles());
        List<ElectronicArticle> result = stockManagement.selectSoldArticles();

        assertTrue(result != null);
        assertTrue(result.size() > 0);
    }

    @Test
    public void selectSoldArticles_correctLength() {
        StockManagement stockManagement = new StockManagement(createArticles());
        List<ElectronicArticle> result = stockManagement.selectSoldArticles();

        assertEquals(7, result.size());
    }

    @Test
    public void selectSoldArticles_correctResult() {
        StockManagement stockManagement = new StockManagement(createArticles());
        List<ElectronicArticle> result = stockManagement.selectSoldArticles();

        ListTester<ElectronicArticle> listTester = new ListTester<>();
        assertTrue(listTester.listsEqual(result, soldArticles()));
    }

    @Test
    public void selectArticlesWherePriceIsLessThan_returnsSomething() {
        StockManagement stockManagement = new StockManagement(createArticles());
        List<ElectronicArticle> result = stockManagement.selectArticlesWherePriceIsLessThan(7);

        assertTrue(result != null);
        assertTrue(result.size() > 0);
    }

    @Test
    public void selectArticlesWherePriceIsLessThan_correctLength() {
        StockManagement stockManagement = new StockManagement(createArticles());
        List<ElectronicArticle> result = stockManagement.selectArticlesWherePriceIsLessThan(7);

        assertEquals(6, result.size());
    }

    @Test
    public void selectArticlesWherePriceIsLessThan_correctResult() {
        StockManagement stockManagement = new StockManagement(createArticles());
        List<ElectronicArticle> result = stockManagement.selectArticlesWherePriceIsLessThan(7);

        ListTester<ElectronicArticle> listTester = new ListTester<>();
        assertTrue(listTester.listsEqual(result, priceUnderSevenArticles()));
    }

    @Test
    public void sortArticlesDependingOnDate_returnsSomething() {
        StockManagement stockManagement = new StockManagement(createArticles());
        List<ElectronicArticle> result = stockManagement.sortArticlesDependingOnDate();

        assertTrue(result != null);
        assertTrue(result.size() > 0);
    }

    @Test
    public void sortArticlesDependingOnDate_correctLength() {
        StockManagement stockManagement = new StockManagement(createArticles());
        List<ElectronicArticle> result = stockManagement.sortArticlesDependingOnDate();

        assertEquals(103, result.size());
    }

    @Test
    public void sortArticlesDependingOnDate_correctResult() {
        StockManagement stockManagement = new StockManagement(createArticles());
        List<ElectronicArticle> result = stockManagement.sortArticlesDependingOnDate();

        String resultString = articleListToString(result);

        assertEquals(orderString, resultString);
    }

    @Test
    public void selectArticlesAfterDate_returnsSomething() {
        StockManagement stockManagement = new StockManagement(createArticles());
        List<ElectronicArticle> result = stockManagement.selectArticlesAfterDate(20191122);

        assertTrue(result != null);
        assertTrue(result.size() > 0);
    }

    @Test
    public void selectArticlesAfterDate_correctLength() {
        StockManagement stockManagement = new StockManagement(createArticles());
        List<ElectronicArticle> result = stockManagement.selectArticlesAfterDate(20191122);

        assertEquals(4, result.size());
    }

    @Test
    public void selectArticlesAfterDate_correctResult() {
        StockManagement stockManagement = new StockManagement(createArticles());
        List<ElectronicArticle> result = stockManagement.selectArticlesAfterDate(20191122);

        ListTester<ElectronicArticle> listTester = new ListTester<>();
        assertTrue(listTester.listsEqual(result, afterDateArticles()));
    }

    @Test
    public void calculateAverageArticlePrice_returnsSomething() {
        StockManagement stockManagement = new StockManagement(createArticles());
        double result = stockManagement.calculateAverageArticlePrice();

        assertTrue(result > 0);
    }

    @Test
    public void calculateAverageArticlePrice_returnsCorrectValue() {
        StockManagement stockManagement = new StockManagement(createArticles());
        double result = stockManagement.calculateAverageArticlePrice();

        double delta = Math.abs(50.492136156674725 - result);

        assertTrue(delta <= 0.1);
    }

    @Test
    public void calculateStockValueSerial_returnsSomething() {
        StockManagement stockManagement = new StockManagement(createArticles());
        double result = stockManagement.calculateStockValueSerial();

        assertTrue(result > 0);
    }

    @Test
    public void calculateStockValueSerial_returnsCorrectValue() {
        StockManagement stockManagement = new StockManagement(createArticles());
        double result = stockManagement.calculateStockValueSerial();

        double delta = Math.abs(359670.3450937271 - result);

        assertTrue(delta <= 0.1);
    }

    @Test(timeout = 1000)
    public void calculateStockValueParallel_returnsSomething() {
        StockManagement stockManagement = new StockManagement(createArticles());
        double result = stockManagement.calculateStockValueParallel();

        assertTrue(result > 0);
    }

    @Test(timeout = 1000)
    public void calculateStockValueParallel_returnsCorrectValue() {
        StockManagement stockManagement = new StockManagement(createArticles());
        double result = stockManagement.calculateStockValueParallel();

        double delta = Math.abs(359670.3450937271 - result);

        assertTrue(delta <= 0.1);
    }

    @Test
    public void selectTopNArticlesDependingOnPrice_returnsSomething() {
        StockManagement stockManagement = new StockManagement(createArticles());
        List<ElectronicArticle> result = stockManagement.selectTopNArticlesDependingOnPrice(4);

        assertTrue(result != null);
        assertTrue(result.size() > 0);
    }

    @Test
    public void selectTopNArticlesDependingOnPrice_correctLength() {
        StockManagement stockManagement = new StockManagement(createArticles());
        List<ElectronicArticle> result = stockManagement.selectTopNArticlesDependingOnPrice(4);

        assertEquals(4, result.size());
    }

    @Test
    public void selectTopNArticlesDependingOnPrice_correctResult() {
        StockManagement stockManagement = new StockManagement(createArticles());
        List<ElectronicArticle> result = stockManagement.selectTopNArticlesDependingOnPrice(4);

        ListTester<ElectronicArticle> listTester = new ListTester<>();
        assertTrue(listTester.listsEqual(result, topPriceArticles()));
    }

    @Test
    public void selectLongestArticleName_returnsSomething() {
        StockManagement stockManagement = new StockManagement(createArticles());
        String result = stockManagement.selectLongestArticleName();

        assertTrue(result != null);
        assertTrue(result.length() > 0);
    }

    @Test
    public void selectLongestArticleName_correctResult() {
        StockManagement stockManagement = new StockManagement(createArticles());
        String result = stockManagement.selectLongestArticleName();

        assertEquals("Generic Array Logic (GAL), Programmable Array Logic (PAL), Field Programmable Gate Array (FPGA)",
                     result);
    }

    @Test
    public void findArticleByArticleNumber_correctResult() {
        StockManagement stockManagement = new StockManagement(createArticles());
        ElectronicArticle res = stockManagement.findArticleByArticleNumber(6);

        ListTester<ElectronicArticle> listTester = new ListTester<>();
        assertTrue(listTester.articleEquals(res, new ElectronicArticle(6, "Videosignal-Steckverbinder", 82, 85.73f,
                                                                       LocalDate.parse("2018-05-09"))));
    }

    @Test
    public void findArticleByArticleNumber_returnsNull() {
        StockManagement stockManagement = new StockManagement(createArticles());
        ElectronicArticle res = stockManagement.findArticleByArticleNumber(1337);

        assertEquals(null, res);
    }

    @Test
    public void printDaysBetweenArticles_returnsSomething() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        StockManagement stockManagement = new StockManagement(createArticles());
        stockManagement.printDaysBetweenArticles(
                new ElectronicArticle(5, "Audio-Steckverbinder", 46, 26.6f, LocalDate.parse("2018-04-11")),
                new ElectronicArticle(6, "Videosignal-Steckverbinder", 82, 85.73f, LocalDate.parse("2018-05-09")));

        System.setOut(originalOut);

        String result = outContent.toString();

        assertTrue(result != null);
        assertTrue(result.length() > 0);
    }

    @Test
    public void printDaysBetweenArticles_correctLength() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        StockManagement stockManagement = new StockManagement(createArticles());
        stockManagement.printDaysBetweenArticles(
                new ElectronicArticle(5, "Audio-Steckverbinder", 46, 26.6f, LocalDate.parse("2018-04-11")),
                new ElectronicArticle(6, "Videosignal-Steckverbinder", 82, 85.73f, LocalDate.parse("2018-05-09")));

        System.setOut(originalOut);

        String result = outContent.toString();

        String[] splittedResult = result.split("\n");

        assertEquals(28, splittedResult.length);
    }

    @Test
    public void printDaysBetweenArticles_correctResult() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        StockManagement stockManagement = new StockManagement(createArticles());
        stockManagement.printDaysBetweenArticles(
                new ElectronicArticle(5, "Audio-Steckverbinder", 46, 26.6f, LocalDate.parse("2018-04-11")),
                new ElectronicArticle(6, "Videosignal-Steckverbinder", 82, 85.73f, LocalDate.parse("2018-05-09")));

        System.setOut(originalOut);

        String result = outContent.toString();

        assertEquals(timeResultString, result);
    }

    @Test
    public void printDaysBetweenArticles_returnsSomething_inverse() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        StockManagement stockManagement = new StockManagement(createArticles());
        stockManagement.printDaysBetweenArticles(
                new ElectronicArticle(6, "Videosignal-Steckverbinder", 82, 85.73f, LocalDate.parse("2018-05-09")),
                new ElectronicArticle(5, "Audio-Steckverbinder", 46, 26.6f, LocalDate.parse("2018-04-11")));

        System.setOut(originalOut);

        String result = outContent.toString();

        assertTrue(result != null);
        assertTrue(result.length() > 0);
    }

    @Test
    public void printDaysBetweenArticles_correctLength_inverse() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        StockManagement stockManagement = new StockManagement(createArticles());
        stockManagement.printDaysBetweenArticles(
                new ElectronicArticle(6, "Videosignal-Steckverbinder", 82, 85.73f, LocalDate.parse("2018-05-09")),
                new ElectronicArticle(5, "Audio-Steckverbinder", 46, 26.6f, LocalDate.parse("2018-04-11")));

        System.setOut(originalOut);

        String result = outContent.toString();

        String[] splittedResult = result.split("\n");

        assertEquals(28, splittedResult.length);
    }

    @Test
    public void printDaysBetweenArticles_correctResult_inverse() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        StockManagement stockManagement = new StockManagement(createArticles());
        stockManagement.printDaysBetweenArticles(
                new ElectronicArticle(6, "Videosignal-Steckverbinder", 82, 85.73f, LocalDate.parse("2018-05-09")),
                new ElectronicArticle(5, "Audio-Steckverbinder", 46, 26.6f, LocalDate.parse("2018-04-11")));

        System.setOut(originalOut);

        String result = outContent.toString();

        assertEquals(timeResultString, result);
    }

    @Test
    public void printDaysBetweenArticles_correctResult_null() {
        StockManagement stockManagement = new StockManagement(createArticles());

        assertThrows(IllegalArgumentException.class, () -> {
            stockManagement.printDaysBetweenArticles(
                    null,
                    new ElectronicArticle(5, "Audio-Steckverbinder", 46, 26.6f, LocalDate.parse("2018-04-11")));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            stockManagement.printDaysBetweenArticles(
                    new ElectronicArticle(5, "Audio-Steckverbinder", 46, 26.6f, LocalDate.parse("2018-04-11")),
                    null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            stockManagement.printDaysBetweenArticles(
                    null,
                    null);
        });
    }

    @Test(timeout = 1000)
    public void calculateMaxStoreCountParallel_returnsSomething() throws InterruptedException {
        StockManagement stockManagement = new StockManagement(createArticles());
        int result = stockManagement.calculateMaxStoreCountParallel(5);

        assertTrue(result > 0);
    }

    @Test(timeout = 1000)
    public void calculateMaxStoreCountParallel_returnsCorrectResult() throws InterruptedException {
        StockManagement stockManagement = new StockManagement(createArticles());
        int result = stockManagement.calculateMaxStoreCountParallel(18);

        assertEquals(1337, result);
    }

    @Test(timeout = 1000)
    public void calculateMaxStoreCountParallel_returnsCorrectResultForEveryTaskCount() throws InterruptedException {
        StockManagement stockManagement = new StockManagement(createArticles());

        for(int i = 1; i < 100; i++) {
            System.out.println(i);
            int result = stockManagement.calculateMaxStoreCountParallel(i);
            assertEquals(1337, result);
        }
    }

    @Test
    public void calculateMaxStoreCountParallel_invalidParameterTest() {
        StockManagement stockManagement = new StockManagement(createArticles());

        assertThrows(IllegalArgumentException.class, () -> {
            stockManagement.calculateMaxStoreCountParallel(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            stockManagement.calculateMaxStoreCountParallel(-1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            stockManagement.calculateMaxStoreCountParallel(104);
        });
    }


    private String articleListToString(List<ElectronicArticle> result) {
        String res = "";

        for (int i = 0; i < result.size(); i++) {
            res += myToString(result.get(i));
        }

        return res;
    }

    private String myToString(ElectronicArticle article) {
        return "ElectronicArticle{" +
               "articleNumber=" + article.getArticleNumber() +
               ", articleName='" + article.getArticleName() + '\'' +
               ", stock=" + article.getStock() +
               ", price=" + article.getPrice() +
               ", orderDate=" + article.getOrderDate() +
               '}';
    }

    private List<ElectronicArticle> soldArticles() {
        List<ElectronicArticle> articles = new ArrayList<>();

        articles.add(
                new ElectronicArticle(25, "Kunststoff-Folienkondensator", 0, 58.01f, LocalDate.parse("2019-12-12")));
        articles.add(new ElectronicArticle(40, "Drossel (Elektrotechnik)", 0, 47.09f, LocalDate.parse("2019-03-27")));
        articles.add(new ElectronicArticle(47, "Röhrendiode, Zweipolröhre", 0, 99.47f, LocalDate.parse("2019-06-17")));
        articles.add(
                new ElectronicArticle(63, "Quecksilberdampfgleichrichter", 0, 72.69f, LocalDate.parse("2019-01-15")));
        articles.add(new ElectronicArticle(65, "Abstimmanzeigeröhre", 0, 66.53f, LocalDate.parse("2019-02-26")));
        articles.add(new ElectronicArticle(80, "Leuchtdiode", 0, 45.26f, LocalDate.parse("2018-01-17")));
        articles.add(new ElectronicArticle(88, "Selen-Photoelement", 0, 63.33f, LocalDate.parse("2018-06-13")));

        return articles;
    }

    private List<ElectronicArticle> priceUnderSevenArticles() {
        List<ElectronicArticle> articles = new ArrayList<>();

        articles.add(new ElectronicArticle(24, "Keramikkondensator", 6, 2.44f, LocalDate.parse("2019-11-22")));
        articles.add(
                new ElectronicArticle(28, "Tantal-Elektrolytkondensator", 38, 6.54f, LocalDate.parse("2018-04-10")));
        articles.add(new ElectronicArticle(31, "Superkondensator (Doppelschichtkondensator)", 81, 6.9f,
                                           LocalDate.parse("2018-08-27")));
        articles.add(new ElectronicArticle(58, "Photomultiplier", 24, 4.64f, LocalDate.parse("2018-06-06")));
        articles.add(new ElectronicArticle(62, "Senditron", 91, 5.82f, LocalDate.parse("2018-09-27")));
        articles.add(new ElectronicArticle(81, "Lichtschranke", 22, 3.34f, LocalDate.parse("2018-01-23")));


        return articles;
    }

    private List<ElectronicArticle> afterDateArticles() {
        List<ElectronicArticle> articles = new ArrayList<>();

        articles.add(new ElectronicArticle(24, "Keramikkondensator", 6, 2.44f, LocalDate.parse("2019-11-22")));
        articles.add(
                new ElectronicArticle(25, "Kunststoff-Folienkondensator", 0, 58.01f, LocalDate.parse("2019-12-12")));
        articles.add(
                new ElectronicArticle(50, "Pentode – Röhre mit drei Gittern (Schirm-, Steuer- und Bremsgitter)", 30,
                                      53.22f, LocalDate.parse("2019-12-23")));
        articles.add(new ElectronicArticle(75, "Operationsverstärker", 55, 82.91f, LocalDate.parse("2019-12-05")));

        return articles;
    }

    private List<ElectronicArticle> topPriceArticles() {
        List<ElectronicArticle> articles = new ArrayList<>();

        articles.add(new ElectronicArticle(47, "Röhrendiode, Zweipolröhre", 0, 99.47f, LocalDate.parse("2019-06-17")));
        articles.add(new ElectronicArticle(64, "Verbundröhre", 52, 98.97f, LocalDate.parse("2019-01-25")));
        articles.add(
                new ElectronicArticle(100, "Drehstrom-Asynchronmaschine", 58, 98.58f, LocalDate.parse("2019-11-18")));
        articles.add(new ElectronicArticle(96, "Grating Light Valve", 1337, 96.99f, LocalDate.parse("2019-03-26")));

        return articles;
    }

    private List<ElectronicArticle> createArticles() {
        List<ElectronicArticle> articles = new ArrayList<>();

        articles.add(new ElectronicArticle(5, "Audio-Steckverbinder", 46, 26.6f, LocalDate.parse("2018-04-11")));
        articles.add(new ElectronicArticle(2, "Ein- und Mehrphasen-Niederspannungssysteme", 3, 80.04f,
                                           LocalDate.parse("2017-12-15")));
        articles.add(
                new ElectronicArticle(1, "Einphasen-Haushaltsstecksysteme", 18, 70.08f, LocalDate.parse("2017-11-22")));
        articles.add(new ElectronicArticle(3, "Kleinspannungsstecker", 26, 53.57f, LocalDate.parse("2018-02-23")));
        articles.add(new ElectronicArticle(4, "Labor-Steckverbinder", 75, 61.54f, LocalDate.parse("2018-03-07")));
        articles.add(new ElectronicArticle(6, "Videosignal-Steckverbinder", 82, 85.73f, LocalDate.parse("2018-05-09")));
        articles.add(new ElectronicArticle(7, "Hochfrequenz-Steckverbinder", 3, 19.74f, LocalDate.parse("2018-06-19")));
        articles.add(new ElectronicArticle(8, "Daten-Steckverbinder", 79, 64.21f, LocalDate.parse("2018-08-16")));
        articles.add(new ElectronicArticle(9, "Telefon-Steckverbinder", 1, 68.94f, LocalDate.parse("2018-09-11")));
        articles.add(new ElectronicArticle(10, "LWL-Steckverbinder", 29, 57.83f, LocalDate.parse("2018-10-31")));
        articles.add(new ElectronicArticle(11, "Schalter", 97, 58.43f, LocalDate.parse("2019-02-04")));
        articles.add(new ElectronicArticle(12, "Relais, Schütz", 53, 64.87f, LocalDate.parse("2019-02-28")));
        articles.add(new ElectronicArticle(13, "Leitungsschutzschalter, Motorschutzschalter", 20, 79.57f,
                                           LocalDate.parse("2019-04-03")));
        articles.add(new ElectronicArticle(14, "Nulldurchgangsschalter", 40, 8.21f, LocalDate.parse("2019-05-01")));
        articles.add(new ElectronicArticle(15, "Nullspannungsschalter", 60, 18.31f, LocalDate.parse("2019-05-02")));
        articles.add(new ElectronicArticle(16, "Reed-Relais", 87, 72.57f, LocalDate.parse("2019-08-05")));
        articles.add(new ElectronicArticle(17, "Schwingquarze", 87, 89.32f, LocalDate.parse("2019-08-22")));
        articles.add(new ElectronicArticle(18, "Keramikresonatoren", 83, 13.88f, LocalDate.parse("2019-08-27")));
        articles.add(
                new ElectronicArticle(19, "Quarzoszillatoren, (PXO, Quarzofen, TCXO, VXO, VCXO, DTCXO)", 50, 26.47f,
                                      LocalDate.parse("2019-09-06")));
        articles.add(new ElectronicArticle(20, "AOW-Oszillatoren", 4, 82.62f, LocalDate.parse("2019-09-25")));
        articles.add(new ElectronicArticle(21, "AOW-Filter", 68, 62.44f, LocalDate.parse("2019-10-07")));
        articles.add(new ElectronicArticle(22, "Keramikfilter", 41, 62.38f, LocalDate.parse("2019-11-06")));
        articles.add(new ElectronicArticle(23, "Festkondensatoren", 22, 68.95f, LocalDate.parse("2019-11-19")));
        articles.add(new ElectronicArticle(24, "Keramikkondensator", 6, 2.44f, LocalDate.parse("2019-11-22")));
        articles.add(
                new ElectronicArticle(25, "Kunststoff-Folienkondensator", 0, 58.01f, LocalDate.parse("2019-12-12")));
        articles.add(new ElectronicArticle(26, "Elektrolytkondensator", 8, 24.61f, LocalDate.parse("2017-12-25")));
        articles.add(new ElectronicArticle(27, "Aluminium-Elektrolytkondensator", 63, 49.22f,
                                           LocalDate.parse("2018-04-05")));
        articles.add(
                new ElectronicArticle(28, "Tantal-Elektrolytkondensator", 38, 6.54f, LocalDate.parse("2018-04-10")));
        articles.add(
                new ElectronicArticle(29, "Niob-Elektrolytkondensator", 77, 96.72f, LocalDate.parse("2018-06-12")));
        articles.add(
                new ElectronicArticle(30, "Polymer-Elektrolytkondensator", 7, 57.73f, LocalDate.parse("2018-07-24")));
        articles.add(new ElectronicArticle(31, "Superkondensator (Doppelschichtkondensator)", 81, 6.9f,
                                           LocalDate.parse("2018-08-27")));
        articles.add(new ElectronicArticle(32, "Lithium-Ionen-Kondensator", 26, 53.11f, LocalDate.parse("2018-09-28")));
        articles.add(new ElectronicArticle(33, "Glimmerkondensator", 56, 33.26f, LocalDate.parse("2018-10-03")));
        articles.add(new ElectronicArticle(34, "Vakuumkondensator", 36, 48.25f, LocalDate.parse("2018-12-17")));
        articles.add(new ElectronicArticle(35, "Leistungskondensatoren", 89, 39.28f, LocalDate.parse("2019-01-02")));
        articles.add(
                new ElectronicArticle(36, "Induktivität (Bauelement),", 39, 22.75f, LocalDate.parse("2019-01-03")));
        articles.add(new ElectronicArticle(37, "Chipinduktivität, Mikroinduktivität", 74, 21.85f,
                                           LocalDate.parse("2019-01-16")));
        articles.add(new ElectronicArticle(38, "Spule (Elektrotechnik),", 67, 65.56f, LocalDate.parse("2019-02-11")));
        articles.add(new ElectronicArticle(39,
                                           "Luftspule, Toroidspule, Zündspule, Schwingspule, Tauchspule," +
                                           " Helmholtz-Spule",
                                           94, 50.47f, LocalDate.parse("2019-03-14")));
        articles.add(new ElectronicArticle(40, "Drossel (Elektrotechnik)", 0, 47.09f, LocalDate.parse("2019-03-27")));
        articles.add(new ElectronicArticle(41, "Transformatoren", 72, 28.46f, LocalDate.parse("2019-04-02")));
        articles.add(new ElectronicArticle(42, "Streufeldtransformator", 35, 51.53f, LocalDate.parse("2019-04-04")));
        articles.add(new ElectronicArticle(43, "Übertrager", 80, 48.65f, LocalDate.parse("2019-04-17")));
        articles.add(new ElectronicArticle(44, "Balun", 11, 10.38f, LocalDate.parse("2019-04-25")));
        articles.add(new ElectronicArticle(45, "Magnete", 6, 8.84f, LocalDate.parse("2019-04-26")));
        articles.add(new ElectronicArticle(46, "Nullode, elektrodenlose Gasentladungsröhre", 13, 14.14f,
                                           LocalDate.parse("2019-06-10")));
        articles.add(new ElectronicArticle(47, "Röhrendiode, Zweipolröhre", 0, 99.47f, LocalDate.parse("2019-06-17")));
        articles.add(
                new ElectronicArticle(48, "Triodenröhre, die einfachste Verstärkerröhre (Anode, Gitter, Kathode)", 70,
                                      54.68f, LocalDate.parse("2019-07-22")));
        articles.add(new ElectronicArticle(49, "Tetrode – Röhre mit zwei Gittern", 16, 88.23f,
                                           LocalDate.parse("2019-11-20")));
        articles.add(
                new ElectronicArticle(50, "Pentode – Röhre mit drei Gittern (Schirm-, Steuer- und Bremsgitter)", 30,
                                      53.22f, LocalDate.parse("2019-12-23")));
        articles.add(new ElectronicArticle(51,
                                           "Hexode – Röhre mit vier Gittern (Steuer- und Bremsgitter, zwei " +
                                           "Schirmgitter)",
                                           69, 32.09f, LocalDate.parse("2017-11-23")));
        articles.add(new ElectronicArticle(52, "Braun’sche Röhre: Kathodenstrahlröhre", 83, 79.15f,
                                           LocalDate.parse("2018-01-10")));
        articles.add(new ElectronicArticle(53, "Röntgenröhre", 5, 61.47f, LocalDate.parse("2018-01-12")));
        articles.add(new ElectronicArticle(54, "Klystron", 42, 27.99f, LocalDate.parse("2018-01-26")));
        articles.add(new ElectronicArticle(55, "Krytron", 99, 65.69f, LocalDate.parse("2018-03-15")));
        articles.add(new ElectronicArticle(56, "Magnetron", 73, 85.14f, LocalDate.parse("2018-05-03")));
        articles.add(new ElectronicArticle(57, "Sekundärelektronenvervielfacher", 46, 93.14f,
                                           LocalDate.parse("2018-05-10")));
        articles.add(new ElectronicArticle(58, "Photomultiplier", 24, 4.64f, LocalDate.parse("2018-06-06")));
        articles.add(new ElectronicArticle(59, "Thyratron", 32, 27.33f, LocalDate.parse("2018-08-01")));
        articles.add(new ElectronicArticle(60, "Excitron", 15, 37.15f, LocalDate.parse("2018-09-06")));
        articles.add(new ElectronicArticle(61, "Ignitron", 16, 85.92f, LocalDate.parse("2018-09-10")));
        articles.add(new ElectronicArticle(62, "Senditron", 91, 5.82f, LocalDate.parse("2018-09-27")));
        articles.add(
                new ElectronicArticle(63, "Quecksilberdampfgleichrichter", 0, 72.69f, LocalDate.parse("2019-01-15")));
        articles.add(new ElectronicArticle(64, "Verbundröhre", 52, 98.97f, LocalDate.parse("2019-01-25")));
        articles.add(new ElectronicArticle(65, "Abstimmanzeigeröhre", 0, 66.53f, LocalDate.parse("2019-02-26")));
        articles.add(
                new ElectronicArticle(66, "Nixie-Röhre – Röhre zur Darstellung verschiedener Zeichen (Ziffern)", 48,
                                      52.03f, LocalDate.parse("2019-03-01")));
        articles.add(new ElectronicArticle(67, "ROM, PROM (nur lesbare Speicher)", 33, 59.9f,
                                           LocalDate.parse("2019-03-12")));
        articles.add(new ElectronicArticle(68,
                                           "RAM (DRAM, SRAM), EPROM, EEPROM, Flash-EPROM (les- und beschreibbare " +
                                           "Speicher)",
                                           13, 80.09f, LocalDate.parse("2019-04-24")));
        articles.add(
                new ElectronicArticle(69, "Mikrocontroller, Mikroprozessor (CPU), Digitaler Signalprozessor (DSP)", 99,
                                      13.88f, LocalDate.parse("2019-04-25")));
        articles.add(new ElectronicArticle(70, "Gleitkommaeinheit, Memory Management Unit (MMU)", 53, 11.55f,
                                           LocalDate.parse("2019-05-07")));
        articles.add(new ElectronicArticle(71, "Grafikprozessor", 66, 40.46f, LocalDate.parse("2019-06-03")));
        articles.add(new ElectronicArticle(72, "Chipsatz", 1, 65.47f, LocalDate.parse("2019-08-08")));
        articles.add(new ElectronicArticle(73,
                                           "Generic Array Logic (GAL), Programmable Array Logic (PAL), Field " +
                                           "Programmable Gate Array (FPGA)",
                                           46, 58.63f, LocalDate.parse("2019-09-04")));
        articles.add(new ElectronicArticle(74, "Logikgatter, Gate Array", 45, 40.68f, LocalDate.parse("2019-10-08")));
        articles.add(new ElectronicArticle(75, "Operationsverstärker", 55, 82.91f, LocalDate.parse("2019-12-05")));
        articles.add(
                new ElectronicArticle(76, "Spannungsregler, Schaltregler", 77, 18.6f, LocalDate.parse("2017-11-29")));
        articles.add(new ElectronicArticle(77, "Digital-Analog-Umsetzer, Analog-Digital-Umsetzer", 39, 41.41f,
                                           LocalDate.parse("2017-11-30")));
        articles.add(new ElectronicArticle(78, "Multiplexer", 60, 20.87f, LocalDate.parse("2017-12-06")));
        articles.add(new ElectronicArticle(79, "Laserdiode", 90, 28.9f, LocalDate.parse("2018-01-11")));
        articles.add(new ElectronicArticle(80, "Leuchtdiode", 0, 45.26f, LocalDate.parse("2018-01-17")));
        articles.add(new ElectronicArticle(81, "Lichtschranke", 22, 3.34f, LocalDate.parse("2018-01-23")));
        articles.add(new ElectronicArticle(82, "Photohalbleiter", 61, 27.73f, LocalDate.parse("2018-02-22")));
        articles.add(new ElectronicArticle(83, "Fotowiderstand", 91, 79.99f, LocalDate.parse("2018-03-13")));
        articles.add(new ElectronicArticle(84, "Halbleiter-Strahlungsdetektoren", 19, 16.95f,
                                           LocalDate.parse("2018-03-16")));
        articles.add(new ElectronicArticle(85, "Photoelemente", 36, 69.4f, LocalDate.parse("2018-05-18")));
        articles.add(new ElectronicArticle(86, "Silizium-Photodiode, pin-Diode, Avalanche-Photodiode", 82, 41.13f,
                                           LocalDate.parse("2018-05-23")));
        articles.add(new ElectronicArticle(87, "Solarzelle", 91, 70.43f, LocalDate.parse("2018-06-04")));
        articles.add(new ElectronicArticle(88, "Selen-Photoelement", 0, 63.33f, LocalDate.parse("2018-06-13")));
        articles.add(new ElectronicArticle(89, "Fototransistor", 44, 22.67f, LocalDate.parse("2018-09-17")));
        articles.add(
                new ElectronicArticle(90, "Fotothyristor (Optothyristor)", 33, 10.12f, LocalDate.parse("2018-10-08")));
        articles.add(new ElectronicArticle(91, "Optokoppler, Solid-state-Relais", 23, 51.98f,
                                           LocalDate.parse("2018-10-18")));
        articles.add(new ElectronicArticle(92, "CCD-Sensoren, CMOS-Sensoren, Bildsensor", 77, 53.6f,
                                           LocalDate.parse("2018-11-02")));
        articles.add(new ElectronicArticle(93, "Lichtwellenleiter (LWL)", 75, 55.56f, LocalDate.parse("2018-11-05")));
        articles.add(
                new ElectronicArticle(94, "Dünnschichttransistor (TFT)", 91, 53.75f, LocalDate.parse("2018-11-27")));
        articles.add(new ElectronicArticle(95, "OLED", 45, 72.22f, LocalDate.parse("2019-02-12")));
        articles.add(new ElectronicArticle(96, "Grating Light Valve", 1337, 96.99f, LocalDate.parse("2019-03-26")));
        articles.add(new ElectronicArticle(97, "Elektromagnet", 30, 64.16f, LocalDate.parse("2019-06-21")));
        articles.add(new ElectronicArticle(98, "Elektromotor", 33, 47.35f, LocalDate.parse("2019-08-13")));
        articles.add(
                new ElectronicArticle(99, "Drehstrom-Synchronmaschine", 77, 85.95f, LocalDate.parse("2019-11-08")));
        articles.add(
                new ElectronicArticle(100, "Drehstrom-Asynchronmaschine", 58, 98.58f, LocalDate.parse("2019-11-18")));
        articles.add(new ElectronicArticle(101, "Gleichstrommaschine", 26, 91.24f, LocalDate.parse("2019-04-05")));
        articles.add(new ElectronicArticle(102, "Lautsprecher", 36, 48.06f, LocalDate.parse("2019-04-23")));
        articles.add(new ElectronicArticle(103, "Flüssigkristallbildschirm (LCD)", 11, 34.11f,
                                           LocalDate.parse("2019-04-30")));

        return articles;
    }

    private class ListTester<T> {
        private boolean listsEqual(List<T> listA, List<T> listB) {
            for (T cA : listA) {
                if (!isElementInList(cA, listB)) {
                    return false;
                }
            }

            for (T cB : listB) {
                if (!isElementInList(cB, listA)) {
                    return false;
                }
            }

            return true;
        }

        private boolean isElementInList(T needle, List<T> list) {
            for (T cElement : list) {
                if (cElement instanceof ElectronicArticle) {
                    if (articleEquals((ElectronicArticle) cElement, (ElectronicArticle) needle)) {
                        return true;
                    }
                }
            }

            return false;
        }

        private boolean articleEquals(ElectronicArticle a, ElectronicArticle b) {
            return a.getArticleNumber() == b.getArticleNumber() &&
                   a.getArticleName().equals(b.getArticleName()) &&
                   a.getStock() == b.getStock() &&
                   a.getPrice() == b.getPrice() &&
                   a.getOrderDate().equals(b.getOrderDate());
        }

    }

    private String timeResultString = "2018-04-11\n" +
                                      "2018-04-12\n" +
                                      "2018-04-13\n" +
                                      "2018-04-14\n" +
                                      "2018-04-15\n" +
                                      "2018-04-16\n" +
                                      "2018-04-17\n" +
                                      "2018-04-18\n" +
                                      "2018-04-19\n" +
                                      "2018-04-20\n" +
                                      "2018-04-21\n" +
                                      "2018-04-22\n" +
                                      "2018-04-23\n" +
                                      "2018-04-24\n" +
                                      "2018-04-25\n" +
                                      "2018-04-26\n" +
                                      "2018-04-27\n" +
                                      "2018-04-28\n" +
                                      "2018-04-29\n" +
                                      "2018-04-30\n" +
                                      "2018-05-01\n" +
                                      "2018-05-02\n" +
                                      "2018-05-03\n" +
                                      "2018-05-04\n" +
                                      "2018-05-05\n" +
                                      "2018-05-06\n" +
                                      "2018-05-07\n" +
                                      "2018-05-08\n";

    private String orderString = "ElectronicArticle{articleNumber=1, articleName='Einphasen-Haushaltsstecksysteme', " +
                                 "stock=18, price=70.08, orderDate=2017-11-22}ElectronicArticle{articleNumber=51, " +
                                 "articleName='Hexode – Röhre mit vier Gittern (Steuer- und Bremsgitter, zwei " +
                                 "Schirmgitter)', stock=69, price=32.09, " +
                                 "orderDate=2017-11-23}ElectronicArticle{articleNumber=76, " +
                                 "articleName='Spannungsregler, Schaltregler', stock=77, price=18.6, " +
                                 "orderDate=2017-11-29}ElectronicArticle{articleNumber=77, " +
                                 "articleName='Digital-Analog-Umsetzer, Analog-Digital-Umsetzer', stock=39, price=41" +
                                 ".41, orderDate=2017-11-30}ElectronicArticle{articleNumber=78, " +
                                 "articleName='Multiplexer', stock=60, price=20.87, " +
                                 "orderDate=2017-12-06}ElectronicArticle{articleNumber=2, articleName='Ein- und " +
                                 "Mehrphasen-Niederspannungssysteme', stock=3, price=80.04, " +
                                 "orderDate=2017-12-15}ElectronicArticle{articleNumber=26, " +
                                 "articleName='Elektrolytkondensator', stock=8, price=24.61, " +
                                 "orderDate=2017-12-25}ElectronicArticle{articleNumber=52, articleName='Braun’sche " +
                                 "Röhre: Kathodenstrahlröhre', stock=83, price=79.15, " +
                                 "orderDate=2018-01-10}ElectronicArticle{articleNumber=79, articleName='Laserdiode', " +
                                 "stock=90, price=28.9, orderDate=2018-01-11}ElectronicArticle{articleNumber=53, " +
                                 "articleName='Röntgenröhre', stock=5, price=61.47, " +
                                 "orderDate=2018-01-12}ElectronicArticle{articleNumber=80, articleName='Leuchtdiode'," +
                                 " stock=0, price=45.26, orderDate=2018-01-17}ElectronicArticle{articleNumber=81, " +
                                 "articleName='Lichtschranke', stock=22, price=3.34, " +
                                 "orderDate=2018-01-23}ElectronicArticle{articleNumber=54, articleName='Klystron', " +
                                 "stock=42, price=27.99, orderDate=2018-01-26}ElectronicArticle{articleNumber=82, " +
                                 "articleName='Photohalbleiter', stock=61, price=27.73, " +
                                 "orderDate=2018-02-22}ElectronicArticle{articleNumber=3, " +
                                 "articleName='Kleinspannungsstecker', stock=26, price=53.57, " +
                                 "orderDate=2018-02-23}ElectronicArticle{articleNumber=4, " +
                                 "articleName='Labor-Steckverbinder', stock=75, price=61.54, " +
                                 "orderDate=2018-03-07}ElectronicArticle{articleNumber=83, " +
                                 "articleName='Fotowiderstand', stock=91, price=79.99, " +
                                 "orderDate=2018-03-13}ElectronicArticle{articleNumber=55, articleName='Krytron', " +
                                 "stock=99, price=65.69, orderDate=2018-03-15}ElectronicArticle{articleNumber=84, " +
                                 "articleName='Halbleiter-Strahlungsdetektoren', stock=19, price=16.95, " +
                                 "orderDate=2018-03-16}ElectronicArticle{articleNumber=27, " +
                                 "articleName='Aluminium-Elektrolytkondensator', stock=63, price=49.22, " +
                                 "orderDate=2018-04-05}ElectronicArticle{articleNumber=28, " +
                                 "articleName='Tantal-Elektrolytkondensator', stock=38, price=6.54, " +
                                 "orderDate=2018-04-10}ElectronicArticle{articleNumber=5, " +
                                 "articleName='Audio-Steckverbinder', stock=46, price=26.6, " +
                                 "orderDate=2018-04-11}ElectronicArticle{articleNumber=56, articleName='Magnetron', " +
                                 "stock=73, price=85.14, orderDate=2018-05-03}ElectronicArticle{articleNumber=6, " +
                                 "articleName='Videosignal-Steckverbinder', stock=82, price=85.73, " +
                                 "orderDate=2018-05-09}ElectronicArticle{articleNumber=57, " +
                                 "articleName='Sekundärelektronenvervielfacher', stock=46, price=93.14, " +
                                 "orderDate=2018-05-10}ElectronicArticle{articleNumber=85, " +
                                 "articleName='Photoelemente', stock=36, price=69.4, " +
                                 "orderDate=2018-05-18}ElectronicArticle{articleNumber=86, " +
                                 "articleName='Silizium-Photodiode, pin-Diode, Avalanche-Photodiode', stock=82, " +
                                 "price=41.13, orderDate=2018-05-23}ElectronicArticle{articleNumber=87, " +
                                 "articleName='Solarzelle', stock=91, price=70.43, " +
                                 "orderDate=2018-06-04}ElectronicArticle{articleNumber=58, " +
                                 "articleName='Photomultiplier', stock=24, price=4.64, " +
                                 "orderDate=2018-06-06}ElectronicArticle{articleNumber=29, " +
                                 "articleName='Niob-Elektrolytkondensator', stock=77, price=96.72, " +
                                 "orderDate=2018-06-12}ElectronicArticle{articleNumber=88, " +
                                 "articleName='Selen-Photoelement', stock=0, price=63.33, " +
                                 "orderDate=2018-06-13}ElectronicArticle{articleNumber=7, " +
                                 "articleName='Hochfrequenz-Steckverbinder', stock=3, price=19.74, " +
                                 "orderDate=2018-06-19}ElectronicArticle{articleNumber=30, " +
                                 "articleName='Polymer-Elektrolytkondensator', stock=7, price=57.73, " +
                                 "orderDate=2018-07-24}ElectronicArticle{articleNumber=59, articleName='Thyratron', " +
                                 "stock=32, price=27.33, orderDate=2018-08-01}ElectronicArticle{articleNumber=8, " +
                                 "articleName='Daten-Steckverbinder', stock=79, price=64.21, " +
                                 "orderDate=2018-08-16}ElectronicArticle{articleNumber=31, " +
                                 "articleName='Superkondensator (Doppelschichtkondensator)', stock=81, price=6.9, " +
                                 "orderDate=2018-08-27}ElectronicArticle{articleNumber=60, articleName='Excitron', " +
                                 "stock=15, price=37.15, orderDate=2018-09-06}ElectronicArticle{articleNumber=61, " +
                                 "articleName='Ignitron', stock=16, price=85.92, " +
                                 "orderDate=2018-09-10}ElectronicArticle{articleNumber=9, " +
                                 "articleName='Telefon-Steckverbinder', stock=1, price=68.94, " +
                                 "orderDate=2018-09-11}ElectronicArticle{articleNumber=89, " +
                                 "articleName='Fototransistor', stock=44, price=22.67, " +
                                 "orderDate=2018-09-17}ElectronicArticle{articleNumber=62, articleName='Senditron', " +
                                 "stock=91, price=5.82, orderDate=2018-09-27}ElectronicArticle{articleNumber=32, " +
                                 "articleName='Lithium-Ionen-Kondensator', stock=26, price=53.11, " +
                                 "orderDate=2018-09-28}ElectronicArticle{articleNumber=33, " +
                                 "articleName='Glimmerkondensator', stock=56, price=33.26, " +
                                 "orderDate=2018-10-03}ElectronicArticle{articleNumber=90, " +
                                 "articleName='Fotothyristor (Optothyristor)', stock=33, price=10.12, " +
                                 "orderDate=2018-10-08}ElectronicArticle{articleNumber=91, articleName='Optokoppler, " +
                                 "Solid-state-Relais', stock=23, price=51.98, " +
                                 "orderDate=2018-10-18}ElectronicArticle{articleNumber=10, " +
                                 "articleName='LWL-Steckverbinder', stock=29, price=57.83, " +
                                 "orderDate=2018-10-31}ElectronicArticle{articleNumber=92, articleName='CCD-Sensoren," +
                                 " CMOS-Sensoren, Bildsensor', stock=77, price=53.6, " +
                                 "orderDate=2018-11-02}ElectronicArticle{articleNumber=93, " +
                                 "articleName='Lichtwellenleiter (LWL)', stock=75, price=55.56, " +
                                 "orderDate=2018-11-05}ElectronicArticle{articleNumber=94, " +
                                 "articleName='Dünnschichttransistor (TFT)', stock=91, price=53.75, " +
                                 "orderDate=2018-11-27}ElectronicArticle{articleNumber=34, " +
                                 "articleName='Vakuumkondensator', stock=36, price=48.25, " +
                                 "orderDate=2018-12-17}ElectronicArticle{articleNumber=35, " +
                                 "articleName='Leistungskondensatoren', stock=89, price=39.28, " +
                                 "orderDate=2019-01-02}ElectronicArticle{articleNumber=36, articleName='Induktivität " +
                                 "(Bauelement),', stock=39, price=22.75, " +
                                 "orderDate=2019-01-03}ElectronicArticle{articleNumber=63, " +
                                 "articleName='Quecksilberdampfgleichrichter', stock=0, price=72.69, " +
                                 "orderDate=2019-01-15}ElectronicArticle{articleNumber=37, " +
                                 "articleName='Chipinduktivität, Mikroinduktivität', stock=74, price=21.85, " +
                                 "orderDate=2019-01-16}ElectronicArticle{articleNumber=64, " +
                                 "articleName='Verbundröhre', stock=52, price=98.97, " +
                                 "orderDate=2019-01-25}ElectronicArticle{articleNumber=11, articleName='Schalter', " +
                                 "stock=97, price=58.43, orderDate=2019-02-04}ElectronicArticle{articleNumber=38, " +
                                 "articleName='Spule (Elektrotechnik),', stock=67, price=65.56, " +
                                 "orderDate=2019-02-11}ElectronicArticle{articleNumber=95, articleName='OLED', " +
                                 "stock=45, price=72.22, orderDate=2019-02-12}ElectronicArticle{articleNumber=65, " +
                                 "articleName='Abstimmanzeigeröhre', stock=0, price=66.53, " +
                                 "orderDate=2019-02-26}ElectronicArticle{articleNumber=12, articleName='Relais," +
                                 " Schütz', stock=53, price=64.87, " +
                                 "orderDate=2019-02-28}ElectronicArticle{articleNumber=66, articleName='Nixie-Röhre –" +
                                 " Röhre zur Darstellung verschiedener Zeichen (Ziffern)', stock=48, price=52.03, " +
                                 "orderDate=2019-03-01}ElectronicArticle{articleNumber=67, articleName='ROM, PROM " +
                                 "(nur lesbare Speicher)', stock=33, price=59.9, " +
                                 "orderDate=2019-03-12}ElectronicArticle{articleNumber=39, articleName='Luftspule," +
                                 " Toroidspule, Zündspule, Schwingspule, Tauchspule, Helmholtz-Spule', stock=94, " +
                                 "price=50.47, orderDate=2019-03-14}ElectronicArticle{articleNumber=96, " +
                                 "articleName='Grating Light Valve', stock=1337, price=96.99, " +
                                 "orderDate=2019-03-26}ElectronicArticle{articleNumber=40, articleName='Drossel " +
                                 "(Elektrotechnik)', stock=0, price=47.09, " +
                                 "orderDate=2019-03-27}ElectronicArticle{articleNumber=41, " +
                                 "articleName='Transformatoren', stock=72, price=28.46, " +
                                 "orderDate=2019-04-02}ElectronicArticle{articleNumber=13, " +
                                 "articleName='Leitungsschutzschalter, Motorschutzschalter', stock=20, price=79.57, " +
                                 "orderDate=2019-04-03}ElectronicArticle{articleNumber=42, " +
                                 "articleName='Streufeldtransformator', stock=35, price=51.53, " +
                                 "orderDate=2019-04-04}ElectronicArticle{articleNumber=101, " +
                                 "articleName='Gleichstrommaschine', stock=26, price=91.24, " +
                                 "orderDate=2019-04-05}ElectronicArticle{articleNumber=43, articleName='Übertrager', " +
                                 "stock=80, price=48.65, orderDate=2019-04-17}ElectronicArticle{articleNumber=102, " +
                                 "articleName='Lautsprecher', stock=36, price=48.06, " +
                                 "orderDate=2019-04-23}ElectronicArticle{articleNumber=68, articleName='RAM (DRAM," +
                                 " SRAM), EPROM, EEPROM, Flash-EPROM (les- und beschreibbare Speicher)', stock=13, " +
                                 "price=80.09, orderDate=2019-04-24}ElectronicArticle{articleNumber=44, " +
                                 "articleName='Balun', stock=11, price=10.38, " +
                                 "orderDate=2019-04-25}ElectronicArticle{articleNumber=69, " +
                                 "articleName='Mikrocontroller, Mikroprozessor (CPU), Digitaler Signalprozessor (DSP)" +
                                 "', stock=99, price=13.88, orderDate=2019-04-25}ElectronicArticle{articleNumber=45, " +
                                 "articleName='Magnete', stock=6, price=8.84, " +
                                 "orderDate=2019-04-26}ElectronicArticle{articleNumber=103, " +
                                 "articleName='Flüssigkristallbildschirm (LCD)', stock=11, price=34.11, " +
                                 "orderDate=2019-04-30}ElectronicArticle{articleNumber=14, " +
                                 "articleName='Nulldurchgangsschalter', stock=40, price=8.21, " +
                                 "orderDate=2019-05-01}ElectronicArticle{articleNumber=15, " +
                                 "articleName='Nullspannungsschalter', stock=60, price=18.31, " +
                                 "orderDate=2019-05-02}ElectronicArticle{articleNumber=70, " +
                                 "articleName='Gleitkommaeinheit, Memory Management Unit (MMU)', stock=53, price=11" +
                                 ".55, orderDate=2019-05-07}ElectronicArticle{articleNumber=71, " +
                                 "articleName='Grafikprozessor', stock=66, price=40.46, " +
                                 "orderDate=2019-06-03}ElectronicArticle{articleNumber=46, articleName='Nullode, " +
                                 "elektrodenlose Gasentladungsröhre', stock=13, price=14.14, " +
                                 "orderDate=2019-06-10}ElectronicArticle{articleNumber=47, articleName='Röhrendiode, " +
                                 "Zweipolröhre', stock=0, price=99.47, " +
                                 "orderDate=2019-06-17}ElectronicArticle{articleNumber=97, " +
                                 "articleName='Elektromagnet', stock=30, price=64.16, " +
                                 "orderDate=2019-06-21}ElectronicArticle{articleNumber=48, articleName='Triodenröhre," +
                                 " die einfachste Verstärkerröhre (Anode, Gitter, Kathode)', stock=70, price=54.68, " +
                                 "orderDate=2019-07-22}ElectronicArticle{articleNumber=16, articleName='Reed-Relais'," +
                                 " stock=87, price=72.57, orderDate=2019-08-05}ElectronicArticle{articleNumber=72, " +
                                 "articleName='Chipsatz', stock=1, price=65.47, " +
                                 "orderDate=2019-08-08}ElectronicArticle{articleNumber=98, " +
                                 "articleName='Elektromotor', stock=33, price=47.35, " +
                                 "orderDate=2019-08-13}ElectronicArticle{articleNumber=17, " +
                                 "articleName='Schwingquarze', stock=87, price=89.32, " +
                                 "orderDate=2019-08-22}ElectronicArticle{articleNumber=18, " +
                                 "articleName='Keramikresonatoren', stock=83, price=13.88, " +
                                 "orderDate=2019-08-27}ElectronicArticle{articleNumber=73, articleName='Generic Array" +
                                 " Logic (GAL), Programmable Array Logic (PAL), Field Programmable Gate Array (FPGA)" +
                                 "', stock=46, price=58.63, orderDate=2019-09-04}ElectronicArticle{articleNumber=19, " +
                                 "articleName='Quarzoszillatoren, (PXO, Quarzofen, TCXO, VXO, VCXO, DTCXO)', " +
                                 "stock=50, price=26.47, orderDate=2019-09-06}ElectronicArticle{articleNumber=20, " +
                                 "articleName='AOW-Oszillatoren', stock=4, price=82.62, " +
                                 "orderDate=2019-09-25}ElectronicArticle{articleNumber=21, articleName='AOW-Filter', " +
                                 "stock=68, price=62.44, orderDate=2019-10-07}ElectronicArticle{articleNumber=74, " +
                                 "articleName='Logikgatter, Gate Array', stock=45, price=40.68, " +
                                 "orderDate=2019-10-08}ElectronicArticle{articleNumber=22, " +
                                 "articleName='Keramikfilter', stock=41, price=62.38, " +
                                 "orderDate=2019-11-06}ElectronicArticle{articleNumber=99, " +
                                 "articleName='Drehstrom-Synchronmaschine', stock=77, price=85.95, " +
                                 "orderDate=2019-11-08}ElectronicArticle{articleNumber=100, " +
                                 "articleName='Drehstrom-Asynchronmaschine', stock=58, price=98.58, " +
                                 "orderDate=2019-11-18}ElectronicArticle{articleNumber=23, " +
                                 "articleName='Festkondensatoren', stock=22, price=68.95, " +
                                 "orderDate=2019-11-19}ElectronicArticle{articleNumber=49, articleName='Tetrode – " +
                                 "Röhre mit zwei Gittern', stock=16, price=88.23, " +
                                 "orderDate=2019-11-20}ElectronicArticle{articleNumber=24, " +
                                 "articleName='Keramikkondensator', stock=6, price=2.44, " +
                                 "orderDate=2019-11-22}ElectronicArticle{articleNumber=75, " +
                                 "articleName='Operationsverstärker', stock=55, price=82.91, " +
                                 "orderDate=2019-12-05}ElectronicArticle{articleNumber=25, " +
                                 "articleName='Kunststoff-Folienkondensator', stock=0, price=58.01, " +
                                 "orderDate=2019-12-12}ElectronicArticle{articleNumber=50, articleName='Pentode – " +
                                 "Röhre mit drei Gittern (Schirm-, Steuer- und Bremsgitter)', stock=30, price=53.22, " +
                                 "orderDate=2019-12-23}";

    private String outputString = "5 - Audio-Steckverbinder (46) Preis: 26.6 Bestelldatum: 11.04.2018\n" +
                                  "2 - Ein- und Mehrphasen-Niederspannungssysteme (3) Preis: 80.04 " +
                                  "Bestelldatum: 15.12.2017\n" +
                                  "1 - Einphasen-Haushaltsstecksysteme (18) Preis: 70.08 Bestelldatum: 22.11" +
                                  ".2017\n" +
                                  "3 - Kleinspannungsstecker (26) Preis: 53.57 Bestelldatum: 23.02.2018\n" +
                                  "4 - Labor-Steckverbinder (75) Preis: 61.54 Bestelldatum: 07.03.2018\n" +
                                  "6 - Videosignal-Steckverbinder (82) Preis: 85.73 Bestelldatum: 09.05.2018\n" +
                                  "7 - Hochfrequenz-Steckverbinder (3) Preis: 19.74 Bestelldatum: 19.06.2018\n" +
                                  "8 - Daten-Steckverbinder (79) Preis: 64.21 Bestelldatum: 16.08.2018\n" +
                                  "9 - Telefon-Steckverbinder (1) Preis: 68.94 Bestelldatum: 11.09.2018\n" +
                                  "10 - LWL-Steckverbinder (29) Preis: 57.83 Bestelldatum: 31.10.2018\n" +
                                  "11 - Schalter (97) Preis: 58.43 Bestelldatum: 04.02.2019\n" +
                                  "12 - Relais, Schütz (53) Preis: 64.87 Bestelldatum: 28.02.2019\n" +
                                  "13 - Leitungsschutzschalter, Motorschutzschalter (20) Preis: 79.57 " +
                                  "Bestelldatum: 03.04.2019\n" +
                                  "14 - Nulldurchgangsschalter (40) Preis: 8.21 Bestelldatum: 01.05.2019\n" +
                                  "15 - Nullspannungsschalter (60) Preis: 18.31 Bestelldatum: 02.05.2019\n" +
                                  "16 - Reed-Relais (87) Preis: 72.57 Bestelldatum: 05.08.2019\n" +
                                  "17 - Schwingquarze (87) Preis: 89.32 Bestelldatum: 22.08.2019\n" +
                                  "18 - Keramikresonatoren (83) Preis: 13.88 Bestelldatum: 27.08.2019\n" +
                                  "19 - Quarzoszillatoren, (PXO, Quarzofen, TCXO, VXO, VCXO, DTCXO) (50) Preis:" +
                                  " 26.47 Bestelldatum: 06.09.2019\n" +
                                  "20 - AOW-Oszillatoren (4) Preis: 82.62 Bestelldatum: 25.09.2019\n" +
                                  "21 - AOW-Filter (68) Preis: 62.44 Bestelldatum: 07.10.2019\n" +
                                  "22 - Keramikfilter (41) Preis: 62.38 Bestelldatum: 06.11.2019\n" +
                                  "23 - Festkondensatoren (22) Preis: 68.95 Bestelldatum: 19.11.2019\n" +
                                  "24 - Keramikkondensator (6) Preis: 2.44 Bestelldatum: 22.11.2019\n" +
                                  "25 - Kunststoff-Folienkondensator (0) Preis: 58.01 Bestelldatum: 12.12" +
                                  ".2019\n" +
                                  "26 - Elektrolytkondensator (8) Preis: 24.61 Bestelldatum: 25.12.2017\n" +
                                  "27 - Aluminium-Elektrolytkondensator (63) Preis: 49.22 Bestelldatum: 05.04" +
                                  ".2018\n" +
                                  "28 - Tantal-Elektrolytkondensator (38) Preis: 6.54 Bestelldatum: 10.04" +
                                  ".2018\n" +
                                  "29 - Niob-Elektrolytkondensator (77) Preis: 96.72 Bestelldatum: 12.06.2018\n" +
                                  "30 - Polymer-Elektrolytkondensator (7) Preis: 57.73 Bestelldatum: 24.07" +
                                  ".2018\n" +
                                  "31 - Superkondensator (Doppelschichtkondensator) (81) Preis: 6.9 " +
                                  "Bestelldatum: 27.08.2018\n" +
                                  "32 - Lithium-Ionen-Kondensator (26) Preis: 53.11 Bestelldatum: 28.09.2018\n" +
                                  "33 - Glimmerkondensator (56) Preis: 33.26 Bestelldatum: 03.10.2018\n" +
                                  "34 - Vakuumkondensator (36) Preis: 48.25 Bestelldatum: 17.12.2018\n" +
                                  "35 - Leistungskondensatoren (89) Preis: 39.28 Bestelldatum: 02.01.2019\n" +
                                  "36 - Induktivität (Bauelement), (39) Preis: 22.75 Bestelldatum: 03.01.2019\n" +
                                  "37 - Chipinduktivität, Mikroinduktivität (74) Preis: 21.85 Bestelldatum: 16" +
                                  ".01.2019\n" +
                                  "38 - Spule (Elektrotechnik), (67) Preis: 65.56 Bestelldatum: 11.02.2019\n" +
                                  "39 - Luftspule, Toroidspule, Zündspule, Schwingspule, Tauchspule," +
                                  " Helmholtz-Spule (94) Preis: 50.47 Bestelldatum: 14.03.2019\n" +
                                  "40 - Drossel (Elektrotechnik) (0) Preis: 47.09 Bestelldatum: 27.03.2019\n" +
                                  "41 - Transformatoren (72) Preis: 28.46 Bestelldatum: 02.04.2019\n" +
                                  "42 - Streufeldtransformator (35) Preis: 51.53 Bestelldatum: 04.04.2019\n" +
                                  "43 - Übertrager (80) Preis: 48.65 Bestelldatum: 17.04.2019\n" +
                                  "44 - Balun (11) Preis: 10.38 Bestelldatum: 25.04.2019\n" +
                                  "45 - Magnete (6) Preis: 8.84 Bestelldatum: 26.04.2019\n" +
                                  "46 - Nullode, elektrodenlose Gasentladungsröhre (13) Preis: 14.14 " +
                                  "Bestelldatum: 10.06.2019\n" +
                                  "47 - Röhrendiode, Zweipolröhre (0) Preis: 99.47 Bestelldatum: 17.06.2019\n" +
                                  "48 - Triodenröhre, die einfachste Verstärkerröhre (Anode, Gitter, Kathode) " +
                                  "(70) Preis: 54.68 Bestelldatum: 22.07.2019\n" +
                                  "49 - Tetrode – Röhre mit zwei Gittern (16) Preis: 88.23 Bestelldatum: 20.11" +
                                  ".2019\n" +
                                  "50 - Pentode – Röhre mit drei Gittern (Schirm-, Steuer- und Bremsgitter) " +
                                  "(30) Preis: 53.22 Bestelldatum: 23.12.2019\n" +
                                  "51 - Hexode – Röhre mit vier Gittern (Steuer- und Bremsgitter, zwei " +
                                  "Schirmgitter) (69) Preis: 32.09 Bestelldatum: 23.11.2017\n" +
                                  "52 - Braun’sche Röhre: Kathodenstrahlröhre (83) Preis: 79.15 Bestelldatum: " +
                                  "10.01.2018\n" +
                                  "53 - Röntgenröhre (5) Preis: 61.47 Bestelldatum: 12.01.2018\n" +
                                  "54 - Klystron (42) Preis: 27.99 Bestelldatum: 26.01.2018\n" +
                                  "55 - Krytron (99) Preis: 65.69 Bestelldatum: 15.03.2018\n" +
                                  "56 - Magnetron (73) Preis: 85.14 Bestelldatum: 03.05.2018\n" +
                                  "57 - Sekundärelektronenvervielfacher (46) Preis: 93.14 Bestelldatum: 10.05" +
                                  ".2018\n" +
                                  "58 - Photomultiplier (24) Preis: 4.64 Bestelldatum: 06.06.2018\n" +
                                  "59 - Thyratron (32) Preis: 27.33 Bestelldatum: 01.08.2018\n" +
                                  "60 - Excitron (15) Preis: 37.15 Bestelldatum: 06.09.2018\n" +
                                  "61 - Ignitron (16) Preis: 85.92 Bestelldatum: 10.09.2018\n" +
                                  "62 - Senditron (91) Preis: 5.82 Bestelldatum: 27.09.2018\n" +
                                  "63 - Quecksilberdampfgleichrichter (0) Preis: 72.69 Bestelldatum: 15.01" +
                                  ".2019\n" +
                                  "64 - Verbundröhre (52) Preis: 98.97 Bestelldatum: 25.01.2019\n" +
                                  "65 - Abstimmanzeigeröhre (0) Preis: 66.53 Bestelldatum: 26.02.2019\n" +
                                  "66 - Nixie-Röhre – Röhre zur Darstellung verschiedener Zeichen (Ziffern) " +
                                  "(48) Preis: 52.03 Bestelldatum: 01.03.2019\n" +
                                  "67 - ROM, PROM (nur lesbare Speicher) (33) Preis: 59.9 Bestelldatum: 12.03" +
                                  ".2019\n" +
                                  "68 - RAM (DRAM, SRAM), EPROM, EEPROM, Flash-EPROM (les- und beschreibbare " +
                                  "Speicher) (13) Preis: 80.09 Bestelldatum: 24.04.2019\n" +
                                  "69 - Mikrocontroller, Mikroprozessor (CPU), Digitaler Signalprozessor (DSP) " +
                                  "(99) Preis: 13.88 Bestelldatum: 25.04.2019\n" +
                                  "70 - Gleitkommaeinheit, Memory Management Unit (MMU) (53) Preis: 11.55 " +
                                  "Bestelldatum: 07.05.2019\n" +
                                  "71 - Grafikprozessor (66) Preis: 40.46 Bestelldatum: 03.06.2019\n" +
                                  "72 - Chipsatz (1) Preis: 65.47 Bestelldatum: 08.08.2019\n" +
                                  "73 - Generic Array Logic (GAL), Programmable Array Logic (PAL), Field " +
                                  "Programmable Gate Array (FPGA) (46) Preis: 58.63 Bestelldatum: 04.09.2019\n" +
                                  "74 - Logikgatter, Gate Array (45) Preis: 40.68 Bestelldatum: 08.10.2019\n" +
                                  "75 - Operationsverstärker (55) Preis: 82.91 Bestelldatum: 05.12.2019\n" +
                                  "76 - Spannungsregler, Schaltregler (77) Preis: 18.6 Bestelldatum: 29.11" +
                                  ".2017\n" +
                                  "77 - Digital-Analog-Umsetzer, Analog-Digital-Umsetzer (39) Preis: 41.41 " +
                                  "Bestelldatum: 30.11.2017\n" +
                                  "78 - Multiplexer (60) Preis: 20.87 Bestelldatum: 06.12.2017\n" +
                                  "79 - Laserdiode (90) Preis: 28.9 Bestelldatum: 11.01.2018\n" +
                                  "80 - Leuchtdiode (0) Preis: 45.26 Bestelldatum: 17.01.2018\n" +
                                  "81 - Lichtschranke (22) Preis: 3.34 Bestelldatum: 23.01.2018\n" +
                                  "82 - Photohalbleiter (61) Preis: 27.73 Bestelldatum: 22.02.2018\n" +
                                  "83 - Fotowiderstand (91) Preis: 79.99 Bestelldatum: 13.03.2018\n" +
                                  "84 - Halbleiter-Strahlungsdetektoren (19) Preis: 16.95 Bestelldatum: 16.03" +
                                  ".2018\n" +
                                  "85 - Photoelemente (36) Preis: 69.4 Bestelldatum: 18.05.2018\n" +
                                  "86 - Silizium-Photodiode, pin-Diode, Avalanche-Photodiode (82) Preis: 41.13 " +
                                  "Bestelldatum: 23.05.2018\n" +
                                  "87 - Solarzelle (91) Preis: 70.43 Bestelldatum: 04.06.2018\n" +
                                  "88 - Selen-Photoelement (0) Preis: 63.33 Bestelldatum: 13.06.2018\n" +
                                  "89 - Fototransistor (44) Preis: 22.67 Bestelldatum: 17.09.2018\n" +
                                  "90 - Fotothyristor (Optothyristor) (33) Preis: 10.12 Bestelldatum: 08.10" +
                                  ".2018\n" +
                                  "91 - Optokoppler, Solid-state-Relais (23) Preis: 51.98 Bestelldatum: 18.10" +
                                  ".2018\n" +
                                  "92 - CCD-Sensoren, CMOS-Sensoren, Bildsensor (77) Preis: 53.6 Bestelldatum: " +
                                  "02.11.2018\n" +
                                  "93 - Lichtwellenleiter (LWL) (75) Preis: 55.56 Bestelldatum: 05.11.2018\n" +
                                  "94 - Dünnschichttransistor (TFT) (91) Preis: 53.75 Bestelldatum: 27.11" +
                                  ".2018\n" +
                                  "95 - OLED (45) Preis: 72.22 Bestelldatum: 12.02.2019\n" +
                                  "96 - Grating Light Valve (1337) Preis: 96.99 Bestelldatum: 26.03.2019\n" +
                                  "97 - Elektromagnet (30) Preis: 64.16 Bestelldatum: 21.06.2019\n" +
                                  "98 - Elektromotor (33) Preis: 47.35 Bestelldatum: 13.08.2019\n" +
                                  "99 - Drehstrom-Synchronmaschine (77) Preis: 85.95 Bestelldatum: 08.11.2019\n" +
                                  "100 - Drehstrom-Asynchronmaschine (58) Preis: 98.58 Bestelldatum: 18.11" +
                                  ".2019\n" +
                                  "101 - Gleichstrommaschine (26) Preis: 91.24 Bestelldatum: 05.04.2019\n" +
                                  "102 - Lautsprecher (36) Preis: 48.06 Bestelldatum: 23.04.2019\n" +
                                  "103 - Flüssigkristallbildschirm (LCD) (11) Preis: 34.11 Bestelldatum: 30.04" +
                                  ".2019\n";
}
