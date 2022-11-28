public class User {
    protected String userId;
    protected String password;
    boolean isLoggedIn = false;
    User(String id, String password){
        userId = id;
        this.password = password;
        isLoggedIn = true;
    }


    User(){
    }
}