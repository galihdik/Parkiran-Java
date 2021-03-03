package com.lawencon.parkiran;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Galih Dika
 *
 */

public class Aplikasi {
	static Scanner inputAngka = new Scanner(System.in);
	static Scanner inputStr = new Scanner(System.in);
	static ArrayList<String> listKendaraan = new ArrayList<String>();
	static ArrayList<String> listPlat = new ArrayList<String>();
	static ArrayList<String> listCi = new ArrayList<String>();
	static ArrayList<String> listCo = new ArrayList<String>();
	static ArrayList<String> listHarga = new ArrayList<String>();
	static String timeStamp;
	static String timeStampp;
	static Date date1 = null;
	static Date date2 = null;
	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int menu = 0;
		System.out.println("====== Selamat Datang di Program Parkiran=====");
		System.out.println("1. Check In Kendaraan");
		System.out.println("2. Check Out Kendaraan");
		System.out.println("3. Laporan Check in dan Check out kendaraan");
		System.out.println("4. Keluar");
		try {
			System.out.println("Pilih menu : ");
			menu = inputAngka.nextInt();
		} catch (Exception e) {
			// TODO: handle exception
			inputAngka.nextLine();
			System.out.println("Tidak ada menu tersebut. silahkan input ulang ! ");
			menu = inputAngka.nextInt();
		}
		while (menu < 1 || menu > 4) {
			System.out.println("Tidak ada menu tersebut. silahkan input ulang ! ");
			menu = inputAngka.nextInt();
		}
		menuu(menu);
	}

	public static void menuu(int menu) {
		if (menu == 1) {
			checkIn();
			Aplikasi.main(null);
		} else if (menu == 2) {
			checkOut();
			Aplikasi.main(null);
		} else if (menu == 3) {
			laporanParkir();
			Aplikasi.main(null);
		} else {
			System.out.println("Terima kasih telah menggunakan program ini");
		}

	}

	public static void checkIn() {
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTimeInMillis());
		String kendaraan = "";
		System.out.println("List jenis kendaraan.");
		System.out.println("1. Mobil ");
		System.out.println("2. Motor");
		System.out.println("Pilih kendaraan : ");
		int pilihKendaraan = inputAngka.nextInt();
		while (pilihKendaraan < 0 || pilihKendaraan > 2) {
			System.out.println("Tidak ada kendaraan lain, silahkan pilih !");
			pilihKendaraan = inputAngka.nextInt();
		}
		if (pilihKendaraan == 1) {
			kendaraan = "Mobil";
		} else if (pilihKendaraan == 2) {
			kendaraan = "Motor";
		}
		System.out.println("Masukan plat nomer kendaraan : ");
		System.out.println("Plat nomer depan : ");
		String platDepan = inputStr.nextLine();
		while (!platDepan.equalsIgnoreCase("B")) {
			System.out.println("Hanya kendaraan dengan plat B yang bisa masuk !");
			platDepan = inputStr.nextLine();
		}
		System.out.println("Plat nomer tengah : ");
		int platTengah = inputAngka.nextInt();
		while (platTengah < 1000 || platTengah > 9999) {
			System.out.println("Plat kendaraan Harus 4 Digit !");
			platTengah = inputAngka.nextInt();
		}

		System.out.println("Plat nomer belakang : ");
		String platBlkg = inputStr.nextLine();
		while (platBlkg.length() != 3) {
			System.out.println("Plat kendaraan belakang hanya bisa  3 digit !");
			platBlkg = inputStr.nextLine();
		}
		String gabungString = platDepan + platTengah + platBlkg;
		System.out.println("jam checkin anda " + timeStamp);
		listKendaraan.add(kendaraan);
		listPlat.add(gabungString);
		listCi.add(timeStamp);
		System.out.println("plat nomer anda " + gabungString);
		System.out.println("Anda berhasil Check in");
	}

	public static void checkOut() {
		for (int i = 0; i < listPlat.size(); i++) {
			if (listPlat.isEmpty()) {
				System.out.println("belum ada kendaraan !");
			} else {
				timeStampp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(Calendar.getInstance().getTimeInMillis());
				System.out.println("Masukan plat kendaraan anda");
				System.out.println("Plat nomer depan : ");
				String platDepan = inputStr.nextLine();
				while (!platDepan.equalsIgnoreCase("B")) {
					System.out.println("Hanya kendaraan dengan plat B yang bisa masuk !");
					platDepan = inputStr.nextLine();
				}
				System.out.println("Plat nomer tengah : ");
				int platTengah = inputAngka.nextInt();
				while (platTengah < 1 || platTengah > 9999) {
					System.out.println("Plat kendaraan tengah maksimal 4 digit dan minimal 1 digit !");
					platTengah = inputAngka.nextInt();
				}
				System.out.println("Plat nomer belakang : ");
				String platBlkg = inputStr.nextLine();
				while (platBlkg.length() != 3) {
					System.out.println("Plat kendaraan belakang hanya bisa  3 digit !");
					platBlkg = inputStr.nextLine();
				}
				String gabungString = platDepan + platTengah + platBlkg;
				if (gabungString.equalsIgnoreCase(listPlat.get(i))) {
					listCo.add(timeStampp);
					System.out.println("Anda cekout pada jam : " + timeStampp);
					System.out.println("Anda berhasil checkout");
					break;
				} else if (!gabungString.equalsIgnoreCase(listPlat.get(i))) {
					System.out.println("Kendaraan tidak ditemukan !");
				}
			}
		}

	}

	public static void laporanParkir() {
		if (listPlat.isEmpty()) {
			System.out.println("Belum ada kendaraan yang masuk !");
		} else {
			System.out.println("Laporan parkir !");

			for (int i = 0; i < listPlat.size(); i++) {
				System.out.println("Plat nomer " + listPlat.get(i));
				System.out.println("Jenis Kendaraan " + listKendaraan.get(i));
				System.out.println("Jam checkin : " + listCi.get(i));
				if (listCo.isEmpty()) {
					System.out.println("Jam Checkout belum ada");
				} else {
					System.out.println("Jam Checkout : " + listCo.get(i));
					try {
						date1 = format.parse(listCi.get(i));
						date2 = format.parse(listCo.get(i));
					} catch (Exception e) {
						e.printStackTrace();
					}
					long diff = date2.getTime() - date1.getTime();
					long diffsec = diff / 1000 % 60;
					long diffMinutes = diff / (60 * 1000) % 60;
					long diffHours = diff / (60 * 60 * 1000);
					Math.ceil(diffHours);
					System.out.println("anda parkir selama : " + diffHours + " jam " + diffMinutes + " menit " + diffsec
							+ " detik");
					if (listKendaraan.get(i).equalsIgnoreCase("Mobil")) {
						if (diffsec < 3600) {
							long totalharga = 5000;
							System.out.println("Total harganya : " + totalharga);
						} else if (diffsec > 3600) {
							long tampungTotal = (long) Math.ceil(diffsec / 3600);
							long totalHarga = 5000 * tampungTotal;
							System.out.println("Total harganya : " + totalHarga);
						}
					} else if (listKendaraan.get(i).equalsIgnoreCase("Motor")) {
						if (diffsec < 3600) {
							long totalharga = 1000;
							System.out.println("Total harganya : " + totalharga);
						} else if (diffsec > 3600) {
							long tampungTotal = (long) Math.ceil(diffsec / 3600);
							long totalHarga = 1000 * tampungTotal;
							System.out.println("Total harganya : " + totalHarga);
						}
					}

				}
			}
		}
	}
}
