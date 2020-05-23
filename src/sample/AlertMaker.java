package sample;

import javafx.scene.control.Alert;

public  class AlertMaker {
    private String tittle;
    private String headerText;
    private String contextText;

    public AlertMaker(String tittle, String headerText, String contextText){

        this.tittle = tittle;
        this.headerText = headerText;
        this.contextText = contextText;
    }

    public void makeAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(tittle);
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getContextText() {
        return contextText;
    }

    public void setContextText(String contextText) {
        this.contextText = contextText;
    }


}
