import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// BPM Class
class BPM {
    private String Model;
    private String Brand;
    private String Storage;
    private String Display;
    private String Main_Camera;
    private String CPU;
    private String Battery;
    private float Price;
    private int stock;

    // Constructor
    public BPM(String Model, String Brand, String Storage, String Display, String Main_Camera, String CPU, String Battery, float Price, int stock) {
        this.Model = Model;
        this.Brand = Brand;
        this.Storage = Storage;
        this.Display = Display;
        this.Main_Camera = Main_Camera;
        this.CPU = CPU;
        this.Battery = Battery;
        this.Price = Price;
        this.stock = stock;
    }

    // Getters and Setters
    public String getModel() { return Model; }
    public void setModel(String Model) { this.Model = Model; }
    public String getBrand() { return Brand; }
    public void setBrand(String Brand) { this.Brand = Brand; }
    public String getStorage() { return Storage; }
    public void setStorage(String Storage) { this.Storage = Storage; }
    public String getDisplay() { return Display; }
    public void setDisplay(String Display) { this.Display = Display; }
    public String getMain_Camera() { return Main_Camera; }
    public void setMain_Camera(String Main_Camera) { this.Main_Camera = Main_Camera; }
    public String getCPU() { return CPU; }
    public void setCPU(String CPU) { this.CPU = CPU; }
    public String getBattery() { return Battery; }
    public void setBattery(String Battery) { this.Battery = Battery; }
    public float getPrice() { return Price; }
    public void setPrice(float Price) { this.Price = Price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    // String representation
    @Override
    public String toString() {
        return Model + "," + Brand + "," + Storage + "," + Display + "," + Main_Camera + "," + CPU + "," + Battery + "," + Price + "," + stock;
    }
}

// PhoneManagementSystem Class
class PhoneManagementSystem {
    private final String fileName = "phones.csv";

    // Save data to CSV file
    public void saveData(List<BPM> phones) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for (BPM phone : phones) {
            writer.write(phone.toString() + "\n");
        }
        writer.close();
    }

    // Load data from CSV file
    public List<BPM> loadData() throws IOException {
        List<BPM> phoneList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            phoneList.add(new BPM(
                fields[0], fields[1], fields[2], fields[3], fields[4],
                fields[5], fields[6], Float.parseFloat(fields[7]), Integer.parseInt(fields[8])
            ));
        }
        reader.close();
        return phoneList;
    }

    // Add phone
    public void addPhone(BPM phone) throws IOException {
        List<BPM> phoneList = loadData();
        phoneList.add(phone);
        saveData(phoneList);
    }

    // Delete phone
    public void deletePhone(String model) throws IOException {
        List<BPM> phoneList = loadData();
        phoneList.removeIf(phone -> phone.getModel().equals(model));
        saveData(phoneList);
    }

    // Update phone
    public void updatePhone(String model, BPM updatedPhone) throws IOException {
        List<BPM> phoneList = loadData();
        for (BPM phone : phoneList) {
            if (phone.getModel().equals(model)) {
                phone.setModel(updatedPhone.getModel());
                phone.setBrand(updatedPhone.getBrand());
                phone.setStorage(updatedPhone.getStorage());
                phone.setDisplay(updatedPhone.getDisplay());
                phone.setMain_Camera(updatedPhone.getMain_Camera());
                phone.setCPU(updatedPhone.getCPU());
                phone.setBattery(updatedPhone.getBattery());
                phone.setPrice(updatedPhone.getPrice());
                phone.setStock(updatedPhone.getStock());
            }
        }
        saveData(phoneList);
    }

    // Search phone
    public BPM searchPhone(String model) throws IOException {
        List<BPM> phoneList = loadData();
        for (BPM phone : phoneList) {
            if (phone.getModel().equals(model)) {
                return phone;
            }
        }
        return null;
    }
}

// Main Class
public class Phone_Management_System {
    public static void main(String[] args) {
        PhoneManagementSystem pms = new PhoneManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Phone\n2. Delete Phone\n3. Update Phone\n4. Search Phone\n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Model: ");
                        String model = scanner.nextLine();
                        System.out.print("Enter Brand: ");
                        String brand = scanner.nextLine();
                        System.out.print("Enter Storage: ");
                        String storage = scanner.nextLine();
                        System.out.print("Enter Display: ");
                        String display = scanner.nextLine();
                        System.out.print("Enter Main Camera: ");
                        String mainCamera = scanner.nextLine();
                        System.out.print("Enter CPU: ");
                        String cpu = scanner.nextLine();
                        System.out.print("Enter Battery: ");
                        String battery = scanner.nextLine();
                        System.out.print("Enter Price: ");
                        float price = scanner.nextFloat();
                        System.out.print("Enter Stock: ");
                        int stock = scanner.nextInt();
                        BPM newPhone = new BPM(model, brand, storage, display, mainCamera, cpu, battery, price, stock);
                        pms.addPhone(newPhone);
                        break;
                    case 2:
                        System.out.print("Enter Model to delete: ");
                        String delModel = scanner.nextLine();
                        pms.deletePhone(delModel);
                        break;
                    case 3:
                        System.out.print("Enter Model to update: ");
                        String updModel = scanner.nextLine();
                        System.out.println("Enter new details...");
                        System.out.print("Enter Brand: ");
                        String updBrand = scanner.nextLine();
                        System.out.print("Enter Storage: ");
                        String updStorage = scanner.nextLine();
                        System.out.print("Enter Display: ");
                        String updDisplay = scanner.nextLine();
                        System.out.print("Enter Main Camera: ");
                        String updCamera = scanner.nextLine();
                        System.out.print("Enter CPU: ");
                        String updCPU = scanner.nextLine();
                        System.out.print("Enter Battery: ");
                        String updBattery = scanner.nextLine();
                        System.out.print("Enter Price: ");
                        float updPrice = scanner.nextFloat();
                        System.out.print("Enter Stock: ");
                        int updStock = scanner.nextInt();
                        BPM updatedPhone = new BPM(updModel, updBrand, updStorage, updDisplay, updCamera, updCPU, updBattery, updPrice, updStock);
                        pms.updatePhone(updModel, updatedPhone);
                        break;
                    case 4:
                        System.out.print("Enter Model to search: ");
                        String searchModel = scanner.nextLine();
                        BPM foundPhone = pms.searchPhone(searchModel);
                        if (foundPhone != null) {
                            System.out.println("Phone found:\n" + foundPhone);
                        } else {
                            System.out.println("Phone not found.");
                        }
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
