package Manager_models;



public class RegisterForm  {
    private String name;
    private String password;
    private String email;
    private String phone; // Номер телефона пользователя
    private String photoPath; // Путь к фото пользователя (опционально)

    // Конструктор
    public RegisterForm(String name, String password, String email, String phone, String photoPath) {
        this.name =name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.photoPath = photoPath;
    }

    // Геттеры и сеттеры
    public String getrname() {
        return name;
    }

    public void setname(String username) {
        this.name = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "RegisterForm{" +
                "username='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }
}

