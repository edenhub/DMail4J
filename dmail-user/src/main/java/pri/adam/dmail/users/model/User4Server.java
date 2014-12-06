package pri.adam.dmail.users.model;

/**
 * Created by adam on 2014/12/6.
 */
public class User4Server {
    private String username;
    private String pwdHash;
    private String pwdAlgorithm;
    private int useForwarding;
    private String forwardDestination;
    private int useAlias;
    private String alias;

    public User4Server(){}

    public User4Server(String username,String pwdHash){
        this.username = username;
        this.pwdHash = pwdHash;
        pwdAlgorithm = "SHA";
        useForwarding = 0;
        forwardDestination = null;
        useAlias = 0;
        alias = null;
    }

    public User4Server(String username,String pwdHash,String pwdAlgorithm){
        this(username,pwdHash);
        this.pwdAlgorithm = pwdAlgorithm;
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

    public String getPwdAlgorithm() {
        return pwdAlgorithm;
    }

    public void setPwdAlgorithm(String pwdAlgorithm) {
        this.pwdAlgorithm = pwdAlgorithm;
    }

    public int getUseForwarding() {
        return useForwarding;
    }

    public void setUseForwarding(int useForwarding) {
        this.useForwarding = useForwarding;
    }

    public String getForwardDestination() {
        return forwardDestination;
    }

    public void setForwardDestination(String forwardDestination) {
        this.forwardDestination = forwardDestination;
    }

    public int getUseAlias() {
        return useAlias;
    }

    public void setUseAlias(int useAlias) {
        this.useAlias = useAlias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User4Server))
            return false;

        User4Server user = (User4Server)obj;

        if (!username.equals(user.getUsername()))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "User4Server{" +
                "username='" + username + '\'' +
                ", pwdHash='" + pwdHash + '\'' +
                ", pwdAlgorithm='" + pwdAlgorithm + '\'' +
                ", useForwarding=" + useForwarding +
                ", forwardDestination='" + forwardDestination + '\'' +
                ", useAlias=" + useAlias +
                ", alias='" + alias + '\'' +
                '}';
    }

    public Object[] toObjectArray(){
        return new Object[]{username,pwdHash,pwdAlgorithm};
    }
}
