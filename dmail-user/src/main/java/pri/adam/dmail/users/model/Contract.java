package pri.adam.dmail.users.model;

/**
 * Created by adam on 2014/12/6.
 */
public class Contract {
    private int id;
    private String mail;
    private String alias;
    private String content;
    private int tofriend;

    public Contract(){}

    public Contract(String mail){
        this.mail = mail;
    }

    public Contract(String mail,int tofriend){
        this(mail);
        this.tofriend = tofriend;
    }

    public Contract(int id,String mail,String alias,int tofriend){
        this.id = id;
        this.mail = mail;
        this.alias = alias;
        this.tofriend = tofriend;
    }

    public Contract(int id, String mail, String alias, String content, int tofriend) {
        this(id,mail,alias,tofriend);
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTofriend() {
        return tofriend;
    }

    public void setTofriend(int tofriend) {
        this.tofriend = tofriend;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Contract))
            return false;

        Contract contract = (Contract)obj;

        if (!mail.equals(contract.getMail()) || tofriend!=contract.getTofriend())
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", alias='" + alias + '\'' +
                ", content='" + content + '\'' +
                ", tofriend=" + tofriend +
                '}';
    }
}
