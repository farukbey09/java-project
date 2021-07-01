public class BasicInfo {
    private String username;
    private String password;
    private String name;
    private String gender;


    public BasicInfo() {

    }
    public BasicInfo(String username,String password,String name,String gender){
        this.username=username;
        this.password=password;
        this.name=name;
        this.gender=gender;


    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}