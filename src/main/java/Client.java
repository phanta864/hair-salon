import java.util.List;

import org.sql2o.*;

public class Client {

    private int id;
    private String name;
    private Integer age;
    private String firstappearance;
    private String neighbourhood;
    private int stylistId;

    public Client(String name, Integer age, String firstappearance, String neighbourhood, int stylistId){
        this.name =name;
        this.age = age;
        this.firstappearance = firstappearance;
        this.neighbourhood = neighbourhood;
        this.stylistId = stylistId;
    }

    public int getId(){

        return id;
    }

    public String getClientName(){

        return name;
    }
    public Integer getClientAge(){

        return age;
    }

    public int getStylistId() {
        return stylistId;
    }

    public String getClientFirstappearance(){
        return firstappearance;
    }

    public String getClientNeighbourhood(){

        return neighbourhood;
    }


    public static List<Client> all() {
        String sql = "SELECT id, name, age, firstappearance, neighbourhood FROM clients";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Client.class);
        }
    }

    public static Client find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM clients where id=:id";
            Client client = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Client.class);
            return client;
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO clients(name, age, firstappearance, neighbourhood, stylistId) VALUES (:name, :age, :firstappearance, :neighbourhood,:stylistId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("firstappearance", this.firstappearance)
                    .addParameter("neighbourhood", this.neighbourhood)
                    .addParameter("stylistId", this.stylistId)
                    .executeUpdate()
                    .getKey();
        }
    }

    public void update(String name, Integer age, String firstappearance,String neighbourhood){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE clients SET (name, age, firstappearance, neighbourhood) = (:name, :age, :firstappearance, :neighbourhood) WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("age", age)
                    .addParameter("firstappearance", firstappearance)
                    .addParameter("neighbourhood", neighbourhood)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public void deleteClient(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM clients WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public boolean equals(Object otherClient){
        if(!(otherClient instanceof Client)){
            return false;
        }
        else{
            Client newClient = (Client) otherClient;

            return this.getClientName().equals(newClient.getClientName()) &&
                    this.getId() == newClient.getId()
                    && this.getStylistId() == newClient.getStylistId();
        }
    }
}