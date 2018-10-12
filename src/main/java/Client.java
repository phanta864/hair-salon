public class Client {
    private String mName;
    private Integer mAge;
    private String mFirstAppearance;
    private String mNeighbourhood;


    public Client(String name, Integer age, String firstAppearance, String neighbourhood){
        mName =name;
        mAge = age;
        mFirstAppearance = firstAppearance;
        mNeighbourhood = neighbourhood;
    }

    public String getName(){
        return mName;
    }
    public Integer getAge(){
        return mAge;
    }
    public String getFirstAppearance(){
        return mFirstAppearance;
    }
    public String getNeighbourhood(){
        return mNeighbourhood;
    }
}
