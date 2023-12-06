package DTO;

public class ContatoDTO {
    private int ID_User;
    private String name_user;
    private String email_user;
    private String telefone_user;

    public String getTelefone_user() {
        return telefone_user;
    }

    public void setTelefone_user(String telefone_user) {
        this.telefone_user = telefone_user;
    }

    public int getID_User() {
        return ID_User;
    }

    public void setID_User(int ID_User) {
        this.ID_User = ID_User;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }
}
