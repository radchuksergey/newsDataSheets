package domain;

/**
 * Created by sergey on 25.01.2016.
 */


public class User {
    private String id ;
    private String userName ;
    private String encryptedPassword ;
    private String salt ;
    private UserType userType;



   public User(String userName, String encryptedPassword, String salt, String userType){
       this.id = null;
       this.userName = userName;
       this.encryptedPassword = encryptedPassword;
       this.salt = salt;
       this.userType = UserType.valueOf(userType);

   }

    public User(String id, String userName, String encryptedPassword, String salt, String userType){
        this.id = id;
        this.userName = userName;
        this.encryptedPassword = encryptedPassword;
        this.salt = salt;
        this.userType = UserType.valueOf(userType);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setUserType(String userType){
        this.userType = UserType.valueOf(userType);
    }



}
