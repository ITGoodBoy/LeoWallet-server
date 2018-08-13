package com.celestialapps.leowallet;

import com.celestialapps.leowallet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LeoWalletApplication implements CommandLineRunner {


	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	LastRepository lastRepository;
	@Autowired
	FavoriteRepository favoriteRepository;
	@Autowired
	PromotionalCodeRepository promotionalCodeRepository;

	public static void main(String[] args) {
		SpringApplication.run(LeoWalletApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//
//		user.setName("Serega");
//		user.getRoles().add(Role.USER);
//		user.setAccount("380636062256");
//		user.setBalance(235823);
//		user.setPassword(passwordEncoder.encode("11111111"));
//
//		userRepository.save(user);
//
//		User user2 = new User();
//		user2.getRoles().add(Role.USER);
//		user2.setName("Кирилл");
//		user2.setAccount("380632018542");
//		user2.setBalance(194);
//		user2.setPassword(passwordEncoder.encode("12344321"));
//
//		userRepository.save(user2);
//
//		User user3 = new User();
//		user3.getRoles().add(Role.USER);
//		user3.setName("");
//		user3.setAccount("4567451234562341");
//		user3.setBalance(6789);
//		user3.setPassword(passwordEncoder.encode("12345678"));
//
//		userRepository.save(user3);
//
//		User user4 = new User();
//		user4.getRoles().add(Role.USER);
//		user4.setName("Триолан");
//		user4.setAccount("565712342345");
//		user4.setPassword(passwordEncoder.encode("87654321"));
//
//		userRepository.save(user4);
//
//		User user5 = new User();
//		user5.getRoles().add(Role.USER);
//		user5.setName("Андрей коллега");
//		user5.setAccount("380956069011");
//		user5.setPassword(passwordEncoder.encode("19283753"));
//
//		userRepository.save(user5);
//
//		User user6 = new User();
//		user6.getRoles().add(Role.USER);
//		user6.setName("Сын маминой подруги");
//		user6.setAccount("380632017542");
//		user6.setPassword(passwordEncoder.encode("235367732"));
//
//		userRepository.save(user6);
//
//		User user7 = new User();
//		user7.getRoles().add(Role.USER);
//		user7.setName("Жена");
//		user7.setAccount("456745987123742341");
//		user7.setPassword(passwordEncoder.encode("1256724356735"));
//
//		userRepository.save(user7);
//
//		User user8 = new User();
//		user8.setName("");
//		user8.getRoles().add(Role.USER);
//		user8.setAccount("3527127964485327");
//		user8.setPassword(passwordEncoder.encode("93333672346"));
//
//		userRepository.save(user8);
//
//		List<Invoice> invoices = new ArrayList<>();
//
//		invoices.add(new Invoice(user2.getName(), user2.getAccount()));
//
//		List<Favorite> favorites = new ArrayList<>();
//
//		favorites.add(new Favorite(123, user3.getName(), user3.getAccount()));
//		favorites.add(new Favorite(222, user4.getName(), user4.getAccount()));
//		favorites.add(new Favorite(333, user5.getName(), user5.getAccount()));
//		favorites.add(new Favorite(123, user6.getName(), user6.getAccount()));
//
//		List<Last> lasts = new ArrayList<>();
//
//		lasts.add(new Last(555, user6.getName(), user6.getAccount()));
//		lasts.add(new Last(123, user7.getName(), user7.getAccount()));
//		lasts.add(new Last(123, user8.getName(), user8.getAccount()));
//
//		user.setInvoices(invoices);
//		user.setFavorites(favorites);
//		user.setLasts(lasts);
//
//		invoiceRepository.saveAll(invoices);
//		favoriteRepository.saveAll(favorites);
//		lastRepository.saveAll(lasts);
//
//		userRepository.save(user);
//

//		PromotionalCode promotionalCode = new PromotionalCode("12344321", 157);
//		promotionalCodeRepository.save(promotionalCode);
//
//		promotionalCode = new PromotionalCode("124125137436234", 632);
//		promotionalCodeRepository.save(promotionalCode);
//
//		promotionalCode = new PromotionalCode("116572", 5785);
//		promotionalCodeRepository.save(promotionalCode);
//
//		promotionalCode = new PromotionalCode("12373451", 1235);
//		promotionalCodeRepository.save(promotionalCode);
//
//		promotionalCode = new PromotionalCode("21421415", 8964);
//		promotionalCodeRepository.save(promotionalCode);
//
//		promotionalCode = new PromotionalCode("666444", 11245);
//		promotionalCodeRepository.save(promotionalCode);
//
//		promotionalCode = new PromotionalCode("111222", 74);
//		promotionalCodeRepository.save(promotionalCode);
//
//		promotionalCode = new PromotionalCode("55567467", 4);
//		promotionalCodeRepository.save(promotionalCode);
//
//		promotionalCode = new PromotionalCode("96734524", 1246);
//		promotionalCodeRepository.save(promotionalCode);
//
//		promotionalCode = new PromotionalCode("52352352362", 86132);
//		promotionalCodeRepository.save(promotionalCode);
	}
}
