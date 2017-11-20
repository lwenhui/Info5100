import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;

public class MyJson {
    public static void main(String[] args) throws IOException {
        File file = new File("./Question3_camino.txt");
        ArrayList<Vehicle> vehicles = readAndGetVehicles(file);
        String s = getJsonString(vehicles);
        writeToFile(s, file.getParent() + "/Q3_type1.txt");

        //Extra
        File file1 = new File("./Q3_type1.txt");
        ArrayList<Vehicle> vehicles1 = readAndGetVehiclesFromNewFile(file1);
        String s1 = getNewJsonString(vehicles1);
        writeToFile(s1, file1.getParent() + "/Q3_type2.txt");
    }
    private static ArrayList<Vehicle> readAndGetVehicles(File file) throws IOException{
        ArrayList<Vehicle> arrayList = new ArrayList<>();
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            if (line.contains("id~webId~category~year~make~model~trim~type~price~photo")) {
                continue;
            }

            String[] vehicleInfo = line.split("~");
            Vehicle vehicle = new Vehicle(vehicleInfo);
            arrayList.add(vehicle);
        }
        br.close();
        reader.close();
        return arrayList;
    }
    public static String getJsonString(ArrayList<Vehicle> vehicles){
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{" + "\n");
        jsonBuilder.append("gmps-camino : [" + "\n");
        for (int i = 0; i < vehicles.size(); i++) {
            jsonBuilder.append("{" + "\n");

            jsonBuilder.append("id : " + vehicles.get(i).id + "," + "\n");
            jsonBuilder.append("category : " + vehicles.get(i).category.toString() + "," + "\n");
            jsonBuilder.append("year : " + vehicles.get(i).year + "," + "\n");
            jsonBuilder.append("make : " + vehicles.get(i).make + "," + "\n");
            jsonBuilder.append("model : " + vehicles.get(i).model + "," + "\n");
            if (vehicles.get(i).trim.isEmpty()) {
                jsonBuilder.append("trim : " + "null," + "\n");
            } else {
                jsonBuilder.append("trim : " + vehicles.get(i).trim + "," + "\n");
            }
            if (vehicles.get(i).type.isEmpty()) {
                jsonBuilder.append("type : " + "null," + "\n");
            } else {
                jsonBuilder.append("type : " + vehicles.get(i).type + "," + "\n");
            }
            jsonBuilder.append("price : " + vehicles.get(i).price + "," + "\n");
            jsonBuilder.append("photo : " + vehicles.get(i).photo + "\n");
            if (i == vehicles.size() - 1) {
                jsonBuilder.append("}" + "\n" + "]" + "\n" + "}" + "\n");
            } else {
                jsonBuilder.append("}," + "\n");
            }
        }
        return jsonBuilder.toString();
    }
    public static void writeToFile(String inputToWrite, String filePath)throws IOException{
        File file = new File(filePath);
        FileWriter writer = new FileWriter(file);
        PrintWriter pw = new PrintWriter(writer);
        pw.print(inputToWrite);
        pw.close();
        writer.close();
    }

    //Extra
    public static ArrayList<Vehicle> readAndGetVehiclesFromNewFile(File file) throws IOException{
        ArrayList<Vehicle> arrayList = new ArrayList<>();
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        String[] arr = new String[10];
        arr[1] = "gmps-camino";
        while (true) {
            String line = br.readLine();
            //System.out.println(line);
            if (line == null) {
                break;
            }
            if (line.contains("{")){
                continue;
            }
            if (line.contains("]")) {
                break;
            }
            if (line.contains("}")) {
                Vehicle vehicle = new Vehicle(arr);
                arrayList.add(vehicle);
                arr = new String[10];
                arr[1] = "gmps-camino";
            }else if (line.contains("mps-camino")) {
                continue;
            } else {
                String[] vehicleInfo = line.split(":", 2);
                if (vehicleInfo[0].contains("id")) {
                    arr[0] = vehicleInfo[1].substring(1, vehicleInfo[1].length() - 1);
                } else if (vehicleInfo[0].contains("category")) {
                    arr[2] = vehicleInfo[1].substring(1, vehicleInfo[1].length() - 1);
                } else if (vehicleInfo[0].contains("year")) {
                    arr[3] = vehicleInfo[1].substring(1, vehicleInfo[1].length() - 1);
                } else if (vehicleInfo[0].contains("make")) {
                    arr[4] = vehicleInfo[1].substring(1, vehicleInfo[1].length() - 1);
                } else if (vehicleInfo[0].contains("model")) {
                    arr[5] = vehicleInfo[1].substring(1, vehicleInfo[1].length() - 1);
                } else if (vehicleInfo[0].contains("trim")) {
                    if (vehicleInfo[1].equals("null")) {
                        vehicleInfo[1] = "";
                    }
                    arr[6] = vehicleInfo[1].substring(1, vehicleInfo[1].length() - 1);
                } else if (vehicleInfo[0].contains("type")) {
                    if (vehicleInfo[1].equals("null")) {
                        vehicleInfo[1] = "";
                    }
                    arr[7] = vehicleInfo[1].substring(1, vehicleInfo[1].length() - 1);
                } else if (vehicleInfo[0].contains("price")) {
                    arr[8] = vehicleInfo[1].substring(1, vehicleInfo[1].length() - 1);
                } else if (vehicleInfo[0].contains("photo")){
                    arr[9] = vehicleInfo[1].trim();
                }
            }
        }
        br.close();
        reader.close();
        return arrayList;
    }
    public static String getNewJsonString(ArrayList<Vehicle> vehicles) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("id~webId~category~year~make~model~trim~type~price~photo");
        jsonBuilder.append("\n");
        for (Vehicle vehicle : vehicles) {
            jsonBuilder.append(vehicle.id + "~");
            jsonBuilder.append(vehicle.webId + "~");
            jsonBuilder.append(vehicle.category.toString().toLowerCase() + "~");
            jsonBuilder.append(vehicle.year + "~");
            jsonBuilder.append(vehicle.make + "~");
            jsonBuilder.append(vehicle.model + "~");
            jsonBuilder.append(vehicle.trim + "~");
            jsonBuilder.append(vehicle.type + "~");
            jsonBuilder.append(vehicle.price + "~");
            jsonBuilder.append(vehicle.photo + "\n");
        }
        jsonBuilder.substring(0, jsonBuilder.length() - 1);
        return jsonBuilder.toString();
    }
}
class Vehicle{

    String id;
    String webId;
    Category category;
    Year year;
    String make;
    String model;
    String trim;
    String type;
    double price;
    URL photo;

    Vehicle(String[] arr){
        this.id = arr[0];
        this.webId = arr[1];
        this.category = Category.getCategory(arr[2].toLowerCase());
        this.year = Year.parse(arr[3]);
        this.make = arr[4];
        this.model = arr[5];
        this.trim = arr[6];
        this.type = arr[7];
        this.price = Double.parseDouble(arr[8]);
        try {
            this.photo = new URL(arr[9]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}

enum Category{
    NEW , USED, CERTIFIED;

    public static Category getCategory(String cat){
        switch (cat){
            case "used": return USED;
            case "new": return NEW;
            case "certified": return CERTIFIED;
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        switch (this){
            case NEW: return "NEW";
            case USED: return "USED";
            case CERTIFIED: return "CERTIFIED";
            default: throw new IllegalArgumentException();
        }
    }
}
