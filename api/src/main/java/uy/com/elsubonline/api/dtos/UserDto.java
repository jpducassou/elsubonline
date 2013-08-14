package uy.com.elsubonline.api.dtos;

public class UserDto {

    private String email;
    private String first_name;
    private String last_name;
    private String nick_name;
    private boolean administrator;

    public UserDto(String email, String first_name, String last_name, String nick_name, boolean administrator) {
        this.email         = email;
        this.first_name    = first_name;
        this.last_name     = last_name;
        this.nick_name     = nick_name;
        this.administrator = administrator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

}
