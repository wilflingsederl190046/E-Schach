import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ElectronicStockMain {
    public static final String STOCK_FILENAME = "stock.csv";

    public static void main(String[] args) {
        StockManagement electronicStockManagement = new StockManagement(STOCK_FILENAME);

        try {
            electronicStockManagement.loadStockFiles();
        } catch (IOException e) {
            System.err.printf("Die Datei %s konnte nicht geladen werden\n", STOCK_FILENAME);
            e.printStackTrace();
            System.exit(1);
        }

        while (true) {
            printMainMenu();
            int userSelection = (int) readUserSelection();

            switch (userSelection) {
                case 1: {
                    electronicStockManagement.printAllArticles();
                    break;
                }
                case 2: {
                    electronicStockManagement.printArticles(electronicStockManagement.selectSoldArticles());
                    break;
                }
                case 3: {
                    float price = (float) readUserSelection("Preis");
                    electronicStockManagement.printArticles(electronicStockManagement.selectArticlesWherePriceIsLessThan(price));
                    break;
                }
                case 4: {
                    int n = (int) readUserSelection("n");
                    electronicStockManagement.printArticles(electronicStockManagement.selectTopNArticlesDependingOnPrice(n));
                    break;
                }
                case 5: {
                    electronicStockManagement.printArticles(electronicStockManagement.sortArticlesDependingOnDate());
                    break;
                }
                case 6: {
                    int date = (int) readUserSelection("Datum im Format yyyyMMdd");
                    electronicStockManagement.printArticles(electronicStockManagement.selectArticlesAfterDate(date));
                    break;
                }
                case 7: {
                    p(electronicStockManagement.calculateAverageArticlePrice() + "");
                    break;
                }
                case 8: {
                    p(electronicStockManagement.calculateStockValueSerial() + "");
                    break;
                }
                case 9: {
                    p(electronicStockManagement.calculateStockValueParallel() + "");
                    break;
                }
                case 10: {
                    String longestArticleName = electronicStockManagement.selectLongestArticleName();
                    p(longestArticleName + "");
                    p("Länge: " + longestArticleName.length() + "");
                    break;
                }
                case 11: {
                    int articleNumber = (int) readUserSelection("Artikelnummer");

                    ElectronicArticle article = electronicStockManagement.findArticleByArticleNumber(articleNumber);

                    if(article == null) {
                        p(String.format("Der Artikel mit der id %d wurde nicht gefunden", articleNumber));
                    } else {
                        p(article.toString());
                    }

                    break;
                }
                case 12: {
                    int articleNumber1 = (int) readUserSelection("Artikelnummer A");
                    int articleNumber2 = (int) readUserSelection("Artikelnummer B");

                    ElectronicArticle articleA = electronicStockManagement.findArticleByArticleNumber(articleNumber1);
                    ElectronicArticle articleB = electronicStockManagement.findArticleByArticleNumber(articleNumber2);

                    electronicStockManagement.printDaysBetweenArticles(articleA, articleB);
                    break;
                }
                case 13: {
                    int taskCount = (int) readUserSelection("Anzahl der Tasks die angelegt werden sollen");
                    try {
                        p(electronicStockManagement.calculateMaxStoreCountParallel(taskCount) + "");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 99: {
                    System.exit(0);
                    break;
                }
                default: {
                    p("Falsche Eingabe");
                }
            }
        }
    }

    private static double readUserSelection() {
        return readUserSelection("");
    }

    private static double readUserSelection(String msg) {
        System.out.print(msg + (msg.equals("") ? "" : " ") + ">");
        Scanner in = new Scanner(System.in);
        try {
            return in.nextDouble();
        } catch(InputMismatchException e) {
            return -1;
        }
    }

    private static String readUserSelectionString(String msg) {
        System.out.print(msg + (msg.equals("") ? "" : " ") + ">");
        Scanner in = new Scanner(System.in);
        return in.next();
    }

    public static void printMainMenu() {
        p("+--------------------------------------------------------+");
        p("| Lagerverwaltung v 1.0                                  |");
        p("|                                                        |");
        p("| Hauptmenue                                             |");
        p("|  1 - Alle Artikel ausgeben                             |");
        p("|  2 - Verkaufte Artikel ausgeben                        |");
        p("|  3 - Artikel unter einem bestimmten Preis ausgeben     |");
        p("|  4 - Top n der teuersten Artikel ausgeben              |");
        p("|  5 - Artikel nach Datum sortiert ausgeben              |");
        p("|  6 - Artikel nach einem bestimmten Datum ausgeben      |");
        p("|  7 - Durchschnittlichen Artikelpreis berechnen         |");
        p("|  8 - Lagerwert seriell berechnen                       |");
        p("|  9 - Lagerwert parallell berechnen                     |");
        p("| 10 - Laengsten Artikelnamen ausgeben                   |");
        p("| 11 - Nach Artikelnummer suchen                         |");
        p("| 12 - Alle Tage zwischen zwei Bestellungen ausgeben     |");
        p("| 13 - Maximalen Lagerbestand parallell finden           |");
        p("| 99 - Programm beenden                                  |");
        p("+--------------------------------------------------------+");
    }

    public static void p(String s) {
        System.out.println(s);
    }
}
