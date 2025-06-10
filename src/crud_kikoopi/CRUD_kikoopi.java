package crud_kikoopi;

import loginPage.loginAdmin;
import loginPage.loginUser;
import loginPage.register;
import menuKikoopi.tambahMenu;
import menuKikoopi.pesan;
import menuKikoopi.showMenu;
import menuKikoopi.riwayatPesanan;
import ulasan.reviewUser;
import ulasan.showReview;

import java.util.Scanner;
import menuKikoopi.updateMenu;

public class CRUD_kikoopi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        loginUser loggedInUser = null;
        loginAdmin loggedInAdmin = null;

        while (isRunning) {
            System.out.println("==== MENU UTAMA ====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    // Menu Login
                    System.out.println("==== LOGIN ====");
                    System.out.println("1. Login as User");
                    System.out.println("2. Login as Admin");
                    System.out.print("Pilih opsi: ");
                    int loginChoice = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline

                    if (loginChoice == 1) {
                        // Login as User
                        loggedInUser = new loginUser("User", "user@example.com", "user123");
                        if (loggedInUser.userLogin()) {
                            userMenu(scanner, loggedInUser);
                        }
                    } else if (loginChoice == 2) {
                        // Login as Admin
                        loggedInAdmin = new loginAdmin("Admin", "admin@example.com", "admin123");
                        if (loggedInAdmin.adminLogin()) {
                            adminMenu(scanner, loggedInAdmin);
                        }
                    }
                    break;

                case 2:
                    // Menu Register
                    register registration = new register();
                    registration.registerUser();  // Register user
                    break;

                case 3:
                    // Exit
                    System.out.println("Exiting...");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    public static void userMenu(Scanner scanner, loginUser loggedInUser) {
    boolean isUserMenuRunning = true;

    while (isUserMenuRunning) {
        System.out.println("==== MENU USER ====");
        System.out.println("1. Lihat Menu");
        System.out.println("2. Pesan Menu");
        System.out.println("3. Berikan Review");
        System.out.println("4. Lihat Review");
        System.out.println("5. Logout");
        System.out.print("Pilih opsi: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        switch (choice) {
            case 1:
                showMenu showMenu = new showMenu();
                showMenu.tampilkanSemuaMenu();
                break;

            case 2:
                // Pesan Menu
                showMenu tampilkanMenu = new showMenu();
                tampilkanMenu.tampilkanSemuaMenu();
                System.out.print("Masukkan kategori menu (food/drink): ");
                String kategori = scanner.nextLine();
                System.out.println("Masukkan nama Menu");
                String namaMenu = scanner.nextLine();
                
                // Membuat objek pesanan dan memanggil orderFoodOrDrink
                pesan pesanan = new pesan(namaMenu, loggedInUser);  // Membuat objek pesanan
                pesanan.orderFoodOrDrink(kategori);  // Menggunakan kategori untuk pesan makanan/minuman
                break;

            case 3:
                reviewUser review = new reviewUser(loggedInUser);
                review.tambahkanUlasan();
                break;

            case 4:
                showReview showReview = new showReview();
                showReview.tampilkanUlasan();
                break;

            case 5:
                System.out.println("Logout berhasil.");
                isUserMenuRunning = false;
                break;

            default:
                System.out.println("Pilihan tidak valid!");
        }
    }
}


    public static void adminMenu(Scanner scanner, loginAdmin loggedInAdmin) {
        boolean isAdminMenuRunning = true;

        while (isAdminMenuRunning) {
            System.out.println("==== MENU ADMIN ====");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Lihat Menu");
            System.out.println("3. Lihat Riwayat Pesanan");
            System.out.println("4. Lihat Review");
            System.out.println("5. Logout");
            System.out.println("6. Edit Menu");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    tambahMenu tambah = new tambahMenu();
                    tambah.addMenu();
                    break;

                case 2:
                    showMenu show = new showMenu();
                    show.tampilkanSemuaMenu();
                    break;

                case 3:
                    riwayatPesanan riwayat = new riwayatPesanan();
                    riwayat.tampilkanPesanan();
                    break;

                case 4:
                    showReview showReview = new showReview();
                    showReview.tampilkanUlasan();
                    break;

                case 5:
                    System.out.println("Logout berhasil.");
                    isAdminMenuRunning = false;
                    break;
                
                case 6:
                    updateMenu edit = new updateMenu();
                    edit.editMenu();
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}
