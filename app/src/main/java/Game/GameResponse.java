package Game;

public class GameResponse {
    String message;
    TypeOfResponse typeOfResponse;

    public GameResponse(String message, TypeOfResponse typeOfResponse) {
        this.message = message;
        this.typeOfResponse = typeOfResponse;
    }

    public String getMessage() {
        return message;
    }

    public TypeOfResponse getTypeOfResponse() {
        return typeOfResponse;
    }
}
