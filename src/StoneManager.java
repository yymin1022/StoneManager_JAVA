import Controller.UIController;
import View.StoneManagerView;

public class StoneManager{
    public static void main(String[] arg0) {
        StoneManagerView stoneManagerView = new StoneManagerView();
        new UIController(stoneManagerView);
    }
}