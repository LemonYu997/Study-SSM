package dao;

public interface AccountDao {
    /**
     * 加钱
     * */
    public void out(String outMan, double money);

    /**
     * 减钱
     * */
    public void in(String inMan, double money);
}
