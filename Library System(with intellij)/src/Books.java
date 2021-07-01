public class Books {
    private  String bookid;
    private String name;
    private  String author;
    private  int pages;
    Books(){
        this.name="";
        this.bookid ="";
        this.author="";
        this.pages=0;
    }
    Books(String bookid, String name, String author, int pages){
        this.name=name;
        this.bookid = bookid;
        this.author=author;
        this.pages=pages;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
