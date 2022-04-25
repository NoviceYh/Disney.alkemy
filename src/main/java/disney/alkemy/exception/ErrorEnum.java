
package disney.alkemy.exception;

public enum ErrorEnum {
    IDMOVIENOTVALID("Id movie not valid"),
    IDCHARACTERNOTVALID("Id character not valid"),
    IDGENRENOTVALID("Id genre not valid"),
    USERALREADYEXIST("User already exist"),
    USERORPASSWORDINCORRECT("Incorrect username or password"),
    USERORPASSWORDNOTFOUND("Username or password not found"),
    PARAMNOTFOUND("Param not found"),
    ERRORTOSENDEMAIL("Error trying to send the email");
    
    private String message;
    
    ErrorEnum(String message){this.message = message;}

    public String getMessage() {return this.message;}    
}
