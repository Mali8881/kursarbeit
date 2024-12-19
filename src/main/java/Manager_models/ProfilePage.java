package Manager_models;


public class ProfilePage {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String photoPath;

    public ProfilePage(int userId, String username, String email, String password, String photoPath) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.photoPath = photoPath;
    }

    // Геттеры и сеттеры
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhotoPath() { return photoPath; }
    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }
}
