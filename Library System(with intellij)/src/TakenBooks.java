public class TakenBooks {
    private String bookid;
    private  String bookname;
    private String username;
    private String time;
    public TakenBooks() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TakenBooks(String bookid, String bookname, String username, String time) {
        this.bookid = bookid;
        this.bookname=bookname;
        this.username=username;
        this.time=time;
    }





    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
