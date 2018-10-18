import org.sql2o.Connection;

import java.util.List;

;

public class Stylist {
    private int id;
    private String firstname;
    private String secondname;
    private String lastname;
    private Integer phonenumber;
    private Integer idnumber;
    private String email;

    public Stylist(String firstname, String secondname, String lastname, Integer phonenumber, Integer idnumber, String email){
        this.firstname = firstname;
        this.secondname = secondname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.idnumber = idnumber;
        this.email = email;
    }

    public String getFirstname(){
        return firstname;
    }
    public String getSecondname(){
        return secondname;
    }

    public String getLastname(){
        return lastname;
    }

    public Integer getphonenumber(){
        return phonenumber;
    }

    public Integer getIdnumber(){
        return idnumber;
    }

    public String getEmail(){
        return email;
    }
    public int getId(){
        return id;
    }

    public static List<Stylist> allStylist(){
        String sql = "SELECT * FROM stylists;";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Stylist.class);
        }
    }
    public List<Client> getClients(){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM clients where stylistid=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Client.class);
        }
    }

    public static Stylist find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM stylists where id=:id";
            Stylist stylist = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Stylist.class);
            return stylist;
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO stylists (firstname, secondname, lastname, phonenumber, idnumber, email) VALUES (:firstname, :secondname, :lastname, :phonenumber, :idnumber, :email)";
            this.id= (int) con.createQuery(sql, true)
                    .addParameter("firstname", this.firstname)
                    .addParameter("secondname", this.secondname)
                    .addParameter("lastname", this.lastname)
                    .addParameter("phonenumber", this.phonenumber)
                    .addParameter("idnumber", this.idnumber)
                    .addParameter("email", this.email)
                    .executeUpdate()
                    .getKey();
        }
    }

    public void update(String name, String style){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE stylists SET (firstname, secondname, lastname, phonenumber, idnumber, email) = (:firstname, :secondname, :lastname, :phonenumber, :idnumber, :email) WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("firstname", firstname)
                    .addParameter("secondname", secondname)
                    .addParameter("lastname", lastname)
                    .addParameter("phonenumber", phonenumber)
                    .addParameter("idnumber", idnumber)
                    .addParameter("email", email)
                    .addParameter("id", this.id)
                    .executeUpdate();
        }
    }

    public void delete(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM stylists WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
        }
    }

    @Override
    public boolean equals(Object otherStylist){
        if(!(otherStylist instanceof Stylist)){
            return false;
        }
        else{
            Stylist newStylist = (Stylist) otherStylist;
            return this.getFirstname().equals(newStylist.getFirstname());
        }
    }
}