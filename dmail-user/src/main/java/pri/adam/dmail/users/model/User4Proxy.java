package pri.adam.dmail.users.model;

import java.util.Set;

/**
 * Created by adam on 2014/12/6.
 */
public class User4Proxy {
    private int id;
    private String username;
    private String pwdHash;

//    在用户指定加载时才加载
    private Set<Contract> contracts;

    public User4Proxy(){}

    public User4Proxy(String username,String pwdHash){
        this.username = username;
        this.pwdHash = pwdHash;
        contracts = null;
    }

    public User4Proxy(int id,String username,String pwdHash){
        this(username,pwdHash);
        this.id = id;
        contracts = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwdHash() {
        return pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }

    public User4Server toUser4Server(){
        assert this!=null;

        return new User4Server(username,pwdHash);
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User4Proxy))
            return false;

        User4Proxy proxy = (User4Proxy)obj;

        if(!username.equals(proxy.getUsername()))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "User4Proxy{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pwdHash='" + pwdHash + '\'' +
                '}';
    }

    public Object[] toObjectArray(){
        return new Object[]{username,pwdHash};
    }
}
