import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class StockManagementTest {

    @Test
    public void selectArticlesWherePriceIsLessThanTest() {
        List<ElectronicArticle> articles = createArticles();
        int price = 6;
        StockManagement sm = new StockManagement(articles);

        List<ElectronicArticle> result = sm.selectArticlesWherePriceIsLessThan(price);
        List<ElectronicArticle> expResult = new ArrayList<>();
        for(ElectronicArticle e : articles) {
            if(e.getPrice() < price) {
                expResult.add(e);
            }
        }
        assertEquals(expResult, result);
    }

    @Test
    public void selectArticlesAfterDateTest() {
        List<ElectronicArticle> articles = createArticles();
        int date = 20180606;
        LocalDate localDate = LocalDate.of(2018, 6, 6);
        StockManagement sm = new StockManagement(articles);

        List<ElectronicArticle> result = sm.selectArticlesAfterDate(date);
        List<ElectronicArticle> expResult = new ArrayList<>();
        for(ElectronicArticle e : articles) {
            if(e.getOrderDate().isAfter(localDate) || e.getOrderDate().isEqual(localDate)) {
                expResult.add(e);
            }
        }
        assertEquals(expResult, result);
    }

    @Test
    public void selectTopNArticlesDependingOnPriceTest() {
        List<ElectronicArticle> articles = createArticles();
        int n = 10;
        StockManagement sm = new StockManagement(articles);

        List<ElectronicArticle> result = sm.selectTopNArticlesDependingOnPrice(n);
        List<ElectronicArticle> expResult = new ArrayList<>();
        List<Float> articlesPrice = new ArrayList<>();
        for(ElectronicArticle e : articles) {
            articlesPrice.add(e.getPrice());
        }
        Collections.sort(articlesPrice);
        Collections.reverse(articlesPrice);
        for(ElectronicArticle e : articles) {
            if(e.getPrice() > articlesPrice.get(n)) {
                expResult.add(e);
            }
        }
        Comparator<ElectronicArticle> comp = new Comparator<ElectronicArticle>() {
            @Override
            public int compare(ElectronicArticle o1, ElectronicArticle o2) {
                return Float.compare(o1.getPrice(), o2.getPrice());
            }
        };
        expResult.sort(comp);
        Collections.reverse(expResult);
        assertEquals(expResult, result);
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
}