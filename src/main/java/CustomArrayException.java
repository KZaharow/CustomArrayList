class CustomArrayException extends Exception {
    private final String msg;

    public CustomArrayException(String msg) {
        this.msg = msg;
    }

    public void getMsg() {
        System.out.println(msg);
    }

}
