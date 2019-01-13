package hska.microServiceWebShop.Clients;

public class ApiException extends Exception {
    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    private String message;
    private int code;

    public ApiException(int code, String message){
        this.message = message;
        this.code = code;
    }
}

