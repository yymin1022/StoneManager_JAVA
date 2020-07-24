import Controller.BTController;
import Controller.UIController;
import Utils.Log;
import View.StoneManagerView;

import javax.bluetooth.*;

public class StoneManager{
    public static void main(String[] arg0) {
        StoneManagerView stoneManagerView = new StoneManagerView();
        UIController uiController = new UIController(stoneManagerView);
    }
}