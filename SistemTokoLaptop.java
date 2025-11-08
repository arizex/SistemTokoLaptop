import java.util.Scanner;

class Laptop {
    private String merk;
    private String tipe;
    private String processor;
    private int ram;
    private int storage;
    private int harga;
 
    public Laptop(String merk, String tipe, String processor, int ram, int storage, int harga) {
        this.merk = merk;
        this.tipe = tipe;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.harga = harga;
    }
    
    public void tampilkanInfo(int nomor) {
        System.out.println(nomor + ". " + merk + " " + tipe);
        System.out.println("   Processor: " + processor);
        System.out.println("   RAM: " + ram + "GB | Storage: " + storage + "GB");
        System.out.println("   Harga: Rp " + String.format("%,d", harga));
        System.out.println();
    }
    

    public String getMerk() {
        return merk;
    }
    
    public String getTipe() {
        return tipe;
    }
    
    public int getHarga() {
        return harga;
    }
}


class Pembeli {
    private String nama;
    private String noTelepon;
    private String alamat;
    
    public void inputData(Scanner input) {
        System.out.print("Nama Pembeli: ");
        nama = input.nextLine();
        
        System.out.print("No. Telepon: ");
        noTelepon = input.nextLine();
        
        System.out.print("Alamat: ");
        alamat = input.nextLine();
    }
    
    public String getNama() {
        return nama;
    }
    
    public String getNoTelepon() {
        return noTelepon;
    }
    
    public String getAlamat() {
        return alamat;
    }
}


class Transaksi {
    private Laptop laptop;
    private int jumlah;
    private String metodePembayaran;
    
    public void setPembelian(Laptop laptop, int jumlah) {
        this.laptop = laptop;
        this.jumlah = jumlah;
    }
    
    public void pilihMetodePembayaran(Scanner input) {
        System.out.println("\n=== METODE PEMBAYARAN ===");
        System.out.println("1. Cash/Transfer");
        System.out.println("2. Cicilan 6 bulan (bunga 5%)");
        System.out.println("3. Cicilan 12 bulan (bunga 10%)");
        System.out.print("Pilihan: ");
        int pilihan = input.nextInt();
        
        switch(pilihan) {
            case 1: metodePembayaran = "Cash/Transfer"; break;
            case 2: metodePembayaran = "Cicilan 6 bulan"; break;
            case 3: metodePembayaran = "Cicilan 12 bulan"; break;
            default: metodePembayaran = "Cash/Transfer";
        }
    }
    

    public int hitungSubtotal() {
        return laptop.getHarga() * jumlah;
    }
    

    public int hitungDiskon() {
        int subtotal = hitungSubtotal();
        int diskon = 0;
        

        if (jumlah >= 5) {
            diskon = subtotal * 15 / 100;
        } else if (jumlah >= 3) {
            diskon = subtotal * 10 / 100;
        } else if (jumlah >= 2) {
            diskon = subtotal * 5 / 100;
        }
        
        return diskon;
    }
    
    public int hitungTotal() {
        return hitungSubtotal() - hitungDiskon();
    }
    
    public int hitungCicilan() {
        int total = hitungTotal();
        
        if (metodePembayaran.equals("Cicilan 6 bulan")) {
            total += total * 5 / 100;
            return total / 6;
        } else if (metodePembayaran.equals("Cicilan 12 bulan")) {
            total += total * 10 / 100;
            return total / 12;
        }
        
        return total;
    }
    
    public void tampilkanNota(Pembeli pembeli) {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║           TOKO LAPTOP COMPSTAR22       ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Nama Pembeli  : " + pembeli.getNama());
        System.out.println("No. Telepon   : " + pembeli.getNoTelepon());
        System.out.println("Alamat        : " + pembeli.getAlamat());
        System.out.println("========================================");
        System.out.println("Produk        : " + laptop.getMerk() + " " + laptop.getTipe());
        System.out.println("Harga Satuan  : Rp " + String.format("%,d", laptop.getHarga()));
        System.out.println("Jumlah        : " + jumlah + " unit");
        System.out.println("----------------------------------------");
        System.out.println("Subtotal      : Rp " + String.format("%,d", hitungSubtotal()));
        
        int diskon = hitungDiskon();
        if (diskon > 0) {
            int persenDiskon = jumlah >= 5 ? 15 : (jumlah >= 3 ? 10 : 5);
            System.out.println("Diskon (" + persenDiskon + "%)   : Rp " + String.format("%,d", diskon));
        }
        
        System.out.println("========================================");
        System.out.println("TOTAL BAYAR   : Rp " + String.format("%,d", hitungTotal()));
        System.out.println("Metode Bayar  : " + metodePembayaran);
        
        if (!metodePembayaran.equals("Cash/Transfer")) {
            int totalDenganBunga = hitungTotal();
            if (metodePembayaran.equals("Cicilan 6 bulan")) {
                totalDenganBunga += totalDenganBunga * 5 / 100;
                System.out.println("Cicilan/bulan : Rp " + String.format("%,d", hitungCicilan()));
                System.out.println("Total + Bunga : Rp " + String.format("%,d", totalDenganBunga));
            } else {
                totalDenganBunga += totalDenganBunga * 10 / 100;
                System.out.println("Cicilan/bulan : Rp " + String.format("%,d", hitungCicilan()));
                System.out.println("Total + Bunga : Rp " + String.format("%,d", totalDenganBunga));
            }
        }
        
        System.out.println("========================================");
        System.out.println("   Terima kasih atas pembeliannya!");
        System.out.println("     Garansi resmi 1 tahun");
        System.out.println("========================================\n");
    }
}

public class TokoLaptop {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║        TOKO LAPTOP COMPSTAR22          ║");
        System.out.println("║     Jual Beli Laptop Terpercaya        ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        Laptop[] daftarLaptop = {
            new Laptop("ASUS", "VivoBook 15", "Intel Core i5-1135G7", 8, 512, 7500000),
            new Laptop("Lenovo", "IdeaPad Slim 3", "AMD Ryzen 5 5500U", 8, 512, 7200000),
            new Laptop("HP", "Pavilion 14", "Intel Core i7-1165G7", 16, 512, 11000000),
            new Laptop("Acer", "Aspire 5", "Intel Core i5-1235U", 8, 512, 7800000),
            new Laptop("MSI", "Modern 14", "Intel Core i7-1255U", 16, 512, 12500000)
        };
        
        System.out.println("=== DAFTAR LAPTOP TERSEDIA ===\n");
        for (int i = 0; i < daftarLaptop.length; i++) {
            daftarLaptop[i].tampilkanInfo(i + 1);
        }
        
        System.out.println("=== DATA PEMBELI ===");
        Pembeli pembeli = new Pembeli();
        pembeli.inputData(input);
        
        System.out.print("\nPilih laptop (1-" + daftarLaptop.length + "): ");
        int pilihanLaptop = input.nextInt();
        
        System.out.print("Jumlah unit yang dibeli: ");
        int jumlah = input.nextInt();
        
        System.out.println("\n💰 INFO DISKON:");
        System.out.println("   - Beli 2 unit = Diskon 5%");
        System.out.println("   - Beli 3-4 unit = Diskon 10%");
        System.out.println("   - Beli 5+ unit = Diskon 15%");
        
        Transaksi transaksi = new Transaksi();
        transaksi.setPembelian(daftarLaptop[pilihanLaptop - 1], jumlah);
        transaksi.pilihMetodePembayaran(input);
        
        transaksi.tampilkanNota(pembeli);
        
        input.close();
    }
}