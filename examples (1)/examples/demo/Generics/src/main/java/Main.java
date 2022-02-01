public class Main {
    public static void main(String[] args) {
        Arzt<Mensch> menschenarzt = new Arzt();

        Mensch result = menschenarzt.behandlung(new Mensch());

        Arzt<Tier> tierArzt = new Arzt<Tier>();
    }
}
