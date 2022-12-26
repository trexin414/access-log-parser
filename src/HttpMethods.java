enum HttpMethods {
    GET("GET"), POST("POST");
    final String txt;

    private HttpMethods(String txt){
        this.txt = txt;
    }
}
