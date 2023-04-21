package model;

public class User
{
    //region attributes
    private int id;
    private String mName;
    private String mLastname;
    private String mEmail;
    private String mPassword;
    //endregion

    //region constructor
    public User(){}
    public User(int id, String mName, String mLastname, String mEmail, String mPassword) {
        this.id = id;
        this.mName = mName;
        this.mLastname = mLastname;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
    }
    //endregion

    //region getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmLastname() {
        return mLastname;
    }

    public void setmLastname(String mLastname) {
        this.mLastname = mLastname;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }
    //endregion
}
