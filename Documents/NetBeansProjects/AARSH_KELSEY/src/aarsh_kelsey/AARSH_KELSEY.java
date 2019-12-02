/*11/27/19
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherplantwater;

//imports for java fx user interface
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import static javafx.scene.text.TextAlignment.CENTER;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

//imports for jar file and extracting weather data
import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.*;
import com.google.gson.reflect.*;




/**
 *
 * @author Kelsey
 */
public class WeatherPlantWater extends Application {
 HBox welcomeBox;
 
 //put pictures in gridpane
 // Team Assignment 4 = Modularization with API
 //Team Assignment 5 = Test & show Test
 //Team Assignment 6 = video showing interface
    
//Buttons
public int j =3;
Button waterButton;
Button waterButton2;
Button waterButton3;
public Button flowersbutton;

ImageView flower1View;
ImageView flower2View;
ImageView flower3View;



VBox vbox1;
//Labels

public static Label CountryLabel = new Label();
public static Label CityLabel = new Label();
public static Label WeatherDescriptionLabel = new Label();
public static Label TemperatureLabel = new Label();
public Label flower1Label = new Label();
public Label flower2Label = new Label();
public Label flower3Label = new Label();
 public Label selectcityLabel;
 public Label selectflowersLabel;
 
String img1text;
String img2text;
String img3text;




 //Flower CheckBox
 CheckBox roseCheckBox;
 CheckBox marigoldCheckBox;
 CheckBox petuniaCheckBox;
 CheckBox daffodilCheckBox;
 CheckBox sunflowerCheckBox;
 Label totalLabel;
 
 Image image1;
 Image image2;
 Image image3;
 

 //Constants for the ListView dimensions
 final double Width = 1100.0, Height = 300.0;
 final double FONT_SIZE = 20; 
 final double FONT = 50;
 
String LOCATION="fresno,us";
String API_KEY = "14bc3c13c84f557708d9ecc439e10171";
String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=imperial";
 
 

  public static Map<String,Object> jsonToMap(String str){

        Map<String,Object> map = new Gson().fromJson(str,new TypeToken<HashMap<String,Object>> () {}.getType());
        return map;
    }
     
        
    public static void main(String[] args) 
    {
      
           
        //Launch the application
        launch(args);
    
    }
    
    /*public void apiHandler(String apiCall){
        try{
              StringBuilder result = new StringBuilder();
              URL url = new URL(urlString);
              URLConnection conn = url.openConnection();
              try (BufferedReader rd = new BufferedReader(new InputStreamReader (conn.getInputStream()))) {
                  String line;
                  while ((line = rd.readLine()) != null){
                      result.append(line);
                  }
              }
              
          Map<String, Object > respMap = jsonToMap (result.toString());
          Map<String, Object > mainMap = (Map<String, Object >)respMap.get("main");
          Map<String, Object > sysMap = (Map<String, Object >)respMap.get("sys");
          String  nameMap = (String) respMap.get("name");
          List<Map<String, Object >> weather = (List<Map<String, Object>>) (respMap.get("weather"));
          Map<String, Object> weatherMap = weather.get(0);
          
         CityLabel.setText("City: " + nameMap); 
         CountryLabel.setText("Country: " + sysMap.get("country"));         
         WeatherDescriptionLabel.setText("Weather Description: "+ weatherMap.get("main"));          
         TemperatureLabel.setText("Current Temperature: " + mainMap.get("temp") +" F" );
         
         //use weather input to conver to  range of index from 0-1, depending on weather conditions
         Object description =  weatherMap.get("main");
        if (description.equals("Thunderstorm") || description.equals("Snow")||description.equals("Rain") || description.equals("Drizzle") ){
            k=1;
        }            
        else{
            k=0;
        }
          
        //Create Button to complete watering & event handlers to press once you have watered
        waterButton = new Button("Water");
        
        waterButton.setOnAction((EventHandler<ActionEvent>) new WaterButtonHandlerRose());
      
        //Create Button to complete watering
        waterButton2 = new Button("Water");
        waterButton2.setOnAction((EventHandler<ActionEvent>) new WaterButtonHandlerMarigold());
        
        //Create Button to complete watering
        waterButton3 = new Button("Water");
        waterButton3.setOnAction((EventHandler<ActionEvent>) new WaterButtonHandlerPetunia());
          
        
       //weather conditions require water
       if (k == 0){
                           
       
        //Flower 1 Time to Water
       waterButton.setStyle("-fx-base: blue");
      
       //Flower 2 Time to Water
       waterButton2.setStyle("-fx-base: blue");
  
       //Flower 3 Time to Water
       waterButton3.setStyle("-fx-base: blue");
                    }
     //weather conditions don't require watering
     else if (k==1){
    
   
    //Do not need to water any plants today due to weather conditions
         waterButton.setStyle("-fx-base: green");
        waterButton2.setStyle("-fx-base: green");
        waterButton3.setStyle("-fx-base: green");
  
                    }
          }catch (IOException e){
              System.out.println(e.getMessage());
          }
    }
    */
    @Override
    public void start(Stage primaryStage)
    {
        apiHandler hello = new apiHandler();
       
        //ComboBox for Selecting city for weather api
        ComboBox<String> locationComboBox1 = new ComboBox<>();
        locationComboBox1.getItems().addAll("Fresno, US","Denver, US");
       
        locationComboBox1.setOnAction(event->
        {
           String location = locationComboBox1.getValue();
              
         if(location.equals("Fresno, US")){
                LOCATION="fresno,us";
                urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=imperial";
                hello.apiHandle(urlString); 
            }
        else{
                LOCATION = "denver,us";
                urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=imperial";
                hello.apiHandle(urlString); 
            }
            
            
      
        });
          hello.apiHandle(urlString);                        
         
         
        
      //styling for flower labels
       flower1Label.setStyle("-fx-font-size:24pt");
        flower2Label.setStyle("-fx-font-size:24pt");
        flower3Label.setStyle("-fx-font-size:24pt");
      
       //Create Flower Image objects
       
       
        
        //Create Flower ImageView objects
        flower1View = new ImageView();
        flower2View = new ImageView();
        flower3View = new ImageView();
           //Resize flower images
        flower1View.setFitWidth(200);
        flower1View.setPreserveRatio(true);
        
        flower2View.setFitWidth(200);
        flower2View.setPreserveRatio(true);
        
        flower3View.setFitWidth(200);
        flower3View.setPreserveRatio(true);
        
        //Label for city combobox
        selectcityLabel = new Label();
        selectcityLabel.setText("Select a City");
        selectcityLabel.setStyle("-fx-font-size:15pt");
        selectcityLabel.setUnderline(true);
        
        //Label for flowers checkbox
        selectflowersLabel = new Label();
        selectflowersLabel.setText("Select 3 Flowers");
       selectflowersLabel.setStyle("-fx-font-size:15pt");
       selectflowersLabel.setUnderline(true);
       
        //hbox for flower images
        HBox hbox = new HBox(10, flower1View, flower2View,flower3View);
        hbox.setPadding(new Insets(10));
        
      
        
        //label for weather data
        Label weatherdataLabel = new Label("Weather Data");
        weatherdataLabel.setFont(new Font("Impact", 20));
        weatherdataLabel.setUnderline(true);
        
        //hbox for city, country
        HBox hbox4 = new HBox(10, CityLabel, CountryLabel);
        hbox4.setAlignment(Pos.CENTER);
       
        
        //Title Effects
        InnerShadow IS = new InnerShadow();
        Text welcomeText = new Text("Plant Watering");
        welcomeText.setEffect(IS);
        welcomeText.setFont(Font.font("Georgia", FontWeight.BOLD, FONT));
        welcomeText.setFill(Color.CORNFLOWERBLUE);

        //Hbox for Title Text
        welcomeBox = new HBox(10, welcomeText);
        welcomeBox.setAlignment(Pos.CENTER);
       
        //Label for instructions on clicking buttons for watering
        Label waterLabel = new Label("If the water button is blue under a plant, it needs water. "
                + "Once you water it, press the button "
                + "and it will turn green to signify that you have watered it.\n If it is green"
                + ", you do not have to water the plant. ");
        waterLabel.setStyle("-fx-font-size:12pt");
       
       
        //GridPane for watering buttons
        GridPane gridpane = new GridPane();
        gridpane.add(waterButton,37,1);//5
        gridpane.add(waterButton2, 55, 1);//21
        gridpane.add(waterButton3,73,1);//36
        gridpane.setVgap(5);
        gridpane.setHgap(10);
        gridpane.setPadding(new Insets(20));
        
        //Gridpane for flower labels  
        GridPane flowerlabelpane = new GridPane();
        flowerlabelpane.add(flower1Label,36,1);
        flowerlabelpane.add(flower2Label, 49, 1);
        flowerlabelpane.add(flower3Label,60,1);
        flowerlabelpane.setVgap(5);
        flowerlabelpane.setHgap(10);
        flowerlabelpane.setPadding(new Insets(20));
        
        waterLabel.setAlignment(Pos.CENTER);
        waterLabel.setTextAlignment( CENTER );  
        waterLabel.setWrapText(true);
        waterLabel.setPadding(new Insets(10));
        
        //Create the flower Checkboxes
         roseCheckBox = new CheckBox();
         marigoldCheckBox = new CheckBox();
         petuniaCheckBox = new CheckBox();
         daffodilCheckBox = new CheckBox();
         sunflowerCheckBox = new CheckBox();
        roseCheckBox.setText("Rose");
        marigoldCheckBox.setText("Marigold");
        petuniaCheckBox.setText("Petunia");
        daffodilCheckBox.setText("Daffodil");
        sunflowerCheckBox.setText("Sunflower");
        
        
        //Create the Button Control for the flowers, to retrieve the users selections
       flowersbutton = new Button("Get Flowers");
        
        //Register the event handler, for the flower button
       flowersbutton.setOnAction(new FlowersButtonHandler());
        
      //Vbox for city selection combobox 
      VBox vbox2 = new VBox(10,selectcityLabel,locationComboBox1);
      
       
      totalLabel = new Label();
      //HBox for flower checkbox selction
      HBox flowershbox = new HBox(10,selectflowersLabel);
      HBox rosehbox = new HBox(10,roseCheckBox);
      HBox marigoldhbox = new HBox(10, marigoldCheckBox);
      HBox petuniahbox = new HBox(10, petuniaCheckBox);
      HBox daffodilhbox = new HBox(10, daffodilCheckBox);
      HBox sunflowerhbox = new HBox(10, sunflowerCheckBox);
      HBox flowersbuttonhbox = new HBox(10, flowersbutton);
      HBox totallabelhbox = new HBox(10, totalLabel);

     
      //Add controls to Final VBox
      vbox1 = new VBox(10, welcomeBox,vbox2,flowershbox,rosehbox, marigoldhbox,petuniahbox, 
              daffodilhbox, sunflowerhbox,flowersbuttonhbox, totallabelhbox, hbox, flowerlabelpane,
              gridpane, waterLabel, weatherdataLabel, hbox4, WeatherDescriptionLabel, TemperatureLabel);
               
      vbox1.setAlignment(Pos.CENTER);
      vbox1.setPadding(new Insets(10));
       
        
        //Create a Scene and display it
        Scene scene9 = new Scene(vbox1);
        primaryStage.setScene(scene9);
        
        //Set the stage title
        primaryStage.setTitle("Fresno/Denver, US Plant Watering Assistant");
        
         //ListView of VBox
        ListView<VBox> listView1 = new ListView<>();
        listView1.setPrefSize(Width, Height);
        listView1.setOrientation(Orientation.VERTICAL);
        listView1.getItems().addAll(vbox1);
        
        //Stack Pane for VBox
        StackPane root = new StackPane();
        root.getChildren().add(listView1);
        primaryStage.setScene(new Scene(root, 1353, 800));//670,500
         root.setStyle("-fx-control-inner-background: green");
         
        
        //Show the window
        primaryStage.show();        
    }
    
    
    class WaterButtonHandlerRose implements EventHandler<ActionEvent> 
    {
        //changing color of buttons once pressed after you water the flowers
        @Override
        public void handle(ActionEvent event)
        {   //fix once add aarsh's code maybe as an extra class??
        
               waterButton.setStyle("-fx-base: green");
           
}
}
     class WaterButtonHandlerMarigold implements EventHandler<ActionEvent> 
    {
        @Override
        public void handle(ActionEvent event)
        {   
            
           
               waterButton2.setStyle("-fx-base: green");
           
}
}
     
     
   class WaterButtonHandlerPetunia implements EventHandler<ActionEvent> 
    {
        @Override
        public void handle(ActionEvent event)
        {   
            
           
               waterButton3.setStyle("-fx-base: green");
           
}
}  
  
  class FlowersButtonHandler implements EventHandler<ActionEvent>
   {
       @Override
       public void handle(ActionEvent event)
       {
         //array to hold the flower names
          ArrayList<String> ar = new ArrayList<>();
          String s1 = "Rose";
          String s2= "Marigold";
          String s3 = "Petunia";
          String s4 = "Daffodil";
          String s5 = "SunFlower";
          
          //keep track of amount of flowers selected
          int result=0;
          int rose = 0;
          int marigold = 0;
          int petunia = 0;
          int daffodil = 0;
          int sunflower = 0;
          
          if(roseCheckBox.isSelected()){
              rose =1;
              ar.add(s1);
          }
          if(marigoldCheckBox.isSelected()){
             marigold = 1;
              ar.add(s2);
          }
          if(petuniaCheckBox.isSelected()){
              petunia = 1;
              ar.add(s3);
          }
          if(daffodilCheckBox.isSelected()){
              daffodil = 1;
              ar.add(s4);
          }
          if(sunflowerCheckBox.isSelected()){
              sunflower = 1;
              ar.add(s5);
          }
          result = rose + marigold + petunia + daffodil + sunflower;
          
          if(result!=3){
              totalLabel.setText(String.format("Please select 3 flower options.")); //error if select more/less than 3 flowers
          }
          if(result==3){
          //set labels to flowers selected
          flower1Label.setText(ar.get(0));
          flower2Label.setText(ar.get(1));
          flower3Label.setText(ar.get(2));
          
          img1text =(ar.get(0));
          img2text = (ar.get(1));
          img3text= (ar.get(2));
          
          image1 = new Image("file:" + img1text + ".jpg");
          image2 = new Image("file:" + img2text + ".jpg");
          image3 = new Image("file:" + img3text + ".jpg");//"file:Rose.jpg
          
          //Create Flower ImageView objects
        flower1View.setImage(image1);
        flower2View.setImage(image2);
        flower3View.setImage(image3);
          
        
          }
   }
   }
    
    
}



