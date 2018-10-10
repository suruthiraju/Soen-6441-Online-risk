package Controller;

import Model.welcomeScreenModel;
import View.welcomeScreenView;

public class welcomeScreen {

    //public static void main(String[] args) {
      
        welcomeScreenView theView = new welcomeScreenView();

        welcomeScreenModel theModel = new welcomeScreenModel();

        welcomeScreenController theController = new welcomeScreenController(theView,theModel);

        //theView.setVisible(true);
        
    //}
}