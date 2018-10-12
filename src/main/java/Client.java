public class Client {
    private String mName;
    private int mAge;
    private String mFirstAppearance;
    private String mNeighbourhood;


    public Client(String name, int age, String firstAppearance, String neighbourhood){
        mName =name;
        mAge = age;
        mFirstAppearance = firstAppearance;
        mNeighbourhood = neighbourhood;
    }

    public String getName(){
        return mName;
    }
}
