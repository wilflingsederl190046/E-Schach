class Arzt<T extends Student> {
    public Arzt() {

    }

    public T behandlung(T lebewesen) {
        lebewesen.doThings();
        return lebewesen;
    }
}
