package Manager_models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


    public class User {
        private final IntegerProperty id = new SimpleIntegerProperty();
        private final StringProperty name = new SimpleStringProperty();
        private final StringProperty email = new SimpleStringProperty();
        private final StringProperty phone = new SimpleStringProperty();

        public User(int id, String name, String email, String phone) {
            this.id.set(id);
            this.name.set(name);
            this.email.set(email);
            this.phone.set(phone);
        }

        public IntegerProperty idProperty() {
            return id;
        }

        public StringProperty nameProperty() {
            return name;
        }

        public StringProperty emailProperty() {
            return email;
        }

        public StringProperty phoneProperty() {
            return phone;
        }
       }
